/**
 * SettingsParameters.java
 * @author Mani E.V 
 *
 * Version 1.0 06-Nov-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.rs.dto;

/**
 *
 *
 * @author Mani E.V
 */
public class PageLayoutSettings {
	private String label;
	private boolean bold;
	private boolean italics;
	private boolean underline;
	private boolean visible;
	private String width;
	private String height;
	private String marginTop;
	private String marginLeft;
	private String marginRight;
	private String fontSize;
	private String key;
	private boolean border;
	private String bgImage;
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the bold
	 */
	public boolean isBold() {
		return bold;
	}
	/**
	 * @param bold the bold to set
	 */
	public void setBold(boolean bold) {
		this.bold = bold;
	}
	/**
	 * @return the italics
	 */
	public boolean isItalics() {
		return italics;
	}
	/**
	 * @param italics the italics to set
	 */
	public void setItalics(boolean italics) {
		this.italics = italics;
	}
	/**
	 * @return the underline
	 */
	public boolean isUnderline() {
		return underline;
	}
	/**
	 * @param underline the underline to set
	 */
	public void setUnderline(boolean underline) {
		this.underline = underline;
	}
	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}
	/**
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	/**
	 * @return the width
	 */
	public String getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(String width) {
		this.width = width;
	}
	/**
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}
	/**
	 * @param label
	 * @param bold
	 * @param italics
	 * @param underline
	 * @param visible
	 * @param width
	 * @param height
	 * @param marginTop 
	 * @param bgImage 
	 */
	public PageLayoutSettings(String label, boolean bold, boolean italics,
			boolean underline, boolean visible, String width, String height, String marginTop, String bgImage) {
		super();
		this.label = label;
		this.bold = bold;
		this.italics = italics;
		this.underline = underline;
		this.visible = visible;
		this.width = width;
		this.height = height;
		this.marginTop=marginTop;
		this.bgImage=bgImage;
	}
	/**
	 * 
	 */
	public PageLayoutSettings() {
		super();
	}
	/**
	 * @return the marginTop
	 */
	public String getMarginTop() {
		return marginTop;
	}
	/**
	 * @param marginTop the marginTop to set
	 */
	public void setMarginTop(String marginTop) {
		this.marginTop = marginTop;
	}
	/**
	 * @return the marginLeft
	 */
	public String getMarginLeft() {
		return marginLeft;
	}
	/**
	 * @param marginLeft the marginLeft to set
	 */
	public void setMarginLeft(String marginLeft) {
		this.marginLeft = marginLeft;
	}
	/**
	 * @return the marginRight
	 */
	public String getMarginRight() {
		return marginRight;
	}
	/**
	 * @param marginRight the marginRight to set
	 */
	public void setMarginRight(String marginRight) {
		this.marginRight = marginRight;
	}
	/**
	 * @return the fontSize
	 */
	public String getFontSize() {
		return fontSize;
	}
	/**
	 * @param fontSize the fontSize to set
	 */
	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the border
	 */
	public boolean isBorder() {
		return border;
	}
	/**
	 * @param border the border to set
	 */
	public void setBorder(boolean border) {
		this.border = border;
	}
	/**
	 * @return the bgImage
	 */
	public String getBgImage() {
		return bgImage;
	}
	/**
	 * @param bgImage the bgImage to set
	 */
	public void setBgImage(String bgImage) {
		this.bgImage = bgImage;
	}
	
	
}
