package com.cod.AniBirth.product.service;


import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.order.entity.OrderItem;
import com.cod.AniBirth.order.repository.OrderRepository;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    @Value("${custom.genFileDirPath}")
    private String genFileDirPath;

    public Page<Product> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 16, Sort.by(sorts));

        return productRepository.findAllByKeyword(kw, pageable);
    }

    public Page<Product> getListByHighPrice(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("price"));
        Pageable pageable = PageRequest.of(page, 16, Sort.by(sorts));

        return productRepository.findAllByKeyword(kw, pageable);
    }

    public void create(String title, String description, int price, String category, MultipartFile thumbnail, Member member, int shippingFee) {
        String thumbnailRelPath = "images/product/" + UUID.randomUUID().toString() + ".jpg";
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
                .category(category)
                .thumbnailImg(thumbnailRelPath)
                .member(member)
                .shippingFee(shippingFee) // 배송비 추가
                .hitCount(0)
                .build();
        productRepository.save(product);
    }

    public Product create(String title, String description, int price, String category, Member member, int shippingFee) {
        Product product = Product.builder()
                .title(title)
                .description(description)
                .price(price)
                .category(category)
                .member(member)
                .shippingFee(shippingFee)
                .thumbnailImg("images/product/sample_product.jpg")
                .hitCount(0)
                .build();
        productRepository.save(product);

        return product;
    }

    public Product getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return product.get();
        } else {
            throw new RuntimeException("product not found");
        }
    }

    public void modify(Long id, String title, String description, int price, String category, MultipartFile thumbnail, int shippingFee) {
        Product product = getProduct(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
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

    public void plusHit(Product product) {
        product.setHitCount(product.getHitCount() + 1);

        productRepository.save(product);
    }

    public List<Product> getProductsByMember(Member member) {
        return productRepository.findByMember(member);
    }

    public List<Product> getPurchasedProductsByMember(Member member) {
        return orderRepository.findPurchasedProductsByMember(member);
    }

    public List<Product> getTopRatedProducts(int limit) {
        return productRepository.findTopRatedProducts(limit);
    }

    public Page<Product> getFoodCategory(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 16, Sort.by(sorts));

        return productRepository.findByCategory("food", pageable);
    }

    public Page<Product> getFoodCategoryByHighPrice(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("price"));
        Pageable pageable = PageRequest.of(page, 16, Sort.by(sorts));

        return productRepository.findByCategory("food", pageable);
    }

    public Page<Product> getFoodCategoryByLowPrice(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.asc("price"));
        Pageable pageable = PageRequest.of(page, 16, Sort.by(sorts));

        return productRepository.findByCategory("food", pageable);
    }

    public Page<Product> getFoodCategoryByHighHit(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("hitCount"));
        Pageable pageable = PageRequest.of(page, 16, Sort.by(sorts));

        return productRepository.findByCategory("food", pageable);
    }

    public Page<Product> getFoodCategoryByHighRating(int page) {
        Pageable pageable = PageRequest.of(page, 16);
        return productRepository.findAllByCategoryToHighRating("food", pageable);
    }

    public Page<Product> getAccessoryCategory(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 16, Sort.by(sorts));

        return productRepository.findByCategory("accessory", pageable);
    }

    public Page<Product> getListByLowPrice(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.asc("price"));
        Pageable pageable = PageRequest.of(page, 16, Sort.by(sorts));

        return productRepository.findAllByKeyword(kw, pageable);
    }

    public Page<Product> getListByHighRating(int page, String kw) {
        Pageable pageable = PageRequest.of(page, 16);
        return productRepository.findAllByHighRating(pageable, kw);
    }

    public Page<Product> getListByHighHit(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("hitCount"));
        Pageable pageable = PageRequest.of(page, 16, Sort.by(sorts));

        return productRepository.findAllByKeyword(kw, pageable);
    }
}
