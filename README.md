# Java课程项目

本项目为Java课程第12小组的课程项目，项目内容为使用java依据git底层原理实现的可以模仿部分git命令的版本控制工具

## 项目要求

- 命令行工具
- 参考git实现原理，实现blob,tree,commit核心存储结构

**功能点：**

  1. 可以提交commit，可以进行”git log”查看commit历史
  2. 可以进行”git reset”回滚到指定commit
  3. 可创建多分支，可在分支之间切换
  4. 不要求merge功能

## Git实现原理

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

## 项目实现

### 实现key-value存储

- 最简单的key-value存储方式
  - 文件名作为key，文件内容作为value
- 支持以下功能
  - 给定value，向存储中添加对应的key-value
  - 给定key，查找得到对应的value值
- 封装成class对外提供接口
- 单元测试

### 将一个文件夹转化成key,value

- 给定一个文件夹目录，将其转化成若干tree和blob
  - 深度优先遍历此目录
    - 遇到子文件就转化成blob并保存
    - 遇到子文件夹就递归调用其内部的子文件/文件夹最后构造tree并保存
- 使用**实现key-value存储**中提供的接口
- 单元测试

### 开发方式

- 每个组长创建一个github仓库，添加组员的push权限
- 永远使用Pull Request来更新主分支
- Commit描述和PR描述尽可能详细
- 使用issue来讨论/记录开发计划、分工以及问题/bug