package com.william.lendtech.security.util;

/**
 * @author william makau
 * @version 1.0.0
 * Date 2022-07-19
 */
public class SecurityConstants {
    public static final String LOGIN_REQUEST_URI = "/login";

    public static final String REGISTRATION_REQUEST_URI = "/register";

    public static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;

    public static final String SECRET_KEY = "mySecretKey";

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";
}
