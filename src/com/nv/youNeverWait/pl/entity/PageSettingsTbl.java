package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the page_settings_tbl database table.
 * 
 */
@Entity
@Table(name="page_settings_tbl")
public class PageSettingsTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	private String alignment;

	@Column(name="bg_image")
	private String bgImage;

	private Boolean bold;

	private Boolean border;

	@Column(name="font_size", length=10)
	private String fontSize;

	private String height;
	private Boolean italics;

	@Column(name="key_name")
	private String keyName;

	@Column(name="label_value")
	private String labelValue;

	@Column(name="margin_left", length=10)
	private String marginLeft;

	@Column(name="margin_right", length=10)
	private String marginRight;

	@Column(name="margin_top", length=10)
	private String marginTop;

	private Boolean underline;

	private Boolean visible;

	private String width;

	//bi-directional many-to-one association to LabBranchTbl
	@ManyToOne
	@JoinColumn(name="branch_id", nullable=false)
	private LabBranchTbl labBranchTbl;

	public PageSettingsTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlignment() {
		return this.alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	public String getBgImage() {
		return this.bgImage;
	}

	public void setBgImage(String bgImage) {
		this.bgImage = bgImage;
	}

	public Boolean getBold() {
		return this.bold;
	}

	public void setBold(Boolean bold) {
		this.bold = bold;
	}

	public Boolean getBorder() {
		return this.border;
	}

	public void setBorder(Boolean border) {
		this.border = border;
	}

	public String getFontSize() {
		return this.fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}

	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public Boolean getItalics() {
		return this.italics;
	}

	public void setItalics(Boolean italics) {
		this.italics = italics;
	}

	public String getMarginLeft() {
		return this.marginLeft;
	}

	public void setMarginLeft(String marginLeft) {
		this.marginLeft = marginLeft;
	}

	public String getMarginRight() {
		return this.marginRight;
	}

	public void setMarginRight(String marginRight) {
		this.marginRight = marginRight;
	}

	public String getMarginTop() {
		return this.marginTop;
	}

	public void setMarginTop(String marginTop) {
		this.marginTop = marginTop;
	}

	public Boolean getUnderline() {
		return this.underline;
	}

	public void setUnderline(Boolean underline) {
		this.underline = underline;
	}

	public Boolean getVisible() {
		return this.visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public String getWidth() {
		return this.width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * @return the keyName
	 */
	public String getKeyName() {
		return keyName;
	}

	/**
	 * @param keyName the keyName to set
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	/**
	 * @return the labelValue
	 */
	public String getLabelValue() {
		return labelValue;
	}

	/**
	 * @param labelValue the labelValue to set
	 */
	public void setLabelValue(String labelValue) {
		this.labelValue = labelValue;
	}

	public LabBranchTbl getLabBranchTbl() {
		return this.labBranchTbl;
	}

	public void setLabBranchTbl(LabBranchTbl labBranchTbl) {
		this.labBranchTbl = labBranchTbl;
	}

}