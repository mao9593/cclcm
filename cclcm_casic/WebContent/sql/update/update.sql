USE [cclcm]
GO
--- svn_revision:582 20140728
---
---
--- Table: CONFIRM_RECORD ENTITY_TYPE字段约束里增加STORAGE
---
alter table CONFIRM_RECORD drop constraint CKC_ENTITY_TYPE_CONFIRM_;
alter table CONFIRM_RECORD add constraint CKC_ENTITY_TYPE_CONFIRM_ check (ENTITY_TYPE in ('PAPER','CD','DEVICE','STORAGE'));
go
---
--- Table: CONFIRM_RECORD CONFIRM_TYPE字段约束里增加RETRIEVE、STORAGE_BR、STORAGE_RT
---
alter table CONFIRM_RECORD drop constraint CKC_CONFIRM_TYPE_CONFIRM_;
alter table CONFIRM_RECORD add constraint CKC_CONFIRM_TYPE_CONFIRM_ check (CONFIRM_TYPE in ('TRANSFER','FILE','DEVICE_BR','DEVICE_RT','READ_BR','READ_RT','RETRIEVE','STORAGE_BR','STORAGE_RT'));
go
---
--- Table: CYCLE_RECORD ENTITY_TYPE字段约束里增加STORAGE
---
alter table CYCLE_RECORD drop constraint CKC_ENTITY_TYPE_CYCLE_RE;
alter table CYCLE_RECORD add constraint CKC_ENTITY_TYPE_CYCLE_RE check (ENTITY_TYPE in ('PAPER','CD','DEVICE','STORAGE'));
go
---
--- Table: SYS_MODULE 存储介质模块需要配置是否开启
--- 如果不存在对应记录，则INSERT数据，否则不做操作
---
if not exists (select 1 from SYS_MODULE where MODULE_CODE = 'STORAGE')
	INSERT INTO SYS_MODULE(MODULE_CODE,MODULE_NAME) VALUES ('STORAGE','存储介质功能模块');
go

---
--- Table: SEC_CONFIG 系统配置表(SEC_CONFIG)里添加记录
--- 如果不存在对应记录，则INSERT数据，否则不做操作
---
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'RETRIEVE_CONFIRM')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('RETRIEVE_CONFIRM','回收模块交接确认',null,'CONFIRM',0);
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'FILE_CONFIRM')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE) VALUES ('FILE_CONFIRM','归档模块交接确认',null,'CONFIRM');
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'TRANSFER_CONFIRM')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE) VALUES ('TRANSFER_CONFIRM','流转模块交接确认',null,'CONFIRM');
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'DEVICE_BR_CONFIRM')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE) VALUES ('DEVICE_BR_CONFIRM','磁介质借用模块交接确认',null,'CONFIRM');
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'DEVICE_RT_CONFIRM')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE) VALUES ('DEVICE_RT_CONFIRM','磁介质归还模块交接确认',null,'CONFIRM');
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'STORAGE_BR_CONFIRM')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('STORAGE_BR_CONFIRM','存储介质分配模块交接确认',null,'CONFIRM',0);
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'STORAGE_RT_CONFIRM')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('STORAGE_RT_CONFIRM','存储介质归还模块交接确认',null,'CONFIRM',0);
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'READ_BR_CONFIRM')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE) VALUES ('READ_BR_CONFIRM','部门载体借用模块交接确认',null,'CONFIRM');
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'READ_RT_CONFIRM')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE) VALUES ('READ_RT_CONFIRM','部门载体归还模块交接确认',null,'CONFIRM');
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'COPY_CONFIRM')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('COPY_CONFIRM','复印模块交接确认',null,'CONFIRM',0);
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'ENTER_CONFIRM')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('ENTER_CONFIRM','录入模块交接确认',null,'CONFIRM',0);
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'CLIENT_MSG')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('CLIENT_MSG','消息提醒开关配置',null,'SYSTEM',1);
go
---
--- 为系统管理员添加交接确认配置权限看看 
--- 如果不存在对应记录，则INSERT数据，否则不做操作。（配置权限注意关联多表时的判断查询语句）
---
if not exists (select 1 from SEC_OPER where OPER_CODE = '01020312')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020312','交接确认配置','admin','basic/configconfirm.action','_image/ico/default.gif','Y','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01020312')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('2','01020312');
go
----------------------------------------------------------------------------------------------------
--- svn_revision:638 20140730
---
---
--- 为系统管理员添加安装文件管理权限
---
if not exists (select 1 from SEC_OPER where OPER_CODE = '01020504')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020504','安装文件管理','admin','basic/uploadsetupfiles.action','_image/ico/default.gif','Y','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01020504')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('2','01020504');
go
---
--- 为刻录作业表增加是否需要审批的字段
---
if not exists (select 1 from syscolumns where id=object_id('EVENT_BURN') and name='NEED_PROCESS')
	alter table EVENT_BURN add NEED_PROCESS nvarchar(10)
go
----------------------------------------------------------------------------------------------------
--- svn_revision:633 标记失败加备注 20140731
---
if not exists (select 1 from syscolumns where id=object_id('entity_paper') and name='fail_remark')
	alter table entity_paper add fail_remark nvarchar(1024) null
go
if not exists (select 1 from syscolumns where id=object_id('entity_cd') and name='fail_remark')
	alter table entity_cd add fail_remark nvarchar(1024) null;
go
----------------------------------------------------------------------------------------------------
--- svn_revision:663 常用文档下载权限 20140804
--- 如果不存在对应记录，则INSERT数据，否则不做操作。（配置权限注意关联多表时的判断查询语句，同时注意现有角色才能为角色配置权限）
---
if not exists (select 1 from SEC_OPER where OPER_CODE = '010206')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('010206','常用文档下载','admin',NULL,NULL,'N','Y');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01020601')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020601','文档列表','admin','basic/downloadfile.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01020601')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('2','01020601');
go
----------------------------------------------------------------------------------------------------
--- svn_revision:664 查看系统信息 20140804
---
if not exists (select 1 from SEC_OPER where OPER_CODE = '01020112')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020112','查看系统信息','admin','basic/viewinfo.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01020112')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('2','01020112');
go


----------------------------------------------------------------------------------------------------
--- svn_revision:679 用户增加独立模式和本地打印的配置 20140805
---
if not exists (select 1 from syscolumns where id=object_id('sec_user') and name='PRINT_METHOD')
	alter table sec_user add PRINT_METHOD int not null default 0
go
if not exists (select 1 from syscolumns where id=object_id('sec_user') and name='PRINT_PERMISSION')
	alter table sec_user add PRINT_PERMISSION int not null default 0
go

---强制结束流程权限添加
if not exists (select 1 from SEC_OPER where OPER_CODE = '01020313')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020313','关闭异常流程','admin','basic/viewclosejob.action','_image/ico/default.gif','N','N');
GO 
 
---svn_revision:783 设置流程加用途
if not exists (select 1 from syscolumns where id=object_id('APPROVE_PROCESS') and name='usage_code')
	alter table APPROVE_PROCESS add usage_code nvarchar(1024) null;
GO
 
---svn_revision:803 自审批默认关闭 URL:basic/configselfapprove.action
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'SELF_APPROVE')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('SELF_APPROVE','自审批流程',null,'APPROVE',0);
GO

---svn_revision:887 管理员限定部门设置
if not exists (select 1 from syscolumns where id=object_id('SEC_DEPT_ADMIN') and name='ROLE_ID')
	alter table SEC_DEPT_ADMIN add ROLE_ID nvarchar(100) not null default '6';
	alter table SEC_DEPT_ADMIN drop constraint PK_SEC_DEPT_ADMIN;
	alter table SEC_DEPT_ADMIN add constraint PK_SEC_DEPT_ADMIN primary key (USER_IIDD, ROLE_ID, DEPT_ID);
go

---svn_revision:937 刻录申请加保密编号
if not exists (select 1 from syscolumns where id=object_id('event_burn') and name='conf_code')
	alter table event_burn add conf_code nvarchar(100) null;
GO

---光盘载体加保密编号
if not exists (select 1 from syscolumns where id=object_id('entity_cd') and name='conf_code')
	alter table entity_cd add conf_code nvarchar(100) null;
GO

---svn_revision:945 上传授权文件权限
if not exists (select 1 from SEC_OPER where OPER_CODE = '01020113')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020113','上传授权文件','admin','html/user/uploadpermissionfile.jsp','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01020113')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('2','01020113');
go

---打印纸张统计
if exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')
begin
	if not exists (select 1 from SEC_ROLE where ROLE_ID = '17')
		INSERT INTO SEC_ROLE(ROLE_ID,ROLE_NAME,SUBSYS_CODE,ROLE_DESC,ROLE_TYPE,ROLE_SPEC_KEY) VALUES('17','打印纸张统计员','admin','可以查看打印纸张统计情况',3,'');	
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01130905')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130905','纸张密级统计','admin','basic/viewpaperstaticbyseclv.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01130906')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130906','纸张颜色统计','admin','basic/viewpaperstaticbycolor.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01130907')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130907','纸张单双面统计','admin','basic/viewpaperstaticbydouble.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01130908')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130908','纸张类型统计','admin','basic/viewpaperstaticbysize.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01130909')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130909','纸张总统计','admin','basic/viewpaperstaticbyall.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '17' and A.OPER_CODE = '01130905')
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('17','01130905');
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '17' and A.OPER_CODE = '01130906')
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('17','01130906');
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '17' and A.OPER_CODE = '01130907')
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('17','01130907');
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '17' and A.OPER_CODE = '01130908')
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('17','01130908');
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '17' and A.OPER_CODE = '01130909')
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('17','01130909');
end
GO

---部门载体借用台帐
if exists (select 1 from SYS_MODULE where MODULE_CODE = 'BORROW')
begin
	if not exists (select 1 from SEC_OPER where OPER_CODE = '011104')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('011104','部门载体借用台帐','admin',NULL,NULL,'N','Y');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01110401') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110401','文件借用台帐','admin','borrow/viewpaperborrowledger.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01110402') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110402','光盘借用台帐','admin','borrow/viewcdborrowledger.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01110401') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110401');
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01110402') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN')
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110402');

end
go

---录入、复印是否流转交接的开关
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'COPY_CONFIRM') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'COPY')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('COPY_CONFIRM','复印模块交接确认',null,'CONFIRM',0);
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'ENTER_CONFIRM') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'LEADIN')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('ENTER_CONFIRM','录入模块交接确认',null,'CONFIRM',0);
GO

---录入一分四
if exists (select 1 from SYS_MODULE where MODULE_CODE = 'LEADIN')
begin
	delete from sec_role_oper where oper_code='01060101';
	delete from sec_oper where oper_code='01060101';
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01060104')and exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01060104','个人文件录入申请','admin','enter/addpersonalpaperenterevent.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01060105')and exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01060105','个人光盘录入申请','admin','enter/addpersonalcdenterevent.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01060106')and exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')and exists (select 1 from SYS_MODULE where MODULE_CODE = 'BORROW')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01060106','部门文件录入申请','admin','enter/adddeptpaperenterevent.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01060107')and exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN')and exists (select 1 from SYS_MODULE where MODULE_CODE = 'BORROW')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01060107','部门光盘录入申请','admin','enter/adddeptcdenterevent.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01060104')and exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01060104');
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01060105')and exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN')
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01060105');
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '6' and A.OPER_CODE = '01060106')and exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')and exists (select 1 from SYS_MODULE where MODULE_CODE = 'BORROW')
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('6','01060106');
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '6' and A.OPER_CODE = '01060107')and exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN')and exists (select 1 from SYS_MODULE where MODULE_CODE = 'BORROW')
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('6','01060107');
end
go

---管理员分部门
if not exists (select 1 from SEC_ROLE where ROLE_ID = '12')
	INSERT INTO SEC_ROLE(ROLE_ID,ROLE_NAME,SUBSYS_CODE,ROLE_DESC,ROLE_TYPE,ROLE_SPEC_KEY) VALUES('12','外发管理员','admin','负责涉密载体的外发处理',3,'');
if not exists (select 1 from SEC_ROLE where ROLE_ID = '13') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'COPY')
	INSERT INTO SEC_ROLE(ROLE_ID,ROLE_NAME,SUBSYS_CODE,ROLE_DESC,ROLE_TYPE,ROLE_SPEC_KEY) VALUES('13','复印管理员','admin','负责纸质涉密文件的复印',3,'');
if not exists (select 1 from SEC_ROLE where ROLE_ID = '14') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')
	INSERT INTO SEC_ROLE(ROLE_ID,ROLE_NAME,SUBSYS_CODE,ROLE_DESC,ROLE_TYPE,ROLE_SPEC_KEY) VALUES('14','打印管理员','admin','在打印现场，负责对打印失败的任务进行标记',3,'');
if not exists (select 1 from SEC_ROLE where ROLE_ID = '15') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN')
	INSERT INTO SEC_ROLE(ROLE_ID,ROLE_NAME,SUBSYS_CODE,ROLE_DESC,ROLE_TYPE,ROLE_SPEC_KEY) VALUES('15','刻录管理员','admin','在刻录现场，负责对刻录失败的任务进行标记',3,'');
if not exists (select 1 from SEC_ROLE where ROLE_ID = '16')
	INSERT INTO SEC_ROLE(ROLE_ID,ROLE_NAME,SUBSYS_CODE,ROLE_DESC,ROLE_TYPE,ROLE_SPEC_KEY) VALUES('16','销毁管理员','admin','负责涉密载体的销毁标记',3,'');
update sec_role set role_spec_key = '' where role_id in('5','6','7','8','9');
delete from sec_role_oper where role_id='5' and oper_code='01130201';
delete from sec_role_oper where role_id='5' and oper_code='01130202';
delete from sec_role_oper where role_id='5' and oper_code='01130401';
delete from sec_role_oper where role_id='5' and oper_code='01130402';
delete from sec_role_oper where role_id='5' and oper_code='01050301';
delete from sec_role_oper where role_id='5' and oper_code='01050302';
delete from sec_role_oper where role_id='5' and oper_code='01130601';
delete from sec_role_oper where role_id='5' and oper_code='01130903';
delete from sec_role_oper where role_id='5' and oper_code='01130501';
delete from sec_role_oper where role_id='5' and oper_code='01130904';
delete from sec_role_oper where role_id='5' and oper_code='01130302';
delete from sec_role_oper where role_id='5' and oper_code='01130303';
delete from sec_role_oper where role_id='5' and oper_code='01130102';
delete from sec_role_oper where role_id='5' and oper_code='01130103';
if not exists (select 1 from sec_oper where oper_code='01130101')
INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130101','文件回收','admin','ledger/viewpaper.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '5' and A.OPER_CODE = '01130101')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('5','01130101');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '5' and A.OPER_CODE = '01130301')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('5','01130301');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '12' and A.OPER_CODE = '01130201')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('12','01130201');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '12' and A.OPER_CODE = '01130202')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('12','01130202');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '12' and A.OPER_CODE = '01130401')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('12','01130401');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '12' and A.OPER_CODE = '01130402')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('12','01130402');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '12' and A.OPER_CODE = '01020110')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('12','01020110');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '13' and A.OPER_CODE = '01050301') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'COPY')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('13','01050301');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '13' and A.OPER_CODE = '01050302') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'COPY')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('13','01050302');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '13' and A.OPER_CODE = '01020110') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'COPY')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('13','01020110');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '14' and A.OPER_CODE = '01020110') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('14','01020110');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '14' and A.OPER_CODE = '01130601') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('14','01130601');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '14' and A.OPER_CODE = '01130903') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('14','01130903');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '15' and A.OPER_CODE = '01020110') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('15','01020110');        
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '15' and A.OPER_CODE = '01130501') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('15','01130501');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '15' and A.OPER_CODE = '01130904') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('15','01130904');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '16' and A.OPER_CODE = '01130302')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('16','01130302');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '16' and A.OPER_CODE = '01130303')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('16','01130303');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '16' and A.OPER_CODE = '01130102')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('16','01130102');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '16' and A.OPER_CODE = '01130103')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('16','01130103');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '16' and A.OPER_CODE = '01020110')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('16','01020110');

---打印申请加关键字
if not exists (select 1 from syscolumns where id=object_id('event_print') and name='keyword_content')
	alter table event_print add keyword_content nvarchar(1024) null;
GO
--- 为打印作业表增加知悉范围字段file_scope 定密依据字段seclv_accord 保密期限字段secret_time
---
if not exists (select 1 from syscolumns where id=object_id('EVENT_PRINT') and name='FILE_SCOPE')
	alter table EVENT_PRINT add FILE_SCOPE nvarchar(1024) null
go
if not exists (select 1 from syscolumns where id=object_id('EVENT_PRINT') and name='SECLV_ACCORD')
	alter table EVENT_PRINT add SECLV_ACCORD nvarchar(1024) null
go
if not exists (select 1 from syscolumns where id=object_id('EVENT_PRINT') and name='SECRET_TIME')
	alter table EVENT_PRINT add SECRET_TIME nvarchar(1024) null
go

if not exists (select 1 from SEC_ROLE where ROLE_ID = '110')
	INSERT INTO SEC_ROLE(ROLE_ID,ROLE_NAME,SUBSYS_CODE,ROLE_DESC,ROLE_TYPE,ROLE_SPEC_KEY) VALUES('110','定密管理员','admin','负责涉密打印申请的审批',3,'')
		
---修改关键字表
if exists (select 1 from syscolumns where id=object_id('sys_keyword') and name='is_sealed')
begin
drop table SYS_KEYWORD
create table SYS_KEYWORD (
   KEYWORD_CODE         nvarchar(64)         not null,
   KEYWORD_CONTENT      nvarchar(50)         null,
   KEYWORD_DETAIL       nvarchar(128)        null,
   SECLV_CODE           int                  null,
   ALARM_SECLV_CODE     int                  null,
   UPDATE_TIME          datetime             null,
   EXT_CODE             nvarchar(128)        null,
   constraint PK_SYS_KEYWORD primary key (KEYWORD_CODE)
)
end
go

---系统管理员关键字管理页面权限
if not exists (select 1 from SEC_OPER where OPER_CODE = '01020114')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020114','关键字管理','admin','basic/managekeyword.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01020114')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('2','01020114');
go	

---系统配置表里加关键字开关控制字段
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'KEYWORD') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('KEYWORD','关键字检测',null,'PRINT_KW',0);
GO

---复印分留用、归档、外发，申请表加CYCLE_TYPE字段
if not exists (select 1 from syscolumns where id=object_id('event_copy') and name='CYCLE_TYPE')
	alter table event_copy add CYCLE_TYPE nvarchar(64) not null default 'REMAIN';
go

----------------------------------------------------------------------------------------------------
---解决老版sql脚本里，event_import表缺少scope_dept_id字段的问题
---
if not exists (select 1 from syscolumns where id=object_id('event_import') and name='SCOPE_DEPT_ID')
	alter table event_import add SCOPE_DEPT_ID  nvarchar(100) null;
go

---纸质、光盘载体增加回收提醒时间字段，录入作业表增加留用状态（长期、短期）字段
if not exists (select 1 from syscolumns where id=object_id('entity_paper') and name='EXPIRE_TIME')
	alter table entity_paper add EXPIRE_TIME datetime null;
go
if not exists (select 1 from syscolumns where id=object_id('entity_cd') and name='EXPIRE_TIME')
	alter table entity_cd add EXPIRE_TIME datetime null;
go
if not exists (select 1 from syscolumns where id=object_id('event_import') and name='PERIOD')
	alter table event_import add PERIOD char(1) not null default 'S';
go

---系统配置表里加载体短期留用时间、回收提醒时间控制字段
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'IMPORT_SHORT_DAYS')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE) VALUES ('IMPORT_SHORT_DAYS','载体短期留用时间','7','REMIND_TIME');
GO
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'REMIND_MSG_DAYS')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE) VALUES ('REMIND_MSG_DAYS','载体回收消息提醒周期','2','REMIND_TIME');
GO

if not exists (select 1 from SEC_OPER where OPER_CODE = '01020314')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020314','闭环提醒设置','admin','basic/configremindmsg.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01020314')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('2','01020314');
go	

---SEC_USER中添加RANK字段用于排序
if not exists (select 1 from syscolumns where id=object_id('sec_user') and name='RANK')
	alter table sec_user add RANK  int not null default 100;
go

---EVENT_IMPORT中添加FILE_KIND字段用于区分普通文件、科研工作手册
if not exists (select 1 from syscolumns where id=object_id('event_import') and name='FILE_KIND')
	alter table event_import add FILE_KIND  nvarchar(64) not null default 'NORMAL';
