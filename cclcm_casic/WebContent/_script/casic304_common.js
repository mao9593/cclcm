/**
 * 添加输入框等变色效果
 */
function onHover(){
	var list=document.getElementsByTagName('input');
	for(i=0;i<list.length;i++){
		input = list[i];
		if(input.getAttribute('readonly')){
			// input.onfocus=new Function('alert("标签只读，不可直接修改");');
		}else if(input.getAttribute('type')=='text'){
			input.onkeypress=new Function('if(this.value.length>=30)return false;')
			input.onfocus = new Function('this.style.backgroundColor="#ede4cd";');
			input.onblur = new Function('this.style.backgroundColor="";');
		}else if(input.getAttribute('type')=='password'){
			input.onfocus = new Function('this.style.backgroundColor="#ede4cd";');
			input.onblur = new Function('this.style.backgroundColor="";');
		}
	}
	// 进入页面，输入焦点自动停留在第一个输入框
	for(i=0;i<list.length;i++){
		input = list[i];
		if(input.getAttribute('type')=='text' || input.getAttribute('type')=='password'){
			if(!input.getAttribute('readonly') && !input.getAttribute('disabled')){
				input.focus();
				break;
			}
		}
	}
	list = document.getElementsByTagName('textarea');
	for(i=0;i<list.length;i++){
		input = list[i];
		if(input.getAttribute('readonly')){
			// input.onfocus=new Function('alert("标签只读，不可直接修改");');
		}else{
			input.onfocus = new Function('this.style.backgroundColor="#ede4cd";');
			input.onblur = new Function('this.style.backgroundColor="";');
		}
	}
}
function onHoverInfinite(){
	var list=document.getElementsByTagName('input');
	for(i=0;i<list.length;i++){
		input = list[i];
		if(input.getAttribute('readonly')){
			// input.onfocus=new Function('alert("标签只读，不可直接修改");');
		}else if(input.getAttribute('type')=='text'){
			input.onfocus = new Function('this.style.backgroundColor="#ede4cd";');
			input.onblur = new Function('this.style.backgroundColor="";');
		}else if(input.getAttribute('type')=='password'){
			input.onfocus = new Function('this.style.backgroundColor="#ede4cd";');
			input.onblur = new Function('this.style.backgroundColor="";');
		}
	}
	// 进入页面，输入焦点自动停留在第一个输入框
	for(i=0;i<list.length;i++){
		input = list[i];
		if(input.getAttribute('type')=='text' || input.getAttribute('type')=='password'){
			if(!input.getAttribute('readonly') && !input.getAttribute('disabled')){
				input.focus();
				break;
			}
		}
	}
	list = document.getElementsByTagName('textarea');
	for(i=0;i<list.length;i++){
		input = list[i];
		if(input.getAttribute('readonly')){
			// input.onfocus=new Function('alert("标签只读，不可直接修改");');
		}else{
			input.onkeypress=new Function('if(this.value.length>=250)return false;')
			input.onfocus = new Function('this.style.backgroundColor="#ede4cd";');
			input.onblur = new Function('this.style.backgroundColor="";if(this.value.length>250){alert("最大合法输入长度为250个字符，超长部分将被截断。");this.value=this.value.substring(0,250);}');
		}
	}
}

// 遍历页面上的所有表单，禁止点击回车键时，form自动提交
function disableEnterSubmit(){
	var list=document.getElementsByTagName('form');
	for(i=0;i<list.length;i++){
		input = list[i];
		input.onkeydown=new Function('if(event.keyCode==13)return false;')
	}
}
/**
 * 部门树形展示
 */
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
   return window.showModalDialog(METAR_WEB_ROOT+"/user/getdeptlist.action?objName=" + objName + "&objId=" + objId + "&inputType=" + inputType, window, "dialogWidth:330px;dialogHeight:500px;status:no;scrollBars:no;Resizeable:no;help:no;");
}

