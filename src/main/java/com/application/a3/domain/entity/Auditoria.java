package com.application.a3.domain.entity;

import java.util.Date;

import com.application.a3.model.ref.TipoOperacao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Auditoria {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auditoria_seq")
	@SequenceGenerator(name = "auditoria_seq", sequenceName = "auditoria_seq", allocationSize = 1)
	@NotNull
	private Integer id;
	@NotNull
	private TipoOperacao tipoOperacao;
	@NotNull
	private Date dataAlteracao;
	@NotBlank
	@NotNull
	private String nomeEntidade;
	@NotNull
	private Integer idEntidade;
	@NotNull
	private String tipoEntidade;
	@NotBlank
	@NotNull
	private String usuario;
	@NotNull
	private Integer idUsuario;

}