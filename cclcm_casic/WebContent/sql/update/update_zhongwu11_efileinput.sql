USE [cclcm]
GO

if not exists (select 1 from  sysobjects where  id = object_id('EVENT_INPUT') and type = 'U')
/*==============================================================*/
/* Table: EVENT_INPUT       电子文件导入 表                                                                     */
/*==============================================================*/
create table EVENT_INPUT (
    ID	            int                identity(1,1),
    EVENT_CODE      nvarchar(64)          null,
    JOB_CODE        nvarchar(100)         null,
    USER_IIDD       nvarchar(100)         null,
    DEPT_ID         nvarchar(100)         null,
    SECLV_CODE      int                   null,
    FILE_LIST       nvarchar(100)         null,
    FILE_SECLEVEL   nvarchar(100)         null,
    MESSAGE_USAGE   nvarchar(1024)        null,
    PERSONAL        nvarchar(1024)        null,
    ADDRESS         nvarchar(1024)        null,
    FILE_NUM        int                   null,
    SUMM            nvarchar(1024)        null,
    MED_TYPE        nvarchar(100)         null,
    APPLY_TIME      datetime              null,
    OPERATE_TIME    datetime              null,
    CD_NUM          nvarchar(64)          null,
    INTERNET_NUM    nvarchar(64)          null,
    INPUT_STATE     int                   null,
    INPUT_USER_IIDD nvarchar(100)         null,
    INPUT_USER_NAME nvarchar(100)         null,
    PROJECT_CODE   nvarchar(64)          null,
    USAGE_CODE     nvarchar(64)          null,
   constraint PK_EVENT_INPUT  primary key (ID)
)
go

---电子文件审计（安全管理员）
if not exists (select 1 from SEC_OPER where OPER_CODE = '01130911')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01130911','电子文件导入总台账','admin','ledger/viewefileinputledger.action','_image/ico/default.gif','N','N');

if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '3' and A.OPER_CODE = '01130911')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('3','01130911');
go
 ---电子文件导入部门台账
if not exists (select 1 from SEC_OPER where OPER_CODE = '01120203' and oper_name = '电子文件导入台账')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01120203','电子文件导入台账','admin','ledger/viewdeptefileinputledger.action','_image/ico/default.gif','N','N');
go

---电子文件导入部门台账
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '6' and A.OPER_CODE = '01120203')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('6','01120203'); --部门秘书
go

if not exists (select 1 from SEC_CONFIG where ITEM_KEY = 'exportefileinputledger')
	INSERT INTO SEC_CONFIG(ITEM_KEY,ITEM_NAME,ITEM_VALUE,ITEM_TYPE,STARTUSE) VALUES ('exportefileinputledger','电子文件导出excel模板','2,3,4,5,6,7,8','EXCELEFILEINPUT',0);
go


---电子文件导入管理
if not exists (select 1 from SEC_OPER where OPER_CODE = '0116')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('0116','电子文件导入管理','admin',NULL,NULL,'N','Y');
if not exists (select 1 from SEC_OPER where OPER_CODE = '011601')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('011601','申请导入','admin',NULL,NULL,'N','Y');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01160101')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01160101','提交导入申请','admin','input/addinputevent.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01160102')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01160102','查看申请记录','admin','input/viewinputlistjob.action','_image/ico/default.gif','N','N');
go

if not exists (select 1 from SEC_OPER where OPER_CODE = '011602')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('011602','导入审批','admin',NULL,NULL,'N','Y');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01160201')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01160201','待审批列表','admin','input/manageinputaprvjob.action','_image/ico/default.gif','N','N');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01160202')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01160202','查看审批记录','admin','input/viewinputaprvjob.action','_image/ico/default.gif','N','N');
go

if not exists (select 1 from SEC_OPER where OPER_CODE = '011603')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('011603','已导入文件下载','admin',NULL,NULL,'N','Y');
if not exists (select 1 from SEC_OPER where OPER_CODE = '01160302')
	INSERT INTO SEC_OPER(OPER_CODE,OPER_NAME,SUBSYS_CODE,WEB_URL,ICON_PATH,EN_PRVT_OPER,EN_DIRECTORY) VALUES('01160302','查看已导入文件','admin','input/viewmanageinput.action','_image/ico/default.gif','N','N');
go

---电子文件导入管理权限
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01160101')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01160101'); --普通用户
go
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01160102')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01160102');
go
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '11' and A.OPER_CODE = '01160302')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('11','01160302');
go
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '10' and A.OPER_CODE = '01160201')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('10','01160201'); --可审批用户
go
if not exists (select 1 from SEC_OPER as A left join SEC_ROLE_OPER as B on A.OPER_CODE = B.OPER_CODE where B.ROLE_ID = '10' and A.OPER_CODE = '01160202')
	INSERT INTO SEC_ROLE_OPER(ROLE_ID,OPER_CODE) VALUES ('10','01160202');
go

--- Table: SYS_MODULE 系统模块配置(电子文件导入模块)
if not exists (select 1 from SYS_MODULE where MODULE_CODE = 'INPUT')
	INSERT INTO SYS_MODULE(MODULE_CODE,MODULE_NAME) VALUES ('INPUT','电子文件导入模块');
go

---添加电子文件导入模块消息通知, 更新CLIENT_MSG触发器
if not exists (select 1 from syscolumns where id=object_id('CLIENT_MSG') and name='title')
	alter table CLIENT_MSG add TITLE nvarchar(1024) null;
go

if not exists (select 1 from syscolumns where id=object_id('CLIENT_MSG') and name='url')
	alter table CLIENT_MSG add URL nvarchar(1024) null;
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
				if @oper_type = 'MSG_INPUT'
				begin
					set @title = '电子文件导入';
                                        if @msg_type = 1
                                        begin
					  set @link_url = 'input/approveinputjob.action?job_code='+@job_code;
                                        end else begin
                                          set @link_url = 'input/viewinputlistjob.action';
                                        end;
				end;	
     update  A set A.title = @title, A.url = @link_url
      from [dbo].[CLIENT_MSG] A,inserted i
      where A.id = i.id;
go

