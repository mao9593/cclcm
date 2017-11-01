<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<SELECT ondblclick="add_TrueSP('allOptionSP','selectOptionSP');" multiple="true"  name="allOptionSP" id="allOptionSP" size="10">
<c:forEach var="item" items="${secplaceList}" varStatus="status">
	<option value="${item.secplace_code}">${item.secplace_name}</option>
</c:forEach>
</SELECT>