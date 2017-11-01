---svn_revision:832 刻录与NAS集成

if not exists (select 1 from SEC_OPER where OPER_CODE = '01040103')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01040103','提交刻录申请','admin','burn/addnasburnevent.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01040104')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01040104','查看申请记录','admin','burn/managenasburnevent.action','_image/ico/default.gif','N','N');
go

update sec_role_oper set oper_code='01040103' where oper_code='01040101';
update sec_role_oper set oper_code='01040104' where oper_code='01040102';
go
 
delete from sec_oper where oper_code='01040101';
delete from sec_oper where oper_code='01040102';
go

if not exists (select 1 from SEC_OPER where OPER_CODE = '01040203')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01040203','待审批列表','admin','basic/manageaprvjob.action?type=BURN&file_src=nas','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01040204')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01040204','查看审批记录','admin','basic/viewaprvjob.action?type=BURN&file_src=nas','_image/ico/default.gif','N','N');
go

update sec_role_oper set oper_code='01040203' where oper_code='01040201';
update sec_role_oper set oper_code='01040204' where oper_code='01040202';
go
 
delete from sec_oper where oper_code='01040201';
delete from sec_oper where oper_code='01040202';
go

UPDATE SEC_ROLE SET ROLE_SPEC_KEY='BURNADMIN',ROLE_DESC='集中刻录，及对刻录失败的任务进行标记' WHERE ROLE_ID='15';
go