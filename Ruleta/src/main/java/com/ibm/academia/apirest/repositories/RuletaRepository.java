package com.ibm.academia.apirest.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.apirest.models.entities.Ruleta;

@Repository
public interface RuletaRepository extends PagingAndSortingRepository<Ruleta, Integer>  {

	@Query("select r.dineroApertura from ruleta r where r.id = ?1")
	public Optional<Ruleta> obtenerDineroApuestas(Integer id);

	@Query("Select r.numeroApuesta from ruleta r where r.id = ?1")
	public Optional<Ruleta> buscarNumeroApuestaPorId(Integer id);
	
	@Query("Update r.dineroApertura=?1 from ruleta r where r.id = ?2 ")
	public void apuestaGanada(Integer cantidadDinero, Integer ruletaId);
}
