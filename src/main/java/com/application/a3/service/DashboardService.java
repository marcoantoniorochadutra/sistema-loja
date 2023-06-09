package com.application.a3.service;

import java.util.Date;

import com.application.a3.model.dto.DashboardResultDto;

public interface DashboardService {

	public DashboardResultDto buildDashboard(Date startDate, Date endDate);

	

	

}
