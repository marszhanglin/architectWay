# RSA非对称加密 #

----------

## 一、对称与非对称加密的区别 ##
**对称加密算法**：在加密和解密时使用的是同一个秘钥，加解密双方必须使用同一个密钥才能进行正常的沟通（如DES）

**非对称加密算法**：需要两个密钥来进行加密和解密，分别是公钥和私钥（如RSA、Elgamal、背包算法、Rabin、D-H、ECC（椭圆曲线加密算法））


## 二、RSA数论事实 ##

将两个大素数相乘十分容易，但是想要对其乘积进行因式分解却极其困难，因此可以将乘积公开作为加密密钥

## 三、RSA的使用场景 ##

1、公钥加密------私钥解密

    第一步：由甲方生成公私对 
    （两组byte[]----(经BASE64加密)-----两串密钥字符串）
    第二步：甲方将公钥字符串Pbs给到乙方
    第三步：乙方要将数据用公钥加密后传递给加密
    (数据字符串A---（转成byte[]）-----Ab)
	(公钥字符串Pbs----(用Base64解密)----公钥字节数组Pb-----
	--(X509EncodedKeySpec)---Pk)
	(最后：Ab---（Pk加密）---Ab_Pk)

	









2、私钥加密------公钥解密


3、私钥签名------私钥验证签名


## 四、过程对象介绍 ##

**X509EncodedKeySpec**：




## 五、RSA使用java ##


