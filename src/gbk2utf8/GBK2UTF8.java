package gbk2utf8;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author PanShan
 * @version 1.0
 *          <p>
 *          将文件以GBK解码，并转换成UTF-8编码存储起来
 *          </p>
 */

public class GBK2UTF8 {
	public static void main(String[] args) throws IOException {
		// 输入、输出路径
		String src = "news_sohusite_xml.dat";
		String des = "news_sohusite_xml.txt";

		// 创建字符输入、输出流
		FileInputStream fis = new FileInputStream(src);
		InputStreamReader isr = new InputStreamReader(fis, "GBK");

		FileOutputStream fos = new FileOutputStream(des);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");

		// 读取输入流，并写出到输出流
		int count = 0;
		char[] buffer = new char[1024];
		int len = -1;
		osw.write("<news>\n");
		while ((len = isr.read(buffer)) > -1) {
			String line = new String(buffer, 0, len);
			line = line.replace("&", "_");
			osw.write(line);
			System.out.println(count++);
		}
		osw.write("</news>");

		// 关闭流
		isr.close();
		fis.close();
		osw.close();
		fos.close();
	}
}
