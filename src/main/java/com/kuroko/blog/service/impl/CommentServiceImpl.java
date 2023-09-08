package com.kuroko.blog.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import com.kuroko.blog.entity.Comment;
import com.kuroko.blog.entity.Post;
import com.kuroko.blog.exception.ResourceNotFoundException;
import com.kuroko.blog.payload.CommentDto;
import com.kuroko.blog.repository.CommentRepository;
import com.kuroko.blog.repository.PostRepository;
import com.kuroko.blog.service.CommentService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Comment newComment = mapToEntity(commentDto);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        newComment.setPost(post);
        commentRepository.save(newComment);
        return mapToDTO(newComment);
    }

    @Override
    public Set<CommentDto> getCommentsByPostId(long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        Set<Comment> comments = post.getComments();
        Set<CommentDto> responseComments = comments.stream().map((comment) -> mapToDTO(comment))
                .collect(Collectors.toSet());
        return responseComments;
    }

    @Override
    public CommentDto getCommentById(long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId));
        return mapToDTO(comment);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId));
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        comment.setName(commentDto.getName());

        Comment newComment = commentRepository.save(comment);
        return mapToDTO(newComment);
    }

    @Override
    public void deleteCommentById(long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId));
        commentRepository.delete(comment);
    }

    private CommentDto mapToDTO(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setBody(comment.getBody());
        commentDto.setEmail(comment.getEmail());
        commentDto.setName(comment.getName());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        comment.setName(commentDto.getName());
        return comment;
    }

}
