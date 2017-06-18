# onlineshop
在线商城
基于SSH（Spring+Struts2+Hibernate）的网上商城项目
*1. 功能：
系统架构图：
 
    整个网上商城系统遵循多层次的架构设计模式（MVC开发模式），从上到下依次为视图层、控制层、服务层（业务层）、持久化层（Dao）、数据库层。层次间的依赖关系的原则是自顶向下的，即上层可以依赖下层，而下层应该尽量减少对上层的依赖。同时，层次之间的交互应该减少对实现的依赖，通过接口进行交互。
    视图层和控制层使用Struts2框架实现，持久化层和数据库层采用Hibernate实现，服务层采用Spring框架进行IoC控制反转解耦合，实现系统扩展。
1.1 前台商城系统：
（1）用户模块：
① 用户注册：前台JS校验，用户名的AJAX异步校验，用户信息的Struts2校验与服务器端的数据库校验；
② 用户登录：采取了相同的校验方式，同时实现了用户退出（销毁session）；
（2）一级分类模块：所有一级分类的显示（存入session中），一级分类索引到二级分类或商品进行显示（级联查询）；
（3）二级分类模块：二级分类的显示，二级分类索引到商品进行显示；
（4）商品模块：首页的热门商品、最新商品的显示，根据一级分类ID分页显示商品，根据二级分类ID分页显示商品，单个商品的详情显示；
（5）购物车模块：商品加入到购物车，购物车列表的分页显示（总计，单商品的数量和小计），购物车内商品的删除，购物车清空，提交购物车内的商品生成订单（需要登录后才能操作，同时删除购物车内的商品）；
（6）订单模块：订单生成（存入到数据库），订单支付（通过第三方支付平台进行支付），订单列表分页显示（根据用户ID查询），根据订单ID查询具体订单；
1.2 后台管理系统：使用dtree.js作为树形结构菜单
（1）管理员登录模块：JS校验、Struts2校验和数据库校验；
（2）用户管理模块：管理员信息的管理（例如添加、删除、修改、显示）
（3）一级分类管理模块：商品一级分类的列表分页显示，一级分类的添加、删除（不考虑级联删除相应的二级分类）、修改；
（4）二级分类管理模块：商品二级分类的列表显示，二级分类的添加（同时需选择相应的以及分类）、删除（不考虑级联删除相应的商品）、修改（所属一级分类不更改）；
（5）商品管理模块：商品列表分页显示（按加入的时间先后）、商品的添加（选定所属的二级分类，商品图片的上传），删除、修改；
（6）订单管理模块：订单项列表的分页显示，订单项的AJAX异步请求显示详情，订单项的发货状态更改；
*2. 主要的实验环境和技术：
运行平台：Windows 7 + Myeclipse2014 + JDK1.8
JSP服务器容器：Tomcat 7
数据库：Mysql5.1
框架：Spring+Struts2+Hibernate
技术：jQuery（异步校验、页面跳转）, dtree框架（用于设计后台管理系统的显示）、JSON（数据存储与传输）
*3. 数据库设计：
3.1表之间的关系：
 
3.2 表的具体情况
（1）用户表：用户密码存入时采用mysql自带的MD5进行加密，用户ID
（2）一级分类表：一级分类ID
（3）二级分类表：二级分类ID，外键：一级分类ID
（4）商品表：外键：二级分类ID
（5）订单表：订单ID，外键：用户ID
（6）订单项表：外键：订单ID，商品ID
（7）管理用户表：管理员ID
*4. 编程实现：
4.1 搭建开发环境：
（1）创建web工程
（2）引入jar包和配置文件：
① struts2的配置文件：需要struts.xml
   web.xml：配置核心过滤器
② Spring配置文件：需要ApplicationContext.xml
   web.xml：配置核心监听器
③ Hibernate：需要映射文件 *.hbm.xml
（3）配置基本配置信息：
① C3P0连接池
   引入外部属性文件：jdbc.properties
   配置连接池：在ApplicationContext.xml中
② Hibernate相关信息：sessionFactory
③ 事务管理

*高并发的考虑：
（1）NIO（非阻塞IO，而不是Socket）的使用；
（2）多线程的同步机制的选择，尽可能地缩小锁的覆盖范围，线程池的使用（Executor）；
（3）数据库的设计，SQL数据的优化，隔离级别的选择，存储引擎（锁粒度）的选择，数据库连接池的使用；
（4）Nginx反向代理服务器（负载均衡）
