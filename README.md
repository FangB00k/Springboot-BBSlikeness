# 工程简介
- 红星开发小组 StarsTodoList项目
- 论坛主页界面

# 稍后事项
  - [ ] 把Controller层的一些业务逻辑迁到Service层 

# 用户访问方式
 - Cookie [ 用户登录 ]
 - Attrubuite [ 危险先不采用 ]
 - Cookie+Session [ 登录成功后全局使 ]
> 参考文章：
> https://blog.csdn.net/qq_41512902/article/details/125912682
> https://blog.csdn.net/m0_49828549/article/details/114269395
> HandlerInterceptor https://juejin.cn/post/6844904020675559432

# 会话
- LoginStatu 登录状态
- Users 用户名称用来操作的

# 小饼干

# 稍后改进
- 返回信息格式调整
# 主体设计前后端分离

# 表结构设计
 - 用户表(User_Messg)
    - account
    - password
    - power
   > 1 普通用户 2管理员 0 禁止用户登录
    - email
    - telephone
 ```sql
CREATE TABLE `user_messg` (
  `account` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `power` int DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`account`)
) 
```

        /*
        *  帖子表设计
        *  -  帖子id
        *  - 发贴用户
        *  - 附图目录
        *  - 帖子内容
        *  - 帖子类型
        *  - 时间戳
        *   - 1 纯文字
        *   - 2 带一张图片
        * */


- 

缺点时间戳应该 服务下发


[//]: # (- 用户发帖表)

[//]: # (  - 用户id)

[//]: # (  - 帖子id)

[//]: # (  - 时间戳)

[//]: # (```sql)

[//]: # (CREATE TABLE `mesg_card` &#40;)

[//]: # (                             `id` int NOT NULL AUTO_INCREMENT,)

[//]: # (                             `account` varchar&#40;255&#41; NOT NULL,)

[//]: # (                             `timestack` varchar&#40;1024&#41; NOT NULL,)

[//]: # (                             PRIMARY KEY &#40;`id`&#41;,)

[//]: # (                             KEY `account` &#40;`account`&#41;,)

[//]: # (                             CONSTRAINT `mesg_card_ibfk_1` FOREIGN KEY &#40;`account`&#41; REFERENCES `user_messg` &#40;`account`&#41;)

[//]: # (&#41; ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;)

[//]: # (```)

[//]: # (- 帖子表)

[//]: # (  - 帖子类别)

[//]: # (  - 帖子内容)

[//]: # (     > 后续更新上 包含图片 ![图片地址])

[//]: # ()
[//]: # (```sql)

[//]: # (CREATE TABLE `card` &#40;)

[//]: # (                        `id` int NOT NULL,)

[//]: # (                        `mesg` varchar&#40;1024&#41; DEFAULT NULL,)

[//]: # (                        KEY `card_ibfk_1` &#40;`id`&#41;,)

[//]: # (                        CONSTRAINT `card_ibfk_1` FOREIGN KEY &#40;`id`&#41; REFERENCES `mesg_card` &#40;`id`&#41; ON DELETE CASCADE ON UPDATE CASCADE)

[//]: # (&#41; ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;)

[//]: # (```)

[//]: # (>- 图片表)

[//]: # (>  - 用户id )

[//]: # (>  - 图片)

[//]: # (>- 类别表)
> 
  
# 细小功能划分
  - [ ] 登录模块
    - [X] 用户登录
       - [ ] 管理员界面
       - [ X ] 用户界面
    - [ X ] 用户界面
      - 用户发帖
      - 用户打卡
      - 用户成就值？
    - [ ] 管理员界面
       - [X] Ban用户
       - [ ] UnBan用户
       - 删除帖子
       - 添加帖子类别
    - [ X ] 主界面
        - 展示最新帖子
  - [ X ] 用户注册界面
    - 普通用户注册...
# 功能实现
- 管理员 
  - 禁止用户
  - 删除帖子
- 普通用户
  - 发帖
  - 登录打卡


# 用户中心添加帖子
```javascript
  $(".alread_send_group").append(
      "       <div class=\"alread_send_card\">\n" +
      "           <span class=\"title\" >Hello 欢迎</span>\n" +
      "           <hr/>\n" +
      "           <span class=card_context\">\n" +
      "                不能践越的是生死边界，可以触及的是心灵体温。纵有疾风起，人生不言弃.\n" +
      "\n" +
      "               &nbsp; &nbsp; &nbsp;&nbsp -《起风了》 宫崎骏\n" +
      "\n" +
      "\n" +
      "           </span>\n" +
      "           <hr>\n" +
      "           Time:<span class=\"time\">2022/12/1 8:00</span>\n" +
      "          id:<span class=\"id\">01</span>&nbsp;&nbsp;\n" +
      "           <button class=\"delete \">删除</button>\n" +
      "       </div>"


  )
```