<%--<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>--%>
<%--<%@ taglib uri="/WEB-INF/xTags.tld" prefix="xtag" %>--%>
<%--<%@ taglib uri="http://jsptags.com/tags/navigation/pager-el" prefix="pg" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>--%>

<%--============================================================--%>
<%--  Page index area                                           --%>
<%-- Outer parameter needed :                                   --%>
<%-- pageOffset, pageLimit, itemsCount                          --%>
<%--============================================================--%>
<table width="100%">
  <tr class="tableRowAction">

    <%--  Calcuate page through--%>
    <c:set var="pageThrough" value="${pageOffset+pageLimit}"/>
    <c:if test="${pageThrough > itemsCount}">
        <c:set var="pageThrough" value="${itemsCount}"/>
    </c:if>
<%--	<pg:index>--%>
		<td width="60%" align="left" nowrap="true">
		    共<c:out value="${itemsCount}"/>项，显示<c:out value="${pageOffset+1}"/>到<c:out value="${pageThrough}"/>项
		</td>
		<td align="right" nowrap="true">
			[<pg:first><a href="<%=pageUrl%>">首页</a></pg:first><pg:prev>/<a href="<%= pageUrl %>">前一页</a></pg:prev>]
			<pg:pages>
				<c:if test="${pageNumber == currentPageNumber}">
					<b><c:out value="${pageNumber}"/></b>
				</c:if>
				<c:if test="${pageNumber != currentPageNumber}">
					<a href="<c:out value="${pageUrl}"/>"><c:out value="${pageNumber}"/></a>
				</c:if>
			</pg:pages>
			[<pg:next><a href="<%= pageUrl %>">后一页</a>/</pg:next><pg:last><a href="<%=pageUrl%>">末页</a></pg:last>]
		</td>
<%--	</pg:index>--%>
  </tr>
</table>
<%--  EOF page index --%>