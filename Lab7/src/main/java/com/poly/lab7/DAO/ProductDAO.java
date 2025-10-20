package com.poly.lab7.DAO;

import com.poly.lab7.Entity.Product;
import com.poly.lab7.Entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {

    // Bài 1 - JPQL
    @Query("FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
    List<Product> findByPrice(double minPrice, double maxPrice);

    // Bài 2 - JPQL
    @Query("FROM Product o WHERE o.name LIKE ?1")
    Page<Product> findByKeywords(String keywords, Pageable pageable);

    // Bài 3 - Tổng hợp
    @Query("SELECT o.category AS group, sum(o.price) AS sum, count(o) AS count " +
            "FROM Product o GROUP BY o.category ORDER BY sum(o.price) DESC")
    List<Report> getInventoryByCategory();

    // Bài 4 - DSL
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    // Bài 5 - DSL
    Page<Product> findAllByNameLike(String keywords, Pageable pageable);
}
