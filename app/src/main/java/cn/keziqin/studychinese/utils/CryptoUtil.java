/**
 *
 */
package cn.keziqin.studychinese.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 对字符串进行加密处理
 */
public class CryptoUtil {
    private static String salt = "chunxiaoyinianjuhuacan,moshijincunkongduimei,dashudeganqingbuyaoshanghai,yinweitakenengshiniba!";

    /**
     * Generate MD5 of a string
     *
     * @param str
     * @return
     */
  /* md5 加密 */
    public static String MD5(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            // return getEncode16(digest);
            return getEncode32(digest);
        } catch (Exception e) {
        }
        return null;

    }

    /**
     * 32位加密
     *
     * @param digest
     * @return
     */
    private static String getEncode32(MessageDigest digest) {
        StringBuilder builder = new StringBuilder();
        for (byte b : digest.digest()) {
            builder.append(Integer.toHexString((b >> 4) & 0xf));
            builder.append(Integer.toHexString(b & 0xf));
        }
        return builder.toString();
    }

    /**
     * 16位加密
     *
     * @param digest
     * @return
     */
    private static String getEncode16(MessageDigest digest) {
        StringBuilder builder = new StringBuilder();
        for (byte b : digest.digest()) {
            builder.append(Integer.toHexString((b >> 4) & 0xf));
            builder.append(Integer.toHexString(b & 0xf));
        }
        // 16位加密，从第9位到25位
        return builder.substring(8, 24);
    }

    /**
     * @param s
     * @return
     */
    public static String SHA256(String s) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");

            digest.reset();

            digest.update(s.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] mdbytes = digest.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    /**
     * 散列加密
     *
     * @param s
     * @return
     */
    public static String hash(String s) {
        // return SHA256(MD5(salt + s + String.valueOf(s.length())) + MD5(s + String.valueOf(s.length())
        // + salt));
        return SHA256(MD5(salt + s + oct(s.length())) + MD5(s + hex(s.length())) + salt);
    }

    /**
     * 10进制转16进制 传入int
     *
     * @param i
     */
    private static String hex(int i) {
        return "0x" + Integer.toHexString(i);
    }

    /**
     * 10进制转8进制 传入 int
     *
     * @param i
     */
    private static String oct(int i) {
        return "0" + Integer.toOctalString(i);
    }

    /**
     * 对称加密
     *
     * @param seed
     * @param clearText
     * @return
     * @throws Exception
     */
    public static String encrypt(String seed, String clearText) throws Exception {
        byte[] rawkey = getRawKey(seed.getBytes());
        byte[] result = encrypt(rawkey, clearText.getBytes());
        return toHex(result);

    }

    /**
     * 对称解密
     *
     * @param seed
     * @param encrypted
     * @return
     * @throws Exception
     */
    public static String decrypt(String seed, String encrypted) throws Exception {
        byte[] rawKey = getRawKey(seed.getBytes());
        byte[] enc = toByte(encrypted);
        byte[] result = decrypt(rawKey, enc);
        return new String(result);
    }

    private static byte[] getRawKey(byte[] seed) throws Exception {
        // TODO Auto-generated method stub
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(seed);
        kgen.init(128, sr);
        SecretKey sKey = kgen.generateKey();
        byte[] raw = sKey.getEncoded();

        return raw;
    }

    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }

    private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    public static String toHex(String txt) {
        return toHex(txt.getBytes());
    }

    public static String fromHex(String hex) {
        return new String(toByte(hex));
    }

    public static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
        return result;
    }

    public static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        final String HEX = "0123456789ABCDEF";
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }
}
