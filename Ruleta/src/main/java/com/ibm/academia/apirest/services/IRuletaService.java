package com.ibm.academia.apirest.services;

import java.util.Optional;

import com.ibm.academia.apirest.models.entities.Ruleta;

public interface IRuletaService {
	
	public Integer crearNueva();
	public String aperturaRuleta(Integer ruletaId);
	public Optional<Ruleta> cierreApuestas(Integer id);
	public Iterable<Ruleta> listadoRuletas(); 
	
}
