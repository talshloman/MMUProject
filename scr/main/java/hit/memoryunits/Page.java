
package hit.memoryunits;

@SuppressWarnings("serial")
public class Page<T> implements java.io.Serializable  {
	private T content;
	private long pageId;

	public Page(int id, T content) {
		this.setPageId(id);
		this.setContent(content);
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public long getPageId() {
		return pageId;
	}

	public void setPageId(long id) {
		this.pageId = id;
	}

	@Override
	public int hashCode() {

		return (int) pageId;

	}

	@Override
	public boolean equals(Object obj) {
		return this.content.equals(obj);
	}

	@Override
	public String toString() {
		return "Page [pageId=" + pageId + ",content=" + content + "]";
	}

}
