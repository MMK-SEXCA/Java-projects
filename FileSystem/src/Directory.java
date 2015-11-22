import java.util.ArrayList;

public class Directory extends Entry{
	
	protected ArrayList<Entry> contents;
	
	public Directory(Directory p, String n) {
		super(p, n);
		this.contents = new ArrayList<Entry>();
	}

	public boolean deleteEntry(Entry entry) {
		return this.contents.remove(entry);
	}
	
	public void addEntry(Entry entry){
		this.contents.add(entry);
	}
	@Override
	public int size() {
		int size = 0;
		for(Entry e : this.contents){
			size += e.size();
		}
		return size;
	}
	
	public int numberOfFiles(){
		int count = 0;
		for(Entry e : this.contents){
			if(e instanceof Directory){
				count += ((Directory) e).numberOfFiles();
			}else if(e instanceof File){
				count++;
			}
		}
		return count;
	}

	public ArrayList<Entry> getContents() {
		return contents;
	}

}
