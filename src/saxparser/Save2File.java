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
 *          ���屣���ļ��࣬�ṩ�����ļ��ľ�̬��������url�е���������Ϊ���docno��Ϊ�ļ�������content���б��棻
 *          ���У�url�к��в�����Ϊ�ļ�����"."�ţ���Ҫ�����滻��ԭʼ���ݼ�����Щcontent����Ϊ�ջ�̫�̣�����ǰ�Ƚ��г������ˡ�
 *          </p>
 */

public class Save2File {
	private static String root_dir = "D:/develop/eclipse-mars-jee/workspace/Classify/dataOut";
	private static long counter = 0;

	public static void save(String url, String docno, String content) throws IOException {
		// ���˽϶��ı�
		if (content == null || content.length() < 30) {
			;
		} else {
			URL raw_url = new URL(url);

			// ��url������������洢
			// String host = raw_url.getHost();
			// String[] category = host.split("\\."); //������ʽ���׳�������/

			String category = raw_url.getHost();
			category = category.replace(".", "_");

			File dirpath = new File(root_dir, category);
			if (!dirpath.exists()) {// ��Ŀ¼������
				dirpath.mkdirs();// mkdirs()�����༶Ŀ¼��mkdir()����һ��Ŀ¼
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
