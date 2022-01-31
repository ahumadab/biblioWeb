package com.diginamic.biblioWeb.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diginamic.biblioWeb.exceptions.ErrorEmprunt;
import com.diginamic.biblioWeb.model.Client;
import com.diginamic.biblioWeb.model.Emprunt;
import com.diginamic.biblioWeb.model.Livre;
import com.diginamic.biblioWeb.repository.JpaClient;
import com.diginamic.biblioWeb.repository.JpaEmprunt;
import com.diginamic.biblioWeb.repository.JpaLivre;

@Controller
@RequestMapping("/emprunt")
public class EmpruntController
{
	@Autowired
	private JpaEmprunt gre;

	@Autowired
	private JpaClient grc;

	@Autowired
	private JpaLivre grl;

	private Optional<Emprunt> verifEmprunt(Integer id) throws ErrorEmprunt
	{
		Optional<Emprunt> e = gre.findById(id);
		if (e.isEmpty())
		{
			throw new ErrorEmprunt("Emprunt id: " + id + " non trouvé !");
		}
		return e;
	}

	@GetMapping("")
	public String findAll(Model model)
	{
		List<Emprunt> emprunts = (List<Emprunt>) gre.findAll();
		emprunts.forEach(e ->
		{
			Set<Livre> livres = new HashSet<>();
			gre.findByLivre(e).forEach(l ->
			{
				l.getEmpruntsLivres().clear();
				livres.add(l);
			});
			e.setLivresEmpruntes(livres);
		});
		model.addAttribute("emprunts", emprunts);
		model.addAttribute("titre", "Listes d'Emprunts");
		return "emprunts/Liste";
	}

	@GetMapping("/add")
	public String add(Model model)
	{
		model.addAttribute("empruntForm", new Emprunt());
		model.addAttribute("clients", (List<Client>) grc.findAll());
		model.addAttribute("livres", (List<Livre>) grl.findAll());
		model.addAttribute("titre", "Ajout d'Emprunt");
		return "emprunts/add";
	}

	@PostMapping("/add")
	public String add(Model model, @Valid @ModelAttribute("empruntForm") Emprunt empruntForm)
	{
		gre.save(empruntForm);
		empruntForm.getLivresEmpruntes().forEach(l ->
		{
			l.getEmpruntsLivres().add(empruntForm);
			grl.save(l);
		});
		return "redirect:/emprunt";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer pid) throws ErrorEmprunt
	{
		Emprunt emprunt = this.verifEmprunt(pid).get();
		gre.findByLivre(emprunt).forEach(l ->
		{
			l.getEmpruntsLivres().clear();
			grl.save(l);
		});
		gre.deleteById(pid);
		return "redirect:/emprunt";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Integer pid, Model model) throws ErrorEmprunt
	{
		Emprunt emprunt = this.verifEmprunt(pid).get();
		model.addAttribute("empruntForm", new Emprunt());
		model.addAttribute("clients", (List<Client>) grc.findAll());
		model.addAttribute("livres", (List<Livre>) grl.findAll());
		model.addAttribute("emprunt", emprunt);
		model.addAttribute("titre", "Modification d'Emprunt");
		return "emprunts/update";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable("id") Integer pid, Model model,
			@Valid @ModelAttribute("empruntForm") Emprunt empruntForm) throws ErrorEmprunt
	{
		Emprunt emprunt = this.verifEmprunt(pid).get();
		emprunt.setDateDebut(empruntForm.getDateDebut());
		emprunt.setDateFin(empruntForm.getDateFin());
		emprunt.setDelai(empruntForm.getDelai());
		emprunt.setClientEmprunts(empruntForm.getClientEmprunts());
		emprunt.setLivresEmpruntes(empruntForm.getLivresEmpruntes());
		gre.save(emprunt);
		empruntForm.getLivresEmpruntes().forEach(l ->
		{
			l.getEmpruntsLivres().add(emprunt);
			grl.save(l);
		});
		return "redirect:/emprunt";
	}
}
