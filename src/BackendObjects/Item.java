package BackendObjects;


public class Item{
	private String description;
	private boolean check;
	public Item(String description) {
		this.description = description;
		this.check = false;
	}
	
	public Item(String description,boolean check) {
		this.description = description;
		this.check = check;
	}
	
	public String getDescription() {
		return description;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public void enableCheck() {
		this.check = true;
	}
	
	public void disableCheck() {
		this.check = false;
	}
	
	public void toggleCheck() {
		this.check = !this.check;
	}
}
