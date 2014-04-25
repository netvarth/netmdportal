package com.nv.youNeverWait.rs.dto;

public class ReportFilterDTO {
	private int id;
	private  String name;
	
	

	/**
	 * @param id
	 * @param name
	 */
	public ReportFilterDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * 
	 */
	public ReportFilterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	

}
