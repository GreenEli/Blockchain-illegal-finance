# Blockchain-illegal-finance

## 工程需求：
### 作为非法金融防范项目中的区块链组，主要负责将非法金融活动中的可疑活动以区块链的形式保存在大数据集群中，需要符合的要求点如下：
1. 金融数据量巨大，需要存放在能存储海量大数据的集群中
2. 对海量原始数据使用区块链的可追溯、防篡改特性进行进一步的改进后二次存储
3. 可以提供一个便于与用户交互的区块链的可视化界面

## 项目说明
- 针对第一点，本项目通过综合取舍，采用基于Hadoop生态的hbase集群进行大规模数据存储。由于Hadoop生态具有显著的map-reduce功能（分布式计算、分布式存储），同时由于Hadoop不能存储小文件的不足，进一步采用能弥补该不足的Hadoop生态上的hbase集群进行存储。
- 针对第二点，本项目采用Java编程语言对原始数据进行二次加工（原始数据分离、分离数据提取、提取数据密码学处理、处理后数据转存MySQL、HBASE）
- 针对第三点，本次项目使用基于Tomcat的前端服务器进行处理，采用JavaScript对前端网页进行进一步编程。

## 准备基础
1. 本项目应用的数据库有MSQL、HADOOP、HBASE
2. 本项目应用的编程语言为java
3. 本项目应用tomcat服务器，采用JavaScript语言进行编程

## 快速入手使用说明：
由于当时疫情期间未能返校，而笔记本难以在虚拟机上运行多节点hbase集群，最后经过综合考虑，选择在windows操作系统上运行伪分布式单节点集群。
本项目使用环境为windows操作系统，采用编程工具为原始ecliipse编辑器
其中相应数据库版本为mysql：5.6.47；hadoop 2.7.2；hbase 1.3.6
### 快速入手使用介绍：
1.成功安装mysql、hadoop、hbase数据库后，将数据库依次打开

 ![image](https://github.com/GreenEli/Blockchain-illegal-finance/blob/main/pic/1.png)
 
2. 将笔者的本项目代码clone下来，并用eclipse编辑器打开该项目（如用IDE编辑器，则需导入相关jar包）

 ![image](https://github.com/GreenEli/Blockchain-illegal-finance/blob/main/pic/2.png)
 
3. 在mysql后台建立一个test02数据库，在hbase数据库中建一个表，表名和两个列族信息分别为（Economic_illegal, FileData, Others）

 ![image](https://github.com/GreenEli/Blockchain-illegal-finance/blob/main/pic/3.png)
 ![image](https://github.com/GreenEli/Blockchain-illegal-finance/blob/main/pic/3-2.png)
 
4. 接着执行项目代码中Creatchart.java语句，得到mysql、hbase二次处理数据

 ![image](https://github.com/GreenEli/Blockchain-illegal-finance/blob/main/pic/4.png)
 ![image](https://github.com/GreenEli/Blockchain-illegal-finance/blob/main/pic/4-2.png)
 ![image](https://github.com/GreenEli/Blockchain-illegal-finance/blob/main/pic/4-3.png)
 
5. 安装完Tomcat服务器后打开Tomcat

![image](https://github.com/GreenEli/Blockchain-illegal-finance/blob/main/pic/5.png)

6. 将源代码中register1.jsp和test1.js导入tomcat主目录下的webapps/ROOT文件夹下

 ![image](https://github.com/GreenEli/Blockchain-illegal-finance/blob/main/pic/6.png)
 
7. 在浏览器输入localhost:8080/register1.jsp,进入前端页面即可

![image](https://github.com/GreenEli/Blockchain-illegal-finance/blob/main/pic/7-1.png)
![image](https://github.com/GreenEli/Blockchain-illegal-finance/blob/main/pic/7-2.png)
![image](https://github.com/GreenEli/Blockchain-illegal-finance/blob/main/pic/7-3.png)
 
### 此外，本项目的后续工作是进一步将项目部署在基于linux服务器版操作系统的完全分布式集群中。


