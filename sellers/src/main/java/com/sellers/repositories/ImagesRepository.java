package com.sellers.repositories;

import com.sellers.entities.Images;
import com.sellers.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ImagesRepository extends JpaRepository<Images, UUID> {
    List<Images> findAllByProduct(Product product);
}
