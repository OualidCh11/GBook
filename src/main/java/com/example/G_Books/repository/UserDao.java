package com.example.G_Books.repository;

import com.example.G_Books.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User,Integer> {

    Optional<User>  findByEmail(String email);
}
