package com.william.lendtech.user;

import java.util.List;

/**
 * @author william makau
 * @version 1.0.0
 * Date 2022-07-19
 * Email: william.k.makau@gmail.com
 */
public interface UserService {
    User saveUser(User user);
    User getUserProfile();
    User getUser(String username);
    List<User> findAll();

    float getCurrentBalance();
}
