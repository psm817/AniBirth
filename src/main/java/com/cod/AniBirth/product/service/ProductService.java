package com.cod.AniBirth.product.service;


import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.product.entity.Product;
import com.cod.AniBirth.product.repository.ProductRepository;
import com.cod.AniBirth.review.entity.Review;
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
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    @Value("${custom.genFileDirPath}")
    private String genFileDirPath;

    public Page<Product> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 8, Sort.by(sorts));

        return productRepository.findAllByKeyword(kw, pageable);
    }

    public void create(String title, String description, int price, MultipartFile thumbnail, Member member, int shippingFee) {
        String thumbnailRelPath = "product/" + UUID.randomUUID().toString() + ".jpg";
        File thumbnailFile = new File(genFileDirPath + "/" + thumbnailRelPath);

        File parentDir = thumbnailFile.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        try {
            thumbnail.transferTo(thumbnailFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Product product = Product.builder()
                .title(title)
                .description(description)
                .price(price)
                .thumbnailImg(thumbnailRelPath)
                .member(member)
                .shippingFee(shippingFee) // 배송비 추가
                .build();
        productRepository.save(product);
    }

    public void create(String title, String description, int price, int shippingFee) {
        Product p = Product.builder()
                .title(title)
                .description(description)
                .price(price)
                .shippingFee(shippingFee)
                .thumbnailImg("product/9f0ad987-997c-4344-8f33-27210dc928b0.jpg")
                .build();
        productRepository.save(p);
    }

    public Product getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return product.get();
        } else {
            throw new RuntimeException("product not found");
        }
    }

    public void modify(Long id, String title, String description, int price, MultipartFile thumbnail, int shippingFee) {
        Product product = getProduct(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setShippingFee(shippingFee); // 배송비 수정

        if (thumbnail != null && !thumbnail.isEmpty()) {
            String thumbnailRelPath = "product/" + UUID.randomUUID().toString() + ".jpg";
            File thumbnailFile = new File(genFileDirPath + "/" + thumbnailRelPath);

            File parentDir = thumbnailFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            try {
                thumbnail.transferTo(thumbnailFile);
                product.setThumbnailImg(thumbnailRelPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        productRepository.save(product);
    }
    public void delete(Long id) {
        Product product = getProduct(id);
        productRepository.delete(product);
    }


    public double calculateAverageStarRating(Product product) {
        List<Review> reviews = product.getReviewList();
        if (reviews == null || reviews.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (Review review : reviews) {
            sum += review.getStarRating();
        }
        return sum / reviews.size();
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
