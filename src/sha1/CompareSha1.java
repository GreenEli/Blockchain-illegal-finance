package sha1;

import java.io.File;

public class CompareSha1 {
	public static void main(String[] args) {
		File file=new File("I:/同花顺_20-04-29/block201-800/黑龙江国中水务股份有限公司.json");
		Sha_1 sha_1=new Sha_1();
		String test=sha_1.getFileSha1(file);
		System.out.print(test);
	}
}
