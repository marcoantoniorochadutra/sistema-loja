package com.application.a3.domain.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.application.a3.model.ref.AbstractEntityLifeCycle;
import com.application.a3.model.ref.AuditableEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class Operacao extends AbstractEntityLifeCycle implements AuditableEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operacao_seq")
	@SequenceGenerator(name = "operacao_seq", sequenceName = "operacao_seq", allocationSize = 1)
	@NotNull
	private int id;
	@JdbcTypeCode(SqlTypes.JSON)
	@NotNull
	private List<Produto> produtos;
	@NotNull
	private Double valorTotal;
	@NotNull
	@OneToOne
	private Usuario usuario;
	
	@Override
	public Map<String, String> getAuditoriaData() {
		Map<String, String> result = new HashMap<>();
		result.put("id", Integer.toString(id));
		result.put("nome", produtos.toString());
		result.put("tipo", "Operacao");
		return result;
	}
}
