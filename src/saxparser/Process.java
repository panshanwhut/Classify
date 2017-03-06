package saxparser;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Process {
	public static void main(String[] args) {
		try {
			// ���ȣ����ù�����������SAXParser����
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();

			// Ȼ��ʵ�����¼�������
			Handler handler = new Handler();

			// ���¼������������SAXParser��parse()���������н���
			parser.parse(new File("D:/develop/eclipse-mars-jee/workspace/Classify/dataIn/news_sohusite_xml.txt"),
					handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
