package com.defusername.bookworm.security.auth.repository;

import com.defusername.bookworm.security.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findUserByUsername(String username);

}
