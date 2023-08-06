package com.sellers.service;

import com.sellers.dto.CommentDto;
import com.sellers.dto.UserDto;
import com.sellers.entities.Comment;
import com.sellers.entities.Product;
import com.sellers.repositories.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    private OauthService oauthService;

    public Comment create(CommentDto commentDto, String token) throws IOException {
        try {
            UserDto user = this.oauthService.getUserByToken(token);

            return this.commentRepository.save(Comment.builder()
                    .active(true)
                    .comment(commentDto.getComment())
                    .user(user.getId())
                    .product(Product.builder().id(commentDto.getProduct().getId()).build())
                    .createdDate(LocalDateTime.now())
                    .lastUpdatedDate(LocalDateTime.now())
                    .build());
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR create comment from user: "+ commentDto.getUser());
        }
    }

    public List<Comment> findCommentByProduct(UUID product, String token){
        try {
            return this.commentRepository.findAllByProduct(Product.builder().id(product).build());
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR Find comment from product: "+ product);
        }
    }
}
