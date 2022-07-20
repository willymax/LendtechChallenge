package com.william.lendtech.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author william makau
 * @version 1.0.0
 * Date 2022-07-20
 * Email: william.k.makau@gmail.com
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    /**
     * The tests are not EXHAUSTIVE
     */

    /**
     * Missing Web Layer tests
     */
    @Test
    void findByUsername() {
        User theUser = new User(-1, "William", "Makau", "willymax", "password");
        userRepository.save(theUser);
        User fromDatabase = userRepository.findByUsername(theUser.getUsername());
        assertEquals(theUser.getUsername(), fromDatabase.getUsername());
    }
}