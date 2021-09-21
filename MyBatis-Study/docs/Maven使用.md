# Maven使用

## 前言

Maven 的核心思想：**约定大于配置**

- 有约束，不要去违反。
  Maven 会规定好你该如何去编写我们的 Java 代码，必须要按照这个规范来；

## IDEA中使用Maven注意点

### 1 - IDEA 项目创建成功后，看一眼 Maven 的配置，如果用的是IDEA绑定的Maven的话修改为自己的

![image](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.3kxbapbq3nm0.png)

- 设置自动导入源码或文档（文档较大，一般不导入）

![img](https://img-blog.csdnimg.cn/20201030200557328.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

### 2 - 标记文件夹

- 标记文件夹，这样Maven才能识别文件夹的功能

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030200931259.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2MTg4MTI3,size_16,color_FFFFFF,t_70#pic_center)

![image](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.2st3qla7tv20.png)

### 3 - 关于Maven创建新项目虽然指定了父项目，但是却没继承父项目依赖（生成的pom.xml文件中没有parent标签）

**目前怀疑可能是由于创建新项目改名时的问题**

==解决办法：删除.idea文件夹，关闭IDEA，重新打开IDEA等待Maven项目重新加载==

## 解决Maven静态资源过滤问题

约定Maven能够读取一些常用配置文件所在目录，防止配置文件不生效

```xml
<!--在父项目的pom.xml中加入此,解决整个项目的Maven静态资源过滤问题 -->
<build>
    <resources>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>false</filtering>
        </resource>
        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>false</filtering>
        </resource>
    </resources>
</build>
```

## 配置Maven阿里云镜像

**配置文件：Maven目录下的conf中的settings.xml**

```xml
 <mirrors>
      <mirror>
           <id>alimaven</id>
           <name>aliyun maven</name>
           <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
           <mirrorOf>central</mirrorOf> 
      </mirror>
 </mirrors>
```

## 配置Maven Local repository

**配置文件：Maven目录下的conf中的settings.xml**

```xml
<localRepository>D:\Software\JAVA\apache-maven-3.8.1-bin\Maven_Repository</localRepository>
```

