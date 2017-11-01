alert("<%
	java.util.Enumeration enum = request.getHeaderNames();
	while(enum.hasMoreElements()) {
		out.print(enum.nextElement() + "<br>");
	}

	out.print(request.getHeader("REFERER"));
%>");

// 设置cookie, 调用方法为setCookie(name, value, [expires], [path], [domain], [secure]);
// expires是cookie的持久时间,单位秒,默认为60×60×24，一天, 如果传入null, 则不设置
// path默认为"/";
function setCookie(name, value) {
	var argv = setCookie.arguments;
	var argc = setCookie.arguments.length;
	var expires = null;

	if (argc > 2) {
		if (argv[2]!=null) {
			expires = new Date();
			expires.setTime(expires.getTime() + 1000*parseInt(argv[2]));
		}
	} else {
		expires = new Date();
		expires.setTime(expires.getTime() + 1000*60*60*24);
	}

	var path = (argc > 3) ? argv[3] : null;
	var domain = (argc > 4) ? argv[4] : null;
	var secure = (argc > 5) ? argv[5] : null;
	
	var newCookie = escape(name) + "=" + escape(value) +
	((expires == null)	? "" : ("; expires="+ expires.toGMTString())) +
	((path == null)		? "; path=/": ("; path=" + path)) +
	((domain == null)	? "" : ("; domain="	+ domain)) +
	((secure == null)	? "" : ("; secure="	+ secure));

	document.cookie = newCookie;
}

// 删除cookie, 将cookie的超时时间设为当前时间－1, 浏览器自动处理
function delCookie(name) {
	var exp = new Date();
	alert(exp.getTime());
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
	document.cookie = escape(name) + "=" + cval + "; expires=" + exp.toGMTString();
}

// Get cookie by name
function getCookie(name) {
	var arg = escape(name) + "=";
	var nameLen = arg.length;
	var cookieLen = document.cookie.length;
	var i = 0;
   
	while (i < cookieLen) {
		var j = i + nameLen;
		if (document.cookie.substring(i, j) == arg)
			return getCookieValueByIndex(j);
		i = document.cookie.indexOf(" ", i) + 1;
		if (i == 0) break;
	}
	return null;
}

// Show the cookie string
function showAllCookies() {
	alert(document.cookie);
}

function getCookieValueByIndex(startIndex) {
	var endIndex = document.cookie.indexOf(";", startIndex);
	if (endIndex == -1) 
		endIndex = document.cookie.length;
	return unescape(document.cookie.substring(startIndex, endIndex));
}

// List all name/value pairs in a table
function listCookies() {
	document.writeln("<table cellpadding=2 cellspacing=0 border=1 align=center>");
	document.writeln("<tr><th>Name<th>Value");
	cookieArray = document.cookie.split(";");
	for (var i=0; i<cookieArray.length; i++) {
		thisCookie = cookieArray[i].split("=");
		cookieName = unescape(thisCookie[0]);
		cookieValue = unescape(thisCookie[1]);
		document.writeln("<tr><td><font color=red>"+cookieName+"</font><td><font color=green>"+cookieValue+"</font>");
	}
	document.writeln("</table>");
}

var SESSION_NAME = "EOMS";
var session = {'key1':'fdasfa', 'key2':'afdafds', 'key3':1233};

if(getCookie(SESSION_NAME)) {
	eval("session=" + getCookie(SESSION_NAME));
}

function saveSession() {
	var arr = new Array();
	for(var key in session) {
		document.write(key + ":" + session[key] + "<br>");
	}

		
	alert(typeof(session));
	for(var key in session['key3']) {
		document.write(key);
	}
}


saveSession();