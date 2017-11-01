package hdsec.web.project.user.model;

import ht304.hdsec.framework.common.model.PagingSorting;

public class RolePagingSorting extends Role implements PagingSorting {

	private PagingSorting ps;

	public RolePagingSorting(Role role, PagingSorting ps) {
		super(role.getRoleId(), role.getRoleName(), role.getPermissionList());
		this.ps = ps;
	}

	public PagingSorting getPs() {
		return ps;
	}

	public void setPs(PagingSorting ps) {
		this.ps = ps;
	}

	@Override
	public int getBeginIndex() {
		return ps.getBeginIndex();
	}

	@Override
	public String getDirection() {
		return ps.getDirection();
	}

	@Override
	public int getEndIndex() {
		return ps.getEndIndex();
	}

	@Override
	public int getPageSize() {
		return ps.getPageSize();
	}

	@Override
	public String getSortingName() {
		return ps.getSortingName();
	}

	@Override
	public boolean isAsc() {
		return ps.isAsc();
	}

}
