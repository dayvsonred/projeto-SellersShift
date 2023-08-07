package com.payment.repositories;

import com.payment.entities.Comment;
import com.payment.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findAllByProduct(Product product);
}
