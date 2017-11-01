<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<SELECT ondblclick="add_TrueCR('allOptionCR','selectOptionCR');" multiple="true"  name="allOptionCR" id="allOptionCR" size="10">
<c:forEach var="item" items="${userList}" varStatus="status">
	<option value="${item.user_iidd}">${item.user_name}/${item.dept_info}</option>
</c:forEach>
</SELECT>