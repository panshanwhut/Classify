package saxparser;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Process {
	public static void main(String[] args) {
		try {
			// 首先，利用工厂方法生成SAXParser对象
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();

			// 然后，实例化事件处理类
			Handler handler = new Handler();

			// 将事件处理类对象传入SAXParser的parse()方法，进行解析
			parser.parse(new File("D:/develop/eclipse-mars-jee/workspace/Classify/dataIn/news_sohusite_xml.txt"),
					handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
