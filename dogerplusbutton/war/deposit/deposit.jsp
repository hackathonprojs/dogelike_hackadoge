<%@page import="java.util.logging.Logger"%>
<%@page import="org.json.JSONObject"%>
<%@page import="dogerplusbutton.common.Constants"%>
<%@page import="dogerplusbutton.blockio.BlockioClientAppengine"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Logger log = Logger.getLogger("deposit.jsp");

// generate new address.  associate that address with this user.  

String uid = request.getParameter("uid");

String address = "";
/** this is the user_id on block.io system.  not the same as uid.  our uid is used as label */
long blockioUserId = -1;

if (uid != null && !"".equals(uid)) {
	// get user by uid (using uid as label)
	// if not found, create new user.
	
	BlockioClientAppengine blockio = new BlockioClientAppengine(Constants.blockioApiKeyDoge);
	JSONObject getAddressResult = blockio.getAddressByLabel(uid);
	if ("success".equals(getAddressResult.getString("status"))) {
		JSONObject data = getAddressResult.getJSONObject("data");
		address = data.getString("address");
		blockioUserId = data.getLong("user_id");
	} else {
		// create user
		JSONObject createUserResult = blockio.createUser(uid);
		log.fine("create user result = " + createUserResult.toString(2));
		if ("success".equals(createUserResult.getString("status"))) {
			JSONObject data = createUserResult.getJSONObject("data");
			address = data.getString("address");
			blockioUserId = data.getLong("user_id");
		}
	}
}

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Deposit</title>
</head>
<body>

<h1>Deposit Dogecoin</h1>
<p>To fund your account, please deposit dogecoin to address</p> <h3><strong><a href="dogecoin:<%=address%>&amount=10000"><%=address%></a></strong></h3>

<p>Or you can scan the following QR code.  </p>

<div id="qrcode"></div>
<script type="text/javascript" src="qrcode.js"></script>
<script type="text/javascript">
new QRCode(document.getElementById("qrcode"), "dogecoin:<%=address%>");
</script>

<p>Very Wow.  Much thanks.  Let's go towards a happy place where everyone can tip each other for their website content.  </p>

</body>
</html>