go

---个人/部门录入光盘/文件在一个页面内实现批量，去掉批量提交按钮
if exists (select 1 from SYS_MODULE where MODULE_CODE = 'LEADIN')
begin
if exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01060102')
	delete from SEC_ROLE_OPER where ROLE_ID='11' and OPER_CODE='01060102';
if exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '6' and A.OPER_CODE = '01060102')
	delete from SEC_ROLE_OPER where ROLE_ID='6' and OPER_CODE='01060102';
if exists (select 1 from SEC_OPER where OPER_CODE = '01060102')
	delete from sec_oper where oper_code='01060102';
end
go

---打印、刻录申请时分短期和长期
if not exists (select 1 from syscolumns where id=object_id('EVENT_PRINT') and name='PERIOD')
	alter table EVENT_PRINT add PERIOD char(1) not null default 'S';
if not exists (select 1 from syscolumns where id=object_id('EVENT_BURN') and name='PERIOD')
	alter table EVENT_BURN add PERIOD char(1) not null default 'S';
go

---复印作业表增加留用状态（长期、短期）字段
if not exists (select 1 from syscolumns where id=object_id('event_copy') and name='PERIOD')
	alter table event_copy add PERIOD char(1) not null default 'S';
go

---SEC_DEPT中添加DEPT_RANK字段用于排序,同时置初值为DEPT_CS
if not exists (select 1 from syscolumns where id=object_id('sec_dept') and name='DEPT_RANK')
	alter table sec_dept add DEPT_RANK nvarchar(100) not null default '0';
go
if not exists (select 1 from sec_dept where dept_rank != '0')
	update sec_dept set dept_rank=dept_cs;
go

---添加载体归属转换EVENT_CHANGE表
if not exists (select 1 from  sysobjects where  id = object_id('EVENT_CHANGE') and type = 'U')
create table EVENT_CHANGE (
   ID                   int                  identity(1,1),
   EVENT_CODE           nvarchar(64)         null,
   USER_IIDD            nvarchar(100)        null,
   DEPT_ID              nvarchar(100)        null,
   SECLV_CODE           int                  null,
   ENTITY_TYPE          nvarchar(64)         null,
   CHANGE_TYPE          nvarchar(64)         null,
   BARCODE              nvarchar(64)         null,
   USAGE_CODE           nvarchar(64)         null,
   SUMM                 nvarchar(1024)       null,
   SCOPE_DEPT_ID        nvarchar(64)         null,
   APPLY_TIME           datetime             null,
   FINISH_TIME          datetime             null,
   CHANGE_STATUS        int                  null,
   JOB_CODE             nvarchar(100)        null,
   HIS_JOB_CODE         nvarchar(1024)       null,
   constraint PK_EVENT_CHANGE  primary key (ID)
)
go

--- Table: SYS_MODULE 载体归属转换模块需要配置是否开启
if not exists (select 1 from SYS_MODULE where MODULE_CODE = 'CHANGE')
	INSERT INTO SYS_MODULE(MODULE_CODE,MODULE_NAME) VALUES ('CHANGE','载体归属转换模块');
go

--- Table: CYCLE_RECORD OPER字段约束里增加个人载体变更为部门载体'CHANGETODEPT',部门载体变更为个人载体'CHANGETOPERSON'
if exists (select 1 from sysobjects where id=object_id('CKC_OPER_CYCLE_RE'))
	alter table CYCLE_RECORD drop constraint CKC_OPER_CYCLE_RE;
---alter table CYCLE_RECORD add constraint CKC_OPER_CYCLE_RE check (OPER in ('PRINT','LEADIN','COPY','BURN','REPRINT','REBURN','TRANSIN','TRANSOUT','SEND','FILE','RETRIEVE','DESTORY','RECV','BORROW','RETURN','REJECT','RESET','REPAIR','CHANGETODEPT','CHANGETOPERSON'));
go

--- Table: CLIENT_MSG OPER_TYPE字段约束里增加CHANGE
alter table CLIENT_MSG drop constraint CKC_OPER_TYPE_CLIENT_M;
---alter table CLIENT_MSG add constraint CKC_OPER_TYPE_CLIENT_M check (OPER_TYPE in ('PRINT','BURN','COPY','LEADIN','DEVICE','TRANSFER','BORROW','SEND_PAPER','FILE_PAPER','DESTROY_PAPER','DELAY_PAPER','SEND_CD','FILE_CD','DESTROY_CD','DELAY_CD','DESTROY_DEVICE','CHANGE'));
go

---添加载体归属转换模块
if exists (select 1 from SYS_MODULE where MODULE_CODE = 'BORROW')
begin
	if not exists (select 1 from SEC_OPER where OPER_CODE = '0115')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('0115','载体归属转换管理','admin',NULL,NULL,'N','Y');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '011501')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('011501','归属转换申请','admin',NULL,NULL,'N','Y');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01150101') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01150101','个人转部门文件','admin','change/viewpersonalpaperledger.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01150102') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01150102','部门转个人文件','admin','change/viewdeptpaperledger.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01150103') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01150103','个人转部门光盘','admin','change/viewpersonalcdledger.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01150104') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01150104','部门转个人光盘','admin','change/viewdeptcdledger.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01150105')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01150105','查看申请记录','admin','change/managechangejob.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '011502')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('011502','归属转换审批','admin',NULL,NULL,'N','Y');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01150201')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01150201','待审批列表','admin','change/managechangeaprvjob.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01150202')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01150202','查看审批记录','admin','change/viewchangeaprvjob.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '011503')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('011503','归属转换管理','admin',NULL,NULL,'N','Y');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01150301')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01150301','载体归属转换','admin','change/managechangeeventlist.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '6' and A.OPER_CODE = '01150101') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('6','01150101'); 
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '6' and A.OPER_CODE = '01150102') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('6','01150102'); 
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '6' and A.OPER_CODE = '01150103') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN')
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('6','01150103'); 
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '6' and A.OPER_CODE = '01150104') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN')
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('6','01150104'); 
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '6' and A.OPER_CODE = '01150105') 
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('6','01150105'); 
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '10' and A.OPER_CODE = '01150201') 
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('10','01150201');
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '10' and A.OPER_CODE = '01150202') 
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('10','01150202');
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '6' and A.OPER_CODE = '01150301') 
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('6','01150301');
end
go

---将用户管理、机构管理、角色管理由系统管理员转换到安全管理员下
if exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '3' and A.OPER_CODE = '01020101')
	DELETE FROM SEC_ROLE_OPER WHERE ROLE_ID = '3' AND OPER_CODE = '01020101';
if exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01020102')
	DELETE FROM SEC_ROLE_OPER WHERE ROLE_ID = '2' AND OPER_CODE = '01020102';
if exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01020103')
	DELETE FROM SEC_ROLE_OPER WHERE ROLE_ID = '2' AND OPER_CODE = '01020103';
if exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01020106')
	DELETE FROM SEC_ROLE_OPER WHERE ROLE_ID = '2' AND OPER_CODE = '01020106';

if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '3' and A.OPER_CODE = '01020102')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('3','01020102');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '3' and A.OPER_CODE = '01020103')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('3','01020103');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '3' and A.OPER_CODE = '01020106')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('3','01020106');
go

--- 用途表SYS_USAGE里加MODULE_CODE字段
if not exists (select 1 from syscolumns where id=object_id('SYS_USAGE') and name='MODULE_CODE')
	alter table SYS_USAGE add MODULE_CODE nvarchar(1024) null
go
--- 用途表SYS_USAGE里加TYPE字段
---if not exists (select 1 from syscolumns where id=object_id('SYS_USAGE') and name='TYPE')
---	alter table SYS_USAGE add TYPE char(1) null default 'Y'
---go

---复印作业表增加复印类型（内部、外来文件）字段
if not exists (select 1 from syscolumns where id=object_id('event_copy') and name='COPY_TYPE')
	alter table event_copy add COPY_TYPE nvarchar(64) not null default 'internal';
go

--- Table: SYS_MODULE 外来文件复印功能模块需要配置是否开启
if not exists (select 1 from SYS_MODULE where MODULE_CODE = 'OUTCOPY')
	INSERT INTO SYS_MODULE(MODULE_CODE,MODULE_NAME) VALUES ('OUTCOPY','外来文件复印功能模块');
go

--- Table: CLIENT_MSG OPER_TYPE字段约束里增加OUTCOPY
---alter table CLIENT_MSG drop constraint CKC_OPER_TYPE_CLIENT_M;
---alter table CLIENT_MSG add constraint CKC_OPER_TYPE_CLIENT_M check (OPER_TYPE in ('PRINT','BURN','COPY','LEADIN','DEVICE','TRANSFER','BORROW','SEND_PAPER','FILE_PAPER','DESTROY_PAPER','DELAY_PAPER','SEND_CD','FILE_CD','DESTROY_CD','DELAY_CD','DESTROY_DEVICE','CHANGE','OUTCOPY'));
go

--- Table: ENTITY_PAPER CREATE_TYPE字段约束里增加OUTCOPY
alter table ENTITY_PAPER drop constraint CKC_CREATE_TYPE_ENTITY_P;
alter table ENTITY_PAPER add constraint CKC_CREATE_TYPE_ENTITY_P check (CREATE_TYPE in ('PRINT','COPY','LEADIN','OUTCOPY'));
go

--- 添加外来文件复印模块权限
---if exists (select 1 from SYS_MODULE where MODULE_CODE = 'OUTCOPY')
---begin
---	if not exists (select 1 from SEC_OPER where OPER_NAME = '内部复印申请')
---		UPDATE SEC_OPER SET OPER_NAME='内部复印申请' WHERE OPER_CODE='010501';
---	if not exists (select 1 from SEC_OPER where OPER_NAME = '内部复印审批')
---		UPDATE SEC_OPER SET OPER_NAME='内部复印审批' WHERE OPER_CODE='010502';
---	if not exists (select 1 from SEC_OPER where OPER_NAME = '内部复印管理')
---		UPDATE SEC_OPER SET OPER_NAME='内部复印管理' WHERE OPER_CODE='010503';
---	if not exists (select 1 from SEC_OPER where OPER_CODE = '010504')
---		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('010504','外来复印申请','admin',NULL,NULL,'N','Y');
---	if not exists (select 1 from SEC_OPER where OPER_CODE = '01050401')
---		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01050401','提交复印申请','admin','copy/addcopyeventbyenter.action','_image/ico/default.gif','N','N');
---	if not exists (select 1 from SEC_OPER where OPER_CODE = '01050402')
---		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01050402','查看申请记录','admin','copy/managecopyjobbyenter.action','_image/ico/default.gif','N','N');
---	if not exists (select 1 from SEC_OPER where OPER_CODE = '010505')
---		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('010505','外来复印审批','admin',NULL,NULL,'N','Y');
---	if not exists (select 1 from SEC_OPER where OPER_CODE = '01050501')
---		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01050501','待审批列表','admin','copy/managecopyaprvjobbyenter.action','_image/ico/default.gif','N','N');
---	if not exists (select 1 from SEC_OPER where OPER_CODE = '01050502')
---		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01050502','查看审批记录','admin','copy/viewcopyaprvjobbyenter.action','_image/ico/default.gif','N','N');
---	if not exists (select 1 from SEC_OPER where OPER_CODE = '010506')
---		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('010506','外来复印管理','admin',NULL,NULL,'N','Y');
---	if not exists (select 1 from SEC_OPER where OPER_CODE = '01050601')
---		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01050601','复印申请列表','admin','copy/managecopyeventlistbyenter.action','_image/ico/default.gif','N','N');
---	if not exists (select 1 from SEC_OPER where OPER_CODE = '01050602')
---		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01050602','补打条码','admin','copy/viewcopypaperledgerbyenter.action','_image/ico/default.gif','N','N');
---	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '10' and A.OPER_CODE = '01050501') 
---		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('10','01050501'); 
---	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '10' and A.OPER_CODE = '01050502') 
---		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('10','01050502');
---	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01050401') 
---		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01050401');
---	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01050402') 
---		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01050402');
---	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '13' and A.OPER_CODE = '01050601') 
---		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('13','01050601');
---	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '13' and A.OPER_CODE = '01050602') 
---		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('13','01050602');
---end
---go


--- 录入申请表EVENT_IMPORT中增加录入文件份数ENTER_NUM字段
if not exists (select 1 from syscolumns where id=object_id('EVENT_IMPORT') and name='ENTER_NUM')
	alter table EVENT_IMPORT add ENTER_NUM int not null default 1;
go

--- 已录入文件、光盘列表更改转向地址
if exists (select 1 from sec_oper where oper_code = '01060401')
	update sec_oper set web_url = 'enter/enterpaperhistory.action' WHERE oper_code='01060401';
go
if exists (select 1 from sec_oper where oper_code = '01060402')
	update sec_oper set web_url = 'enter/entercdhistory.action' WHERE oper_code='01060402';
go

--Table:sec_oper sec_role_oper系统管理员增加一级目录排序管理功能
if not exists (select 1 from syscolumns where id=object_id('SEC_OPER') and name='DIR_RANK')
	alter table SEC_OPER add DIR_RANK int not null default 10;
if not exists (select 1 from sec_oper where oper_code = '01020115')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020115','一级目录管理', 'admin','user/managefirstdir.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01020115')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('2','01020115');
go

--- Table: SEC_CONFIG 系统配置表里添加记录
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'SEND_REJECT_STATUS')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE) VALUES ('SEND_REJECT_STATUS','外发拒收后载体状态','0','SEND');
go

--- Table: CYCLE_RECORD OPER字段约束里增加外发拒收'SENDREJECT',归档拒收'FILEREJECT'
---alter table CYCLE_RECORD drop constraint CKC_OPER_CYCLE_RE;
---alter table CYCLE_RECORD add constraint CKC_OPER_CYCLE_RE check (OPER in ('PRINT','LEADIN','COPY','BURN','REPRINT','REBURN','TRANSIN','TRANSOUT','SEND','FILE','RETRIEVE','DESTORY','RECV','BORROW','RETURN','REJECT','RESET','REPAIR','CHANGETODEPT','CHANGETOPERSON','SENDREJECT','FILEREJECT'));
---go

---Table: sec_oper/sec_role_oper 系统管理员增加上传常用文档的功能
if not exists (select 1 from SEC_OPER where OPER_CODE = '01020602')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020602','上传文档','admin','basic/uploadfile.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01020602') 
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('2','01020602');

---扫描录入
if exists (select 1 from SYS_MODULE where MODULE_CODE = 'LEADIN')
begin
	if not exists (select 1 from SEC_ROLE where ROLE_ID = '19')
		INSERT INTO SEC_ROLE(ROLE_ID,ROLE_NAME,SUBSYS_CODE,ROLE_DESC,ROLE_TYPE,ROLE_SPEC_KEY) VALUES('19','扫描录入管理员','admin','负责外来文件的扫描录入',3,'');	
	if not exists (select 1 from SEC_OPER where OPER_CODE = '010605')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('010605','扫描录入申请','admin',null,null,'N','Y');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01060501')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01060501','申请扫描录入','admin','enter/addscanevent.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01060502')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01060502','扫描申请记录','admin','enter/manageenterlistjob.action?entertype=SCAN','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '010606')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('010606','扫描录入管理','admin',null,null,'N','Y');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01060601')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01060601','扫描录入管理','admin','enter/managescanlist.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01060501')
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01060501');
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01060502')
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01060502');
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '19' and A.OPER_CODE = '01060601')
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('19','01060601');
end
GO

---添加磁介质类型MED_TYPE表
if not exists (select 1 from  sysobjects where  id = object_id('MED_TYPE') and type = 'U')
create table MED_TYPE (
   ID           int		             not null,
   TYPENAME     nvarchar(1024)       not null,
   CONTENT      nvarchar(1024)       null,
   constraint PK_MED_TYPE  primary key (ID)
)
go

--- Table: MED_TYPE 磁介质类型表里添加默认类型
if exists (select 1 from SYS_MODULE where MODULE_CODE = 'DEVICE')
begin
if not exists (select 1 from MED_TYPE where ID = 1)
	INSERT INTO MED_TYPE(ID,TYPENAME) VALUES (1,'U盘');
if not exists (select 1 from MED_TYPE where ID = 2)
	INSERT INTO MED_TYPE(ID,TYPENAME) VALUES (2,'移动硬盘');
if not exists (select 1 from MED_TYPE where ID = 3)
	INSERT INTO MED_TYPE(ID,TYPENAME) VALUES (3,'便携式计算机');
if not exists (select 1 from MED_TYPE where ID = 4)
	INSERT INTO MED_TYPE(ID,TYPENAME) VALUES (4,'照相机');
if not exists (select 1 from MED_TYPE where ID = 5)
	INSERT INTO MED_TYPE(ID,TYPENAME) VALUES (5,'录像机');
if not exists (select 1 from MED_TYPE where ID = 6)
	INSERT INTO MED_TYPE(ID,TYPENAME) VALUES (6,'录音笔');
if not exists (select 1 from MED_TYPE where ID = 8)
	INSERT INTO MED_TYPE(ID,TYPENAME) VALUES (8,'软盘');
if not exists (select 1 from MED_TYPE where ID = 9)
	INSERT INTO MED_TYPE(ID,TYPENAME) VALUES (9,'磁带');
if not exists (select 1 from MED_TYPE where ID = 10)
	INSERT INTO MED_TYPE(ID,TYPENAME) VALUES (10,'录像带');
if not exists (select 1 from MED_TYPE where ID = 11)
	INSERT INTO MED_TYPE(ID,TYPENAME) VALUES (11,'录音带');
if not exists (select 1 from MED_TYPE where ID = 12)
	INSERT INTO MED_TYPE(ID,TYPENAME) VALUES (12,'移动光驱');
if not exists (select 1 from MED_TYPE where ID = 13)
	INSERT INTO MED_TYPE(ID,TYPENAME) VALUES (13,'红黑电源');
if not exists (select 1 from MED_TYPE where ID = 14)
	INSERT INTO MED_TYPE(ID,TYPENAME) VALUES (14,'安全设备');
if not exists (select 1 from MED_TYPE where ID = 15)
	INSERT INTO MED_TYPE(ID,TYPENAME) VALUES (15,'多功能导入装置');
if not exists (select 1 from MED_TYPE where ID = 16)
	INSERT INTO MED_TYPE(ID,TYPENAME) VALUES (16,'硬盘');
end
go

---刻录申请作业表增加光盘条码字段(根据特车需求新加)
if not exists (select 1 from syscolumns where id=object_id('event_burn') and name='cd_barcode')
	alter table event_burn add cd_barcode nvarchar(64) null;
go

---密级表增加别名字段(根据31所需求新加)
if not exists (select 1 from syscolumns where id=object_id('sec_user_seclv') and name='OTHERNAME')
	alter table sec_user_seclv add OTHERNAME nvarchar(100) null;
go
---增加拼图打印管理员为拼图补打并粘贴条码
if not exists (select 1 from SEC_ROLE where ROLE_ID = '20')
	INSERT INTO SEC_ROLE(ROLE_ID,ROLE_NAME,SUBSYS_CODE,ROLE_DESC,ROLE_TYPE,ROLE_SPEC_KEY) VALUES ('20','拼图管理员','admin','为拼图打印的子图补打并粘贴条码',3,'');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01130602')	
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130602','补打拼图条码','admin','ledger/viewgraphpaper.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '20' and A.OPER_CODE = '01130602')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('20','01130602');

--- 为安全保密管理员增加设置默认密码功能
if not exists (select 1 from SEC_OPER where OPER_CODE = '01020116')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020116','默认密码设置','admin','basic/defaultpassword.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '3' and A.OPER_CODE = '01020116')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('3','01020116');
go

---默认密码设置
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'DEFAULT_PASSWORD')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE) VALUES ('DEFAULT_PASSWORD','默认密码设置','12345678a@','DEFAULT_PASSWORD');
go

--- 文件总台账、光盘总台账更改转向地址
if exists (select 1 from sec_oper where oper_code = '01130901')
	update sec_oper set web_url = 'ledger/managepaperledger.action' WHERE oper_code='01130901';
go
if exists (select 1 from sec_oper where oper_code = '01130902')
	update sec_oper set web_url = 'ledger/manageledger.action' WHERE oper_code='01130902';
go

