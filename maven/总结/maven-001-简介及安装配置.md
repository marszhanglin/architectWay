1、需要安装 jdk 

 **%JAVA_HOME%**

2、需要设置 M2_HOME

**%M2_HOME%**\bin

3、代理设置  

有的时候公司的网络是无法访问maven中央库的

    ping  repol.maven.org //果然ping不通，回家ping

4、库源及代理的使用

E:\硬盘整理\maven\apache-maven-3.3.3\apache-maven-3.3.3\conf\settings.xml 

	 <!-- 阿里源 -->
	<mirror>
    <id>alimaven</id>
    <name>aliyun maven</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
    <!--mirrorOf>central</mirrorOf-->   
    <mirrorOf>*</mirrorOf>   
    </mirror>




5、本地库位置

E:\硬盘整理\maven\apache-maven-3.3.3\apache-maven-3.3.3\path\to\local\repo