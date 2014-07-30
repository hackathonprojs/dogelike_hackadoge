<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.logging.*" %>
<%@ page import="dogerplusbutton.persistence.*" %>

<%
	// no cache
    response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>

<% 
// cors
response.setHeader("Access-Control-Allow-Origin", "*");
%>

<%
long total = -1L;

Logger log = Logger.getLogger("total.jsp");

// get parameters
String uid = request.getParameter("uid");
String domain = request.getParameter("domain");

if (uid != null) { uid = uid.trim(); }
if (domain != null) { domain = domain.trim(); }

log.finer("total.jsp called. uid="+uid+".  domain="+domain);

if (uid != null && !"".equals(uid) 
	&& domain != null && !"".equals(domain)) {
	total = ClickCountPersistence.totalByUidDomain(uid, domain);
}


%>

<%=total%>