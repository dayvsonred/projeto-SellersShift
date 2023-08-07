package com.sellers.repositories;

import com.sellers.entities.Product;
import com.sellers.entities.Sold;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SoldRepository extends JpaRepository<Sold, UUID> {
    Page<Sold> findAllByOrderByLastUpdatedDateAsc(Pageable pageable);
    Page<Sold> findAllByProduct(UUID product, Pageable pageable);
    Page<Sold> findAllByUser(UUID user, Pageable pageable);
}
