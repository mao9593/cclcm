<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>岗位等级修改</title>
    <link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
 <style>
.myDiv {
	DISPLAY:BLOCK;BORDER-RIGHT: #669999 2px solid; PADDING-RIGHT: 5px; BORDER-TOP: #669999 2px solid; PADDING-LEFT: 5px; PADDING-BOTTOM: 5px; MARGIN: 3px; BORDER-LEFT: #669999 2px solid; WIDTH: 500; PADDING-TOP: 5px; BORDER-BOTTOM: #669999 2px solid
}
.trDiv {
	DISPLAY:BLOCK;BORDER-RIGHT: #669999 2px solid; PADDING-RIGHT: 5px; BORDER-TOP: #669999 2px solid; PADDING-LEFT: 5px; PADDING-BOTTOM: 5px; MARGIN: 3px; BORDER-LEFT: #669999 2px solid; WIDTH: 450;HEIGHT: 50; PADDING-TOP: 5px; BORDER-BOTTOM: #669999 2px solid
}
.tdDiv {
	BORDER-RIGHT: #669999 2px solid; PADDING-RIGHT: 5px; BORDER-TOP: #669999 2px solid; PADDING-LEFT: 5px; FLOAT: left; PADDING-BOTTOM: 0px; MARGIN: 3px; BORDER-LEFT: #669999 2px solid; WIDTH: 100;HEIGHT:40; PADDING-TOP: 5px; BORDER-BOTTOM: #669999 2px solid
}
.trDivOver {
	BACKGROUND-COLOR: #eee
}
.tdDivOver {
	BACKGROUND-COLOR: #eee
}

.tdDivDrag {
	BORDER-RIGHT: #000 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #000 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 10px; MARGIN-BOTTOM: 5px; PADDING-BOTTOM: 2px; BORDER-LEFT: #000 1px solid; WIDTH: 94px; CURSOR: pointer; PADDING-TOP: 2px; BORDER-BOTTOM: #000 1px solid; FONT-FAMILY: verdana, tahoma, arial; BACKGROUND-COLOR: #eee
}
.dragObj {
	FILTER: alpha(opacity=50);DISPLAY:BLOCK;BORDER-RIGHT: #266939 2px solid; BORDER-TOP: #266939 2px solid; FLOAT: left; PADDING-BOTTOM: 0px; MARGIN: 3px; BORDER-LEFT: #266939 2px solid; WIDTH: 100;HEIGHT: 40; PADDING-TOP: 5px; BORDER-BOTTOM: #266939 2px solid
}
.dragTempTr {
	DISPLAY:BLOCK;BORDER-RIGHT: #600999 2px solid; PADDING-RIGHT: 5px; BORDER-TOP: #600999 2px solid; PADDING-LEFT: 5px; PADDING-BOTTOM: 0px; MARGIN: 3px; BORDER-LEFT: #600999 2px solid; WIDTH: 450;HEIGHT: 50; PADDING-TOP: 5px; BORDER-BOTTOM: #600999 2px solid
}
.dragTempTd{
	DISPLAY:BLOCK;BORDER-RIGHT: #266939 2px solid; PADDING-RIGHT: 5px; BORDER-TOP: #266939 2px solid; PADDING-LEFT: 5px; FLOAT: left; PADDING-BOTTOM: 0px; MARGIN: 3px; BORDER-LEFT: #266939 2px solid; WIDTH: 100;HEIGHT:40; PADDING-TOP: 5px; BORDER-BOTTOM: #266939 2px solid
}
</style>
<script language="javascript">
Number.prototype.NaN0=function(){return isNaN(this)?0:this;}
//移动中的对象
var dragObj=document.createElement("div");
//dragObj.style.display="none";
dragObj.style.position="absolute";

//移动时创建的临时对象
var createTempObj=null;
var createTempIndex=-2;

//判断对象是否移动,是否鼠标按下
var isMoving=false;
var isMouseDown=false;

var preTr=null;
var perTrIndex=-1;
var preTd=null;
var preTdIndex=-1;

var isTrAdd=false;

