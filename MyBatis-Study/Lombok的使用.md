# Lombok

## 前言

[Lombok](https://projectlombok.org/)是一个可以通过简单的注解形式来帮助我们简化消除一些必须有但显得臃肿的 Java 代码的工具，通过使用对应的注解，可以在编译源码的时候生成对应的方法。官方地址：https://projectlombok.org/，github 项目地址：https://github.com/rzwitserloot/lombok。

**引入Maven包**：

```xml
<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.20</version>
    <scope>provided</scope>
</dependency>
```

## lombok 常用注解

使用 lombok 注解需要在项目中引用`lombok` jar 包。

- `@Data`：注解在类上；提供类所有属性的`getting`和`setting`方法，此外还提供了`equals`、`canEqual`、`hashCode` 、`toString` 方法
- `@Setter`：注解在属性上；为属性提供 setting 方法
- `@Getter`：注解在属性上；为属性提供 getting 方法
- `@Slf4j`：注解在类上；为类提供一个属性名为`log` 的`slf4j`日志对象
- `@NoArgsConstructor`：注解在类上：为类提供一个无参的构造方法
- `@AllArgsConstructor` ：注解在类上；为类提供一个全参的构造方法
- `@NonNull`：注解在参数上；如果该参数为 null 会 throw new NullPointerException(参数名);
- `@Cleanup`：注释在引用变量前，自动回收资源 默认调用 close 方法
- `@SneakyThrows` ：注解在方法上，为方法抛出指定异常

