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
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130908','纸张类型计','admin','basic/viewpaperstaticbysize.action','_image/ico/default.gif','N','N');
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
	INSERT INTO SEC_ROLE(ROLE_ID,ROLE_NAME,SUBSYS_CODE,ROLE_DESC,ROLE_TYPE,ROLE_SPEC_KEY) VALUES('15','刻录管理员','admin','集中刻录，及对刻录失败的任务进行标记',3,'BURNADMIN');
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

ALTER PROCEDURE [dbo].[proc_getMsgs] (
    @userid nvarchar(64)
    )
    
AS 
 declare  @printMsg int ,@printAccessMsg int,@printDenyMsg int, @setVer nvarchar(100)
 declare  @printRetrieveMsg int , @burnRetrieveMsg int

 Set @printMsg=0
 Set @printAccessMsg=0
 Set @printDenyMsg=0
 Set @printRetrieveMsg=0
 Set @burnRetrieveMsg=0
 
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
  
SELECT @printMsg as N'printMsg',
  @printAccessMsg as N'printAccessMsg',
  @printDenyMsg as N'printDenyMsg',
  @setVer as N'setVer',
  @printRetrieveMsg as N'printRetrieveMsg',
  @burnRetrieveMsg as N'burnRetrieveMsg'

go

---SEC_USER中添加RANK字段用于排序
if not exists (select 1 from syscolumns where id=object_id('sec_user') and name='RANK')
	alter table sec_user add RANK  int not null default 100;
go

---EVENT_IMPORT中添加FILE_KIND字段用于区分普通文件、保密手册
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
alter table CLIENT_MSG add constraint CKC_OPER_TYPE_CLIENT_M check (OPER_TYPE in ('PRINT','BURN','COPY','LEADIN','DEVICE','TRANSFER','BORROW','SEND_PAPER','FILE_PAPER','DESTROY_PAPER','DELAY_PAPER','SEND_CD','FILE_CD','DESTROY_CD','DELAY_CD','DESTROY_DEVICE','CHANGE'));
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
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01150102','部门转个人文件','admin',' change/viewdeptpaperledger.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01150103') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01150103',' 个人转部门光盘','admin','change/viewpersonalcdledger.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01150104') and exists (select 1 from SYS_MODULE where MODULE_CODE = 'BURN')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01150104',' 部门转个人光盘','admin','change/viewdeptcdledger.action','_image/ico/default.gif','N','N');
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

---复印作业表增加复印类型（内部、外来文件）字段
if not exists (select 1 from syscolumns where id=object_id('event_copy') and name='COPY_TYPE')
	alter table event_copy add COPY_TYPE nvarchar(64) not null default 'internal';
go

--- Table: SYS_MODULE 外来文件复印功能模块需要配置是否开启
if not exists (select 1 from SYS_MODULE where MODULE_CODE = 'OUTCOPY')
	INSERT INTO SYS_MODULE(MODULE_CODE,MODULE_NAME) VALUES ('OUTCOPY','外来文件复印功能模块');
go

--- Table: CLIENT_MSG OPER_TYPE字段约束里增加OUTCOPY
alter table CLIENT_MSG drop constraint CKC_OPER_TYPE_CLIENT_M;
alter table CLIENT_MSG add constraint CKC_OPER_TYPE_CLIENT_M check (OPER_TYPE in ('PRINT','BURN','COPY','LEADIN','DEVICE','TRANSFER','BORROW','SEND_PAPER','FILE_PAPER','DESTROY_PAPER','DELAY_PAPER','SEND_CD','FILE_CD','DESTROY_CD','DELAY_CD','DESTROY_DEVICE','CHANGE','OUTCOPY'));
go

--- Table: ENTITY_PAPER CREATE_TYPE字段约束里增加OUTCOPY
alter table ENTITY_PAPER drop constraint CKC_CREATE_TYPE_ENTITY_P;
alter table ENTITY_PAPER add constraint CKC_CREATE_TYPE_ENTITY_P check (CREATE_TYPE in ('PRINT','COPY','LEADIN','OUTCOPY'));
go

--- 添加外来文件复印模块权限
if exists (select 1 from SYS_MODULE where MODULE_CODE = 'OUTCOPY')
begin
	if not exists (select 1 from SEC_OPER where OPER_NAME = '内部复印申请')
		UPDATE SEC_OPER SET OPER_NAME='内部复印申请' WHERE OPER_CODE='010501';
	if not exists (select 1 from SEC_OPER where OPER_NAME = '内部复印审批')
		UPDATE SEC_OPER SET OPER_NAME='内部复印审批' WHERE OPER_CODE='010502';
	if not exists (select 1 from SEC_OPER where OPER_NAME = '内部复印管理')
		UPDATE SEC_OPER SET OPER_NAME='内部复印管理' WHERE OPER_CODE='010503';
	if not exists (select 1 from SEC_OPER where OPER_CODE = '010504')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('010504','外来复印申请','admin',NULL,NULL,'N','Y');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01050401')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01050401','提交复印申请','admin','copy/addcopyeventbyenter.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01050402')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01050402','查看申请记录','admin','copy/managecopyjobbyenter.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '010505')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('010505','外来复印审批','admin',NULL,NULL,'N','Y');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01050501')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01050501','待审批列表','admin','copy/managecopyaprvjobbyenter.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01050502')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01050502','查看审批记录','admin','copy/viewcopyaprvjobbyenter.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '010506')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('010506','外来复印管理','admin',NULL,NULL,'N','Y');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01050601')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01050601','复印申请列表','admin','copy/managecopyeventlistbyenter.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER where OPER_CODE = '01050602')
		INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01050602','补打条码','admin','copy/viewcopypaperledgerbyenter.action','_image/ico/default.gif','N','N');
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '10' and A.OPER_CODE = '01050501') 
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('10','01050501'); 
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '10' and A.OPER_CODE = '01050502') 
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('10','01050502');
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01050401') 
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01050401');
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01050402') 
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01050402');
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '13' and A.OPER_CODE = '01050601') 
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('13','01050601');
	if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '13' and A.OPER_CODE = '01050602') 
		INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('13','01050602');
end
go


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



----------------------------------------------------------------------------------------------------
--- 删除字段示例
--- 删除表table_1中的字段column_1
---
--- if exists (select 1 from syscolumns where id=object_id('table_1') and name='column_1')
--- begin
--- alter table table_1 DROP COLUMN column_1
--- end