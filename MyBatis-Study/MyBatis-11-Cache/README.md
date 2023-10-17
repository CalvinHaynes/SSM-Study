# MyBatis-11-Cache（了解即可）

## 前言

#### 1. 什么是缓存[Cache]？

- 存在内存中的临时数据。
- 将用户经常查询的数据放在缓存（内存）中，用户去查询数据就不用从磁盘上（关系型数据库查询文件）查询，从缓存中查询，从而提高查询效率，解决了高并发系统的性能问题。

#### 2. 为什么使用缓存？

- 减少和数据库的交互次数，减少系统开销，提高系统效率。

#### 3. 什么样的数据能使用缓存？

- 经常查询并且不经常改变的数据。【可以使用缓存】

## MyBatis缓存

Mybatis包含一个非常强大的查询缓存特性，它可以非常方便地定制和配置缓存。缓存可以极大的提升查询效率。

- Mybatis系统中默认定义了两级缓存：一级缓存和二级缓存
- 默认情况下，只有一级缓存开启。（SqlSession级别的缓存，也称为本地缓存）
- 二级缓存需要手动开启和配置，它是基于namespace级别的缓存。
- 为了提高扩展性，Mybatis还定义了缓存接口Cache，我们可以通过实现Cache接口来自定义二级缓存。

### 一级缓存

#### 概述

- 一级缓存也叫本地缓存：
  - 与数据库同一次会话期间查询到的数据会放在本地缓存中。
  - 以后如果需要获取相同的数据，直接从缓存中拿，没必要再去查询数据库
  
#### 代码测试

  1. 开启日志
  2. 测试在一个sqlSession中查询两次相同记录
  3. 查看输出

![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.irvfdcx78ig.png)

- **缓存失效的情况**：

  1. 查询不同东西
  2. 增删改操作，由于有可能会操作原本的数据，所以必定会刷新缓存

  ![image](https://xf233.oss-cn-hangzhou.aliyuncs.com/CalvinHaynesBlogImage/image.3e16n529qwu0.png)

  3. 查询不同的Mapper.xml
  4. 手动清理缓存

```java
  @Test
    public void CacheTest3() {
        SqlSession session = MyBatisUtils.getSession();

        UserMapper mapper = session.getMapper(UserMapper.class);

        Users user = mapper.selectUserById(1002);
        System.out.println(user);

        //手动清理缓存
        session.clearCache();

        Users user2 = mapper.selectUserById(1002);
        System.out.println(user2);

        session.close();
    }
```

==小结：一级缓存默认是开启的，只在一次SqlSession中有效，也就是拿到连接到关闭连接这个区间段！
一级缓存相当于一个Map。==

### 二级缓存

#### 概述

- 二级缓存也叫全局缓存，一级缓存作用域太低了，所以诞生了二级缓存；
- 基于namespace级别的缓存，一个名称空间，对应一个二级缓存；
- 工作机制
  - 一个会话查询一条数据，这个数据就会被放在当前会话的一级缓存中；
  - 如果当前会话关闭了，这个会话对应的一级缓存就没了；但是我们想要的是，会话关闭了，一级缓存中的数据被保存到二级缓存中；
  - 新的会话查询信息，就可以从二级缓存中获取内容；
  - 不同的mapper查出的数据就会放在自己对应的缓存（map）中；
#### 代码测试
1. 在mybatis-config.xml开启全局缓存

```xml
<!--显式的开启全局缓存-->
<setting name="cacheEnabled" value="true"/>
```
2. 在mybatis-config.xml开启全局缓存

```xml
 <!--在当前Mapper.xml中使用二级缓存-->
<cache/>
```

基本上就是这样。这个简单语句的效果如下:

- 映射语句文件中的所有 select 语句的结果将会被缓存。
- 映射语句文件中的所有 insert、update 和 delete 语句会刷新缓存。
- 缓存会使用最近最少使用算法（LRU, Least Recently Used）算法来清除不需要的缓存。
- 缓存不会定时进行刷新（也就是说，没有刷新间隔）。
- 缓存会保存列表或对象（无论查询方法返回哪种）的 1024 个引用。
- 缓存会被视为读/写缓存，这意味着获取到的对象并不是共享的，可以安全地被调用者修改，而不干扰其他调用者或线程所做的潜在修改。

3. 官网的自定义参数操作

```xml
<cache
  eviction="FIFO"
  flushInterval="60000"
  size="512"
  readOnly="true"/>
```

这个更高级的配置创建了一个 FIFO 缓存，每隔 60 秒刷新，最多可以存储结果对象或列表的 512 个引用，而且返回的对象被认为是只读的，因此对它们进行修改可能会在不同线程中的调用者产生冲突。

可用的清除策略有：

- `LRU` – 最近最少使用：移除最长时间不被使用的对象。
- `FIFO` – 先进先出：按对象进入缓存的顺序来移除它们。
- `SOFT` – 软引用：基于垃圾回收器状态和软引用规则移除对象。
- `WEAK` – 弱引用：更积极地基于垃圾收集器状态和弱引用规则移除对象。

默认的清除策略是 LRU。

4. 测试

   ```java
    @Test
   public void Level2CacheTest1() {
       SqlSession session = MyBatisUtils.getSession();
       SqlSession session2 = MyBatisUtils.getSession();
   
       UserMapper mapper = session.getMapper(UserMapper.class);
       UserMapper mapper2 = session2.getMapper(UserMapper.class);
   
   
       Users user = mapper.selectUserById(1002);
       System.out.println(user);
       session.close();
   
       Users user2 = mapper2.selectUserById(1002);
       System.out.println(user2);
   
       session2.close();
   }
   ```

   ==问题：如果没有自定义参数，则会报错，我们需要将实体类序列化！==

   ```java
   org.apache.ibatis.cache.CacheException: Error serializing object.  Cause: java.io.NotSerializableException: top.calvinhaynes.pojo.Users
   ```


## 缓存原理图

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201030140755274.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xpNjQzOTM3NTc5,size_16,color_FFFFFF,t_70#pic_center)

![img](https://img-blog.csdnimg.cn/img_convert/ed26d0d84bc9803ceab7ff4b6bab25de.png)

## 自定义缓存Encache（了解，现已几乎不用）

Ehcache是一种广泛使用的开源Java分布式缓存，主要面向通用缓存。

要在程序中使用ehcache，先要导包！

在mapper中指定使用我们的ehcache缓存实现！

**目前：Redis数据库来做缓存！K-V**

## 小结

- 只要开启了二级缓存，在同一个Mapper下就有效；
- 所有的数据都会先放在一级缓存中；
- 只有当会话提交或者关闭的时候，才会提交到二级缓存中！

