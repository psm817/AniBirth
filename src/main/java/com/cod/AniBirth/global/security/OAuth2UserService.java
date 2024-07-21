package com.cod.AniBirth.global.security;

import com.cod.AniBirth.global.security.exception.MemberNotFoundException;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
                .getUserNameAttributeName();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String oauthId = oAuth2User.getName();

        Member member = null;
        String oauthType = userRequest.getClientRegistration().getRegistrationId().toUpperCase();

        if (isNew(oauthType, oauthId)) {
            switch (oauthType) {
                case "KAKAO" -> {
                    Map attributesProperties = (Map) attributes.get("properties");
                    Map attributesKakaoAcount = (Map) attributes.get("kakao_account");
                    String nickname = (String) attributesProperties.get("nickname");
                    String username = "KAKAO_%s".formatted(oauthId);

                    member = new Member();
                    member.setUsername(username);
                    member.setNickname(nickname);
                    member.setPassword("");
                    member.setIsActive(1);
                    member.setAuthority(2);
                    member.setThumbnailImg("/images/profile_default.jpg");
                    memberRepository.save(member);
                }
                case "NAVER" -> {
                    String name = (String) ((Map) attributes.get("response")).get("id");

                    String username = "NAVER_%s".formatted(name.substring(0, 3));
                    String nickname =  (String) ((Map) attributes.get("response")).get("name");
                    String email = (String) ((Map) attributes.get("response")).get("email");

                    member = new Member();
                    member.setUsername(username);
                    member.setNickname(nickname);
                    member.setPassword("");
                    member.setEmail(email);
                    member.setIsActive(1);
                    member.setAuthority(2);
                    member.setThumbnailImg("/images/profile_default.jpg");
                    memberRepository.save(member);
                }
            }
        } else {
            member = memberRepository.findByUsername("%s_%s".formatted(oauthType, oauthId))
                    .orElseThrow(MemberNotFoundException::new);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("member"));
        return new MemberContext(member, authorities, attributes, userNameAttributeName);
    }

    private boolean isNew(String oAuthType, String oAuthId) {
        return memberRepository.findByUsername("%s_%s".formatted(oAuthType, oAuthId)).isEmpty();
    }
}