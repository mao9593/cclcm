---������ӡ�ļ��ؼ��ּ��������
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

---������ӡ�ļ��Ĺؼ��ֽ��json���ݽ�����
create table RISKLIST_PRINT (
   ID                   int                  identity(1,1),
   TID                  nvarchar(512)        null,
   RISKCLASS            nvarchar(512)        null,
   HITCOUNT				nvarchar(64)		 null,
   LEVELNAME            nvarchar(512)        null,
   SENSITVECONTENTS     nvarchar(MAX)        null,
   FILENAME             nvarchar(1024)       null,
   FILETYPE             nvarchar(512)        null,
   SUMM                 nvarchar(1024)       null,
   constraint PK_RISKLIST_PRINT primary key (ID)
)
go

---������¼�ļ��Ĺؼ��ֽ��json���ݽ�����
create table RISKLIST_BURN (
   ID                   int                  identity(1,1),
   TID                  nvarchar(512)        null,
   RISKCLASS            nvarchar(512)        null,
   HITCOUNT				nvarchar(64)		 null,
   LEVELNAME            nvarchar(512)        null,
   SENSITVECONTENTS     nvarchar(MAX)        null,
   FILENAME             nvarchar(1024)       null,
   FILETYPE             nvarchar(512)        null,
   SUMM                 nvarchar(1024)       null,
   constraint PK_RISKLIST_BURN primary key (ID)
)
go


---������¼�ļ��ؼ��ּ��������

create table KEYWORD_BURN (
   ID                   int                  identity(1,1),
   EVENT_CODE           nvarchar(64)         null,
   FILE_NAMEMD5         nvarchar(1024)       null,
   FILE_NAME            nvarchar(1024)       null,
   USER_IIDD            nvarchar(64)         null,
   CALL_RESULT          text                 null,
   TID                  nvarchar(512)        null,
   POLICY               nvarchar(512)        null,
   FILELIST             text                 null,
   SCANTYPE             nvarchar(512)        null,
   SUMM                 nvarchar(1024)       null,
   constraint PK_KEYWORD_BURN  primary key (ID)
)
go