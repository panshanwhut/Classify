package saxparser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;

/**
 * @author PanShan
 * @version 1.0
 *          <p>
 *          定义保存文件类，提供保存文件的静态方法。将url中的主机名作为类别，docno作为文件名，对content进行保存；
 *          其中，url中含有不能作为文件名的"."号，需要进行替换。原始数据集中有些content内容为空或太短，保存前先进行初步过滤。
 *          </p>
 */

public class Save2File {
	private static String root_dir = "D:/develop/eclipse-mars-jee/workspace/Classify/dataOut";
	private static long counter = 0;

	public static void save(String url, String docno, String content) throws IOException {
		// 过滤较短文本
		if (content == null || content.length() < 30) {
			;
		} else {
			URL raw_url = new URL(url);

			// 按url的主机名分类存储
			// String host = raw_url.getHost();
			// String[] category = host.split("\\."); //正则表达式容易出错，两个/

			String category = raw_url.getHost();
			category = category.replace(".", "_");

			File dirpath = new File(root_dir, category);
			if (!dirpath.exists()) {// 子目录不存在
				dirpath.mkdirs();// mkdirs()创建多级目录，mkdir()创建一个目录
			}

			File fpath = new File(dirpath, String.format("%s.txt", docno));
			FileOutputStream fos = new FileOutputStream(fpath);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			osw.write(content);

			osw.close();
			fos.close();

			System.out.println(counter++);
		}
	}
}
