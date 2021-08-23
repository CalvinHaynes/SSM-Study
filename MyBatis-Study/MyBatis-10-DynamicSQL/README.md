# [MyBatis-10-Dynamic SQL](https://mybatis.org/mybatis-3/zh/dynamic-sql.html)

## 前言

动态 SQL 是 MyBatis 的强大特性之一。如果你使用过 JDBC 或其它类似的框架，你应该能理解根据不同条件拼接 SQL 语句有多痛苦，例如拼接时要确保不能忘记添加必要的空格，还要注意去掉列表最后一个列名的逗号。利用动态 SQL，可以彻底摆脱这种痛苦。

**动态SQL就是静态SQL中可以加入一些动态的逻辑**

使用动态 SQL 并非一件易事，但借助可用于任何 SQL 映射语句中的强大的动态 SQL 语言，MyBatis 显著地提升了这一特性的易用性。

如果你之前用过 JSTL 或任何基于类 XML 语言的文本处理器，你对动态 SQL 元素可能会感觉似曾相识。在 MyBatis 之前的版本中，需要花时间了解大量的元素。借助功能强大的基于 OGNL 的表达式，MyBatis 3 替换了之前的大部分元素，大大精简了元素种类，现在要学习的元素种类比原来的一半还要少。

- ==if==
- choose (when, otherwise)
- trim (==where, set==)
- foreach

**标题超链接是官方文档**

==**打开Module自行测试**==

## if

```xml
<select id="queryBlogIf" parameterType="map" resultType="Blog">
    select * from blog where 1=1
    <if test="title != null">
        and title = #{title}
    </if>
    <if test="author != null">
        and author = #{author}
    </if>
</select>
```

## choose&when

```xml
<select id="queryBlogChoose" parameterType="map" resultType="Blog">
    select * from blog
    <where>
        <choose>
            <when test="title != null">
                title = #{title}
            </when>
            <when test="author != null">
                and author = #{author}
            </when>
            <otherwise>
                and views = #{views}
            </otherwise>
        </choose>
    </where>
</select>
```

## where

```xml
<select id="queryBlogIf" parameterType="map" resultType="blog">
    select * from blog
    <!--
        where 元素只会在子元素返回任何内容的情况下才插入 “WHERE” 子句。
        而且，若子句的开头为 “AND” 或 “OR”，where 元素也会将它们去除。
    -->
    <where>
        <if test="title!=null">
            and title = #{title}
        </if>
        <if test="author!=null">
            and author = #{author}
        </if>
    </where>
</select>
```

## set

```xml
<!--注意set是用的逗号隔开-->
<update id="updateBlogSet" parameterType="map">
    update blog
    <set>
        <if test="title != null">
            title = #{title},
        </if>
        <if test="author != null">
            author = #{author}
        </if>
    </set>
    where id = #{id};
</update>
```

## Foreach

```xml
<select id="queryBlogForeach" parameterType="map" resultType="blog">
    select * from blog
    <where>
        <!--
        collection:指定输入对象中的集合属性
        item:每次遍历生成的对象
        open:开始遍历时的拼接字符串
        close:结束时拼接的字符串
        separator:遍历对象之间需要拼接的字符串
        select * from blog where 1=1 and (id=1 or id=2 or id=3)
      -->
        <foreach collection="ids" item="id" open="and (" close=")" separator="or">
            id=#{id}
        </foreach>
    </where>
</select>
```

注意：

①、最好基于 单表来定义 sql 片段，提高片段的可重用性

②、在 sql 片段中不要包括 where

## SQL片段

有时候可能某个 sql 语句我们用的特别多，为了增加代码的重用性，简化代码，我们需要将这些代码抽取出来，然后使用时直接调用。

1. 使用SQL标签抽取公共部分
2. 在需要使用的地方使用include标签引用即可

**提取SQL片段：**

```xml
<sql id="if-title-author">
   <if test="title != null">
      title = #{title}
   </if>
   <if test="author != null">
      and author = #{author}
   </if>
</sql>
```

**修改If测试时的XML配置**：

```xml
<select id="queryBlogIf" parameterType="map" resultType="blog">
    select * from blog
    <!--
        where 元素只会在子元素返回任何内容的情况下才插入 “WHERE” 子句。
        而且，若子句的开头为 “AND” 或 “OR”，where 元素也会将它们去除。
    -->
    <where>
        <!-- 引用 sql 片段，如果refid 指定的不在本文件中，那么需要在前面加上 namespace -->
        <include refid="if-title-author"/>
        <!-- 在这里还可以引用其他的 sql 片段 -->
    </where>
</select>
```

## 小结

其实动态 sql 语句的编写往往就是一个拼接的问题，为了保证拼接准确，我们最好首先要写原生的 sql 语句出来，然后在通过 mybatis 动态sql 对照着改，防止出错。多在实践中使用才是熟练掌握它的技巧。

动态SQL就是在拼接SQL语句，我们只需要保证SQL的正确性，按照正确的SQL格式，去排列组合就可以了
