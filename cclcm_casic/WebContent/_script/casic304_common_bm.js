//把密级设置为非密，参数为密级控件名称
function setSeclv(param){
	$("#"+param).val('6');
}

/**
 * 添加输入框等变色效果(将textarea框最大限制设置为500)
 */
function onHoverBm(){
	var list=document.getElementsByTagName('input');
	for(i=0;i<list.length;i++){
		input = list[i];
		if(input.getAttribute('readonly')){
			// input.onfocus=new Function('alert("标签只读，不可直接修改");');
		}else if(input.getAttribute('type')=='text'){
			input.onkeypress=new Function('if(this.value.length>=30)return false;')
			input.onfocus = new Function('this.style.backgroundColor="#ede4cd";');
			input.onblur = new Function('this.style.backgroundColor="";if(this.value.length>30){alert("最大合法输入长度为30个字符，超长部分将被截断。");this.value=this.value.substring(0,30);}');
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
			input.onkeypress=new Function('if(this.value.length>=500)return false;')
			input.onfocus = new Function('this.style.backgroundColor="#ede4cd";');
			input.onblur = new Function('this.style.backgroundColor="";if(this.value.length>500){alert("最大合法输入长度为500个字符，超长部分将被截断。");this.value=this.value.substring(0,500);}');
		}
	}
}

function viewOpinion_new(type){
	var cur_step = Number($("#current_step").val());//新一轮审批中的当前步
	var len = Number($("#listSize").val());//总的审批步数，包含重新申请步
	var history_step = 0;
	if(len > cur_step){
		history_step = len - cur_step + 1;//历史审批步数，不包含新一轮审批的步数
	}

	var opinions = $("#opinion_all").val();
	var opinion_hs = $("#opinion_history").val();
	var step_opinion = new Array();
	var hstep_opinion = new Array();
	step_opinion = opinions.split("|");
	hstep_opinion = opinion_hs.split("|");
	var stepx = "";
	var i = 0;
	//展示历史审批
	var tr_history = "";
	for(i=1;i<=history_step;i++){
		var this_opinion = new Array();
		this_opinion = hstep_opinion[i].split("#");
		var new_tr = '<tr><td align="center">'+this_opinion[0]+'</td><td colspan="5"><font color="blue"><b>'+this_opinion[1]+'</b></font></td></tr>';
		tr_history = tr_history + new_tr;
	}
	$("#addapprovehistory").after(tr_history);
	//展示新一轮审批中的已经审批的内容
	if(type == "read"){
		for(i=1;i<cur_step;i++){
			var this_opinion = new Array();
			this_opinion = step_opinion[i].split("#");
			stepx = "showfile" + i.toString();
			if(document.getElementById(stepx)){
				document.getElementById(stepx).style.display = "block";
				var opin = "text" + i.toString();
				if(document.getElementById(opin)){
					document.getElementById(opin).innerHTML = '<font color="blue"><b>'+this_opinion[1]+'</b></font>';
				}
			}else{
				stepx = "step" + i.toString();
				if(document.getElementById(stepx)){
					document.getElementById(stepx).innerHTML = '<font color="blue"><b>'+this_opinion[1]+'</b></font>';
				}
			}
		}
	}else{
		for(i=1;i<=cur_step;i++){
			var this_opinion = new Array();
			this_opinion = step_opinion[i].split("#");
			stepx = "showfile" + i.toString();
			if(document.getElementById(stepx)){
				document.getElementById(stepx).style.display = "block";
				var opin = "text" + i.toString();
				if(document.getElementById(opin)){
					document.getElementById(opin).innerHTML = '<font color="blue"><b>'+this_opinion[1]+'</b></font>';
				}
			}else{
				stepx = "step" + i.toString();
				if(document.getElementById(stepx)){
					document.getElementById(stepx).innerHTML = '<font color="blue"><b>'+this_opinion[1]+'</b></font>';
				}
			}
		}
	}
	

	if(type == "read"){
		stepx = "opinion"+i.toString();
		if(document.getElementById(stepx)){
			document.getElementById(stepx).style.backgroundColor='#ede4cd';
			document.getElementById(stepx).readOnly="";
		}
		
		stepx = "hidden"+i.toString();
		if(document.getElementById(stepx)){
//			var step_num = "";
//			var nums = 1;
//			while(nums < cur_step){
//				step_num = step_num + '<option value="'+nums+'">回退'+nums+'步</option>';
//				alert("read2.0 step_num:"+step_num);
//				nums = nums + 1;
//				alert("read2.0 nums:"+nums);
//			}
//			alert("read3 step_num:"+step_num);
//			var choose_opinion = '&nbsp;&nbsp;<input type="radio" name="approved" checked="checked" value="true" id="approved" />同意&nbsp;&nbsp;'+
//			'<input type="radio" name="approved" value="false"/>不同意  '+
//			'<select name="return_step" id="return_step" style="width:100px;">'+
//			'<option value="end" >直接结束流程</option>'+step_num+'<option value="start">回退到申请人处</option></select>';
			var choose_opinion = '&nbsp;&nbsp;<input type="radio" name="approved" checked="checked" value="true" id="approved" />同意&nbsp;&nbsp;'+
			'<input type="radio" name="approved" value="false"/>不同意';
			document.getElementById(stepx).innerHTML = choose_opinion;
		}
	
		stepx = "addfile"+i.toString();
		if(document.getElementById(stepx)){
			document.getElementById(stepx).style.display = "block";
		}
		stepx = "fileflag"+i.toString();
		if(document.getElementById(stepx)){
			document.getElementById(stepx).innerHTML = '<input type="hidden" name="add_file" value="Y" id="add_file" />';
		}
		
	}
}

