<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <style type="text/css">
		${specCSS}
	</style>
    <style type="text/css">
        .menulink {
            color: black;
            text-decoration: none;
        }
        .menulink:hover {
            color: #715c1f;
        }
        #ssm2 A {
            color: black;
            text-decoration: none;
            font-size: 12;
            font-family: verdana;
        }
        #ssm2 A:hover {
            color: red;
        }
    </style>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script type="text/javascript">
    	${specScript}
    </script>
</head>
<body style="margin:0px" scroll="auto" oncontextmenu="self.event.returnValue=false">
<table cellpadding="0" cellspacing="0" border="0" width="100%" height="100%">
<!--页眉-->
<tr>
<td>
<table width="100%" border="0" cellpadding="0" cellspacing="0" id="tdHEAD"
       style="display:block">
    <tr>
        <td height="58" background="images/_system/portal/cclcm_banner.jpg">
            <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                	<td align="left">&nbsp;</td>
                    <td id="tdSmallButton" width="128" height="100%" align="right" valign="top">
                        <table width="70%" height="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                            	<!-- <td><a title="锁定当前会话" href="javascript:holdSession('${user.user_iidd}');">
                                    <img alt="锁定当前会话" border="0" name="Image6"
                                         src="images/_system/portal/lock.gif"
                                         onMouseOut="imgOut()"
                                         onMouseOver="imgOver()"></a>
                                </td> -->
                                <!-- <td><a title="返回上一页" href="javascript:history.go(-1);">
                                    <img alt="返回上一页" border="0" name="Image6"
                                         src="images/_system/portal/s-button-back.gif"
                                         onMouseOut="imgOut()"
                                         onMouseOver="imgOver()"></a>
                                </td>
                                 -->
                                <td><a title="刷新当前页"
                                       href="javascript:window.location=window.location;"
                                       target="mainFrame"><img alt="刷新当前页" border="0" name="Image5"
                                                          src="images/_system/portal/s-button-refresh.gif"
                                                          onMouseOut="imgOut()" onMouseOver="imgOver()"></a>
                                </td>
                                <td><a title="注销当前登陆" href="logout.action"
                                       target="_top"><img alt="注销当前登陆" border="0" name="Image4"
                                                          src="images/_system/portal/s-button-close.gif"
                                                          onMouseOut="imgOut()" onMouseOver="imgOver()"></a>
                                </td>
                            </tr>
                            <!--这里可以显示一些信息-->
                            <tr><td colspan="3" height="100%">&nbsp;</td></tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<!--显示菜单按钮-->
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr height="23">
        <td><img alt="" src="images/_system/portal/title-menu-handle.gif"></td>
        <c:forEach items="${menus}" var="menu" varStatus="status">
	        <td height="23" nowrap="true" background="images/_system/portal/title-menu-background.gif" width="75">
	            <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	                <tr><td nowrap="true" style="padding-top:4px; font-size:9pt;" align="center">
	                    <a class="menulink" href="<c:url value="?menuId=${status.index}"/>" <c:if
	                            test="${not empty menu.onclick}">onclick="<c:out value='${menu.onclick}'/>"</c:if> >
	                        <c:if test="${status.index == menuId}">★</c:if><c:out value="${menu.caption}"/></a> |
	                </td></tr>
	            </table>
	        </td>
   		</c:forEach>
   		<c:if test="${user.isSysAdmin == 'Y'}">
	   		<td nowrap="true"
	        	style="font-size:9pt; padding-top:5px;padding-left:250px;"     
	            background="images/_system/portal/title-menu-background.gif">
	   			<marquee direction="left" width="400"><font color="red">${alarmText}</font></marquee>
			</td>   
   		</c:if>
   		<td width="100%" align="right"
        	style="font-size:9pt; padding-top:5px; padding-right:10px"     
            background="images/_system/portal/title-menu-background.gif">
   				欢迎您,${user.user_name}
		</td>   
</tr>
</table>
</td></tr>
<!--*页眉控制*-->
<tr align="center">
    <td height="7" background="images/_system/portal/switch-title-bg.gif">
    	<img src="images/_system/portal/switch-title-open.gif" onClick="titleSwitchClicked()"
    		onMouseOver="imgOver()" onMouseOut="imgOut()"/></td>
</tr>
<!--页面主体-->
<tr><td height="100%">
    <table cellpadding="0" cellspacing="0" border="0" width="100%" height="100%"><tr>
        <!--**左侧导航-->
        <td id="tdPANEL" width="150" style="display:block" valign="top" bgcolor="#BBBBBB" nowrap="nowrap">
        ${menuPanel}
        </td>
        <!--**左侧导航控制*-->
        <td width="7" align="center" background="images/_system/portal/switch-panel-bg.gif">
            <img alt="" name="leftseparator" 
                 src="images/_system/portal/switch-panel-open.gif"
                 onClick="panelSwitchClicked();" onMouseOver="imgOver();" onMouseOut="imgOut();"/>
        </td>
        <!--**主页面框架-->
        <td>
            <iframe src="about:blank" id="mainFrame" name="mainFrame" height="100%" width="100%" frameborder="0" 
                    style="z-index:2;position:relative;"></iframe>
        </td>
    </tr></table>