//取得对象的绝对位置,放到对象的absTop和absLeft属性中
function getAbsPos(ele){
for(i=0;i<ele.length;i++){
  var e=ele[i];
  var left=0;
  var top=0;
  	ele[i].absWidth=e.offsetWidth;
	ele[i].absHeight=e.offsetHeight;
	while (e.offsetParent){
		left += e.offsetLeft + (e.currentStyle?(parseInt(e.currentStyle.borderLeftWidth)).NaN0():0);
		top  += e.offsetTop  + (e.currentStyle?(parseInt(e.currentStyle.borderTopWidth)).NaN0():0);
		e     = e.offsetParent;
	}
	ele[i].absTop=top;
	ele[i].absLeft=left;

	}
}

//判断对象是否在ele集合中,返回index,否则返回-1
function getObjOver(ele,currObj,offsetX,offsetY){
  for(i=0;i<ele.length;i++){
    var e=ele[i];
    if(currObj.absTop>e.absTop&&currObj.absLeft>e.absLeft&&
       currObj.absTop<e.absTop+e.absHeight&&
       currObj.absLeft<e.absLeft+e.absWidth){
     return i;
     break;
    }

  }
  return -1;
}
//更新tr显示顺序
function updateOrder(ele){
 for(i=0;i<ele.length;i++){
     var e=ele[i];
    e.firstChild.innerText="等级"+(i+1);
     if(e.children.length>1){
     for(j=1;j<e.children.length;j++)
         e.children[j].all.post_class.value=i+1;
 }
     }
}

function mouseDown(){
isMouseDown = true;
var myDiv=document.getElementById("myDiv");
//var info=document.getElementById("info");
var styleCon=document.getElementById("styleCon");
 //alert(myDiv);

 var myTr=myDiv.children;
 //detachEventAll(myTr);
 ev =window.event;  //事件对象
  document.body.preEvX=ev.clientX;
    document.body.preEvY=ev.clientY;
 //ev.cancelBubble=true;
 //var target   = ev.srcElement; //鼠标经过的对象
 getAbsPos(myTr);

 //取得鼠标的绝对位置
 ev.absTop=ev.clientY + document.body.scrollTop  - document.body.clientTop;
 ev.absLeft=ev.clientX + document.body.scrollLeft - document.body.clientLeft;
 var overTrIndex=getObjOver(myTr,ev,0,0);
 if(overTrIndex!=-1){
 //鼠标经过的tr
  //info.innerText="tr:"+ev.absTop+" "+ev.absLeft+" "+getObjOver(myTr,ev,0,0);

  var myTd=new Array(); //鼠标经过的tr内的所有td的集合
//alert(myTr[overTrIndex].children.length);
for(i=0;i<myTr[overTrIndex].children.length;i++){
if(myTr[overTrIndex].children[i].id=="mytd"){
  myTd.push(myTr[overTrIndex].children[i]);
}
}

  //alert(myTd.length);

  if(myTd){
   //td不为空,找到当前的td
     getAbsPos(myTd);
     var overTdIndex=getObjOver(myTd,ev,0,0);

    // info.innerText="td:"+ev.absTop+" "+ev.absLeft+" "+getObjOver(myTd,ev,0,0);

     if(overTdIndex!=-1){
     //鼠标经过的td
     preTd=myTd[overTdIndex];
     preTd.offsetX=ev.absLeft-preTd.absLeft;
     preTd.offsetY=ev.absTop-preTd.absTop;
     preTr=myTr[overTrIndex];
     preTrIndex=overTrIndex;
     dragObj.innerHTML="";
     dragObj.appendChild(preTd.cloneNode(true));
     document.body.appendChild(dragObj);
        dragObj.style.display="block";
        dragObj.firstChild.className=styleCon.getAttribute("dragObj");
        dragObj.style.top  = ev.absTop-preTd.offsetY;
        dragObj.style.left  = ev.absLeft-preTd.offsetX;
        dragObj.zIndex=1;

     createTempObj=document.createElement("div");
     //createTempObj.style.display="none";
     }
  }

}
 //info.innerText= createTempObj.style.cssText;
}

