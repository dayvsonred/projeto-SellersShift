package com.product.repositories;

import com.product.entities.Images;
import com.product.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImagesRepository extends JpaRepository<Images, UUID> {
    List<Images> findAllByProduct(Product product);
}
