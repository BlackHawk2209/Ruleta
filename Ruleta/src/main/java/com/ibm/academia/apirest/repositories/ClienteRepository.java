package com.ibm.academia.apirest.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ibm.academia.apirest.models.entities.Cliente;


public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Integer>  {

	

}
