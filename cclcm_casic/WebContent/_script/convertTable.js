//导出表格为Excel文件
function AutomateExcel(objID){
            //objID为表格ID
			//需要在浏览器安全级别设置中启用ActiveX
			// Start Excel and get Application object.
            var oXL=null;
          try{
             oXL = new ActiveXObject("Excel.Application");
              }catch(e){
              alert(e.message);
              return ;
          }
            if (oXL == null){
				alert("创建Excel文件失败，可能是您的计算机上没有正确安装Microsoft Office Excel软件！");
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