package com.cod.AniBirth.volunteer.service;

import com.cod.AniBirth.global.security.DataNotFoundException;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.volunteer.entity.Volunteer;
import com.cod.AniBirth.volunteer.entity.VolunteerReview;
import com.cod.AniBirth.volunteer.repository.VolunteerReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VolunteerReviewService {
    private final VolunteerReviewRepository volunteerReviewRepository;

    @Value("${custom.genFileDirPath}")
    private String genFileDirPath;

    public void create(String title, String body, int hit, Member member, MultipartFile thumbnailImg, List<String> subImages) {
        String thumbnailRelPath = "images/volunteer/" + UUID.randomUUID().toString() + ".jpg";
        File thumbnailFile = new File(genFileDirPath + "/" + thumbnailRelPath);

        File parentDir = thumbnailFile.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        try {
            thumbnailImg.transferTo(thumbnailFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        VolunteerReview volunteerReview = VolunteerReview.builder()
                .title(title)
                .body(body)
                .hit(hit)
                .writer(member)
                .thumbnailImg(thumbnailRelPath)
                .subImages(subImages)
                .build();

        volunteerReviewRepository.save(volunteerReview);
    }

    public VolunteerReview createI(String title, String body, int hit, Member member, MultipartFile thumbnailImg, List<String> subImages) {
        String thumbnailRelPath = "images/volunteer/" + UUID.randomUUID().toString() + ".jpg";
        File thumbnailFile = new File(genFileDirPath + "/" + thumbnailRelPath);

        File parentDir = thumbnailFile.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        try {
            thumbnailImg.transferTo(thumbnailFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        VolunteerReview volunteerReview = VolunteerReview.builder()
                .title(title)
                .body(body)
                .hit(hit)
                .writer(member)
                .thumbnailImg(thumbnailRelPath)
                .subImages(subImages)
                .build();

        return volunteerReviewRepository.save(volunteerReview);
    }

    public VolunteerReview createProd(String title, String body, int hit, Member member, String thumbnailImg, List<String> subImages) throws IOException {
        MultipartFile thumbnail = getMultipartFile(thumbnailImg);

        VolunteerReview volunteerReview = createI(
                title,
                body,
                hit,
                member,
                thumbnail,
                subImages
        );

        return volunteerReview;
    }

    private MultipartFile getMultipartFile(String filePath) throws IOException {
        // 절대 경로를 직접 사용함으로 System.getProperty("user.dir") 제거
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("파일이 존재하지 않습니다: " + file.getAbsolutePath());
            throw new IOException("파일이 존재하지 않습니다: " + file.getAbsolutePath());
        }
        FileInputStream input = new FileInputStream(file);
        return new MockMultipartFile(
                file.getName(),
                file.getName(),
                "image/jpeg",
                input
        );
    }

    public void create(String title, String body, int hit, Member member, String thumbnailImg, List<String> subImages) {
        VolunteerReview volunteerReview = VolunteerReview.builder()
                .title(title)
                .body(body)
                .hit(hit)
                .writer(member)
                .thumbnailImg(thumbnailImg)
                .subImages(subImages)
                .build();

        volunteerReviewRepository.save(volunteerReview);
    }

    public Page<VolunteerReview> getAll(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 6, Sort.by(sorts));

        return volunteerReviewRepository.findAll(pageable);
    }

    public VolunteerReview getReviewById(Long id) {
        Optional<VolunteerReview> ovr = volunteerReviewRepository.findById(id);

        if(ovr.isPresent()) {
            return ovr.get();
        } else {
            throw new DataNotFoundException("review not found");
        }
    }

    public void hit(VolunteerReview volunteerReview) {
        volunteerReview.setHit(volunteerReview.getHit() + 1);

        volunteerReviewRepository.save(volunteerReview);
    }

    public void modify(VolunteerReview volunteerReview, Member member, String title, String body, MultipartFile imageFileName, List<String> subImageNames) {
        volunteerReview.setWriter(member);
        volunteerReview.setTitle(title);
        volunteerReview.setBody(body);
        volunteerReview.setSubImages(subImageNames);

        if (imageFileName != null && !imageFileName.isEmpty()) {
            String thumbnailRelPath = "images/volunteer/" + UUID.randomUUID().toString() + ".jpg";
            File thumbnailFile = new File(genFileDirPath + "/" + thumbnailRelPath);

            File parentDir = thumbnailFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            try {
                imageFileName.transferTo(thumbnailFile);
                volunteerReview.setThumbnailImg(thumbnailRelPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        volunteerReviewRepository.save(volunteerReview);
    }

    public void delete(VolunteerReview volunteerReview) {
        volunteerReviewRepository.delete(volunteerReview);
    }

    public VolunteerReview getPreviousVR(Long id) {
        return volunteerReviewRepository.findFirstByIdLessThanOrderByIdDesc(id);
    }

    public VolunteerReview getNextVR(Long id) {
        return volunteerReviewRepository.findFirstByIdGreaterThanOrderByIdAsc(id);
    }

    public List<VolunteerReview> getRecentVolunteerReviews() {
        return volunteerReviewRepository.findTop4ByOrderByCreateDateDesc();
    }
}
