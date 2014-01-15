/**
 * PDFReportView.java
 * January 13,2013
 */
package com.nv.youNeverWait.report;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRQuery;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.web.servlet.View;

/**
 * @author Luciya
 * 
 */
public class PDFReportView implements View {

	@PersistenceContext()
	private static final String CONTENT_TYPE = "application/pdf";

	@Override
	public String getContentType() {
		return this.CONTENT_TYPE;
	}

	/**
	 * for loading report in pdf format
	 */
	@Override
	public void render(Map model, HttpServletRequest request,
			HttpServletResponse response) throws JRException, SQLException,
			IOException {
		response.addHeader("Content-Disposition", "inline;");
		Connection conn = (Connection) model.get("REPORT_CONNECTION");
		String JRXmlFilePath = (String) model.get("JRXML_URL");

		JasperDesign design = JRXmlLoader.load(JRXmlFilePath);
		// String maskingList = (String) model.get("maskingList");
		// if(maskingList!=null && !maskingList.equals("")){
		// String[] maskingField=maskingList.split(",");
		// for(int i=0;i<maskingField.length;i++){
		// String[] fieldName=maskingField[i].split(":");
		// if(fieldName[1].equals("false")){
		// model.put(fieldName[0], false);
		// }
		// else{
		// model.put(fieldName[0], true);
		// }
		// }
		// }
		// changes
		String subQuery = "";
		String finalQuery = "";
		String queryString = "";
		JRQuery query = design.getQuery();
		queryString = query.getText();
		/* getting param list */
		String parameterList = (String) model.get("paramList");
		if (parameterList != null && !parameterList.equals("")) {
			String[] param = parameterList.split(":");
			for (int i = 0; i < param.length; i++) {
				subQuery = subQuery + param[i];
			}
		}
		String filterQury = "";
		String[] querySplit = queryString.split("filter");
		if (subQuery.equals("")) {
			if (filterQury.equals("")) {
				finalQuery = querySplit[0] + querySplit[1];
			}
			else{
				finalQuery = querySplit[0] + filterQury+querySplit[1];
			}
		} else
			if (filterQury.equals("")) {
			finalQuery = querySplit[0] + subQuery + querySplit[1];
			}
			else{
				finalQuery = querySplit[0] +filterQury+subQuery + querySplit[1];
			}
		System.out.println("finalQuery-->" + finalQuery);
		JRDesignQuery newQuery = new JRDesignQuery();
		newQuery.setText(finalQuery);
		design.setQuery(newQuery);
		System.out.println("aaaaaa" + design.getQuery().getText());
		//

		JasperReport jReport = JasperCompileManager.compileReport(design);
		JasperPrint jPrint = JasperFillManager.fillReport(jReport, model, conn);

		OutputStream out = response.getOutputStream();
		// JasperExportManager.exportReportToPdfFile(jPrint, "C://report.pdf");
		JasperExportManager.exportReportToPdfStream(jPrint, out);
		out.flush();
		out.close();

		if (conn != null)
			conn.close();
	}

}
