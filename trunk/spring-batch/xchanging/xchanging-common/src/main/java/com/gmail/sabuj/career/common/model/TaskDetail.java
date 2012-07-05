package com.gmail.sabuj.career.common.model;

/**
 * The Task details created from the properties file.
 * Example Property values:
 * <pre><code>
 * task.filter.params_3=worksheet.name:Data 1,unit.type:MMcf
 * task.filter.params_4=worksheet.name:Data 2,source.key:N9100US2
 * </code></pre>
 * @author <a href="mailo:career.sabuj@gmail.com">Sabuj Das</a>
 *
 */
public class TaskDetail {

	private String taskName;
	private String worksheetName;
	private boolean hasWorkBookFilter = false;
	private String unitType;
	private boolean hasUnitTypeFilter = false;
	private String sourceKey;
	private boolean hasSourceKeyFilter = false;
	
	public TaskDetail(String taskName) {
		this.taskName = taskName;
	}

	public boolean isHasWorkBookFilter() {
		return hasWorkBookFilter;
	}

	public void setHasWorkBookFilter(boolean hasWorkBookFilter) {
		this.hasWorkBookFilter = hasWorkBookFilter;
	}

	public boolean isHasUnitTypeFilter() {
		return hasUnitTypeFilter;
	}

	public void setHasUnitTypeFilter(boolean hasUnitTypeFilter) {
		this.hasUnitTypeFilter = hasUnitTypeFilter;
	}

	public boolean isHasSourceKeyFilter() {
		return hasSourceKeyFilter;
	}

	public void setHasSourceKeyFilter(boolean hasSourceKeyFilter) {
		this.hasSourceKeyFilter = hasSourceKeyFilter;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getWorksheetName() {
		return worksheetName;
	}

	public void setWorkbookName(String workbookName) {
		this.worksheetName = workbookName;
		hasWorkBookFilter = true;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
		hasUnitTypeFilter = true;
	}

	public String getSourceKey() {
		return sourceKey;
	}

	public void setSourceKey(String sourceKey) {
		this.sourceKey = sourceKey;
		hasSourceKeyFilter = true;
	}

	/**
	 * Considering the task name is unique
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((taskName == null) ? 0 : taskName.hashCode());
		return result;
	}

	/**
	 * Considering the task name is unique
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskDetail other = (TaskDetail) obj;
		if (taskName == null) {
			if (other.taskName != null)
				return false;
		} else if (!taskName.equals(other.taskName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TaskDetail [taskName=" + taskName + "]";
	}
	
	
	
}
