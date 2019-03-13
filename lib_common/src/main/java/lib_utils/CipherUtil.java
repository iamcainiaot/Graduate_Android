package lib_utils;

import android.util.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 加密工具类
 * Created by shenhua_yu on 2018/3/14.
 */
public class CipherUtil {

    private static final String TAG = CipherUtil.class.getName();

    private CipherUtil() {
        throw new RuntimeException("you cannot new CipherUtil!");
    }

//    static {
//        System.loadLibrary("_cipher");
//    }

    /**
     * DES加密
     *
     * @param oriData 需要加密的数据
     * @return 加密后的数据
     */
    public static String desEncrypt(String oriData) {
        try {
            return Base64.encodeToString(
                    encryptNative(oriData.getBytes("UTF-8")),
                    Base64.DEFAULT);
        } catch (Exception e) {
            MyLogUtil.e(TAG, e);
            return oriData;
        }
    }

    /**
     * DES解密
     *
     * @param encryptData 加密后的数据
     * @return 解密后的数据
     */
    public static String desDecrypt(String encryptData) {
        try {
            return new String(
                    decryptNative(Base64.decode(encryptData.getBytes("UTF-8"),
                            Base64.DEFAULT)),
                    "UTF-8");
        } catch (Exception e) {
            MyLogUtil.e(TAG, e);
            return encryptData;
        }
    }

    /**
     * 封装秘钥
     *
     * @param key     秘钥
     * @param desType 加密方式
     * @return 秘钥对象
     * @throws Exception 异常
     */
    private static Key toKey(byte[] key, String desType) throws Exception {
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(desType);
        return keyFactory.generateSecret(dks);
    }

    /**
     * DES加密，NDK中调用,方法名与参数列表不得更改
     *
     * @param data 明文
     * @return 密文
     * @throws Exception 异常
     */
    private static byte[] encrypt(byte[] data, byte[] key, byte[] py, String desType, String desMode) throws Exception {
        Key k = toKey(key, desType);
        Cipher cipher = Cipher.getInstance(desMode);
        IvParameterSpec iv = new IvParameterSpec(py);
        cipher.init(Cipher.ENCRYPT_MODE, k, iv);//加密模式
        return cipher.doFinal(data);
    }

    /**
     * DES解密，NDK中调用,方法名与参数列表不得更改
     *
     * @param data 密文
     * @return 明文
     * @throws Exception 异常
     */
    private static byte[] decrypt(byte[] data, byte[] key, byte[] py, String desType, String desMode) throws Exception {
        Key k = toKey(key, desType);
        Cipher cipher = Cipher.getInstance(desMode);
        IvParameterSpec iv = new IvParameterSpec(py);
        cipher.init(Cipher.DECRYPT_MODE, k, iv);//解密模式
        return cipher.doFinal(data);
    }

    /**
     * 加密
     *
     * @param source 明文
     * @return 密文
     */
    private native static byte[] encryptNative(byte[] source);

    /**
     * 解密
     *
     * @param source 密文
     * @return 明文
     */
    private native static byte[] decryptNative(byte[] source);
}