**相关代码：**

	import java.security.Key;
	import java.security.KeyFactory;
	import java.security.KeyPair;
	import java.security.KeyPairGenerator;
	import java.security.NoSuchAlgorithmException;
	import java.security.PrivateKey;
	import java.security.PublicKey;
	import java.security.Signature;
	import java.security.interfaces.RSAPrivateKey;
	import java.security.interfaces.RSAPublicKey;
	import java.security.spec.PKCS8EncodedKeySpec;
	import java.security.spec.X509EncodedKeySpec;
	import java.util.HashMap;
	import java.util.Map;
	
	import javax.crypto.Cipher;
	
	import sun.misc.BASE64Decoder;
	import sun.misc.BASE64Encoder;

	public class RSA {  
    /** 
     * 定义加密方式 
     */  
    private final static String KEY_RSA = "RSA";  
    /** 
     * 定义签名算法 
     */  
    private final static String KEY_RSA_SIGNATURE = "MD5withRSA";  
    /** 
     * 定义公钥算法 
     */  
    private final static String KEY_RSA_PUBLICKEY = "RSAPublicKey";  
    /** 
     * 定义私钥算法 
     */  
    private final static String KEY_RSA_PRIVATEKEY = "RSAPrivateKey";  
  
    /** 
     * 初始化密钥 
     * @return 
     */  
    public static Map<String, Object> init() {  
        Map<String, Object> map = null;  
        try {  
            KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_RSA);  
            generator.initialize(1024);  
            KeyPair keyPair = generator.generateKeyPair();  
            // 公钥  
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  
            // 私钥  
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  
            // 将密钥封装为map  
            map = new HashMap<String, Object>();  
            map.put(KEY_RSA_PUBLICKEY, publicKey);  
            map.put(KEY_RSA_PRIVATEKEY, privateKey);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
        return map;  
    }  
  
    /** 
     * 用私钥对信息生成数字签名 
     * @param data 加密数据 
     * @param privateKey 私钥 
     * @return 
     */  
    public static String sign(byte[] data, String privateKey) {  
        String str = "";  
        try {  
            // 解密由base64编码的私钥  
            byte[] bytes = decryptBase64(privateKey);  
            // 构造PKCS8EncodedKeySpec对象  
            PKCS8EncodedKeySpec pkcs = new PKCS8EncodedKeySpec(bytes);  
            // 指定的加密算法  
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);  
            // 取私钥对象  
            PrivateKey key = factory.generatePrivate(pkcs);  
            // 用私钥对信息生成数字签名  
            Signature signature = Signature.getInstance(KEY_RSA_SIGNATURE);  
            signature.initSign(key);  
            signature.update(data);  
            str = encryptBase64(signature.sign());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return str;  
    }  
  
    /** 
     * 校验数字签名 
     * @param data 加密数据 
     * @param publicKey 公钥 
     * @param sign 数字签名 
     * @return 校验成功返回true，失败返回false 
     */  
    public static boolean verify(byte[] data, String publicKey, String sign) {  
        boolean flag = false;  
        try {  
            // 解密由base64编码的公钥  
            byte[] bytes = decryptBase64(publicKey);  
            // 构造X509EncodedKeySpec对象  
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);  
            // 指定的加密算法  
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);  
            // 取公钥对象  
            PublicKey key = factory.generatePublic(keySpec);  
            // 用公钥验证数字签名  
            Signature signature = Signature.getInstance(KEY_RSA_SIGNATURE);  
            signature.initVerify(key);  
            signature.update(data);  
            flag = signature.verify(decryptBase64(sign));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return flag;  
    }  
  
    /** 
     * 私钥解密 
     * @param data 加密数据 
     * @param key 私钥 
     * @return 
     */  
    public static byte[] decryptByPrivateKey(byte[] data, String key) {  
        byte[] result = null;  
        try {  
            // 对私钥解密  
            byte[] bytes = decryptBase64(key);  
            // 取得私钥  
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);  
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);  
            PrivateKey privateKey = factory.generatePrivate(keySpec);  
            // 对数据解密  
            Cipher cipher = Cipher.getInstance(factory.getAlgorithm());  
            cipher.init(Cipher.DECRYPT_MODE, privateKey);  
            result = cipher.doFinal(data);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
  
    /** 
     * 私钥解密 
     * @param data 加密数据 
     * @param key 公钥 
     * @return 
     */  
    public static byte[] decryptByPublicKey(byte[] data, String key) {  
        byte[] result = null;  
        try {  
            // 对公钥解密  
            byte[] bytes = decryptBase64(key);  
            // 取得公钥  
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);  
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);  
            PublicKey publicKey = factory.generatePublic(keySpec);  
            // 对数据解密  
            Cipher cipher = Cipher.getInstance(factory.getAlgorithm());  
            cipher.init(Cipher.DECRYPT_MODE, publicKey);  
            result = cipher.doFinal(data);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
  
    /** 
     * 公钥加密 
     * @param data 待加密数据 
     * @param key 公钥 
     * @return 
     */  
    public static byte[] encryptByPublicKey(byte[] data, String key) {  
        byte[] result = null;  
        try {  
            byte[] bytes = decryptBase64(key);  
            // 取得公钥  
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);  
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);  
            PublicKey publicKey = factory.generatePublic(keySpec);  
            // 对数据加密  
            Cipher cipher = Cipher.getInstance(factory.getAlgorithm());  
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
            result = cipher.doFinal(data);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
  
    /** 
     * 私钥加密 
     * @param data 待加密数据 
     * @param key 私钥 
     * @return 
     */  
    public static byte[] encryptByPrivateKey(byte[] data, String key) {  
        byte[] result = null;  
        try {  
            byte[] bytes = decryptBase64(key);  
            // 取得私钥  
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);  
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);  
            PrivateKey privateKey = factory.generatePrivate(keySpec);  
            // 对数据加密  
            Cipher cipher = Cipher.getInstance(factory.getAlgorithm());  
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);  
            result = cipher.doFinal(data);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
  
    /** 
     * 获取公钥 
     * @param map 
     * @return 
     */  
    public static String getPublicKey(Map<String, Object> map) {  
        String str = "";  
        try {  
            Key key = (Key) map.get(KEY_RSA_PUBLICKEY);  
            str = encryptBase64(key.getEncoded());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return str;  
    }  
  
    /** 
     * 获取私钥 
     * @param map 
     * @return 
     */  
    public static String getPrivateKey(Map<String, Object> map) {  
        String str = "";  
        try {  
            Key key = (Key) map.get(KEY_RSA_PRIVATEKEY);  
            str = encryptBase64(key.getEncoded());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return str;  
    }  
  
    /** 
     * BASE64 解密 
     * @param key 需要解密的字符串 
     * @return 字节数组 
     * @throws Exception 
     */  
    public static byte[] decryptBase64(String key) throws Exception {  
        return (new BASE64Decoder()).decodeBuffer(key);  
    }  
  
    /** 
     * BASE64 加密 
     * @param key 需要加密的字节数组 
     * @return 字符串 
     * @throws Exception 
     */  
    public static String encryptBase64(byte[] key) throws Exception {  
        return (new BASE64Encoder()).encodeBuffer(key);  
    }  
  
    /** 
     * 测试方法 
     * @param args 
     */  
    public static void main(String[] args) {  
        String privateKey = "";  
        String publicKey = "";  
        // 生成公钥私钥  
        Map<String, Object> map = init();  
        publicKey = getPublicKey(map);  
        privateKey = getPrivateKey(map);  
        System.out.println("公钥: \n\r" + publicKey);  
        System.out.println("私钥： \n\r" + privateKey);  
        System.out.println("公钥加密--------私钥解密");  
        String word = "你好，世界！";  
        byte[] encWord = encryptByPublicKey(word.getBytes(), publicKey);  
        String decWord = new String(decryptByPrivateKey(encWord, privateKey));  
        System.out.println("加密前: " + word + "\n\r" + "解密后: " + decWord);  
        System.out.println("私钥加密--------公钥解密");  
        String english = "Hello, World!";  
        byte[] encEnglish = encryptByPrivateKey(english.getBytes(), privateKey);  
        String decEnglish = new String(decryptByPublicKey(encEnglish, publicKey));  
        System.out.println("加密前: " + english + "\n\r" + "解密后: " + decEnglish);  
        System.out.println("私钥签名——公钥验证签名");  
        // 产生签名  
        String sign = sign(encEnglish, privateKey);  
        System.out.println("签名:\r" + sign);  
        // 验证签名  
        boolean status = verify(encEnglish, publicKey, sign);  
        System.out.println("状态:\r" + status);  
    }  
	}





**运行结果：**


    
    公钥: 
    
    MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXonkWEFYvhBAPRtsI/ZjCDGF7hi6+F4DHRtcG
    gwLwabliZ1jI1LMNhP/pYFFfH2Ka89R6PUsqLFKALwsuUMZm7+cRB7KRird6uuF6pcpL9iiTCTKi
    hvrDMmfzMbBVcykPZIfiKwm2rGrE1NbQsvubDy20aDS2ctW/pUn88XdYVQIDAQAB
    
    私钥： 
    
    MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJeieRYQVi+EEA9G2wj9mMIMYXuG
    Lr4XgMdG1waDAvBpuWJnWMjUsw2E/+lgUV8fYprz1Ho9SyosUoAvCy5Qxmbv5xEHspGKt3q64Xql
    ykv2KJMJMqKG+sMyZ/MxsFVzKQ9kh+IrCbasasTU1tCy+5sPLbRoNLZy1b+lSfzxd1hVAgMBAAEC
    gYBUgHyOKSh+blAs00W5Kun4KcIhflOKJ7riUr39qQD4TVK8Xe+Ca9YkkJu0yLJQGkNvKracMJA0
    vmJzgMPwWZ9WXdLF6nDfShBuQdvPVTh+ccBSMkprFXQ85GRPPjCcPi92qJUFuzBAloJ49orQHY/X
    6RdDUdpT/xLD8K83W/gOAQJBANULDLuj6XfOfVQKZw/hUAX0tbVJJRPze6Lgai3SNnhCk0qYYG4n
    2+IXPq3ak7ijhQmw4d53ROIiiNOjF7DLSZUCQQC2NZ0b8E6yTrkdB6hnTF3mpnvdIA5gy2b1bLvJ
    ycbIA3vrMtoDfuysVRHfPOcqUTTXsYfhG8rMztaf8lheL6PBAkA9vbsN8j3qE7Ssk2s05fZcE4RJ
    qo4NTyYImQMMjnxss9ad6rUfl3uY9WWb2tmuy+z1VwbOg0A6TQncU8q82N1VAkBk3s9oHSPj8FLo
    cEnZTdyaCMZ/hvSOv2vzKEvZjRu/tLYQYelxj/2D9eBb/oJW2ffwBLcddt5RfZwxld833RXBAkB0
    M5Tji43uU7bwQttugKss0bcESdGwPhCJU3fOUSrdqJgmJPwN233Mte8oCoqFBlpeMFj5tAD1wIS5
    deiOOvTG
    
    公钥加密--------私钥解密
    加密前: 你好，mars！
    
    解密后: 你好，mars！
    私钥加密--------公钥解密
    加密前: Hello, mars!
    
    解密后: Hello, mars!
    私钥签名——公钥验证签名
    签名:
    FLSdnWQijV85NdB3hu43+3fT2MaM4V3vureeidjdJ21pDcm0H7EKq6d7gD7l10j1IydR9sEwDhIV
    0naEqfOeV4ACcu8plANcLVjGIT4AqPMeTmn8h0azxrk/QHFaEl1WEzYYlY4/16+HBff2kniblQtV
    BeWmMfS6u3cAReTqRss=
    
    状态:
    true



























