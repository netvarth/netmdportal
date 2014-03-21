package com.nv.youNeverWait.report;

import java.util.List;

import com.nv.youNeverWait.analatic.bl.Analatic;
import com.nv.youNeverWait.analatic.bl.Inference;
import com.nv.youNeverWait.analatic.bl.Measure;

public class SummaryReport implements ReportData{
	
	private Analatic analatic;

	@Override
	public List<Inference<? extends Measure>> getDataBeans(String fMonth,
			String fYear, String toMonth, String toYear, Integer hospital) {
		
		return analatic.getAggregatedInferencesPerHospital(fMonth, fYear, toMonth, toYear,hospital);
	}

	@Override
	public List<Inference<? extends Measure>> getDataBeans(String fMonth,
			String fYear, String toMonth, String toYear) {
	
		return analatic.getAggregatedInferences(fMonth, fYear, toMonth, toYear);
	}

	public Analatic getAnalatic() {
		return analatic;
	}

	public void setAnalatic(Analatic analatic) {
		this.analatic = analatic;
	}

	
	

}