package hbase;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class ExampleForHbase {
	public static Configuration configuration;
	public static Connection connection;
	public static Admin admin;

	// 主函数中的语句请逐句执行，只需删除其前的//即可，如：执行insertRow时请将其他语句注释
	public static void main(String[] args) throws IOException {
/*		//a test foe add
		File file = new File("I:/同花顺_20-04-29/block801-1300");
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
	        
			File temp = files[i];
			 String sha= getFileSha1(temp);
			 System.out.println(sha);
			 insertRow("Score", sha, "sname", "", temp.getName());
			 System.out.println("insert ok");
		}*/
		Scanner in = new Scanner(System.in);
		while (true) {
			System.out.println("******************HBase对数据库的操作******************");
			System.out.println("1.创建一个表");
			System.out.println("2.在Score表中插入一条数据，其行键为95001,sname为Mary");
			System.out.println("3.在Score表中插入一条数据，其行键为95001,course:Math为88");
			System.out.println("4.在Score表中插入一条数据，其行键为95001,course:English为85");
			System.out.println("5.删除Score表中指定列数据，其行键为95001,列族为course，列为Math");
			System.out.println("6.删除Score表中指定列族数据，其行键为95001,列族为course");
			System.out.println("7.删除Score表中指定行数据，其行键为95001");
			System.out.println("8.查询Score表中，行键为95001，列族为course，列为Math的值");
			System.out.println("9.查询Score表中，行键为95001，列族为sname的值");
			System.out.println("10.删除Score表");
			System.out.println("请输入相对序号：");
			int a = in.nextInt();
			switch (a) {
			
			case 1:
				// 创建一个表，表名为Score，列族为sname,course
				createTable("Economic_illegal", new String[] { "FileData", "Others" });
//				createTable("Score", new String[] { "sname", "course" });
				break;
				
			case 2:
				// 在Score表中插入一条数据，其行键为95001,sname为Mary（因为sname列族下没有子列所以第四个参数为空）
				// 等价命令：put 'Score','95001','sname','Mary'
				String string1="http://basic.10jqka.com.cn/688159/company.html\", \"corporationBackground_industryCommerceImformation_name\": \"深圳市有方科技股份有限公司";
				insertRow("Score", "95003", "sname", "", string1);
				
				break;
				
			case 3:
				// 在Score表中插入一条数据，其行键为95001,course:Math为88（course为列族，Math为course下的子列）
				// 等价命令：put 'Score','95001','score:Math','88'
				String string=null;
//				insertRow("Score", "95001", "course", "Math", "88");
				insertRow("Economic_illegal", "95001", "FileData", "Title", "88");
				insertRow("Economic_illegal", "95001", "FileData", "Content", "88");
				break;
				
			case 4:
				// 在Score表中插入一条数据，其行键为95001,course:English为85（course为列族，English为course下的子列）
				// 等价命令：put 'Score','95001','score:English','85'
				insertRow("Score", "95001", "course", "English", "85");
				break;
				
			case 5:
				// 1、删除Score表中指定列数据，其行键为95001,列族为course，列为Math
				// 执行这句代码前请deleteRow方法的定义中，将删除指定列数据的代码取消注释注释，将删除制定列族的代码注释
				// 等价命令：delete 'Score','95001','score:Math'
				deleteRow("Score", "95001", "course", "Math");
				break;
				
			case 6:
				// 2、删除Score表中指定列族数据，其行键为95001,列族为course（95001的Math和English的值都会被删除）
				// 执行这句代码前请deleteRow方法的定义中，将删除指定列数据的代码注释，将删除制定列族的代码取消注释
				// 等价命令：delete 'Score','95001','score'
				deleteRow("Score", "95001", "course", "");
				break;
				
			case 7: 
				// 3、删除Score表中指定行数据，其行键为95001
				// 执行这句代码前请deleteRow方法的定义中，将删除指定列数据的代码注释，以及将删除制定列族的代码注释
				// 等价命令：deleteall 'Score','95001'
				deleteRow("Score", "95001", "", "");

				
			case 8:
				// 查询Score表中，行键为95001，列族为course，列为Math的值
				getData("Score", "95003", "sname", "");
				break;
				
			case 9:
				// 查询Score表中，行键为95001，列族为sname的值（因为sname列族下没有子列所以第四个参数为空）
//				getData("Score", "6fb2bd7a49fdb00fd34d9f06eecc0d40de8d1f58", "sname", "");
				getData("Economic_illegal", "04126e93a4e29d1c73aaaa9c73e9520a5c2745e4", "FileData", "Content");
				break;
				
			case 10:
				// 删除Score表
				deleteTable("Score");
				break;
			}
		}
	}

	// 建立连接
	public static void init() {
		configuration = HBaseConfiguration.create();
//		configuration.set("hbase.rootdir", "hdfs://localhost:9000/hbase");
		try {
			connection = ConnectionFactory.createConnection(configuration);
			admin = connection.getAdmin();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 关闭连接
	public static void close() {
		try {
			if (admin != null) {
				admin.close();
			}
			if (null != connection) {
				connection.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 建表。HBase的表中会有一个系统默认的属性作为主键，主键无需自行创建，默认为put命令操作中表名后第一个数据，因此此处无需创建id列
	 * 
	 * @param myTableName
	 *            表名
	 * @param colFamily
	 *            列族名
	 * @throws IOException
	 */
	public static void createTable(String myTableName, String[] colFamily)
			throws IOException {

		init();
		TableName tableName = TableName.valueOf(myTableName);

		if (admin.tableExists(tableName)) {
			System.out.println("talbe is exists!");
		} else {
			HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
			for (String str : colFamily) {
				HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(str);
				hTableDescriptor.addFamily(hColumnDescriptor);
			}
			admin.createTable(hTableDescriptor);
			System.out.println("create table success");
		}
		close();
	}

	/**
	 * 删除指定表
	 * 
	 * @param tableName
	 *            表名
	 * @throws IOException
	 */
	public static void deleteTable(String tableName) throws IOException {
		init();
		TableName tn = TableName.valueOf(tableName);
		if (admin.tableExists(tn)) {
			admin.disableTable(tn);
			admin.deleteTable(tn);
		}
		close();
	}

	/**
	 * 查看已有表
	 * 
	 * @throws IOException
	 */
	public static void listTables() throws IOException {
		init();
		HTableDescriptor hTableDescriptors[] = admin.listTables();
		for (HTableDescriptor hTableDescriptor : hTableDescriptors) {
			System.out.println(hTableDescriptor.getNameAsString());
		}
		close();
	}

	/**
	 * 向某一行的某一列插入数据
	 * 
	 * @param tableName
	 *            表名
	 * @param rowKey
	 *            行键
	 * @param colFamily
	 *            列族名
	 * @param col
	 *            列名（如果其列族下没有子列，此参数可为空）
	 * @param val
	 *            值
	 * @throws IOException
	 */
	public static void insertRow(String tableName, String rowKey,
			String colFamily, String col, String val) throws IOException {
		init();
		Table table = connection.getTable(TableName.valueOf(tableName));
		Put put = new Put(rowKey.getBytes());
		put.addColumn(colFamily.getBytes(), col.getBytes(), val.getBytes());
		table.put(put);
		table.close();
		close();
	}
	public static void insertRowBytes(String tableName, String rowKey,
			String colFamily, String col, String val) throws IOException {
		init();
		Table table = connection.getTable(TableName.valueOf(tableName));
		Put put = new Put(rowKey.getBytes());
		put.addColumn(colFamily.getBytes(), col.getBytes(), val.getBytes());
		table.put(put);
		table.close();
		close();
	}

	/**
	 * 删除数据
	 * 
	 * @param tableName
	 *            表名
	 * @param rowKey
	 *            行键
	 * @param colFamily
	 *            列族名
	 * @param col
	 *            列名
	 * @throws IOException
	 */
	public static void deleteRow(String tableName, String rowKey,
			String colFamily, String col) throws IOException {
		init();
		Table table = connection.getTable(TableName.valueOf(tableName));
		Delete delete = new Delete(rowKey.getBytes());
		// 删除指定列族的所有数据
		// delete.addFamily(colFamily.getBytes());
		// 删除指定列的数据
		// delete.addColumn(colFamily.getBytes(), col.getBytes());

		table.delete(delete);
		table.close();
		close();
	}

	/**
	 * 根据行键rowkey查找数据
	 * 
	 * @param tableName
	 *            表名
	 * @param rowKey
	 *            行键
	 * @param colFamily
	 *            列族名
	 * @param col
	 *            列名
	 * @throws IOException
	 */
	public static void getData(String tableName, String rowKey,
			String colFamily, String col) throws IOException {
		init();
		Table table = connection.getTable(TableName.valueOf(tableName));
		Get get = new Get(rowKey.getBytes());
		get.addColumn(colFamily.getBytes(), col.getBytes());
		Result result = table.get(get);
		showCell(result);
		table.close();
		close();
	}

	/**
	 * 格式化输出
	 * 
	 * @param result
	 */
	public static void showCell(Result result) {
		Cell[] cells = result.rawCells();
		for (Cell cell : cells) {
			System.out.println("RowName:" + new String(CellUtil.cloneRow(cell))
					+ " ");
			System.out.println("Timetamp:" + cell.getTimestamp() + " ");
			System.out.println("column Family:"
					+ new String(CellUtil.cloneFamily(cell)) + " ");
			System.out.println("row Name:"
					+ new String(CellUtil.cloneQualifier(cell)) + " ");
			System.out.println("value:" + new String(CellUtil.cloneValue(cell))
					+ " ");
		}
	}
//	get a hash for a file
	public static String getFileSha1(File file) 
	{
	    FileInputStream in = null;
	    try
	    {
	        in = new FileInputStream(file);
	        MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        byte[] buffer = new byte[1024 * 1024 * 10];

	        int len = 0;
	        while ((len = in.read(buffer)) > 0)
	        {
	            digest.update(buffer, 0, len);
	        }
	        String sha1 = new BigInteger(1, digest.digest()).toString(16);
	        int length = 40 - sha1.length();
	        if (length > 0)
	        {
	            for (int i = 0; i < length; i++)
	            {
	                sha1 = "0" + sha1;
	            }
	        }
	        return sha1;
	    }
	    catch (IOException e)
	    {
	        System.out.println(e);
	    }
	    catch (NoSuchAlgorithmException e)
	    {
	        System.out.println(e);
	    }
	    finally
	    {
	        try
	        {
	            if (in != null)
	            {
	                in.close();
	            }
	        }
	        catch (IOException e)
	        {
	            System.out.println(e);
	        }
	    }
		return null;
	}
}

