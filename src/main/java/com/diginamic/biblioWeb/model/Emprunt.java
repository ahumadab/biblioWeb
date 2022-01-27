package com.diginamic.biblioWeb.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "EMPRUNT")
public class Emprunt
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_debut")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateDebut;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "date_fin")
	private Date dateFin;

	private Integer delai;

	@ManyToOne
	@JoinColumn(name = "id_client")
	private Client clientEmprunts;

//	@ManyToMany(mappedBy = "empruntsLivres")
	@Transient
	private Set<Livre> livresEmpruntes;

	public Emprunt()
	{
		super();
		this.livresEmpruntes = new HashSet<>();
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Date getDateDebut()
	{
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut)
	{
		this.dateDebut = dateDebut;
	}

	public Date getDateFin()
	{
		return dateFin;
	}

	public void setDateFin(Date dateFin)
	{
		this.dateFin = dateFin;
	}

	public Integer getDelai()
	{
		return delai;
	}

	public void setDelai(Integer delai)
	{
		this.delai = delai;
	}

	public Client getClientEmprunts()
	{
		return clientEmprunts;
	}

	public void setClientEmprunts(Client clientEmprunts)
	{
		this.clientEmprunts = clientEmprunts;
	}

	public Set<Livre> getLivresEmpruntes()
	{
		return livresEmpruntes;
	}

	public void setLivresEmpruntes(Set<Livre> livresEmpruntes)
	{
		this.livresEmpruntes = livresEmpruntes;
	}

	@Override
	public String toString()
	{
		return "Emprunt [id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", delai=" + delai + "]";
	}

}
