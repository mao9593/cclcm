USE [cclcm]
GO

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('DEPT_OPEN_SCOPE') and o.name = 'FK_DEPT_OPEN_SCOPE_BR_DEPT_ID')
alter table DEPT_OPEN_SCOPE
   drop constraint FK_DEPT_OPEN_SCOPE_BR_DEPT_ID
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_BORROW') and o.name = 'FK_EVENT_BORROW_DEPT_ID')
alter table EVENT_BORROW
   drop constraint FK_EVENT_BORROW_DEPT_ID
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_BORROW') and o.name = 'FK_EVENT_BORROW_JOB_CODE')
alter table EVENT_BORROW
   drop constraint FK_EVENT_BORROW_JOB_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_BORROW') and o.name = 'FK_EVENT_BORROW_USER_IIDD')
alter table EVENT_BORROW
   drop constraint FK_EVENT_BORROW_USER_IIDD
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_BURN') and o.name = 'FK_EVENT_BURN_DEPT_ID')
alter table EVENT_BURN
   drop constraint FK_EVENT_BURN_DEPT_ID
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_BURN') and o.name = 'FK_EVENT_BURN_JOB_CODE')
alter table EVENT_BURN
   drop constraint FK_EVENT_BURN_JOB_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_BURN') and o.name = 'FK_EVENT_BURN_SECLV_CODE')
alter table EVENT_BURN
   drop constraint FK_EVENT_BURN_SECLV_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_BURN') and o.name = 'FK_EVENT_BURN_USER_IIDD')
alter table EVENT_BURN
   drop constraint FK_EVENT_BURN_USER_IIDD
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_COPY') and o.name = 'FK_EVENT_COPY_DEPT_ID')
alter table EVENT_COPY
   drop constraint FK_EVENT_COPY_DEPT_ID
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_COPY') and o.name = 'FK_EVENT_COPY_JOB_CODE')
alter table EVENT_COPY
   drop constraint FK_EVENT_COPY_JOB_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_COPY') and o.name = 'FK_EVENT_COPY_SECLV_CODE')
alter table EVENT_COPY
   drop constraint FK_EVENT_COPY_SECLV_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_COPY') and o.name = 'FK_EVENT_COPY_USER_IIDD')
alter table EVENT_COPY
   drop constraint FK_EVENT_COPY_USER_IIDD
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_DEVICE') and o.name = 'FK_EVENT_DEVICE_DEPT_ID')
alter table EVENT_DEVICE
   drop constraint FK_EVENT_DEVICE_DEPT_ID
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_DEVICE') and o.name = 'FK_EVENT_DEVICE_JOB_CODE')
alter table EVENT_DEVICE
   drop constraint FK_EVENT_DEVICE_JOB_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_DEVICE') and o.name = 'FK_EVENT_DEVICE_SECLV_CODE')
alter table EVENT_DEVICE
   drop constraint FK_EVENT_DEVICE_SECLV_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_DEVICE') and o.name = 'FK_EVENT_DEVICE_USER_IIDD')
alter table EVENT_DEVICE
   drop constraint FK_EVENT_DEVICE_USER_IIDD
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_IMPORT') and o.name = 'FK_EVENT_IMPORT_DEPT_ID')
alter table EVENT_IMPORT
   drop constraint FK_EVENT_IMPORT_DEPT_ID
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_IMPORT') and o.name = 'FK_EVENT_IMPORT_JOB_CODE')
alter table EVENT_IMPORT
   drop constraint FK_EVENT_IMPORT_JOB_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_IMPORT') and o.name = 'FK_EVENT_IMPORT_SECLV_CODE')
alter table EVENT_IMPORT
   drop constraint FK_EVENT_IMPORT_SECLV_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_IMPORT') and o.name = 'FK_EVENT_IMPORT_USER_IIDD')
alter table EVENT_IMPORT
   drop constraint FK_EVENT_IMPORT_USER_IIDD
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_PRINT') and o.name = 'FK_EVENT_PRINT_DEPT_ID')
alter table EVENT_PRINT
   drop constraint FK_EVENT_PRINT_DEPT_ID
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_PRINT') and o.name = 'FK_EVENT_PRINT_JOB_CODE')
alter table EVENT_PRINT
   drop constraint FK_EVENT_PRINT_JOB_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_PRINT') and o.name = 'FK_EVENT_PRINT_SECLV_CODE')
alter table EVENT_PRINT
   drop constraint FK_EVENT_PRINT_SECLV_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_PRINT') and o.name = 'FK_EVENT_PRINT_USER_IIDD')
alter table EVENT_PRINT
   drop constraint FK_EVENT_PRINT_USER_IIDD
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_TRANSFER') and o.name = 'FK_EVENT_TRANSFER_DEPT_ID')
alter table EVENT_TRANSFER
   drop constraint FK_EVENT_TRANSFER_DEPT_ID
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_TRANSFER') and o.name = 'FK_EVENT_TRANSFER_SECLV_CODE')
alter table EVENT_TRANSFER
   drop constraint FK_EVENT_TRANSFER_SECLV_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('EVENT_TRANSFER') and o.name = 'FK_EVENT_TRANSFER_USER_IIDD')
alter table EVENT_TRANSFER
   drop constraint FK_EVENT_TRANSFER_USER_IIDD
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SEC_DEPT_POST') and o.name = 'FK_SEC_DEPT_POST_DEPT_CODE')
alter table SEC_DEPT_POST
   drop constraint FK_SEC_DEPT_POST_DEPT_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SEC_DEPT_POST') and o.name = 'FK_SEC_DEPT_POST_POST_ID')
alter table SEC_DEPT_POST
   drop constraint FK_SEC_DEPT_POST_POST_ID
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SEC_DEPT_SUBSYS') and o.name = 'FK_SEC_DEPT_SUBSYS_DEPT_CODE')
alter table SEC_DEPT_SUBSYS
   drop constraint FK_SEC_DEPT_SUBSYS_DEPT_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SEC_DEPT_SUBSYS') and o.name = 'FK_SEC_DEPT_SUBSYS_SUBSYS_CODE')
alter table SEC_DEPT_SUBSYS
   drop constraint FK_SEC_DEPT_SUBSYS_SUBSYS_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SEC_OPER') and o.name = 'FK_SEC_OPER_SUBSYS_CODE')
alter table SEC_OPER
   drop constraint FK_SEC_OPER_SUBSYS_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SEC_ROLE') and o.name = 'FK_SEC_ROLE_SUBSYS_CODE')
alter table SEC_ROLE
   drop constraint FK_SEC_ROLE_SUBSYS_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SEC_ROLE_OPER') and o.name = 'FK_SEC_ROLE_OPER_OPER_ID')
alter table SEC_ROLE_OPER
   drop constraint FK_SEC_ROLE_OPER_OPER_ID
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SEC_ROLE_OPER') and o.name = 'FK_SEC_ROLE_OPER_ROLE_ID')
alter table SEC_ROLE_OPER
   drop constraint FK_SEC_ROLE_OPER_ROLE_ID
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SEC_ROLE_USER') and o.name = 'FK_SEC_ROLE_USER_ROLE_ID')
alter table SEC_ROLE_USER
   drop constraint FK_SEC_ROLE_USER_ROLE_ID
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SEC_ROLE_USER') and o.name = 'FK_SEC_ROLE_USER_USER_ID')
alter table SEC_ROLE_USER
   drop constraint FK_SEC_ROLE_USER_USER_ID
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SEC_USER') and o.name = 'FK_SEC_USER_DEPT_ID')
alter table SEC_USER
   drop constraint FK_SEC_USER_DEPT_ID
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SEC_USER') and o.name = 'FK_SEC_USER_REAL_USER_ID')
alter table SEC_USER
   drop constraint FK_SEC_USER_REAL_USER_ID
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SYS_BURNER') and o.name = 'FK_SYS_BURNER_CONSOLE_CODE')
alter table SYS_BURNER
   drop constraint FK_SYS_BURNER_CONSOLE_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SYS_BURNER') and o.name = 'FK_SYS_BURNER_DEPT_ID')
alter table SYS_BURNER
   drop constraint FK_SYS_BURNER_DEPT_ID
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SYS_BURNER') and o.name = 'FK_SYS_BURNER_SECLV_CODE')
alter table SYS_BURNER
   drop constraint FK_SYS_BURNER_SECLV_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SYS_CONSOLE') and o.name = 'FK_SYS_CONS_REFERENCE_SEC_USER')
alter table SYS_CONSOLE
   drop constraint FK_SYS_CONS_REFERENCE_SEC_USER
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SYS_EXCHANGEBOX') and o.name = 'FK_SYS_EXCHANGEBOX_SECLV_CODE')
alter table SYS_EXCHANGEBOX
   drop constraint FK_SYS_EXCHANGEBOX_SECLV_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SYS_EXCHANGEUNIT') and o.name = 'FK_FK_SYS_EXCHANGEUNIT_SECLV_CODE')
alter table SYS_EXCHANGEUNIT
   drop constraint FK_FK_SYS_EXCHANGEUNIT_SECLV_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SYS_EXCHANGEUNIT') and o.name = 'FK_SYS_EXCHANGEUNIT_BOX_CODE')
alter table SYS_EXCHANGEUNIT
   drop constraint FK_SYS_EXCHANGEUNIT_BOX_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SYS_MFP') and o.name = 'FK_SYS_CONSOLE_SECLV_CODE')
alter table SYS_MFP
   drop constraint FK_SYS_CONSOLE_SECLV_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SYS_MFP') and o.name = 'FK_SYS_MFP_DEPT_ID')
alter table SYS_MFP
   drop constraint FK_SYS_MFP_DEPT_ID
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SYS_MFP') and o.name = 'FK_SYS_MFP_SECLV_CODE')
alter table SYS_MFP
   drop constraint FK_SYS_MFP_SECLV_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SYS_PRINTER') and o.name = 'FK_SYS_PRINTER_CONSOLE_CODE')
alter table SYS_PRINTER
   drop constraint FK_SYS_PRINTER_CONSOLE_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SYS_PRINTER') and o.name = 'FK_SYS_PRINTER_DEPT_ID')
alter table SYS_PRINTER
   drop constraint FK_SYS_PRINTER_DEPT_ID
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SYS_PRINTER') and o.name = 'FK_SYS_PRINTER_SECLV_CODE')
alter table SYS_PRINTER
   drop constraint FK_SYS_PRINTER_SECLV_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SYS_PROXY_OPER') and o.name = 'FK_SYS_PROXY_OPER_PROXY_USER_IIDD')
alter table SYS_PROXY_OPER
   drop constraint FK_SYS_PROXY_OPER_PROXY_USER_IIDD
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SYS_PROXY_OPER') and o.name = 'FK_SYS_PROXY_OPER_USER_IIDD')
alter table SYS_PROXY_OPER
   drop constraint FK_SYS_PROXY_OPER_USER_IIDD
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SYS_RECYCLEBOX') and o.name = 'FK_SYS_RECYCLEBOX_SECLV_CODE')
alter table SYS_RECYCLEBOX
   drop constraint FK_SYS_RECYCLEBOX_SECLV_CODE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SYS_SECLEVEL') and o.name = 'FK_SYS_SECLEVEL_SECLV_CODE')
alter table SYS_SECLEVEL
   drop constraint FK_SYS_SECLEVEL_SECLV_CODE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('APPROVE_PROCESS')
            and   type = 'U')
   drop table APPROVE_PROCESS
go

if exists (select 1
            from  sysobjects
           where  id = object_id('BAK_LOG')
            and   type = 'U')
   drop table BAK_LOG
go

if exists (select 1
            from  sysobjects
           where  id = object_id('BAK_STRATEGY')
            and   type = 'U')
   drop table BAK_STRATEGY
go

if exists (select 1
            from  sysobjects
           where  id = object_id('CLIENT_MSG')
            and   type = 'U')
   drop table CLIENT_MSG
go

if exists (select 1
            from  sysobjects
           where  id = object_id('CONFIRM_RECORD')
            and   type = 'U')
   drop table CONFIRM_RECORD
go

if exists (select 1
            from  sysobjects
           where  id = object_id('CYCLE_RECORD')
            and   type = 'U')
   drop table CYCLE_RECORD
go

if exists (select 1
            from  sysobjects
           where  id = object_id('DEPT_OPEN_SCOPE')
            and   type = 'U')
   drop table DEPT_OPEN_SCOPE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('ENTITY_CD')
            and   type = 'U')
   drop table ENTITY_CD
go

if exists (select 1
            from  sysobjects
           where  id = object_id('ENTITY_DEVICE')
            and   type = 'U')
   drop table ENTITY_DEVICE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('ENTITY_PAPER')
            and   type = 'U')
   drop table ENTITY_PAPER
go

if exists (select 1
            from  sysobjects
           where  id = object_id('ENTITY_STORAGE')
            and   type = 'U')
   drop table ENTITY_STORAGE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('EVENT_BORROW')
            and   type = 'U')
   drop table EVENT_BORROW
go

if exists (select 1
            from  sysobjects
           where  id = object_id('EVENT_BURN')
            and   type = 'U')
   drop table EVENT_BURN
go

if exists (select 1
            from  sysobjects
           where  id = object_id('EVENT_COPY')
            and   type = 'U')
   drop table EVENT_COPY
go

if exists (select 1
            from  sysobjects
           where  id = object_id('EVENT_DEVICE')
            and   type = 'U')
   drop table EVENT_DEVICE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('EVENT_IMPORT')
            and   type = 'U')
   drop table EVENT_IMPORT
go

if exists (select 1
            from  sysobjects
           where  id = object_id('EVENT_PRINT')
            and   type = 'U')
   drop table EVENT_PRINT
go

if exists (select 1
            from  sysobjects
           where  id = object_id('EVENT_TRANSFER')
            and   type = 'U')
   drop table EVENT_TRANSFER
go

if exists (select 1
            from  sysobjects
           where  id = object_id('FILE_INFO')
            and   type = 'U')
   drop table FILE_INFO
go

if exists (select 1
            from  sysobjects
           where  id = object_id('JOB_DEVICE')
            and   type = 'U')
   drop table JOB_DEVICE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('JOB_INPUT')
            and   type = 'U')
   drop table JOB_INPUT
go

if exists (select 1
            from  sysobjects
           where  id = object_id('JOB_OUTPUT')
            and   type = 'U')
   drop table JOB_OUTPUT
go

if exists (select 1
            from  sysobjects
           where  id = object_id('JOB_PROCESS')
            and   type = 'U')
   drop table JOB_PROCESS
go

if exists (select 1
            from  sysobjects
           where  id = object_id('JOB_TRANSFER')
            and   type = 'U')
   drop table JOB_TRANSFER
go

if exists (select 1
            from  sysobjects
           where  id = object_id('LOG_OPERATION_ADMIN')
            and   type = 'U')
   drop table LOG_OPERATION_ADMIN
go

if exists (select 1
            from  sysobjects
           where  id = object_id('LOG_OPERATION_COMMON')
            and   type = 'U')
   drop table LOG_OPERATION_COMMON
go

if exists (select 1
            from  sysobjects
           where  id = object_id('LOG_SYSTEM')
            and   type = 'U')
   drop table LOG_SYSTEM
go

if exists (select 1
            from  sysobjects
           where  id = object_id('LOG_USER_LOGIN')
            and   type = 'U')
   drop table LOG_USER_LOGIN
go

if exists (select 1
            from  sysobjects
           where  id = object_id('OP_TAG')
            and   type = 'U')
   drop table OP_TAG
go

if exists (select 1
            from  sysobjects
           where  id = object_id('PROCESS_RECORD')
            and   type = 'U')
   drop table PROCESS_RECORD
go

if exists (select 1
            from  sysobjects
           where  id = object_id('REAL_USER')
            and   type = 'U')
   drop table REAL_USER
go

if exists (select 1
            from  sysobjects
           where  id = object_id('REF_PRINTER_GROUP')
            and   type = 'U')
   drop table REF_PRINTER_GROUP
go

if exists (select 1
            from  sysobjects
           where  id = object_id('REF_PRINTER_USER')
            and   type = 'U')
   drop table REF_PRINTER_USER
go

