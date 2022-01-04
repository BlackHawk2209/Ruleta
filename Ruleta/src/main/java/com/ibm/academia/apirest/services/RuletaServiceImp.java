package com.ibm.academia.apirest.services;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.academia.apirest.models.entities.Ruleta;
import com.ibm.academia.apirest.repositories.RuletaRepository;

public class RuletaServiceImp implements IRuletaService {

	@Autowired
	private RuletaRepository ruletaRepository;
	private Ruleta ruleta;
	
	@Override
	@Transactional
	public Integer crearNueva() {
		ruletaRepository.save(ruleta);
		
		return ruleta.getId();
		
	}

	@Override
	@Transactional
	public String aperturaRuleta(Integer ruletaId) {
		
		 if(ruletaRepository.findById(ruletaId).isPresent()) {
			 
			((Ruleta) ruleta).setEstadoRuleta(true);
			 ruletaRepository.save(ruleta);
			 return String.format("La ruleta con id %d si ha abierto", ruletaId);
			 
		 }else {
			return "La ruleta que deseas abrir no existe"; 
		 }
		
	}

	@Override
	@Transactional
	public Optional<Ruleta> cierreApuestas(Integer id) {
		
		if(ruletaRepository.findById(id).isPresent()) 
			((Ruleta) ruleta).setEstadoRuleta(false);
			 ruletaRepository.save(ruleta);
			return ruletaRepository.obtenerDineroApuestas(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Ruleta> listadoRuletas() {
		return ruletaRepository.findAll();
	}

}
