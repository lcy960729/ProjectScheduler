package com.lcy.projectscheduler.security.repository;

import com.lcy.projectscheduler.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailLike(String email);
}
