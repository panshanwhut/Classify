package saxparser;

import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.Date;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author PanShan
 * @version 1.0
 *          <p>
 *          SAX���������е��¼������࣬�����˲�ͬ�¼������
 *          </p>
 *          <p>
 *          ���У����������е���Ҫ��ʱ���ļ�д����̹����У�����IO�ܼ��͹�����д�ļ����̿��������̳߳ؽ��ж��̹߳��������Ч��
 *          </p>
 *          <p>
 *          ���߳������£��ܹ���ʱ114���ӣ��̳߳ص�ʹ���д���һ��ʵ�֡�
 *          </p>
 */

public class Handler extends DefaultHandler {
	// private ExecutorService cachedThreadPool =
	// Executors.newSingleThreadExecutor();

	private Document document = null;

	// ��ʶ���������еı�ǩ״̬
	private boolean b_url = false;
	private boolean b_docno = false;
	private boolean b_content = false;

	// ��¼���������ļ�����ֹʱ��
	private long startTime = 0;
	private long endTime = 0;

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		startTime = new Date().getTime();
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		endTime = new Date().getTime();
		System.out.println("XML files were parsed, total time:" + ((endTime - startTime) / 1000.0) + "s");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("doc")) {
			document = new Document();
		} else if (qName.equalsIgnoreCase("url")) {
			b_url = true;
		} else if (qName.equalsIgnoreCase("docno")) {
			b_docno = true;
		} else if (qName.equalsIgnoreCase("content")) {
			b_content = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("doc")) {
			// cachedThreadPool.execute(new Runnable() {
			// @Override
			// public void run() {
			// try {
			// Save2File.save(document.url, document.docno, document.content);
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
			// }
			// });
			try {
				Save2File.save(document.url, document.docno, document.content);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (b_url) {
			document.url = new String(ch, start, length);
			b_url = false;// ������󣬱���رյ�����Ϊ��������һ��Ԫ��Authorʱcharacters���ᱻִ�У�������رյ�������ִ�н�����
		} else if (b_docno) {
			document.docno = new String(ch, start, length);
			b_docno = false;
		} else if (b_content) {
			document.content = new String(ch, start, length);
			b_content = false;
		}
	}

}