---文件和光盘闭环（销毁、归档、外发）申请后，查询闭环申请记录可以查看到责任人已经变更的记录
---禁用以前的“处理申请记录”入口
delete from sec_role_oper where oper_code='01110102' or oper_code='01110202';
---添加文件闭环申请的新入口
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110103') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110103','销毁申请记录','admin','ledger/destroypaperapply.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110104') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110104','外发申请记录','admin','ledger/sendpaperapply.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110105') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110105','归档申请记录','admin','ledger/filepaperapply.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110106') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110106','延期申请记录','admin','ledger/delaypaperapply.action','_image/ico/default.gif','N','N');	
if not exists (select 1 from SEC_ROLE_OPER where ROLE_ID = '11' and OPER_CODE = '01110103') 
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110103');
if not exists (select 1 from SEC_ROLE_OPER where ROLE_ID = '11' and OPER_CODE = '01110104')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110104');
if not exists (select 1 from SEC_ROLE_OPER where ROLE_ID = '11' and OPER_CODE = '01110105')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110105');
if not exists (select 1 from SEC_ROLE_OPER where ROLE_ID = '11' and OPER_CODE = '01110106')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110106');

---添加光盘闭环申请的新入口
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110203') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110203','销毁申请记录','admin','ledger/destroycdapply.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110204') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110204','外发申请记录','admin','ledger/sendcdapply.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110205') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110205','归档申请记录','admin','ledger/filecdapply.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110206') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110206','延期申请记录','admin','ledger/delaycdapply.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_ROLE_OPER where ROLE_ID = '11' and OPER_CODE = '01110203') 
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110203');
if not exists (select 1 from SEC_ROLE_OPER where ROLE_ID = '11' and OPER_CODE = '01110204')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110204');
if not exists (select 1 from SEC_ROLE_OPER where ROLE_ID = '11' and OPER_CODE = '01110205')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110205');
if not exists (select 1 from SEC_ROLE_OPER where ROLE_ID = '11' and OPER_CODE = '01110206')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110206');

---增加送销表
if not exists (select 1 from  sysobjects where  id = object_id('EVENT_SENDDESTROY') and type = 'U')
	create table EVENT_SENDDESTROY (
	   ID                   int                  identity(1,1),
	   EVENT_CODE           nvarchar(64)         null,
	   USER_IIDD            nvarchar(100)        null,
	   DEPT_ID              nvarchar(100)        null,
	   SECLV_CODE           int                  null,
	   ENTITY_TYPE          nvarchar(64)         null,
	   BARCODE              nvarchar(64)         null,
	   PROJECT_CODE         nvarchar(64)         null,
	   USAGE_CODE           nvarchar(64)         null,
	   SUMM                 nvarchar(1024)       null,
	   APPLY_TIME           datetime             null,
	   FINISH_TIME          datetime             null,
	   SENDDESTROY_STATUS   int               null,
	   JOB_CODE             nvarchar(100)        null,
	   HIS_JOB_CODE         nvarchar(1024)       null,
	   constraint PK_EVENT_SENDDESTROY primary key (ID)
	)
go

---去掉文件载体表状态约束
alter table ENTITY_PAPER drop constraint CKC_PAPER_STATE_ENTITY_P;
go

---去掉光盘载体表状态约束
alter table ENTITY_CD drop constraint CKC_CD_STATE_ENTITY_C;
go

---去掉消息表操作约束
alter table CLIENT_MSG drop constraint CKC_OPER_TYPE_CLIENT_M;
go

---增加文件送销操作与权限
if exists (select 1 from SEC_OPER where OPER_CODE = '01130102')
	begin
		DELETE FROM SEC_ROLE_OPER WHERE OPER_CODE = '01130102';
		delete from sec_oper where oper_code='01130102';
	end
if exists (select 1 from SEC_OPER where OPER_CODE = '01130103')
	begin
		DELETE FROM SEC_ROLE_OPER WHERE OPER_CODE = '01130103';
		delete from sec_oper where oper_code='01130103';
	end
if not exists (select 1 from SEC_OPER where OPER_CODE = '01130106')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130106','文件销毁','admin','ledger/viewretrievedpaper.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01130107')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130107','已销毁列表','admin','ledger/viewdestroypaper.action','_image/ico/default.gif','N','N');

if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '16' and A.OPER_CODE = '01130106') 
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('16','01130106');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '16' and A.OPER_CODE = '01130107') 
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('16','01130107');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '10' and A.OPER_CODE = '01131003') 
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('10','01131003');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '10' and A.OPER_CODE = '01131004') 
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('10','01131004');
go	


---增加光盘送销权限
if exists (select 1 from SEC_OPER where OPER_CODE = '01130302')
	begin
		DELETE FROM SEC_ROLE_OPER WHERE OPER_CODE = '01130302';
		delete from sec_oper where oper_code='01130302';
	end
if exists (select 1 from SEC_OPER where OPER_CODE = '01130303')
	begin
		DELETE FROM SEC_ROLE_OPER WHERE OPER_CODE = '01130303';
		delete from sec_oper where oper_code='01130303';
	end
if not exists (select 1 from SEC_OPER where OPER_CODE = '01130306')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130306','光盘销毁','admin','ledger/viewretrievedcd.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01130307')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130307','已销毁列表','admin','ledger/viewdestroycd.action','_image/ico/default.gif','N','N');
		
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '16' and A.OPER_CODE = '01130306') 
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('16','01130306');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '16' and A.OPER_CODE = '01130307') 
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('16','01130307');

if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '10' and A.OPER_CODE = '01131103') 
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('10','01131103');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '10' and A.OPER_CODE = '01131104') 
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('10','01131104');
go		

--- 为刻录作业表增加"喷盘模"相关参数的字段 
---
if not exists (select 1 from syscolumns where id=object_id('EVENT_BURN') and name='model_num')
	alter table EVENT_BURN add model_num nvarchar(100);
if not exists (select 1 from syscolumns where id=object_id('EVENT_BURN') and name='post_num')
	alter table EVENT_BURN add post_num nvarchar(100);
if not exists (select 1 from syscolumns where id=object_id('EVENT_BURN') and name='record_num')
	alter table EVENT_BURN add record_num nvarchar(100);
if not exists (select 1 from syscolumns where id=object_id('EVENT_BURN') and name='disk_name')
	alter table EVENT_BURN add disk_name nvarchar(100);
if not exists (select 1 from syscolumns where id=object_id('EVENT_BURN') and name='base_mark')
	alter table EVENT_BURN add base_mark nvarchar(100);
if not exists (select 1 from syscolumns where id=object_id('EVENT_BURN') and name='step')
	alter table EVENT_BURN add step nvarchar(100);
if not exists (select 1 from syscolumns where id=object_id('EVENT_BURN') and name='purpose')
	alter table EVENT_BURN add purpose nvarchar(100);
go

---在EVENT_PRINT表中，修改PRINT_TYPE字段的约束条件
alter table EVENT_PRINT drop constraint [CKC_PRINT_TYPE_EVENT_PR];
go
alter table EVENT_PRINT add constraint [CKC_PRINT_TYPE_EVENT_PR] check([PRINT_TYPE] in (1,2,3,4,5));
go

---设置三员登录IP
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'SECADMIN_LOGIN_IP')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('SECADMIN_LOGIN_IP','secadmin设定IP',null,'THREE_ADMIN_LOGIN_IP',0);
GO
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'SYSADMIN_LOGIN_IP')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('SYSADMIN_LOGIN_IP','sysadmin设定IP',null,'THREE_ADMIN_LOGIN_IP',0);
GO
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'AUDADMIN_LOGIN_IP')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('AUDADMIN_LOGIN_IP','audadmin设定IP',null,'THREE_ADMIN_LOGIN_IP',0);
GO

if not exists (select 1 from SEC_OPER where OPER_CODE = '01020117')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020117','设置三员登录IP','admin','basic/threeadminloginip.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01020117')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('2','01020117');
go
--- 为管理员添加自审批设置菜单
if not exists (select 1 from SEC_OPER where OPER_CODE = '01020118')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020118','自审批管理','admin','basic/configselfapprove.action','_image/ico/default.gif','Y','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '1' and A.OPER_CODE = '01020118')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('1','01020118');
go
	
---延期留用期限(纸质和光盘)
if not exists (select 1 from syscolumns where id=object_id('entity_paper') and name='delay_days')
	alter table entity_paper add delay_days int  null;
go
if not exists (select 1 from syscolumns where id=object_id('entity_cd') and name='delay_days')
	alter table entity_cd add delay_days int  null;
go

---纸张折合率表
if not exists (select 1 from  sysobjects where  id = object_id('PAPER_CONVERSION_RATE') and type = 'U')
create table PAPER_CONVERSION_RATE (
   TYPE_NAME            nvarchar(100)         not null,
   CONVERSION_RATE      float                 null,
   constraint PK_PAPER_CONVERSION_RATE primary key nonclustered (TYPE_NAME)
)
go

---给审计管理员修改折合率的权限
if not exists (select 1 from SEC_OPER where OPER_CODE = '01130910')
			INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130910','纸张折合率','admin','ledger/viewpaperconversionrate.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '17' and A.OPER_CODE = '01130910') 
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('17','01130910');

--- 
---在录入文件表中添加文号字段
if not exists (select 1 from syscolumns where id=object_id('EVENT_IMPORT') and name='FILE_NUM')
	alter table EVENT_IMPORT add FILE_NUM nvarchar(64)
go

---在EVENT_PRINT表中，添加“替换页”的相关字段
if not exists (select 1 from syscolumns where id=object_id('EVENT_PRINT') and name='PID_barcode')
	alter table EVENT_PRINT add PID_barcode nvarchar(100)
go
if not exists (select 1 from syscolumns where id=object_id('EVENT_PRINT') and name='PID_PAGENUM')
	alter table EVENT_PRINT add PID_PAGENUM nvarchar(100)
go

---在ENTITY_PAPER表中，添加“替换页”的相关字段
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='PID_barcode')
	alter table ENTITY_PAPER add PID_barcode nvarchar(100)
go
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='PID_PAGENUM')
	alter table ENTITY_PAPER add PID_PAGENUM nvarchar(100)
go
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='RETRIEVE_PAGENUM')
	alter table ENTITY_PAPER add RETRIEVE_PAGENUM nvarchar(100)
go
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='RETRIEVE_REPLACE')
	alter table ENTITY_PAPER add RETRIEVE_REPLACE nvarchar(100)
go

---设置替换页回收地址
if not exists (select 1 from SEC_OPER where OPER_CODE = '01130108')
INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130108','替换页回收','admin','ledger/viewreplacepage.action','_image/ico/default.gif','N','N');
go
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '5' and A.OPER_CODE = '01130108')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('5','01130108');
go
---设置替换页销毁地址
if not exists (select 1 from SEC_OPER where OPER_CODE = '01130109')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130109','替换页销毁','admin','ledger/viewdestroyreplacepage.action','_image/ico/default.gif','N','N');
go
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '16' and A.OPER_CODE = '01130109')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('16','01130109');
go

---在ENTITY_PAPER表中，添加“替换页销毁”的字段
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='DESTROY_PAGENUM')
	alter table ENTITY_PAPER add DESTROY_PAGENUM nvarchar(100)
go


---系统管理员增加重置密码按钮
if not exists (select 1 from SEC_OPER where OPER_CODE = '01020119')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020119','重置密码', 'admin','basic/sysresetuserpwd.action','_image/ico/default.gif','N','N');
go
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01020119')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('2','01020119');
go
---审计管理员增加重置密码按钮
if not exists (select 1 from SEC_OPER where OPER_CODE = '01020120')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020120','重置密码', 'admin','basic/audresetuserpwd.action','_image/ico/default.gif','N','N');
go
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '4' and A.OPER_CODE = '01020120')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('4','01020120');
go

---增加宣传报道刻录文件中间表
if not exists (select 1 from  sysobjects where  id = object_id('PUBLICITY_BURN') and type = 'U')
create table PUBLICITY_BURN (
   ID                   int                  identity(1,1),
   IDCARD               nvarchar(100)         null,
   EVENT_CODE           nvarchar(100)         null,
   CD_SERIAL            nvarchar(100)         null,
   SECLV_CODE           nvarchar(100)         null,
   FILE_NAME            nvarchar(100)         null,
   FILE_SECLEVEL        nvarchar(100)         null,
   FILE_CONTENTS        image                 null,
   BURN_STATES          nvarchar(100)         null,
   FSEQUENCENO          int                   null,
   FILE_ID              nvarchar(100)         null,
   REQDEPT              nvarchar(100)         null,
   REQPERSON            nvarchar(100)         null,
   constraint PK_PUBLICITY_BURN primary key (ID)
)
go
---增加设置标记失败最大天数
if not exists (select 1 from SEC_OPER where OPER_CODE = '01020315')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020315','全局参数配置','admin','basic/configsystem.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01020315')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('2','01020315');
go

----------------------------------------------------------------------------------------------------
--- 删除字段示例
--- 删除表table_1中的字段column_1
---
--- if exists (select 1 from syscolumns where id=object_id('table_1') and name='column_1')
--- begin
--- alter table table_1 DROP COLUMN column_1
--- end

/*==============================================================*/
/* Table: EVENT_MODIFY                                         */
/*==============================================================*/

if not exists (select 1 from  sysobjects where  id = object_id('EVENT_MODIFY') and type = 'U')
create table EVENT_MODIFY (
   ID                   int                  identity(1,1),
   EVENT_CODE           nvarchar(64)         null,
   USER_IIDD            nvarchar(100)        null,
   DEPT_ID              nvarchar(100)        null,
   SECLV_CODE           int                  null,
   ENTITY_TYPE          nvarchar(64)         null,
   BARCODE              nvarchar(64)         null,
   PRE_SECLV            int                  null,
   TRG_SECLV            int                  null,
   PRE_SCOPE            nvarchar(64)         null,
   TRG_SCOPE            nvarchar(64)         null,
   PRE_TIMELIMIT        int                  null,
   TRG_TIMELIMIT        int                  null,
   SUMM                 nvarchar(1024)       null,
   APPLY_TIME           datetime             null,
   FINISH_TIME          datetime             null,
   MODIFY_STATUS        int                  null,
   PROJECT_CODE         nvarchar(64)         null,
   JOB_CODE             nvarchar(100)        null,
   EXT_COLUMN           nvarchar(1024)       null,
   constraint PK_EVENT_MODIFY primary key (ID)
)
go

---密级变更增加操作与权限
if not exists (select 1 from SEC_OPER where OPER_CODE = '011106')
 INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('011106','载体变更管理','admin',NULL,NULL,'N','Y');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110601')
 INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110601','密级变更申请','admin','ledger/viewmodifyledger.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110602')
 INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110602','变更申请记录','admin','ledger/managemodifyledger.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER where OPER_CODE = '011107')
 INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('011107','密级变更管理','admin',NULL,NULL,'N','Y');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110701')
 INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110701','密级变更确认','admin','ledger/viewconfirmmodify.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110702') 
 INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110702','密级变更确认列表','admin','ledger/viewconfmodifyljob.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER where OPER_CODE = '011108')
 INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('011108','变更审批','admin',NULL,NULL,'N','Y');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110801')
 INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110801','待审批列表','admin','ledger/managemodifyjob.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110802')
 INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110802','查看审批记录','admin','ledger/viewmodifyjob.action','_image/ico/default.gif','N','N');

---if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01110601')
---INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110601');
---if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01110602')
---INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110602');
---if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '24' and A.OPER_CODE = '01110701')
---INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('24','01110701');
---if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '24' and A.OPER_CODE = '01110702')
---INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('24','01110702');
---if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '10' and A.OPER_CODE = '01110801')
---INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('10','01110801');
---if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '10' and A.OPER_CODE = '01110802')
---INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('10','01110802');

---密级变更角色配置
---if not exists (select 1 from SEC_ROLE where ROLE_ID = '24')
---	INSERT INTO SEC_ROLE(ROLE_ID,ROLE_NAME,SUBSYS_CODE,ROLE_DESC,ROLE_TYPE,ROLE_SPEC_KEY) VALUES('24','密级变更管理员','admin','对密级变更审批后确认，然后重新输出条码',3,'');

--
--- 添加外带管理员
if not exists (select 1 from SEC_ROLE where ROLE_ID = '101')
		INSERT INTO SEC_ROLE(ROLE_ID,ROLE_NAME,SUBSYS_CODE,ROLE_DESC,ROLE_TYPE,ROLE_SPEC_KEY) VALUES('101','外带管理员','admin','负责处理带回的文件和光盘',3,'');
--增加外带查询员 
if not exists (select 1 from SEC_ROLE where ROLE_ID = '102')
		INSERT INTO SEC_ROLE(ROLE_ID,ROLE_NAME,SUBSYS_CODE,ROLE_DESC,ROLE_TYPE,ROLE_SPEC_KEY) VALUES('102','外带查询员','admin','负责查看带回的文件和光盘记录',3,'');
---


---
---文件外带申请记录
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110110')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110110','外带申请记录','admin','ledger/managecarryoutpaperjob.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01110110')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110110');
go
---光盘外带申请记录
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110211')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110211','外带申请记录','admin','ledger/managecarryoutcdjob.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01110211')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110211');
go
---

---载体外带审批
if not exists (select 1 from SEC_OPER where OPER_CODE = '011318')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('011318','载体外带审批','admin',NULL,NULL,'N','Y');
go

---待审批列表
if not exists (select 1 from SEC_OPER where OPER_CODE = '01131802')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01131802','待审批列表','admin','ledger/managecarryoutaprvjob.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '6' and A.OPER_CODE = '01131802')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('6','01131802');
go

---审批记录列表
if not exists (select 1 from SEC_OPER where OPER_CODE = '01131804')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01131804','审批记录列表','admin','ledger/viewcarryouthandlejob.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '6' and A.OPER_CODE = '01131804')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('6','01131804');
go
---


---
---载体外带管理
if not exists (select 1 from SEC_OPER where OPER_CODE = '011319')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('011319','载体外带管理','admin',NULL,NULL,'N','Y')
go
---文件带回确认
if not exists (select 1 from SEC_OPER where OPER_CODE = '01131902')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01131902','文件带回确认','admin','ledger/managecarryoutconfirmpapereventlist.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '101' and A.OPER_CODE = '01131902')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('101','01131902');
go
---光盘带回确认
if not exists (select 1 from SEC_OPER where OPER_CODE = '01131904')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01131904','光盘带回确认','admin','ledger/managecarryoutconfirmcdeventlist.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '101' and A.OPER_CODE = '01131904')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('101','01131904');
go


---文件外带记录
if not exists (select 1 from SEC_OPER where OPER_CODE = '01131906')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01131906','文件外带记录','admin','ledger/viewcarryoutpapereventlist.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '102' and A.OPER_CODE = '01131906')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('102','01131906');
go

---光盘外带记录
if not exists (select 1 from SEC_OPER where OPER_CODE = '01131908')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01131908','光盘外带记录','admin','ledger/viewcarryoutcdeventlist.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '102' and A.OPER_CODE = '01131908')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('102','01131908');
go





---普通用户具有个人转部门权限
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01150101')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01150101');
go
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01150103')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01150103');
go

--部门磁介质管理员
if not exists (select 1 from SEC_ROLE where ROLE_ID = '108')
		INSERT INTO SEC_ROLE(ROLE_ID,ROLE_NAME,SUBSYS_CODE,ROLE_DESC,ROLE_TYPE,ROLE_SPEC_KEY) VALUES('108','部门磁介质管理员','admin','负责管理分配可管理的部门的磁介质',3,'');

--给部门磁介质管理员增加管理权限
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '108' and A.OPER_CODE = '01070302')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('108','01070302');
go
--给部门磁介质管理员增加分配权限
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '108' and A.OPER_CODE = '01070401')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('108','01070401');
go

--- 涉密人员类别表增加默认允许操作的密级字段 mod by rmf 20150520
---
if not exists (select 1 from syscolumns where id=object_id('USER_SECURITY') and name='DEFAULT_VALUE')
	alter table USER_SECURITY add DEFAULT_VALUE nvarchar(1024)
go

