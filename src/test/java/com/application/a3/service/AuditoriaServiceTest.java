package com.application.a3.service;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.application.a3.domain.entity.Auditoria;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class AuditoriaServiceTest {
	
	private final AuditoriaService auditoriaService;

	@Autowired
	public AuditoriaServiceTest(AuditoriaService auditoriaService) {
		this.auditoriaService = auditoriaService;
	}
	
	@Test
	@Order(1)
	public void deveCadastrarFornecedor() {

		List<Auditoria> s = auditoriaService.buscarAuditoriaFornecedor();
		System.out.println(s);

	}

}
