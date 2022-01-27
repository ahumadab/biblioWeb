package com.diginamic.biblioWeb.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LIVRE")
public class Livre
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String auteur;

	private String titre;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "COMPO", joinColumns = @JoinColumn(name = "ID_LIV", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ID_EMP", referencedColumnName = "ID"))
	private Set<Emprunt> empruntsLivres;

	public Livre()
	{
		this.empruntsLivres = new HashSet<>();
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getAuteur()
	{
		return auteur;
	}

	public void setAuteur(String auteur)
	{
		this.auteur = auteur;
	}

	public String getTitre()
	{
		return titre;
	}

	public void setTitre(String titre)
	{
		this.titre = titre;
	}

	public Set<Emprunt> getEmpruntsLivres()
	{
		return empruntsLivres;
	}

	public void setEmpruntsLivres(Set<Emprunt> empruntsLivres)
	{
		this.empruntsLivres = empruntsLivres;
	}

	@Override
	public String toString()
	{
		return "Livre [id=" + id + ", auteur=" + auteur + ", titre=" + titre + "]";
	}

}
