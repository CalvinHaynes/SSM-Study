# MyBatis-4-ResultMap

## 前言

**resultMap 解决的核心问题就是数据库中的字段名和 Java Bean 的属性名不同的问题**，通过resultMap 的结果集映射，可以将其一一对应起来。

- `resultMap` 元素是 MyBatis 中最重要最强大的元素。

- 它可以让你从 90% 的 JDBC `ResultSets` 数据提取代码中解放出来，并在一些情形下允许你进行一些 JDBC 不支持的操作。

- 实际上，在为一些比如连接的复杂语句编写映射代码的时候，一份 `resultMap` 能够代替实现同等功能的数千行代码。

- ResultMap 的设计思想是，对简单的语句做到零配置，对于复杂一点的语句，只需要描述语句之间的关系就行了。
  - 对简单语句做到零配置如何理解：MyBatis 会在幕后自动创建一个 `ResultMap`，再根据属性名来映射列到 JavaBean 的属性上，如果名称都一致，那 SQL 语句就可以正常执行，否则对应的不一致的名称将会在数据库找不到对应的数据。
  - 如果名称不一致，就可以显式利用ResultMap进行结果集映射，将数据库中的字段名和 Java Bean 的属性名一一映射起来


