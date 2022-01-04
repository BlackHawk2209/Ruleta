package com.ibm.academia.apirest.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.academia.apirest.models.entities.Cliente;
import com.ibm.academia.apirest.models.entities.Ruleta;
import com.ibm.academia.apirest.repositories.ClienteRepository;
import com.ibm.academia.apirest.repositories.RuletaRepository;

public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	private RuletaRepository ruletaRepository;
	private Cliente cliente;
	
	
	@Override
	public String apuestaNumero(Integer numeroApuesta, Integer cantidadDinero, Integer ruletaId) {
		cliente = null;
		cliente.setNumeroApostar(numeroApuesta);
		clienteRepository.save(cliente);
		
		Optional<Ruleta> numeroRuleta = ruletaRepository.buscarNumeroApuestaPorId(ruletaId);
				
		if(cliente.getNumeroApostar().equals(numeroRuleta)) {
			cliente.setDineroApuesta(cantidadDinero+cantidadDinero);
			return "Felicidades has acertado al numero y ganado el dinero que apostaste";
		}else {
			ruletaRepository.apuestaGanada(cantidadDinero, ruletaId);
			return "No has acertado en la apuesta, pierdes el dinero apostado";
		}
	
	}

}
