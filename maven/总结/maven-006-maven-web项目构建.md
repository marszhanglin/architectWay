
http://blog.csdn.net/edward0830ly/article/details/8748986


### 构建普通maven项目 ###

    mvn archetype:generate  



### 构建web流程 ###
1、命令行构建

    mvn archetype:generate -DgroupId=mars -DartifactId=com.mars.ssm 
    -DarchetypeArtifactId=maven-archetype-webapp -DinteractivMode=false


2、修改pom

    mvn archetype:generate -DgroupId=mars -DartifactId=logservice_101 -DarchetypeArtifactId=maven-archetype-webapp -DinteractivMode=false




### 注意事项： ###
1. 构建时文件夹下不能有pom.xml文件  这个文件生成后再去编辑









