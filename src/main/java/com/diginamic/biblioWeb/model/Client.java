package com.diginamic.biblioWeb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CLIENT")
public class Client
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@NotBlank
	private String nom;

	@NotNull
	@NotBlank
	private String prenom;

//	@OneToMany(mappedBy = "clientEmprunts", fetch = FetchType.LAZY)
//	private Set<Emprunt> emprunts;

	public Client()
	{
		super();
//		this.emprunts = new HashSet<Emprunt>();
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getNom()
	{
		return nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public String getPrenom()
	{
		return prenom;
	}

	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}

//	public Set<Emprunt> getEmprunts()
//	{
//		return emprunts;
//	}
//
//	public void setEmprunts(Set<Emprunt> emprunts)
//	{
//		this.emprunts = emprunts;
//	}

	@Override
	public String toString()
	{
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
	}

}