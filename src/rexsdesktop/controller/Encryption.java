/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Carlos Herrera
 */
public class Encryption {

    public static String toSHA256(String text) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));

        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static boolean compareHash(String pw, String hash) {
        try {
            pw = toSHA256(pw);
            return BCrypt.checkpw(pw, hash);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error hashing: " + ex);
            return false;
        }
    }

    public static String hashPW(String pw) {
        String bcHash = "";
        try {
            String pw256 = toSHA256(pw);
            bcHash = BCrypt.hashpw(pw256, BCrypt.gensalt(12));
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error hashing: " + ex);
        }
        return bcHash;
    }
}
