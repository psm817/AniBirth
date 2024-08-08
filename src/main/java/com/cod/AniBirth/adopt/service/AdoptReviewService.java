package com.cod.AniBirth.adopt.service;

import com.cod.AniBirth.adopt.entity.AdoptReview;
import com.cod.AniBirth.adopt.repository.AdoptReviewRepository;
import com.cod.AniBirth.animal.entity.Animal;
import com.cod.AniBirth.global.security.DataNotFoundException;
import com.cod.AniBirth.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

        if( adoptReview.isPresent()) {
            return adoptReview.get();
        } else {
            throw new DataNotFoundException("adoptReview not found");
        }
    }
}
