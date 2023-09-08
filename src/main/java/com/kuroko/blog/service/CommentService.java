package com.kuroko.blog.service;

import java.util.Set;

import com.kuroko.blog.payload.CommentDto;

public interface CommentService {
    public CommentDto createComment(CommentDto commentDto, long postId);

    public Set<CommentDto> getCommentsByPostId(long id);

    public CommentDto getCommentById(long id);

    public CommentDto updateComment(CommentDto commentDto, long id);

    public void deleteCommentById(long id);
}
