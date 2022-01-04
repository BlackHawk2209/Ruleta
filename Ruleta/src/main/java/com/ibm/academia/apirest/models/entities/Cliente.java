package com.ibm.academia.apirest.models.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



import lombok.Data;

@Data
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "No puede ser nulo")
	@NotEmpty(message = "No puede ser vacio")
	@Size(min=0, max=10000)
	@Column(name="dinero_apuesta", nullable = false)
	private Integer dineroApuesta;

	@NotNull(message = "No puede ser nulo")
	@NotEmpty(message = "No puede ser vacio")
	@Size(min=0, max=36)
	@Column(name = "numero_Apostar", nullable = false)
	private Integer numeroApostar;
	
	@ManyToMany(mappedBy = "clientes", fetch = FetchType.LAZY)
	private Set<Ruleta> ruletas;
	
	private static final long serialVersionUID = -4603466110009254811L;

	

}
