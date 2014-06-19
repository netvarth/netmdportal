package com.nv.youNeverWait.report;

import java.util.List;

import com.nv.youNeverWait.analytic.bl.Analytic;
import com.nv.youNeverWait.analytic.bl.Inference;
import com.nv.youNeverWait.analytic.bl.Measure;

public class SummaryReport implements ReportData{
	
	private Analytic analatic;

	@Override
	public List<Inference> getDataBeans(String fMonth,
			String fYear, String toMonth, String toYear, Integer hospital) {
		
		return analatic.getAggregatedInferencesPerHospital(fMonth, fYear, toMonth, toYear,hospital);
	}

	@Override
	public List<Inference> getDataBeans(String fMonth,
			String fYear, String toMonth, String toYear) {
	
		return analatic.getAggregatedInferences(fMonth, fYear, toMonth, toYear);
	}

	public Analytic getAnalatic() {
		return analatic;
	}

	public void setAnalatic(Analytic analatic) {
		this.analatic = analatic;
	}

	
	

}
