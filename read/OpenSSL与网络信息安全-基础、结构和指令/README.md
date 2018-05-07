
 




Openssl包含：
SSL协议库、密码算法、应用程序




### 2-3章  密码学的知识 ###

### 4-6章  OpenSSL安装使用 ###

### 7-12章 OpenSSL具体使用   本书精华 重点 ###





## 第九章 ##

**操作1** sha1

    sha1 -c  -hex pln.txt 
    dst -sha1 -c-hex pln.txt



**操作2**：生成公私钥
生成一个RSA私钥

    genrsa -out rsaprivkey.pem -passout pass:111111  -des3 1024 

从生成的RSA私钥中导出一个相应的RSA公钥并保存&

    rsa -in  rsaprivkey.pem -passin  pass:111111 -out rsapubkey.pem -pubout 


**操作3**：私钥签名    两步放在一个命令中  第一步：对文件进行SHA1  第二步：对sha1的信息摘要进行RSA加密

    dgst -sha1 -sign rsaprivkey.pem -out sgn.txt  ali-mtms-1.0.46-unsign.apk


**操作4**：公钥验签    三步放在一个命令中  第一步：使用相同算法获得原文件摘要M1  第二步：使用公钥解密sgn.txt得到摘要信息M2   第三步：对比两个摘要M1与M2 

    dgst -sha1 -verify rsapubkey.pem -signature sgn.txt  ali-mtms-1.0.46-unsign.apk

提示：Verified OK 表示验证通过




**操作5**：查看x509的基本信息
    
    openssl x509 -in baidu.pem -noout -text  
    


### 其它 ###
**Android 签名机制:Key的产生方法和签名原理：**
http://www.360doc.com/content/14/1119/08/9200790_426306550.shtml



**openssl的x509命令简单入门:**   
https://blog.csdn.net/u010846177/article/details/54356897



客户端获取OID的方式为（用到MeSdk.jar）   标准版：

	public byte[] getAppOid() {
		byte[] oid = null;
		byte[] enOid = null;
		try {
			CertificateInfo certificateInfo = new CertificateInfo(BaseApplication.getContext());
			X509Certificate cert = certificateInfo.getCertificateInfo();
			byte[] encode = cert.getEncoded();
			oid = RSAUtils.sha1(encode);
			LoggerUtils.d("oid:" + BytesUtils.bytesToHex(oid));
			
			Map<String, String> certMap =  StringUtils.convertStr2Map(cert.getSubjectX500Principal().toString());
			String certName = certMap.get("CN");
			LoggerUtils.d("certName:" + certName);
			
			byte[] oidCn = (BytesUtils.bytesToHex(oid) + certName).getBytes("utf-8");
			enOid = RSAUtils.sha1(oidCn);
			
			LoggerUtils.d("enOid:" + BytesUtils.bytesToHex(enOid));
			
		} catch (Exception e) {
			LoggerUtils.e("获取应用证书异常", e);
		}
		return enOid;
	}


Ali版：

	public byte[] getAppOid() {
		byte[] enOid = null;
		try {
			CertificateInfo certificateInfo = new CertificateInfo(BaseApplication.getContext());
			X509Certificate cert = certificateInfo.getCertificateInfo();
			
			
			Map<String, String> certMap =  StringUtils.convertStr2Map(cert.getSubjectX500Principal().toString());
			String certName = certMap.get("CN");
			LoggerUtils.d("certName:" + certName);
			
			byte[] oidCn = (certName).getBytes("utf-8");
			enOid = RSAUtils.sha1(oidCn);
			
			LoggerUtils.d("enOid:" + BytesUtils.bytesToHex(enOid));
			
		} catch (Exception e) {
			LoggerUtils.e("获取应用证书异常", e);
		}
		return enOid;
	}




最后对oid进行sdes加密,密钥为字节的0x31,加密前要后补位0,（OtherFunctionFragment.initData）
这样才能进行传输


**工具：E:\简单工具\国密计算工具**


## 阿里的OID算法如下（就是CN做下SHA1）： ##
cn:demo-standard  
-->hex:64656D6F2D7374616E64617264 
-->sha1:703CD6EC 90331DA7 7184BA08 4FBD9F00 0E5DB43E    
-->3des(31313131313131313131313131313131,703CD6EC 90331DA7 7184BA08 4FBD9F00 0E5DB43E00000000):06934493 FA3085DE 10DBA0FD 9F349159 DC3DB4BF 9CAEC410    


cn:AlibabaCommerce
-->hex:416C6962616261436F6D6D65726365 
-->sha1:DFA28399 143EBF02 B23CFE46 A5A9F53A B896B53E    （真正的OID，apache上的值）
-->3des(31313131313131313131313131313131,DFA28399 143EBF02 B23CFE46 A5A9F53A B896B53E00000000):2796419F C490EF50 2AB194C0 87CC2271 7DA2255D C73BD815   （Mtms客户端显示的值）


## 标准版的OID算法如下： ##
步骤：
1、获取证书指纹信息  openssl x509 -in baidu.pem -noout -fingerprint
2、指纹信息与cn结合计算一次sha1:70185A60EF323CA0285D050E2D9A7FD1BD0B4EEEdemo-standard
3、对sha1的结果再进行0x31的3des加密

fingerprint：70185A60EF323CA0285D050E2D9A7FD1BD0B4EEE
-->sha1(fingerprint+cn)(70185A60EF323CA0285D050E2D9A7FD1BD0B4EEEdemo-standard):43BFF332 B2915D66 D6A94D58 D13A5B12 6BD2B52E  （真正的OID，apache上的值）
-->3des(31313131313131313131313131313131,43BFF332 B2915D66 D6A94D58 D13A5B12 6BD2B52E00000000):90D710D2 937BE7FB 8D4394AF FAAD6E41 463DCEF5 753DE44B   （Mtms客户端显示的值）





encode:demo-standard  
-->sha1:70185A60EF323CA0285D050E2D9A7FD1BD0B4EEE 
-->sha1:703CD6EC 90331DA7 7184BA08 4FBD9F00 0E5DB43E    
-->3des(31313131313131313131313131313131,703CD6EC 90331DA7 7184BA08 4FBD9F00 0E5DB43E00000000):06934493 FA3085DE 10DBA0FD 9F349159 DC3DB4BF 9CAEC410






Ali服务端获取OID的方式为（）