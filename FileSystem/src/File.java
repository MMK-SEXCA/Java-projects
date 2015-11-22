
public class File extends Entry{
	
	String contents;
	int size;
	
	public File(Directory p, String n, int size) {
		super(p, n);
		this.size = size;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	
	
}
