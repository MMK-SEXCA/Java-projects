
public abstract class Entry {
	public static final String separator = "/"; 
	protected Directory parent;
	protected long created;
	protected long lastUpdated;
	protected long lastAccessed;
	protected String name;
	
	public Entry(Directory p, String n){
		this.parent = p;
		this.name = n;
		this.created = System.currentTimeMillis();
		this.lastUpdated = System.currentTimeMillis();
		this.lastAccessed = System.currentTimeMillis();
	}
	
	public String getFullPath(){
		if (this.parent == null)return this.name;
		return this.parent.getFullPath()+ separator + this.name;
	}
	
	public abstract int size();
	
	public boolean delete(){
		if (this.parent == null)return false;
		return ((Directory) this.parent).deleteEntry(this);
	}
}