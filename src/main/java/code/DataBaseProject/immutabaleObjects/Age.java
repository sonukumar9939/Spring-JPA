package code.DataBaseProject.immutabaleObjects;

public class Age implements Cloneable{

	private int day;
	private String month;
	private int year;
	
	public Age() {}
	
	public Age(int day, String month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public Object clone() {
		Object clone = null;
		if(clone==null) {
			try {
				clone= super.clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return clone;
		
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Age [day=" + day + ", month=" + month + ", year=" + year + "]";
	}

}
