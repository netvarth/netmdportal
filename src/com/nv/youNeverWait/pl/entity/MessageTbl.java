package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the message_tbl database table.
 * 
 */
@Entity
@Table(name="message_tbl")
public class MessageTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="created_date_time", length=45)
	private String createdDateTime;

	@Column(name="destination_lab_id", nullable=false)
	private int destinationLabId;

	@Column(name="source_lab_id", nullable=false)
	private int sourceLabId;

	//bi-directional many-to-one association to ResultTbl
    @ManyToOne
	@JoinColumn(name="result_id", nullable=false)
	private ResultTbl resultTbl;

    public MessageTbl() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreatedDateTime() {
		return this.createdDateTime;
	}

	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public int getDestinationLabId() {
		return this.destinationLabId;
	}

	public void setDestinationLabId(int destinationLabId) {
		this.destinationLabId = destinationLabId;
	}

	public int getSourceLabId() {
		return this.sourceLabId;
	}

	public void setSourceLabId(int sourceLabId) {
		this.sourceLabId = sourceLabId;
	}

	public ResultTbl getResultTbl() {
		return this.resultTbl;
	}

	public void setResultTbl(ResultTbl resultTbl) {
		this.resultTbl = resultTbl;
	}
	
}