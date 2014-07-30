<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Setup</title>

<script src="jquery-1.11.1.min.js"></script>

<script type="text/javascript">
function onBtnSaveClick() {
	window.open('./deposit.jsp?uid=sol','_self','resizable=yes')
}
</script>

</head>
<body style="background-color: rgba(0, 255, 0, 1); ">
<font face="Comic Sans MS">

<div style="background-color: white; width: 100%; position: relative; top: -10px; left: -10px;">
	<img src="titlebar.jpeg"></img>
</div>

<div style="text-align: center;">
	<img src="dogecoin.png" style="width: 150px; height: 150px; padding: 30px;"></img>
	<h1 style="font-size: 2em;">Everytime I click on <img src="dogerbutton3.png" style="height: 90px; position: relative; top: 30px;"></img>, I want to give <input id="amount" type="number" style="background-color: rgba(255, 255, 255, 0.3); font-size: 1.3em; width: 100px"></input> Dogecoin</h1>
	<div style="height: 50px;"></div>
	<button onclick="onBtnSaveClick()" style="border-style: solid; border-color: grey; border-width: 3px; width: 200px; height: 70px; font-size: 2em; round-corner: 15px; -moz-border-radius: 15px; -webkit-border-radius: 15px;" ><div >Save</div></button>
</div>

</font>

</body>
</html>