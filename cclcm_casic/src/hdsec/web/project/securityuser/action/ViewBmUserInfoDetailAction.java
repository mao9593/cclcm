package hdsec.web.project.securityuser.action;

import hdsec.web.project.burn.model.BurnFile;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.bm.BMPropertyUtil;
import hdsec.web.project.common.bm.model.BmRealUser;
import hdsec.web.project.securityuser.model.AbroadInfo;
import hdsec.web.project.securityuser.model.ExperienceInfo;
import hdsec.web.project.securityuser.model.FamilyInfo;
import hdsec.web.project.securityuser.model.ResignEvent;
import hdsec.web.project.user.model.SecUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 查看用户信息完善作业详情
 * 
 * @author guojiao
 */
public class ViewBmUserInfoDetailAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private String user_id = "";
	private BmRealUser userinfo = null;
	private List<AbroadInfo> abroadinfo = null;
	private List<ExperienceInfo> experienceinfo = null;
	private List<FamilyInfo> familyinfo = null;
	private List<BurnFile> burnFileList = null;
	private SecUser secUser = null;
	private String paper_total = "";
	private String cd_total = "";
	private Integer all_total = null;
	private List<ResignEvent> resign_event = null;
	private ResignEvent revent = null;
	private String headpath = "";// 用户头像路径

	public String getHeadpath() {
		return headpath;
	}

	public ResignEvent getRevent() {
		return revent;
	}

	public Integer getAll_total() {
		return all_total;
	}

	public String getPaper_total() {
		return paper_total;
	}

	public String getCd_total() {
		return cd_total;
	}

	public SecUser getSecUser() {
		return secUser;
	}

	public List<BurnFile> getBurnFileList() {
		return burnFileList;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public List<AbroadInfo> getAbroadinfo() {
		return abroadinfo;
	}

	public List<ExperienceInfo> getExperienceinfo() {
		return experienceinfo;
	}

	public List<FamilyInfo> getFamilyinfo() {
		return familyinfo;
	}

	public BmRealUser getUserinfo() {
		return userinfo;
	}

	@Override
	public String executeFunction() throws Exception {
		if (user_id.equals("")) {
			throw new Exception("未获取到用户ID无法查询");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("real_user_id", user_id);
		map.put("info_type", 2);
		userinfo = securityUserService.getUserInfoByEventCode(map);
		if (userinfo == null) {
			throw new Exception("查询的用户个人资料未完善或被删除");
		}
		// 查询该用户个人涉密载体总数
		map.put("user_id", user_id);
		paper_total = securityUserService.getUserEntityPaperTotal(map);
		cd_total = securityUserService.getUserEntityCdTotal(map);
		all_total = Integer.valueOf(paper_total).intValue() + Integer.valueOf(cd_total).intValue();

		// 查询离职、退休人员的脱密期时间
		if (userinfo.getUser_statue() == 2 || userinfo.getUser_statue() == 3) {
			Map<String, Object> remap = new HashMap<String, Object>();
			remap.put("resign_user_iidd", userinfo.getReal_user_id());
			if (userinfo.getUser_statue() == 2) {
				remap.put("reason", "1");
			} else {
				remap.put("reason", "2");
			}
			resign_event = securityUserService.getUResignEventList(remap);
			if (resign_event.size() != 0 && resign_event != null) {
				revent = resign_event.get(0);
			}
		}

		// 个人头像
		headpath = getHeadShot(userinfo.getReal_user_id());

		secUser = userService.getSecUserByUid(user_id);
		Map<String, Object> part = new HashMap<String, Object>();
		part.put("event_code", userinfo.getEvent_code());
		if (!userinfo.getJob_resume().equals("")) {
			experienceinfo = securityUserService.getUserExperience(part);
		}
		if (!userinfo.getAbroad_twice().equals("")) {
			abroadinfo = securityUserService.getUserAbroad(part);

			burnFileList = new ArrayList<BurnFile>();
			for (int a = 0; a < abroadinfo.size(); a++) {
				String[] filelist = abroadinfo.get(a).getAbroad_file().split(CCLCMConstants.DEVIDE_SYMBOL);
				if (filelist.length > 0 && StringUtils.hasLength(filelist[0])) {
					String storPath = "";
					try {
						storPath = BMPropertyUtil.getSecUserInfoSepcialFilePath();
					} catch (Exception e) {
						e.printStackTrace();
					}

					String file_path = "";
					for (int i = 0; i < filelist.length; i++) {
						file_path = storPath + "/" + user_id + "/" + filelist[i];
						burnFileList.add(new BurnFile(filelist[i], file_path));

					}
				}
			}
		}
		if (!userinfo.getFamily_info().equals("")) {
			familyinfo = securityUserService.getUserFamily(part);
		}

		return SUCCESS;
	}
}
