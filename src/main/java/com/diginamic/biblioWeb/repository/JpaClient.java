package com.diginamic.biblioWeb.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.diginamic.biblioWeb.model.Client;
import com.diginamic.biblioWeb.model.Emprunt;

public interface JpaClient extends CrudRepository<Client, Integer>
{
	@Query("select emp from Emprunt emp where emp.clientEmprunts.id = :id")
	public Iterable<Emprunt> getEmpruntByClient(Integer id);
}
