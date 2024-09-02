package com.cod.AniBirth.adopt.service;

import com.cod.AniBirth.adopt.entity.AdoptReview;
import com.cod.AniBirth.adopt.repository.AdoptReviewRepository;
import com.cod.AniBirth.animal.entity.Animal;
import com.cod.AniBirth.global.security.DataNotFoundException;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.volunteer.entity.VolunteerReview;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdoptReviewService {
    private final AdoptReviewRepository adoptReviewRepository;

    @Value("${custom.genFileDirPath}")
    private String genFileDirPath;

    public void create(String title, String content, MultipartFile images, Member member) {

        String thumbnailRelPath = "images/adoptreview/" + UUID.randomUUID().toString() + ".jpg";
        File thumbnailFile = new File(genFileDirPath + "/" + thumbnailRelPath);

        File parentDir = thumbnailFile.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        try {
            images.transferTo(thumbnailFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        AdoptReview ar = AdoptReview.builder()
                .title(title)
                .content(content)
                .writer(member)
                .images(thumbnailRelPath)
                .build();

        adoptReviewRepository.save(ar);
    }

    public String uploadImage(MultipartFile image) {
        if (image.isEmpty()) {
            return "";
        }

        String saveFilename = "images/adoptreview/" + UUID.randomUUID().toString() + ".jpg";
        String fileFullPath = Paths.get(genFileDirPath + "/" + saveFilename).toString();

        File dir = new File(genFileDirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            File uploadFile = new File(fileFullPath);
            image.transferTo(uploadFile);
            return saveFilename;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public AdoptReview createI(String title, String content, MultipartFile images, Member member) {

        String thumbnailRelPath = "images/adoptreview/" + UUID.randomUUID().toString() + ".jpg";
        File thumbnailFile = new File(genFileDirPath + "/" + thumbnailRelPath);

        File parentDir = thumbnailFile.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        try {
            images.transferTo(thumbnailFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        AdoptReview ar = AdoptReview.builder()
                .title(title)
                .content(content)
                .writer(member)
                .images(thumbnailRelPath)
                .build();

        return adoptReviewRepository.save(ar);
    }

    public AdoptReview createProd(String title, String content, String thumbnailImg, Member member) throws IOException {
        MultipartFile thumbnail = getMultipartFile(thumbnailImg);

        AdoptReview ar = createI(
                title,
                content,
                thumbnail,
                member
        );

        return ar;
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

    public void create(String title, String content, String images, Member member) {


        AdoptReview ar = AdoptReview.builder()
                .title(title)
                .content(content)
                .writer(member)
                .images(images)
                .build();

        adoptReviewRepository.save(ar);
    }

    @Transactional
    public void incrementViews(Long id) {
        AdoptReview adoptReview = adoptReviewRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("AdoptReview not found"));
        adoptReview.setHit(adoptReview.getHit() + 1);
        adoptReviewRepository.save(adoptReview);
    }

    public Page<AdoptReview> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 12, Sort.by(sorts));

        return adoptReviewRepository.findAll(pageable);
    }

    public AdoptReview getreview(Long id) {
        Optional<AdoptReview> adoptReview = adoptReviewRepository.findById(id);

        if (adoptReview.isPresent()) {
            return adoptReview.get();
        } else {
            throw new DataNotFoundException("adoptReview not found");
        }
    }


    public List<AdoptReview> getRecentAdoptReviews() {
        return adoptReviewRepository.findTop4ByOrderByCreateDateDesc();
    }

    public AdoptReview getPreviousVR(Long id) {
        return adoptReviewRepository.findFirstByIdLessThanOrderByIdDesc(id);
    }

    public AdoptReview getNextVR(Long id) {
        return adoptReviewRepository.findFirstByIdGreaterThanOrderByIdAsc(id);
    }

    public AdoptReview getAdoptReviews(Long id) {
        Optional<AdoptReview> adoptReview = this.adoptReviewRepository.findById(id);
        if (adoptReview.isPresent()) {
            return adoptReview.get();
        } else {
            throw new DataNotFoundException("해당되는 리뷰를 찾을 수 없습니다");
        }
    }

    @Transactional
    public void modify(AdoptReview adoptReview, String title, String content, MultipartFile images, Member member) {
        adoptReview.setTitle(title);
        adoptReview.setContent(content);


        if (!images.isEmpty()) {
            try {
                // 기존 썸네일 파일 삭제
                if (adoptReview.getImages() != null) {
                    Path oldFilePath = Paths.get(genFileDirPath, adoptReview.getImages());
                    Files.deleteIfExists(oldFilePath);
                }

                // 새로운 썸네일 파일 저장
                String originalFileName = images.getOriginalFilename();
                String fileName = System.currentTimeMillis() + "_" + originalFileName;
                Path filePath = Paths.get(genFileDirPath, fileName);
                Files.copy(images.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                adoptReview.setImages(fileName);
            } catch (IOException e) {
                throw new RuntimeException("Failed to save thumbnail file", e);
            }
        }
        this.adoptReviewRepository.save(adoptReview);
    }

    public void delete(AdoptReview adoptReview) {
        this.adoptReviewRepository.delete(adoptReview);
    }

    public void hit(AdoptReview adoptReview) {
        adoptReview.setHit(adoptReview.getHit() + 1);

        adoptReviewRepository.save(adoptReview);
    }
}
