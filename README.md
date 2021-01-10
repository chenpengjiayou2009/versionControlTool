# **1** 项目概况

本项目为Java课程第15小组的课程项目，项目内容为使用Java依据git底层原理实现的可以模仿部分git命令的版本控制工具。组长为陈鹏；组员包括罗子衿、毕在然。



# **2** 功能实现情况

本版本管理工具实现的功能主要包括GIT核心储存结构与命令行工具两部分。 



## 2.1 GIT核心储存结构

### **Key-value存储**

1. value：Object的内容
2. Key：Object内容的hash

### **Object的三种类型**

**1. Blob：文件**

- Blob的Value：文件内容
- Blob的Key：文件内容的hash值

**2. Tree：文件夹**

- Tree的Value包含以下三个部分：
  1. 子文件夹和子文件名称；
     2. 每个子文件Blob key；
     3. 每个子文件夹tree的key；
- Tree的Key：Tree的Value的hash值

**3. Commit：提交**

- Commit的Value：
  1. 项目根目录tree对象的key；
     2. 前驱commit对象的key；
     3. 代码author；
     4. 代码commiter；
     5. commit时间戳；
     6. commit备注/注释；
- Commit的Key：Commit的Value的hash值



## 2.2 命令行工具

请先编译然后将包含Git.class的文件夹加入CLASSPATH并将Apache-Common-cli.jar加入CLASSPATH

### 1 初始化

java Git init

实现细节：1. 创建 .git 文件夹

### 2 添加文件到暂存区

java Git add <filename>

### 3 提交暂存区生成提交记录

java Git commit -m <message> -a <author> -c <committer>

### 4 回滚

java Git reset -s(--soft) -m(--mixed) -h(--hard)

### 5 创建分支

java Git branch <branchname>

### 6 查看分支

java Git branch

### 7 切换分支

java Git checkout <branchname>


### 9 查看提交记录

java Git log

### 10 比较版本区别

Java Git diff