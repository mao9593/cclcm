<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<script>
$(document).ready(function(){
	if($("#infotype").val() != null){
		alert("该类型子类型为空");
	}
}
</script>
<select name="info_id" id="info_id">
	<option value="">--全部--</option>
	<c:forEach items="${infotype}" var="info">
			<option value="${info.info_id}">${info.info_type}</option>
	</c:forEach>				
</select>