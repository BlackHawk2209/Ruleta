package com.ibm.academia.apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.exceptions.BadRequestException;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.models.entities.Ruleta;
import com.ibm.academia.apirest.services.IRuletaService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/ruleta")
@Api(value = "Metodos relacionados unicamente con la ruleta", tags = "Acciones de la ruleta")
public class RuletaController {
	
	@Autowired
	IRuletaService ruletaService;
	
	/**
	 * Endpoint para que se cree una ruleta 
	 * @return devuelve el id de la ruleta creada
	 * @NotFoundException En caso de que la ruleta no se pueda crear
	 * @author BPG 
	 */
	@GetMapping("/crear")
	public ResponseEntity<?> crearNueva(){
		Integer ruletas = ruletaService.crearNueva();
		
		if(ruletas.equals(null))
			throw new NotFoundException("No se pudo crear la ruleta");
		
		return new ResponseEntity<Integer>(ruletas, HttpStatus.OK);
	}
	
	/**
	 * Endpoint para cambiar el estado de una ruleta a abierto
	 * @param ruletaId
	 * @return devuelve un string donde dice si se abrio la ruleta o si ya estaba abierta
	 * @NotFoundException en caso de que el id de la ruleta a abrir no exista
	 * @author BPG
	 */
	@PutMapping("/apertura/ruletaId/{ruletaId}")
	public ResponseEntity<?> aperturaRuleta(@PathVariable Integer ruletaId){
		String ruletas = ruletaService.aperturaRuleta(ruletaId);
		
		if(!ruletas.isEmpty())
			throw new BadRequestException(String.format("La ruleta con ID: %d no existe", ruletaId));
		
		return new ResponseEntity<String>(ruletas, HttpStatus.OK);
	}
	
	/**
	 * Endpoint para cerrar ruletas por medio de Id
	 * @param ruletaId
	 * @return El resultado de las apuestas desde su apertura hasta su cierre
	 * @author BPG
	 */
	@PutMapping("/cierre/ruletaId/{ruletaId}")
	public ResponseEntity<?> cierreApuestas(@PathVariable Integer ruletaId){
		Optional<Ruleta> ruletas = ruletaService.cierreApuestas(ruletaId);
		
		if(!ruletas.isPresent())
			throw new BadRequestException(String.format("La ruleta con ID: %d no existe", ruletaId));
		
		return new ResponseEntity<Optional<Ruleta>>(ruletas, HttpStatus.OK);
	}
	
	
	/**
	 * Endpoint que lista todas las ruletas con su estado
	 * @return el id de la ruleta con su estado abieto o cerrado
	 * @author BPG
	 */
	@GetMapping("/listar")
	public ResponseEntity<?> listarRuletas(){
		List<Ruleta> ruletas = (List<Ruleta>) ruletaService.listadoRuletas();
		
		if(ruletas.isEmpty())
			throw new NotFoundException("No existen ruletas en la base de datos.");
		
		return new ResponseEntity<List<Ruleta>>(ruletas, HttpStatus.OK);
	}
	
	
}
