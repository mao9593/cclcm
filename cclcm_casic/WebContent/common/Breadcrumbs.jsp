<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.struts.tiles.beans.MenuItem" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--
Description :
    Generates HTML code for site navigation. It is so called breadcrumbs.
    You have seen this type of navigation many times on various web sites. It looks some like this:
    first_link >> second_link >> etc.

Parameter:
    @param  sitemap vector holding the sitemap array
--%>

<%
    Vector arrNav = (Vector)request.getAttribute("sitemap");
    if (arrNav!=null) {
%>
<table width="100%" border="0" cellspacing="0" cellpadding="4">
  <tr>
    <td nowrap="true">您现在的位置：</td>
<%
        for (int i = 0; i < arrNav.size(); i++) {
            MenuItem item = (MenuItem) arrNav.elementAt(i);
            // output the link
%>
    <td nowrap="true"><a href="<%=item.getLink()%>"><%=item.getValue()%></a></td>
    <td>&gt;</td>
<%    }
    }
%>
    <td width="100%">&nbsp;</td>
  </tr>
</table>