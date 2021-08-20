# MyBatis-5-LogFactory

## 前言

如果一个数据库操作，出现了异常，我们需要排错，日志就是最好的助手！

曾经：sout、debug

现在：LogFactory



**MyBatis中支持的LogFactory有以下这些：**

- SLF4J
- LOG4J 【掌握】
- LOG4J2
- JDK_LOGGING
- COMMONS_LOGGING
- STDOUT_LOGGING 【掌握】
- NO_LOGGING

在设置文件中设置

## STDOUT_LOGGING 

### 1 - mybatis-config.xml配置

```xml
<settings>
    <setting name="logImpl" value="STDOUT_LOGGING"/>
</settings>

```

### 2 - 打印日志

```log
D:\Software\JAVA\JDK\adopt-openjdk-11.0.12\bin\java.exe -ea -Didea.test.cyclic.buffer.size=1048576 "-javaagent:D:\Software\JAVA\Interllij IDEA\IntelliJ IDEA 2021.1.2\lib\idea_rt.jar=54238:D:\Software\JAVA\Interllij IDEA\IntelliJ IDEA 2021.1.2\bin" -Dfile.encoding=UTF-8 -classpath "D:\Software\JAVA\Interllij IDEA\IntelliJ IDEA 2021.1.2\lib\idea_rt.jar;D:\Software\JAVA\Interllij IDEA\IntelliJ IDEA 2021.1.2\plugins\junit\lib\junit5-rt.jar;D:\Software\JAVA\Interllij IDEA\IntelliJ IDEA 2021.1.2\plugins\junit\lib\junit-rt.jar;D:\OpenSource\My Repository\My Repository for Github\SSM-Study\MyBatis-Study\MyBatis-5-LogFactory\target\test-classes;D:\OpenSource\My Repository\My Repository for Github\SSM-Study\MyBatis-Study\MyBatis-5-LogFactory\target\classes;D:\Software\JAVA\apache-maven-3.8.1-bin\Maven_Repository\log4j\log4j\1.2.17\log4j-1.2.17.jar;D:\Software\JAVA\apache-maven-3.8.1-bin\Maven_Repository\mysql\mysql-connector-java\8.0.26\mysql-connector-java-8.0.26.jar;D:\Software\JAVA\apache-maven-3.8.1-bin\Maven_Repository\com\google\protobuf\protobuf-java\3.11.4\protobuf-java-3.11.4.jar;D:\Software\JAVA\apache-maven-3.8.1-bin\Maven_Repository\org\mybatis\mybatis\3.5.7\mybatis-3.5.7.jar;D:\Software\JAVA\apache-maven-3.8.1-bin\Maven_Repository\junit\junit\4.13.2\junit-4.13.2.jar;D:\Software\JAVA\apache-maven-3.8.1-bin\Maven_Repository\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar" com.intellij.rt.junit.JUnitStarter -ideVersion5 -junit4 top.calvinhaynes.mapper.TestPageBreak,selectUserByIdTest
[org.apache.ibatis.logging.LogFactory]-Logging initialized using 'class org.apache.ibatis.logging.log4j.Log4jImpl' adapter.
Logging initialized using 'class org.apache.ibatis.logging.stdout.StdOutImpl' adapter.
PooledDataSource forcefully closed/removed all connections.
PooledDataSource forcefully closed/removed all connections.
PooledDataSource forcefully closed/removed all connections.
PooledDataSource forcefully closed/removed all connections.
Opening JDBC Connection
Created connection 1489193907.
Setting autocommit to false on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@58c34bb3]
==>  Preparing: select * from users where id=?;
==> Parameters: 1002(Integer)
<==    Columns: id, name, password
<==        Row: 1002, Mary, 654321
<==      Total: 1
Users{id=1002, name='Mary', password='654321'}
Resetting autocommit to true on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@58c34bb3]
Closing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@58c34bb3]
Returned connection 1489193907 to pool.
```

## LOG4J

