package com.product.repositories;

import com.product.entities.Product;
import com.product.entities.ProductViews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductViewsRepository extends JpaRepository<ProductViews, UUID> {

    List<ProductViews> findAllByProduct(Product product);
}
