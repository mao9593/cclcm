/*
用于封装简单的XMLHTTP通信
 */

/*
用POST方法发送HTTP请求并返回响应数据。
服务器端可返回一个页面，页面中只包含返回的内容，典型页面内代码只有以下一行：
<%=request.getAttribute("return_data")%>
 */
function send(reqURI){
	// alert(reqURI);
	xml = new ActiveXObject("Microsoft.XMLHTTP");
	var post="";// 构造要携带的数据
	// xml.open("POST","http://localhost:7001/TestWL/index.jsp",false);//使用POST方法打开一个到服务器的连接，以异步方式通信
	try{
		xml.open("POST", reqURI, false);
　　		xml.setrequestheader("content-length", post.length);
　　		xml.setrequestheader("content-type", "application/x-www-form-urlencoded");
		xml.send(post);// 发送数据
	}
	catch(e){
		alert(e);
	}
	// alert(xml.responseText);
　　return xml.responseText;// 接收服务器返回的数据
}