package com.application.a3.web.endpoint;

import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.a3.model.dto.OperacaoDto;
import com.application.a3.service.OperacaoService;

import jakarta.validation.Valid;

@RestController
@Singleton
@RequestMapping("/operacao")
public class OperacaoController {
	
	private final OperacaoService operacaoService;
	
	@Autowired
	public OperacaoController(OperacaoService operacaoService) {
		this.operacaoService = operacaoService;
	}
	
	

	@PostMapping("/cadastrar")
	public OperacaoDto efetuarVenda(@Valid @RequestBody OperacaoDto dto) {
		return operacaoService.efetuarVenda(dto);
	}
	
	
	@DeleteMapping("/reembolsar/{id}")
	public void realizarReembolso(@PathVariable("id") Integer id) {
		operacaoService.efetuarReembolso(id);
	}
	
	@GetMapping("/listar/{usuarioId}")
	public void listarPorUsuario(@PathVariable("id") Integer id, @Valid @RequestBody OperacaoDto dto) {
		operacaoService.efetuarReembolso(id);
	}

}
