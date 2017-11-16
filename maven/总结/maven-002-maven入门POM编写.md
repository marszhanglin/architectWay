**项目地址：E:\code\github\architectWay\maven\maven_hellow_word**

1、创建POM.xml

    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0"
     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    	
    	<!-- 相当于包名-->
    	<groupId>com.mars.maven</groupId>
    	<!-- 项内目唯一id -->
    	<artifactId>hello_word</artifactId>
    	<!-- 当前版本 -->
    	<version>1.0-SNAPSHOT</version>
    	<!-- 友好名称（非必要）-->
    	<name>hellow word</name>

    </project>


2、在pom.xml下运行

    
    第一步：mvn install
    
    第二步：mvn compiler:compile
    
    第三步：mvn org.apache.maven.plugins:maven-compiler-plugin:compile
    
    第四步：mvn org.apache.maven.plugins:maven-compiler-plugin:2.0.2:compile
    
    mvn clean complie


3、主代码 

src/main/java/


4、测试代码

    src/test/java/


5、往pom.xml添加jutil依赖

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
<modelVersion>4.0.0</modelVersion>
	
	<!-- 相当于包名-->
<groupId>com.mars.maven</groupId>
	<!-- 项内目唯一id -->
<artifactId>hello_word</artifactId>
	<!-- 当前版本 -->
<version>1.0-SNAPSHOT</version>
	<!-- 友好名称（非必要）-->
	<name>hellow word</name>
	
	<!-- 依赖集合-->
	<dependencies>
		<!-- 单个依赖-->
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.7</version>
			<!-- 该依赖的适用范围，默认值为complie（表示可以在main及test内import），这里设置为test表示只能在test内使用-->
			<scope>test</scope>
		</dependency>
	
	</dependencies>
	
	
</project>




6、添加main执行支持，plugin

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
<modelVersion>4.0.0</modelVersion>
	
	<!-- 相当于包名-->
<groupId>com.mars.maven</groupId>
	<!-- 项内目唯一id -->
<artifactId>hello_word</artifactId>
	<!-- 当前版本 -->
<version>1.0-SNAPSHOT</version>
	<!-- 友好名称（非必要）-->
	<name>hellow word</name>
	
	<!-- 依赖集合-->
	<dependencies>
		<!-- 单个依赖-->
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.7</version>
			<!-- 该依赖的适用范围，默认值为complie（表示可以在main及test内import），这里设置为test表示只能在test内使用-->
			<scope>test</scope>
		</dependency>
	
	</dependencies> 
	
	<build>
		<plugins>
		<!--  main插件支持 -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-shade-plugin</artifactId>
				<version>1.2.1</version>
				<executions>
					<execution>
						<phase>package</phase>
						<goals>
							<goal>shade</goal>
						</goals>
						<configuration>
							<transformers>
								<transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
									<mainClass>com.mars.maven.HelloWorld</mainClass>
								</transformer>
							</transformers>
						</configuration>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>

</project>








