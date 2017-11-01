package basic.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import basic.model.EntityPaper;

public interface Mapper {
	
	//SysBarcode getProcBarcodeByCode(int barcode_code);
	
	String getCreateBarcode(Map<String, Object> map);
	
	/**
	 * test
	 */
	List<EntityPaper> getAllPaperLedgerList(Map<String, Object> map);
	
	/**
	 * test
	 */
	List<EntityPaper> getAllPaperLedgerList(Map<String, Object> map, RowBounds rbs);
	
	/**
	 * test
	 */
	int getAllPaperLedgerSize(Map<String, Object> map);
	
	//SysBarcode getBarcodeByCode(String string);
	
}
