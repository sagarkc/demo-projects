package com.gs.question.master.model.entity.dshb;

import java.util.HashSet;
import java.util.Set;

public class Dashboard {

	
	private Long dashboardId;
	private String dashboardTitle;
	private String description;
	
	private Set<DashboardSection> dashboardSections = new HashSet<>(0);
	
	
}
