package com.william.lendtech.user;

/**
 * @author william makau
 * @version 1.0.0
 * Date 2022-07-19
 * Email: william.k.makau@gmail.com
 */
public interface UserService {
    User saveUser(User user);
    User getUser(String username);
}
