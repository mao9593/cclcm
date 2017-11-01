<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<tiles:insert page="/layout/navibarLayout.jsp" flush="true">
	<tiles:put name="logo" value="/common/logo.jsp" />
	<tiles:put name="navibar"  value="/common/navimenu.jsp" />
</tiles:insert>
