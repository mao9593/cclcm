<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<table border=0 cellspacing="0" cellpadding="0" >
					<tr>
						<td width="10%" align="center">计算机名称型号</td>
						<td width="23%" align="center" >&nbsp;${computer.computer_name}</td>
						<td width="10%" align="center">配置</td>
						<td width="23%" align="center" >&nbsp;${computer.computer_detail}</td>	
					</tr>
					<tr>
						<td align="center">责任人</td>
						<td align="center" >&nbsp;${computer.duty_user_name}</td>
						<td align="center">责任部门</td>
						<td align="center" >&nbsp;${computer.duty_dept_name}</td>
					</tr>
					<tr>
						<td align="center">保密编号</td>
						<td align="center" >&nbsp;${computer.conf_code}</td>
						<td align="center">密级</td>
						<td align="center" >&nbsp;${computer.seclv_name}</td>
						
					</tr>
					<tr>
						<td align="center">设备编号</td>
						<td align="center" >&nbsp;${computer.computer_code}</td>
						<td align="center">硬盘序列号</td>
						<td align="center" >&nbsp;${computer.hdisk_no}</td>
					</tr>
					<tr>
						<td align="center">输出端口号</td>
						<td align="center">&nbsp;${computer.output_point}</td>
						<td align="center">KEY号</td>
						<td align="center">&nbsp;${computer.key_code}</td>
					</tr>	
					<tr>
						<td align="center">IP地址</td>
						<td align="center">&nbsp;${computer.computer_ip}</td>
						<td align="center">MAC地址</td>
						<td align="center" >&nbsp;${computer.computer_mac}</td>
					</tr>
					<tr>
						<td align="center">存放区域</td>
						<td align="center" >&nbsp;${computer.storage_area}</td>
						<td align="center">存放位置</td>
						<td align="center" >&nbsp;${computer.storage_location}</td>
					</tr>	 	
				</table>