<%@ page language="java" contentType="text/css;charset=UTF-8" %>
.MyCheckBox {
	behavior:url(<%=request.getContextPath()%>/_css/my-checkbox.htc);
}
.MyRadio {
	behavior:url(<%=request.getContextPath()%>/_css/my-radio.htc);
}
.MyButton {
	<%--behavior:url(<%=request.getContextPath()%>/_css/my-button.htc);--%>
    background-color:#F1F1F1;
    margin-bottom:2px;
    height:22px;
    font-size:9pt;
	color:#5A6CA2;
    padding-top:2px;
    padding-bottom:0px;
    border-top: 1pt solid #FFFFFF;
    border-left:1pt solid #FFFFFF;
    border-right: 1pt solid #5A6DA5;
	border-bottom:1pt solid #5A6DA5;
}
.MySelect {
	color:#009900;
	behavior:url(<%=request.getContextPath()%>/_css/my-select.htc);
}
.MyTextArea {
	border:0;
	color:#009900;
	behavior:url(<%=request.getContextPath()%>/_css/my-textarea.htc);
	scrollbar-darkshadow-color:#CCCFFF;
}
.MyInput {
	<%--behavior:url(<%=request.getContextPath()%>/_css/my-input.htc);--%>
}

.MyInputDate {
	behavior:url(<%=request.getContextPath()%>/_css/my-input-date.htc);
}

.MyInputTime {
	behavior:url(<%=request.getContextPath()%>/_css/my-input-time.htc);
}

.MyFormTable {
	behavior:url(<%=request.getContextPath()%>/_css/my-form-table.htc);
	background-color:#DDDDDD;
	border:1px solid #AAAAAA;
}

.MyFormTable TD {
	background-color:#AFD5FA;
	border-width:1;
	font-size:9pt;
}

.MyFormTable TD.Title {
	height:24;
	text-align:right;
	font-size:9pt;
}

.MyFormTable TD.TitleRequired {
	height:24;
	text-align:right;
	color:red;
	font-size:9pt;
}

.TxtSel{
   font-size:9pt;
   color:blue;
}
.TxtNoSel{
   font-size:9pt;
   color:black;
}

.CheckBoxTable{
	display:inline;
	cursor: hand;
	height:16;
}
.CheckBoxImg{
}
.CheckBoxDisable{
    font-size:9pt;
	color:#000000;
	cursor:default;
}

.RadioTable{
	display:inline;
	cursor: hand;
	height:16;
}
.RadioImg{
}
.RadioDisable{
    font-size:9pt;
	color:#000000;
	cursor:default;
}




.ButtonTable{
	display:inline;
	cursor: hand;
	height:24;
}

.ButtonTable_td {
	border-top:1pt solid #8596CA!important;
	border-bottom:1pt solid #5A6DA5!important;
	padding-top:2;
	background-image: url('../images/my-button-background.gif');
	background-repeat: repeat-x;
	cursor:hand;
	color:#5A6CA2;
}
.ButtonTable TD {
    padding:0px !important;
	background-color: transparent!important;
    border:0px;
}

.ButtonValue{
	font-size:9pt;
	color:#5A6CA2;
}

.ButtonDisable{
	filter:dropshadow(color=#FFFFFF,offx=1,offy=1);color:#A0B0C9;cursor:default;
}


BODY
{
    background: white;
    SCROLLBAR-FACE-COLOR: #edeff8;
   	SCROLLBAR-HIGHLIGHT-COLOR: #ffffff;
   	SCROLLBAR-SHADOW-COLOR: #adb6de;
   	SCROLLBAR-3DLIGHT-COLOR: #adb6de;
   	SCROLLBAR-ARROW-COLOR: #adb6de;
   	SCROLLBAR-TRACK-COLOR: #ffffff;
   	SCROLLBAR-DARKSHADOW-COLOR: #ffffff;
   	SCROLLBAR-BASE-COLOR: rgb(66,93,128);
 	MARGIN: 5px
}
.titleFont{
		    FONT-SIZE: 18pt;
		    COLOR: #000099;
		    TEXT-ALIGN: center;
			font-weight:normal;
			font-family:华文新魏
}
.listTable{
    background-color:#eeeeee;

}

.listTR
{
  FONT-SIZE: 9pt;
  FONT-STYLE: normal;
  BACKGROUND-COLOR: #B8D3F5;
  TEXT-ALIGN: center;
  BORDER-RIGHT: buttonshadow 1px solid;
  PADDING-RIGHT: 5px;
  BORDER-TOP: buttonhighlight 1px solid;
  PADDING-LEFT: 5px;
  BORDER-LEFT: buttonhighlight 1px solid;
  BORDER-BOTTOM: buttonshadow 1px solid;
	cursor:default;
	border-color:white;
}
.normalTR
{
    BACKGROUND-COLOR: #E7EEF7;
    COLOR: black;
    font-size: 9pt;
	border-color:white;
}
.newButton {
    BORDER-RIGHT: 1px solid;
    BORDER-TOP: 1px solid;
    BORDER-LEFT: 1px solid;
    BORDER-BOTTOM: 1px solid;
    BACKGROUND-COLOR: #DDE1F0;
	border-color: silver silver silver silver;
	color:black;
	cursor:hand;
}
.purpleTd
{
    FONT-SIZE: 9pt;
    FONT-STYLE: normal;
    BACKGROUND-COLOR: #DDE1F0;
    TEXT-ALIGN: center;
}


.panel {
    padding:4px 6px 2px 6px;
    border:2px outset #FFFFFF;
    BACKGROUND-COLOR: #edf2f7
}

.panel FIELDSET {
    padding:6px;
    padding-top:0px;
    width:100%
}

FORM {
    margin:0px;
}