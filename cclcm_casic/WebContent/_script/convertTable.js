//�������ΪExcel�ļ�
function AutomateExcel(objID){
            //objIDΪ���ID
			//��Ҫ���������ȫ��������������ActiveX
			// Start Excel and get Application object.
            var oXL=null;
          try{
             oXL = new ActiveXObject("Excel.Application");
              }catch(e){
              alert(e.message);
              return ;
          }
            if (oXL == null){
				alert("����Excel�ļ�ʧ�ܣ����������ļ������û����ȷ��װMicrosoft Office Excel�����");
				return;
			}
			// Get a new workbook.
			var oWB = oXL.Workbooks.Add();
			var oSheet = oWB.ActiveSheet;
			//alert(document.getElementById(objID));
			window.clipboardData.setData("Text", document.getElementById(objID).outerHTML);
			oSheet.Paste();
			oXL.Visible = true;
			oXL.UserControl = true;
		}