function checkIP(value){
	var exp=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
	if(value.match(exp) == null){
		return false;
	}
	return true;
}
function test_password(str_password){
	var pattern = /^[a-zA-Z0-9~!@#$%^&*()_+:-]*[a-zA-Z0-9~!@#$%^&*()_+:-]+[a-zA-Z0-9~!@#$%^&*()_+:-]*$/;
	if(str_password.length<10){
		alert("密码长度小于10");
		document.all.password.focus();
   		return false;
	}
	if(pattern.test(str_password)){
   		return true;
	}
	else{
		alert("密码复杂度不符合要求");
		document.all.password.focus();
		return false;	
	}
}
function exportLog(formId,url,url1){
	document.getElementById(formId).action = url;
	document.getElementById(formId).submit();
	document.getElementById(formId).action = url1;
}
function go(url){
	window.location.href=url;
}
function preCalendar(){
	Calendar.setup({inputField: "startTime", button: "startTime_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
	Calendar.setup({inputField: "endTime", button: "endTime_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
}
function preCalendarDay(){
	Calendar.setup({inputField: "start_time", button: "start_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d", cache: true, weekNumbers:true, showOthers: true, step: 1});
	Calendar.setup({inputField: "end_time", button: "end_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d", cache: true, weekNumbers:true, showOthers: true, step: 1});
}
function add_True(allOption,selectOption){
	var all = document.getElementById(allOption);
	var select = document.getElementById(selectOption);
	if (all.selectedIndex > -1){
		selected_spr_text = all.options[all.selectedIndex].text;
		selected_spr_value = all.options[all.selectedIndex].value;
		var sel_sprlen = select.options.length - 1;
		var exist_flag = 1;
		var j = 0;
		for(j = 0; j <= sel_sprlen; j++){
			if(select.options[j].value == selected_spr_value){
				exist_flag = 0;
				break;
			}
		}
		if(exist_flag){
			var temp = new Option(selected_spr_text);
			temp.value = selected_spr_value;
			select.options[++sel_sprlen] = temp;
		}
		else{
			alert("'" + selected_spr_text + "'" + "该选项已存在于右边列表中！请重新选择");
		}
	}
}
function add_MoreTrue(allOption,selectOption){
	var all = document.getElementById(allOption);
	var select = document.getElementById(selectOption);
	if (all.selectedIndex > -1){
		var j = 0, k = 0;
		for(k = 0; k < all.options.length; k++){
			if(all.options[k].selected == true){
				var exist_flag = 1;
				selected_spr_text = all.options[k].text;
				selected_spr_value = all.options[k].value;
				var sel_sprlen = select.options.length - 1;
				for(j = 0; j <= sel_sprlen; j++){
					if(select.options[j].value == selected_spr_value){
                           exist_flag = 0;
                           break;
					}
				}
				if(exist_flag){
					var temp = new Option(selected_spr_text);
					temp.value = selected_spr_value;
					select.options[++sel_sprlen] = temp;
				}
				else{
					alert("'" + selected_spr_text + "'" + "该选项已存在于右边列表中！请重新选择");
				}
			}
		}
	}
}
function add_all_True(allOption,selectOption){
	var all = document.getElementById(allOption);
	var select = document.getElementById(selectOption);
	var sel_sprlen = select.options.length-1;
	var j = 0;
	for(j = sel_sprlen; j >= 0; j--){
		select.options[j] = null;
	}
	for(j = 0; j < all.options.length; j++){
		var temp = new Option(all.options[j].text);
		temp.value = all.options[j].value;
		select.options[j] = temp;
	}
}
function del_True(selectOption){
	var select = document.getElementById(selectOption);
	var sel_sprindex = select.selectedIndex;
	if(sel_sprindex != -1){
		select.options[sel_sprindex] =null;
	}
}
function del_MoreTrue(selectOption){
	var select = document.getElementById(selectOption);
	var k = 0;
	var temp = select.options.length - 1;
	for(k = temp; k >= 0; k--){
		if(select.options[k].selected == true){
			select.options[k] = null;
		}
	}
}
function del_all_True(selectOption){
	var select = document.getElementById(selectOption);
	var sel_sprlen = select.options.length - 1;
	var j = 0;
	for(j = sel_sprlen; j >= 0; j--){
		select.options[j] = null;
	}
}
var total=0;
function prepareBizApproval(step_dept,step_role,step_dept_name,step_role_name,ctx){
	if(step_dept.length > 0){
		var step_dept = step_dept.split("#");
		var step_role = step_role.split("#");
		var step_dept_name = step_dept_name.split("#");
		var step_role_name = step_role_name.split("#");
	}
	for(i=0;i<step_dept.length;i++){
		var step = new Object();
		step.dept_code = step_dept[i]; 
		step.dept_name = step_dept_name[i];
		step.role_id = step_role[i];
		step.role_name = step_role_name[i];
		total++;
		var $parent_td = $("#prc_end").parentsUntil("table").last().parentsUntil("tr").last();
		$td_step = create_step_element_biz(total,step,ctx);
		$parent_td.before($td_step);
		$parent_td.before("<td><table><tr><td><img src='"+ctx+"/_image/ico/process/to.gif' border='0'/></td></tr></table></td>");
	}
}
function create_step_element_biz(index,obj,ctx){
	$step = $("<img>",{
		alt:index+"级审批",
		border:"0",
		src:ctx+"/_image/ico/process/prc_step.jpg"
	});
	$td=$("<td/>");
	$table=$("<table/>");
	$tbody = $("<tbody/>");
	$tr1 = $("<tr/>");
	$td1 = $("<td align='center'/>");
	$td1.append($step);
	$tr1.append($td1);
	$tr2 = $("<tr/>");
	$td2 = $("<td>"+obj.dept_name+"["+obj.role_name+"]"+"</td>");
	$tr2.append($td2);
	$tbody.append($tr1);
	$tbody.append($tr2);
	$table.append($tbody);
	$td.append($table);
	return $td;
}
var pattern = /^[0-9]*$/;
function isInteger(value){
	if(!pattern.test(value)){
		return false;
	}
	if(value < 1 || value > 10000){
		return false;
	}
	return true;
}
String.prototype.trim = function()
{
    return this.replace(/^\s+|\s+$/g,"");
}
var code_pattern=/^[0-9a-zA-Z_]{1,30}$/;
var code_pattern_addword=/^[0-9a-zA-Z_\u4e00-\u9fa5]{1,30}$/;
function checkCode(value){
	if(!code_pattern.test(value)){
		return false;
	}
	return true;
}
function checkCode_addword(value){
	if(!code_pattern_addword.test(value)){
		return false;
	}
	return true;
}
function checkTime(){
	if($("input[name='startTime']").val() != "" && $("input[name='endTime']").val() != ""){
		var startTimeInput = $("input[name='startTime']").val();
		var endTimeInput = $("input[name='endTime']").val();
		var startYear = startTimeInput.substring(0,4);
		var endYear = endTimeInput.substring(0,4);
		var startTime = startTimeInput.substr(5,5)+"-"+startYear+startTimeInput.substr(10);
		var endTime = endTimeInput.substr(5,5)+"-"+endYear+endTimeInput.substr(10);
		var startLong = Date.parse(startTime);
		var endLong = Date.parse(endTime);
		if(startLong != NaN && endLong != NaN && startLong > endLong){
			alert("起止时间查询条件设置不合理，请修改");
			return false;
		}
	}
	return true;
}

function checkDateTime(){
	if($("input[name='start_time']").val() != "" && $("input[name='end_time']").val() != ""){
		var startTimeInput = $("input[name='start_time']").val();
		var endTimeInput = $("input[name='end_time']").val();
		var startYear = startTimeInput.substring(0,4);
		var endYear = endTimeInput.substring(0,4);
		var startTime = startTimeInput.substr(5,5)+"-"+startYear+startTimeInput.substr(10);
		var endTime = endTimeInput.substr(5,5)+"-"+endYear+endTimeInput.substr(10);
		var startLong = Date.parse(startTime);
		var endLong = Date.parse(endTime);
		if(startLong != NaN && endLong != NaN && startLong > endLong){
			alert("起止时间查询条件设置不合理，请修改");
			return false;
		}
	}
	return true;
}
function addSelectAllCheckbox(){
	$("th").first().html('<input type="checkbox" onclick="setSelectAllStatus(this);" value=""/>');
}
function setSelectAllStatus(tag){
	if(tag.checked){
		$(":checkbox").attr("checked",true);
	}else{
		$(":checkbox").attr("checked",false);
	}
}