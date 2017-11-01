<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:if test="${result=='success'}">
恢复成功!
</c:if>
<c:if test="${result=='exception'}">
恢复出错!
</c:if>
<c:if test="${result=='paramError'}">
参数错误!
</c:if>