--外带表
if not exists (select 1 from  sysobjects where  id = object_id('EVENT_CARRYOUT') and type = 'U')
	create table EVENT_CARRYOUT (
	   ID 					int 				identity(1,1),
	   EVENT_CODE 			nvarchar(64) 		null,
	   USER_IIDD 			nvarchar(100) 		null,
	   DEPT_ID 				nvarchar(100) 		null,
	   ENTITY_TYPE 			nvarchar(64) 		null,
	   BARCODE 				nvarchar(64)		null,
	   PROJECT_CODE 		nvarchar(64) 		null,
	   CARRYOUT_INFO 		nvarchar(1024)		null,
	   CARRYIN_INFO 		nvarchar(1024)		null,
	   APPROVAL_CODE 		nvarchar(64)		null,
	   START_TIME 			datetime 			null,
	   END_TIME 			datetime 			null,
	   SECLV_CODE			int 				null,
	   USAGE_CODE 			nvarchar(120) 		null,
	   SUMM 				nvarchar(120) 		null,
	   FINISH_TIME 			datetime 			null,
	   CARRYOUT_STATUS 		int 				null,
	   JOB_CODE 			nvarchar(100) 		null,
	   EXT_COLUMN 			varchar(100)		null,
	   constraint PK_EVENT_CARRYOUT primary key (ID)
	)
go
if not exists (select 1 from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_CARRYOUT') and o.name = 'FK_EVENT_CARRYOUT_JOB_CODE')
	alter table EVENT_CARRYOUT add constraint FK_EVENT_CARRYOUT_JOB_CODE foreign key (JOB_CODE) references JOB_PROCESS(JOB_CODE)
go
if not exists (select 1 from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_CARRYOUT') and o.name = 'FK_EVENT_CARRYOUT_DEPT_ID')
	alter table EVENT_CARRYOUT add constraint FK_EVENT_CARRYOUT_DEPT_ID  foreign key (DEPT_ID) references SEC_DEPT(DEPT_ID)
go
if not exists (select 1 from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_CARRYOUT') and o.name = 'FK_EVENT_CARRYOUT_USER_IIDD')
	alter table EVENT_CARRYOUT add constraint FK_EVENT_CARRYOUT_USER_IIDD foreign key (USER_IIDD) references SEC_USER(USER_IIDD)
go
if not exists (select 1 from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_CARRYOUT') and o.name = 'FK_EVENT_CARRYOUT_SECLV_CODE')
	alter table EVENT_CARRYOUT add constraint FK_EVENT_CARRYOUT_SECLV_CODE foreign key (SECLV_CODE) references SEC_USER_SECLV(SECLV_CODE)
go

    alter table "cclcm"."dbo"."EVENT_CARRYOUT"  
        add constraint "FK_EVENT_CARRYOUT_JOB_CODE" 
        foreign key ("JOB_CODE") 
        references "cclcm"."dbo"."JOB_PROCESS"("JOB_CODE")
go
    alter table "cclcm"."dbo"."EVENT_CARRYOUT"  
        add constraint "FK_EVENT_CARRYOUT_DEPT_ID" 
        foreign key ("DEPT_ID") 
        references "cclcm"."dbo"."SEC_DEPT"("DEPT_ID")
go
    alter table "cclcm"."dbo"."EVENT_CARRYOUT"  
        add constraint "FK_EVENT_CARRYOUT_USER_IIDD" 
        foreign key ("USER_IIDD") 
        references "cclcm"."dbo"."SEC_USER"("USER_IIDD")
go
    alter table "cclcm"."dbo"."EVENT_CARRYOUT"  
        add constraint "FK_EVENT_CARRYOUT_SECLV_CODE" 
        foreign key ("SECLV_CODE") 
        references "cclcm"."dbo"."SEC_USER_SECLV"("SECLV_CODE")
go

--- 
---在载体生命周期表中添加job_code字段
if not exists (select 1 from syscolumns where id=object_id('CYCLE_RECORD') and name='JOB_CODE')
	alter table CYCLE_RECORD add JOB_CODE nvarchar(100)
go

---
---录入管理中添加个人文件录入管理、个人光盘录入管理
INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01060303','个人文件录入管理','admin','enter/manageenterpaperlist.action?selftype=Y','_image/ico/default.gif','N','N');
INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01060304','个人光盘录入管理','admin','enter/manageentercdlist.action?selftype=Y','_image/ico/default.gif','N','N');
INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01060602','个人扫描录入管理','admin','enter/managescanlist.action?self_type=Y','_image/ico/default.gif','N','N');
go

---导出excel模板配置
if not exists (select 1 from SEC_OPER where OPER_CODE = '01020323')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020323','导出excel模板配置','admin','basic/configexceltemplet.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01020323')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('2','01020323');
go

if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'exportpersonalpaperledger')
 	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('exportpersonalpaperledger','个人文件导出excel模板','2,3,4,5,6,7,8','EXCELPAPER',0);
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'exportpaperledger')
 	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('exportpaperledger','文件总台账导出excel模板','2,3,4,5,6,7,8','EXCELPAPER',0);
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'exportdeptpaperledger')
 	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('exportdeptpaperledger','公共文件台账导出excel模板','2,3,4,5,6,7,8','EXCELPAPER',0);
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'exportdeptuserpaperledger')
 	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('exportdeptuserpaperledger','用户文件台账导出excel模板','2,3,4,5,6,7,8','EXCELPAPER',0);
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'exportpaperborrowledger')
 	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('exportpaperborrowledger','文件借用台账导出excel模板','2,3,4,5,6,7,8','EXCELPAPER',0);
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'exportretrievedpaper')
 	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('exportretrievedpaper','文件销毁导出excel模板','2,3,4,5,6,7,8','EXCELPAPER',0);
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'exportdestroypaper')
 	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('exportdestroypaper','已销毁文件列表导出excel模板','2,3,4,5,6,7,8','EXCELPAPER',0);
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'exportfilepaper')
 	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('exportfilepaper','已归档文件导出excel模板','2,3,4,5,6,7,8','EXCELPAPER',0);
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'exportsendpaper')
 	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('exportsendpaper','已外发文件导出excel模板','2,3,4,5,6,7,8','EXCELPAPER',0);

if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'exportpersonalcdledger')
 	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('exportpersonalcdledger','个人光盘导出excel模板','2,3,4,5,6,7,8','EXCELCD',0);
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'exportcdledger')
 	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('exportcdledger','光盘总台账导出excel模板','2,3,4,5,6,7,8','EXCELCD',0);
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'exportdeptcdledger')
 	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('exportdeptcdledger','公共光盘台账导出excel模板','2,3,4,5,6,7,8','EXCELCD',0);
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'exportdeptusercdledger')
 	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('exportdeptusercdledger','用户光盘台账导出excel模板','2,3,4,5,6,7,8','EXCELCD',0);
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'exportcdborrowledger')
 	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('exportcdborrowledger','光盘借用台账导出excel模板','2,3,4,5,6,7,8','EXCELCD',0);
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'exportretrievedcd')
 	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('exportretrievedcd','光盘销毁导出excel模板','2,3,4,5,6,7,8','EXCELCD',0);
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'exportdestroycd')
 	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('exportdestroycd','已销毁光盘列表导出excel模板','2,3,4,5,6,7,8','EXCELCD',0);
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'exportfilecd')
 	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('exportfilecd','已归档光盘导出excel模板','2,3,4,5,6,7,8','EXCELCD',0);
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'exportsendcd')
 	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('exportsendcd','已外发光盘导出excel模板','2,3,4,5,6,7,8','EXCELCD',0);
go

if exists (select 1 from syscolumns where id=object_id('SEC_CONFIG') and name='ITEM_VALUE')
	alter table SEC_CONFIG alter column ITEM_VALUE nvarchar(1024)
go
---在SYS_PRINTER表中，添加“打印机排序值”字段
if not exists (select 1 from syscolumns where id=object_id('SYS_PRINTER') and name='PRINTER_RANK')
	alter table SYS_PRINTER add PRINTER_RANK int not null default 0
go


--- 文件表ENTITY_PAPER里加字段MATTER_STATE(外发事项办理)
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='MATTER_STATE')
	alter table ENTITY_PAPER add MATTER_STATE int null
go

--- 光盘表ENTITY_CD里加字段MATTER_STATE(外发事项办理)
if not exists (select 1 from syscolumns where id=object_id('ENTITY_CD') and name='MATTER_STATE')
	alter table ENTITY_CD add MATTER_STATE int null
go

--- 文件表ENTITY_PAPER里加字段 CYC_REMARKS（闭环备注）
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='CYC_REMARKS')
	alter table ENTITY_PAPER add CYC_REMARKS nvarchar(1024) null
go

--- 光盘表ENTITY_CD里加字段CYC_REMARKS（闭环备注）
if not exists (select 1 from syscolumns where id=object_id('ENTITY_CD') and name='CYC_REMARKS')
	alter table ENTITY_CD add CYC_REMARKS nvarchar(1024) null
go
---设置自动解锁打印作业时间
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'AUTO_UNLOCK_TIME')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('AUTO_UNLOCK_TIME','自动解锁打印作业时间','10','AUTO_UNLOCK_TIME',0);
GO

--- 打印表EVENT_PRINT里加字段LOCK_TIME作业解锁时间
if not exists (select 1 from syscolumns where id=object_id('EVENT_PRINT') and name='LOCK_TIME')
	alter table EVENT_PRINT add LOCK_TIME datetime null
go
   
--- 打印表EVENT_PRINT里加字段CONSOLE_CODE 加锁控制台ID
if not exists (select 1 from syscolumns where id=object_id('EVENT_PRINT') and name='CONSOLE_CODE')
	alter table EVENT_PRINT add CONSOLE_CODE nvarchar(64) null
go
   

--文件表ENTITY_PAPER里加字段REAL_PAGE_COUNT实际输出页数
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='REAL_PAGE_COUNT')
alter table ENTITY_PAPER add REAL_PAGE_COUNT nvarchar(64)  null
go
   
/*==============================================================*/
/* Table: UNLOCK_EVENT_LOG    解锁作业日志表                                     */
/*==============================================================*/
if not exists (select 1 from  sysobjects where  id = object_id('UNLOCK_EVENT_LOG') and type = 'U')
create table UNLOCK_EVENT_LOG (
ID	                   int primary key identity(1,1),
USER_IIDD	           nvarchar(100)		null,
EVENT_CODE	           nvarchar(64)         null,
UNLOCK_TIME	           datetime             null,
CONSOLE_CODE		   nvarchar(64)         null,
EVENT_TYPE	           nvarchar(64)         null,
EXT_CODE	           nvarchar(255)        null,
EXT_INT	               int                  null,
EXT_PRINT	           nvarchar(255)        null
)
go

-- 删除光盘台账cd_barcode字段唯一性约束
--alter table entity_cd drop constraint CONS_UNI_CD_BARCODE 

--删除文件台账paper_barcode字段唯一性约束
--alter table entity_paper drop constraint CONS_UNI_PAPER_BARCODE 

--添加文件台账paper_barcode字段唯一性约束
alter table [entity_paper] add constraint CONS_UNI_PAPER_BARCODE unique (PAPER_BARCODE)

go
--添加光盘台账cd_barcode字段唯一性约束
alter table [entity_cd] add constraint CONS_UNI_CD_BARCODE unique (CD_BARCODE)
go

--- 文件表ENTITY_PAPER里加字段SECRET_BOOK科研工作手册
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='SECRET_BOOK')
	alter table ENTITY_PAPER add SECRET_BOOK int null
go

---普通用户具有查看申请记录权限
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01150105')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01150105');
go

---控制台列表
if not exists (select 1 from SEC_OPER where OPER_CODE = '01020505')
       INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020505','控制台列表','admin','client/consolemanage.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01020505')
       INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('2','01020505');
go
---控制台版本列表
if not exists (select 1 from SEC_OPER where OPER_CODE = '01020506')
       INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020506','控制台版本列表','admin','basic/viewconsoleversionfile.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01020506')
       INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('2','01020506');
go
---客户端管理名称改为终端管理
if exists ( select 1 from SEC_OPER where OPER_CODE = '010205')
      update SEC_OPER set OPER_NAME = '终端管理' where OPER_CODE = '010205'
go

---光盘录入申请加保密编号
if not exists (select 1 from syscolumns where id=object_id('event_import') and name='conf_code')
	alter table event_import add conf_code nvarchar(100) null;
go

---文件台账中增加需要回收的文件台账菜单
INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110107','待回收文件台账','admin','ledger/viewselfpaperrecycleledger.action','_image/ico/default.gif','N','N');
go

---普通用户具有回收文件台账权限
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01110107')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110107');
go

---添加OA特殊打印功能
INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('010303','特殊打印','admin',NULL,NULL,'N','Y');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01030301')
       INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01030301','特殊打印流程','admin','print/addspecialprintevent.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01030301')
       INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01030301');
go

if not exists (select 1 from SEC_OPER where OPER_CODE = '01030302')
       INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01030302','查看申请记录','admin','print/managespecialprintevent.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01030302')
       INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01030302');
go

INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('010304','特殊打印管理','admin',NULL,NULL,'N','Y');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01030401')
       INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01030401','特殊打印管理','admin','print/managespecialprintlist.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '14' and A.OPER_CODE = '01030401')
       INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('14','01030401');
go
--预台帐记录
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110119')
INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110119','预台帐记录','admin','ledger/viewselfexpectpaperledger.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01110119')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110119');
go	
--预台帐打印
if not exists (select 1 from SEC_OPER where OPER_CODE = '01130608')
INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130608','预台帐打印','admin','ledger/viewexpectpaperledger.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '14' and A.OPER_CODE = '01130608')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('14','01130608');

go

if not exists (select 1 from  sysobjects where  id = object_id('EVENT_SPECIALPRINT') and type = 'U')
create table EVENT_SPECIALPRINT (
   ID                   int                  identity(1,1),
   EVENT_CODE           nvarchar(64)         null,
   USER_IIDD            nvarchar(100)        null,
   DEPT_ID              nvarchar(100)        null,
   SECLV_CODE           int                  null,
   ENTITY_TYPE          nvarchar(64)         null,
   BARCODE              nvarchar(64)         null,
   PROJECT_CODE         nvarchar(64)         null,
   USAGE_CODE           nvarchar(64)         null,
   APPLY_TIME           datetime             null,
   SUMM                 nvarchar(1024)       null,
   PAPER_NAME           nvarchar(1024)       null,
   PAGE_NUM             int                  null,
   PAPER_NUM            int                  null,
   MAKE_TYPE            nvarchar(64)         null,
   FILE_SELV            int                  null,
   PRINT_DIRECT         int                  null,
   PRINT_DOUBLE         int                  null,
   PAPER_COLOR          int                  null,
   PAPER_KIND           nvarchar(64)         null,
   FILE_NUM             int                  null,
   FILE_LIST            nvarchar(1024)       null,
   PAPER_STATUS         int                  null default '0',
   MANAGER_USER_NAME    nvarchar(100)        null,
   MANAGER_USER_IIDD    nvarchar(100)        null,
   MANAGER_DEPT_NAME    nvarchar(100)        null,
   MANAGER_DEPT_IIDD    nvarchar(100)        null, 
   JOB_CODE             nvarchar(100)        null,
   HIS_JOB_CODE         nvarchar(1024)       null,
   constraint PK_EVENT_SPECIALPRINT primary key (ID)
)
go

--- Table: ENTITY_PAPER CREATE_TYPE字段约束里增加SPECIAL-特殊打印
alter table ENTITY_PAPER drop constraint CKC_CREATE_TYPE_ENTITY_P;
alter table ENTITY_PAPER add constraint CKC_CREATE_TYPE_ENTITY_P check (CREATE_TYPE in ('PRINT','COPY','LEADIN','OUTCOPY','SPECIAL'));
go


---设置回收和送销开关
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'Recover_On_Off')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('Recover_On_Off','回收开关',null,'CONFIRM','1');
GO
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'SendDestroy_On_Off')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('SendDestroy_On_Off','送销开关',null,'CONFIRM','1');
GO
---添加光盘补打条码页面
if not exists (select 1 from SEC_OPER where OPER_CODE = '01130502')
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130502','光盘补打条码','admin','ledger/viewcdreprintbarcode.action','_image/ico/default.gif','N','N');
go
---添加是否为代理字段，委托人字段
if not exists (select 1 from syscolumns where id=object_id('SEC_ROLE_USER') and name='IS_PROXY')
	alter table SEC_ROLE_USER add IS_PROXY nvarchar(100) null
go
if not exists (select 1 from syscolumns where id=object_id('SEC_ROLE_USER') and name='AGENT')
	alter table SEC_ROLE_USER add AGENT nvarchar(100) null
go

update sec_oper set web_url = 'client/clienttask.action' where oper_code = '01010101';
if not exists (select 1 from syscolumns where id=object_id('client_msg') and name='title ')
	alter table CLIENT_MSG add title nvarchar(1024) null;
go
if not exists (select 1 from syscolumns where id=object_id('client_msg') and name='url ')
	alter table CLIENT_MSG add url nvarchar(1024) null;
go

if not exists (select 1 from SEC_OPER where OPER_CODE = '01120105')
   INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01120105','部门科研工作手册','admin','ledger/viewdeptsecretpaperledger.action','_image/ico/default.gif','N','N');
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('6','01120105');   
go
if not exists (select 1 from SEC_OPER where OPER_CODE = '01080108')
   INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01080108','科研工作手册领用','admin','borrow/viewsecretborrowpaper.action','_image/ico/default.gif','N','N');
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01080108');  
go
--预台帐打印
if not exists (select 1 from SEC_OPER where OPER_CODE = '01130608')
INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130608','预台帐打印','admin','ledger/viewexpectpaperledger.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '14' and A.OPER_CODE = '01130608')
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('14','01130608');

go

--- 文件表ENTITY_PAPER里加字段SECRET_BOOK科研工作手册
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='SECRET_BOOK')
	alter table ENTITY_PAPER add SECRET_BOOK int null
go


if exists (select 1 from dbo.sysobjects where id=object_id (N'[dbo].[T_Addurl]') and (objectproperty (id ,N'IsTrigger' ) = 1))
  drop trigger T_Addurl;
go


set ANSI_NULLS ON
set QUOTED_IDENTIFIER ON
go



if(object_id('T_Addurl','TR') is not null)
 drop trigger T_Addurl
go

