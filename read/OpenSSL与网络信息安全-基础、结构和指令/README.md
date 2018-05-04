
 




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



### 其它 ###
Android 签名机制:Key的产生方法和签名原理：
http://www.360doc.com/content/14/1119/08/9200790_426306550.shtml