</td></tr>
<!--页脚-->
<tr>
    <td height="26" style="padding-top:5px; border-top:2px outset #FFFFFF;font-size:9pt;" align="center"
        background="images/_system/portal/title-menu-background.gif">
        			北京京航计算通讯研究所
    </td>
</tr>
</table>
<script language="JavaScript" type="text/javascript">
    	$(document).ready(function(){
			myWindowLoad();
		});
		function myWindowResize() {
			cspbRefresh();
		}
		function myWindowLoad() {
		    switchSmallButton();
		    /*if("${menuId}" == "0"){
			    mainFrame.location = "${ctx}/client/viewpendingwork.action";
		    }else{
		    	mainFrame.location = "${ctx}/html/introduce.jsp";
		    }*/
		    var goUrl = $(".cspbItm").first().attr("href");
		    mainFrame.location = "${ctx}/"+goUrl;
		    $(".cspbItm").first().css({ color:"#007bbb", fontWeight:"bold" });
		    //saveCookie();
		    cspbShowSection(0);
		}
		var menuId = 8;
		var pState = "open";
		var tState = "open";
		var gHis = new Array (0,0,0,0,0,0,0,0,0);
		var iHis = new Array (0,0,0,0,0,0,0,0,0);
		function holdSession(user_iidd) {
			var url = "${ctx}/user/holdsession.action?user_iidd="+user_iidd;
			var ret = window.showModalDialog(url, '', 'dialogHeight:200px;dialogWidth:500px;center:yes;status:no;help:no;scroll:no;unadorned:no;resizable:no');
			if(ret != undefined && ret=='unlock'){
				
			}else{
				holdSession(user_iidd);
			}
		}
		var loginPrompt = '${loginPrompt}';
		if(loginPrompt != ''){
			alert(loginPrompt);
		}
		//-->;
</script>
<script language="javascript" type="text/javascript">
// 操作cookie
function saveCookie() {
    return false;
    var c = pState + ":" + tState + ":";
    for (var i = 0; i < gHis.length; i++) {
        if (i > 0) c += ".";
        c += gHis[i];
    }
    c += ":";
    for (var i = 0; i < iHis.length; i++) {
        if (i > 0) c += ".";
        c += iHis[i];
    }
    c += ":" + menuId;
    //7天的有效期
    //var expireDate = new Date();
    //expireDate.setDate(expireDate.getDate() + 7);
    //document.cookie = "COOKIE_MENU" + "=" + c + "; path=/hdsec/; expires=" + expireDate.toGMTString() + ";"
}

//动态改变图片
function imgOver() {
    var obj = event.srcElement;
    if (obj.src.lastIndexOf("-over") < 0) obj.src = obj.src.substring(0, obj.src.lastIndexOf(".")) + "-over.gif";
}
function imgOut() {
    var obj = event.srcElement;
    if (obj.src.lastIndexOf("-over") > 0) obj.src = obj.src.substring(0, obj.src.lastIndexOf("-")) + ".gif";
}

//缩放隐藏
function switchBar(objId) {
    var obj = document.getElementById(objId);

    if (!obj) alert("对不起，页面出现了异常");
    if (obj.style.display == "none") {
        obj.style.display = "block";
    } else {
        obj.style.display = "none";
    }
    myWindowResize();
}

//点击上方的缩放按钮
function titleSwitchClicked() {
    tStateOld = document.getElementById("tdHEAD").style.display == "none" ? "close" : "open";
    switchBar("tdHEAD");
    tState = document.getElementById("tdHEAD").style.display == "none" ? "close" : "open";
    event.srcElement.src = event.srcElement.src.replace(tStateOld, tState);
    switchSmallButton();
    //saveCookie();
}

//点击左方的缩放按钮
function panelSwitchClicked() {
    pStateOld = document.getElementById("tdPANEL").style.display == "none" ? "close" : "open";
    switchBar("tdPANEL");
    pState = document.getElementById("tdPANEL").style.display == "none" ? "close" : "open";
    event.srcElement.src = event.srcElement.src.replace(pStateOld, pState);
    //saveCookie();
}

//点击Panel栏目组标题
function panelGroupClicked(id) {
    gHis[menuId] = id;
    //saveCookie();
}

//点击Panel项目
function panelItemClicked(id) {
    iHis[menuId] = id;
    try {
        /*var target = event.srcElement.target;
        if (event.srcElement.tagName && event.srcElement.tagName != "A")
            target = event.srcElement.parentElement.target;
        if (!target || target == "mainFrame") {
            setTimeout(refreshTitle, 300);
            saveCookie();
        }*/
        $(".cspbItm:visible").css({color:"black",fontWeight:"normal"});
		$(".cspbItm:visible:eq("+id+")").css({ color:"#007bbb", fontWeight:"bold" });
    } catch (e) {
    }
}

//点击刷新按钮
function refreshClicked() {
    document.location = "?menuid=8&event=refresh";
}

//控制小按钮图标跟随tdHEAD的状态
function switchSmallButton() {
    //if(document.getElementById("tdHEAD").style.display=="none") {
    //    document.getElementById("tdSmallButton").style.display="block";
    //} else document.getElementById("tdSmallButton").style.display="none";
}
</script>
</body>
</html>