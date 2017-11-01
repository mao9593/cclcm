package hdsec.web.project.exception;

/**
 * 导入用户时，如果输入数据有误，导致无法插入用户的，抛此异常
 * 2014-5-1 下午4:13:49
 * 
 * @author renmingfei
 */
public class CellValueWrongException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public CellValueWrongException() {
		super();
	}
	
	public CellValueWrongException(String message) {
		super(message);
	}
	
}
