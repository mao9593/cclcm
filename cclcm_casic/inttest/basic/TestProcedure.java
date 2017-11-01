package basic;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import basic.mapper.Mappers;
import basic.model.EntityPaper;

/**
 * @author yy
 *
 */
public class TestProcedure {
	
	public Mappers mappers;
	
	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("./applicationContext.xml");
		TestProcedure test = (TestProcedure) appContext.getBean("testProcedure");
		//test.getBarcodeByCode();
		long start_time = new Date().getTime();
		List<EntityPaper> papers = test.getPaperList();
		long end_time = new Date().getTime();
		System.out.println("total search time : " + (end_time - start_time));
		//		for (EntityPaper paper : papers) {
		//			System.out.println(paper.getEvent_code());
		//		}
	}
	
	List<EntityPaper> getPaperList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", "gaoxm");
		map.put("seclv_code", 2);
		map.put("paper_state", 9);
		RowBounds rbs = new RowBounds(1, 15);
		long start_time = new Date().getTime();
		int totalSize = mappers.getAllPaperLedgerSize(map);
		long end_time = new Date().getTime();
		System.out.println("totalSize: " + totalSize + "    and search total size time:" + (end_time - start_time));
		//return mapper.getAllPaperLedgerList(map, rbs);
		return mappers.getAllPaperLedgerList(map, rbs);
		
	}
	
	void getBarcodeByCode() {
		//		String name = mapper.getBarcodeByCode("3").getBarcode_name();
		//		System.out.println(name);
		//		SysBarcode ct = mapper.getProcBarcodeByCode(3);
		//		System.out.println(ct.getBarcode_name());
		Map<String, Object> map = new HashMap<String, Object>();
		mappers.getCreateBarcode(map);
		System.out.println(map.get("outValue"));
	}
	

	public Mappers getMappers() {
		return mappers;
	}

	public void setMappers(Mappers mappers) {
		this.mappers = mappers;
	}
	
}
