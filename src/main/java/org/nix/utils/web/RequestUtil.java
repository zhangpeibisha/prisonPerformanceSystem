/*
 * Copyright erong software, Inc. All rights reserved.
 * SHENZHEN ERONG SOFTWARE CO.,LTD. WWW.ERONGSOFT.COM
 */

package org.nix.utils.web;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;


/**
 * @author joshuaxu
 *
 */
public class RequestUtil {
	private ServletRequest request;
	public RequestUtil(ServletRequest request) {
		this.request=request;
	}

	public String getParameterString(String name) {
		String parameterValue=(String)request.getParameter(name);
		if (parameterValue==null)
			return "";

		return parameterValue;
	}
	
	public String getAttributeString(String name) {
		String attributeValue=(String)request.getAttribute(name);
		if (attributeValue==null)
			return "";

		return attributeValue;
	}
	
	public String getParameterSelected(String name, String value) {
		String parameterValue=(String)request.getParameter(name);
		if (parameterValue==null)
			return "";
		
		if (parameterValue.equals(value))
			return "selected=\"selected\"";
		
		return "";
	}
	
	public String getWebsite() {
		String path = ((HttpServletRequest)request).getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
		return basePath;
	}
	
	public String getContextPath() {
		String path = ((HttpServletRequest)request).getContextPath();
		return path;
	}
	
	public String getErrorPage() {
		return getWebsite()+"/error.jsp";
	}
	
	
}
