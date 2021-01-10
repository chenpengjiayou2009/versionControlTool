# **1** 项目概况

本项目为Java课程第15小组的课程项目，项目内容为使用Java依据git底层原理实现的可以模仿部分git命令的版本控制工具。组长为陈鹏；组员包括罗子衿、毕在然。



# **2** 功能实现情况

本版本管理工具实现的功能主要包括GIT核心储存结构与命令行工具两部分。

![img](file:///C:\Users\Lenovo\AppData\Local\Temp\ksohtml35592\wps1.png) 



## 2.1 GIT核心储存结构

GIT的核心储存结构的Object包括blob,tree,commit三类。

其中每一个Object中的value为Object的内容；Key为Object内容的hash。

![img](file:///C:\Users\Lenovo\AppData\Local\Temp\ksohtml35592\wps2.jpg) 



## 2.2 命令行工具

1 初始化

java init

实现细节：1. 创建 .git 文件夹

2 添加文件到暂存区

java add <filename>

3 提交暂存区生成提交记录

java commit -m <message> -a <author> -c <committer>

4 回滚

java reset -s(--soft) -m(--mixed) -h(--hard)

5 创建分支

java branch <branchname>

6 查看分支

java branch

7 切换分支

java checkout <branchname>

8 合并分支

Java merge <branchname>

9 查看提交记录

java log

10 比较版本区别

Java diff