package hdsec.web.project.disc.action;

import hdsec.web.project.disc.model.EntitySpaceCD;


/**
 * 空白盘详细信息
 * @author zp
 *
 */
public class ViewSpaceCdInfoAction extends DiscBaseAction {
	private static final long serialVersionUID = 1L;
	private String id;
	private EntitySpaceCD entity;
	
	
	public EntitySpaceCD getEntity() {
		return entity;
	}

	public void setEntity(EntitySpaceCD entity) {
		this.entity = entity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String executeFunction() throws Exception {
		entity=discService.getEntitySpaceCdById(id);
		return SUCCESS;
	}
}
