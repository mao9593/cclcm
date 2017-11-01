<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link href="${ctx}/_css/classyloader.css" type="text/css" rel="stylesheet"/>
    <!--[if IE]>
    <script src="${ctx}/_script/html5/html5shiv.js"></script>
    <script src="${ctx}/_script/canvas/excanvas.js"></script>
    <![endif]-->
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/jq_classyloader/jquery.classyloader.js"></script>
    <script>
        $(function(){
            var loader =  $('.loader').ClassyLoader({
              fontColor: 'rgba(225, 225, 225, 0.8)',
              lineColor: 'rgba(9, 86, 168, 1)',
              remainingLineColor: 'rgba(255, 255, 255, 0)',
              percentage: 99
            });       
            
   
        });
		$(function(){
		    $('#page2').load('${queryString}',function(){ $('#page1').hide();});
		    
		});

    </script>
</head>
<body>
<div id="page1">
    <div id="black_overlay" style="top:0px ; left:0px"></div>
    <div id="load_content" >
        <div style="float: left; padding-top: 12px; padding-left: 5px;">
        </div>
        <div style="float: left; padding-top: 15px;">               
           <canvas class="loader"></canvas>
        </div>
    </div>
</div>
<div id="page2"></div>

</body>
</html>