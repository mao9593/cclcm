<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:if test="${done == 'Y'}">
<SELECT ondblclick="add_True('next_approver_all','next_approver_sel');" style="WIDTH: 100px" multiple="true" size="8" id="next_approver_all">
<c:forEach var="item" items="${userList}" varStatus="status">
	<option value="${item.user_iidd}">${item.user_name}</option>
</c:forEach>
</SELECT>
</c:if>
<c:if test="${done != 'Y'}">
	${done}
</c:if>