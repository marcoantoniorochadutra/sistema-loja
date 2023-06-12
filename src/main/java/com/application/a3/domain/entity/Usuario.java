package com.application.a3.domain.entity;

import com.application.a3.model.ref.TipoUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Devido a não pode usar JWT, classe apenas para utilização no Objeto Auditoria.

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
	@SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", allocationSize = 1)
	@NotNull
	private Integer id;
	@NotNull
	@Column(unique = true)
	private String nome;
	@NotNull
	@Column(unique = true)
	private String email;
	@NotNull
	@Column(unique = true)
	private String cadastroNacional;
	@NotNull
	@Column(unique = true, length = 11)
	@Pattern(regexp = "^\\d{8,11}$")
	private String numeroTelefone;
	@Column(unique = true, length = 11)
	@Pattern(regexp = "^\\d{8,11}$")
	@NotNull
	private String numeroCelular;
	@NotNull
	private TipoUsuario tipo;

}
