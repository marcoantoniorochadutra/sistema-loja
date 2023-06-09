package com.application.a3.web.endpoint;

import java.util.Date;

import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.a3.model.dto.DashboardResultDto;
import com.application.a3.service.DashboardService;

@RestController
@Singleton
@RequestMapping("/dashboard")
public class DashboardController {
	
	private final DashboardService dashboardService;

	@Autowired
	public DashboardController(DashboardService dashboardService) {
		this.dashboardService = dashboardService;
	}
	
	@GetMapping("/popular")
	public DashboardResultDto valorEstoqueTotal(@RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) Date startDate, @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) Date endDate) {
		return dashboardService.buildDashboard(startDate, endDate);
	}

}
