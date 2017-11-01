<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>

<h2>Request Parameters:</h2>
<hr/>
<%
    Enumeration enu=request.getParameterNames();
    while (enu.hasMoreElements()) {
        String s=(String)enu.nextElement();
        out.write(s+" -- "+request.getParameter(s)+"<br/>");
    }
%>
<p>&nbsp;</p>

<h2>Request Attributes:</h2>
<hr/>
<%
    enu=request.getAttributeNames();
    while (enu.hasMoreElements()) {
        String s=(String)enu.nextElement();
        out.write(s+" -- "+request.getAttribute(s)+"<br/>");
    }
%>