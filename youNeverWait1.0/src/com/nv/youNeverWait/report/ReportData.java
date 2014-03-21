package com.nv.youNeverWait.report;

import java.util.List;

import com.nv.youNeverWait.analatic.bl.Inference;
import com.nv.youNeverWait.analatic.bl.Measure;

public interface ReportData {
	
	public List<Inference<? extends Measure>> getDataBeans(String fMonth, String fYear, String toMonth, String toYear,Integer hospital);

	public List<Inference<? extends Measure>> getDataBeans(String fMonth,String fYear, String toMonth,String toYear);

}
