<%-- Error Messages --%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<logic:messagesPresent>
	<div class="error">
		<html:messages id="error">
			<c:out value="${error}"/><br/>
		</html:messages>
	</div>
</logic:messagesPresent>

<%-- Success Messages --%>
<logic:messagesPresent message="true">
	<div class="message" id="message">
		<html:messages id="message" message="true">
			<c:out value="${message}"/><br/>
		</html:messages>
	</div>
	<script type="text/javascript">
		new Effect.Highlight('message');
		window.setTimeout("Effect.DropOut('message')", 1000);
	</script>
</logic:messagesPresent>


