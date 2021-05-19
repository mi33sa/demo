package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Comment;

public interface Commentrepository extends JpaRepository<Comment, Long> {

}
