<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:if test="${delResult=='success'}">
删除成功!
</c:if>
<c:if test="${delResult=='exception'}">
删除出错!
</c:if>
<c:if test="${delResult=='paramError'}">
参数错误!
</c:if>
