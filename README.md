# Blockchain-illegal-finance

## Engineering requirements:
### As the blockchain group in the illegal financial prevention project, it is mainly responsible for storing suspicious activities in illegal financial activities in the form of blockchain in the big data cluster. The requirements that need to be met are as follows:
1. The amount of financial data is huge and needs to be stored in a cluster that can store massive amounts of big data.
2. The traceability and anti-tampering characteristics of the massive original data using the blockchain are further improved and then stored for the second time.
3. It can provide a visual interface of the blockchain that facilitates interaction with users

## project instruction
-Aiming at the first point, this project adopts the hbase cluster based on the Hadoop ecology for large-scale data storage through comprehensive trade-offs, because the Hadoop ecosystem has significant map-reduce functions (distributed computing, distributed storage), and because Hadoop cannot store small files, the hbase cluster in the Hadoop ecosystem that can make up for the shortcomings is further used for storage.
-For the second point, this project uses the Java programming language to perform secondary processing on the original data (separation of original data, extraction of separated data, cryptographic processing of extracted data, and transfer of processed data to MySQL, HBASE).
-For the third point, this project uses a Tomcat-based front-end server for processing, and uses JavaScript to further program the front-end web page.

## Prepare the basics
1. The databases used in this project are MSQL, HADOOP, HBASE
2. The programming language used in this project is java
3. This project uses tomcat server and uses JavaScript language for programming

## Quick start instructions:
Due to the failure to return to school during the epidemic at that time, and it was difficult for the laptop to run a multi-node hbase cluster on a virtual machine, after comprehensive consideration, we chose to run a pseudo-distributed single-node cluster on the windows operating system.
The use environment of this project is the windows operating system, and the programming tool is the original ecliipse editor.
The corresponding database version is mysql: 5.6.47; hadoop 2.7.2; hbase 1.3.6.
### Quick start introduction:
1. After successfully installing the mysql, hadoop, and hbase databases, open the databases in turn.

 ![image](https://github.com/GreenEli/Blockchain-illegal-finance/blob/main/pic/1.png)
 
2. Clone the author's code for this project, and open the project with the eclipse editor (if you use the IDE editor, you need to import the relevant jar package).

 ![image](https://github.com/GreenEli/Blockchain-illegal-finance/blob/main/pic/2.png)
 
3. Create a test02 database in the mysql background, and create a table in the hbase database. The table name and two column family information are (Economic_illegal, FileData, Others).

 ![image](https://github.com/GreenEli/Blockchain-illegal-finance/blob/main/pic/3.png)
 ![image](https://github.com/GreenEli/Blockchain-illegal-finance/blob/main/pic/3-2.png)
 
4. Then execute the Creatchart.java statement in the project code to get the secondary processing data of mysql and hbase

 ![image](https://github.com/GreenEli/Blockchain-illegal-finance/blob/main/pic/4.png)
 ![image](https://github.com/GreenEli/Blockchain-illegal-finance/blob/main/pic/4-2.png)
 ![image](https://github.com/GreenEli/Blockchain-illegal-finance/blob/main/pic/4-3.png)
 
5. Open Tomcat after installing Tomcat server.

![image](https://github.com/GreenEli/Blockchain-illegal-finance/blob/main/pic/5.png)

6. Import register1.jsp and test1.js in the source code into the webapps/ROOT folder under the tomcat home directory

 ![image](https://github.com/GreenEli/Blockchain-illegal-finance/blob/main/pic/6.png)
 
7. Enter localhost:8080/register1.jsp in the browser and enter the web page

![image](https://github.com/GreenEli/Blockchain-illegal-finance/blob/main/pic/7-1.png)
![image](https://github.com/GreenEli/Blockchain-illegal-finance/blob/main/pic/7-2.png)
![image](https://github.com/GreenEli/Blockchain-illegal-finance/blob/main/pic/7-3.png)
 


