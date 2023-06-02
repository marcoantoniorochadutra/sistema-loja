package com.application.a3.domain.entity;

import java.util.HashMap;
import java.util.Map;

import com.application.a3.model.ref.AbstractEntityLifeCycle;
import com.application.a3.model.ref.AuditableEntity;
import com.application.a3.model.ref.TipoEntidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
@Table(name = "Fornecedor")
public class Fornecedor extends AbstractEntityLifeCycle implements AuditableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fornecedor_seq")
	@SequenceGenerator(sequenceName = "fornecedor_seq", name = "fornecedor_seq", allocationSize = 1)
	@NotNull
	private Integer id;
	@NotBlank
	@NotNull
	private String nome;
	@NotNull
	private String apelido;
	@NotNull
	private String email;
	@NotNull
	@Column(unique = true)
	private String cadastroNacional;
	@NotNull
	private String numeroTelefone;
	@NotNull
	private String numeroCelular;
	@NotNull
	private TipoEntidade tipo;

	
	@Override
	public Map<String, String> getAuditoriaData() {
		Map<String, String> result = new HashMap<>();
		result.put("id", Integer.toString(id));
		result.put("nome", nome);
		result.put("tipo", "Fornecedor");
		return result;
	}
}
