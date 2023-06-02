package com.application.a3.domain.entity;

import com.application.a3.model.ref.TipoUsuario;

import jakarta.persistence.Column;
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
	@NotBlank
	@NotNull
	@Column(unique = true)
	private String nome;
	@NotBlank
	@NotNull
	@Column(unique = true)
	private String cadastroNacional;
	@NotBlank
	@NotNull
	private String numeroTelefone;
	@NotBlank
	@NotNull
	private String numeroCelular;
	@NotNull
	private TipoUsuario tipo;

}