function mouseMove(){
var myDiv=document.getElementById("myDiv");
//var info=document.getElementById("info");
var styleCon=document.getElementById("styleCon");
 //alert(myDiv);

 var myTr=myDiv.children;
 //detachEventAll(myTr);

 ev =window.event;  //事件对象
 ev.cancelBubble=true;
 var target   = ev.srcElement; //鼠标经过的对象

 //取得鼠标的绝对位置
 ev.absTop=ev.clientY + document.body.scrollTop  - document.body.clientTop;
 ev.absLeft=ev.clientX + document.body.scrollLeft - document.body.clientLeft;

 //getAbsPos(myTr);
 var overTrIndex=getObjOver(myTr,ev,0,0);

 if(preTd){
       // dragObj.innerHTML="";
        //dragObj.zIndex=1;
        //dragObj.appendChild(preTd.cloneNode(true));

        //dragObj.innerText="BBBBB";
        //dragObj.style.display="block";
        dragObj.firstChild.className=styleCon.getAttribute("dragObj");
        dragObj.style.top  = ev.absTop-preTd.offsetY;
        dragObj.style.left  = ev.absLeft-preTd.offsetX;
        //preTd.style.display="none";
        //document.body.appendChild(dragObj);
     //取得body的位置,滚动时使用
         getAbsPos(new Array(document.body));
         if(document.body.preEvY<=ev.clientY&&ev.clientY>document.body.clientHeight-80){
           window.scrollBy(0,10);
            // alert(document.body.clientHeight);
        }
		if(document.body.preEvY>=ev.clientY&&ev.clientY<80){
         window.scrollBy(0,-10);
           // info.innerText=document.body.scrollHeight+" "+ev.absTop+" "+(document.body.absTop+document.body.absHeight);
        }
      document.body.preEvY =ev.clientY;
     //info.innerText=document.body.scrollHeight+" "+ev.clientY+" "+(document.body.absTop+document.body.absHeight);

 }

 if(overTrIndex!=-1){
 //鼠标经过的tr
  //info.innerText="tr:"+dragObj.style.top+" "+myTr[overTrIndex].absTop+" "+getObjOver(myTr,ev,0,0);

if(isMouseDown&&preTd){
if(parseInt(dragObj.style.top)<myTr[overTrIndex].absTop+myTr[overTrIndex].absHeight&&
            parseInt(dragObj.style.top)>myTr[overTrIndex].absTop+myTr[overTrIndex].absHeight-preTd.absHeight){
      //info.innerText="add";
      //插入tr
      createTempObj.style.display="block";
      createTempObj.className=styleCon.getAttribute("dragTempTr");
      myTr[overTrIndex].insertAdjacentElement("afterEnd", createTempObj)
      createTempIndex=overTrIndex+1;
      isTrAdd=true;
      createTempObj.hide=false;

 }
 else if(myTr[overTrIndex]!=createTempObj&&myTr[overTrIndex]!=preTr){
       myTr[overTrIndex].appendChild(createTempObj);
       createTempObj.style.display="block";
      createTempObj.className=styleCon.getAttribute("dragTempTd");

      createTempIndex=-2;
      isTrAdd=false;
       createTempObj.hide=false;

 }else {
    //alert("aaa");
    createTempObj.style.display="none";
    createTempObj.hide=true;
    myTr[overTrIndex].appendChild(createTempObj);
    isTrAdd=false;


 }
  isMoving=true;
 // info.innerText=isMouseDown;
 }

  //var myTd=myTr[overTrIndex].children; //鼠标经过的tr内的所有td的集合


   /*
  if(myTd){
   //td不为空,找到当前的td
     var overTdIndex=getObjOver(myTd,ev,0,0);
     getAbsPos(myTd);
     //info.innerText="td:"+ev.absTop+" "+ev.absLeft+" "+getObjOver(myTd,ev,0,0);

     if(overTdIndex!=-1){
     //鼠标经过的td
        if(isMouseDown&&preTd!=null){

        //拖动经过
        //info.innerText="drag";
        //var tempTd=myTd[overTdIndex].cloneNode(true);
        //如果在tr边缘
        if(){
        }
        else if(){
        //如果在td边缘
        }


       // alert(dragObj.outerHTML);

        }else{
        //只是经过
        //info.innerText="moving";

        }


     }
  }*/

 }else{
  //最上方
 if(isMouseDown&&preTd&&parseInt(dragObj.style.top)<myTr[0].absTop&&parseInt(dragObj.style.top)>myTr[0].absTop-30){
      myTr[0].insertAdjacentElement("beforeBegin", createTempObj);
      createTempObj.style.display="block";
      createTempObj.className=styleCon.getAttribute("dragTempTr");
      createTempIndex=0;
      isTrAdd=true;
      createTempObj.hide=false;

}

 }


 //info.innerText="tr:"+ev.absTop+" "+ev.absLeft+" "+getObjOver(myTr,ev,0,0);
 //info.innerText=createTempObj.style.cssText;

}

