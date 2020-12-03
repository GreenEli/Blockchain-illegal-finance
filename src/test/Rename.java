package test;

import java.io.File;

public class Rename {
	public static void main(String[] args) {  
        //想命名的原文件的路径  
        File file = new File("I:\\同花顺_20-04-29\\block1-100\\阿尔特汽车技术股份有xian公司.json");  
        //将原文件更改为f:\a\b.xlsx，其中路径是必要的。注意  
        file.renameTo(new File("I:\\同花顺_20-04-29\\block1-100\\阿尔特汽车技术股份有gon司.json"));  
        //想命名的原文件夹的路径  
        File file1 = new File("I:\\TongHuaShun");  
        //将原文件夹更改为A，其中路径是必要的。注意  
        file1.renameTo(new File("I:\\TongHuaShunWangZha"));  
    }  
}
