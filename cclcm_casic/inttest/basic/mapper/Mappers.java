package basic.mapper;

import hdsec.web.project.basic.model.TransferMessage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import basic.model.EntityPaper;

public interface Mappers {
	
	//SysBarcode getProcBarcodeByCode(int barcode_code);
	
	String getCreateBarcode(Map<String, Object> map);
	
	List<EntityPaper> getAllPaperLedgerList(Map<String, Object> map);
	
	List<EntityPaper> getAllPaperLedgerList(Map<String, Object> map, RowBounds rbs);
	
	int getAllPaperLedgerSize(Map<String, Object> map);

	List<TransferMessage> getTransferMessage();
	
	//SysBarcode getBarcodeByCode(String string);
	
}
