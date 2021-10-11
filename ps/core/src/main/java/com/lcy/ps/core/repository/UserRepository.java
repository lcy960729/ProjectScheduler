package com.lcy.ps.core.repository;

import com.lcy.ps.core.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailLike(String email);
}