if exists (select 1
            from  sysobjects
           where  id = object_id('REF_SECLEVEL_ROLE')
            and   type = 'U')
   drop table REF_SECLEVEL_ROLE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('REJECT_RECORD')
            and   type = 'U')
   drop table REJECT_RECORD
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SEC_CONFIG')
            and   type = 'U')
   drop table SEC_CONFIG
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SEC_DEPT')
            and   type = 'U')
   drop table SEC_DEPT
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SEC_DEPT_ADMIN')
            and   type = 'U')
   drop table SEC_DEPT_ADMIN
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SEC_DEPT_LEVEL')
            and   type = 'U')
   drop table SEC_DEPT_LEVEL
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SEC_DEPT_POST')
            and   type = 'U')
   drop table SEC_DEPT_POST
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SEC_DEPT_SUBSYS')
            and   type = 'U')
   drop table SEC_DEPT_SUBSYS
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SEC_DEPT_TYPE')
            and   type = 'U')
   drop table SEC_DEPT_TYPE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SEC_DOMAIN_MEMBER')
            and   type = 'U')
   drop table SEC_DOMAIN_MEMBER
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SEC_OPER')
            and   type = 'U')
   drop table SEC_OPER
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SEC_ROLE')
            and   type = 'U')
   drop table SEC_ROLE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SEC_ROLE_OPER')
            and   type = 'U')
   drop table SEC_ROLE_OPER
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SEC_ROLE_USER')
            and   type = 'U')
   drop table SEC_ROLE_USER
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SEC_SUBSYS')
            and   type = 'U')
   drop table SEC_SUBSYS
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SEC_TERRITORY')
            and   type = 'U')
   drop table SEC_TERRITORY
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SEC_USER')
            and   type = 'U')
   drop table SEC_USER
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SEC_USER_POST')
            and   type = 'U')
   drop table SEC_USER_POST
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SEC_USER_SECLV')
            and   type = 'U')
   drop table SEC_USER_SECLV
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SYS_BARCODE')
            and   type = 'U')
   drop table SYS_BARCODE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SYS_BURNER')
            and   type = 'U')
   drop table SYS_BURNER
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SYS_CONSOLE')
            and   type = 'U')
   drop table SYS_CONSOLE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SYS_CVS')
            and   type = 'U')
   drop table SYS_CVS
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SYS_EXCHANGEBOX')
            and   type = 'U')
   drop table SYS_EXCHANGEBOX
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SYS_EXCHANGEUNIT')
            and   type = 'U')
   drop table SYS_EXCHANGEUNIT
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SYS_GENNO')
            and   type = 'U')
   drop table SYS_GENNO
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SYS_KEYWORD')
            and   type = 'U')
   drop table SYS_KEYWORD
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SYS_MFP')
            and   type = 'U')
   drop table SYS_MFP
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SYS_MODULE')
            and   type = 'U')
   drop table SYS_MODULE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SYS_PLACE')
            and   type = 'U')
   drop table SYS_PLACE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SYS_PRINTER')
            and   type = 'U')
   drop table SYS_PRINTER
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SYS_PROJECT')
            and   type = 'U')
   drop table SYS_PROJECT
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SYS_PROXY_APRV')
            and   type = 'U')
   drop table SYS_PROXY_APRV
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SYS_PROXY_OPER')
            and   type = 'U')
   drop table SYS_PROXY_OPER
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SYS_RECYCLEBOX')
            and   type = 'U')
   drop table SYS_RECYCLEBOX
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SYS_SECLEVEL')
            and   type = 'U')
   drop table SYS_SECLEVEL
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SYS_USAGE')
            and   type = 'U')
   drop table SYS_USAGE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SYS_WATERMASK')
            and   type = 'U')
   drop table SYS_WATERMASK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('USER_SECURITY')
            and   type = 'U')
   drop table USER_SECURITY
go

if exists (select 1
            from  sysobjects
           where  id = object_id('EVENT_SPECIALPRINT')
            and   type = 'U')
   drop table EVENT_SPECIALPRINT
go

if exists (select 1
            from  sysobjects
           where  id = object_id('EVENT_TEMP')
            and   type = 'U')
   drop table EVENT_TEMP
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SYS_FIXACCORDING')
            and   type = 'U')
   drop table SYS_FIXACCORDING
go
/*==============================================================*/
/* Table: APPROVE_PROCESS                                       */
/*==============================================================*/
create table APPROVE_PROCESS (
   PROCESS_ID           nvarchar(100)        not null,
   PROCESS_NAME         nvarchar(100)        null,
   DEPT_ID	            nvarchar(1024)       not null,
   SECLV_CODE           nvarchar(1024)       not null,
   JOBTYPE_CODE         nvarchar(1024)       not null,
   USAGE_CODE			nvarchar(1024)       not null,
   STEP_DEPT            nvarchar(1024)       null,
   STEP_ROLE            nvarchar(1024)       null,
   TOTAL_STEPS          numeric(4)           null,
   EXT_CODE             nvarchar(1024)       null,
   IS_SEALED            char(1)              null default 'N',
   constraint PK_APPROVE_PROCESS primary key nonclustered (PROCESS_ID)
)
go

/*==============================================================*/
/* Table: BAK_LOG                                               */
/*==============================================================*/
create table BAK_LOG (
   ID                   numeric              identity(1,1),
   BACVERSION           nvarchar(128)        null,
   CURVERSION           nvarchar(128)        not null default 'N',
   DIR                  nvarchar(128)        not null,
   FILENAME             nvarchar(128)        not null,
   BACKUPTIME           nvarchar(128)        not null,
   RESTORETIME          nvarchar(128)        null,
   BAKCONTENT           nvarchar(1024)       null,
   RESERVED             nvarchar(128)        null,
   constraint PK_BAK_LOG primary key (ID)
)
go

/*==============================================================*/
/* Table: BAK_STRATEGY                                          */
/*==============================================================*/
create table BAK_STRATEGY (
   ID                   int                  not null,
   STRATEGYCODE         nvarchar(128)        not null,
   DIR                  nvarchar(1024)       not null,
   CONTENTTYPE          nvarchar(64)         null,
   TYPE                 nvarchar(128)        not null,
   STARTTIME            datetime             null,
   INTERVAL             nvarchar(128)        null,
   ENABLE               int                  not null default 2,
   constraint PK_BAK_STRATEGY primary key (ID)
)
go

/*==============================================================*/
/* Table: CLIENT_MSG                                            */
/*==============================================================*/
create table CLIENT_MSG (
   ID                   int                  identity(1,1),
   ACCEPT_USER_IIDD     nvarchar(100)        not null,
   ACCEPT_USER_NAME     nvarchar(100)        null,
   OPER_TYPE            nvarchar(100)        not null,
      ---constraint CKC_OPER_TYPE_CLIENT_M check (OPER_TYPE in ('PRINT','BURN','COPY','LEADIN','DEVICE','TRANSFER','BORROW','SEND_PAPER','FILE_PAPER','DESTROY_PAPER','DELAY_PAPER','SEND_CD','FILE_CD','DESTROY_CD','DELAY_CD','DESTROY_DEVICE','CHANGE','OUTCOPY')),
   MESSAGE_TYPE         int                  not null,
   JOB_CODE             nvarchar(100)        not null,
   MESSAGE              nvarchar(1024)       null,
   INSERT_TIME          datetime             null,
   IS_READ              int                  null default 0,
   READ_TIME            datetime             null,
   constraint PK_CLIENT_MSG primary key (ID)
)
go

