---svn_revision:NAS集成刻录向普通刻录模式转换

if not exists (select 1 from SEC_OPER where OPER_CODE = '01040101')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01040101','提交刻录申请','admin','burn/addburnevent.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01040102')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01040102','查看申请记录','admin','burn/manageburnevent.action','_image/ico/default.gif','N','N');
go

update sec_role_oper set oper_code='01040101' where oper_code='01040103';
update sec_role_oper set oper_code='01040102' where oper_code='01040104';
go
 
delete from sec_oper where oper_code='01040103';
delete from sec_oper where oper_code='01040104';
go

if not exists (select 1 from SEC_OPER where OPER_CODE = '01040201')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01040201','待审批列表','admin','basic/manageaprvjob.action?type=BURN','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01040202')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01040202','查看审批记录','admin','basic/viewaprvjob.action?type=BURN','_image/ico/default.gif','N','N');
go

update sec_role_oper set oper_code='01040201' where oper_code='01040203';
update sec_role_oper set oper_code='01040202' where oper_code='01040204';
go
 
delete from sec_oper where oper_code='01040203';
delete from sec_oper where oper_code='01040204';
go

UPDATE SEC_ROLE SET ROLE_SPEC_KEY='',ROLE_DESC='在刻录现场，负责对刻录失败的任务进行标记' WHERE ROLE_ID='15';
go