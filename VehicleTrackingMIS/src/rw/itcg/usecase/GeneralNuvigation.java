package rw.itcg.usecase;

import javax.annotation.ManagedBean;


/**
 *@author NIYOMWUNGERI
 *8:05:50 AM, Dec 27, 2016	
 */
@ManagedBean
public class GeneralNuvigation {
	
	public String goToIndex(){
			return "index";
	}
	public String goToLogin(){
		return "login";
	}
}
