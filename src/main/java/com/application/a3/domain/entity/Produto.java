package com.application.a3.domain.entity;

import java.util.HashMap;
import java.util.Map;

import com.application.a3.model.ref.AbstractEntityLifeCycle;
import com.application.a3.model.ref.AuditableEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Produto extends AbstractEntityLifeCycle implements AuditableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
	@SequenceGenerator(name = "produto_seq", sequenceName = "produto_seq", allocationSize = 1)
	@NotNull
	private int id;
	@NotBlank
	@NotNull
	@Column(unique = true)
	private String nome;
	@NotNull
	private Double valorVenda;
	@NotNull
	private Double valorCusto;
	@NotNull
	private Integer quantidade;
	@ManyToOne
	@NotNull
	private Fornecedor fornecedor;
	
	@Override
	public Map<String, String> getAuditoriaData() {
		Map<String, String> result = new HashMap<>();
		result.put("id", Integer.toString(id));
		result.put("nome", nome);
		result.put("tipo", "Produto");
		return result;
	}

}
