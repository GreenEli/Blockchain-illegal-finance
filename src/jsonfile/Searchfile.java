package jsonfile;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Searchfile {
	public static void main(String[] args) throws IOException {
		Scanner scanner=new Scanner(System.in);
		System.out.println("please put search key");
		String path=scanner.next();
		File file = new File(path); 
		Desktop.getDesktop().open(file); 
	}

}
