package hdsec.web.project.secmanage.service;

import hdsec.web.project.secmanage.model.ExchangeMaterialEvent;
import hdsec.web.project.secmanage.model.ExhibitionEvent;
import hdsec.web.project.secmanage.model.FileOutMakeEvent;
import hdsec.web.project.secmanage.model.InternetEmailEvent;
import hdsec.web.project.secmanage.model.PaperPatentEvent;
import hdsec.web.project.secmanage.model.PunishRectifyEvent;
import hdsec.web.project.secmanage.model.ResearchFieldInEvent;
import hdsec.web.project.secmanage.model.SecCheckEvent;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SecManageService {

	/**
	 * 添加进入重要科研场地申请作业
	 * 
	 * @param event
	 * @param next_approver
	 * @throws Exception
	 */
	void addResearchFieldInEvent(ResearchFieldInEvent event, String next_approver) throws Exception;

	/**
	 * 查询进入重要科研场地申请作业列表
	 * 
	 * @param map
	 * @return
	 */
	List<ResearchFieldInEvent> getResearchFieldInEventList(Map<String, Object> map);

	/**
	 * 根据作业流水号查询进入重要科研场地申请详情
	 * 
	 * @param event_code
	 * @return
	 */
	ResearchFieldInEvent getResearchFieldInEventByEventCode(String event_code);

	/**
	 * 通过作业流水号查询任务流水号
	 * 
	 * @param event_code
	 * @return
	 */
	String getJobCodeByEventCode(String event_code);

	/**
	 * 通过作业流水号删除进入重要科研场地作业
	 * 
	 * @param event_code
	 */
	void delDelResearchFieldInEventByEventCode(String event_code);

	/**
	 * 根据任务作业号查询进入重要科研场地作业信息
	 * 
	 * @param job_code
	 * @return
	 */
	List<ResearchFieldInEvent> getResearchFieldInEventListByJobCode(String job_code);

	/** 添加外网电子邮件申请作业 **/
	void addInternetEmailEvent(InternetEmailEvent event, String next_approver) throws Exception;

	/** 查询外网电子邮件任务列表 **/
	List<InternetEmailEvent> getInternetEmailEventList(Map<String, Object> map);

	/** 根据event_code删除外网电子邮件作业 **/
	void delDelInternetEmailEventByEventCode(String event_code);

	/** 根据event_code查询外网电子邮件作业的job_code **/
	String getEmailJobCodeByEventCode(String event_code);

	/** 根据event_code查询外网电子邮件作业列表 **/
	InternetEmailEvent getInternetEmailEventByEventCode(String event_code);

	/** 根据job_code查询外网电子邮件作业列表 **/
	List<InternetEmailEvent> getInternetEmailListByJobCode(String job_code);

	/** 添加部门专项保密检查申请作业 **/
	void addSecCheckEvent(SecCheckEvent event, String next_approver) throws Exception;

	/** 查询部门专项保密检查任务列表 **/
	List<SecCheckEvent> getSecCheckEventList(Map<String, Object> map);

	/** 根据event_code删除部门专项保密检查作业 **/
	void delDelSecCheckEventByEventCode(String event_code);

	/** 根据event_code查询部门专项保密检查作业的job_code **/
	String getSecCheckJobCodeByEventCode(String event_code);

	/** 根据event_code查询部门专项保密检查作业列表 **/
	SecCheckEvent getSecCheckEventByEventCode(String event_code);

	/** 根据job_code查询部门专项保密检查作业列表 **/
	List<SecCheckEvent> getSecCheckListByJobCode(String job_code);

	/** 添加对外交流材料申请作业 **/
	void addExchangeMaterialEvent(ExchangeMaterialEvent event, String next_approver) throws Exception;

	/** 查询对外交流材料任务列表 **/
	List<ExchangeMaterialEvent> getExchangeMaterialEventList(Map<String, Object> map);

	/** 根据event_code删除对外交流材料作业 **/
	void delDelExchangeMaterialEventByEventCode(String event_code);

	/** 根据event_code查询对外交流材料作业的job_code **/
	String getExchangeMaterialJobCodeByEventCode(String event_code);

	/** 根据event_code查询对外交流材料作业列表 **/
	ExchangeMaterialEvent getExchangeMaterialEventByEventCode(String event_code);

	/** 根据job_code查询对外交流材料作业列表 **/
	List<ExchangeMaterialEvent> getExchangeMaterialListByJobCode(String job_code);

	/**
	 * 添加涉密文件（资料）外出制作申请
	 * 
	 * @param event
	 * @param next_approver
	 * @throws Exception
	 */
	void addFileOutMakeEvent(FileOutMakeEvent event, String next_approver) throws Exception;

	/**
	 * 查询涉密文件（资料）外出制作申请作业列表
	 * 
	 * @param map
	 * @return
	 */
	List<FileOutMakeEvent> getFileOutMakeEventList(Map<String, Object> map);

	/**
	 * 根据作业流水号查询涉密文件（资料）外出制作申请详情
	 * 
	 * @param event_code
	 * @return
	 */
	FileOutMakeEvent getFileOutMakeEventByEventCode(String event_code);

	/**
	 * 通过涉密文件（资料）外出制作作业流水号查询任务流水号
	 * 
	 * @param event_code
	 * @return
	 */
	String getJobCodeByFileOutMakeEventCode(String event_code);

	/**
	 * 通过作业流水号删除涉密文件（资料）外出制作作业
	 * 
	 * @param event_code
	 */
	void delDelFileOutMakeEventByEventCode(String event_code);

	/**
	 * 根据任务作业号查询涉密文件（资料）外出制作作业信息
	 * 
	 * @param job_code
	 * @return
	 */
	List<FileOutMakeEvent> getFileOutMakeEventListByJobCode(String job_code);

	/**
	 * 添加展览/展示保密审查申请
	 * 
	 * @param event
	 * @param next_approver
	 * @throws Exception
	 */
	void addExhibitionEvent(ExhibitionEvent event, String next_approver) throws Exception;

	/**
	 * 根据条件查询展示展览申请表
	 * 
	 * @param map
	 * @return
	 */
	List<ExhibitionEvent> getExhibitionEventList(Map<String, Object> map);

	/**
	 * 根据event_code获得展示展览申请
	 * 
	 * @param event_code
	 * @return
	 */
	ExhibitionEvent getExhibitionEventByEventCode(String event_code);

	/**
	 * 通过event_code获得展示展览的job_code
	 * 
	 * @param event_code
	 * @return
	 */
	String getExhibitionJobCodeByEventCode(String event_code);

	/**
	 * 根据job_code查询展示展览申请表
	 * 
	 * @param job_code
	 * @return
	 */
	List<ExhibitionEvent> getExhibitionListByJobCode(String job_code);

	/**
	 * 通过作业流水号删除展示展览保密审查作业
	 * 
	 * @param event_code
	 */
	void delDelExhibitionEventByEventCode(String event_code);

	/**
	 * 添加保密工作违规处罚整改督查申请
	 * 
	 * @param event
	 * @param next_approver
	 * @throws Exception
	 */
	void addPunishEvent(PunishRectifyEvent event, String next_approver) throws Exception;

	/**
	 * 查询保密工作违规处罚整改督查作业列表
	 * 
	 * @param map
	 * @return
	 */
	List<PunishRectifyEvent> getPunishEventList(Map<String, Object> map);

	/**
	 * 通过作业流水号删除保密工作违规处罚整改督查作业
	 * 
	 * @param event_code
	 */
	void delDelPunishEventByEventCode(String event_code, String apply_type);

	/**
	 * 根据作业流水号查询保密工作违规处罚整改督查详情
	 * 
	 * @param event_code
	 * @return
	 */
	PunishRectifyEvent getPunishEventByEventCode(String event_code);

	/**
	 * 通过event_code获得保密工作违规处罚整改督查的job_code
	 * 
	 * @param event_code
	 * @return
	 */
	String getPunishJobCodeByEventCode(String event_code);

	/**
	 * 根据job_code查询保密工作违规处罚整改督查申请表
	 * 
	 * @param job_code
	 * @return
	 */
	List<PunishRectifyEvent> getPunishEventListByJobCode(String job_code);

	/**
	 * 根据event_code添加审批时上传的文件列表
	 * 
	 * @param event_code
	 * @param file_list
	 * @param file_num
	 */
	void addApproveFile(String event_code, String approve_file_list, Integer approve_file_num);

	/**
	 * seccheckevent删除文件后，更新文件信息
	 * 
	 * @param event_code
	 * @param fileNum
	 * @param newFileList
	 */
	void updateSecCheckEventFileInfo(String event_code, Integer fileNum, String newFileList);

	/**
	 * seccheckevent用户重新提交申请后更新申请内容
	 * 
	 * @param event_code
	 * @param contact_num
	 * @param check_content
	 * @param file_num
	 * @param file_list
	 */
	void updateSecCheckEvent(String event_code, String contact_num, String check_content, Integer file_num,
			String file_list, Date apply_time);

	/**
	 * 提交论文发表专利申请申请
	 * 
	 * @param event
	 * @param next_approver
	 * @throws Exception
	 */
	void addPaperPatentEvent(PaperPatentEvent event, String next_approver) throws Exception;

	/**
	 * 通过论文发表专利申请的event_code获得job_code
	 * 
	 * @param event_code
	 * @return
	 */
	String getPaperPatentJobCodeByEventCode(String event_code);

	/**
	 * 根据job_code查询论文发表专利申请申请表
	 * 
	 * @param job_code
	 * @return
	 */
	List<PaperPatentEvent> getPaperPatentListByJobCode(String job_code);

	/**
	 * 查询论文发表专利申请申请列表
	 * 
	 * @param map
	 * @return
	 */
	List<PaperPatentEvent> getPaperPatentEventList(Map<String, Object> map);

	/**
	 * 根据作业流水号查询进入重要科研场地申请详情
	 * 
	 * @param event_code
	 * @return
	 */
	PaperPatentEvent getPaperPatentEventByEventCode(String event_code);

	/**
	 * 通过作业流水号删除论文发表专利申请作业
	 * 
	 * @param event_code
	 */
	void delPaperPatentEventByEventCode(String event_code, String file_type);
}