package com.nv.youNeverWait.analatic.bl;

public class Measure {
	
	private String  key;
	private Integer measure;
	private String  year;
	private int  month;
	private String  hospital;
    private String  column=null;
	
	
	public Measure(String key, Integer measure, String year, int month,
			String hospital) {
		super();
		this.key = key;
		this.measure = measure;
		this.year = year;
		this.month = month;
		this.hospital = hospital;
	}
	
	
	
	public Measure() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getMonth() {
		return month;
	}



	public void setMonth(int month) {
		this.month = month;
	}



	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Integer getMeasure() {
		return measure;
	}
	public void setMeasure(Integer measure) {
		this.measure = measure;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}

	
	
	
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	
	


}
