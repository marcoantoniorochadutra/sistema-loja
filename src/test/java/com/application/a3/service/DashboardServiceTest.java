package com.application.a3.service;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class DashboardServiceTest {
	
	private final DashboardService dashboardService;

	@Autowired
	public DashboardServiceTest(DashboardService dashboardService) {
		this.dashboardService = dashboardService;
	}



	@Test
	@Order(1)
	public void deveBuscarValorEstoque() {
		
		Double valor = dashboardService.valorEstoqueTotal();
		
		assertEquals(24200.0, valor);
	}

	@Test
	@Order(2)
	public void deveBuscarLucroEstoque() {

		Double valor = dashboardService.lucroEstoqueTotal();

		assertEquals(25000.0, valor);
	}

}
