package com.cod.AniBirth.article.service;

import com.cod.AniBirth.article.entity.Article;
import com.cod.AniBirth.article.entity.Qa;
import com.cod.AniBirth.article.repository.QaRepository;
import com.cod.AniBirth.global.security.DataNotFoundException;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QaService {

    private final QaRepository qaRepository;
    private final MemberService memberService;

    public Page<Qa> getList(Pageable pageable) {
        return qaRepository.findAll(pageable);
    }

    public List<Qa> getList() {
        return qaRepository.findAll();
    }

    public Qa getQaById(Long id) {
        return qaRepository.findById(id).orElse(null);
    }


    public void saveQa(Qa qa) {
        // ID가 있는 엔티티는 기존에 존재하는 엔티티를 업데이트
        if (qa.getId() != null) {
            Qa existingQa = qaRepository.findById(qa.getId())
                    .orElseThrow(() -> new DataNotFoundException("QA not found"));
            existingQa.setTitle(qa.getTitle());
            existingQa.setContent(qa.getContent());
            qaRepository.save(existingQa);
        }
        else {
//            // ID가 없으면 새로 추가
            qaRepository.save(qa);
        }
    }



    public void deleteQa(Long id) {
        qaRepository.deleteById(id);
    }

    public void create(String title, String content, Member member, int viewCount) {
        Qa qa = Qa.builder()
                .title(title)
                .content(content)
                .member(member)
                .viewCount(viewCount)
                .build();

        qaRepository.save(qa);
    }

    public void increaseViewCount(Long id) {
        Qa qa = qaRepository.findById(id).orElseThrow(() -> new DataNotFoundException("QA not found"));
        qa.setViewCount(qa.getViewCount() + 1);
        qaRepository.save(qa);
    }

    public void edit(Qa existingQa, String title, String content) {
        existingQa.setTitle(title);
        existingQa.setContent(content);
        existingQa.setModifyDate(LocalDateTime.now());

        qaRepository.save(existingQa);

    }

    public void addAdminComment(Long id, String adminComment, Member member) {
        Qa qa = getQaById(id);
        if (qa == null) {
            throw new DataNotFoundException("해당 QA를 찾을 수 없습니다.");
        }
        // 관리자 댓글 추가
        qa.getAdminComments().add(adminComment);
        qa.getCommentAuthors().add(member.getUsername());

        qaRepository.save(qa);
    }



    public void removeComment(Long id, String comment, Member member) {
        Qa qa = getQaById(id);
        int index = qa.getAdminComments().indexOf(comment);
        if (index >= 0 && qa.getCommentAuthors().get(index).equals(member.getUsername())) {
            qa.getAdminComments().remove(index);
            qa.getCommentAuthors().remove(index);
            qaRepository.save(qa);
        } else {
            throw new SecurityException("댓글 삭제 권한이 없습니다.");
        }
    }

}
