部署：  

    mvn tomcat:run

清理：target目录会被删掉

    mvn  clean  

编译：生成.class文件

    mvn compile

测试：必须要有xxxTest的java文件 

    mvn test

打包：会根据项目的不同在target下打成war或jar，

    mvn package

查看依赖：

    mvn dependency:list   //查看列表
	mvn dependency:tree	  //查看树形结构
	mvn dependency:analyze  //分析依赖


构建web项目：

    mvn archetype:generate -DgroupId=mars -DartifactId=com.mars.ssm 
    -DarchetypeArtifactId=maven-archetype-webapp -DinteractivMode=false


