1.执行execute.sql中的sql语句，注意是否存在乱码
2.managesecrole.jsp文件覆盖到html/user/下
3.其他四个jsp文件覆盖到html/burn/下

-----------------------------------------------------
web version :	HDCCLCMWEB_V6.0.2.0(build 1567)
svn revision:	1567
update date:	2014-11-18 11:15

	刻录与NAS集成存在相关页面按钮、翻页无权限、无法下载附件等问题，对主干版本进行了修正。SVN为1551版本。
	代码在"主版本刻录与NAS集成更正_build1551"文件夹中。各文件路径如下:
	（1）html\basic： viewaprvjob.jsp、 manageaprvjob.jsp
	（2）html\burn： managenasburnevent.jsp
	（3）hdsec\web\project\basic\action： ViewEventDetailAction.java
	（4）hdsec\web\project\client\action： ViewPendingWorkAction.java
	（5）hdsec\web\project\client\service： ClientService.java、 ClientServiceImpl.java
	用户可根据需要登录超级用户赋予普通刻录申请审批、与NAS集成刻录申请审批权限。


-----------------------------------------------------
web version :	HDCCLCMWEB_V6.0.2.0(build 1605)
svn revision:	1605
update date:	2014-11-25 15:55

特车中参照主干更新sql(增加磁介质类型表，且初始化)
解决刻录与NAS集成审批后，不刷新无法下载附件问题

-----------------------------------------------------
web version :	HDCCLCMWEB_V6.0.2.0(build 1628)
svn revision:	1628
update date:	2014-12-3 9:50
	1.初始化时把控制台管理由打印模块移到公用模块，更新SQL
	2.修改查看台账详细，作业号不存在或错误时页面报错的问题（通用）
	3.SQL语句中在event_burn中增加字段cd_barcode（根据特车需求新增）
