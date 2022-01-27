package com.diginamic.biblioWeb.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.diginamic.biblioWeb.model.Client;
import com.diginamic.biblioWeb.model.Emprunt;
import com.diginamic.biblioWeb.model.Livre;

public interface JpaEmprunt extends CrudRepository<Emprunt, Integer>
{
	@Query("select l from Livre l where :emp MEMBER OF l.empruntsLivres")
	public Iterable<Livre> findByLivre(Emprunt emp);

	@Query("select emp from Emprunt emp where emp.clientEmprunts IS :client")
	public Iterable<Emprunt> findByClient(Client client);

//	@Query("select emp from Emprunt emp where emp.clientEmprunts IS :client")
//	public Iterable<Emprunt> findByLivre(Client client);
}
