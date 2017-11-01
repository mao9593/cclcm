/*
 * modified by renmingfei 2013-07-05
 */
// 得到部门树
function openDeptSelect(objName, objId, inputType){
	if (window.event != null){
		window.event.cancelBubble = true;
		eventX = event.x;
		eventY = event.y;
	}
	else{
		eventX = 0;
		eventY = 0;
	}
	if(treeType == null || treeType == undefined) treeType = "";
		if(specCodes == null || specCodes == undefined) specCodes = "";
    if(operCodes == null || operCodes == undefined) operCodes = "";
	if(defaultTab==null||defaultTab==undefined) defaultTab="";
    window.showModalDialog(METAR_WEB_ROOT+"/user/getdeptlist.action?objName=" + objName + "&objId=" + objId + "&inputType=" + inputType, window, "dialogWidth:330px;dialogHeight:500px;status:no;scrollBars:no;Resizeable:no;dialogLeft:" + eventX + ";dialogTop:" + eventY);
}


