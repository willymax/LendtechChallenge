package com.william.lendtech.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author william makau
 * @version 1.0.0
 * Date 2022-07-19
 * Email: william.k.makau@gmail.com
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}