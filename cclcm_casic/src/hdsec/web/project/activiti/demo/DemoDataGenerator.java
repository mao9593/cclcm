package hdsec.web.project.activiti.demo;

/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import hdsec.web.project.user.service.UserService;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.apache.log4j.Logger;

/**
 * @author Joram Barrez
 */
public class DemoDataGenerator implements ModelDataJsonConstants {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	@Resource
	protected transient IdentityService identityService;
	@Resource
	protected transient RepositoryService repositoryService;
	@Resource
	protected UserService userService;
	
	protected boolean createDemoUsersAndGroups;
	protected boolean createDemoProcessDefinitions;
	
	public void init() {
		logger.debug("开始初始化流程引擎，部署流程定义。");
		if (createDemoUsersAndGroups) {
			initDemoUsers();
		}
		
		if (createDemoProcessDefinitions) {
			initProcessDefinitions();
		}
		logger.debug("流程引擎初始化结束。");
	}
	
	public void setCreateDemoUsersAndGroups(boolean createDemoUsersAndGroups) {
		this.createDemoUsersAndGroups = createDemoUsersAndGroups;
	}
	
	public void setCreateDemoProcessDefinitions(boolean createDemoProcessDefinitions) {
		this.createDemoProcessDefinitions = createDemoProcessDefinitions;
	}
	
	protected void initDemoUsers() {
		logger.info("创建用户开始");
		List<String> userIdList = userService.getUserIdList(Collections.<String, String> emptyMap());
		for (String user_iidd : userIdList) {
			createUser(user_iidd, "", "", "", "");
		}
		/*createUser("renmingfei", "renmingfei", "renmingfei", "renmingfei", "renmingfei@activiti.org");
		createUser("lijing", "lijing", "lijing", "lijing", "lijing@activiti.org");
		createUser("zhangyongjing", "zhangyongjing", "zhangyongjing", "zhangyongjing", "zhangyongjing@activiti.org");
		createUser("lishicheng", "lishicheng", "lishicheng", "lishicheng", "lishicheng@activiti.org");
		createUser("zhangjunfeng", "zhangjunfeng", "zhangjunfeng", "zhangjunfeng", "zhangjunfeng@activiti.org");*/
		logger.info("创建用户结束");
	}
	
	protected void createUser(String userId, String firstName, String lastName, String password, String email) {
		if (identityService.createUserQuery().userId(userId).count() == 0) {
			User user = identityService.newUser(userId);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPassword(password);
			user.setEmail(email);
			identityService.saveUser(user);
		}
	}
	
	protected void initProcessDefinitions() {
		logger.info("部署流程开始");
		String deploymentName = "approval_process";
		//查询流程是否已经存在
		List<Deployment> deploymentList = repositoryService.createDeploymentQuery().deploymentName(deploymentName)
				.list();
		if (deploymentList == null || deploymentList.size() == 0) {
			repositoryService.createDeployment().name(deploymentName)
					.addClasspathResource("hdsec/web/project/activiti/demo/process/approval_process_common.bpmn20.xml")
					.deploy();
			logger.info("部署流程：approve_process");
		} else {
			logger.info("流程已存在：approve_process");
		}
		logger.info("部署流程结束");
	}
}
