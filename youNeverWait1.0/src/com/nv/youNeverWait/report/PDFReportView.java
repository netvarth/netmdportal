/**
 * PDFReportView.java
 * January 13,2013
 */
package com.nv.youNeverWait.report;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
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

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.NetmdBranchTbl;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;

/**
 * @author Luciya
 * 
 */
public class PDFReportView  implements View  {

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
		Connection conn = null;
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
		String yearPortion="";
		String mnthPortion="";
		String orPortion ="or";
		String andPortion="and";
		String filterQury = "";
		JRQuery query = design.getQuery();
		queryString = query.getText();

		String startMnth=(String)model.get("startMonth");
		String endMnth=(String)model.get("endMonth");
		String startYear=(String)model.get("startYear");
		String endYear=(String)model.get("endYear");
		int endMonth=Integer.parseInt(endMnth);
		int startMonth=Integer.parseInt(startMnth);
		int strYr=Integer.parseInt(startYear);
		int endYr=Integer.parseInt(endYear);
		/*generating where condition*/
		Map<Integer, String> mnthMap = new HashMap<Integer, String>();
		/*Putting values to map*/
		mnthMap.put(1,"january");
		mnthMap.put(2,"february");
		mnthMap.put(3,"march");
		mnthMap.put(4,"april");
		mnthMap.put(5,"may");
		mnthMap.put(6,"june");
		mnthMap.put(7,"july");
		mnthMap.put(8,"august");
		mnthMap.put(9,"september");
		mnthMap.put(10,"october");
		mnthMap.put(11,"november");
		mnthMap.put(12,"december");
		
		if(strYr==endYr){
			/*Checking whether start month and end month are equal less or greater*/
			if(startMonth==endMonth){
				mnthPortion="'"+mnthMap.get(startMonth)+"'";
				mnthPortion="(mnth = "+mnthPortion;
				mnthPortion=mnthPortion+")";
				System.out.println("month when year are same"+mnthPortion);
			}
			if(startMonth>endMonth){
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidStartMonth);
				se.setDisplayErrMsg(true);
				throw se;
			}
			if(startMonth<endMonth){
				mnthPortion="(mnth = ";
				for(int strtmnth=startMonth;strtmnth<=endMonth;strtmnth++){
					mnthPortion=mnthPortion+"'"+mnthMap.get(strtmnth)+"'";
					
					if(strtmnth!=endMonth){
						mnthPortion=mnthPortion+" "+orPortion+" ";
						mnthPortion=mnthPortion+"mnth = ";
					}
				}
				mnthPortion=mnthPortion+")";
				System.out.println("mnth...."+mnthPortion);
				
			}
			//andPortion="and";			
			yearPortion=andPortion+" "+"(yer = "+"'"+startYear+"'";
			yearPortion=yearPortion+")";
			filterQury=mnthPortion+yearPortion;
			System.out.println("final wher query"+filterQury);
		}
		
		
		if(strYr>endYr){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidStartYear);
			se.setDisplayErrMsg(true);
			throw se;
			
		}
		if(strYr<endYr){
			/*Checking whether start month and end month are equal less or greater*/
			if(startMonth<=endMonth ){
				mnthPortion="(mnth = ";
				for(int i=1;i<=12;i++){
				mnthPortion=mnthPortion+"'"+mnthMap.get(i)+"'";
				if(i!=12){
				mnthPortion=mnthPortion+" "+orPortion+" ";
				mnthPortion=mnthPortion+"mnth = ";
				}				
				}
				mnthPortion=mnthPortion+")";
				System.out.println("mnth...."+mnthPortion);
			}
			if(startMonth>endMonth){
				mnthPortion="(mnth = ";
				for(int j=startMonth;j<=12;j++){
					mnthPortion=mnthPortion+"'"+mnthMap.get(j)+"'";
					mnthPortion=mnthPortion+" "+orPortion;
					mnthPortion=mnthPortion+" "+"mnth = ";
					if(j==12){
						for(int k=1;k<=endMonth;k++){
							mnthPortion=mnthPortion+"'"+mnthMap.get(k)+"'";
							if(k!=endMonth){
								mnthPortion=mnthPortion+" "+orPortion;
								mnthPortion=mnthPortion+" "+"mnth = ";
							}
						}
					}
					
				}
				mnthPortion=mnthPortion+")";
				System.out.println("mnth...."+mnthPortion);
			}
		
			
			mnthPortion=mnthPortion+andPortion;
			yearPortion="(yer = ";
			for(int yr=strYr;yr<=endYr;yr++){
				String newYear=Integer.toString(yr);
				yearPortion=yearPortion+"'"+newYear+"'";
				if(yr!=endYr){
					yearPortion=yearPortion+" "+orPortion+" ";
					yearPortion=yearPortion+"yer = ";
				}
			}
			yearPortion=yearPortion+")";
	
			filterQury=mnthPortion+yearPortion;
			System.out.println("final wher query"+filterQury);
		}
		
		/*Generating sub query*/
		String netmdBranch=(String)model.get("paramList");
		if(netmdBranch!=null && !netmdBranch.isEmpty()){
			int netmdBranchId=Integer.parseInt(netmdBranch);		
			subQuery="and netmdBranchId = "+netmdBranchId;
		}
		String[] querySplit = queryString.split("filter");
		
		if (subQuery.equals("")) {
			if (filterQury.equals("")) {
				finalQuery = querySplit[0]  + " "+ querySplit[1];
			}
			else{
				finalQuery = querySplit[0] + " "+filterQury+" "+querySplit[1];
			}
		} else
			if (filterQury.equals("")) {
			finalQuery = querySplit[0]  + " "+ subQuery  + " "+querySplit[1];
			}
			else{
				finalQuery = querySplit[0] + " "+filterQury+subQuery + " "+querySplit[1];
			}
		System.out.println("finalQuery-->" + finalQuery);
		JRDesignQuery newQuery = new JRDesignQuery();
		newQuery.setText(finalQuery);
		design.setQuery(newQuery);
		System.out.println("aaaaaa" + design.getQuery().getText());
		//

		conn=(Connection) model.get("REPORT_CONNECTION");
		try{
		JasperReport jReport = JasperCompileManager.compileReport(design);
		JasperPrint jPrint = JasperFillManager.fillReport(jReport, model, conn);

		OutputStream out = response.getOutputStream();
		// JasperExportManager.exportReportToPdfFile(jPrint, "C://report.pdf");
		JasperExportManager.exportReportToPdfStream(jPrint, out);
		out.flush();
		out.close();
		}catch(Exception e){
			e.printStackTrace();
			
		} finally{
		if (conn != null)
			conn.close();
		}
	}



}
