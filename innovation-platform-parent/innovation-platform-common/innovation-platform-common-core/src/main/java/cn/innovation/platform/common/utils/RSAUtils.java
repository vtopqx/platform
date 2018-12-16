package cn.innovation.platform.common.utils;

import org.apache.commons.lang3.ArrayUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @description:
 * @date: 2018/7/4-18:06
 * @modifyBy:
 */
public class RSAUtils {
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";
    private static String RSA = "RSA";

    public static KeyPair generateRSAKeyPair() throws Exception {
        return generateRSAKeyPair(1024);
    }

    public static KeyPair generateRSAKeyPair(int keyLength) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
        kpg.initialize(keyLength);
        return kpg.genKeyPair();
    }


    public static byte[] encryptData(byte[] data, PublicKey publicKey) throws Exception {
        byte[] dataReturn = new byte[0];
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        // 加密时超过117字节就报错。为此采用分段加密的办法来加密
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i += 100) {
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i,
                    i + 100));
            sb.append(new String(doFinal));
            dataReturn = ArrayUtils.addAll(dataReturn, doFinal);
        }

        return dataReturn;
    }

    public static String encryptData(String data, PublicKey publicKey) throws Exception {
        byte[] bytes = RSAUtils.encryptData(data.getBytes(), publicKey);
        return b64Encoder(bytes);
    }

    public static byte[] decryptData(byte[] encryptedData, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        // 解密时超过128字节就报错。为此采用分段解密的办法来解密
        byte[] dataReturn = new byte[0];
        for (int i = 0; i < encryptedData.length; i += 128) {
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(encryptedData, i,
                    i + 128));
            dataReturn = ArrayUtils.addAll(dataReturn, doFinal);
        }

        return dataReturn;
    }

    public static String decryptData(String encryptedData, PrivateKey privateKey) throws Exception {
        byte[] bytes = RSAUtils.decryptData(b64Decoder(encryptedData), privateKey);
        return  new String(bytes);
    }

    /**
     * 验证数字签名函数入口
     *
     * @param plainBytes 待验签明文字节数组
     * @param signBytes  待验签签名后字节数组
     * @param publicKey  验签使用公钥
     * @return 验签是否通过
     * @throws Exception
     */
    public static boolean verifyDigitalSign(byte[] plainBytes, byte[] signBytes, PublicKey publicKey) throws Exception {
        boolean isValid = false;
        try {
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initVerify(publicKey);
            signature.update(plainBytes);
            isValid = signature.verify(signBytes);
            return isValid;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception(String.format("验证数字签名时没有[%s]此类算法", SIGN_ALGORITHMS));
        } catch (InvalidKeyException e) {
            throw new Exception("验证数字签名时公钥无效");
        } catch (SignatureException e) {
            throw new Exception("验证数字签名时出现异常");
        }
    }

    public static String rsaSign(byte[] encryptByte, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
        signature.initSign(privateKey);
        signature.update(encryptByte);
        byte[] signed = signature.sign();
        return b64Encoder(signed);
    }

    public static PublicKey getPublicKey(byte[] keyBytes) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        return keyFactory.generatePublic(keySpec);
    }

    public static PrivateKey getPrivateKey(byte[] keyBytes) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        return keyFactory.generatePrivate(keySpec);
    }

    public static PublicKey getPublicKey(String modulus, String publicExponent)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        BigInteger bigIntModulus = new BigInteger(modulus);
        BigInteger bigIntPrivateExponent = new BigInteger(publicExponent);
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        return keyFactory.generatePublic(keySpec);
    }

    public static PrivateKey getPrivateKey(String modulus, String privateExponent)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        BigInteger bigIntModulus = new BigInteger(modulus);
        BigInteger bigIntPrivateExponent = new BigInteger(privateExponent);
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        return keyFactory.generatePrivate(keySpec);
    }

    public static PublicKey loadPublicKey(String publicKeyStr) throws Exception {
        byte[] buffer = b64Decoder(publicKeyStr);
        return getPublicKey(buffer);
    }

    public static PrivateKey loadPrivateKey(String privateKeyStr) throws Exception {
        byte[] buffer = b64Decoder(privateKeyStr);
        return getPrivateKey(buffer);
    }

    public static PublicKey loadPublicKey(InputStream in) throws Exception {
        return loadPublicKey(readKey(in));
    }

    public static PrivateKey loadPrivateKey(InputStream in) throws Exception {
        return loadPrivateKey(readKey(in));
    }

    public static byte[] b64Decoder(String str) throws IOException {
        return (new BASE64Decoder()).decodeBuffer(str);
    }

    /**
     * 字节数组转b64 ;注意：根据RFC822规定，BASE64Encoder编码每76个字符，会加上一个回车换行。
     * 使用的密码最终是二进制的，就算有换行，编码<->解码已经消掉；所以有无换行符不重要；b64转换只是一个中间形态，byte[]<->b64能正确转换就行
     * @param bytes
     * @return
     */
    public static String b64Encoder(byte[] bytes){
        return new BASE64Encoder().encode(bytes).replaceAll("[\r\n]","");
    }

    private static String readKey(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        while (br.ready()) {
            String readLine = br.readLine();
            if (readLine.charAt(0) == '-') {
                continue;
            } else {
                //注意这里去掉了换行符号，会不会丢失信息？使得这个串（b64）转byte[]密码不正确？
                //目前没发现问题
                sb.append(readLine);
            }
        }

        return sb.toString();
    }


   /* public  static  void main(String[ ] asdfs) throws Exception {
        PublicKey publicKey = RSAUtils.loadPublicKey(new FileInputStream(new File("C:\\Users\\laosy\\Desktop\\RSA_key\\our_public_key.pem")));

        PrivateKey privateKey = RSAUtils.loadPrivateKey(new FileInputStream(new File("C:\\Users\\laosy\\Desktop\\RSA_key\\our_private_key.pkcs8")));


        String test = "{\"biz_channel\":\"YX-SZBN18\",\"partner_pin\":\"2982761753489408\",\"partner_no\":\"93\",\"card_no\":null,\"card_level\":null,\"apply_name\":null,\"apply_id_no\":null,\"apply_phone_no\":\"408693929947136\",\"bank_apply_no\":null,\"callback\":\"http://rehuhu.free.ngrok.cc/cmsb/appliedCb\",\"field1\":null,\"field2\":null,\"field3\":null}";
        //加密
//        String encryptStr = (new BASE64Encoder()).encodeBuffer(RSAUtils.encryptData(test.getBytes(), publicKey));
        String encryptStr = encryptData(test,publicKey);
        System.out.println(encryptStr);
        System.out.println();

        //解密
//        String s = new String(RSAUtils.decryptData((new BASE64Decoder()).decodeBuffer(encryptStr), privateKey));
        String s = decryptData(encryptStr,privateKey);
        System.out.println(s);

    }*/
}
