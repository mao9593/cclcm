var xmlHttp = false;
try {
  xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
} catch (e) {
  try {
    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
  } catch (e2) {
    xmlHttp = false;
  }
}
if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
  xmlHttp = new XMLHttpRequest();
}
function callServer(url) {
	xmlHttp.open("GET", url, true);
	xmlHttp.onreadystatechange = updateResult;
	xmlHttp.send(null);
}
function callServer1(url,param) {
	xmlHttp.open("POST", url, true);
	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
	xmlHttp.onreadystatechange = updateResult;
	xmlHttp.send(param);
}
function callServer3(url,param) {
	xmlHttp.open("POST", url, true);
	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
	xmlHttp.onreadystatechange = updateResult1;
	xmlHttp.send(param);
}
function callServer4(url,param) {
	xmlHttp.open("POST", url, true);
	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
	xmlHttp.onreadystatechange = updateResult2;
	xmlHttp.send(param);
}
function callServer2(url) {
	xmlHttp.open("GET", url, true);
	xmlHttp.onreadystatechange = updatePage;
	xmlHttp.send(null);
}
function callServerPostForm(url,form) {
	xmlHttp.open("POST", url, true);
	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
	xmlHttp.onreadystatechange = getAjaxResult;
	xmlHttp.send(serialize(form));
}
function callServerPostForm1(url,form) {
	xmlHttp.open("POST", url, true);
	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
	xmlHttp.onreadystatechange = getAjaxResult1;
	xmlHttp.send(serialize(form));
}
function callServerPostForm2(url,form) {
	xmlHttp.open("POST", url, true);
	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
	xmlHttp.onreadystatechange = getAjaxResult2;
	xmlHttp.send(serialize(form));
}
function updateResult() {
	if (xmlHttp.readyState < 4) {
		result.innerHTML="检测中...";
	}
	if (xmlHttp.readyState == 4) {
		var response = xmlHttp.responseText;
		result.innerHTML=response;
	}
}
function updatePage() {
	if (xmlHttp.readyState == 4) {
		var response = xmlHttp.responseText;
		alert(response);
		window.location = window.location;
	}
}
/* 表单序列化函数，用于使用ajax提交表单的情况 */
function serialize(form){
	var parts = [],
	field = null,
	i,
	len,
	j,
	optLen,
	option,
	optValue;
	for(i=0,len=form.elements.length;i<len;i++){
		field = form.elements[i];
		switch(field.type){
			case "select-one":
			case "select-multiple":
				if(field.name.length){
					for(j=0,optLen=field.options.length;j<optLen;j++){
						option=field.options[j];
						if(option.selected){
							optValue = "";
							if(option.hasAttribute){
								optValue = (option.hasAttribute("value")?option.value:option.text);
							}else{
								optValue = (option.attributes["value"].specified?option.value:option.text);
							}
							parts.push(encodeURIComponent(field.name) + "=" + encodeURIComponent(optValue));
						}
					}
				}
				break;
			case undefined:   // 字段集
			case "file":      // 文件输入
			case "submit":    // 提交按钮
			case "reset":     // 重置按钮
			case "button":    // 自定义按钮
				break;
			case "radio":     // 单选按钮
			case "checkbox":  // 复选框
				if(!field.checked){
					break;
				}
				/* 执行默认操作 */
				
			default:
				// 不包含没有名字的表单字段
				if(field.name.length){
					parts.push(encodeURIComponent(field.name) + "=" + encodeURIComponent(field.value));
				}
		}
	}
	return parts.join("&");
}