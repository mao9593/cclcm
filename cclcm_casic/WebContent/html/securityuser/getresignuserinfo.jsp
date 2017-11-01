<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<table width="100%" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr> 
	 	<td width="12%" align="center">性别</td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;${bmUser.base_sex_name}</b></font></td> 
    	<td width="12%" align="center">出生年月</td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;${bmUser.base_birthday}</b></font></td> 
    	<td width="12%" align="center">学历</td>
    	<td width="21%" align="center"><font color="blue"><b>&nbsp;${bmUser.edu_education_name}</b></font></td>
	</tr>
	<tr> 
    	<td align="center">政治面貌</td>
    	<td align="center"><font color="blue"><b>&nbsp;${bmUser.base_politics_name}</b></font></td>
    	<td align="center">人员涉密等级</td>
    	<td align="center"><font color="blue"><b>&nbsp;${resignUser.security_name}</b></font></td>
    	<td align="center">职务/职称 </td>
    	<td align="center"><font color="blue"><b>&nbsp;${bmUser.job_techpost}/${bmUser.job_techtitle}</b></font></td>
	</tr>
	<tr> 
    	<td align="center">现从事岗位 </td>
    	<td align="center"><font color="blue"><b>&nbsp;${resignUser.post_name}</b></font></td>  	
    	<td align="center">联系电话</td>
    	<td align="center"><font color="blue"><b>&nbsp;${bmUser.com_telephone}</b></font></td>
    	<td align="center">涉密资料总数</td>
    	<td align="center"><font color="blue"><b>&nbsp;${all_total}</b></font></td>
	</tr>	
	<tr>
		<td align="center">名下计算机数量</td>
    	<td align="center"><font color="blue"><b>&nbsp;${computer_num}</b></font></td>
    	<td align="center">名下设备数量</td>
    	<td align="center"><font color="blue"><b>&nbsp;${device_num}</b></font></td>
    	<td align="center">名下介质数量</td>
    	<td align="center"><font color="blue"><b>&nbsp;${media_num}</b></font></td>
	</tr>
</table>