function mouseUp(){
var myDiv=document.getElementById("myDiv");
//var info=document.getElementById("info");
var styleCon=document.getElementById("styleCon");
 //alert(myDiv);

 var myTr=myDiv.children;

 ev =window.event;  //事件对象
 ev.cancelBubble=true;
 var target   = ev.srcElement; //鼠标经过的对象

 //取得鼠标的绝对位置
 ev.absTop=ev.clientY + document.body.scrollTop  - document.body.clientTop;
 ev.absLeft=ev.clientX + document.body.scrollLeft - document.body.clientLeft;

 getAbsPos(myTr);
 var overTrIndex=getObjOver(myTr,ev,0,0);


dragObj.style.display="none";

if(preTd&&isMoving){
 if(!createTempObj.hide){
  if(isTrAdd){
  createTempOrder=document.createElement("div");
  createTempOrder.style.cssText="display:block;WIDTH:400;";
  createTempObj.appendChild(createTempOrder);
  createTempObj.appendChild(preTd.cloneNode(true));
  createTempObj.className=styleCon.getAttribute("trDiv");
  preTd.outerHTML="";

  }else{
  //td add
  createTempObj.outerHTML=preTd.outerHTML;
  createTempObj.className=styleCon.getAttribute("tdDiv");
  preTd.outerHTML="";

  }

}else{
 createTempObj.outerHTML="";

}

 if(preTr.children.length<2){
    preTr.outerHTML="";
  }
updateOrder(myTr);
}

//info.innerText=createTempObj.outerHTML.outerHTML;
isMouseDown=false;
isMoving=false;
preTd=null;
preTr=null;

}

document.onmousedown = mouseDown;
document.onmousemove = mouseMove;
document.onmouseup = mouseUp;

document.onload=function(){
document.body.appendChild(dragObj);
}
</script>

</head>
<body onselectstart="return false">
<form action="${ctx}/user/configpostclass.action" method="post">
 <table border="0" cellspacing="0" cellpadding="0" class="table_only_border" width="100%" height="100%">
	<tr>
		<td colspan="2" class="title_box">
			岗位等级修改
		</td>
	</tr>
	<tr>
		<td valign="top" align="center">
        <div id="myDiv" class="myDiv">
        <c:forEach var="postListItem" items="${postList}" varStatus="status">
        	<div id="mytr" class="trDiv">
        		<div id="order" style="display:block;WIDTH:400;">等级<c:out value="${status.index+1}"/></div>
        		<c:forEach var="post" items="${postListItem}">
        			<div id="mytd"  class='tdDiv'>
        				${post.post_name}
        				<input name="post_id" type="hidden" value="${post.post_id}"/>
                 		<input name="post_class" type="hidden" value="<c:out value="${status.index+1}"/>"/>
                 	</div>
        		</c:forEach>
        		</div>
        </c:forEach>
        </div>
        </td>
</tr>
     <tr>
            <td align="center">
            <input type="submit" class="button_2003" value="确定"/>
            <input type="button" class="button_2003" value="返回" onclick="window.location='${ctx}/user/managepost.action'"/>
        </td>
     </tr>
     </table>
<!--the div hold styles-->
<div id="styleCon" trDiv="trDiv" tdDiv="tdDiv" trDivOver="trDivOver" tdDivOver="tdDivOver" tdDivDrag="tdDivDrag"
      dragObj="dragObj" dragTempTr="dragTempTr" dragTempTd="dragTempTd"></div>
    </form>
</body>
</html>