/*==============================================================*/
/* Table: CONFIRM_RECORD                                        */
/*==============================================================*/
create table CONFIRM_RECORD (
   ID                   int                  identity(1,1),
   APPLY_USER_IIDD      nvarchar(100)        not null,
   APPLY_USER_NAME      nvarchar(100)        null,
   APPLY_DEPT_ID        nvarchar(100)        null,
   APPLY_DEPT_NAME      nvarchar(100)        null,
   CONFIRM_USER_IIDD    nvarchar(100)        not null,
   CONFIRM_USER_NAME    nvarchar(100)        null,
   CONFIRM_DEPT_ID      nvarchar(100)        null,
   CONFIRM_DEPT_NAME    nvarchar(100)        null,
   ENTITY_TYPE          nvarchar(100)        not null
      constraint CKC_ENTITY_TYPE_CONFIRM_ check (ENTITY_TYPE in ('PAPER','CD','DEVICE','STORAGE')),
   ENTITY_BARCODE       nvarchar(100)        not null,
   ENTITY_NAME          nvarchar(1024)       null,
   SECLV_NAME           nvarchar(100)        null,
   CONFIRM_TYPE         nvarchar(100)        not null
      constraint CKC_CONFIRM_TYPE_CONFIRM_ check (CONFIRM_TYPE in ('TRANSFER','FILE','DEVICE_BR','DEVICE_RT','READ_BR','READ_RT','RETRIEVE','STORAGE_BR','STORAGE_RT')),
   EVENT_CODE           nvarchar(100)        null,
   CREATE_TIME          datetime             null,
   CONFIRM_TIME         datetime             null,
   CONFIRM_STATUS       char(1)              not null default 'N'
      constraint CKC_CONFIRM_STATUS_CONFIRM_ check (CONFIRM_STATUS in ('Y','N')),
   constraint PK_CONFIRM_RECORD primary key (ID)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'PAPER纸质，CD光盘，DEVICE磁介质',
   'user', @CurrentUser, 'table', 'CONFIRM_RECORD', 'column', 'ENTITY_TYPE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'TRANSFER流转、DEVICE_BR借用磁介质、DEVICE_RT归还磁介质、READ_BR借阅文件、READ_RT归还文件、FILE归档',
   'user', @CurrentUser, 'table', 'CONFIRM_RECORD', 'column', 'CONFIRM_TYPE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '（归档业务该字段为空）',
   'user', @CurrentUser, 'table', 'CONFIRM_RECORD', 'column', 'EVENT_CODE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Y确认完成，N待确认',
   'user', @CurrentUser, 'table', 'CONFIRM_RECORD', 'column', 'CONFIRM_STATUS'
go

/*==============================================================*/
/* Table: CYCLE_RECORD                                          */
/*==============================================================*/
create table CYCLE_RECORD (
   BARCODE              nvarchar(128)        not null,
   ENTITY_TYPE          nvarchar(64)         not null
      constraint CKC_ENTITY_TYPE_CYCLE_RE check (ENTITY_TYPE in ('PAPER','CD','DEVICE','STORAGE')),
   OPER_TIME            datetime             not null,
   USER_NAME            nvarchar(64)         not null,
   DEPT_NAME            nvarchar(64)         not null,
   OPER                 nvarchar(64)         not null,
   REJECT_CODE          nvarchar(64)         null,
   JOB_CODE             nvarchar(100)        null
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'PAPER/CD/DEVICE',
   'user', @CurrentUser, 'table', 'CYCLE_RECORD', 'column', 'ENTITY_TYPE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '("PRINT","打印")("LEADIN","录入")("COPY","复印")("BURN","刻录") ("REPRINT","补打")("REBURN","补刻")("TRANSIN","流转入")("TRANSOUT","流转出")("SEND","外发")("FILE","归档")("RETRIEVE","回收")("DESTORY","销毁")',
   'user', @CurrentUser, 'table', 'CYCLE_RECORD', 'column', 'OPER'
go

/*==============================================================*/
/* Table: DEPT_OPEN_SCOPE                                       */
/*==============================================================*/
create table DEPT_OPEN_SCOPE (
   DEPT_ID              nvarchar(100)        not null,
   BR_DEPT_ID           nvarchar(100)        not null,
   constraint PK_DEPT_OPEN_SCOPE primary key (DEPT_ID, BR_DEPT_ID)
)
go

/*==============================================================*/
/* Table: ENTITY_CD                                             */
/*==============================================================*/
create table ENTITY_CD (
   CD_ID                int                  identity(1,1),
   CD_BARCODE           nvarchar(64)         null
   		constraint CONS_UNI_CD_BARCODE unique (CD_BARCODE),
   EVENT_CODE           nvarchar(64)         null,
   DEPT_ID              nvarchar(64)         null,
   DEPT_NAME            nvarchar(64)         null,
   USER_IIDD            nvarchar(64)         null,
   USER_NAME            nvarchar(64)         null,
   DUTY_USER_IIDD       nvarchar(64)         null,
   DUTY_USER_NAME       nvarchar(64)         null,
   DUTY_DEPT_ID         nvarchar(64)         null,
   DUTY_DEPT_NAME       nvarchar(64)         null,
   SECLV_CODE           int                  null,
   BURN_TIME            datetime             null,
   BURN_MACHINE         nvarchar(128)        null,
   BURN_IPADDRESS       nvarchar(128)        null,
   CD_TYPE              nvarchar(64)         null,
   CD_VOLUME            nvarchar(64)         null,
   DATA_TYPE            int                  null default 0,
   FILE_LIST            nvarchar(1024)       null,
   FILE_NUM             int                  null,
   IS_REBURN            nvarchar(10)         null,
   BURN_RESULT          int                  null default 1,
   BURN_TYPE            int                  null,
   PROJECT_CODE         nvarchar(1024)       null,
   CD_STATE             int                  not null default 0,
      ---constraint CKC_CD_STATE_ENTITY_C check (CD_STATE in (1,2,3,4,5,6,7,8,9,0,10)),
   CREATE_TYPE          nvarchar(64)         not null default 'BURN'
      constraint CKC_CREATE_TYPE_ENTITY_C check (CREATE_TYPE in ('LEADIN','BURN')),
   SCOPE                nvarchar(64)         not null default 'PERSON'
      constraint CKC_SCOPE_ENTITY_C check (SCOPE in ('PERSON','DEPT')),
   SCOPE_DEPT_ID        nvarchar(100)        null,
   SCOPE_DEPT_NAME      nvarchar(100)        null,
   BURNER_CODE          nvarchar(64)         null,
   BURNER_NAME          nvarchar(1024)       null,
   MFP_CODE             nvarchar(64)         null,
   MFP_NAME             nvarchar(1024)       null,
   RETRIEVE_TIME        datetime             null,
   RETRIEVE_TYPE        int                  null,
   RETRIEVE_USER_IIDD   nvarchar(64)         null,
   RETRIEVE_BOX_CODE    nvarchar(64)         null,
   DESTROY_TIME         datetime             null,
   DESTROY_USER_IIDD    nvarchar(64)         null,
   SEND_NUM             nvarchar(64)         null,
   CONF_CODE			nvarchar(100)		 null,
   JOB_CODE             nvarchar(64)         null,
   FAIL_REMARK 			nvarchar(1024) 		 null,
   EXPIRE_TIME          datetime             null,
   DELAY_DAYS           int                  null,
   MATTER_STATE 		int					 null,
   CYC_REMARKS          nvarchar(1024)       null,
   SUPERVISE_USER_IIDD  nvarchar(64)         null,
   SRC_CODE             nvarchar(128)        null,
   CONFIDENTIAL_NUM		nvarchar(128)		 null,
   OUTPUT_UNDERTAKER  	nvarchar(100) 		 null,
   OUTPUT_CONFIDENTIAL_NUM  nvarchar(128) 	 null,
   constraint PK_ENTITY_CD primary key (CD_ID)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '（0普通数据；1镜像；2音视频）',
   'user', @CurrentUser, 'table', 'ENTITY_CD', 'column', 'DATA_TYPE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0表示留用，1已回收，2已外发，3已归档，4已销毁,，5流转中，6借用中，7申请销毁，8申请外发，9申请归档10待交接',
   'user', @CurrentUser, 'table', 'ENTITY_CD', 'column', 'CD_STATE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'BURN刻录LEADIN录入',
   'user', @CurrentUser, 'table', 'ENTITY_CD', 'column', 'CREATE_TYPE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'PERSON个人文件DEPT部门文件，默认PERSON',
   'user', @CurrentUser, 'table', 'ENTITY_CD', 'column', 'SCOPE'
go

/*==============================================================*/
/* Table: ENTITY_DEVICE                                         */
/*==============================================================*/
create table ENTITY_DEVICE (
   DEVICE_CODE          nvarchar(64)         not null,
   DEVICE_NAME          nvarchar(1024)       null,
   USER_IIDD            nvarchar(64)         not null,
   DEPT_ID              nvarchar(64)         null,
   DEVICE_SERIES        nvarchar(1024)       null,
   DEVICE_BARCODE       nvarchar(1024)       not null,
   ENTER_TIME           datetime	     null,
   MED_TYPE             int                  not null,
   SECLV_CODE           int                  not null,
   DUTY_USER_IIDD       nvarchar(1024)       null,
   MED_CONTENT          nvarchar(1024)       null,
   BORROW_USER_IIDD     nvarchar(64)         null,
   DEVICE_STATUS        int                  not null,
   DEVICE_MODEL         nvarchar(64)         null,
   DEVICE_DETAIL        nvarchar(256)        null,
   IS_SEALED            char(1)              null default 'N',
   JOB_CODE             nvarchar(64)         null,
   SRC_CODE             nvarchar(128)        null,
   SCOPE                nvarchar(64)         null,
   SUPERVISE_USER_IIDD  nvarchar(64)         null,
   DESTROY_TIME         datetime	         null,
   CONFIDENTIAL_NUM		nvarchar(128)		 null
   constraint PK_ENTITY_DEVICE primary key (DEVICE_CODE)
)
go

/*==============================================================*/
/* Table: ENTITY_PAPER                                          */
/*==============================================================*/
create table ENTITY_PAPER (
   PAPER_ID             int                  identity(1,1),
   PAPER_BARCODE        nvarchar(64)         null
   	constraint CONS_UNI_PAPER_BARCODE unique (PAPER_BARCODE),
   EVENT_CODE           nvarchar(64)         null,
   USER_IIDD            nvarchar(64)         null,
   USER_NAME            nvarchar(64)         null,
   DEPT_ID              nvarchar(100)        null,
   DEPT_NAME            nvarchar(64)         null,
   DUTY_USER_IIDD       nvarchar(64)         null,
   DUTY_USER_NAME       nvarchar(64)         null,
   DUTY_DEPT_ID         nvarchar(64)         null,
   DUTY_DEPT_NAME       nvarchar(64)         null,
   SECLV_CODE           int                  null,
   PRINT_TIME           datetime             null,
   IS_REPRINT           nvarchar(10)         null default '0',
   PRINT_RESULT         int                  null default 1,
   PRINT_RESULT_DETAIL  nvarchar(128)        null,
   FILE_TITLE           nvarchar(1024)       null,
   PROJECT_CODE         nvarchar(1024)       null,
   FILEID               nvarchar(128)        null,
   PAGE_COUNT           int                  null,
   PAGE_SIZE            nvarchar(1024)       null,
   COLOR                int                  null,
   PRINT_DIRECT         int                  null,
   PRINT_DOUBLE         int                  null,
   PAPER_STATE          int                  not null default 0,
   CREATE_TYPE          nvarchar(64)         not null default 'PRINT'
      constraint CKC_CREATE_TYPE_ENTITY_P check (CREATE_TYPE in ('PRINT','COPY','LEADIN','OUTCOPY','SPECIAL','MERGE_ENTITY')),
   SCOPE                nvarchar(64)         not null default 'PERSON'
      constraint CKC_SCOPE_ENTITY_P check (SCOPE in ('PERSON','DEPT')),
   SCOPE_DEPT_ID        nvarchar(100)        null,
   SCOPE_DEPT_NAME      nvarchar(100)        null,
   PRINTER_CODE         nvarchar(64)         null,
   PRINTER_NAME         nvarchar(1024)       null,
   RETRIEVE_TIME        datetime             null,
   RETRIEVE_TYPE        int                  null,
   RETRIEVE_USER_IIDD   nvarchar(64)         null,
   RETRIEVE_BOX_CODE    nvarchar(64)         null,
   DESTROY_TIME         datetime             null,
   DESTROY_USER_IIDD    nvarchar(64)         null,
   SEND_NUM             nvarchar(64)         null,
   JOB_CODE             nvarchar(64)         null,
   FAIL_REMARK 			nvarchar(1024) 		 null,
   EXPIRE_TIME          datetime             null,
   DELAY_DAYS           int                  null,
   PID_barcode 			nvarchar(1024) 		 null,
   PID_PAGENUM 			nvarchar(1024) 		 null,
   RETRIEVE_PAGENUM 	nvarchar(1024) 		 null,
   RETRIEVE_REPLACE 	nvarchar(1024) 		 null,
   DESTROY_PAGENUM  	nvarchar(1024) 		 null,
   MATTER_STATE 		int 				 null,
   CYC_REMARKS          nvarchar(1024)       null,
   REAL_PAGE_COUNT      nvarchar(64)         null,
   SECRET_BOOK		    int 				 null,
   constraint PK_ENTITY_PAPER primary key (PAPER_ID),
   COMPANY              nvarchar(64)         null,
   PUBLISH_TYPE         nvarchar(64)         null,
   COMPANY_SEND         nvarchar(64)         null,
   URGENCY_LV           nvarchar(64)         null,
   FILED_DATE           nvarchar(64)         null,
   PUBLISH_LIMITS       nvarchar(64)         null,
   SUMM                 nvarchar(1024)       null,
   SUPERVISE_USER_IIDD  nvarchar(64)         null,
   SRC_CODE             nvarchar(128)        null,
   SPLIT_BARCODE        nvarchar(64)         null,
   CONFIDENTIAL_NUM		nvarchar(128)		 null,
   OUTPUT_UNDERTAKER  	nvarchar(100) 		 null,
   OUTPUT_CONFIDENTIAL_NUM  nvarchar(128) 	 null,
   MERGE_STATE          int                  not null default 0,
   MERGE_CODE			nvarchar(100)		 null,
   MERGECODE_PRINT		int                  not null default 0
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0表示留用，1已回收，2已外发，3已归档，4已销毁，5流转中，6借用中，7申请销毁，8申请外发，9申请归档10待交接',
   'user', @CurrentUser, 'table', 'ENTITY_PAPER', 'column', 'PAPER_STATE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'PRINT打印COPY复印LEADIN录入',
   'user', @CurrentUser, 'table', 'ENTITY_PAPER', 'column', 'CREATE_TYPE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'PERSON个人文件DEPT部门文件，默认PERSON',
   'user', @CurrentUser, 'table', 'ENTITY_PAPER', 'column', 'SCOPE'
go

/*==============================================================*/
/* Table: ENTITY_STORAGE                                        */
/*==============================================================*/
create table ENTITY_STORAGE (
   STORAGE_CODE         nvarchar(100)        not null,
   STORAGE_NAME         nvarchar(100)        not null,
   INPUT_USER_IIDD      nvarchar(100)        not null,
   INPUT_USER_NAME      nvarchar(100)        null,
   DEPT_ID              nvarchar(100)        not null,
   DEPT_NAME            nvarchar(100)        null,
   STORAGE_SERIES       nvarchar(1024)       null,
   STORAGE_BARCODE      nvarchar(64)         not null,
   INPUT_TIME           datetime             null,
   MED_TYPE             int                  null,
   SECLV_CODE           int                  not null,
   SECLV_NAME           nvarchar(100)        null,
   DUTY_USER_IIDD       nvarchar(100)        not null,
   DUTY_USER_NAME       nvarchar(100)        null,
   USE_USER_IIDD        nvarchar(100)        null,
   USE_USER_NAME        nvarchar(100)        null,
   STORAGE_STATUS       int                  not null default 0,
   STORAGE_MODEL        nvarchar(100)        null,
   STORAGE_DETAIL       nvarchar(1024)       null,
   IS_SEALED            char(1)              null default 'N',
   constraint PK_ENTITY_STORAGE primary key (STORAGE_CODE)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1,固定硬盘2存储卡3其他',
   'user', @CurrentUser, 'table', 'ENTITY_STORAGE', 'column', 'MED_TYPE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '（0未分配1已分配，2送修处理中3报废处理中）默认0',
   'user', @CurrentUser, 'table', 'ENTITY_STORAGE', 'column', 'STORAGE_STATUS'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '（Y/N）默认为N',
   'user', @CurrentUser, 'table', 'ENTITY_STORAGE', 'column', 'IS_SEALED'
go

/*==============================================================*/
/* Table: EVENT_BORROW                                          */
/*==============================================================*/
create table EVENT_BORROW (
   ID                   int                  identity(1,1),
   EVENT_CODE           nvarchar(64)         null,
   USER_IIDD            nvarchar(100)        null,
   DEPT_ID              nvarchar(100)        null,
   SECLV_CODE           int                  null,
   ENTITY_TYPE          nvarchar(64)         null,
   BARCODE              nvarchar(64)         null,
   ENTITY_NAME          nvarchar(1024)       null,
   PROJECT_CODE         nvarchar(64)         null,
   USAGE_CODE           nvarchar(64)         null,
   SUMM                 nvarchar(1024)       null,
   LEND_DEPT_ID         nvarchar(64)         null,
   LEND_USER_IIDD       nvarchar(64)         null,
   APPLY_TIME           datetime             null,
   FINISH_TIME          datetime             null,
   RETURN_TIME          datetime             null,
   BORROW_STATUS        int                  null,
   JOB_CODE             nvarchar(100)        null,
   HIS_JOB_CODE         nvarchar(1024)       null,
   LIMIT_TIME			datetime			 null,
   IS_SURE			    nvarchar(64) 		 not null default 'N',
   constraint PK_EVENT_BORROW primary key (ID)
)
go

/*==============================================================*/
/* Table: EVENT_BURN                                            */
/*==============================================================*/
create table EVENT_BURN (
   ID                   int                  identity(1,1),
   EVENT_CODE           nvarchar(64)         null,
   USER_IIDD            nvarchar(100)        null,
   DEPT_ID              nvarchar(100)        null,
   PROJECT_CODE         nvarchar(64)         null,
   USAGE_CODE           nvarchar(64)         null,
   CYCLE_TYPE           nvarchar(64)         not null default 'REMAIN'
      constraint CKC_CYCLE_TYPE_EVENT_BU check (CYCLE_TYPE in ('REMAIN','FILE','SEND')),
   SUMM                 nvarchar(1024)       null,
   CD_NUM               int                  null,
   SECLV_CODE           int                  null,
   DATA_TYPE            int                  null,
   FILE_NUM             int                  null,
   FILE_LIST            text                 null,
   FILE_SECLEVEL        nvarchar(1000)       null,
   APPLY_TIME           datetime             null,
   BURN_STATUS          int                  null default 0,
   FINISH_TIME          datetime             null,
   CD_SERIAL            nvarchar(512)        null,
   IS_PROXY             int                  null,
   IS_LOCK              int                  null,
   REMAINTIMES          int                  null,
   CONF_CODE			nvarchar(100)		 null,
   JOB_CODE             nvarchar(100)        null,
   HIS_JOB_CODE         nvarchar(1024)       null,
   FIXEDNUM             int                  null default 0,
   PERIOD				char(1) 			 not null default 'S',
   CD_BARCODE           nvarchar(64)         null,
   PRINT_EVENTCODE      nvarchar(64)         null,
   PROXYBURN_USER_IIDD   nvarchar(100)        null,
   constraint PK_EVENT_BURN primary key (ID)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '留用REMAIN,归档FILE,外发SEND',
   'user', @CurrentUser, 'table', 'EVENT_BURN', 'column', 'CYCLE_TYPE'
go

/*==============================================================*/
/* Table: EVENT_COPY                                            */
/*==============================================================*/
create table EVENT_COPY (
   ID                   int                  identity(1,1),
   EVENT_CODE           nvarchar(64)         null,
   USER_IIDD            nvarchar(100)        null,
   DEPT_ID              nvarchar(100)        null,
   ORIGINALID           nvarchar(max)         null,
   FILE_NAME            nvarchar(max)        null,
   SECLV_CODE           int                  null,
   COPY_NUM             int                  null,
   PAGE_NUM             int                  null,
   SOURCE               nvarchar(512)        null,
   PLACE                nvarchar(64)         null,
   PROJECT_CODE         nvarchar(64)         null,
   USAGE_CODE           nvarchar(64)         null,
   SUMM                 nvarchar(1024)       null,
   IS_DOUBLE            int                  null,
   PAGE_TYPE            nvarchar(64)         null,
   SCALE                int                  null,
   ORIENTATION          int                  null,
   COLOR                int                  null,
   APPLY_TIME           datetime             null,
   FINISH_TIME          DATETIME             null,
   COPY_STATUS          int                  null,
   IS_BARCODE           int                  null,
   JOB_CODE             nvarchar(100)        null,
   HIS_JOB_CODE         nvarchar(1024)       null,
   CYCLE_TYPE			nvarchar(64)		 not null default 'REMAIN',
   PERIOD               char(1)              not null default 'L',
   COPY_TYPE			nvarchar(64)		 not null default 'internal',
   COPY_MERGE           nvarchar(100)        null,
   constraint PK_EVENT_COPY primary key (ID)
)
go

/*==============================================================*/
/* Table: EVENT_DEVICE                                          */
/*==============================================================*/
create table EVENT_DEVICE (
   ID                   int                  identity(1,1),
   EVENT_CODE           nvarchar(64)         null,
   USER_IIDD            nvarchar(100)        null,
   DEPT_ID              nvarchar(100)        null,
   SECLV_CODE           int                  null,
   PROJECT_CODE         nvarchar(64)         null,
   USAGE_CODE           nvarchar(64)         null,
   SUMM                 nvarchar(1024)       null,
   MED_TYPE             int                  null,
   BORROW_TYPE          int                  null,
   DEVICE_SERIES        nvarchar(64)         null,
   DEVICE_BARCODE       nvarchar(64)         null,
   APPLY_TIME           datetime             null,
   FINISH_TIME          datetime             null,
   RETURN_TIME          datetime             null,
   BORROW_DATE          int                  null,
   PLACE                nvarchar(512)        null,
   DEVICE_STATUS        int                  null default 0,
   JOB_CODE             nvarchar(100)        null,
   HIS_JOB_CODE         nvarchar(1024)       null,
   constraint PK_EVENT_DEVICE primary key (ID)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0为交接，1已交接，2已归还',
   'user', @CurrentUser, 'table', 'EVENT_DEVICE', 'column', 'DEVICE_STATUS'
go

/*==============================================================*/
/* Table: EVENT_IMPORT                                          */
/*==============================================================*/
create table EVENT_IMPORT (
   ID                   int                  identity(1,1),
   EVENT_CODE           nvarchar(64)         not null,
   USER_IIDD            nvarchar(100)        not null,
   DEPT_ID              nvarchar(100)        null,
   ZIPFILE              nvarchar(1024)       null,
   SECLV_CODE           int                  null,
   MEDIUM_SERIAL        nvarchar(64)         null,
   PAGE_NUM             int                  null,
   SOURCE               nvarchar(512)        null,
   PLACE                nvarchar(512)        null,
   PROJECT_CODE         nvarchar(64)         null,
   USAGE_CODE           nvarchar(64)         null,
   SUMM                 nvarchar(1024)       null,
   APPLY_TIME           datetime             null,
   IMPORT_STATUS        int                  null,
   FILE_TYPE            int                  null,
   FINISH_TIME          datetime             null,
   IMPORT_USER_IIDD     nvarchar(64)          null,
   SCOPE                nvarchar(64)          not null default 'PERSON'
      constraint CKC_SCOPE_EVENT_IM check (SCOPE in ('DEPT','PERSON')),
   SCOPE_DEPT_ID        nvarchar(100)         null,
   JOB_CODE             nvarchar(100)        null,
   HIS_JOB_CODE         nvarchar(1024)       null,
   PERIOD               char(1)              not null default 'S',
   FILE_KIND            nvarchar(64)         not null default 'NORMAL',
   ENTER_NUM            int                  not null default 1,
   FILE_NUM             nvarchar(64)         null,
   CONF_CODE			nvarchar(100)		 null,
   constraint PK_EVENT_IMPORT primary key (ID),
   PAPER_BARCODE        nvarchar(64)         null,
   COMPANY              nvarchar(64)         null,
   PUBLISH_TYPE         nvarchar(64)         null,
   FILEID               nvarchar(128)        null,
   COMPANY_SEND         nvarchar(64)         null,
   URGENCY_LV           nvarchar(64)         null,
   FILED_DATE           nvarchar(64)         null,
   PUBLISH_LIMITS       nvarchar(64)         null,
   CREATE_TYPE          nvarchar(64)         null,
   CREATE_USER_NAME     nvarchar(64)         null,
   SRC_CODE             nvarchar(128)        null,
   CONFIDENTIAL_NUM		nvarchar(128)		 null
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0未录入1已录入',
   'user', @CurrentUser, 'table', 'EVENT_IMPORT', 'column', 'IMPORT_STATUS'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '（1为文件2为光盘）',
   'user', @CurrentUser, 'table', 'EVENT_IMPORT', 'column', 'FILE_TYPE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'PERSON个人文件DEPT部门文件,默认PERSON',
   'user', @CurrentUser, 'table', 'EVENT_IMPORT', 'column', 'SCOPE'
go

/*==============================================================*/
/* Table: EVENT_PRINT                                           */
/*==============================================================*/
create table EVENT_PRINT (
   ID                   int                  identity(1,1),
   EVENT_CODE           nvarchar(64)         null,
   SECLV_CODE           int                  null,
   PRINT_TYPE           int                  not null default 1
      constraint CKC_PRINT_TYPE_EVENT_PR check (PRINT_TYPE in (1,2,3,4,5)),
   PART_NUM             int                  null,
   DURA_TION            int                  null,
   EXTENT               nvarchar(64)         null,
   USER_IIDD            nvarchar(100)        null,
   DEPT_ID              nvarchar(100)        null,
   DL_FILENAME          nvarchar(1024)       null,
   ST_FILENAME          nvarchar(1024)       null,
   FILE_TITLE           nvarchar(1024)       null,
   PROJECT_CODE         nvarchar(64)         null,
   USAGE_CODE           nvarchar(64)         null,
   CYCLE_TYPE           nvarchar(64)         not null default 'REMAIN'
      constraint CKC_CYCLE_TYPE_EVENT_PR check (CYCLE_TYPE in ('REMAIN','FILE','SEND')),
   SUMM                 nvarchar(1024)       null,
   PLACE                nvarchar(1024)       null,
   FILEID               nvarchar(128)        null,
   PRINT_COUNT          int                  null,
   PAGE_COUNT           int                  null,
   PAGE_SIZE            nvarchar(1024)       null,
   COLOR                int                  null default 1,
   PRINT_DIRECT         int                  null,
   PRINT_DOUBLE         int                  null,
   PRINT_COLLATE        int                  null,
   FILE_SIZE            int                  null,
   APPLY_TIME           datetime             null,
   FINISH_TIME          datetime             null,
   PRINT_STATUS         int                  not null default 0,
   FILE_MD5             nvarchar(128)        null,
   IS_LOCK              int                  null default 0,
   ILLEGELWORD          int                  null,
   REMAINTIMES          int                  null,
   FIXEDNUM             int                  null,
   RESERVED             nvarchar(64)         null,
   JOB_CODE             nvarchar(100)        null,
   HIS_JOB_CODE         nvarchar(1024)       null,
   KEYWORD_CONTENT      nvarchar(1024)       null,
   PERIOD				char(1) 			 not null default 'S',
   PID_barcode          nvarchar(1024)       null,
   PID_PAGENUM          nvarchar(1024)       null,
   LOCK_TIME            datetime             null,
   CONSOLE_CODE         nvarchar(64)         null,
   FILE_IS_READ         int                  not null default 0,
   PROXYPRINT_USER_IIDD   nvarchar(100)      null,
   SECLV_ACCORD         nvarchar(1024)       null,
   SECRET_TIME          nvarchar(1024)       null,
   FILE_SCOPE			nvarchar(1024)       null,
   constraint PK_EVENT_PRINT primary key (ID)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1普通打印，2拼图打印，3替换页打印, 4针式打印',
   'user', @CurrentUser, 'table', 'EVENT_PRINT', 'column', 'PRINT_TYPE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '留用REMAIN,归档FILE,外发SEND',
   'user', @CurrentUser, 'table', 'EVENT_PRINT', 'column', 'CYCLE_TYPE'
go

/*==============================================================*/
/* Table: EVENT_TRANSFER                                        */
/*==============================================================*/
create table EVENT_TRANSFER (
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
   ACCEPT_USER_IIDD     nvarchar(64)         null,
   ACCEPT_DEPT_ID       nvarchar(64)         null,
   APPLY_TIME           datetime             null,
   FINISH_TIME          datetime             null,
   TRANSFER_STATUS      int                  null,
   JOB_CODE             nvarchar(100)        null,
   HIS_JOB_CODE         nvarchar(1024)       null,
   constraint PK_EVENT_TRANSFER primary key (ID)
)
go

/*==============================================================*/
/* Table: FILE_INFO                                             */
/*==============================================================*/
create table FILE_INFO (
   ID                   int                  identity(1,1),
   FILE_NAME            nvarchar(100)        not null,
   PROD_NUM             nvarchar(100)        not null,
   VERSION              nvarchar(100)        not null,
   STORE_LOCATION       nvarchar(128)        not null,
   CREATE_USER_IIDD     nvarchar(100)        not null,
   CREATE_TIME          datetime             not null,
   UPDATE_USER_IIDD     nvarchar(100)        null,
   UPDATE_TIME          datetime             null,
   COMMENT              nvarchar(100)        null,
   TYPE                 nvarchar(100)        null,
   constraint PK_FILE_INFO primary key (ID),
   constraint AK_VERSION_UNIQUE_FILE_INF unique (VERSION)
)
go

/*==============================================================*/
/* Table: JOB_DEVICE                                            */
/*==============================================================*/
create table JOB_DEVICE (
   JOB_CODE             nvarchar(64)         not null,
   SECLV_CODE           int                  null,
   USER_IIDD            nvarchar(100)        null,
   DEPT_ID              nvarchar(100)        null,
   START_TIME           datetime             null,
   PROJECT_CODE         nvarchar(64)         null,
   USAGE_CODE           nvarchar(64)         null,
   CONTENT              nvarchar(1024)       null,
   constraint PK_JOB_DEVICE primary key (JOB_CODE)
)
go

/*==============================================================*/
/* Table: JOB_INPUT                                             */
/*==============================================================*/
create table JOB_INPUT (
   JOB_CODE             nvarchar(64)         not null,
   SECLV_CODE           int                  null,
   USER_IIDD            nvarchar(100)        null,
   DEPT_ID              nvarchar(100)        null,
   ACCEPT_USER_IIDD     nvarchar(100)        null,
   INPUT_SOURCE         int                  null,
   SOURCE_UNIT          int                  null,
   START_TIME           datetime             null,
   PROJECT_CODE         nvarchar(64)         null,
   USAGE_CODE           nvarchar(64)         null,
   CONTENT              nvarchar(1024)       null,
   constraint PK_JOB_INPUT primary key (JOB_CODE)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1纸介质、2光盘、3U盘、4互联网',
   'user', @CurrentUser, 'table', 'JOB_INPUT', 'column', 'INPUT_SOURCE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0单位/1个人',
   'user', @CurrentUser, 'table', 'JOB_INPUT', 'column', 'SOURCE_UNIT'
go

/*==============================================================*/
/* Table: JOB_OUTPUT                                            */
/*==============================================================*/
create table JOB_OUTPUT (
   JOB_CODE             nvarchar(64)         not null,
   SECLV_CODE           int                  null,
   USER_IIDD            nvarchar(100)        null,
   DEPT_ID              nvarchar(100)        null,
   ACCEPT_DEPT_NAME     nvarchar(100)        null,
   ACCEPT_USER_NAME     nvarchar(100)        null,
   START_TIME           datetime             null,
   PROJECT_CODE         nvarchar(64)         null,
   USAGE_CODE           nvarchar(64)         null,
   CONTENT              nvarchar(1024)       null,
   constraint PK_JOB_OUTPUT primary key (JOB_CODE)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '打印、复印、刻录的输出审批单',
   'user', @CurrentUser, 'table', 'JOB_OUTPUT'
go

/*==============================================================*/
/* Table: JOB_PROCESS                                           */
/*==============================================================*/
create table JOB_PROCESS (
   JOB_CODE             nvarchar(100)        not null,
   JOBTYPE_CODE         nvarchar(100)        not null,
   SECLV_CODE           int                  not null,
   USER_IIDD            nvarchar(100)        not null,
   DEPT_ID              nvarchar(100)        not null,
   JOB_STATUS           nvarchar(100)        not null,
   START_TIME           datetime             not null,
   INSTANCE_ID          nvarchar(100)        not null,
   PROCESS_ID           nvarchar(100)        null,
   NEXT_APPROVER        nvarchar(1024)       null,
   NEXT_APPROVER_NAME   nvarchar(1024)       null,
   EXP_DEPT_ID          nvarchar(100)        null,
   EXP_ROLE_ID          nvarchar(100)        null,
   COMMENT              nvarchar(1024)       null,
   ACCEPT_USER_IIDD     nvarchar(100)        null,
   OUTPUT_DEPT_NAME     nvarchar(100)        null,
   OUTPUT_USER_NAME     nvarchar(100)        null,
   IS_RECEIPT           char(1)              null default 'N'
      constraint CKC_IS_RECEIPT_JOB_PROC check (IS_RECEIPT is null or (IS_RECEIPT in ('N','Y'))),
   INPUT_SOURCE         int                  null,
   SOURCE_UNIT          int                  null,
   TRANSFER_TYPE        nvarchar(64)         null,
   UNIT_CODE            nvarchar(64)         null,
   BOX_CODE             nvarchar(64)         null,
   PIC_NAME             nvarchar(64)         null,
   FILE_READ_STATUS     int                  not null default 0,
   SEND_WAY 			nvarchar(100) 		 null,
   OUTPUT_UNDERTAKER 	nvarchar(100) 		 null,
   CARRYOUT_USER_IIDDS 	nvarchar(100) 		 null,
   CARRYOUT_USER_NAMES 	nvarchar(100) 		 null,
   constraint PK_JOB_PROCESS primary key (JOB_CODE)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1纸介质、2光盘、3U盘、4互联网',
   'user', @CurrentUser, 'table', 'JOB_PROCESS', 'column', 'INPUT_SOURCE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0单位/1个人',
   'user', @CurrentUser, 'table', 'JOB_PROCESS', 'column', 'SOURCE_UNIT'
go

/*==============================================================*/
/* Table: JOB_TRANSFER                                          */
/*==============================================================*/
create table JOB_TRANSFER (
   JOB_CODE             nvarchar(64)         not null,
   SECLV_CODE           int                  null,
   USER_IIDD            nvarchar(100)        null,
   DEPT_ID              nvarchar(100)        null,
   ACCEPT_USER_IIDD     nvarchar(100)        null,
   TRANSFER_TYPE        nvarchar(64)         null,
   START_TIME           datetime             null,
   PROJECT_CODE         nvarchar(64)         null,
   USAGE_CODE           nvarchar(64)         null,
   CONTENT              nvarchar(1024)       null,
   UNIT_CODE            nvarchar(64)         null,
   BOX_CODE             nvarchar(64)         null,
   PIC_NAME             nvarchar(64)         null,
   constraint PK_JOB_TRANSFER primary key (JOB_CODE)
)
go

/*==============================================================*/
/* Table: LOG_OPERATION_ADMIN                                   */
/*==============================================================*/
create table LOG_OPERATION_ADMIN (
   ID                   int                  identity(1,1),
   USER_ID              nvarchar(128)        null,
   USER_NAME            nvarchar(128)        null,
   DEPT_NAME            nvarchar(128)        null,
   LOG_DETAIL           nvarchar(1024)       null,
   RESULT               nvarchar(128)        null,
   LOG_TIME             datetime             null,
   LOG_TYPE             int                  null,
   LOGIN_IP             nvarchar(128)        null,
   LOGIN_HOSTNAME       nvarchar(128)        null,
   SUBSYS_CODE          nvarchar(100)        null,
   constraint PK_LOG_OPERATION_ADMIN primary key nonclustered (ID)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '管理员操作日志表',
   'user', @CurrentUser, 'table', 'LOG_OPERATION_ADMIN'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '自增量主键',
   'user', @CurrentUser, 'table', 'LOG_OPERATION_ADMIN', 'column', 'ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '管理员id，便于后续关联查询
   ',
   'user', @CurrentUser, 'table', 'LOG_OPERATION_ADMIN', 'column', 'USER_ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '管理员名称',
   'user', @CurrentUser, 'table', 'LOG_OPERATION_ADMIN', 'column', 'USER_NAME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '部门名称',
   'user', @CurrentUser, 'table', 'LOG_OPERATION_ADMIN', 'column', 'DEPT_NAME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '日志内容（拼串记录详细内容）',
   'user', @CurrentUser, 'table', 'LOG_OPERATION_ADMIN', 'column', 'LOG_DETAIL'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '操作结果（成功、失败）',
   'user', @CurrentUser, 'table', 'LOG_OPERATION_ADMIN', 'column', 'RESULT'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '产生时间，精确到秒，格式如2013-1-1 00:00:00',
   'user', @CurrentUser, 'table', 'LOG_OPERATION_ADMIN', 'column', 'LOG_TIME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '操作类型（）',
   'user', @CurrentUser, 'table', 'LOG_OPERATION_ADMIN', 'column', 'LOG_TYPE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '登陆IP地址',
   'user', @CurrentUser, 'table', 'LOG_OPERATION_ADMIN', 'column', 'LOGIN_IP'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '登陆主机名称',
   'user', @CurrentUser, 'table', 'LOG_OPERATION_ADMIN', 'column', 'LOGIN_HOSTNAME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '子系统标志位（PRINT，打印系统；BURN，刻录系统；NAS，安全NAS；NAD，接入控制系统）',
   'user', @CurrentUser, 'table', 'LOG_OPERATION_ADMIN', 'column', 'SUBSYS_CODE'
go

/*==============================================================*/
/* Table: LOG_OPERATION_COMMON                                  */
/*==============================================================*/
create table LOG_OPERATION_COMMON (
   ID                   int                  identity(1,1),
   USER_ID              nvarchar(128)        null,
   USER_NAME            nvarchar(128)        null,
   DEPT_NAME            nvarchar(128)        null,
   LOG_DETAIL           nvarchar(1024)       null,
   RESULT               nvarchar(128)        null,
   LOG_TIME             datetime             null,
   LOG_TYPE             int                  null,
   LOGIN_IP             nvarchar(128)        null,
   LOGIN_HOSTNAME       nvarchar(128)        null,
   SUBSYS_CODE          nvarchar(100)        null,
   constraint PK_LOG_OPERATION_COMMON primary key nonclustered (ID)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '普通用户操作日志表',
   'user', @CurrentUser, 'table', 'LOG_OPERATION_COMMON'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '自增量主键',
   'user', @CurrentUser, 'table', 'LOG_OPERATION_COMMON', 'column', 'ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户id，便于后续关联查询
   ',
   'user', @CurrentUser, 'table', 'LOG_OPERATION_COMMON', 'column', 'USER_ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户名称',
   'user', @CurrentUser, 'table', 'LOG_OPERATION_COMMON', 'column', 'USER_NAME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '部门名称',
   'user', @CurrentUser, 'table', 'LOG_OPERATION_COMMON', 'column', 'DEPT_NAME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '日志内容（拼串记录详细内容）',
   'user', @CurrentUser, 'table', 'LOG_OPERATION_COMMON', 'column', 'LOG_DETAIL'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '操作结果（成功、失败）',
   'user', @CurrentUser, 'table', 'LOG_OPERATION_COMMON', 'column', 'RESULT'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '产生时间，精确到秒，格式如2013-1-1 00:00:00',
   'user', @CurrentUser, 'table', 'LOG_OPERATION_COMMON', 'column', 'LOG_TIME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '操作类型（）',
   'user', @CurrentUser, 'table', 'LOG_OPERATION_COMMON', 'column', 'LOG_TYPE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '登陆IP地址',
   'user', @CurrentUser, 'table', 'LOG_OPERATION_COMMON', 'column', 'LOGIN_IP'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '登陆主机名称',
   'user', @CurrentUser, 'table', 'LOG_OPERATION_COMMON', 'column', 'LOGIN_HOSTNAME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '子系统标志位（PRINT，打印系统；BURN，刻录系统；NAS，安全NAS；NAD，接入控制系统）',
   'user', @CurrentUser, 'table', 'LOG_OPERATION_COMMON', 'column', 'SUBSYS_CODE'
go

/*==============================================================*/
/* Table: LOG_SYSTEM                                            */
/*==============================================================*/
create table LOG_SYSTEM (
   ID                   int                  identity(1,1),
   SUBSYS_CODE          nvarchar(100)        not null,
   SUBSYS_NAME          nvarchar(100)        not null,
   SOURCE_MODULE        int                  not null,
   LOG_TYPE             int                  null,
   LOG_DETAIL           nvarchar(1024)       null,
   LOG_TIME             datetime             null,
   constraint PK_LOG_SYSTEM primary key nonclustered (ID)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '系统安全日志表',
   'user', @CurrentUser, 'table', 'LOG_SYSTEM'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '自增量主键',
   'user', @CurrentUser, 'table', 'LOG_SYSTEM', 'column', 'ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '日志来源--产品编码（PRINT；BURN；NAS；NAD）
   ',
   'user', @CurrentUser, 'table', 'LOG_SYSTEM', 'column', 'SUBSYS_CODE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '日志来源--产品名称（打印系统；刻录系统；安全NAS；接入控制系统）',
   'user', @CurrentUser, 'table', 'LOG_SYSTEM', 'column', 'SUBSYS_NAME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '日志来源--模块（1，服务器；2，客户端；3，控制台；4，页面；5，其他）',
   'user', @CurrentUser, 'table', 'LOG_SYSTEM', 'column', 'SOURCE_MODULE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '日志类型（1，一般消息；2，警告类；3，严重；4，运维调试类）',
   'user', @CurrentUser, 'table', 'LOG_SYSTEM', 'column', 'LOG_TYPE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '日志内容',
   'user', @CurrentUser, 'table', 'LOG_SYSTEM', 'column', 'LOG_DETAIL'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '产生时间，精确到秒，格式如2013-1-1 00:00:00',
   'user', @CurrentUser, 'table', 'LOG_SYSTEM', 'column', 'LOG_TIME'
go

/*==============================================================*/
/* Table: LOG_USER_LOGIN                                        */
/*==============================================================*/
create table LOG_USER_LOGIN (
   ID                   int                  identity(1,1),
   USER_ID              nvarchar(128)        null,
   USER_NAME            nvarchar(128)        null,
   DEPT_NAME            nvarchar(128)        null,
   LOG_DETAIL           nvarchar(1024)       null,
   RESULT               nvarchar(128)        null,
   LOG_TIME             datetime             null,
   LOGIN_IP             nvarchar(128)        null,
   LOGIN_HOSTNAME       nvarchar(128)        null,
   constraint PK_LOG_USER_LOGIN primary key nonclustered (ID)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户登录日志表',
   'user', @CurrentUser, 'table', 'LOG_USER_LOGIN'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '自增量主键',
   'user', @CurrentUser, 'table', 'LOG_USER_LOGIN', 'column', 'ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '管理员id，便于后续关联查询
   ',
   'user', @CurrentUser, 'table', 'LOG_USER_LOGIN', 'column', 'USER_ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '管理员名称',
   'user', @CurrentUser, 'table', 'LOG_USER_LOGIN', 'column', 'USER_NAME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '部门名称',
   'user', @CurrentUser, 'table', 'LOG_USER_LOGIN', 'column', 'DEPT_NAME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '日志内容（拼串记录详细内容）',
   'user', @CurrentUser, 'table', 'LOG_USER_LOGIN', 'column', 'LOG_DETAIL'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '操作结果（成功、失败）',
   'user', @CurrentUser, 'table', 'LOG_USER_LOGIN', 'column', 'RESULT'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '产生时间，精确到秒，格式如2013-1-1 00:00:00',
   'user', @CurrentUser, 'table', 'LOG_USER_LOGIN', 'column', 'LOG_TIME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '登陆IP地址',
   'user', @CurrentUser, 'table', 'LOG_USER_LOGIN', 'column', 'LOGIN_IP'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '登陆主机名称',
   'user', @CurrentUser, 'table', 'LOG_USER_LOGIN', 'column', 'LOGIN_HOSTNAME'
go

/*==============================================================*/
/* Table: OP_TAG                                                */
/*==============================================================*/
create table OP_TAG (
   TAG_CODE             nvarchar(100)        not null,
   TAG_NAME             nvarchar(100)        null,
   ACTIVATE             int                  null default 1,
   IS_SEALED            char(1)              null default 'N',
   constraint PK_OP_TAG primary key nonclustered (TAG_CODE)
)
go

/*==============================================================*/
/* Table: PROCESS_RECORD                                        */
/*==============================================================*/
create table PROCESS_RECORD (
   JOB_CODE             nvarchar(100)        not null,
   JOBTYPE_CODE         nvarchar(100)        not null,
   JOBTYPE_NAME         nvarchar(100)        null,
   USER_IIDD            nvarchar(100)        not null,
   USER_NAME            nvarchar(100)        null,
   DEPT_NAME            nvarchar(100)        null,
   OPERATION            nvarchar(100)        not null,
   OPINION              nvarchar(1000)       null,
   OP_TIME              datetime             not null
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '记录审批结果：申请号、申请类型、申请类型名称、操作人ID、操作人姓名、操作人部门名称、操作结果、审批意见、操作时间',
   'user', @CurrentUser, 'table', 'PROCESS_RECORD'
go

/*==============================================================*/
/* Table: REAL_USER                                             */
/*==============================================================*/
create table REAL_USER (
   REAL_USER_ID         nvarchar(100)        not null,
   BASE_USERNAME        nvarchar(100)        null,
   BASE_SEX             char(1)              null
      constraint CKC_BASE_SEX_REAL_USE check (BASE_SEX is null or (BASE_SEX in ('M','F'))),
   BASE_NATION          nvarchar(100)        null,
   BASE_BIRTHDAY        datetime             null,
   BASE_BIRTHPLACE      nvarchar(100)        null,
   BASE_NATIVEPLACE     nvarchar(100)        null,
   BASE_COUNTRY         nvarchar(100)        null,
   BASE_POLITICS        nvarchar(100)        null,
   BASE_JOINPARTYTIME   datetime             null,
   EDU_EDUCATION        nvarchar(100)        null,
   EDU_DEGREE           nvarchar(100)        null,
   EDU_SCHOOL           nvarchar(100)        null,
   EDU_MAJOR            nvarchar(100)        null,
   EDU_LANGUAGE         nvarchar(100)        null,
   EDU_FAMILIARITY      nvarchar(100)        null,
   COM_RESIDENCY        nvarchar(100)        null,
   COM_POLICE           nvarchar(100)        null,
   COM_ADDRESS          nvarchar(100)        null,
   COM_TELEPHONE        nvarchar(100)        null,
   COM_MOBILE           nvarchar(100)        null,
   COM_EMAIL            nvarchar(100)        null,
   JOB_CATEGORY         nvarchar(100)        null,
   JOB_SECLEVEL         nvarchar(100)        null,
   JOB_ADMINPOST        nvarchar(100)        null,
   JOB_TECHPOST         nvarchar(100)        null,
   JOB_TECHTITLE        nvarchar(100)        null,
   JOB_HUMANSORT        nvarchar(100)        null,
   JOB_INSECTIME        datetime             null,
   JOB_WORKYEARS        int                  null,
   JOB_EMPLOYTYPE       nvarchar(100)        null,
   JOB_PASSNUM          nvarchar(100)        null,
   JOB_PASSLEVEL        nvarchar(100)        null,
   JOB_INPOSTTIME       datetime             null,
   JOB_OFFPOSTTIME      datetime             null,
   JOB_RESUME           nvarchar(1024)       null,
   IS_SEALED            char(1)              null default 'N',
   constraint PK_REAL_USER primary key nonclustered (REAL_USER_ID)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '实际用户信息表',
   'user', @CurrentUser, 'table', 'REAL_USER'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户ID，主键',
   'user', @CurrentUser, 'table', 'REAL_USER', 'column', 'REAL_USER_ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户真实姓名',
   'user', @CurrentUser, 'table', 'REAL_USER', 'column', 'BASE_USERNAME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '性别',
   'user', @CurrentUser, 'table', 'REAL_USER', 'column', 'BASE_SEX'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '生日',
   'user', @CurrentUser, 'table', 'REAL_USER', 'column', 'BASE_BIRTHDAY'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '学历',
   'user', @CurrentUser, 'table', 'REAL_USER', 'column', 'EDU_EDUCATION'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '固定电话',
   'user', @CurrentUser, 'table', 'REAL_USER', 'column', 'COM_TELEPHONE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '移动电话',
   'user', @CurrentUser, 'table', 'REAL_USER', 'column', 'COM_MOBILE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '电子邮箱',
   'user', @CurrentUser, 'table', 'REAL_USER', 'column', 'COM_EMAIL'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '员工类型',
   'user', @CurrentUser, 'table', 'REAL_USER', 'column', 'JOB_EMPLOYTYPE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '即假删标记，''Y''表示封存''N''或者null表示正常',
   'user', @CurrentUser, 'table', 'REAL_USER', 'column', 'IS_SEALED'
go

/*==============================================================*/
/* Table: REF_PRINTER_GROUP                                     */
/*==============================================================*/
create table REF_PRINTER_GROUP (
   DEPT_ID              nvarchar(100)        not null,
   PRINTER_CODE         nvarchar(64)         not null,
   IS_DEFAULT           char(1)              not null default 'N'
      constraint CKC_IS_DEFAULT_REF_PRIN check (IS_DEFAULT in ('N','Y')),
   constraint PK_REF_PRINTER_GROUP primary key (DEPT_ID, PRINTER_CODE)
)
go

/*==============================================================*/
/* Table: REF_PRINTER_USER                                      */
/*==============================================================*/
create table REF_PRINTER_USER (
   USER_IIDD            nvarchar(100)        not null,
   PRINTER_CODE         nvarchar(64)         not null
)
go

/*==============================================================*/
/* Table: REF_SECLEVEL_ROLE                                     */
/*==============================================================*/
create table REF_SECLEVEL_ROLE (
   SECLV_CODE           int                  not null,
   PRINT_VALUE          nvarchar(1024)       null,
   COPY_VALUE           nvarchar(1024)       null,
   IMPORT_VALUE         nvarchar(1024)       null,
   BURN_VALUE           nvarchar(1024)       null,
   COPY_BURN_VALUE      nvarchar(1024)       null,
   DEVICE_VALUE         nvarchar(1024)       null,
   constraint PK_REF_SECLEVEL_ROLE primary key (SECLV_CODE)
)
go

/*==============================================================*/
/* Table: REJECT_RECORD                                         */
/*==============================================================*/
create table REJECT_RECORD (
   REJECT_CODE          nvarchar(64)         not null,
   ENTITY_BARCODE       nvarchar(64)         not null,
   ENTITY_TYPE          nvarchar(64)         not null
      constraint CKC_ENTITY_TYPE_REJECT_R check (ENTITY_TYPE in ('CD','PAPER')),
   ENTITY_NAME          nvarchar(1024)       null,
   REJECT_TIME          datetime             null,
   SEND_USER_IIDD       nvarchar(100)        not null,
   SEND_DEPT_ID         nvarchar(100)        null,
   REJECT_USER_NAME     nvarchar(100)        null,
   REJECT_DEPT_NAME     nvarchar(100)        null,
   REJECT_TYPE          nvarchar(100)        not null
      constraint CKC_REJECT_TYPE_REJECT_R check (REJECT_TYPE in ('READ','UNREAD','COPY')),
   COMMENT              nvarchar(100)        null,
   constraint PK_REJECT_RECORD primary key (REJECT_CODE)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'CD/PAPER',
   'user', @CurrentUser, 'table', 'REJECT_RECORD', 'column', 'ENTITY_TYPE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'READ/UNREAD/COPY',
   'user', @CurrentUser, 'table', 'REJECT_RECORD', 'column', 'REJECT_TYPE'
go

/*==============================================================*/
/* Table: SEC_CONFIG                                            */
/*==============================================================*/
create table SEC_CONFIG (
   ITEM_KEY             nvarchar(100)        not null,
   ITEM_NAME            nvarchar(100)        null,
   ITEM_VALUE           nvarchar(100)        null,
   ITEM_TYPE            nvarchar(100)        null,
   STARTUSE             int                  null default 1,
   constraint PK_SEC_CONFIG primary key nonclustered (ITEM_KEY)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '系统配置表（竖表）',
   'user', @CurrentUser, 'table', 'SEC_CONFIG'
go

/*==============================================================*/
/* Table: SEC_DEPT                                              */
/*==============================================================*/
create table SEC_DEPT (
   DEPT_ID              nvarchar(100)        not null,
   DEPT_NAME            nvarchar(100)        null,
   DEPT_CS              nvarchar(100)        not null,
   DEPT_PARENT_ID       nvarchar(100)        null,
   DEPT_TYPE_CODE       nvarchar(100)        null,
   TERR_CODE            nvarchar(100)        null,
   DEPT_LEVEL_CODE      nvarchar(100)        null,
   DEPT_DESC            nvarchar(400)        null,
   EXT_CODE             nvarchar(100)        null,
   IS_SEALED            char(1)              null default 'N',
   DEPT_RANK 			nvarchar(100)		 not null default '0',
   constraint PK_SEC_DEPT primary key nonclustered (DEPT_ID)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户部门表',
   'user', @CurrentUser, 'table', 'SEC_DEPT'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '部门代码，主键',
   'user', @CurrentUser, 'table', 'SEC_DEPT', 'column', 'DEPT_ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '部门名称',
   'user', @CurrentUser, 'table', 'SEC_DEPT', 'column', 'DEPT_NAME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '部门类型代码',
   'user', @CurrentUser, 'table', 'SEC_DEPT', 'column', 'DEPT_TYPE_CODE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '部门地点代码',
   'user', @CurrentUser, 'table', 'SEC_DEPT', 'column', 'TERR_CODE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '部门等级代码',
   'user', @CurrentUser, 'table', 'SEC_DEPT', 'column', 'DEPT_LEVEL_CODE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '部门功能描述',
   'user', @CurrentUser, 'table', 'SEC_DEPT', 'column', 'DEPT_DESC'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '扩展字段，用于跟其他系统的集成',
   'user', @CurrentUser, 'table', 'SEC_DEPT', 'column', 'EXT_CODE'
go

/*==============================================================*/
/* Table: SEC_DEPT_ADMIN                                        */
/*==============================================================*/
create table SEC_DEPT_ADMIN (
   USER_IIDD            nvarchar(100)        not null,
   ROLE_ID				nvarchar(100)        not null,
   DEPT_ID              nvarchar(100)        not null,
   DEPT_CS              nvarchar(100)        not null,
   IS_INHERIT           char(1)              not null default 'N'
      constraint CKC_IS_INHERIT_SEC_DEPT check (IS_INHERIT in ('N','Y')),
   TYPEFLAG			    nvarchar(64)		 not null default 'MANAGE',
   constraint PK_SEC_DEPT_ADMIN primary key (USER_IIDD, ROLE_ID, DEPT_ID)
)
go

/*==============================================================*/
/* Table: SEC_DEPT_LEVEL                                        */
/*==============================================================*/
create table SEC_DEPT_LEVEL (
   DEPT_LEVEL_CODE      nvarchar(100)        not null,
   DEPT_LEVEL_NAME      nvarchar(100)        null,
   DEPT_LEVEL_DESC      nvarchar(255)        null,
   IS_SEALED            char(1)              null default 'N',
   constraint PK_SEC_DEPT_LEVEL primary key nonclustered (DEPT_LEVEL_CODE)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '部门等级表',
   'user', @CurrentUser, 'table', 'SEC_DEPT_LEVEL'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '部门等级代码，主键',
   'user', @CurrentUser, 'table', 'SEC_DEPT_LEVEL', 'column', 'DEPT_LEVEL_CODE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '部门等级名称（如单位、部门、班组）',
   'user', @CurrentUser, 'table', 'SEC_DEPT_LEVEL', 'column', 'DEPT_LEVEL_NAME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '等级描述',
   'user', @CurrentUser, 'table', 'SEC_DEPT_LEVEL', 'column', 'DEPT_LEVEL_DESC'
go

/*==============================================================*/
/* Table: SEC_DEPT_POST                                         */
/*==============================================================*/
create table SEC_DEPT_POST (
   DEPT_ID              nvarchar(100)        not null,
   POST_ID              nvarchar(100)        not null,
   constraint PK_SEC_DEPT_POST primary key nonclustered (DEPT_ID, POST_ID)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '部门岗位配置表',
   'user', @CurrentUser, 'table', 'SEC_DEPT_POST'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '部门代码，外键',
   'user', @CurrentUser, 'table', 'SEC_DEPT_POST', 'column', 'DEPT_ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '岗位ID，外键',
   'user', @CurrentUser, 'table', 'SEC_DEPT_POST', 'column', 'POST_ID'
go

/*==============================================================*/
/* Table: SEC_DEPT_SUBSYS                                       */
/*==============================================================*/
create table SEC_DEPT_SUBSYS (
   DEPT_ID              nvarchar(100)        not null,
   SUBSYS_CODE          nvarchar(100)        not null,
   constraint PK_SEC_DEPT_SUBSYS primary key nonclustered (DEPT_ID, SUBSYS_CODE)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '部门和子系统关联表',
   'user', @CurrentUser, 'table', 'SEC_DEPT_SUBSYS'
go

/*==============================================================*/
/* Table: SEC_DEPT_TYPE                                         */
/*==============================================================*/
create table SEC_DEPT_TYPE (
   DEPT_TYPE_CODE       nvarchar(100)        not null,
   DEPT_TYPE_NAME       nvarchar(100)        null,
   DEPT_TYPE_DESC       nvarchar(255)        null,
   IS_SEALED            char(1)              null default 'N',
   constraint PK_SEC_DEPT_TYPE primary key nonclustered (DEPT_TYPE_CODE)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '部门类型表',
   'user', @CurrentUser, 'table', 'SEC_DEPT_TYPE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '部门类型代码，主键',
   'user', @CurrentUser, 'table', 'SEC_DEPT_TYPE', 'column', 'DEPT_TYPE_CODE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '部门类型名称',
   'user', @CurrentUser, 'table', 'SEC_DEPT_TYPE', 'column', 'DEPT_TYPE_NAME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '部门类型描述',
   'user', @CurrentUser, 'table', 'SEC_DEPT_TYPE', 'column', 'DEPT_TYPE_DESC'
go

/*==============================================================*/
/* Table: SEC_DOMAIN_MEMBER                                     */
/*==============================================================*/
create table SEC_DOMAIN_MEMBER (
   DOMAIN               nvarchar(100)         null,
   MEMBER_CODE          nvarchar(100)         null
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '指向用户角色关联表中的domain的外键',
   'user', @CurrentUser, 'table', 'SEC_DOMAIN_MEMBER', 'column', 'DOMAIN'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '对应资源的表的主键code',
   'user', @CurrentUser, 'table', 'SEC_DOMAIN_MEMBER', 'column', 'MEMBER_CODE'
go

/*==============================================================*/
/* Table: SEC_OPER                                              */
/*==============================================================*/
create table SEC_OPER (
   OPER_CODE            nvarchar(100)        not null default 'N',
   OPER_NAME            nvarchar(100)        null,
   OPER_DESC            nvarchar(255)        null,
   SUBSYS_CODE          nvarchar(100)        null,
   WEB_URL              nvarchar(255)        null,
   ICON_PATH            nvarchar(255)        null,
   WEB_MARK             nvarchar(255)        null,
   SERVER_MARK          nvarchar(255)        null,
   DIR_RANK				int					 not null default 10,
   EN_DIRECTORY         char(1)              null
      constraint CKC_EN_DIRECTORY_SEC_OPER check (EN_DIRECTORY is null or (EN_DIRECTORY in ('Y','N'))),
   EN_PRVT_OPER         char(1)              null
      constraint CKC_EN_PRVT_OPER_SEC_OPER check (EN_PRVT_OPER is null or (EN_PRVT_OPER in ('Y','N'))),
   IS_SEALED            char(1)              null default 'N',
   constraint PK_SEC_OPER primary key nonclustered (OPER_CODE)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户操作表',
   'user', @CurrentUser, 'table', 'SEC_OPER'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '操作代码',
   'user', @CurrentUser, 'table', 'SEC_OPER', 'column', 'OPER_CODE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '操作名称',
   'user', @CurrentUser, 'table', 'SEC_OPER', 'column', 'OPER_NAME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '操作功能描述',
   'user', @CurrentUser, 'table', 'SEC_OPER', 'column', 'OPER_DESC'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'web平台功能访问路径',
   'user', @CurrentUser, 'table', 'SEC_OPER', 'column', 'WEB_URL'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '图标路径',
   'user', @CurrentUser, 'table', 'SEC_OPER', 'column', 'ICON_PATH'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'WEB端权限标记，为对应action类名',
   'user', @CurrentUser, 'table', 'SEC_OPER', 'column', 'WEB_MARK'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '服务端标记',
   'user', @CurrentUser, 'table', 'SEC_OPER', 'column', 'SERVER_MARK'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '是否目录节点，合法值为Y或者N',
   'user', @CurrentUser, 'table', 'SEC_OPER', 'column', 'EN_DIRECTORY'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '私有操作标志，不会在配置角色的操作列表中显示出来。''Y''是私有操作，''N''不是私有操作',
   'user', @CurrentUser, 'table', 'SEC_OPER', 'column', 'EN_PRVT_OPER'
go

/*==============================================================*/
/* Table: SEC_ROLE                                              */
/*==============================================================*/
create table SEC_ROLE (
   ROLE_ID              nvarchar(100)        not null,
   ROLE_NAME            nvarchar(100)        not null,
   ROLE_DESC            nvarchar(255)        null,
   SUBSYS_CODE          nvarchar(100)        not null,
   ROLE_TYPE            int                  not null,
   ROLE_SPEC_KEY        nvarchar(100)        null,
   IS_SEALED            char(1)              null default 'N',
   constraint PK_SEC_ROLE primary key nonclustered (ROLE_ID)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '角色表',
   'user', @CurrentUser, 'table', 'SEC_ROLE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '角色ID',
   'user', @CurrentUser, 'table', 'SEC_ROLE', 'column', 'ROLE_ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '角色名称',
   'user', @CurrentUser, 'table', 'SEC_ROLE', 'column', 'ROLE_NAME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '角色描述',
   'user', @CurrentUser, 'table', 'SEC_ROLE', 'column', 'ROLE_DESC'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '子系统代码，外键',
   'user', @CurrentUser, 'table', 'SEC_ROLE', 'column', 'SUBSYS_CODE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '角色类型，1为系统内置角色，不在系统内显示。2为只读角色，创建后不能修改和删除。3为普通角色，可以修改和删除',
   'user', @CurrentUser, 'table', 'SEC_ROLE', 'column', 'ROLE_TYPE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '特殊角色（秘书角色）的键值，以区分特殊条目是部门还是地区或是其他',
   'user', @CurrentUser, 'table', 'SEC_ROLE', 'column', 'ROLE_SPEC_KEY'
go

/*==============================================================*/
/* Table: SEC_ROLE_OPER                                         */
/*==============================================================*/
create table SEC_ROLE_OPER (
   ROLE_ID              nvarchar(100)        not null,
   OPER_CODE            nvarchar(100)        not null,
   constraint PK_SEC_ROLE_OPER primary key nonclustered (ROLE_ID, OPER_CODE)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '角色操作关联表',
   'user', @CurrentUser, 'table', 'SEC_ROLE_OPER'
go

/*==============================================================*/
/* Table: SEC_ROLE_USER                                         */
/*==============================================================*/
create table SEC_ROLE_USER (
   USER_IIDD            nvarchar(100)        not null,
   ROLE_ID              nvarchar(100)        not null,
   DOMAIN               nvarchar(100)        null,
   DOMAIN_TABLE         nvarchar(100)        null,
   IS_PROXY				nvarchar(100)		 null,
   AGENT				nvarchar(100)		 null
   constraint PK_SEC_ROLE_USER primary key nonclustered (USER_IIDD, ROLE_ID)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户和角色关联表',
   'user', @CurrentUser, 'table', 'SEC_ROLE_USER'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户关联特殊角色的时候，标识到一个作用域上',
   'user', @CurrentUser, 'table', 'SEC_ROLE_USER', 'column', 'DOMAIN'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '特殊角色关联资源的表名',
   'user', @CurrentUser, 'table', 'SEC_ROLE_USER', 'column', 'DOMAIN_TABLE'
go

/*==============================================================*/
/* Table: SEC_SUBSYS                                            */
/*==============================================================*/
create table SEC_SUBSYS (
   SUBSYS_CODE          nvarchar(100)        not null,
   SUBSYS_NAME          nvarchar(100)        not null,
   SUBSYS_DESC          nvarchar(255)        null,
   OPER_CODE_PREFIX     nvarchar(2)          null,
   IS_SEALED            char(1)              null default 'N',
   constraint PK_SEC_SUBSYS primary key nonclustered (SUBSYS_CODE)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '子系统表',
   'user', @CurrentUser, 'table', 'SEC_SUBSYS'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '主键，子系统代码',
   'user', @CurrentUser, 'table', 'SEC_SUBSYS', 'column', 'SUBSYS_CODE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '子系统名称',
   'user', @CurrentUser, 'table', 'SEC_SUBSYS', 'column', 'SUBSYS_NAME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '子系统描述',
   'user', @CurrentUser, 'table', 'SEC_SUBSYS', 'column', 'SUBSYS_DESC'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '子系统内操作编号的起始前缀（01-09）',
   'user', @CurrentUser, 'table', 'SEC_SUBSYS', 'column', 'OPER_CODE_PREFIX'
go

/*==============================================================*/
/* Table: SEC_TERRITORY                                         */
/*==============================================================*/
create table SEC_TERRITORY (
   TERR_CODE            nvarchar(100)        not null,
   TERR_NAME            nvarchar(100)        null,
   TERR_DESC            nvarchar(100)        null,
   IS_SEALED            char(1)              null default 'N',
   constraint PK_SEC_TERRITORY primary key nonclustered (TERR_CODE)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '部门地区表',
   'user', @CurrentUser, 'table', 'SEC_TERRITORY'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '地区代码，主键',
   'user', @CurrentUser, 'table', 'SEC_TERRITORY', 'column', 'TERR_CODE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '地区名称',
   'user', @CurrentUser, 'table', 'SEC_TERRITORY', 'column', 'TERR_NAME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '地区描述',
   'user', @CurrentUser, 'table', 'SEC_TERRITORY', 'column', 'TERR_DESC'
go

/*==============================================================*/
/* Table: SEC_USER                                              */
/*==============================================================*/
create table SEC_USER (
   USER_IIDD            nvarchar(100)        not null,
   REAL_USER_ID         nvarchar(100)        null,
   USER_NAME            nvarchar(100)        null,
   USER_PPWW            nvarchar(128)        not null,
   DEPT_ID              nvarchar(100)        not null,
   STATUS               int                  null,
   POST_ID              nvarchar(100)        null,
   USER_TYPE            int                  null,
   LAST_LOGON_TIME      datetime             null,
   LAST_CHANGE_PW_TIME  datetime             null,
   LOGFAIL_TIMES        int                  null default 0,
   LOCK_TIME            datetime             null,
   EXT_CODE             nvarchar(255)        null,
   IDCARD               nvarchar(100)        null,
   SECURITY_CODE        nvarchar(100)        null,
   PASS_NUM             nvarchar(100)        null,
   PRINT_METHOD			int					 not null default 0,
   PRINT_PERMISSION		int					 not null default 0,
   IS_SEALED            char(1)              null default 'N',
   RANK					int 				 not null default 100,
   constraint PK_SEC_USER primary key nonclustered (USER_IIDD)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户表',
   'user', @CurrentUser, 'table', 'SEC_USER'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户登录ID，主键',
   'user', @CurrentUser, 'table', 'SEC_USER', 'column', 'USER_IIDD'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '关联到用户信息表主键，外键',
   'user', @CurrentUser, 'table', 'SEC_USER', 'column', 'REAL_USER_ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户登录名
   ',
   'user', @CurrentUser, 'table', 'SEC_USER', 'column', 'USER_NAME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '登录密码',
   'user', @CurrentUser, 'table', 'SEC_USER', 'column', 'USER_PPWW'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户状态',
   'user', @CurrentUser, 'table', 'SEC_USER', 'column', 'STATUS'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户岗位ID',
   'user', @CurrentUser, 'table', 'SEC_USER', 'column', 'POST_ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户类型',
   'user', @CurrentUser, 'table', 'SEC_USER', 'column', 'USER_TYPE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '上次登录时间',
   'user', @CurrentUser, 'table', 'SEC_USER', 'column', 'LAST_LOGON_TIME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '上次修改密码时间',
   'user', @CurrentUser, 'table', 'SEC_USER', 'column', 'LAST_CHANGE_PW_TIME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '扩展字段，用于与其他系统集成等',
   'user', @CurrentUser, 'table', 'SEC_USER', 'column', 'EXT_CODE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '直接存汉字',
   'user', @CurrentUser, 'table', 'SEC_USER', 'column', 'SECURITY_CODE'
go

/*==============================================================*/
/* Table: SEC_USER_POST                                         */
/*==============================================================*/
create table SEC_USER_POST (
   POST_ID              nvarchar(100)        not null,
   POST_NAME            nvarchar(100)        null,
   POST_DESC            nvarchar(255)        null,
   POST_LEVEL           int                  null,
   POST_CLASS           int                  null,
   IS_SEALED            char(1)              null default 'N',
   constraint PK_SEC_USER_POST primary key nonclustered (POST_ID)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户岗位表',
   'user', @CurrentUser, 'table', 'SEC_USER_POST'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '岗位ID，主键',
   'user', @CurrentUser, 'table', 'SEC_USER_POST', 'column', 'POST_ID'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '岗位名称',
   'user', @CurrentUser, 'table', 'SEC_USER_POST', 'column', 'POST_NAME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '岗位描述',
   'user', @CurrentUser, 'table', 'SEC_USER_POST', 'column', 'POST_DESC'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '岗位等级',
   'user', @CurrentUser, 'table', 'SEC_USER_POST', 'column', 'POST_LEVEL'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '岗位分类？',
   'user', @CurrentUser, 'table', 'SEC_USER_POST', 'column', 'POST_CLASS'
go

/*==============================================================*/
/* Table: SEC_USER_SECLV                                        */
/*==============================================================*/
create table SEC_USER_SECLV (
   SECLV_CODE           int                  not null,
   SECLV_NAME           nvarchar(100)        not null,
   SECLV_RANK           int                  null,
   IS_SEALED            char(1)              null default 'N',
   OTHERNAME            nvarchar(100)        null,
   constraint PK_SEC_USER_SECLV primary key nonclustered (SECLV_CODE)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户密级表',
   'user', @CurrentUser, 'table', 'SEC_USER_SECLV'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '密级编号',
   'user', @CurrentUser, 'table', 'SEC_USER_SECLV', 'column', 'SECLV_CODE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '密级名称',
   'user', @CurrentUser, 'table', 'SEC_USER_SECLV', 'column', 'SECLV_NAME'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '密级排序',
   'user', @CurrentUser, 'table', 'SEC_USER_SECLV', 'column', 'SECLV_RANK'
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

/*==============================================================*/
/* Table: SYS_BURNER                                            */
/*==============================================================*/
create table SYS_BURNER (
   BURNER_CODE          nvarchar(64)         not null,
   BURNER_NAME          nvarchar(1024)       not null,
   BURNER_PATH          nvarchar(1024)       not null,
   BURNER_TYPE          nvarchar(1024)       null,
   BURNER_BRAND         nvarchar(1024)       null,
   BURNER_MODEL         nvarchar(1024)       null,
   DEPT_ID              nvarchar(100)        not null,
   BURNER_LOCATION      nvarchar(1024)       null,
   CREATE_TIME          datetime             not null,
   DELETE_TIME          datetime             null,
   CONSOLE_CODE         nvarchar(64)         null,
   SECLV_CODE           int                  not null,
   BURNER_USEFOR        int                  null default 0,
   MFP_CODE             nvarchar(64)         null,
   IS_SEALED            CHAR(1)              null default 'N',
   constraint PK_SYS_BURNER primary key (BURNER_CODE)
)
go

/*==============================================================*/
/* Table: SYS_CONSOLE                                           */
/*==============================================================*/
create table SYS_CONSOLE (
   CONSOLE_CODE         nvarchar(64)         not null,
   CONSOLE_NAME         nvarchar(1024)       not null,
   HARDWARE_TYPE        nvarchar(64)         null,
   CONSOLE_TYPE         nvarchar(1024)       not null,
   SECLV_CODE           int                  not null,
   CONSOLE_LOCATION     nvarchar(1024)       null,
   CURR_VERSION         nvarchar(128)        null,
   SET_VERSION          nvarchar(128)        null,
   CONSOLE_MAC          nvarchar(64)         null,
   CONSOLE_IPADDR       nvarchar(64)         null,
   INSTALL_TIME         datetime             null,
   CONSOLE_STATUS       nvarchar(10)         null,
   UNINSTALL_TIME       datetime             null,
   IS_ONLINE            nvarchar(10)         null,
   LAST_CONNECT_TIME    datetime             null,
   IS_BARCODE_PRINT     nvarchar(10)         null,
   ALLOWSECLEVEL        nvarchar(1024)       null,
   IS_SEALED            CHAR(1)	             null default 'N',
   IS_UPDATE            CHAR(1)	             null default 'N',
   constraint PK_SYS_CONSOLE primary key (CONSOLE_CODE)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '管道符|分隔,打印PRINT，刻录BURN，流转TRANSFER，借用DEVICE，归档FILE',
   'user', @CurrentUser, 'table', 'SYS_CONSOLE', 'column', 'CONSOLE_TYPE'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '管道符 | 分隔，密级都不可高于控制台本身密级',
   'user', @CurrentUser, 'table', 'SYS_CONSOLE', 'column', 'ALLOWSECLEVEL'
go

/*==============================================================*/
/* Table: SYS_CVS                                               */
/*==============================================================*/
create table SYS_CVS (
   CVS_CODE             nvarchar(64)         not null,
   USER_IIDD            nvarchar(64)         null,
   COMPUTER_NAME        nvarchar(128)        null,
   IP_ADDRESS           nvarchar(128)        null,
   SOFT_CODE            int                  null,
   CURR_VERSION         nvarchar(128)        null,
   INSTALL_VERSION      nvarchar(128)        null,
   SET_VERSION          nvarchar(128)        null,
   INSTALL_TIME         datetime             null,
   UPDATE_TIME          datetime             null,
   OS_VERSION           nvarchar(128)        null,
   LAST_TIME            datetime             null,
   IS_ONLINE            nvarchar(10)         null,
   IS_SEALED            CHAR(1)              null default 'N',
   UNINSTALL            datetime             null,
   constraint PK_SYS_CVS primary key (CVS_CODE)
)
go

/*==============================================================*/
/* Table: SYS_EXCHANGEBOX                                       */
/*==============================================================*/
create table SYS_EXCHANGEBOX (
   BOX_CODE             nvarchar(64)         not null,
   BOX_NAME             nvarchar(128)        null,
   BOX_LOCATION         nvarchar(128)        null,
   SECLV_CODE           int                  null,
   BOX_STATUS           int                  null,
   IS_SEALED            char(1)              null default 'N',
   constraint PK_SYS_EXCHANGEBOX primary key (BOX_CODE)
)
go

/*==============================================================*/
/* Table: SYS_EXCHANGEUNIT                                      */
/*==============================================================*/
create table SYS_EXCHANGEUNIT (
   UNIT_CODE            nvarchar(64)         not null,
   BOX_CODE             nvarchar(64)         null,
   SECLV_CODE           int                  null,
   UNIT_TYPE            int                  null,
   GOOD_BARCODE         nvarchar(64)         null,
   UNIT_STATUS          nvarchar(64)         null,
   IS_SEALED            char(1)              null default 'N',
   constraint PK_SYS_EXCHANGEUNIT primary key (UNIT_CODE)
)
go

/*==============================================================*/
/* Table: SYS_GENNO                                             */
/*==============================================================*/
create table SYS_GENNO (
   GENNO_CODE           int                  not null,
   GENNO_NO             nvarchar(128)        null,
   START_DATE           int                  null,
   constraint PK_SYS_GENNO primary key (GENNO_CODE)
)
go

/*==============================================================*/
/* Table: SYS_KEYWORD                                           */
/*==============================================================*/
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
go

/*==============================================================*/
/* Table: SYS_MFP                                               */
/*==============================================================*/
create table SYS_MFP (
   MFP_CODE             nvarchar(64)         not null,
   MFP_NAME             nvarchar(1024)       not null,
   MFP_BRAND            nvarchar(1024)       null,
   MFP_MODEL            nvarchar(1024)       null,
   DEPT_ID              nvarchar(100)        not null,
   MFP_LOCATION         nvarchar(1024)       null,
   CREATE_TIME          datetime             not null,
   DELETE_TIME          datetime             null,
   CONSOLE_CODE         nvarchar(64)         null,
   SECLV_CODE           int                  not null,
   IS_SEALED            CHAR(1)              null default 'N',
   constraint PK_SYS_MFP primary key (MFP_CODE)
)
go

/*==============================================================*/
/* Table: SYS_MODULE                                            */
/*==============================================================*/
create table SYS_MODULE (
   MODULE_CODE          nvarchar(128)        not null,
   MODULE_NAME          nvarchar(1024)       not null,
   MODULE_ENABLE        nvarchar(10)         not null default 'Y',
   IS_SEALED            char(1)              null default 'N',
   constraint PK_SYS_MODULE primary key (MODULE_CODE)
)
go

/*==============================================================*/
/* Table: SYS_PLACE                                             */
/*==============================================================*/
create table SYS_PLACE (
   PLACE_CODE           nvarchar(128)        not null,
   PLACE_NAME           nvarchar(1024)       not null,
   PLACE_DESC           nvarchar(1024)       null,
   IS_SEALED            char(1)              null default 'N',
   constraint PK_SYS_PLACE primary key (PLACE_CODE)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '配置集中业务的地点',
   'user', @CurrentUser, 'table', 'SYS_PLACE'
go

/*==============================================================*/
/* Table: SYS_PRINTER                                           */
/*==============================================================*/
create table SYS_PRINTER (
   PRINTER_CODE         nvarchar(64)         not null,
   PRINTER_NAME         nvarchar(1024)       not null,
   PRINTER_PATH         nvarchar(1024)       not null,
   PRINTER_TYPE         nvarchar(1024)       not null,
   PRINTER_BRAND        nvarchar(1024)       null,
   PRINTER_MODEL        nvarchar(1024)       null,
   PRINTER_COLOR        nvarchar(64)         null,
   DEPT_ID              nvarchar(100)        not null,
   PRINTER_IPADDR       nvarchar(64)         null,
   PRINTER_LOCATION     nvarchar(1024)       null,
   CREATE_TIME          datetime             not null,
   DELETE_TIME          datetime             null,
   IS_DOUBLE            nvarchar(10)         null,
   CONSOLE_CODE         nvarchar(64)         null,
   SECLV_CODE           int                  not null,
   LAST_CONNECT_TIME    datetime             null,
   IS_SEALED            CHAR(1)              not null default 'N',
   PRINTER_RANK         int                  not null default 0,
   constraint PK_SYS_PRINTER primary key (PRINTER_CODE)
)
go

/*==============================================================*/
/* Table: EVENT_SPACECD                                           */
/*==============================================================*/
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

/*==============================================================*/
/* Table: SYS_PROJECT                                           */
/*==============================================================*/
create table SYS_PROJECT (
   PROJECT_CODE         nvarchar(64)         not null,
   PROJECT_NAME         nvarchar(64)         null,
   PROJECT_CONTENT      nvarchar(1024)       null,
   START_TIME           datetime             null,
   END_TIME             datetime             null,
   IS_SEALED            char(1)              null default 'N',
   constraint PK_SYS_PROJECT primary key (PROJECT_CODE)
)
go

/*==============================================================*/
/* Table: SYS_PROXY_APRV                                        */
/*==============================================================*/
create table SYS_PROXY_APRV (
   PROXY_USER_IIDD      nvarchar(64)         not null,
   USER_IIDD            nvarchar(64)         not null,
   IS_SEALED            char(1)              null default 'N',
   PROXY_TYPE           nvarchar(1024)       not null,
   USEFUL_LIFE_TIME     datetime             not null
)
go

/*==============================================================*/
/* Table: SYS_PROXY_OPER                                        */
/*==============================================================*/
create table SYS_PROXY_OPER (
   PROXY_USER_IIDD      nvarchar(100)        not null,
   USER_IIDD            nvarchar(100)        not null,
   IS_SEALED            char(1)              null default 'N',
   PROXY_TYPE           nvarchar(64)         not null,
   USEFUL_LIFE_TIME     datetime             not null
)
go

/*==============================================================*/
/* Table: SYS_RECYCLEBOX                                        */
/*==============================================================*/
create table SYS_RECYCLEBOX (
   BOX_CODE             nvarchar(64)         not null,
   BOX_NAME             nvarchar(128)        null,
   BOX_LOCATION         nvarchar(128)        null,
   SECLV_CODE           int                  null,
   BOX_TYPE             int                  null,
   CURRENT_NUM          int                  null,
   BOX_STATUS           int                  null,
   IS_SEALED            char(1)              null default 'N',
   constraint PK_SYS_RECYCLEBOX primary key (BOX_CODE)
)
go

/*==============================================================*/
/* Table: SYS_SECLEVEL                                          */
/*==============================================================*/
create table SYS_SECLEVEL (
   SECLV_CODE           int                  not null,
   LEAK_TIME            int                  not null,
   ARCHIVE_TIME         int                  not null,
   ALLOW_REPRINT        int                  not null,
   IS_PAPER_AUDIT       char(1)              not null default 'N'
      constraint CKC_IS_PAPER_AUDIT_SYS_SECL check (IS_PAPER_AUDIT in ('N','Y')),
   IS_CD_AUDIT          char(1)              not null default 'N'
      constraint CKC_IS_CD_AUDIT_SYS_SECL check (IS_CD_AUDIT in ('N','Y')),
   EXT_CODE1            nvarchar(100)        null,
   EXT_CODE2            nvarchar(100)        null,
   constraint PK_SYS_SECLEVEL primary key (SECLV_CODE)
)
go

/*==============================================================*/
/* Table: SYS_USAGE                                             */
/*==============================================================*/
create table SYS_USAGE (
   USAGE_CODE           nvarchar(64)         not null,
   USAGE_NAME           nvarchar(1024)       null,
   USAGE_CONTENT        nvarchar(1024)       null,
   IS_PRINTBC           char(1)              null default 'Y',
   IS_SEALED            char(1)              null default 'N',
   MODULE_CODE 			nvarchar(1024) 		 null,
   TYPE                 CHAR(1)              null default 'Y',
   constraint PK_SYS_USAGE primary key (USAGE_CODE)
)
go

/*==============================================================*/
/* Table: SYS_WATERMASK                                         */
/*==============================================================*/
create table SYS_WATERMASK (
   WATERMASK_CODE       nvarchar(64)         not null,
   WATERMASK_CONTENT    nvarchar(1024)       not null,
   WATERMASK_SECLV      nvarchar(128)        null,
   WATERMASK_USAGE      nvarchar(128)        null default '1',
   WATERMASK_CONSOLE    nvarchar(128)        null,
   WATERMASK_PROJECT    nvarchar(128)        null,
   WATERMASK_TYPE       int                  null default 0,
   WATERMASK_FORMAT     int                  null,
   WATERMASK_CX         int                  null,
   WATERMASK_CY         int                  null,
   WATERMASK_SPACEBETWEEN int                  null,
   WATERMASK_ROTATE     int                  null,
   WATERMASK_OPACITY    int                  null,
   FONT_COLOR           int                  null,
   FONT_SIZE            int                  null,
   IS_SEALED            CHAR(1)              null default 'N',
   constraint PK_SYS_WATERMASK primary key (WATERMASK_CODE)
)
go

/*==============================================================*/
/* Table: USER_SECURITY                                         */
/*==============================================================*/
create table USER_SECURITY (
   SECURITY_CODE        nvarchar(128)        not null,
   SECURITY_NAME        nvarchar(128)        null,
   SECURITY_DESC        nvarchar(1024)       null,
   PRINT_VALUE          nvarchar(1024)       null,
   COPY_VALUE           nvarchar(1024)       null,
   IMPORT_VALUE         nvarchar(1024)       null,
   BURN_VALUE           nvarchar(1024)       null,
   COPY_BURN_VALUE      nvarchar(1024)       null,
   DEVICE_VALUE         nvarchar(1024)       null,
   DEFAULT_VALUE        nvarchar(1024)       null,
   IS_SEALED            char(1)              null default 'N',
   constraint PK_USER_SECURITY primary key (SECURITY_CODE)
)
go

alter table DEPT_OPEN_SCOPE
   add constraint FK_DEPT_OPEN_SCOPE_BR_DEPT_ID foreign key (DEPT_ID)
      references SEC_DEPT (DEPT_ID)
go

alter table EVENT_BORROW
   add constraint FK_EVENT_BORROW_DEPT_ID foreign key (DEPT_ID)
      references SEC_DEPT (DEPT_ID)
go

alter table EVENT_BORROW
   add constraint FK_EVENT_BORROW_JOB_CODE foreign key (JOB_CODE)
      references JOB_PROCESS (JOB_CODE)
go

alter table EVENT_BORROW
   add constraint FK_EVENT_BORROW_USER_IIDD foreign key (USER_IIDD)
      references SEC_USER (USER_IIDD)
go

alter table EVENT_BURN
   add constraint FK_EVENT_BURN_DEPT_ID foreign key (DEPT_ID)
      references SEC_DEPT (DEPT_ID)
go

alter table EVENT_BURN
   add constraint FK_EVENT_BURN_JOB_CODE foreign key (JOB_CODE)
      references JOB_PROCESS (JOB_CODE)
go

alter table EVENT_BURN
   add constraint FK_EVENT_BURN_SECLV_CODE foreign key (SECLV_CODE)
      references SEC_USER_SECLV (SECLV_CODE)
go

alter table EVENT_BURN
   add constraint FK_EVENT_BURN_USER_IIDD foreign key (USER_IIDD)
      references SEC_USER (USER_IIDD)
go

alter table EVENT_COPY
   add constraint FK_EVENT_COPY_DEPT_ID foreign key (DEPT_ID)
      references SEC_DEPT (DEPT_ID)
go

alter table EVENT_COPY
   add constraint FK_EVENT_COPY_JOB_CODE foreign key (JOB_CODE)
      references JOB_PROCESS (JOB_CODE)
go

alter table EVENT_COPY
   add constraint FK_EVENT_COPY_SECLV_CODE foreign key (SECLV_CODE)
      references SEC_USER_SECLV (SECLV_CODE)
go

alter table EVENT_COPY
   add constraint FK_EVENT_COPY_USER_IIDD foreign key (USER_IIDD)
      references SEC_USER (USER_IIDD)
go

alter table EVENT_DEVICE
   add constraint FK_EVENT_DEVICE_DEPT_ID foreign key (DEPT_ID)
      references SEC_DEPT (DEPT_ID)
go

alter table EVENT_DEVICE
   add constraint FK_EVENT_DEVICE_JOB_CODE foreign key (JOB_CODE)
      references JOB_PROCESS (JOB_CODE)
go

alter table EVENT_DEVICE
   add constraint FK_EVENT_DEVICE_SECLV_CODE foreign key (SECLV_CODE)
      references SEC_USER_SECLV (SECLV_CODE)
go

alter table EVENT_DEVICE
   add constraint FK_EVENT_DEVICE_USER_IIDD foreign key (USER_IIDD)
      references SEC_USER (USER_IIDD)
go

alter table EVENT_IMPORT
   add constraint FK_EVENT_IMPORT_DEPT_ID foreign key (DEPT_ID)
      references SEC_DEPT (DEPT_ID)
go

alter table EVENT_IMPORT
   add constraint FK_EVENT_IMPORT_JOB_CODE foreign key (JOB_CODE)
      references JOB_PROCESS (JOB_CODE)
go

alter table EVENT_IMPORT
   add constraint FK_EVENT_IMPORT_SECLV_CODE foreign key (SECLV_CODE)
      references SEC_USER_SECLV (SECLV_CODE)
go

alter table EVENT_IMPORT
   add constraint FK_EVENT_IMPORT_USER_IIDD foreign key (USER_IIDD)
      references SEC_USER (USER_IIDD)
go

alter table EVENT_PRINT
   add constraint FK_EVENT_PRINT_DEPT_ID foreign key (DEPT_ID)
      references SEC_DEPT (DEPT_ID)
go

alter table EVENT_PRINT
   add constraint FK_EVENT_PRINT_JOB_CODE foreign key (JOB_CODE)
      references JOB_PROCESS (JOB_CODE)
go

alter table EVENT_PRINT
   add constraint FK_EVENT_PRINT_SECLV_CODE foreign key (SECLV_CODE)
      references SEC_USER_SECLV (SECLV_CODE)
go

alter table EVENT_PRINT
   add constraint FK_EVENT_PRINT_USER_IIDD foreign key (USER_IIDD)
      references SEC_USER (USER_IIDD)
go

alter table EVENT_TRANSFER
   add constraint FK_EVENT_TRANSFER_DEPT_ID foreign key (DEPT_ID)
      references SEC_DEPT (DEPT_ID)
go

alter table EVENT_TRANSFER
   add constraint FK_EVENT_TRANSFER_SECLV_CODE foreign key (SECLV_CODE)
      references SEC_USER_SECLV (SECLV_CODE)
go

alter table EVENT_TRANSFER
   add constraint FK_EVENT_TRANSFER_USER_IIDD foreign key (USER_IIDD)
      references SEC_USER (USER_IIDD)
go

alter table SEC_DEPT_POST
   add constraint FK_SEC_DEPT_POST_DEPT_CODE foreign key (DEPT_ID)
      references SEC_DEPT (DEPT_ID)
go

alter table SEC_DEPT_POST
   add constraint FK_SEC_DEPT_POST_POST_ID foreign key (POST_ID)
      references SEC_USER_POST (POST_ID)
go

alter table SEC_DEPT_SUBSYS
   add constraint FK_SEC_DEPT_SUBSYS_DEPT_CODE foreign key (DEPT_ID)
      references SEC_DEPT (DEPT_ID)
go

alter table SEC_DEPT_SUBSYS
   add constraint FK_SEC_DEPT_SUBSYS_SUBSYS_CODE foreign key (SUBSYS_CODE)
      references SEC_SUBSYS (SUBSYS_CODE)
go

alter table SEC_OPER
   add constraint FK_SEC_OPER_SUBSYS_CODE foreign key (SUBSYS_CODE)
      references SEC_SUBSYS (SUBSYS_CODE)
go

alter table SEC_ROLE
   add constraint FK_SEC_ROLE_SUBSYS_CODE foreign key (SUBSYS_CODE)
      references SEC_SUBSYS (SUBSYS_CODE)
go

alter table SEC_ROLE_OPER
   add constraint FK_SEC_ROLE_OPER_OPER_ID foreign key (OPER_CODE)
      references SEC_OPER (OPER_CODE)
go

alter table SEC_ROLE_OPER
   add constraint FK_SEC_ROLE_OPER_ROLE_ID foreign key (ROLE_ID)
      references SEC_ROLE (ROLE_ID)
go

alter table SEC_ROLE_USER
   add constraint FK_SEC_ROLE_USER_ROLE_ID foreign key (ROLE_ID)
      references SEC_ROLE (ROLE_ID)
go

alter table SEC_ROLE_USER
   add constraint FK_SEC_ROLE_USER_USER_ID foreign key (USER_IIDD)
      references SEC_USER (USER_IIDD)
go

alter table SEC_USER
   add constraint FK_SEC_USER_DEPT_ID foreign key (DEPT_ID)
      references SEC_DEPT (DEPT_ID)
go

alter table SEC_USER
   add constraint FK_SEC_USER_REAL_USER_ID foreign key (REAL_USER_ID)
      references REAL_USER (REAL_USER_ID)
go

alter table SYS_BURNER
   add constraint FK_SYS_BURNER_CONSOLE_CODE foreign key (CONSOLE_CODE)
      references SYS_CONSOLE (CONSOLE_CODE)
go

alter table SYS_BURNER
   add constraint FK_SYS_BURNER_DEPT_ID foreign key (DEPT_ID)
      references SEC_DEPT (DEPT_ID)
go

alter table SYS_BURNER
   add constraint FK_SYS_BURNER_SECLV_CODE foreign key (SECLV_CODE)
      references SEC_USER_SECLV (SECLV_CODE)
go

alter table SYS_CONSOLE
   add constraint FK_SYS_CONS_REFERENCE_SEC_USER foreign key (SECLV_CODE)
      references SEC_USER_SECLV (SECLV_CODE)
go

alter table SYS_EXCHANGEBOX
   add constraint FK_SYS_EXCHANGEBOX_SECLV_CODE foreign key (SECLV_CODE)
      references SEC_USER_SECLV (SECLV_CODE)
go

alter table SYS_EXCHANGEUNIT
   add constraint FK_FK_SYS_EXCHANGEUNIT_SECLV_CODE foreign key (SECLV_CODE)
      references SEC_USER_SECLV (SECLV_CODE)
go

alter table SYS_EXCHANGEUNIT
   add constraint FK_SYS_EXCHANGEUNIT_BOX_CODE foreign key (BOX_CODE)
      references SYS_EXCHANGEBOX (BOX_CODE)
go

alter table SYS_MFP
   add constraint FK_SYS_CONSOLE_SECLV_CODE foreign key (CONSOLE_CODE)
      references SYS_CONSOLE (CONSOLE_CODE)
go

alter table SYS_MFP
   add constraint FK_SYS_MFP_DEPT_ID foreign key (DEPT_ID)
      references SEC_DEPT (DEPT_ID)
go

alter table SYS_MFP
   add constraint FK_SYS_MFP_SECLV_CODE foreign key (SECLV_CODE)
      references SEC_USER_SECLV (SECLV_CODE)
go

alter table SYS_PRINTER
   add constraint FK_SYS_PRINTER_CONSOLE_CODE foreign key (CONSOLE_CODE)
      references SYS_CONSOLE (CONSOLE_CODE)
go

alter table SYS_PRINTER
   add constraint FK_SYS_PRINTER_DEPT_ID foreign key (DEPT_ID)
      references SEC_DEPT (DEPT_ID)
go

alter table SYS_PRINTER
   add constraint FK_SYS_PRINTER_SECLV_CODE foreign key (SECLV_CODE)
      references SEC_USER_SECLV (SECLV_CODE)
go

alter table SYS_PROXY_OPER
   add constraint FK_SYS_PROXY_OPER_PROXY_USER_IIDD foreign key (USER_IIDD)
      references SEC_USER (USER_IIDD)
go

alter table SYS_RECYCLEBOX
   add constraint FK_SYS_RECYCLEBOX_SECLV_CODE foreign key (SECLV_CODE)
      references SEC_USER_SECLV (SECLV_CODE)
go

alter table SYS_SECLEVEL
   add constraint FK_SYS_SECLEVEL_SECLV_CODE foreign key (SECLV_CODE)
      references SEC_USER_SECLV (SECLV_CODE)
go

--存储过程--

--CREATE TABLE [dbo].[BARCODE_SEED](
	--[SEED_VALUE] [numeric](18, 0) NULL
--) ON [PRIMARY]

--go

--INSERT INTO BARCODE_SEED(SEED_VALUE) VALUES(1)

--go
CREATE TABLE [dbo].[BARCODE_SEED](
	[SEED_VALUE] [bigint] NULL,
	[DEPT_ID] [nvarchar](100) NULL,
	[SECLV_CODE] [int]  NULL,
	[SEED_YEAR] [int] NULL
) ON [PRIMARY]

go

INSERT INTO BARCODE_SEED(SEED_VALUE) VALUES(1)

go
-- =============================================
-- Author:		<Author,,wx>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[CREATEBARCODE]
@outValue bigint output
AS
BEGIN
	BEGIN TRAN
-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	UPDATE BARCODE_SEED SET SEED_VALUE = SEED_VALUE + 1
    -- Insert statements for procedure here
	SELECT @outValue=SEED_VALUE-1 FROM BARCODE_SEED
	
	COMMIT
	
	IF(@outValue=9999999999999)
		BEGIN
			BEGIN TRAN
				UPDATE BARCODE_SEED SET SEED_VALUE = 1
				UPDATE BARCODE_SEED SET SEED_VALUE = SEED_VALUE + 1
				SELECT @outValue=SEED_VALUE-1 FROM BARCODE_SEED
				COMMIT	
		END

	RETURN @outValue
END

go

CREATE PROCEDURE [dbo].[proc_getMsgs] (
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

/*==============================================================*/
/* Table: EVENT_CHANGE                                        */
/*==============================================================*/
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

/*==============================================================*/
/* Table: MED_TYPE                                             */
/*==============================================================*/
create table MED_TYPE (
   ID           int		             not null,
   TYPENAME     nvarchar(1024)       not null,
   CONTENT      nvarchar(1024)       null,
   constraint PK_MED_TYPE  primary key (ID)
)
go

/*==============================================================*/
/* Table: EVENT_BURN                                             */
/*==============================================================*/
    alter table EVENT_BURN add model_num nvarchar(100)
go
	alter table EVENT_BURN add post_num nvarchar(100)
go	
	alter table EVENT_BURN add record_num nvarchar(100)
go	
	alter table EVENT_BURN add disk_name nvarchar(100)
go
	alter table EVENT_BURN add base_mark nvarchar(100)
go
	alter table EVENT_BURN add step nvarchar(100)
go
	alter table EVENT_BURN add purpose nvarchar(100)
go

/*==============================================================*/
/* Table: EVENT_SENDDESTROY                                   */
/*==============================================================*/
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
   SENDDESTROY_STATUS   int                  null,
   JOB_CODE             nvarchar(100)        null,
   HIS_JOB_CODE         nvarchar(1024)       null,
   constraint PK_EVENT_SENDDESTROY primary key (ID)
)
go

/*==============================================================*/
/* Table: PAPER_CONVERSION_RATE                                   */
/*==============================================================*/
create table PAPER_CONVERSION_RATE (
   TYPE_NAME            nvarchar(100)         not null,
   CONVERSION_RATE      float                 null,
   constraint PK_PAPER_CONVERSION_RATE primary key nonclustered (TYPE_NAME)
)
go

-----替换页触发器
Create TRIGGER [dbo].[test_PIDBarcode]
   ON [dbo].[EVENT_PRINT]
   for UPDATE
AS 
BEGIN

    DECLARE @PRINT_STATUS int
    DECLARE @EVENT_CODE nvarchar(64) 
    DECLARE @PID_BARCODE nvarchar(100)
    DECLARE @PID_PAGENUM nvarchar(100)
    DECLARE @TEMP_PAGENUM nvarchar(100)      

    if Update(PRINT_STATUS)
    Begin
    set @PRINT_STATUS =(select PRINT_STATUS from inserted)

    if (@PRINT_STATUS > 0)
    begin

    set @EVENT_CODE = (select EVENT_CODE from inserted)
    set @PID_BARCODE = (select PID_BARCODE from inserted)
    set @PID_PAGENUM = (select PID_PAGENUM from inserted)

    set @TEMP_PAGENUM = (select PID_PAGENUM from entity_paper where PAPER_BARCODE = @PID_BARCODE)
    if(LEN(@TEMP_PAGENUM)>0)
        begin
        update entity_paper set PID_PAGENUM = @TEMP_PAGENUM + ','+ @PID_PAGENUM where PAPER_BARCODE = @PID_BARCODE
        update entity_paper set retrieve_replace='' where PAPER_BARCODE = @PID_BARCODE
        end
    else
        begin
        update entity_paper set PID_PAGENUM = @PID_PAGENUM where PAPER_BARCODE = @PID_BARCODE
        end

    update entity_paper set PID_BARCODE = @PID_BARCODE ,PID_PAGENUM = @PID_PAGENUM where EVENT_CODE = @EVENT_CODE

END
END
end
go
/*==============================================================*/
/* Table: EVENT_MODIFY                                         */
/*==============================================================*/
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
/*==============================================================*/
/* Table: PUBLICITY_BURN                                        */
/*==============================================================*/
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
   constraint PK_PUBLICITY_BURN primary key (ID)
)
go


/*==============================================================*/
/* Table: EVENT_CARRYOUT     外带表                              */
/*==============================================================*/
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
   SUMM 				nvarchar(1024) 		null,
   FINISH_TIME 			datetime 			null,
   CARRYOUT_STATUS 		int 				null,
   JOB_CODE 			nvarchar(100) 		null,
   EXT_COLUMN 			varchar(100)		null,
   constraint PK_EVENT_CARRYOUT primary key (ID)
)
go

alter table EVENT_CARRYOUT  
    add constraint FK_EVENT_CARRYOUT_JOB_CODE 
    foreign key (JOB_CODE) 
    references JOB_PROCESS(JOB_CODE)
go
alter table EVENT_CARRYOUT  
    add constraint FK_EVENT_CARRYOUT_DEPT_ID 
    foreign key (DEPT_ID) 
    references SEC_DEPT(DEPT_ID)
go
alter table EVENT_CARRYOUT  
    add constraint FK_EVENT_CARRYOUT_USER_IIDD 
    foreign key (USER_IIDD) 
    references SEC_USER(USER_IIDD)
go
alter table EVENT_CARRYOUT  
    add constraint FK_EVENT_CARRYOUT_SECLV_CODE 
    foreign key (SECLV_CODE) 
    references SEC_USER_SECLV(SECLV_CODE)
go

/*==============================================================*/
/* Table: UNLOCK_EVENT_LOG    解锁作业日志表                                     */
/*==============================================================*/
create table UNLOCK_EVENT_LOG (
ID	                   int                identity(1,1),
USER_IIDD	           nvarchar(100)		null,
EVENT_CODE	           nvarchar(64)         null,
UNLOCK_TIME	           datetime             null,
CONSOLE_CODE		   nvarchar(64)         null,
EVENT_TYPE	           nvarchar(64)         null,
EXT_CODE	           nvarchar(255)        null,
EXT_INT	               int                  null,
EXT_PRINT	           nvarchar(255)        null,
constraint PK_UNLOCK_EVENT_LOG primary key (ID)
)
go

/*==============================================================*/
/* Table: EVENT_SPECIALPRINT                                        */
/*==============================================================*/
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

/*==============================================================*/
/* Table: EVENT_TEMP  其他申请任务表                                 */
/*==============================================================*/
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

/*==============================================================*/
/* Table: SYS_FIXACCORDING  定密依据库表                                                                            */
/*==============================================================*/
create table SYS_FIXACCORDING (
   ID                   int                  identity(1,1),
   TYPE   		        nvarchar(64)         null,
   CONTENT      	    nvarchar(1024)       null,
   IS_SEALED            char(1)              null default 'N',
   EXT_CODE             nvarchar(100)        null,
   constraint PK_SYS_FIXACCORDING primary key (ID)
)
go

update sec_oper set web_url = 'client/clienttask.action' where oper_code = '01010101';
if not exists (select 1 from syscolumns where id=object_id('client_msg') and name='title ')
	alter table CLIENT_MSG add title nvarchar(1024) null;
go
if not exists (select 1 from syscolumns where id=object_id('client_msg') and name='url ')
	alter table CLIENT_MSG add url nvarchar(1024) null;
go

if exists (select 1 from dbo.sysobjects where id=object_id (N'[dbo].[T_Addurl]') and (objectproperty (id ,N'IsTrigger' ) = 1))
  drop trigger T_Addurl;
go

set ANSI_NULLS ON
set QUOTED_IDENTIFIER ON
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
---				if @oper_type = 'MSG_INPUT'
---				begin
---					set @title = '电子文件导入';
---                                        if @msg_type = 1
---                                       begin
---					  set @link_url = 'input/approveinputjob.action?job_code='+@job_code;
---                                       end else begin
---                                         set @link_url = 'input/viewinputlistjob1.action';
---                                       end;
---				end;	
				if @oper_type = 'MERGE_ENTITY'
				 begin
				    set @title = '台账合并';
		                    if @msg_type = 1 
		                    begin
				    set @link_url = 'basic/handlejob.action?type=PAPER&job_code='+@job_code;
		                    end else begin
		                        set @link_url = 'ledger/mergepaperapply.action';
		                    end
				 end;		
     update  A set A.title = @title, A.url = @link_url
      from [dbo].[CLIENT_MSG] A,inserted i
      where A.id = i.id;
go

go
/*==============================================================*/
/* Table: BARCODE_COMPARE                                       */
/*==============================================================*/
create table BARCODE_COMPARE (
   ID                  int		             identity(1,1),
   BARCODE	           nvarchar(64)		     null,
   PDFCODE	           nvarchar(1024)        null
   constraint PK_BARCODE_COMPARE  primary key (ID)
)
go

/*==============================================================*/
/* Table: WS_BARCODEINFO                                       */
/*==============================================================*/
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
/*==============================================================*/
/* Table: WS_PRINT_LINK                                       */
/*==============================================================*/
create table WS_PRINT_LINK (
   ID                  int                   identity(1,1),
   USER_IIDD           nvarchar(64)          null,
   LINK                nvarchar(2048)        null,
   constraint PK_WS_PRINT_LINK  primary key (ID)
)
go

/*==============================================================*/
/* Table: KEYWORD_PRINT                                       */
/*==============================================================*/
create table KEYWORD_PRINT (
   ID                   int                  identity(1,1),
   EVENT_CODE           nvarchar(64)         null,
   ST_FILENAME          nvarchar(1024)       null,
   FILE_NAME            nvarchar(1024)       null,
   USER_IIDD            nvarchar(64)         null,
   CALL_RESULT          text                 null,
   TID                  nvarchar(512)        null,
   POLICY               nvarchar(512)        null,
   FILELIST             text                 null,
   SCANTYPE             nvarchar(512)        null,
   SUMM                 nvarchar(1024)       null,
   constraint PK_KEYWORD_PRINT  primary key (ID)
)
go

---中物5所专用
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

---中物11所专用(添加电子文件导入模块)
---if exists (select 1
---            from  sysobjects
---           where  id = object_id('EVENT_INPUT')
---            and   type = 'U')
---   drop table EVENT_INPUT
---go

/*==============================================================*/
/* Table: EVENT_INPUT       电子文件导入 表                                                                     */
/*==============================================================*/
---create table EVENT_INPUT (
---    ID	            int                identity(1,1),
---    EVENT_CODE      nvarchar(64)          null,
---    JOB_CODE        nvarchar(100)         null,
---    USER_IIDD       nvarchar(100)         null,
---    DEPT_ID         nvarchar(100)         null,
---    SECLV_CODE      int                   null,
---    FILE_LIST       nvarchar(100)         null,
---    FILE_SECLEVEL   nvarchar(100)         null,
---    MESSAGE_USAGE   nvarchar(1024)        null,
---    PERSONAL        nvarchar(1024)        null,
---    ADDRESS         nvarchar(1024)        null,
---    FILE_NUM        int                   null,
---    SUMM            nvarchar(1024)        null,
---    MED_TYPE        nvarchar(100)         null,
---    APPLY_TIME      datetime              null,
---    OPERATE_TIME    datetime              null,
---    CD_NUM          nvarchar(64)          null,
---    INTERNET_NUM    nvarchar(64)          null,
---    INPUT_STATE     int                   null,
---    INPUT_USER_IIDD nvarchar(100)         null,
---    INPUT_USER_NAME nvarchar(100)         null,
---    PROJECT_CODE   nvarchar(64)          null,
---    USAGE_CODE     nvarchar(64)          null,
---   constraint PK_EVENT_INPUT  primary key (ID)
---)
---go

---添加定密依据与部门绑定关联REF_FIXACCORDING_DEPT表
if not exists (select 1 from  sysobjects where  id = object_id('REF_FIXACCORDING_DEPT') and type = 'U')
create table REF_FIXACCORDING_DEPT (
   DEPT_ID     nvarchar(100)         null,
   FIX_ID      int       			 null,
)
go