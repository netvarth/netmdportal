package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the upload_installer_tbl database table.
 * 
 */
@Entity
@Table(name="upload_installer_tbl")
public class UploadInstallerTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="`application _name`")
	private String application_Name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Lob
	private String query;

	@Column(name="version_no")
	private float versionNo;

	@Column(name="war_file_path",length=500)
	private String warFilePath;

	/**
	 * 
	 */
	public UploadInstallerTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApplication_Name() {
		return this.application_Name;
	}

	public void setApplication_Name(String application_Name) {
		this.application_Name = application_Name;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getQuery() {
		return this.query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public float getVersionNo() {
		return this.versionNo;
	}

	public void setVersionNo(float versionNo) {
		this.versionNo = versionNo;
	}

	public String getWarFilePath() {
		return this.warFilePath;
	}

	public void setWarFilePath(String warFilePath) {
		this.warFilePath = warFilePath;
	}

}