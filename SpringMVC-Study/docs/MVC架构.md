# MVC架构

![[(img-BWDJGUCN-1588757845419)(JavaWeb.assets/1568424227281.png)]](https://img-blog.csdnimg.cn/20200508154512751.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2JlbGxfbG92ZQ==,size_16,color_FFFFFF,t_70)

Model

- 业务处理 ：业务逻辑（Service）

- 数据持久层：CRUD （Dao - 数据持久化对象）


View

- 展示数据
- 提供链接发起Servlet请求 （a，form，img…）

Controller （Servlet）

- 接收用户的请求 ：（req：请求参数、Session信息….）

- 交给业务层处理对应的代码

- 控制视图的跳转

