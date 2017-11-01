package hdsec.web.project.exception;

/**
 * 导入用户时，必填选项如果为空，则抛出此异常
 * 2014-5-1 下午4:13:49
 * 
 * @author renmingfei
 */
public class CellBlankException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public CellBlankException() {
		super();
	}
	
	public CellBlankException(String message) {
		super(message);
	}
	
}
