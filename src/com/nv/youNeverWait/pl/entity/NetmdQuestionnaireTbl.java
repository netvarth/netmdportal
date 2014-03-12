package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the netmd_questionnaire_tbl database table.
 * 
 */
@Entity
@Table(name="netmd_questionnaire_tbl")
public class NetmdQuestionnaireTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time", nullable=false)
	private Date createdTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_time", nullable=false)
	private Date updatedTime;

	//bi-directional many-to-one association to NetmdAnswerTbl
	@OneToMany(mappedBy="netmdQuestionnaireTbl")
	private Set<NetmdAnswerTbl> netmdAnswerTbls;

	public NetmdQuestionnaireTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Set<NetmdAnswerTbl> getNetmdAnswerTbls() {
		return this.netmdAnswerTbls;
	}

	public void setNetmdAnswerTbls(Set<NetmdAnswerTbl> netmdAnswerTbls) {
		this.netmdAnswerTbls = netmdAnswerTbls;
	}

	public NetmdAnswerTbl addNetmdAnswerTbl(NetmdAnswerTbl netmdAnswerTbl) {
		getNetmdAnswerTbls().add(netmdAnswerTbl);
		netmdAnswerTbl.setNetmdQuestionnaireTbl(this);

		return netmdAnswerTbl;
	}

	public NetmdAnswerTbl removeNetmdAnswerTbl(NetmdAnswerTbl netmdAnswerTbl) {
		getNetmdAnswerTbls().remove(netmdAnswerTbl);
		netmdAnswerTbl.setNetmdQuestionnaireTbl(null);

		return netmdAnswerTbl;
	}

}