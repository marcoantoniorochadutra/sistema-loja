package com.application.a3.web.endpoint;

import java.util.List;

import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.a3.domain.entity.Auditoria;
import com.application.a3.service.AuditoriaService;

@RestController
@Singleton
@RequestMapping("/auditoria")
public class AuditoriaController {

	private final AuditoriaService auditoriaService;

	@Autowired
	public AuditoriaController(AuditoriaService auditoriaService) {
		this.auditoriaService = auditoriaService;
	}

	@GetMapping("/buscar/usuario/{usuario}")
	public List<Auditoria> buscarUsuario(@PathVariable("usuario") String usuario) {
		return auditoriaService.buscarAuditoriaUsuario(usuario);
	}

	@GetMapping("/buscar/fornecedor/")
	public List<Auditoria> buscarFornecedor() {
		return auditoriaService.buscarAuditoriaFornecedor();
	}

	@GetMapping("/buscar/operacao/")
	public List<Auditoria> buscarOperacao() {
		return auditoriaService.buscarAuditoriaOperacao();
	}

	@GetMapping("/buscar/produto/")
	public List<Auditoria> buscarProduto() {
		return auditoriaService.buscarAuditoriaProduto();
	}

}
