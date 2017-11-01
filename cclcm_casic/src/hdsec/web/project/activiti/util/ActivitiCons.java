package hdsec.web.project.activiti.util;

public class ActivitiCons {
	/**
	 * 待审批
	 */
	public static final String APPLY_APPROVED_DEFAULT = "none";
	/**
	 * 审批中
	 */
	public static final String APPLY_APPROVED_UNDER = "under";
	/**
	 * 审批通过
	 */
	public static final String APPLY_APPROVED_PASS = "true";
	/**
	 * 审批驳回
	 */
	public static final String APPLY_APPROVED_REJECT = "false";
	/**
	 * 确认驳回，流程结束
	 */
	public static final String APPLY_APPROVED_END = "done";
	/**
	 * 分隔审批和审批意见的分隔符
	 */
	public static final String APPROVAL_SEPARATOR = "@#";
	/**
	 * 审批结果：同意
	 */
	public static final String APPROVE_RESULT_PASS = "Y";
	/**
	 * 审批结果：不同意
	 */
	public static final String APPROVE_RESULT_REJECT = "N";
}
