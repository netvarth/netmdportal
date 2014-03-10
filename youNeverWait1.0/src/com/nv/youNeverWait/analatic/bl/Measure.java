package com.nv.youNeverWait.analatic.bl;

public class Measure {
	
	private String  key;
	private Integer measure;
	private String  year;
	private String  month;
	private String  hospital;
    private String  column=null;
	
	
	public Measure(String key, Integer measure, String year, String month,
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
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
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
