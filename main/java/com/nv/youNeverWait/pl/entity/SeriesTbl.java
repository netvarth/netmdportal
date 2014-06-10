package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the series_tbl database table.
 * 
 */
@Entity
@Table(name="series_tbl")
public class SeriesTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="ends_on")
	private int endsOn;

	@Column(name="occurance_type", nullable=false, length=45)
	private String occuranceType;

	@Column(name="schedule_repeat", nullable=false, length=45)
	private String scheduleRepeat;
	
	@Column(name="weekly_type", length=100)
	private String weeklyType;

	//bi-directional many-to-one association to NetmdPassphraseTbl
    @ManyToOne
	@JoinColumn(name="netmd_passphrase_id")
	private NetmdPassphraseTbl netmdPassphraseTbl;
    
    @Column(name="series_id")
	private int seriesId;
    
	//bi-directional many-to-one association to DoctorScheduleTbl
	@OneToMany(mappedBy="seriesTbl")
	private Set<DoctorScheduleTbl> doctorScheduleTbls;

    public SeriesTbl() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getEndsOn() {
		return this.endsOn;
	}

	public void setEndsOn(int endsOn) {
		this.endsOn = endsOn;
	}

	public String getOccuranceType() {
		return this.occuranceType;
	}

	public void setOccuranceType(String occuranceType) {
		this.occuranceType = occuranceType;
	}

	
	public String getWeeklyType() {
		return this.weeklyType;
	}

	public void setWeeklyType(String weeklyType) {
		this.weeklyType = weeklyType;
	}
	public String getScheduleRepeat() {
		return this.scheduleRepeat;
	}

	public void setScheduleRepeat(String scheduleRepeat) {
		this.scheduleRepeat = scheduleRepeat;
	}

	public Set<DoctorScheduleTbl> getDoctorScheduleTbls() {
		return this.doctorScheduleTbls;
	}

	public void setDoctorScheduleTbls(Set<DoctorScheduleTbl> doctorScheduleTbls) {
		this.doctorScheduleTbls = doctorScheduleTbls;
	}
	
	public NetmdPassphraseTbl getNetmdPassphraseTbl() {
		return this.netmdPassphraseTbl;
	}

	public void setNetmdPassphraseTbl(NetmdPassphraseTbl netmdPassphraseTbl) {
		this.netmdPassphraseTbl = netmdPassphraseTbl;
	}
	
	public int getSeriesId() {
		return this.seriesId;
	}

	public void setSeriesId(int seriesId) {
		this.seriesId = seriesId;
	}

}