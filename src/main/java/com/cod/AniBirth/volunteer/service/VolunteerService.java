package com.cod.AniBirth.volunteer.service;

import com.cod.AniBirth.global.security.DataNotFoundException;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.volunteer.entity.Volunteer;
import com.cod.AniBirth.volunteer.repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VolunteerService {
    private final VolunteerRepository volunteerRepository;

    @Value("${custom.genFileDirPath}")
    private String genFileDirPath;

    public Volunteer create(String title, String content, String location, String startDate,
                            String endDate, String deadLineDate, MultipartFile thumbnailImg, int limit, Member member, int applicant) {
        String thumbnailRelPath = "images/volunteer/" + UUID.randomUUID().toString() + ".jpg";
        File thumbnailFile = new File(genFileDirPath + "/" +  thumbnailRelPath);

        File parentDir = thumbnailFile.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        try {
            thumbnailImg.transferTo(thumbnailFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Volunteer volunteer = Volunteer.builder()
                .title(title)
                .content(content)
                .location(location)
                .startDate(startDate)
                .endDate(endDate)
                .deadLineDate(deadLineDate)
                .thumbnailImg(thumbnailRelPath)
                .limit(limit)
                .member(member)
                .applicant(applicant)
                .build();

        return volunteerRepository.save(volunteer);
    }

    public Volunteer create(String title, String content, String location, String startDate,
                            String endDate, String deadLineDate, String thumbnailImg, int limit, Member member, int applicant) {
        Volunteer volunteer = Volunteer.builder()
                .title(title)
                .content(content)
                .location(location)
                .startDate(startDate)
                .endDate(endDate)
                .deadLineDate(deadLineDate)
                .thumbnailImg(thumbnailImg)
                .limit(limit)
                .member(member)
                .applicant(applicant)
                .build();

        return volunteerRepository.save(volunteer);
    }

    public Page<Volunteer> getAllVolunteer(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 4, Sort.by(sorts));

        return volunteerRepository.findAll(pageable);
    }

    public List<Volunteer> getAllVolunteer() {
        return volunteerRepository.findAll();
    }

    public Volunteer getVolunteerById(Long id) {
        Optional<Volunteer> ov = volunteerRepository.findById(id);

        if(ov.isPresent()) {
            return ov.get();
        } else {
            throw new DataNotFoundException("volunteer not found");
        }
    }

    public void modify(Volunteer volunteer, String title, String content, String location, String startDate,
                       String endDate, String deadLineDate, MultipartFile imageFileName, int limit, Member member, int size) {
        volunteer.setTitle(title);
        volunteer.setContent(content);
        volunteer.setLocation(location);
        volunteer.setStartDate(startDate);
        volunteer.setEndDate(endDate);
        volunteer.setDeadLineDate(deadLineDate);
        volunteer.setLimit(limit);
        volunteer.setMember(member);
        volunteer.setApplicant(size);
        volunteer.setModifyDate(LocalDateTime.now());

        if (imageFileName != null && !imageFileName.isEmpty()) {
            String thumbnailRelPath = "images/volunteer/" + UUID.randomUUID().toString() + ".jpg";
            File thumbnailFile = new File(genFileDirPath + "/" + thumbnailRelPath);

            File parentDir = thumbnailFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            try {
                imageFileName.transferTo(thumbnailFile);
                volunteer.setThumbnailImg(thumbnailRelPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        volunteerRepository.save(volunteer);
    }

    public void delete(Volunteer volunteer) {
        volunteerRepository.delete(volunteer);
    }

    public List<Volunteer> getVolunteerByMember(Member member) {
        return volunteerRepository.findByMember(member);
    }

    public List<Volunteer> getRecentVolunteers() {
        return volunteerRepository.findTop4ByOrderByCreateDateDesc();
    }
}
