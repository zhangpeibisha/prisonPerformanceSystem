/*
 * Copyright 2008-2010 erong software, Inc. All rights reserved.
 * SHENZHEN ERONG SOFTWARE CO.,LTD. WWW.ERONGSOFT.COM
 */

package org.nix.utils.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author joshuaxu
 * 
 */
public class SessionUtil {

	final public static String USER_LOGINFO = "userLoginfo";
	final public static String STAFF_BRANCH = "staffBranch";
	final public static String STAFF_BRANCH_REGION = "staffBranch";
	final public static String O2O_USER_LOGINFO = "o2oUserLoginfo";
	final public static String O2O_ADMIN_LOGINFO = "o2oAdminLoginfo";
	final public static String SESSION_LIST_INFO = "sessionListInfo";
	final public static String SESSION_LOGINTYPE = "sessionLoginType";
	final public static String DEFAULT_RESUME_ID = "defaultResumeId";

	/**
	 * check if user is timeout
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isTimeout(final HttpServletRequest request) {
		if (getSession(request, USER_LOGINFO) == null) {
			return true;
		} else {
			return false;
		}
	}

	public static void setAttribute(final HttpServletRequest request, final String key,
			final Object obj) {
		request.getSession().setAttribute(key, obj);
	}

	public static Object getAttribute(final HttpServletRequest request, final String key) {
		return request.getSession().getAttribute(key);
	}

	public static void removeAttribute(final HttpServletRequest request, final String key) {
		request.getSession().removeAttribute(key);
	}

	public static void setSession(final HttpServletRequest request, final String key,
			final Object obj) {
		request.getSession().setAttribute(key, obj);
	}

	/*
	 * check if obj exists in session
	 */
	public static boolean isExistSession(final HttpServletRequest request, final String key) {
		if (request.getSession().getAttribute(key) != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * get object from session
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static Object getSession(final HttpServletRequest request, final String key) {
		return request.getSession().getAttribute(key);
	}

	/**
	 * invalidate session
	 * 
	 * @param request
	 */
	public static void removeSession(final HttpServletRequest request)
	{
		final HttpSession session = request.getSession();
		if (session != null) {
			session.removeAttribute(USER_LOGINFO);
			session.removeAttribute(SESSION_LIST_INFO);
			session.removeAttribute(SESSION_LOGINTYPE);
			session.invalidate();
		}
	}

}