create  trigger [T_Addurl] on [dbo].[CLIENT_MSG] for insert
as 
declare
	@sequence_no varchar(64),
	@pid varchar(320),
	@id_num varchar(64),
	@accept_user_id varchar(50),
	@accept_user_name varchar(50),
	@title varchar(30),
	@link_url varchar(1024),
	@msg_type varchar(1),
	@job_code varchar(64),
	@oper_type varchar(30),
	@from_dept varchar(64),
	@from_user_id varchar(64),
	@from_man varchar(30);
	select 
		@sequence_no='dy'+cast(id as varchar(32))+job_code, 
		@accept_user_id = accept_user_iidd,
		@accept_user_name = accept_user_name,
		@job_code = job_code,
		@oper_type = oper_type,
		@from_user_id = substring(job_code,0,charindex('-',job_code)),
		@msg_type = message_type from inserted;

              select @pid=@accept_user_name+idcard from sec_user where user_iidd = @accept_user_id;
			select @from_man=user_name,@from_dept=dept_name
			from sec_user u,sec_dept d where u.dept_id = d.dept_id and  u.user_iidd=@from_user_id;
		
		if @oper_type = 'PRINT'
		begin
		    set @title = '文件打印';
	            if @msg_type = 1
                    begin
			set @link_url = 'print/approveprintjob.action?job_code='+@job_code;
                    end
                        else begin
                            set @link_url = 'print/manageprintjob.action'
                        end
		 end;
		 if @oper_type = 'PROXY_PRINT'
		 begin
		    set @title = '委托打印';
	            if @msg_type = 1
                    begin
			set @link_url = 'print/manageprintevent.action?proxyprint_user_iidd='+@accept_user_id;
                    end
                        else begin
                            set @link_url = 'print/manageprintevent.action?proxyprint_user_iidd='+@accept_user_id;
                        end
		 end;
		 if @oper_type = 'PROXY_BURN'
		 begin
			set @title = '委托刻录';
                if @msg_type = 1
                     begin
		    set @link_url = 'burn/manageburnevent.action?proxyburn_user_iidd='+@accept_user_id;
                     end else begin
                            set @link_url = 'burn/manageburnevent.action?proxyburn_user_iidd='+@accept_user_id;
                         end;
		 end;
		 if @oper_type = 'SPECIAL_PRINT'
		 begin
		    set @title = '特殊打印';
	            if @msg_type = 1
                    begin
			set @link_url = 'print/approvespecialprintjob.action?job_code='+@job_code;
                    end
                        else begin
                            set @link_url = 'print/managespecialprintevent.action'
                        end
		 end;
		 
		 
		 if @oper_type = 'FILE_PAPER'
		 begin
		    set @title = '文件归档';
                    if @msg_type = 1 
                    begin
		    set @link_url = 'basic/handlejob.action?type=PAPER&job_code='+@job_code;
                    end else begin
                        set @link_url = 'ledger/filepaperapply.action';
                    end
		 end;
			if @oper_type = 'SEND_PAPER'
				begin
					set @title = '文件外发';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'basic/handlejob.action?type=PAPER&job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'ledger/sendpaperapply.action';
                                        end;
				end;
			if @oper_type = 'DESTROY_PAPER'
				begin	
					set @title = '文件销毁';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'basic/handlejob.action?type=PAPER&job_code='+@job_code;
                                        end else
                                        begin
                                          set @link_url = 'ledger/viewselfpaperrecycleledger.action'; 
                                        end;

				end;
			if @oper_type = 'DELAY_PAPER'
				begin
					set @title = '文件延期留用';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'basic/handlejob.action?type=PAPER&job_code='+@job_code;
                                        end else 
                                        begin
                                          set @link_url = 'ledger/delaypaperapply.action';
                                        end;
				end;
			if @oper_type = 'CARRYOUT_PAPER'
				begin
					set @title = '文件外带';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'ledger/approvecarryoutjob.action?job_code='+@job_code;
                                        end else 
                                        begin
                                          set @link_url = 'ledger/managecarryoutpaperjob.action';
                                        end;
				end;
			if @oper_type = 'PAPER_DEL'
				begin
					set @title = '文件台账删除';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'basic/handlejob.action?type=PAPER&job_code='+@job_code;
                                        end else 
                                        begin
                                          set @link_url = 'ledger/papermodifyapply.action';
                                        end;
				end;
			if @oper_type = 'PAPER_MODIFY'
				begin
					set @title = '文件台账修改';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'basic/handlejob.action?type=PAPER&job_code='+@job_code;
                                        end else 
                                        begin
                                          set @link_url = 'ledger/papermodifyapply.action';
                                        end;
				end;
			if @oper_type = 'FILECD_DESTROY'
				begin
					set @title = '已归档光盘销毁';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'basic/handlejob.action?type=CD&job_code='+@job_code;
                                        end else 
                                        begin
                                          set @link_url = 'ledger/managefilecdevent.action';
                                        end;
				end;
			if @oper_type = 'FILE_DESTROY'
				begin
					set @title = '已归档文件销毁';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'basic/handlejob.action?type=PAPER&job_code='+@job_code;
                                        end else 
                                        begin
                                          set @link_url = 'ledger/managefilepaperevent.action';
                                        end;
				end;
			if @oper_type = 'BURN'
				begin
					set @title = '光盘刻录';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'basic/approvejob.action?job_code='+@job_code;
                                        end else begin
                                           set @link_url = 'burn/manageburnevent.action';
                                        end;
				end;
			if @oper_type = 'FILE_CD'
				begin
					set @title = '光盘归档';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'basic/handlejob.action?type=CD&job_code='+@job_code;
                                        end else
                                        begin
                                           set @link_url = 'ledger/filecdapply.action';
                                        end;
				end;
			if @oper_type = 'SEND_CD'
				begin
					set @title = '光盘外发';
                                         if @msg_type = 1 begin
 					    set @link_url = 'basic/handlejob.action?type=CD&job_code='+@job_code;
                                         end else begin
                                             set @link_url = 'ledger/sendcdapply.action';
                                         end;
				end;
			if @oper_type = 'DESTROY_CD'
				begin
					set @title = '光盘销毁';
                                        if @msg_type = 1
                                        begin
					   set @link_url = 'basic/handlejob.action?type=CD&job_code='+@job_code;
                                        end else
                                        begin
                                           set @link_url = 'ledger/destroycdapply.action';
                                        end;
				end;
			if @oper_type = 'SENDES_CD'
				begin
					set @title = '光盘送销';
                                        if @msg_type = 1
                                        begin
					   set @link_url = 'basic/handlejob.action?type=CD&job_code='+@job_code;
                                        end else
                                        begin
                                           set @link_url = 'ledger/viewsenddestroycd.action';
                                        end;
				end;
			if @oper_type = 'SENDES_PAPER'
				begin	
					set @title = '文件送销';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'basic/handlejob.action?type=PAPER&job_code='+@job_code;
                                        end else
                                        begin
                                          set @link_url = 'ledger/viewsenddestroypaper.action'; 
                                        end;
				end;
			if @oper_type = 'DELAY_CD'
				begin
					set @title = '光盘延期留用';
                                        if @msg_type = 1
                                        begin
					   set @link_url = 'basic/handlejob.action?type=CD&job_code='+@job_code;
                                        end else
                                        begin
                                          set @link_url = 'ledger/delaycdapply.action';
                                        end;
				end;
			if @oper_type = 'CARRYOUT_CD'
				begin
					set @title = '光盘外带';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'ledger/approvecarryoutjob.action?job_code='+@job_code;
                                        end else 
                                        begin
                                          set @link_url = 'ledger/managecarryoutcdjob.action';
                                        end;
				end;
			if @oper_type = 'COPY'
				begin
					set @title = '复印';
                                        if @msg_type = 1
                                        begin
					   set @link_url = 'copy/approvecopyjob.action?job_code='+@job_code;
                                        end else
                                        begin
                                           set @link_url = 'copy/managecopyjob.action';
                                        end;
				end;
				if @oper_type = 'COPY_MERGE'
				begin
					set @title = '复印';
                                        if @msg_type = 1
                                        begin
					   set @link_url = 'copy/approvecopyjob.action?job_code='+@job_code;
                                        end else
                                        begin
                                           set @link_url = 'copy/managecopyjob.action';
                                        end;
				end;
				
			if @oper_type = 'OUTCOPY'
				begin
					set @title = '外来复印';
                                        if @msg_type = 1
                                        begin
					   set @link_url = 'copy/approvecopyjob.action?job_code='+@job_code;
                                        end else
                                        begin
                                           set @link_url = 'copy/managecopyjobbyenter.action';
                                        end;
				end;
			if @oper_type = 'LEADIN'
				begin
					set @title = '录入';
                                        if @msg_type = 1
                                        begin
					   set @link_url = 'enter/approveenterjob.action?job_code='+@job_code;
                                        end else
                                        begin
                                           set @link_url = 'enter/manageenterlistjob.action';
                                        end;
				end;
			if @oper_type = 'SCAN_LEADIN'
				begin
					set @title = '扫描录入';
                                        if @msg_type = 1
                                        begin
					   set @link_url = 'enter/approveenterjob.action?job_code='+@job_code;
                                        end else
                                        begin
                                           set @link_url = 'enter/manageenterlistjob.action?entertype=SCAN';
                                        end;
				end;
			if @oper_type = 'TRANSFER'
				begin
					set @title = '流转';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'basic/approvejob.action?job_code='+@job_code;
                                        end else
                                        begin
                                          set @link_url = 'transfer/managepapertransferevent.action';
                                        end;
				end;
			if @oper_type = 'TRANSFER_CONFIRM'
				begin
					set @title = '流转确认';
            							if @msg_type = 1
                                        begin
					  set @link_url = 'basic/approvejob.action?job_code='+@job_code;
                                        end else
                                        begin
                                          set @link_url = 'transfer/viewconfirmtransfer.action';
                                        end;
				end;
			if @oper_type = 'DEVICE'
				begin
					set @title = '磁介质借用';
                                        if @msg_type = 1
                                        begin
					   set @link_url = 'device/approvedevicejob.action?job_code='+@job_code;
                                        end else
                                        begin
                                           set @link_url = 'device/managedevicejob.action';
                                        end;
				end;
			if @oper_type = 'BORROW'
				begin
					set @title = '部门载体借用';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'basic/approvejob.action?job_code='+@job_code;
                                        end else if @msg_type = 7
                                        begin
					  set @link_url = 'borrow/viewpersonalborrowledger.action';
                                        end else 
                                        begin
                                           set @link_url = 'borrow/viewborrowpaper.action';
                                        end;
				end;
			if @oper_type = 'DESTROY_DEVICE'
				begin
					set @title = '磁介质销毁';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'basic/approvejob.action?job_code='+@job_code;
                                        end else 
                                        begin
                                          set @link_url = 'device/managehandledevice.action';
                                        end;
				end;
			if @oper_type = 'CHANGE'
				begin
					set @title = '载体归属转换';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'change/approvechangejob.action?job_code='+@job_code;
                                        end else 
                                        begin
                                          set @link_url = 'change/managechangejob.action';
                                        end;
				end;
			if @oper_type = 'USERSEC_ACTIVITY'
				begin
					set @title = '重大涉密活动';
                                        if @msg_type = 1
                                        begin
					   set @link_url = 'secactivity/approveusecactijob.action?job_code='+@job_code;
                                        end else
                                        begin
                                           set @link_url = 'securityuser/managesecusereventlist.action?module=secmanage';
                                        end;
				end;
			if @oper_type = 'USERSECLV_ADD'
				begin
					set @title = '新增涉密人员';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'securityuser/approveuseclvchangejob.action?job_code='+@job_code;
                                        end else
                                        begin
                                          set @link_url = 'securityuser/managesecusereventlist.action';
                                        end;
				end;	
			if @oper_type = 'USERSECLV_CHANGE'
				begin
					set @title = '用户等级变更';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'securityuser/approveuseclvchangejob.action?job_code='+@job_code;
                                        end else
                                        begin
                                          set @link_url = 'securityuser/managesecusereventlist.action';
                                        end;
				end;

			if @oper_type = 'SECUSER_ABROAD'
				begin
					set @title = '因私出国/境';
                                        if @msg_type = 1
                                        begin
					   set @link_url = 'securityuser/approveuabroadjob.action?job_code='+@job_code;
                                        end else
                                        begin
                                           set @link_url = 'securityuser/managesecusereventlist.action';
                                        end;
				end;
			if @oper_type = 'SECUSER_ENTRUST'
				begin
					set @title = '委托保密';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'securityuser/approveuserentrustjob.action?job_code='+@job_code;
                                        end else
                                        begin
                                          set @link_url = 'securityuser/managesecusereventlist.action';
                                        end;
				end;
			if @oper_type = 'RESIGN'
				begin
					set @title = '离职/脱密';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'securityuser/approveuresignjob.action?job_code='+@job_code;
                                        end else
                                        begin
                                          set @link_url = 'securityuser/managesecusereventlist.action';
                                        end;
				end;
			if @oper_type = 'OUT_EXCHANGE'
				begin
					set @title = '与境外交流保密工作';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'secactivity/approvesecoutexchangejob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'securityuser/managesecusereventlist.action?module=secmanage';
                                        end;
				end;	
			if @oper_type = 'USER_INFO'
				begin
					set @title = '完善个人资料工作';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'securityuser/approveuserinfojob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'securityuser/manageuserinfolist.action';
                                        end;
				end;
			if @oper_type = 'EVENT_SECPLACE'
				begin
					set @title = '新增涉密场所';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'secplace/approvesecplacejob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'secplace/viewsecplaceevent.action';
                                        end;
				end;
			if @oper_type = 'ENTER_SECPLACE'
				begin
					set @title = '进出要害部门部位';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'secplace/approveentersecplacejob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'securityuser/managesecusereventlist.action?module=secmanage';
                                        end;
				end;
				if @oper_type = 'INFO_DEVICE'
				begin
					set @title = '新增信息设备产品介质';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'computer/approveinfodevicejob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'computer/manageinfodevicelist.action';
                                        end;
				end;
				if @oper_type = 'DEVICE_CHANGE'
				begin
					set @title = '信息设备变更';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'computer/approveinfodevicejob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'computer/manageinfodevicelist.action';
                                        end;
				end;
				if @oper_type = 'DEVICE_DES'
				begin
					set @title = '信息设备报废';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'computer/approveinfodevicejob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'computer/manageinfodevicelist.action';
                                        end;
				end;
				if @oper_type = 'EVENT_REPCOM'
				begin
					set @title = '计算机维修';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'computer/approvecomputerjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'computer/viewcomputerevent.action';
                                        end;
				end;
				if @oper_type = 'EVENT_INTCOM'
				begin
					set @title = '新增计算机（网络）';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'computer/approvecomputerjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'computer/viewcomputerevent.action';
                                        end;
				end;
				if @oper_type = 'EVENT_SINCOM'
				begin
					set @title = '新增计算机（单机）';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'computer/approvecomputerjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'computer/viewcomputerevent.action';
                                        end;
				end;
				if @oper_type = 'EVENT_CHGCOM'
				begin
					set @title = '计算机变更';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'computer/approvecomputerjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'computer/viewcomputerevent.action';
                                        end;
				end;
				if @oper_type = 'EVENT_DESCOM'
				begin
					set @title = '计算机报废';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'computer/approvecomputerjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'computer/viewcomputerevent.action';
                                        end;
				end;
				if @oper_type = 'EVENT_REPORT'
				begin
					set @title = '宣传报道';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'publicity/approvereportjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'securityuser/managesecusereventlist.action?module=publicity';
                                        end;
				end;
				if @oper_type = 'EVENT_DEPTREPORT'
				begin
					set @title = '部门投稿保密审查';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'publicity/approvereportjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'securityuser/managesecusereventlist.action?module=publicity';
                                        end;
				end;
				if @oper_type = 'EVENT_INTRAPUBL'
				begin
					set @title = '内网信息发布保密审查';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'publicity/approvereportjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'securityuser/managesecusereventlist.action?module=publicity';
                                        end;
				end;
				if @oper_type = 'EVENT_INTERPUBL'
				begin
					set @title = '外网信息发布保密审查';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'publicity/approvereportjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'securityuser/managesecusereventlist.action?module=publicity';
                                        end;
				end;
				if @oper_type = 'EVENT_REINSTALL'
				begin
					set @title = '计算机重装系统';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'infosystem/approveinfosystemjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'infosystem/viewinfosystemevent.action';
                                        end;
				end;
				if @oper_type = 'EVENT_QUITINT'
				begin
					set @title = '计算机退网';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'infosystem/approveinfosystemjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'infosystem/viewinfosystemevent.action';
                                        end;
				end;
				if @oper_type = 'EVENT_REPCOM'
				begin
					set @title = '计算机维修';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'infosystem/approveinfosystemjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'infosystem/viewinfosystemevent.action';
                                        end;
				end;
				if @oper_type = 'EVENT_USBKEY'
				begin
					set @title = 'USB-KEY申请/更新';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'infosystem/approveinfosystemjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'infosystem/viewinfosystemevent.action';
                                        end;
				end;
				if @oper_type = 'EVENT_LOCALPRINTER'
				begin
					set @title = '保留本地打印机';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'infosystem/approveinfosystemjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'infosystem/viewinfosystemevent.action';
                                        end;
				end;
				if @oper_type = 'EVENT_OPENPORT'
				begin
					set @title = '开通端口';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'infosystem/approveinfosystemjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'infosystem/viewinfosystemevent.action';
                                        end;
				end;
				if @oper_type = 'INTER_EMAIL'
				begin
					set @title = '外网邮件';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'secmanage/approveinternetemailjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'securityuser/managesecusereventlist.action?module=publicity';
                                        end;
				end;
				if @oper_type = 'SEC_CHECK'
				begin
					set @title = '部门专项保密检查';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'secmanage/approveseccheckjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'securityuser/managesecusereventlist.action?module=secmanageBMC';
                                        end;
				end;
				if @oper_type = 'FILEOUTMAKE'
				begin
					set @title = '涉外文件制作';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'secmanage/approvefileoutmakejob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'securityuser/managesecusereventlist.action?module=secmanage';
                                        end;
				end;
				if @oper_type = 'MATERIAL'
				begin
					set @title = '对外提供资料';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'secmanage/approveexchangematerialjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'securityuser/managesecusereventlist.action?module=publicity';
                                        end;
				end;
				if @oper_type = 'EXHIBITION'
				begin
					set @title = '展示展览保密审查';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'secmanage/approveexhibitionjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'securityuser/managesecusereventlist.action?module=publicity';
                                        end;
				end;
				if @oper_type = 'FIELDIN'
				begin
					set @title = '进入科研场地';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'secmanage/approveresearchfieldinjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'securityuser/managesecusereventlist.action?module=secmanage';
                                        end;
				end;
				if @oper_type = 'PUNISH_DEPT'
				begin
					set @title = '部门自查保密违规处罚';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'secmanage/approvepunishjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'securityuser/managesecusereventlist.action?module=secmanageBMC';
                                        end;
				end;
				if @oper_type = 'PUNISH_SECCHECK'
				begin
					set @title = '保密检查保密违规处罚';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'secmanage/approvepunishjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'securityuser/managesecusereventlist.action?module=secmanageBMC';
                                        end;
				end;
				if @oper_type = 'PUNISH_RECTIFY'
				begin
					set @title = '保密整改督查';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'secmanage/approvepunishjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'securityuser/managesecusereventlist.action?module=secmanageBMC';
                                        end;
				end;
				if @oper_type = 'INFO_OTHER'
				begin
					set @title = '新增信息设备办公设备';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'computer/approveinfodevicejob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'computer/manageinfodevicelist.action';
                                        end;
				end;
				if @oper_type = 'CHANGE_OTHER'
				begin
					set @title = '信息设备变更介质';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'computer/approveinfodevicejob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'computer/manageinfodevicelist.action';
                                        end;
				end;
				if @oper_type = 'BORROW_BOOK'
				begin
					set @title = '笔记本借用公司内申请';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'computer/approveborrowbookjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'computer/manageborrowbookevent.action';
                                        end;
				end;
				if @oper_type = 'PERSONAL_FILE'
				begin
					set @title = '个人涉密资料';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'carriermanage/approvepersonalfilejob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'carriermanage/managepersonalfileevent';
                                        end;
				end;
				if @oper_type = 'BORROW_BOOKOUT'
				begin
					set @title = '笔记本借用公司外申请';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'computer/approveborrowbookjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'computer/manageborrowbookevent.action';
                                        end;
				end;
				if @oper_type = 'QIYUAN_BORROW_BOOK'
				begin
					set @title = '便携式计算机借用申请';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'computer/approveqiyuanborrowbookjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'computer/manageqiyuanborrowbookevent.action';
                                        end;
				end;
				if @oper_type = 'PAPER_RESEARCH'
				begin
					set @title = '科研技术类论文发表';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'secmanage/approvepaperpatentjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'securityuser/managesecusereventlist.action?module=publicity';
                                        end;
				end;
				if @oper_type = 'PAPER_OTHERS'
				begin
					set @title = '政研管理类论文发表';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'secmanage/approvepaperpatentjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'securityuser/managesecusereventlist.action?module=publicity';
                                        end;
				end;
				if @oper_type = 'PAPERPATENT'
				begin
					set @title = '专利申请';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'secmanage/approvepaperpatentjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'securityuser/managesecusereventlist.action?module=publicity';
                                        end;
				end;
				if @oper_type = 'MODIFY_SECLV'
				begin
					set @title = '密级变更';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'ledger/approvemodifyjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'ledger/managemodifyledger.action';
                                        end;
				end;
				if @oper_type = 'DESTROY_PAPER_BYSELF'
				begin
					set @title = '文件监销';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'basic/handlejob.action?type=PAPER&job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'ledger/manageselfdestroypaperjob.action';
                                        end;
				end;
				if @oper_type = 'DESTROY_CD_BYSELF'
				begin
					set @title = '光盘监销';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'basic/handlejob.action?type=CD&job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'ledger/manageselfdestroycdjob.action';
                                        end;
				end;
				if @oper_type = 'DESTROY_DEVICE_BYSELF'
				begin
					set @title = '磁介质监销';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'basic/handlejob.action?type=DEVICE&job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'ledger/manageselfdestroydevicejob.action';
                                        end;
				end;
				if @oper_type = 'SPACECD_BORROW'
				begin
					set @title = '空白盘申领';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'disc/approvespacecdjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'disc/managespacecdlistjob.action';
                                        end;
				end;
				
     update  A set A.title = @title, A.url = @link_url
      from [dbo].[CLIENT_MSG] A,inserted i
      where A.id = i.id;
go


---增加event_copy表中字段      
if not exists (select 1 from syscolumns where id=object_id('EVENT_COPY') and name='COPY_MERGE')
	alter table EVENT_COPY add COPY_MERGE nvarchar(100) null
go



