# SpringMVC-HelloSpringMVC



## 根据Demo结合SpringMVC原理分析

![图片](https://mmbiz.qpic.cn/mmbiz_png/uJDAUKrGC7KwPOPWq00pMJiaK86lF6BjIaosVziclWLEJQkzobxHrpHcmtu2yTeVWPmEI4Yq5PaicS52VaJt8dYfQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

### SpringMVC原理图

![图片](https://mmbiz.qpic.cn/mmbiz_png/uJDAUKrGC7KwPOPWq00pMJiaK86lF6BjIbmPOkY8TxF6qvGAGXxC7dArYcr8uJlWoVC4aF4bfxgCGCD8sHg8mgw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

## 遇到的问题

发现一直报404错误：

![image](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.d2a32b2rh2g.png)

**查询之后发现是Tomcat10版本不兼容的问题，降Tomcat版本到Tomcat9.0.52，成功运行**



idea中web.xml报错 Servlet should have a mapping
配置springmvc时，报错，实际mapping已经写了。
工程的web.xml位置配置错误

![image](https://cdn.jsdelivr.net/gh/CalvinHaynes/ImageHub@main/BlogImage/image.3yz7zjx2b7q0.png)

项目依赖

直接导入jstl1.2的jar包会显示如上问题，经过查询，问题是在tomcat中已有jsp-api和servlet-api，但maven下载jstl时也会把它所依赖的这两个包下载下来，所以就有了包冲突，因此在maven的pom.xml中需要排除这两个包的下载

```xml
  <dependencies>

        <!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>4.0.1</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/javax.servlet.jsp/javax.servlet.jsp-api -->
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>javax.servlet.jsp-api</artifactId>
            <version>2.3.3</version>
        </dependency>

        <!-- JSTL（Java server pages standarded tag library，
        即JSP标准标签库）是由JCP（Java community Proces）所制定的标准规范，
        它主要提供给Java Web开发人员一个标准通用的标签库，并由Apache的Jakarta小组来维护。
        开发人员可以利用这些标签取代JSP页面上的Java代码，从而提高程序的可读性，降低程序的维护难度。-->
        <!-- https://mvnrepository.com/artifact/javax.servlet.jsp.jstl/jstl -->
        <dependency>
            <groupId>javax.servlet.jsp.jstl</groupId>
            <artifactId>jstl-api</artifactId>
            <version>1.2</version>
            <exclusions>
                <exclusion>
                    <groupId>javax.servlet</groupId>
                    <artifactId>servlet-api</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>javax.servlet.jsp</groupId>
                    <artifactId>jsp-api</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.glassfish.web</groupId>
            <artifactId>jstl-impl</artifactId>
            <version>1.2</version>
            <exclusions>
                <exclusion>
                    <groupId>javax.servlet</groupId>
                    <artifactId>servlet-api</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>javax.servlet.jsp</groupId>
                    <artifactId>jsp-api</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>javax.servlet.jsp.jstl</groupId>
                    <artifactId>jstl-api</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>
```

