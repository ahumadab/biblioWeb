package com.diginamic.biblioWeb.repository;

import org.springframework.data.repository.CrudRepository;

import com.diginamic.biblioWeb.model.Livre;

public interface JpaLivre extends CrudRepository<Livre, Integer>
{

}
