# 父类

## command

- 使用Scanner接收用户指令实现命令行交互

# 子类

## init

初始化仓库，用于创建一个空的git仓库，或重置一个已存在的git仓库

## add

把当前工作目录中的文件放入暂存区域。
1.  将本地文件的时间戳、长度，当前文档对象的id等信息保存到一个树形目录中去（即暂存区） 

3.  将本地文件的内容做快照并保存到对象库中。

## commit

•在给定的工作区域，生成相应的blob、tree和commit

包含根目录tree对象的key  包含前一次commit的key  以上两行构成本次commit的value，本次commit的key就是以上两行内容的哈希  第一次commit没有前一次commit

- 提示：
需要存储指向当前最新commit的HEAD指针
每次新生成一个commit前，需要把根目录的tree key与已有的最新commit的tree key进行比较，发现不相同时（即文件发生了变动）才添加这个commit

## reset

重置暂存区文件，相当于暂存区该文件恢复到上一次`commit`状态

 - 根据commit key查询得到commit的value  
 -  从commit value中解析得到根目录tree的key  
 - 恢复(path)：  
 - 根据tree的key查询得到value     
   - 解析value中的每一条记录，即这个tree对象所代表的文件夹内的子文件与子文件夹名称以及对应的blob/tree key     
   - 对于blob，在path中创建文件，命名为相应的文件名，写入blob的value     
   - 对于tree，在path中创建文件夹，命名为相应的文件夹名，递归调用恢复(path+文件夹名)
 - 回滚至某个commit后，需要更新HEAD指针