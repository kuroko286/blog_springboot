package com.kuroko.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "posts", uniqueConstraints = { @UniqueConstraint(columnNames = { "title" }) })
public class Post {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "title", nullable = false)
        private String title;

        @Column(name = "description", nullable = false)
        private String description;

        @Column(name = "content", nullable = false)
        private String content;

        @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
        private Set<Comment> comments = new HashSet<>();
}
