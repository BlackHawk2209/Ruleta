package com.ibm.academia.apirest.models.entities;

import java.io.Serializable;
import java.util.Random;
import java.util.Set;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Positive;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ruletas")
public class Ruleta implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="estado_Ruleta", nullable=false)
	private boolean estadoRuleta;
	
	@Column(name = "numero_Apuesta")
	private Integer numeroApuesta;

	@Positive(message= "el valor debe ser mayor que cero")
	@Column(name = "dinero_Apertura")
	private Integer dineroApertura;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name= "ruleta_cliente", schema = "JuegoAzar",
			joinColumns = @JoinColumn(name = "ruleta_id"),
			inverseJoinColumns = @JoinColumn(name = "carrera_id")
			)
	private Set<Cliente> clientes;
	
	@PrePersist
	private void antesPersistir() {
		Random r = new Random();
		this.numeroApuesta = r.nextInt(37); 
	}
	
	private static final long serialVersionUID = 1232700132915165721L;

	
}
