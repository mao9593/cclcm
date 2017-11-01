<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<c:if test="${chkResult=='available'}">
    <div style="color:#33CC00">该条码可用!</div>
</c:if>
<c:if test="${chkResult=='used'}">
	<div style="color:#FF0000"> 该条码已存在,请更换!</div>
</c:if>
<c:if test="${chkResult=='syntaxErr'}">
	<div style="color:#FF0000"> 条码中不能包含空格和特殊字符!</div>
</c:if>
<c:if test="${chkResult=='exception'}">
	<div style="color:#FF9900"> 检测出错,请重试!</div>
</c:if>
<c:if test="${chkResult=='blank'}">
	<div style="color:#FF9900"> 请输入预发条码!</div>
</c:if>