- Log4j是[Apache](https://baike.baidu.com/item/Apache/8512995)的一个开源项目，通过使用Log4j，我们可以控制日志信息输送的目的地是[控制台](https://baike.baidu.com/item/控制台/2438626)、文件、[GUI](https://baike.baidu.com/item/GUI)组件，甚至是套接口服务器、[NT](https://baike.baidu.com/item/NT/3443842)的事件记录器、[UNIX](https://baike.baidu.com/item/UNIX) [Syslog](https://baike.baidu.com/item/Syslog)[守护进程](https://baike.baidu.com/item/守护进程/966835)等；

- 我们也可以控制每一条日志的输出格式；

- 通过定义每一条日志信息的级别，我们能够更加细致地控制日志的生成过程。

- 最令人感兴趣的就是，这些可以通过一个[配置文件](https://baike.baidu.com/item/配置文件/286550)来灵活地进行配置，而不需要修改应用的代码。

### 测试

#### 1 - 导包

```xml
<dependencies>
        <!-- https://mvnrepository.com/artifact/log4j/log4j -->
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>
    </dependencies>
```

#### 2 - 写配置文件 log4j.properties

```properties
#将等级为DEBUG的日志信息输出到console和file这两个目的地，console和file的定义在下面的代码
log4j.rootLogger=DEBUG,console,file
#控制台输出的相关设置
log4j.appender.console=org.apache.log4j.ConsoleAppender
log4j.appender.console.Target=System.out
log4j.appender.console.Threshold=DEBUG
log4j.appender.console.layout=org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=[%c]-%m%n
#文件输出的相关设置
log4j.appender.file=org.apache.log4j.RollingFileAppender
log4j.appender.file.File=./log/calvin.log
log4j.appender.file.MaxFileSize=10mb
log4j.appender.file.Threshold=DEBUG
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=[%p][%d{yy-MM-dd}][%c]%m%n
#日志输出级别
log4j.logger.org.mybatis=DEBUG
log4j.logger.java.sql=DEBUG
log4j.logger.java.sql.Statement=DEBUG
log4j.logger.java.sql.ResultSet=DEBUG
log4j.logger.java.sql.PreparedStatement=DEBUG
```

#### 3 - 打印日志

```log
D:\Software\JAVA\JDK\adopt-openjdk-11.0.12\bin\java.exe -ea -Didea.test.cyclic.buffer.size=1048576 "-javaagent:D:\Software\JAVA\Interllij IDEA\IntelliJ IDEA 2021.1.2\lib\idea_rt.jar=50810:D:\Software\JAVA\Interllij IDEA\IntelliJ IDEA 2021.1.2\bin" -Dfile.encoding=UTF-8 -classpath "D:\Software\JAVA\Interllij IDEA\IntelliJ IDEA 2021.1.2\lib\idea_rt.jar;D:\Software\JAVA\Interllij IDEA\IntelliJ IDEA 2021.1.2\plugins\junit\lib\junit5-rt.jar;D:\Software\JAVA\Interllij IDEA\IntelliJ IDEA 2021.1.2\plugins\junit\lib\junit-rt.jar;D:\OpenSource\My Repository\My Repository for Github\SSM-Study\MyBatis-Study\MyBatis-5-LogFactory\target\test-classes;D:\OpenSource\My Repository\My Repository for Github\SSM-Study\MyBatis-Study\MyBatis-5-LogFactory\target\classes;D:\Software\JAVA\apache-maven-3.8.1-bin\Maven_Repository\log4j\log4j\1.2.17\log4j-1.2.17.jar;D:\Software\JAVA\apache-maven-3.8.1-bin\Maven_Repository\mysql\mysql-connector-java\8.0.26\mysql-connector-java-8.0.26.jar;D:\Software\JAVA\apache-maven-3.8.1-bin\Maven_Repository\com\google\protobuf\protobuf-java\3.11.4\protobuf-java-3.11.4.jar;D:\Software\JAVA\apache-maven-3.8.1-bin\Maven_Repository\org\mybatis\mybatis\3.5.7\mybatis-3.5.7.jar;D:\Software\JAVA\apache-maven-3.8.1-bin\Maven_Repository\junit\junit\4.13.2\junit-4.13.2.jar;D:\Software\JAVA\apache-maven-3.8.1-bin\Maven_Repository\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar" com.intellij.rt.junit.JUnitStarter -ideVersion5 -junit4 top.calvinhaynes.mapper.TestPageBreak
[org.apache.ibatis.logging.LogFactory]-Logging initialized using 'class org.apache.ibatis.logging.log4j.Log4jImpl' adapter.
[org.apache.ibatis.logging.LogFactory]-Logging initialized using 'class org.apache.ibatis.logging.log4j.Log4jImpl' adapter.
[org.apache.ibatis.datasource.pooled.PooledDataSource]-PooledDataSource forcefully closed/removed all connections.
[org.apache.ibatis.datasource.pooled.PooledDataSource]-PooledDataSource forcefully closed/removed all connections.
[org.apache.ibatis.datasource.pooled.PooledDataSource]-PooledDataSource forcefully closed/removed all connections.
[org.apache.ibatis.datasource.pooled.PooledDataSource]-PooledDataSource forcefully closed/removed all connections.
[org.apache.ibatis.transaction.jdbc.JdbcTransaction]-Opening JDBC Connection
[org.apache.ibatis.datasource.pooled.PooledDataSource]-Created connection 1966670937.
[org.apache.ibatis.transaction.jdbc.JdbcTransaction]-Setting autocommit to false on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@75390459]
[top.calvinhaynes.mapper.UserMapper.selectUserById]-==>  Preparing: select * from users where id=?;
[top.calvinhaynes.mapper.UserMapper.selectUserById]-==> Parameters: 1002(Integer)
[top.calvinhaynes.mapper.UserMapper.selectUserById]-<==      Total: 1
Users{id=1002, name='Mary', password='654321'}
[org.apache.ibatis.transaction.jdbc.JdbcTransaction]-Resetting autocommit to true on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@75390459]
[org.apache.ibatis.transaction.jdbc.JdbcTransaction]-Closing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@75390459]
[org.apache.ibatis.datasource.pooled.PooledDataSource]-Returned connection 1966670937 to pool.

Process finished with exit code 0

```

### 简单使用

1. 在要使用Log4j的类中，导入包 import org.apache.log4j.Logger;
2. 日志对象，参数为当前类的class对象

```java
Logger logger = Logger.getLogger(Test1.class);
```

3. 日志级别

```java
logger.info("info: 测试log4j");
logger.debug("debug: 测试log4j");
logger.error("error:测试log4j");
```

