package com.application.a3.web.endpoint;

import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("/valor/estoque")
	public Double valorEstoqueTotal() {
		return dashboardService.valorEstoqueTotal();
	}

}
