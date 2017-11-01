<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<title>YUI Library Examples: Dom Collection: Using addClass</title>
	<meta http-equiv="content-type" content="text/html; charset=gbk">
    	<link rel="stylesheet" type="text/css" href="../../assets/yui.css" >

<style>
/*Supplemental CSS for the YUI distribution*/
#custom-doc { width: 95%; min-width: 950px; }
</style>


<script type="text/javascript" src="build/yahoo/yahoo.js"></script>
<script type="text/javascript" src="build/event/event.js"></script>
<script type="text/javascript" src="build/dom/dom.js"></script>

<link href="<%=request.getContextPath()%>/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
<script language="javascript" src="<%=request.getContextPath()%>/_script/calendar2/calendar.js"></script>


<!--begin custom header content for this example-->
<style type="text/css">
#foo {
    margin-bottom:1em;
}
</style>
<!--end custom header content for this example-->
</head>


  <body>

  <!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

	<div id="foo" class="bar">foo</div>

<input type="text" id="txtggg">
<button id="demo-run">选择时间</button>

<script type="text/javascript">
(function() {
    var addClass = function(e) {

//        YAHOO.util.Dom.addClass('foo', 'baz');
//        alert(YAHOO.util.Dom.get('foo').className);
    };

    YAHOO.util.Event.on('demo-run', 'click', addClass);

    
})();
  Calendar.setup({inputField: "txtggg", button: "txtggg", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M", cache: true, weekNumbers:false,showOthers: true, step: 1});
</script>

	<!--END SOURCE CODE FOR EXAMPLE =============================== -->


  </body>
</html>