package hdsec.web.project.securityuser.action;

import hdsec.web.project.common.bm.model.BMSysConfigItem;
import hdsec.web.project.common.bm.model.BmRealUser;
import hdsec.web.project.securityuser.model.AbroadInfo;
import hdsec.web.project.securityuser.model.ExperienceInfo;
import hdsec.web.project.securityuser.model.FamilyInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 将人员信息中拼接字符都拆分插入新的数据库表中
 * 
 * @author guojiao
 */
public class BackUpUserPartInfoAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private List<BmRealUser> bmUser = null;
	private String back = "";

	public void setBack(String back) {
		this.back = back;
	}

	@Override
	public String executeFunction() throws Exception {
		if (back.equals("Y")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("area", "backup");
			bmUser = securityUserService.getAllUserInfoList(map);
			for (BmRealUser temp : bmUser) {
				// 个人简历
				if (!temp.getJob_resume().equals("")) {
					String[] experiencess = temp.getJob_resume().split(BMSysConfigItem.COMMON_SEPARATOR_END);
					// experienceinfo1 = new ArrayList<ExperienceInfo>();
					for (int i = 0; i < experiencess.length; i++) {
						String name = experiencess[i] + "end|";
						String[] experiences_info = name.split("\\|");
						Integer row_num = i + 1;
						if (experiences_info.length > 4) {
							ExperienceInfo experiences = new ExperienceInfo(experiences_info[2], experiences_info[3],
									experiences_info[4], row_num.toString(), temp.getEvent_code());
							securityUserService.addUserExperiences(experiences);
						}
					}
				}
				// 出国情况
				if (!temp.getAbroad_twice().equals("")) {
					String[] twice_abroads = temp.getAbroad_twice().split(BMSysConfigItem.COMMON_SEPARATOR_END);
					// abroadinfo1 = new ArrayList<AbroadInfo>();
					for (int i = 0; i < twice_abroads.length; i++) {
						String name = twice_abroads[i] + "end|";
						String[] abroad_info = name.split("\\|");
						Integer row_num = i + 1;
						if (abroad_info.length > 9) {
							AbroadInfo abroad = new AbroadInfo(abroad_info[2], abroad_info[3], abroad_info[4],
									abroad_info[5], abroad_info[6], abroad_info[7], abroad_info[8], abroad_info[9],
									row_num.toString(), temp.getEvent_code());
							securityUserService.addUserAbroad(abroad);
						}
					}
				}

				// 家庭成员情况
				if (!temp.getFamily_info().equals("")) {
					String[] family_nums = temp.getFamily_info().split(BMSysConfigItem.COMMON_SEPARATOR_END);
					// familyinfo1 = new ArrayList<FamilyInfo>();
					for (int i = 0; i < family_nums.length; i++) {
						String name = family_nums[i] + "end|";
						String[] family_info = name.split("\\|");
						Integer row_num = i + 1;
						if (family_info.length > 9) {
							FamilyInfo family = new FamilyInfo(family_info[2], family_info[3], family_info[4],
									family_info[5], family_info[6], family_info[7], family_info[8], family_info[9],
									row_num.toString(), temp.getEvent_code());
							securityUserService.addUserFamily(family);
						}
					}
				}
			}
		}

		return SUCCESS;
	}
}
