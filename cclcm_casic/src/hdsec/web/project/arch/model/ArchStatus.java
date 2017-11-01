package hdsec.web.project.arch.model;

public class ArchStatus {

	/**
	 * 在架空闲，可申请借用
	 */
	public static final int AVAILABLE = 0;
	/**
	 * 已有人申请借用，但尚未通过审批，档案被锁定
	 */
	public static final int LOCKED = 1;
	/**
	 * 借用申请已经通过，待取走
	 */
	public static final int WAITING = 2;
	/**
	 * 档案已借出
	 */
	public static final int LENT = 3;
	/**
	 * 档案删除
	 */
	public static final int DELETED = 4;
}
