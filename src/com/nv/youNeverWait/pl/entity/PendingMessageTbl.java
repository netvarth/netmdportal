package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the pending_message_tbl database table.
 * 
 */
@Entity
@Table(name="pending_message_tbl")
public class PendingMessageTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="application_specifier", nullable=false, length=1)
	private String applicationSpecifier;

	@Column(name="attempts_made")
	private int attemptsMade;

	@Column(name="cluster_id")
	private int clusterId;

	@Column(name="communication_type", nullable=false, length=1)
	private String communicationType;

    @Lob()
	private String content;

	@Column(name="error_code", length=1)
	private String errorCode;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="last_attempt_on")
	private Date lastAttemptOn;

	@Column(nullable=false, length=1)
	private String status;

    public PendingMessageTbl() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApplicationSpecifier() {
		return this.applicationSpecifier;
	}

	public void setApplicationSpecifier(String applicationSpecifier) {
		this.applicationSpecifier = applicationSpecifier;
	}

	public int getAttemptsMade() {
		return this.attemptsMade;
	}

	public void setAttemptsMade(int attemptsMade) {
		this.attemptsMade = attemptsMade;
	}

	public int getClusterId() {
		return this.clusterId;
	}

	public void setClusterId(int clusterId) {
		this.clusterId = clusterId;
	}

	public String getCommunicationType() {
		return this.communicationType;
	}

	public void setCommunicationType(String communicationType) {
		this.communicationType = communicationType;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Date getLastAttemptOn() {
		return this.lastAttemptOn;
	}

	public void setLastAttemptOn(Date lastAttemptOn) {
		this.lastAttemptOn = lastAttemptOn;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}