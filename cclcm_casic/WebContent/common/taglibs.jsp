<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="ctx" scope="request" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
<!--
	var METAR_WEB_ROOT="<c:out value='${ctx}'/>";
	if("${topPromptCancel}" == "Y"){
	}else{
		if("${topPrompt}" != ""){
			alert("${topPrompt}");
			if("${topHref}" != ""){
				window.location.href ="${topHref}"; 
			}
		}
	}
//-->

</script>
