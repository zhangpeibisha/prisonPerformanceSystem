/*
 * Copyright erong software, Inc. All rights reserved.
 * SHENZHEN ERONG SOFTWARE CO.,LTD. WWW.ERONGSOFT.COM
 */

package org.nix.utils.web;

import java.sql.Timestamp;
import java.util.Map;

/**
 * SessionListInfo . @author joshuaxu 
 */

public class SessionListInfo {
	private String pageName;
	private Integer pageNo;
	private Map<String, Object> listConditions;
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Map<String, Object> getListConditions() {
		return listConditions;
	}
	public void setListConditions(Map<String, Object> listConditions) {
		this.listConditions = listConditions;
	}
}
