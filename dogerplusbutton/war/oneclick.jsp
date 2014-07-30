<%@page import="dogerplusbutton.blockio.BlockioClientAppengine"%>
<%@page import="dogerplusbutton.user.DomainPreference"%>
<%@page import="dogerplusbutton.user.UserPreference"%>
<%@page import="dogerplusbutton.common.Constants"%>
<%@page import="dogerplusbutton.blockio.BlockioClientAppengine"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.logging.*" %>
<%@ page import="dogerplusbutton.persistence.*" %>
<%@ page import="com.google.appengine.api.datastore.*" %>

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

<%-- this is used when the user click on the like button, it will call this api point. --%>

<%
long count = -1L;

Logger log = Logger.getLogger("oneclick.jsp");

System.out.println("remember to change api key and pin in constants.java");

// get parameters
String uid = request.getParameter("uid");
String domain = request.getParameter("domain");

if (uid != null) { uid = uid.trim(); }
if (domain != null) { domain = domain.trim(); }

log.finer("oneclick.jsp called. uid="+uid+".  domain="+domain);

if (uid != null && !"".equals(uid) 
	&& domain != null && !"".equals(domain)) {
	// write data into the system.
	
	Entity result = ClickCountPersistence.incrementCount(uid, domain, 1);
	count = (Long)result.getProperty("count");
	
	// send money to his account.
	BlockioClientAppengine blockio = new BlockioClientAppengine(Constants.blockioApiKeyDoge);
	DomainPreference domainPreference = DomainPreference.getDomainPreference(domain);
	double amount = UserPreference.DEFAULT_DOGE_TIP_AMOUNT;
	String payoutAddress = domainPreference.getPayoutAddress();
	blockio.withdraw(amount, payoutAddress, Constants.blockioPin);

	log.info("sending " + amount + " doge to " + payoutAddress);
}


%>

<%=count%>