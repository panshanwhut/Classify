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
 *          SAX解析过程中的事件处理类，定义了不同事件额处理方法
 *          </p>
 *          <p>
 *          其中，解析过程中的主要耗时在文件写入磁盘过程中，属于IO密集型工作，写文件过程可以利用线程池进行多线程工作，提高效率
 *          </p>
 *          <p>
 *          单线程条件下，总共耗时114分钟；线程池的使用有待进一步实现。
 *          </p>
 */

public class Handler extends DefaultHandler {
	// private ExecutorService cachedThreadPool =
	// Executors.newSingleThreadExecutor();

	private Document document = null;

	// 标识解析过程中的标签状态
	private boolean b_url = false;
	private boolean b_docno = false;
	private boolean b_content = false;

	// 记录解析整个文件的起止时间
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
			b_url = false;// 解析完后，必须关闭掉；因为解析到下一个元素Author时characters还会被执行，如果不关闭掉会首先执行进来。
		} else if (b_docno) {
			document.docno = new String(ch, start, length);
			b_docno = false;
		} else if (b_content) {
			document.content = new String(ch, start, length);
			b_content = false;
		}
	}

}
