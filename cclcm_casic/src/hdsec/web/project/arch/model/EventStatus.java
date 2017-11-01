package hdsec.web.project.arch.model;

public class EventStatus {

	/**
	 * 未借入
	 */
	public static final int WAITING = 0;
	/**
	 * 已借入
	 */
	public static final int LENT = 1;
	/**
	 * 已归还
	 */
	public static final int RETURN = 2;
	/**
	 * 未续借
	 */
	public static final int UNRENEW = 3;
	/**
	 * 已续借
	 */
	public static final int RENEW = 4;
}
