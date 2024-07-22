package com.example.G_Books.repository;

import com.example.G_Books.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenDao extends JpaRepository<Token,Integer> {

    Optional<Token> findByToken(String token);
}
