<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="org.json.JSONObject"%>
<%@page import="dogerplusbutton.common.Constants"%>
<%@page import="dogerplusbutton.blockio.BlockioClientAppengine"%>

<%
Logger log = Logger.getLogger("deposit.jsp");

// generate new address.  associate that address with this user.  

String uid = request.getParameter("uid");

String address = "";
/** this is the user_id on block.io system.  not the same as uid.  our uid is used as label */
long blockioUserId = -1;

if (uid == null || "".equals(uid)) {
	// hackathon. default to 
	uid = "sol";
}

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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Deposit</title>

<script src="jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="qrcode.js"></script>
<script type="text/javascript">
function onBtnDepositClick() {
	var amount = $("#amount").val();
	console.log(amount);
	window.open('dogecoin:<%=address%>&amount='+amount,'_self','resizable=yes')
}

function showqrcode() {
	console.log("showqrcode");
	new QRCode(document.getElementById("qrcode"), "dogecoin:<%=address%>");	
}
</script>

</head>
<body style="background-color: rgba(0, 255, 0, 1); ">
<font face="Comic Sans MS">

<div style="background-color: white; width: 100%; position: relative; left: -10px; top: -10px;">
	<img src="titlebar.jpeg" style=""></img>
</div>

<div style="text-align: center;">
	<img src="dogecoin.png" style="width: 150px; height: 150px; padding: 30px;"></img>
	<h1 style="font-size: 2em;">Deposit <input id="amount" type="number" style="background-color: rgba(255, 255, 255, 0.3); font-size: 1.3em; width: 100px"></input> Dogecoin to Doger</h1>
	<h2>Address: <%=address%> <img src="qrcode.jpg" onclick="showqrcode()" style="width: 40px; height: 40px;"></img></h2>
	<button onclick="onBtnDepositClick()" style="border-style: solid; border-color: grey; border-width: 3px; width: 200px; height: 70px; font-size: 2em; round-corner: 15px; -moz-border-radius: 15px; -webkit-border-radius: 15px;" ><div >Deposit</div></button>
	<div style="width: 300px; margin: 0 auto; padding: 30px;" id="qrcode"></div>
</div>

</font>

</body>
</html>