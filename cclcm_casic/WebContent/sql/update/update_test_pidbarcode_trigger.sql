ALTER TRIGGER [dbo].[test_PIDBarcode]
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


