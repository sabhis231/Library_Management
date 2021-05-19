/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sabhis.Library_Management.utils;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author sabhis231
 */
public class CommonUtil {

    public static final String AES = "AES";
    public static final String key = "DB99A2A8EB6904F492E9DF0595ED683C";

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }

    private static byte[] hexStringToByteArray(String s) {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(s.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }

    public static String plainToHashPassword(String passwordPlain) {
        String password = null;
        try {
            byte[] bytekey = hexStringToByteArray(CommonUtil.key);

            SecretKeySpec sks = new SecretKeySpec(bytekey, CommonUtil.AES);

            Cipher cipher = Cipher.getInstance(CommonUtil.AES);
            cipher.init(Cipher.ENCRYPT_MODE, sks, cipher.getParameters());

            byte[] encrypted = cipher.doFinal(passwordPlain.getBytes());

            password = byteArrayToHexString(encrypted);
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex) {
            System.out.println(ex);
        } finally {
            return password;
        }
    }

    public static String hashToPlainPassword(String hexPassword) {
        String hashPassword = null;
        try {
            byte[] bytekey = hexStringToByteArray(CommonUtil.key);
            SecretKeySpec sks = new SecretKeySpec(bytekey, CommonUtil.AES);
            Cipher cipher = Cipher.getInstance(CommonUtil.AES);
            cipher.init(Cipher.DECRYPT_MODE, sks);
            byte[] decrypted = cipher.doFinal(hexStringToByteArray(hexPassword));
            hashPassword = new String(decrypted);
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            return hashPassword;
        }

    }
}
