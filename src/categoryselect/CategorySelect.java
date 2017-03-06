package categoryselect;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author PanShan
 * @version 1.0
 *          <p>
 *          挑选出所有类别中文档数目大于1000的类别，并存储到指定路径下
 *          </p>
 */

public class CategorySelect {
	public static void main(String[] args) {
		String src_dir = "D:\\develop\\eclipse-mars-jee\\workspace\\Classify\\dataOut";
		String des_dir = "D:\\develop\\eclipse-mars-jee\\workspace\\Classify\\raw_material";
		
		File src_dir_path = new File(src_dir);
		File[] files = src_dir_path.listFiles();
		
		ArrayList<KV> arrlist = new ArrayList<KV>();
		for (File file : files) {
			arrlist.add(new KV(file, file.listFiles().length));
		}
		
		Collections.sort(arrlist);
		
		for (int i = 0; i < 50; i++) {
			KV kv = arrlist.get(i);
			System.out.println(kv.value);
			System.out.println(kv.key.getName());
//			kv.key.renameTo(new File(des_dir+"\\"+i));
		}
	}
	
}
class KV implements Comparable<KV>{
	public File key = null;
	public int value = 0;
	
	public KV(File key, int value) {
		this.key = key;
		this.value = value;
	}
	public KV(){
		super();
	}
	
	@Override
	public int compareTo(KV o) {
		return o.value - this.value;
	}
	
	@Override
	public String toString() {
		return value+"";
	}
}