---如果是旧数据库，外部复印管理和内部复印管理还未合并，执行以下语句进行合并
---if exists (select 1 from SEC_OPER where OPER_CODE='01050602' and OPER_NAME='补打条码')
---begin
---	update SEC_OPER set OPER_NAME='复印管理' where OPER_CODE='010503';
---	update SEC_OPER set OPER_NAME='补打条码(内)' where OPER_CODE='01050302';
---	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01050303','补打条码(外)','admin','copy/viewcopypaperledgerbyenter.action','_image/ico/default.gif','N','N');
---	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('13','01050303');
---	delete from SEC_ROLE_OPER where OPER_CODE='01050602';
---	delete from SEC_OPER where OPER_CODE='01050602';
---end
---go
---增加barcode_seed表中字段
if not exists (select 1 from syscolumns where id=object_id('BARCODE_SEED') and name='DEPT_ID')
	alter table BARCODE_SEED add DEPT_ID nvarchar(100) null
go
if not exists (select 1 from syscolumns where id=object_id('BARCODE_SEED') and name='SECLV_CODE')
	alter table BARCODE_SEED add SECLV_CODE int null
go
if not exists (select 1 from syscolumns where id=object_id('BARCODE_SEED') and name='SEED_YEAR')
	alter table BARCODE_SEED add SEED_YEAR int null
go
if exists (select 1 from syscolumns where id=object_id('BARCODE_SEED') and name='SEED_VALUE')
	alter table BARCODE_SEED alter column SEED_VALUE bigint null
go
---增加存储过程
CREATE PROCEDURE [dbo].[CREATEBARCODE_CSIC]
(@outValue bigint output,
 @inDeptID nvarchar(100),
 @inSeclvCode int,
 @inYear int)
AS
 
BEGIN
	BEGIN TRAN
-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
   
	if not exists (select 1 from BARCODE_SEED where DEPT_ID = @inDeptID and SECLV_CODE = @inSeclvCode and SEED_YEAR = @inYear)
    BEGIN
         insert into BARCODE_SEED (DEPT_ID,SECLV_CODE,SEED_VALUE,SEED_YEAR) values (@inDeptID,@inSeclvCode,1,@inYear)
    END
    
	UPDATE BARCODE_SEED SET SEED_VALUE = SEED_VALUE + 1 where DEPT_ID = @inDeptID and SECLV_CODE = @inSeclvCode and SEED_YEAR = @inYear
    -- Insert statements for procedure here
	SELECT @outValue=SEED_VALUE-1 FROM BARCODE_SEED where DEPT_ID = @inDeptID and SECLV_CODE = @inSeclvCode and SEED_YEAR = @inYear
	
	IF(@outValue=99999)
		BEGIN
				UPDATE BARCODE_SEED SET SEED_VALUE = 1
				UPDATE BARCODE_SEED SET SEED_VALUE = SEED_VALUE + 1
				SELECT @outValue=SEED_VALUE-1 FROM BARCODE_SEED where DEPT_ID = @inDeptID and SECLV_CODE = @inSeclvCode and SEED_YEAR = @inYear	
		END

    COMMIT
	RETURN @outValue
END

go

---兼职审批配置与管理员配置部门共用20151126
if not exists (select 1 from syscolumns where id=object_id('SEC_DEPT_ADMIN') and name='TYPEFLAG')
	alter table SEC_DEPT_ADMIN add TYPEFLAG nvarchar(64) not null default 'MANAGE';
go

---用途表添加type，TYPE=Y表示用途，TYPE=N表示特殊打印的制作类型-
if not exists (select 1 from syscolumns where id=object_id('SYS_USAGE') and name='TYPE')
begin
	alter table SYS_USAGE add TYPE char(1) not null default 'Y';
	---update SYS_USAGE set type='Y';
end
go

if not exists (select 1 from SEC_OPER where OPER_CODE='01020316' and OPER_NAME='特殊打印类型管理')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020316','特殊打印类型管理','admin','basic/managespecialpapertype.action','_image/ico/default.gif','N','N');
go

---修改event_copy表中字段      
alter table EVENT_COPY
alter column ORIGINALID nvarchar(max)

alter table EVENT_COPY
alter column FILE_NAME nvarchar(max)
go
/*==============================================================*/
/* Table: EVENT_SPACECD                                         */
/*==============================================================*/
if not exists (select 1 from  sysobjects where  id = object_id('EVENT_SPACECD') and type = 'U')
create table EVENT_SPACECD(
	ID					INT primary key identity(1,1),
	EVENT_CODE			VARCHAR(64)			    null,
	USER_IIDD			VARCHAR(100)			null,
	DEPT_ID				VARCHAR(100)			null,
	APPLY_USER_IIDD     VARCHAR(100)			null,
    APPLY_DEPT_ID       VARCHAR(100)			null,
    SCOPE_DEPT_ID       VARCHAR(100)			null,
    SCOPE_DEPT_NAME     VARCHAR(100)			null,
	SECLV_CODE			INT						null,
	---USAGE_CODE          INT						null,
	SUMM				VARCHAR(1024)			null,
	APPLY_TIME			DATETIME				null,
	FILE_TYPE			INT						null,
	SCOPE				VARCHAR(64)				null,
	ENTER_NUM			INT						null,
	ENTER_CODE			VARCHAR(100)			null,
	CD_TYPE             VARCHAR(64)             null,
	SPACECD_TYPE        VARCHAR(64)             null,
	JOB_CODE 			varchar(100) 		    null,
	PROJECT_CODE        varchar(1024)           null,
    ASSIGN_STATUS       INT						null,
    USAGE_CODE          VARCHAR(64)             null,
)
go
/*==============================================================*/
/* Table: ENTITY_SPACECD                                             */
/*==============================================================*/
if not exists (select 1 from  sysobjects where  id = object_id('ENTITY_SPACECD') and type = 'U')
create table ENTITY_SPACECD (
    ID					int primary key   identity(1,1),
   EVENT_CODE			VARCHAR(64)			 null,
   BARCODE				nvarchar(64)         null,
   PDF417CODE           nvarchar(1024)       null,
   CONF_CODE            nvarchar(1024)       null,
   DEPT_ID              nvarchar(64)         null,
   DEPT_NAME            nvarchar(64)         null,
   USER_IIDD            nvarchar(64)         null,
   USER_NAME            nvarchar(64)         null,
   DUTY_USER_IIDD       nvarchar(64)         null,
   DUTY_USER_NAME       nvarchar(64)         null,
   DUTY_DEPT_ID         nvarchar(64)         null,
   DUTY_DEPT_NAME       nvarchar(64)         null,
   LEADIN_TIME			datetime             null,
   SECLV_CODE           int                  null,
   CD_TYPE              nvarchar(64)         null,
   PROJECT_CODE         nvarchar(1024)       null,
   FILE_LIST			varchar(1024)		 null,
   FILE_NUM				int					 null,
   SPACECD_STATE        int                  not null default 0,
   CREATE_TYPE          nvarchar(64)         not null default 'LEADIN' check (CREATE_TYPE in ('LEADIN')),
   SCOPE                nvarchar(64)         not null default 'PERSON' check (SCOPE in ('PERSON','DEPT')),
   SCOPE_DEPT_ID        nvarchar(100)        null,
   SCOPE_DEPT_NAME      nvarchar(100)        null,
   JOB_CODE             nvarchar(64)         null,
   comment				nvarchar(2048)       null,
   summ					nvarchar(2048)       null,
   SPACECD_TYPE         nvarchar(64)         null,
   BORROW_EVENT_CODE    VARCHAR(64)			 null, 
   EXT_CODE             nvarchar(255)        null,
   PAINTING_STATUS      int                  null,
   PAINTING_TIME        datetime             null,
)
go
CREATE PROCEDURE [dbo].[CREATEBARCODE_1]
@outValue bigint output,
@count int 
AS
BEGIN
	BEGIN TRAN
-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	UPDATE BARCODE_SEED SET SEED_VALUE = SEED_VALUE + @count
    -- Insert statements for procedure here
	SELECT @outValue=SEED_VALUE-1 FROM BARCODE_SEED
	
	COMMIT
	
	IF(@outValue=9999999999999)
		BEGIN
			BEGIN TRAN
				UPDATE BARCODE_SEED SET SEED_VALUE = 1
				UPDATE BARCODE_SEED SET SEED_VALUE = SEED_VALUE + @count
				SELECT @outValue=SEED_VALUE-1 FROM BARCODE_SEED
				COMMIT	
		END

	RETURN @outValue
END

go




--- 添加空白盘管理员
if not exists (select 1 from SEC_ROLE where ROLE_ID = '555')
		INSERT INTO SEC_ROLE(ROLE_ID,ROLE_NAME,SUBSYS_CODE,ROLE_DESC,ROLE_TYPE,ROLE_SPEC_KEY) VALUES('555','空白盘管理员','admin','负责管理空白盘',3,'');

if not exists (select 1 from SEC_OPER where OPER_CODE = '0161')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('0161','空白盘管理','admin',NULL,NULL,'N','Y');

---------录入---------

---申请录入
if not exists (select 1 from SEC_OPER where OPER_CODE = '016101')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('016101','申请录入','admin',NULL,NULL,'N','Y');

---空白盘录入
if not exists (select 1 from SEC_OPER where OPER_CODE = '01610101')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01610101','空白盘录入','admin','disc/adddeptspacecd.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '555' and A.OPER_CODE = '01610101')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('555','01610101');
go

---空白盘录入记录
if not exists (select 1 from SEC_OPER where OPER_CODE = '01610102')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01610102','空白盘录入记录','admin','disc/managespacecdenterlistjob.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '555' and A.OPER_CODE = '01610102')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('555','01610102');
go

---------领用---------

---申请领用
if not exists (select 1 from SEC_OPER where OPER_CODE = '016102')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('016102','申请领用','admin',NULL,NULL,'N','Y');

---空白盘领用
if not exists (select 1 from SEC_OPER where OPER_CODE = '01610201')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01610201','空白盘领用','admin','disc/viewborrowspacecd.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01610201')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01610201');
go

---空白盘领用记录
if not exists (select 1 from SEC_OPER where OPER_CODE = '01610202')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01610202','空白盘领用记录','admin','disc/spacecdapply.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01610202')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01610202');
go

---------管理---------

---空白盘管理
if not exists (select 1 from SEC_OPER where OPER_CODE = '016104')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('016104','空白盘管理','admin',NULL,NULL,'N','Y');

---部门空白盘管理
if not exists (select 1 from SEC_OPER where OPER_CODE = '01610401')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01610401','部门空白盘管理','admin','disc/viewdeptspacecdledger.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '555' and A.OPER_CODE = '01610401')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('555','01610401');
go
---个人空白盘管理
if not exists (select 1 from SEC_OPER where OPER_CODE = '01610402')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01610402','个人空白盘管理','admin','disc/viewselfspacecdledger.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01610402')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01610402');
go
---已分发列表
if not exists (select 1 from SEC_OPER where OPER_CODE = '01610403')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01610403','已分发列表','admin',' disc/viewgivespacecdledger.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '555' and A.OPER_CODE = '01610403')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('555','01610403');
go
---部门中转盘管理
if not exists (select 1 from SEC_OPER where OPER_CODE = '01610404')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01610404','部门中转盘管理','admin','disc/viewdeptchangespacecdledger.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '555' and A.OPER_CODE = '01610404')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('555','01610404');
go

---------审批---------

---空白盘领用审批
if not exists (select 1 from SEC_OPER where OPER_CODE = '016103')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('016103','空白盘领用审批','admin',NULL,NULL,'N','Y');

---待审批列表
if not exists (select 1 from SEC_OPER where OPER_CODE = '01610301')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01610301','待审批列表','admin','disc/managespacecdaprvjob.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '10' and A.OPER_CODE = '01610301')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('10','01610301');
go

---查看审批记录
if not exists (select 1 from SEC_OPER where OPER_CODE = '01610302')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01610302','查看审批记录','admin','disc/viewspacecdhandlejob.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '10' and A.OPER_CODE = '01610302')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('10','01610302');
go
alter table ENTITY_CD drop constraint [CKC_CREATE_TYPE_ENTITY_C];
go
alter table ENTITY_CD add constraint [CKC_CREATE_TYPE_ENTITY_C] check([CREATE_TYPE] in ('BURN','LEADIN','CHANGESPACECD'));
go

---修改表EVENT_SPACECD

if not exists (select 1 from syscolumns where id=object_id('ENTITY_SPACECD') and name='PAINTING_STATUS')
	alter table ENTITY_SPACECD add PAINTING_STATUS int null;
go
if not exists (select 1 from syscolumns where id=object_id('EVENT_SPACECD') and name='JOB_CODE')
	alter table EVENT_SPACECD add JOB_CODE varchar(100);
go
if not exists (select 1 from syscolumns where id=object_id('EVENT_SPACECD') and name='PROJECT_CODE')
	alter table EVENT_SPACECD add PROJECT_CODE varchar(1024);
go
if not exists (select 1 from syscolumns where id=object_id('EVENT_SPACECD') and name='APPLY_USER_IIDD')
	alter table EVENT_SPACECD add APPLY_USER_IIDD varchar(100);
go
if not exists (select 1 from syscolumns where id=object_id('EVENT_SPACECD') and name='APPLY _DEPT_ID ')
	alter table EVENT_SPACECD add APPLY_DEPT_ID  varchar(100);
go
if not exists (select 1 from syscolumns where id=object_id('EVENT_SPACECD') and name='SCOPE_DEPT_ID  ')
	alter table EVENT_SPACECD add SCOPE_DEPT_ID   varchar(100);
go
if not exists (select 1 from syscolumns where id=object_id('EVENT_SPACECD') and name='SCOPE_DEPT_NAME  ')
	alter table EVENT_SPACECD add SCOPE_DEPT_NAME   varchar(100);
go
if not exists (select 1 from syscolumns where id=object_id('EVENT_SPACECD') and name='SPACECD_TYPE')
	alter table EVENT_SPACECD add SPACECD_TYPE varchar(100);
go
if not exists (select 1 from syscolumns where id=object_id('EVENT_SPACECD') and name='CD_TYPE')
	alter table EVENT_SPACECD add CD_TYPE varchar(100);
go
if not exists (select 1 from syscolumns where id=object_id('EVENT_SPACECD') and name='ASSIGN_STATUS')
	alter table EVENT_SPACECD add ASSIGN_STATUS int null;
go

if not exists (select 1 from syscolumns where id=object_id('ENTITY_SPACECD') and name='BORROW_EVENT_CODE')
	alter table ENTITY_SPACECD add BORROW_EVENT_CODE varchar(64);
go
if not exists (select 1 from syscolumns where id=object_id('ENTITY_SPACECD') and name='SPACECD_TYPE')
	alter table ENTITY_SPACECD add SPACECD_TYPE varchar(100);
go
if not exists (select 1 from syscolumns where id=object_id('ENTITY_SPACECD') and name='PAINTING_TIME')
	alter table ENTITY_SPACECD add PAINTING_TIME datetime null;
go
if not exists (select 1 from syscolumns where id=object_id('ENTITY_SPACECD') and name='PAINTING_STATUS')
	alter table ENTITY_SPACECD add PAINTING_STATUS int null;
go
if not exists (select 1 from syscolumns where id=object_id('ENTITY_SPACECD') and name='PDF417CODE')
	alter table ENTITY_SPACECD add PDF417CODE varchar(1024);
go

---如果是旧数据库，空白盘模块菜单需要修改，执行以下语句进行合并
if exists (select 1 from SEC_OPER where OPER_CODE='016101' and OPER_NAME='申请录入')
	update SEC_OPER set OPER_NAME='空白盘录入' where OPER_CODE='016101';
go
if exists (select 1 from SEC_OPER where OPER_CODE='01610101' and OPER_NAME='空白盘录入')
	update SEC_OPER set OPER_NAME='申请录入',WEB_URL='disc/addspacecdjob.action' where OPER_CODE='01610101';
go
if exists (select 1 from SEC_OPER where OPER_CODE='01610102' and OPER_NAME='空白盘录入记录')
	update SEC_OPER set OPER_NAME='录入记录' where OPER_CODE='01610102';
go
if exists (select 1 from SEC_OPER where OPER_CODE='016102' and OPER_NAME='申请领用')
	update SEC_OPER set OPER_NAME='空白盘申领' where OPER_CODE='016102';
go
if exists (select 1 from SEC_OPER where OPER_CODE='01610201' and OPER_NAME='空白盘领用')
	update SEC_OPER set OPER_NAME='申请领用',WEB_URL='disc/viewspacecdstock.action' where OPER_CODE='01610201';
go
if exists (select 1 from SEC_OPER where OPER_CODE='01610202' and OPER_NAME='空白盘领用记录')
	update SEC_OPER set OPER_NAME='领用申请记录',WEB_URL='disc/managespacecdlistjob.action' where OPER_CODE='01610202';
go
if exists (select 1 from SEC_OPER where OPER_CODE='01610401' and OPER_NAME='部门空白盘管理')
	update SEC_OPER set OPER_NAME='空白盘台帐',WEB_URL='disc/viewdeptspacecdledger.action'where OPER_CODE='01610401';
go
if exists (select 1 from SEC_OPER where OPER_CODE='01610402' and OPER_NAME='个人空白盘管理')
	update SEC_OPER set OPER_NAME='个人空白盘台帐' where OPER_CODE='01610402';
go
if exists (select 1 from SEC_OPER where OPER_CODE='01610403' and OPER_NAME='已分发列表')
	update SEC_OPER set OPER_NAME='待分配列表', WEB_URL='disc/viewwaitingjoblist.action' where OPER_CODE='01610403';
go
if exists (select 1 from SEC_OPER where OPER_CODE='01610404' and OPER_NAME='部门中转盘管理')
	update SEC_OPER set OPER_NAME='已分发列表', WEB_URL='disc/viewgivespacecdledger.action' where OPER_CODE='01610404';
go
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01610402');
INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('555','01610403');

if exists (select 1 from SEC_ROLE_OPER where OPER_CODE='01610405')
delete from SEC_ROLE_OPER where OPER_CODE='01610405';
go
if exists (select 1 from SEC_OPER where OPER_CODE='01610405')
delete from SEC_OPER where OPER_CODE='01610405';
go

/*==============================================================*/
/* Table: SYS_BARCODE                                           */
/*==============================================================*/
create table SYS_BARCODE (
   BARCODE_CODE         int                  identity(1,1),
   BARCODE_NAME         nvarchar(256)        not null,
   SECLV_CODE           nvarchar(1024)       null,
   USAGE_CODE           nvarchar(1024)       null,
   CONSOLE_CODE         nvarchar(1024)       null,
   PROJECT_CODE         nvarchar(1024)       null,
   CONTENT              nvarchar(1024)       null,
   TEXTCONTENT          nvarchar(256)        null,
   BYPAGE               int                  not null default 0,
   FORM                 int                  not null,
   POSITION             int                  null,
   PAGENO               int                  null,
   PERPAGE              int                  null,
   CORD_X               int                  null,
   CORD_Y               int                  null,
   SIZE_X               int                  null,
   SIZE_Y               int                  null,
   IS_SEALED            char(1)              null default 'N',
   IS_DEFAULT           char(1)              null default 'N',
   constraint PK_SYS_BARCODE primary key (BARCODE_CODE)
)
go

---添加条码表
if not exists (select 1 from  sysobjects where  id = object_id('BARCODE_COMPARE') and type = 'U')
create table BARCODE_COMPARE (
   ID                  int		             identity(1,1),
   BARCODE	           nvarchar(64)		     null,
   PDFCODE	           nvarchar(1024)        null
   constraint PK_BARCODE_COMPARE  primary key (ID)
)
go

--- 与38所保密系统集成，打印申请表增加集成标志wsflag(0未传递，1已传递),审批标志approve_status(0未审批，1审批通过)
---
if not exists (select 1 from syscolumns where id=object_id('event_print') and name='wsflag')
	alter table event_print add wsflag int not null default 0;
go
if not exists (select 1 from syscolumns where id=object_id('event_print') and name='approve_status')
	alter table event_print add approve_status int not null default 0;
go

--- 与38所保密系统集成，刻录申请表增加审批标志approve_status(0未审批，1审批通过)
if not exists (select 1 from syscolumns where id=object_id('event_burn') and name='approve_status')
	alter table event_burn add approve_status int not null default 0;
go


