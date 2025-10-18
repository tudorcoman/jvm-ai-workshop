package com.workshop.ai.repository;

import com.workshop.ai.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>  {
}
