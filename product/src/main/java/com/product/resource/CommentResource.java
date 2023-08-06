package com.product.resource;

import com.product.dto.CommentDto;
import com.product.entities.Comment;
import com.product.entities.Images;
import com.product.service.CommentService;
import com.product.service.ImagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/comment")
public class CommentResource {

    @Autowired
    CommentService commentService;

    @PostMapping(value = "/product/{product}")
    public ResponseEntity<Comment> create(
            @RequestParam("file") MultipartFile file,
            @PathVariable CommentDto commentDto,
            @RequestHeader(value = "Authorization") String token) throws IOException {
        return ResponseEntity.ok(this.commentService.create( commentDto, token));
    }

    @GetMapping(value = "/product/{product}")
    public ResponseEntity<List<Comment>> getImages(
            @PathVariable UUID product,
            @RequestHeader(value = "Authorization") String token) throws IOException {
        return ResponseEntity.ok(this.commentService.findCommentByProduct(product, token));
    }

}
