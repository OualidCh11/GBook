package com.example.G_Books.repository;

import com.example.G_Books.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleDao extends JpaRepository<Role,Integer> {

    Optional<Role> findByName(String role);

}
