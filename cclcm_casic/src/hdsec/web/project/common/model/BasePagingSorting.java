package hdsec.web.project.common.model;

import ht304.hdsec.framework.common.model.PagingSorting;

import java.util.HashMap;
import java.util.Map;

public class BasePagingSorting implements PagingSorting {

	private int beginIndex;

	private int endIndex;

	private String direction;

	private String sortingName;

	private int pageSize;

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getSortingName() {
		return sortingName;
	}

	public void setSortingName(String sortingName) {
		this.sortingName = sortingName;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isAsc() {
		return "asc".equalsIgnoreCase(direction);
	}

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageSize);
		map.put("sortingName", sortingName);
		map.put("beginIndex", beginIndex);
		map.put("endIndex", endIndex);
		map.put("direction", direction);
		return map;
	}

}
