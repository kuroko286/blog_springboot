package com.kuroko.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kuroko.blog.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
