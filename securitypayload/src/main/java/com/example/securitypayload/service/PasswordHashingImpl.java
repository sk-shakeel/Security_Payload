package com.example.securitypayload.service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class PasswordHashingImpl implements PasswordHashing{
    @Override
    public String hashedPassword(String password) {
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");

            byte[] passwordBytes = password.getBytes();

            sha1.update(passwordBytes);

            byte[] hashedBytes = sha1.digest();

            StringBuilder hexBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                hexBuilder.append(String.format("%02x", b));
            }

            return hexBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
