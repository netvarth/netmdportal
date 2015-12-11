package com.nv.youNeverWait.common;

import javax.xml.bind.JAXBContext;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;

public class NvJaxb2Marshaller extends Jaxb2Marshaller {
	
	
public JAXBContext getJaxBContext(){
 return   super.getJaxbContext();
}

}
