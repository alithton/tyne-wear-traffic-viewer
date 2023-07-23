package com.afsmith.tyneweartrafficviewer.business.services;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * Service for generating the salted hash of a password. Based on code provided
 * in {@see https://stackoverflow.com/questions/18142745/how-do-i-generate-a-salt-in-java-for-salted-hash}.
 */
public class PasswordHashingService {

    private static final int ITERATIONS = 10_000;
    private static final int KEY_LENGTH = 256;
    private static final SecureRandom RANDOM = new SecureRandom();

    public String hash(String password, String salt) {
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), ITERATIONS, KEY_LENGTH);
        SecretKey key = getKey(keySpec);
        return bytesToBase64(key.getEncoded());
    }

    public String getSalt() {
        byte[] saltBytes = getRandomBytes(16);
        return bytesToBase64(saltBytes);
    }

    public String getToken(int len) {
        byte[] token = getRandomBytes(len);
        return bytesToBase64(token);
    }


    private byte[] getRandomBytes(int len) {
        byte[] salt = new byte[len];
        RANDOM.nextBytes(salt);
        return salt;
    }

    private SecretKey getKey(KeySpec keySpec) {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return keyFactory.generateSecret(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Failed to generate key.", e);
        }
    }

    private String bytesToBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
}
