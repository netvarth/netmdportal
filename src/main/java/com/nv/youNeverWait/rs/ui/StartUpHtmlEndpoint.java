/**
 * StartUpHtmlEndpoint.java
 * 
 * @Author Asha Chandran
 *
 * Dec 1, 2012
 */
package com.nv.youNeverWait.rs.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller  
@RequestMapping("ui/html/")
public class StartUpHtmlEndpoint {


	@RequestMapping(value ="startUp",method=RequestMethod.GET)
	public String check(){
		return "userLoginPage";
	}

}