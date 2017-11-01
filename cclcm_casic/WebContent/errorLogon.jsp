<%@ page contentType="text/html;charset=UTF-8" %>
<script>
	<% String errorMsg = (String) request.getAttribute("errorMsg");%>
	alert("<%=errorMsg%>");
	history.go(-1);
</script>