package saxparser;

/**
 * @author PanShan
 * @version 1.0
 *          <p>
 *          定义文档类
 *          </p>
 */

public class Document {
	public String url = null;
	public String docno = null;
	public String content = null;

	public Document() {
		super();
	}

	@Override
	public String toString() {
		if (this.url == null || this.docno == null || this.content == null) {
			return "null";
		} else {
			String line = "url=" + url + ",docno=" + docno + ",content=" + content;
			return line;
		}
	}
}