---与38所保密系统集成，添加保密系统传递条码内容表WS_BARCODEINFO
if not exists (select 1 from  sysobjects where  id = object_id('WS_BARCODEINFO') and type = 'U')
create table WS_BARCODEINFO (
   ID                   int                  identity(1,1),
   JOBTYPE              int                  null,
   EVENT_CODE           nvarchar(64)         null,
   WFID                 nvarchar(100)        null,
   USER_IIDD            nvarchar(64)         null,
   DOC_NAME             nvarchar(512)        null,
   FILE_ORDER           nvarchar(512)        null,
   SEND_NO              nvarchar(512)        null,
   SECLV_CODE           int                  null,
   URGENT               nvarchar(512)        null,
   DISTRIBUTION         nvarchar(512)        null,
   ENTITY_TYPE			nvarchar(64)         null,
   FILE_SOURCE         	nvarchar(512)        null,
   FILE_LIST         	nvarchar(1024)       null,
   PDFCODE		        nvarchar(1024)       null,
   SECRET               nvarchar(64)         null,
   constraint PK_WS_BARCODEINFO  primary key (ID)
)
go


---在EVENT_PRINT表中，修改PRINT_TYPE字段的约束条件      
alter table EVENT_PRINT drop constraint [CKC_PRINT_TYPE_EVENT_PR];
go
alter table EVENT_PRINT add constraint [CKC_PRINT_TYPE_EVENT_PR] check([PRINT_TYPE] in (1,2,3,4,5));
go

--38所集成保存打印任务连接
if not exists (select 1 from  sysobjects where  id = object_id('WS_PRINT_LINK') and type = 'U')
create table WS_PRINT_LINK (
   ID                  int                   identity(1,1),
   USER_IIDD           nvarchar(64)          null,
   LINK                nvarchar(2048)        null, 
   constraint PK_WS_PRINT_LINK  primary key (ID)
)
go

---EVENT_IMPORT表中添加中电集团新条码规则字段
if not exists (select 1 from syscolumns where id=object_id('EVENT_IMPORT') and name='PAPER_BARCODE')
	alter table EVENT_IMPORT add PAPER_BARCODE nvarchar(64) null
go
if not exists (select 1 from syscolumns where id=object_id('EVENT_IMPORT') and name='COMPANY')
	alter table EVENT_IMPORT add COMPANY nvarchar(64) null
go
if not exists (select 1 from syscolumns where id=object_id('EVENT_IMPORT') and name='PUBLISH_TYPE')
	alter table EVENT_IMPORT add PUBLISH_TYPE nvarchar(64) null
go
if not exists (select 1 from syscolumns where id=object_id('EVENT_IMPORT') and name='FILEID')
	alter table EVENT_IMPORT add FILEID nvarchar(128) null
go
if not exists (select 1 from syscolumns where id=object_id('EVENT_IMPORT') and name='COMPANY_SEND')
	alter table EVENT_IMPORT add COMPANY_SEND nvarchar(64) null
go
if not exists (select 1 from syscolumns where id=object_id('EVENT_IMPORT') and name='URGENCY_LV')
	alter table EVENT_IMPORT add URGENCY_LV nvarchar(64) null
go
if not exists (select 1 from syscolumns where id=object_id('EVENT_IMPORT') and name='FILED_DATE')
	alter table EVENT_IMPORT add FILED_DATE nvarchar(64) null
go
if not exists (select 1 from syscolumns where id=object_id('EVENT_IMPORT') and name='PUBLISH_LIMITS')
	alter table EVENT_IMPORT add PUBLISH_LIMITS nvarchar(64) null
go
if not exists (select 1 from syscolumns where id=object_id('EVENT_IMPORT') and name='CREATE_TYPE')
	alter table EVENT_IMPORT add CREATE_TYPE nvarchar(64) null
go
if not exists (select 1 from syscolumns where id=object_id('EVENT_IMPORT') and name='CREATE_USER_NAME')
	alter table EVENT_IMPORT add CREATE_USER_NAME nvarchar(64) null
go
---ENTITY_PAPER表中添加中电集团新条码规则字段
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='COMPANY')
	alter table ENTITY_PAPER add COMPANY nvarchar(64) null
go
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='PUBLISH_TYPE')
	alter table ENTITY_PAPER add PUBLISH_TYPE nvarchar(64) null
go
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='COMPANY_SEND')
	alter table ENTITY_PAPER add COMPANY_SEND nvarchar(64) null
go
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='URGENCY_LV')
	alter table ENTITY_PAPER add URGENCY_LV nvarchar(64) null
go
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='FILED_DATE')
	alter table ENTITY_PAPER add FILED_DATE nvarchar(64) null
go
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='PUBLISH_LIMITS')
	alter table ENTITY_PAPER add PUBLISH_LIMITS nvarchar(64) null
go
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='SUMM')
	alter table ENTITY_PAPER add SUMM nvarchar(1024) null
go
---ENTITY_PAPER表中添加中电集团台账字段
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='SEND_ID')
	alter table ENTITY_PAPER add SEND_ID nvarchar(64) null
go
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='SEND_MODE')
	alter table ENTITY_PAPER add SEND_MODE nvarchar(64) null
go
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='BOX_NUM')
	alter table ENTITY_PAPER add BOX_NUM nvarchar(64) null
go
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='FILE_ORDER_NUM')
	alter table ENTITY_PAPER add FILE_ORDER_NUM nvarchar(64) null
go
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='MANAGE_OPINION')
	alter table ENTITY_PAPER add MANAGE_OPINION nvarchar(1024) null
go
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='RECEIVE_ID')
	alter table ENTITY_PAPER add RECEIVE_ID nvarchar(64) null
go
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='REMARK')
	alter table ENTITY_PAPER add REMARK nvarchar(1024) null
go
if not exists (select 1 from SEC_ROLE where ROLE_ID = '666')
	INSERT INTO SEC_ROLE(ROLE_ID,ROLE_NAME,SUBSYS_CODE,ROLE_DESC,ROLE_TYPE,ROLE_SPEC_KEY) 
		VALUES('666','无审批独立模式','admin','不需要审批的独立模式，配置此角色可直接输出',3,'')
go
---SYS_CONSOLE表中添加IS_UPDATE字段
if not exists (select 1 from syscolumns where id=object_id('SYS_CONSOLE') and name='IS_UPDATE')
	alter table SYS_CONSOLE add IS_UPDATE CHAR(1) null default 'N'
go

ALTER PROCEDURE [dbo].[CREATEBARCODE_CSIC]
(@outValue bigint output,
 @inDeptID nvarchar(100),
 @inSeclvCode int,
 @inYear int)
AS
 
BEGIN
	BEGIN TRAN
-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
   
	if not exists (select 1 from BARCODE_SEED where SECLV_CODE = @inSeclvCode and SEED_YEAR = @inYear)
    BEGIN
         insert into BARCODE_SEED (SECLV_CODE,SEED_VALUE,SEED_YEAR) values (@inSeclvCode,1,@inYear)
    END
    
	UPDATE BARCODE_SEED SET SEED_VALUE = SEED_VALUE + 1 where SECLV_CODE = @inSeclvCode and SEED_YEAR = @inYear
    -- Insert statements for procedure here
	SELECT @outValue=SEED_VALUE-1 FROM BARCODE_SEED where SECLV_CODE = @inSeclvCode and SEED_YEAR = @inYear
	
	IF(@outValue=99999)
		BEGIN
				UPDATE BARCODE_SEED SET SEED_VALUE = 1
				UPDATE BARCODE_SEED SET SEED_VALUE = SEED_VALUE + 1
				SELECT @outValue=SEED_VALUE-1 FROM BARCODE_SEED where SECLV_CODE = @inSeclvCode and SEED_YEAR = @inYear	
		END

    COMMIT
	RETURN @outValue
END

----录入错误台账信息修改/删除模块201603
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110108')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110108','删除修改记录','admin','ledger/papermodifyapply.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01110108')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110108');
go
----扫描录入记录
if exists (select 1 from SEC_OPER where OPER_CODE = '01060502')
	update SEC_OPER set WEB_URL='enter/manageenterscanlistjob.action' where OPER_CODE='01060502';
go
---特殊打印模块添加管理员录入记录
if not exists (select 1 from SEC_OPER where OPER_CODE = '01030402')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01030402','特殊打印记录','admin','print/specialprintpaperhistory.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '14' and A.OPER_CODE = '01030402')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('14','01030402');	
go
---个人非密外发闭环模块
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110902')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110902','个人外发闭环任务','admin','ledger/selfclosedsendprocess.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01110902')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110902');	
go

--回收送销开关
if not exists (select 1 from SEC_OPER where OPER_CODE = '01020318')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020318','回收送销流程配置','admin','basic/configrecoverdestroy.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01020318')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('2','01020318');
go
---已归档文件的销毁
---if not exists (select 1 from SEC_OPER where OPER_CODE = '01130703')
---	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130703','已归档记录','admin','ledger/managefilepaperevent.action','_image/ico/default.gif','N','N');
---if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '9' and A.OPER_CODE = '01130703')
---	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('9','01130703');
---go
----已归档载体二次处理申请表
if not exists (select 1 from sysobjects where id = object_id('EVENT_TEMP') and type = 'U')
	create table EVENT_TEMP (
	   ID                   int                  identity(1,1),
	   EVENT_CODE           nvarchar(64)         null,
	   USER_IIDD            nvarchar(100)        null,
	   DEPT_ID              nvarchar(100)        null,
	   SECLV_CODE           int                  null,
	   PROJECT_CODE         nvarchar(64)         null,
	   USAGE_CODE           nvarchar(64)         null,
	   APPLY_TIME           datetime             null,
	   SUMM                 nvarchar(1024)       null,
	   PAGE_NUM             nvarchar(100)        null,
	   SCOPE_DEPT_ID        nvarchar(100)        null,
	   SCOPE_DEPT_NAME      nvarchar(100)        null,
	   ENTITY_TYPE          nvarchar(100)        null,
	   BARCODE              nvarchar(100)        null,
	   FILE_NAME            nvarchar(100)        null,
	   STATUE               nvarchar(10)         null,
	   JOB_CODE             nvarchar(100)        null,
	   HIS_JOB_CODE         nvarchar(1024)       null,
	   constraint PK_EVENT_TEMP primary key (ID)
	)
go
---已归档光盘的销毁
---if not exists (select 1 from SEC_OPER where OPER_CODE = '01130803')
---	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130803','已归档记录','admin','ledger/managefilecdevent.action','_image/ico/default.gif','N','N');
---if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '9' and A.OPER_CODE = '01130803')
---	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('9','01130803');
---go


---流程表增加文件阅读状态
if not exists (select 1 from syscolumns where id=object_id('job_process') and name='FILE_READ_STATUS')
	alter table job_process add FILE_READ_STATUS int not null default 0;
go

---作业表增加文件阅读状态
if not exists (select 1 from syscolumns where id=object_id('event_print') and name='FILE_IS_READ')
	alter table event_print add FILE_IS_READ int not null default 0;
go

---台帐表中增加监销人字段
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='SUPERVISE_USER_IIDD')
	alter table ENTITY_PAPER add SUPERVISE_USER_IIDD nvarchar(64) null
go

---自主销毁申请记录
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110111')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110111','监销申请记录','admin','ledger/manageselfdestroypaperjob.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01110111')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110111');
go

---监销确认页面
if not exists (select 1 from SEC_OPER where OPER_CODE = '01130110')
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130110','监销确认','admin','ledger/supervisedestroypaper.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01130110')
    INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01130110'); 
    
---添加归档文件借用按钮
if exists (select 1 from SYS_MODULE where MODULE_CODE = 'BORROW')
begin
if exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')and not exists (select 1 from SEC_OPER where OPER_NAME = '部门文件借用')
	UPDATE SEC_OPER SET OPER_NAME='部门文件借用' WHERE OPER_CODE='01080101';
if exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN') and not exists (select 1 from SEC_OPER where OPER_NAME = '部门光盘借用')
	UPDATE SEC_OPER SET OPER_NAME='部门光盘借用' WHERE OPER_CODE='01080102';
if exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')and not exists (select 1 from SEC_OPER where OPER_NAME = '归档文件借用')
	begin
		DELETE FROM SEC_ROLE_OPER WHERE ROLE_ID = '11' AND OPER_CODE = '01080103';
		DELETE FROM SEC_OPER WHERE OPER_CODE = '01080103';
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01080103','归档文件借用','admin','borrow/viewborrowfilepaper.action','_image/ico/default.gif','N','N');
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01080103');
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01080105','查看申请记录','admin','borrow/viewpersonalborrowledger.action','_image/ico/default.gif','N','N');
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01080105');
	end
if exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN')and not exists (select 1 from SEC_OPER where OPER_NAME = '归档光盘借用')
	begin
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01080104','归档光盘借用','admin','borrow/viewborrowfilecd.action','_image/ico/default.gif','N','N');
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01080104');
	end
if exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')and not exists (select 1 from SEC_OPER where OPER_NAME = '部门文件交接')
	UPDATE SEC_OPER SET OPER_NAME='部门文件交接' WHERE OPER_CODE='01080301';
if exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN') and not exists (select 1 from SEC_OPER where OPER_NAME = '部门光盘交接')
	UPDATE SEC_OPER SET OPER_NAME='部门光盘交接' WHERE OPER_CODE='01080302';
if exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')and not exists (select 1 from SEC_OPER where OPER_NAME = '归档文件交接')
	begin
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01080303','归档文件交接','admin','borrow/confirmfilepaperborrow.action','_image/ico/default.gif','N','N');
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('9','01080303');
	end
if exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN')and not exists (select 1 from SEC_OPER where OPER_NAME = '归档光盘交接')
	begin
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01080304','归档光盘交接','admin','borrow/confirmfilecdborrow.action','_image/ico/default.gif','N','N');
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('9','01080304');
	end	
end
go

---增加客户端审批消息提醒和回收消息提醒配置
--审批消息提醒开关
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'APPROVE_ON_OFF')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('APPROVE_ON_OFF','审批消息提醒开关',null,'CLIENT_MSG',1);
go

--回收消息提醒开关
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'RETRIEVE_ON_OFF')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('RETRIEVE_ON_OFF','回收消息提醒开关',null,'CLIENT_MSG',0);
go

---个人涉密文件台账查看
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110112')
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110112','个人涉密文件台帐','admin','ledger/viewselfsecuritypaperledger.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01110112')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110112');
go

---光盘台帐表中增加监销人字段
if not exists (select 1 from syscolumns where id=object_id('ENTITY_CD') and name='SUPERVISE_USER_IIDD')
	alter table ENTITY_CD add SUPERVISE_USER_IIDD nvarchar(64) null
go

---光盘自主销毁申请记录
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110207')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110207','监销申请记录','admin','ledger/manageselfdestroycdjob.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01110207')
    INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110207');
go

---光盘监销确认页面
if not exists (select 1 from SEC_OPER where OPER_CODE = '01130308')
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130308','监销确认','admin','ledger/supervisedestroycd.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01130308')
    INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01130308'); 
go

---打印刻录作业表中增加代理人字段
if not exists (select 1 from syscolumns where id=object_id('EVENT_PRINT') and name='PROXYPRINT_USER_IIDD')
	alter table EVENT_PRINT add PROXYPRINT_USER_IIDD nvarchar(100) null
go

if not exists (select 1 from syscolumns where id=object_id('EVENT_BURN') and name='PROXYBURN_USER_IIDD')
	alter table EVENT_BURN add PROXYBURN_USER_IIDD nvarchar(100) null
go

---可输出交接单密级配置
if not exists (select 1 from SEC_OPER where OPER_CODE = '01020319')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020319','可输出交接单的密级配置','admin','basic/configreceiptvsseclv.action','_image/ico/default.gif','N','N');
go
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01020319') 
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('2','01020319');
go

---录入作业表增加原文件/光盘编号字段
if not exists (select 1 from syscolumns where id=object_id('EVENT_IMPORT') and name='SRC_CODE')
	alter table EVENT_IMPORT add SRC_CODE nvarchar(128) null
go

---文件台账表entity_paper增加原文件编号字段
if not exists (select 1 from syscolumns where id=object_id('ENTITY_PAPER') and name='SRC_CODE')
	alter table ENTITY_PAPER add SRC_CODE nvarchar(128) null
go

---光盘台账表entity_cd增加原光盘编号字段
if not exists (select 1 from syscolumns where id=object_id('ENTITY_CD') and name='SRC_CODE')
	alter table ENTITY_CD add SRC_CODE nvarchar(128) null
go

---磁介质台账表entity_device增加原编号、磁介质归属、监销人、销毁时间字段
if not exists (select 1 from syscolumns where id=object_id('ENTITY_DEVICE') and name='SRC_CODE')
	alter table ENTITY_DEVICE add SRC_CODE nvarchar(128) null
go

if not exists (select 1 from syscolumns where id=object_id('ENTITY_DEVICE') and name='SCOPE')
	alter table ENTITY_DEVICE add SCOPE nvarchar(64) null
go

if not exists (select 1 from syscolumns where id=object_id('ENTITY_DEVICE') and name='SUPERVISE_USER_IIDD')
	alter table ENTITY_DEVICE add SUPERVISE_USER_IIDD nvarchar(64) null
go

if not exists (select 1 from syscolumns where id=object_id('ENTITY_DEVICE') and name='DESTROY_TIME')
	alter table ENTITY_DEVICE add DESTROY_TIME datetime null
go

---增加文件监销确认记录菜单
if not exists (select 1 from SEC_OPER where OPER_CODE = '01130111')
   INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130111','已监销确认记录','admin','ledger/viewsupervisedestroypaper.action','_image/ico/default.gif','N','N');
go
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01130111')
   INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01130111');
go

---增加光盘监销确认记录菜单
if not exists (select 1 from SEC_OPER where OPER_CODE = '01130309')
   INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130309','已监销确认记录','admin','ledger/viewsupervisedestroycd.action','_image/ico/default.gif','N','N');
go
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01130309')
   INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01130309');
go

---增加个人磁介质录入申请菜单
if not exists (select 1 from SEC_OPER where OPER_CODE = '01060108')
   INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01060108','个人磁介质录入申请','admin','enter/addpersonaldeviceenterevent.action','_image/ico/default.gif','N','N');	
go
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01060108')
   INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01060108');
go

---增加个人磁介质录入管理菜单
if not exists (select 1 from SEC_OPER where OPER_CODE = '01060306')
   INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01060306','个人磁介质录入管理','admin','enter/manageenterdevicelist.action?selftype=Y','_image/ico/default.gif','N','N');
go
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '6' and A.OPER_CODE = '01060306')
   INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('6','01060306');
go

---增加个人磁介质录入记录菜单
if not exists (select 1 from SEC_OPER where OPER_CODE = '01060403')
   INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01060403','个人磁介质录入记录','admin','enter/enterdevicehistory.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '6' and A.OPER_CODE = '01060403')
   INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('6','01060403');
go

---增加个人磁介质自主销毁：监销申请记录、监销确认、已监销确认记录页面
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110303')
   INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110303','监销申请记录','admin','ledger/manageselfdestroydevicejob.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01110303')
   INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110303');

if not exists (select 1 from SEC_OPER where OPER_CODE = '011312')
   INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('011312','个人磁介质销毁','admin',NULL,NULL,'N','Y');

if not exists (select 1 from SEC_OPER where OPER_CODE = '01131201')
   INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01131201','监销确认','admin','ledger/supervisedestroydevice.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01131201')
   INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01131201');

if not exists (select 1 from SEC_OPER where OPER_CODE = '01131202')
   INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01131202','已监销确认记录','admin','ledger/viewsupervisedestroydevice.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01131202')
   INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01131202');

---修改部门台账下菜单名, 部门保密手册   更改为   部门科研工作手册
if exists (select 1 from SEC_OPER where OPER_CODE = '01120105' and OPER_NAME = '部门保密手册')
   UPDATE SEC_OPER SET OPER_NAME = '部门科研工作手册' where OPER_CODE = '01120105';
---修改部门载体借用管理下菜单名, 保密手册领用   更改为   科研工作手册领用
if exists (select 1 from SEC_OPER where OPER_CODE = '01080108' and OPER_NAME = '保密手册领用')
   UPDATE SEC_OPER SET OPER_NAME = '科研工作手册领用' where OPER_CODE = '01080108';

---增加科研工作手册借用台账、监销申请记录、自主销毁:监销确认、已监销确认记录
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110403')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110403','科研工作手册借用台账','admin','borrow/viewselfsecretpaperledger.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01110403')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110403');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110404')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110404','科研工作手册监销申请记录','admin','ledger/manageselfdestroysecretpaperjob.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01110404')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110404');
		
