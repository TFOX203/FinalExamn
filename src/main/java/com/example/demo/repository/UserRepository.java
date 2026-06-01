package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.domain.User;

/*
 *
 * @author Valenciano
 * 29 may 2026
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
