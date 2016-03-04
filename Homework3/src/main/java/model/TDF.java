package model;

public class TDF {
	
	private int sNO;
	private String id;
	private int count;
	
	
	public TDF(int sNO, String id, int count) {
		super();
		this.sNO = sNO;
		this.id = id;
		this.count = count;
	}
	
	public TDF() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getsNO() {
		return sNO;
	}
	public void setsNO(int sNO) {
		this.sNO = sNO;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	

}
