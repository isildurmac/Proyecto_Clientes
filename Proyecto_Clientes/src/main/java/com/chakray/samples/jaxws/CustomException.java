package com.chakray.samples.jaxws;

import javax.xml.ws.WebFault;

@WebFault(name="CustomExceptionFaultBean")
public class CustomException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8739600426954514104L;
	
	private CustomExceptionFaultBean faultBean;
 
    public CustomException(String message, CustomExceptionFaultBean faultInfo){
        super(message);
        faultBean = faultInfo;
    }
 
    public CustomException(String message, CustomExceptionFaultBean faultInfo, Throwable cause) {
        super(message, cause);
        faultBean = faultInfo;
    }
 
    public CustomExceptionFaultBean getFaultInfo(){
        return faultBean;
    }
}
