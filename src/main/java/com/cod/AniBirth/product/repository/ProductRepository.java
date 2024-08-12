package com.cod.AniBirth.product.repository;


import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);

    @Query("""
            select distinct p
            from Product p
            where p.title like %:kw%
            or p.description like %:kw%
            """)
    Page<Product> findAllByKeyword(@Param("kw") String kw, Pageable pageable);

    List<Product> findByMember(Member member);

    @Query(value = "SELECT p.* FROM product p JOIN review r ON p.id = r.product_id GROUP BY p.id ORDER BY AVG(r.star_rating) DESC, RAND() LIMIT :limit", nativeQuery = true)
    List<Product> findTopRatedProducts(@Param("limit") int limit);

    Page<Product> findByCategory(String food, Pageable pageable);

    @Query("SELECT p FROM Product p " +
            "LEFT JOIN p.reviewList r " +
            "GROUP BY p " +
            "ORDER BY COALESCE(AVG(r.starRating), 0) DESC")
    Page<Product> findAllByHighRating(Pageable pageable, String kw);

    @Query("SELECT p FROM Product p " +
            "LEFT JOIN p.reviewList r " +
            "WHERE p.category = :category " +
            "GROUP BY p " +
            "ORDER BY COALESCE(AVG(r.starRating), 0) DESC")
    Page<Product> findAllByCategoryToHighRating(@Param("category") String category, Pageable pageable);
}
