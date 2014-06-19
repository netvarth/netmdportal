package com.nv.youNeverWait.analytic.bl;

public class AggregateMeasure extends Measure {
	
	private String  cluster;
	private String  row;
	private Integer measure;
	private String  year;
	private int typeOrder=1;
	private int  month;
	private String  hospital;
    private String  column="";
    
    
	public String getCluster() {
		return cluster;
	}
	public void setCluster(String cluster) {
		this.cluster = cluster;
	}
	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
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
	public int getTypeOrder() {
		return typeOrder;
	}
	public void setTypeOrder(int typeOrder) {
		this.typeOrder = typeOrder;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
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
	
	public AggregateMeasure(String cluster,Measure measure) {
		super();
		this.cluster = cluster;
		this.row=measure.getRow();
		this.measure=measure.getMeasure();
		this.year=measure.getYear();
		this.typeOrder=measure.getTypeOrder();
		this.month=measure.getMonth();
		this.hospital=measure.getHospital();
	    this.column=measure.getColumn();
	}
    
    
    
    
    

}
