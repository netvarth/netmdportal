package com.nv.youNeverWait.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class BeanMaker {
	 List<DataBean> beanCollection = new ArrayList<DataBean>();
	

	public static Collection<DataBean> getBeanCollection() {
		List<DataBean> beanCollection = new ArrayList<DataBean>();
		DataBean dataBean = new DataBean();
		dataBean.setName("Age");
		
		dataBean.setValueSet(new KeyValue("18-25",19,"Jan","2014","Mother Hospital","Age"));
		dataBean.setValueSet(new KeyValue("25-30",20,"Jan","2014","Mother Hospital","Age"));
		dataBean.setValueSet(new KeyValue("18-25",20,"Feb","2014","Mother Hospital","Age"));
		dataBean.setValueSet(new KeyValue("25-30",20,"Feb","2014","Mother Hospital","Age"));
		beanCollection.add(dataBean);
		dataBean = new DataBean();
		dataBean.setName("Delivery Type");
		
		dataBean.setValueSet(new KeyValue("Indused",19,"Jan","2014","Mother Hospital","Delivery Type"));
		dataBean.setValueSet(new KeyValue("Spontanious",20,"Jan","2014","Mother Hospital","Delivery Type"));
		dataBean.setValueSet(new KeyValue("cesarean Delivery",2,"Jan","2014","Mother Hospital","Delivery Type"));
		beanCollection.add(dataBean);
		return beanCollection; 
	}

	

	
}
