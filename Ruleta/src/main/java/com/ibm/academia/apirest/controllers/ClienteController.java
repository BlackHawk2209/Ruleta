package com.ibm.academia.apirest.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.services.IClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	/**
	 * Endpoint para apostar 
	 * @param dinero
	 * @param numero
	 * @return el string de que se realizo la apuesta y si se gano o no
	 * @author BPG
	 */
	@GetMapping("/apuesta/numero/{numero}/dinero/{dinero}/idruleta/{ruletaId")
	public ResponseEntity<?> apostarNumero(@PathVariable Integer numero, @PathVariable Integer dinero, @PathVariable Integer ruletaId){
		
		String apuesta = clienteService.apuestaNumero(numero, dinero, ruletaId);
		if(apuesta.isBlank())
			throw new NotFoundException(String.format("No se pudo realizar la apuesta"));
			
			return new ResponseEntity<String>(apuesta, HttpStatus.OK);
		
	}
	
}