if not exists (select 1 from SEC_OPER where OPER_CODE = '011320')
   INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('011320','科研工作手册销毁','admin',NULL,NULL,'N','Y');

if not exists (select 1 from SEC_OPER where OPER_CODE = '01132001')
   INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01132001','监销确认','admin','ledger/supervisedestroysecretpaper.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01132001')
   INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01132001');

if not exists (select 1 from SEC_OPER where OPER_CODE = '01132002')
   INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01132002','已监销确认记录','admin','ledger/viewsupervisedestroysecretpaper.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01132002')
   INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01132002');
go

---修改磁介质管理模块下的菜单名
if exists (select 1 from SEC_OPER where OPER_CODE = '01070303' and OPER_NAME = '销毁申请列表')
   UPDATE SEC_OPER SET OPER_NAME = '监销申请记录' where OPER_CODE = '01070303';
if exists (select 1 from SEC_OPER where OPER_CODE = '010703' and OPER_NAME = '磁介质管理')
   UPDATE SEC_OPER SET OPER_NAME = '部门磁介质管理' where OPER_CODE = '010703';
if exists (select 1 from SEC_OPER where OPER_CODE = '01070301' and OPER_NAME = '磁介质录入')
   UPDATE SEC_OPER SET OPER_NAME = '部门磁介质录入' where OPER_CODE = '01070301';
if exists (select 1 from SEC_OPER where OPER_CODE = '01070302' and OPER_NAME = '磁介质管理')
   UPDATE SEC_OPER SET OPER_NAME = '部门磁介质管理' where OPER_CODE = '01070302';

----空白盘作业表增加usage_code字段
if not exists (select 1 from syscolumns where id=object_id('EVENT_SPACECD') and name='USAGE_CODE')
	alter table EVENT_SPACECD add USAGE_CODE varchar(64);
go

----增加空白盘管理模块(中物院机关使用空白盘管理模块，中物五所不使用，注意注释下面sql语句)
if not exists (select 1 from SEC_ROLE where ROLE_ID = '555')
    INSERT INTO SEC_ROLE(ROLE_ID,ROLE_NAME,SUBSYS_CODE,ROLE_DESC,ROLE_TYPE,ROLE_SPEC_KEY) VALUES('555','空白盘管理员','admin','负责管理空白盘',3,'');
go

if not exists (select 1 from SEC_OPER where OPER_CODE = '0161')
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('0161','空白盘管理','admin',NULL,NULL,'N','Y');
go
	
if not exists (select 1 from SEC_OPER where OPER_CODE = '016101')
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('016101','空白盘录入','admin',NULL,NULL,'N','Y');
go
	
if not exists (select 1 from SEC_OPER where OPER_CODE = '01610101')
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01610101','申请录入','admin','disc/addspacecdjob.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '555' and A.OPER_CODE = '01610101')
    INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('555','01610101');
go
	
if not exists (select 1 from SEC_OPER where OPER_CODE = '01610102')
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01610102','录入记录','admin','disc/managespacecdenterlistjob.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '555' and A.OPER_CODE = '01610102')
    INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('555','01610102');
go
	
if not exists (select 1 from SEC_OPER where OPER_CODE = '016102')
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('016102','空白盘领用','admin',NULL,NULL,'N','Y');
go
	
if not exists (select 1 from SEC_OPER where OPER_CODE = '01610201')
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01610201','申请领用','admin','disc/viewspacecdstock.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01610201')
    INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01610201');
go
	
if not exists (select 1 from SEC_OPER where OPER_CODE = '01610202')
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01610202','领用申请记录','admin','disc/managespacecdlistjob.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01610202')
    INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01610202');
go
	
if not exists (select 1 from SEC_OPER where OPER_CODE = '016103')	
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('016103','空白盘领用审批','admin',NULL,NULL,'N','Y');
go

if not exists (select 1 from SEC_OPER where OPER_CODE = '01610301')
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01610301','待审批列表','admin','disc/managespacecdaprvjob.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '10' and A.OPER_CODE = '01610301')
    INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('10','01610301');
go
	
if not exists (select 1 from SEC_OPER where OPER_CODE = '01610302')
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01610302','查看审批记录','admin','disc/viewspacecdhandlejob.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '10' and A.OPER_CODE = '01610302')
    INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('10','01610302');
go
	
if not exists (select 1 from SEC_OPER where OPER_CODE = '016104')
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('016104','空白盘管理','admin',NULL,NULL,'N','Y');
go

if not exists (select 1 from SEC_OPER where OPER_CODE = '01610401')
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01610401','空白盘台帐','admin','disc/viewdeptspacecdledger.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '555' and A.OPER_CODE = '01610401')
    INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('555','01610401');
go
	
if not exists (select 1 from SEC_OPER where OPER_CODE = '01610402')
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01610402','个人空白盘台帐','admin','disc/viewselfspacecdledger.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01610402')
    INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01610402');
go
	
if not exists (select 1 from SEC_OPER where OPER_CODE = '01610403')
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01610403','待分配列表','admin',' disc/viewwaitingjoblist.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '555' and A.OPER_CODE = '01610403')
    INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('555','01610403');
go
	
if not exists (select 1 from SEC_OPER where OPER_CODE = '01610404')
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01610404','已分发列表','admin','disc/viewgivespacecdledger.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '555' and A.OPER_CODE = '01610404')
    INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('555','01610404');
go

if exists (select 1 from SYS_MODULE where MODULE_CODE = 'PRINT')and not exists (select 1 from SEC_OPER where OPER_CODE = '01080305')
	begin
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01080305','部门文件确认','admin','borrow/confirmdeptpaperborrow.action','_image/ico/default.gif','N','N');
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01080305');
	end
go
if exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN')and not exists (select 1 from SEC_OPER where OPER_CODE = '01080306')
	begin
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01080306','部门光盘确认','admin','borrow/confirmdeptcdborrow.action','_image/ico/default.gif','N','N');
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01080306');
	end	
go

if not exists (select 1 from syscolumns where id=object_id('event_borrow') and name='is_sure')
	alter table event_borrow add IS_SURE nvarchar(64) not null default 'N';
go


--流转消息提醒开关
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'TRANSFER_ON_OFF')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('TRANSFER_ON_OFF','流转消息提醒开关',null,'CLIENT_MSG',0);
go

--借用消息提醒开关
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'BORROW_ON_OFF')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('BORROW_ON_OFF','借用消息提醒开关',null,'CLIENT_MSG',0);
go

---添加借用期限时间字段
if not exists (select 1 from syscolumns where id=object_id('EVENT_BORROW') and name='LIMIT_TIME')
	alter table EVENT_BORROW add LIMIT_TIME datetime null
go

---客户端消息配置
if not exists (select 1 from SEC_OPER where OPER_CODE = '01020324')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020324','客户端消息配置','admin','basic/configremind.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01020324')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('2','01020324');
go

---entity_paper表增加split_barcode字段
if not exists (select 1 from syscolumns where id=object_id('entity_paper') and name='SPLIT_BARCODE')
	alter table entity_paper add SPLIT_BARCODE nvarchar(64) null
go

ALTER PROCEDURE [dbo].[proc_getMsgs] (
    @userid nvarchar(64)
    )
    
AS 
 declare  @printMsg int ,@printAccessMsg int,@printDenyMsg int, @setVer nvarchar(100)
 declare  @printRetrieveMsg int , @burnRetrieveMsg int
 declare  @scanMsg int, @borrowMsg int, @transferMsg int
 Set @printMsg=0
 Set @printAccessMsg=0
 Set @printDenyMsg=0
 Set @printRetrieveMsg=0
 Set @burnRetrieveMsg=0
 Set @scanMsg=0
 Set @borrowMsg=0
 Set @transferMsg=0
 
 SELECT @printMsg = count(*) FROM CLIENT_MSG
    WHERE ACCEPT_USER_IIDD = @userid AND MESSAGE_TYPE = 1 AND IS_READ = 0;
 SELECT @printAccessMsg = count(*) FROM CLIENT_MSG
    WHERE ACCEPT_USER_IIDD = @userid AND MESSAGE_TYPE = 2 AND IS_READ = 0;
 SELECT @printDenyMsg = count(*) FROM CLIENT_MSG
    WHERE ACCEPT_USER_IIDD = @userid AND MESSAGE_TYPE = 3 AND IS_READ = 0;
 select @setVer = SET_VERSION  from SYS_CVS 
    where USER_IIDD = @userid;
SELECT @printRetrieveMsg = count(*) FROM CLIENT_MSG
    WHERE ACCEPT_USER_IIDD = @userid AND MESSAGE_TYPE = 4 AND OPER_TYPE = 'PRINT' AND IS_READ = 0;
SELECT @burnRetrieveMsg = count(*) FROM CLIENT_MSG
    WHERE ACCEPT_USER_IIDD = @userid AND MESSAGE_TYPE = 4 AND OPER_TYPE = 'BURN' AND IS_READ = 0;
SELECT @scanMsg = count(*) FROM CLIENT_MSG
    WHERE ACCEPT_USER_IIDD = @userid AND MESSAGE_TYPE = 6 AND OPER_TYPE = 'SCAN' AND IS_READ = 0;
 SELECT @borrowMsg = count(*) FROM CLIENT_MSG
    WHERE ACCEPT_USER_IIDD = @userid AND MESSAGE_TYPE = 7 AND OPER_TYPE = 'BORROW' AND IS_READ = 0;
 SELECT @transferMsg = count(*) FROM CLIENT_MSG
    WHERE ACCEPT_USER_IIDD = @userid AND MESSAGE_TYPE = 8 AND OPER_TYPE = 'TRANSFER_CONFIRM' AND IS_READ = 0;
  
SELECT @printMsg as N'printMsg',
  @printAccessMsg as N'printAccessMsg',
  @printDenyMsg as N'printDenyMsg',
  @setVer as N'setVer',
  @printRetrieveMsg as N'printRetrieveMsg',
  @burnRetrieveMsg as N'burnRetrieveMsg',
  @scanMsg as N'scanMsg',
  @borrowMsg as N'borrowMsg',
  @transferMsg as N'transferMsg'

go

---(默认关闭)中物7所 需求: 刻录模块限制用户提交文件数量 及 压缩类型文件, 并且可由系统管理员配置 文件数量 和 压缩类型
--- Table: SEC_CONFIG 设置默认系统配置
---
INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE) VALUES ('UPLOAD_BURNFILE_NUM','刻录上传文件数目','10','BURN');

INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE) VALUES ('UPLOAD_BURNFILE_ZIPTYPE','刻录上传文件限制压缩类型','.zip,.rar,.tar,.gzip,.z,.7-zip','BURN');

---
--- 为系统管理员添加刻录上传文件数目设置菜单 
--- 如果不存在对应记录，则INSERT数据，否则不做操作。
---
if not exists (select 1 from SEC_OPER where OPER_CODE = '01020321')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020321','刻录参数配置','admin','basic/configburn.action','_image/ico/default.gif','Y','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01020321')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('2','01020321');
go

-----中物7所定制-部门载体归属需要个人确认取消管理员确认转换-此处为开关切换通用模式和定制模式--默认value为0是通用模式
if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'DEPTTOPERSON_MODE')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE) VALUES ('DEPTTOPERSON_MODE','部门载体归属转换方式','0','DTPM');
go

if not exists (select 1 from SEC_OPER where OPER_CODE = '01020320')
     INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020320','部门载体归属转换模式','admin','basic/configdepttopersonmode.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01020320')
     INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('2','01020320');
go

if not exists (select 1 from SEC_OPER where OPER_CODE = '01150302')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01150302','部门转个人确认','admin','change/managechangepersonalconfirm.action','_image/ico/default.gif','N','N');
go


----委托打印、刻录台账
--if not exists (select 1 from SEC_OPER where OPER_CODE = '01110113')
     --INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110113','委托打印文件台帐','admin','ledger/viewproxypaperledger.action','_image/ico/default.gif','N','N');
--if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01110113')
     --INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110113');
     
--if not exists (select 1 from SEC_OPER where OPER_CODE = '01110208')
     --INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110208','委托刻录光盘台帐','admin','ledger/viewproxycdledger.action','_image/ico/default.gif','N','N');
--if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01110113')
    -- INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110208');
     
--event_print表增加字段 is_cancel, 标识该条作业是否被审批人退回
if not exists (select 1 from syscolumns where id=object_id('event_print') and name='IS_CANCEL')
	alter table event_print add IS_CANCEL int not null default 0
go

--增加监销人 角色
if not exists (select 1 from SEC_ROLE where ROLE_ID = '120')
	INSERT INTO SEC_ROLE(ROLE_ID,ROLE_NAME,SUBSYS_CODE,ROLE_DESC,ROLE_TYPE,ROLE_SPEC_KEY) VALUES('120','监销人','admin','负责涉密载体的监督销毁',3,'');
	
---新增合并复印菜单  2017.6.7 
if not exists (select 1 from SEC_OPER where OPER_CODE = '010507')
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('010507','合并复印管理','admin',NULL,NULL,'N','Y');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01050701')
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01050701','复印申请列表','admin','copy/managecopyeventlistbymerge.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '13' and A.OPER_CODE = '01050701')
    INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('13','01050701');
go
if not exists (select 1 from SEC_OPER where OPER_CODE = '01050702')
   INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01050702','补打条码','admin','copy/viewcopypaperledgerbymerge.action','_image/ico/default.gif','N','N'); 
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '13' and A.OPER_CODE = '01050702')
    INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('13','01050702');
go

---复印作业表增加print_eventcode字段存储打印复印合并的打印作业号 2017.6.7 
if not exists (select 1 from syscolumns where id=object_id('EVENT_COPY') and name='FILE_NUM')
	alter table EVENT_COPY add PRINT_EVENTCODE nvarchar(64) null;
go

--- Table: ENTITY_PAPER CREATE_TYPE字段约束里增加OUTCOPY-2017.6.7
alter table ENTITY_PAPER drop constraint CKC_CREATE_TYPE_ENTITY_P;
alter table ENTITY_PAPER add constraint CKC_CREATE_TYPE_ENTITY_P check (CREATE_TYPE in ('PRINT','COPY','LEADIN','OUTCOPY','MERGECOPY'));
go

---向event_import表中增加机要号字段
if not exists (select 1 from syscolumns where id=object_id('event_import') and name='CONFIDENTIAL_NUM')
	alter table event_import add CONFIDENTIAL_NUM  nvarchar(128) null;
go

---向entity_paper表中增加机要号字段
if not exists (select 1 from syscolumns where id=object_id('entity_paper') and name='CONFIDENTIAL_NUM')
	alter table entity_paper add CONFIDENTIAL_NUM  nvarchar(128) null;
go

---向entity_cd表中增加机要号字段
if not exists (select 1 from syscolumns where id=object_id('entity_cd') and name='CONFIDENTIAL_NUM')
	alter table entity_cd add CONFIDENTIAL_NUM  nvarchar(128) null;
go

---向entity_device表中增加机要号字段
if not exists (select 1 from syscolumns where id=object_id('entity_device') and name='CONFIDENTIAL_NUM')
	alter table entity_device add CONFIDENTIAL_NUM  nvarchar(128) null;
go

---JOB_PROCESS表中添加 外发外带流程 所需字段  
if not exists (select 1 from syscolumns where id=object_id('JOB_PROCESS') and name='SEND_WAY')
	alter table JOB_PROCESS add SEND_WAY nvarchar(100) null
go
if not exists (select 1 from syscolumns where id=object_id('JOB_PROCESS') and name='OUTPUT_UNDERTAKER')
	alter table JOB_PROCESS add OUTPUT_UNDERTAKER nvarchar(100) null
go
if not exists (select 1 from syscolumns where id=object_id('JOB_PROCESS') and name='CARRYOUT_USER_IIDDS')
	alter table JOB_PROCESS add CARRYOUT_USER_IIDDS nvarchar(100) null
go
if not exists (select 1 from syscolumns where id=object_id('JOB_PROCESS') and name='CARRYOUT_USER_NAMES')
	alter table JOB_PROCESS add CARRYOUT_USER_NAMES nvarchar(100) null
go

---向entity_paper表中添加 外发机要号字段 
if not exists (select 1 from syscolumns where id=object_id('entity_paper') and name='OUTPUT_CONFIDENTIAL_NUM')
	alter table entity_paper add OUTPUT_CONFIDENTIAL_NUM  nvarchar(128) null;
go
---向entity_paper表中添加 外发承办人字段 
if not exists (select 1 from syscolumns where id=object_id('entity_paper') and name='OUTPUT_UNDERTAKER')
	alter table entity_paper add OUTPUT_UNDERTAKER  nvarchar(100) null;
go

---向entity_cd表中添加 外发机要号字段 
if not exists (select 1 from syscolumns where id=object_id('entity_cd') and name='OUTPUT_CONFIDENTIAL_NUM')
	alter table entity_cd add OUTPUT_CONFIDENTIAL_NUM  nvarchar(128) null;
go
---向entity_cd表中添加 外发承办人字段 
if not exists (select 1 from syscolumns where id=object_id('entity_cd') and name='OUTPUT_UNDERTAKER')
	alter table entity_cd add OUTPUT_UNDERTAKER  nvarchar(100) null;
go
--添加公共涉密文件台帐菜单
go
--添加公共涉密文件台帐菜单
if not exists (select 1 from SEC_OPER where OPER_CODE = '01120103')
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01120103','公共涉密文件台帐','admin','ledger/viewdeptsecuritypaperledger.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '6' and A.OPER_CODE = '01120103')
    INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('6','01120103');
go
---添加定密依据管理库
if not exists (select 1 from SEC_OPER where OPER_CODE = '01020322')
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01020322','定密依据管理库','admin','print/managefixedaccording.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01020322')
    INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('2','01020322');
go

if not exists (select 1 from  sysobjects where  id = object_id('SYS_FIXACCORDING') and type = 'U')
create table SYS_FIXACCORDING (
   ID                   int                  identity(1,1),
   TYPE   		        nvarchar(64)         null,
   CONTENT      	    nvarchar(1024)       null,
   IS_SEALED            char(1)              null default 'N',
   EXT_CODE             nvarchar(100)        null,
   constraint PK_SYS_FIXACCORDING primary key (ID)
)
go
----添加台账合并功能，文件台账添加两个字段
if not exists (select 1 from syscolumns where id=object_id('entity_paper') and name='merge_code')
	alter table entity_paper add merge_code nvarchar(100) null
go
if not exists (select 1 from syscolumns where id=object_id('entity_paper') and name='merge_state')
	alter table entity_paper add merge_state int not null default 0
go
if not exists (select 1 from syscolumns where id=object_id('entity_paper') and name='mergecode_print')
	alter table entity_paper add mergecode_print int not null default 0
go
---添加合并新台账查看并打条码菜单
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110114')
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110114','台账合并条码输出','admin','ledger/viewmergeentityledger.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '6' and A.OPER_CODE = '01110114')
    INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('6','01110114');
go

alter table ENTITY_PAPER drop constraint CKC_CREATE_TYPE_ENTITY_P;
alter table ENTITY_PAPER add constraint CKC_CREATE_TYPE_ENTITY_P check (CREATE_TYPE in ('PRINT','COPY','LEADIN','OUTCOPY','SPECIAL','MERGE_ENTITY'));
go
if not exists (select 1 from SEC_OPER where OPER_CODE = '01110115')
    INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01110115','台账合并申请记录','admin','ledger/mergepaperapply.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01110115')
    INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01110115');
go

---添加文件外发已拒绝菜单
if not exists (select 1 from SEC_OPER where OPER_CODE = '01130203')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130203','已拒绝外发列表','admin','ledger/viewrejectsendpaper.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01130203')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('12','01130203');
go
---添加光盘外发已拒绝菜单
if not exists (select 1 from SEC_OPER where OPER_CODE = '01130403')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130403','已拒绝外发列表','admin','ledger/viewrejectsendcd.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '2' and A.OPER_CODE = '01130403')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('12','01130403');
go
---添加定密依据与部门绑定关联REF_FIXACCORDING_DEPT表
if not exists (select 1 from  sysobjects where  id = object_id('REF_FIXACCORDING_DEPT') and type = 'U')
create table REF_FIXACCORDING_DEPT (
   DEPT_ID     nvarchar(100)         null,
   FIX_ID      int       			 null,
)
go