function subOpinion_new(opinion_name){
	var i= Number($("#current_step").val());
	var stepx = "opinion"+i.toString();
	if(document.getElementById(stepx)){
		var this_value = document.getElementById(stepx).value;
		if( this_value == ""){
			return "result_null";
		}else{
			var re = /#/;
			if(this_value.search(re) != -1){
				return "result_#";
			}else{
				var values = opinion_name + "#"+this_value;
				$("#opinion").val(values);
				return "result_true"
			}
		}
	}else{
		return "step_null";
	}	
}		

function viewOpinion(type){
	var len = $("#listSize").val();
	var opinions = $("#opinion_all").val();
	var step_opinion = new Array();
	step_opinion = opinions.split("|");
	var stepx = "";
	var i = 1;
	for(i=1;i<=len;i++){
		stepx = "showfile" + i.toString();
		if(document.getElementById(stepx)){
			document.getElementById(stepx).style.display = "block";
			var opin = "text" + i.toString();
			if(document.getElementById(opin)){
				document.getElementById(opin).innerHTML = '<font color="blue"><b>'+step_opinion[i]+'</b></font>';
			}
		}else{
			stepx = "step" + i.toString();
			if(document.getElementById(stepx)){
				document.getElementById(stepx).innerHTML = '<font color="blue"><b>'+step_opinion[i]+'</b></font>';
			}
		}
	}
	if(type == "read"){
		stepx = "opinion"+i.toString();
		if(document.getElementById(stepx)){
			document.getElementById(stepx).style.backgroundColor='#ede4cd';
			document.getElementById(stepx).readOnly="";
		}
		
		stepx = "hidden"+i.toString();
		if(document.getElementById(stepx)){
			document.getElementById(stepx).innerHTML ='&nbsp;&nbsp;<input type="radio" name="approved" checked="checked" value="true" id="approved" />同意&nbsp;&nbsp;<input type="radio" name="approved" value="false"/>不同意';
		}
	
		stepx = "addfile"+i.toString();
		if(document.getElementById(stepx)){
			document.getElementById(stepx).style.display = "block";
		}
		stepx = "fileflag"+i.toString();
		if(document.getElementById(stepx)){
			document.getElementById(stepx).innerHTML = '<input type="hidden" name="add_file" value="Y" id="add_file" />';
		}
		
	}
}

function subOpinion(){
	var i= Number($("#listSize").val());
	i = i+1;
	var stepx = "opinion"+i.toString();
	if(document.getElementById(stepx)){
		var values = document.getElementById(stepx).value;
		$("#opinion").val(values);
	}else {
		$("#opinion").val("同意");
	}	
	
}		

/**
 *  根据单选有无选择，确定复选框是否全部置灰全可选或全不可选
 *  @para value:单选选择“有”、“无”；
 *        other:是否包含其他输入框使其全置灰或全可填。1包含其他输入框，0无其他输入框。
 *  @author guojiao 2016-2-25 
 **/
function judgeIfGray(value, other){
	if(value == "无"){
		var objs = window.document.getElementsByTagName("input");
		for(var i=0;i<objs.length;i++){
			if(objs[i].type == "checkbox"){
				objs[i].disabled = true;
				objs[i].checked = false;
			}
		}
		if(other == 1){
			$("#test1").attr("disabled",true);
		}
	}else{
		var objs = window.document.getElementsByTagName("input");
		for(var i=0;i<objs.length;i++){
			if(objs[i].type == "checkbox"){
				objs[i].disabled = false;
			}
		}
		if(other == 1){
			$("#test1").attr("disabled",false);
		}
	}
}