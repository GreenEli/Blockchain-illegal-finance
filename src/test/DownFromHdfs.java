package test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;


public class DownFromHdfs {
	public static void main(String[] args) throws IOException, URISyntaxException {
      //test for download file from hdfs
        String MASTER_URI = "hdfs://localhost:9000";
        String DEST_PATH = "/aaaaaab/block1-100/东方电子股份有限公司.json";
        Configuration conf1 = new Configuration();

        FileSystem fs1 = FileSystem.get(new URI(MASTER_URI), conf1);

        InputStream in = fs1.open(new Path(DEST_PATH));

        OutputStream out = new FileOutputStream("I:\\TongHuaShunWangZha\\1.txt");

        IOUtils.copyBytes(in, out, conf1);
        System.out.print("ok");
        fs1.close();
	}

}
