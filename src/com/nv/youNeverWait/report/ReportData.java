package com.nv.youNeverWait.report;

import java.util.List;

import com.nv.youNeverWait.analatic.bl.Inference;


public interface ReportData {
	
	public List<Inference> getDataBeans(String fMonth, String fYear, String toMonth, String toYear,Integer hospital);

	public List<Inference> getDataBeans(String fMonth,String fYear, String toMonth,String toYear);

}
