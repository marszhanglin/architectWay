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
