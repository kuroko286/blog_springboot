package com.kuroko.blog.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kuroko.blog.payload.CommentDto;
import com.kuroko.blog.service.CommentService;

@RestController
@RequestMapping("/api/posts")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
            @PathVariable(name = "postId") long postId) {
        return new ResponseEntity<CommentDto>(commentService.createComment(commentDto, postId), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public Set<CommentDto> getCommentsByPostId(@PathVariable(name = "postId") long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(name = "postId") long postId,
            @PathVariable(name = "commentId") long commentId) {
        return ResponseEntity.ok(commentService.getCommentById(commentId));
    }

    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto,
            @PathVariable(name = "postId") long postId, @PathVariable(name = "commentId") long commentId) {
        CommentDto newComment = commentService.updateComment(commentDto, commentId);
        return new ResponseEntity<CommentDto>(newComment, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(name = "postId") long postId,
            @PathVariable(name = "commentId") long commentId) {
        commentService.deleteCommentById(commentId);
        return new ResponseEntity<String>("Delete comment successfullly", HttpStatus.OK);
    }
}
