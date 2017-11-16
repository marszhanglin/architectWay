**项目地址：E:\code\github\architectWay\maven\maven_002_archetype**

1、插件archetype，用于自动生成maven项目**模板**  看下面pom




        <modelVersion>4.0.0</modelVersion>  
  
    <!-- 相当于包名-->
    <groupId>com.mars.maven</groupId>
	<!-- 项内目唯一id -->
    <artifactId>hello-web-archetype</artifactId>  
	<!-- 当前版本 -->
    <version>1.0-SNAPSHOT</version>
	<!-- 友好名称（非必要）-->
	<name>hello-web-archetype</name> 
    <packaging>jar</packaging>   
  
    <distributionManagement>  
        <repository>  
            <id>releases</id>  
            <name>Micaicms Releases</name>  
            <url>http://127.0.0.1:8081/nexus/content/repositories/releases/</url>  
        </repository>  
        <snapshotRepository>  
            <id>snapshots</id>  
            <name>Micaicms Releases</name>  
            <url>http://127.0.0.1:8081/nexus/content/repositories/snapshots/</url>  
        </snapshotRepository>  
    </distributionManagement>  
  
    <build>  
        <pluginManagement>  
            <plugins>  
                <plugin>  
                    <groupId>org.apache.maven.plugins</groupId>  
                    <artifactId>maven-archetype-plugin</artifactId>  
                    <version>2.2</version>  
                </plugin>  
                <plugin>  
                    <groupId>org.apache.maven.plugins</groupId>  
                    <artifactId>maven-compiler-plugin</artifactId>  
                    <configuration>  
                        <source>1.5</source>  
                        <target>1.5</target>  
                    </configuration>  
                </plugin>  
                <plugin>  
                    <groupId>org.apache.maven.plugins</groupId>  
                    <artifactId>maven-resources-plugin</artifactId>  
                    <configuration>  
                        <encoding>UTF-8</encoding>  
                    </configuration>  
                </plugin>  
            </plugins>  
        </pluginManagement>  
    </build>  
