package com.example.shop.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDto extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
