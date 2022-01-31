package com.diginamic.biblioWeb.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diginamic.biblioWeb.exceptions.ErrorLivre;
import com.diginamic.biblioWeb.model.Livre;
import com.diginamic.biblioWeb.repository.JpaLivre;

@Controller
@RequestMapping("/livre")
public class LivreController
{
	@Autowired
	private JpaLivre grl;

	private Optional<Livre> verifLivre(Integer id) throws ErrorLivre
	{
		Optional<Livre> l = grl.findById(id);
		if (l.isEmpty())
		{
			throw new ErrorLivre("Livre id: " + id + " non trouvé !");
		}
		return l;
	}

	@GetMapping
	public String findAll(Model model)
	{
		model.addAttribute("livres", (List<Livre>) grl.findAll());
		model.addAttribute("titre", "Liste de Livres");
		return "livres/Liste";
	}

	@GetMapping("/add")
	public String add(Model model)
	{
		model.addAttribute("livreForm", new Livre());
		model.addAttribute("titre", "Ajout de Livre");
		return "livres/add";
	}

	@PostMapping("/add")
	public String add(Model model, @Valid @ModelAttribute("livreForm") Livre livreFrom)
	{
		grl.save(livreFrom);
		return "redirect:/livre";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer pid) throws ErrorLivre
	{
		this.verifLivre(pid);
		grl.deleteById(pid);
		return "redirect:/livre";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Integer pid, Model model) throws ErrorLivre
	{
		Livre livre = this.verifLivre(pid).get();
		model.addAttribute("livreForm", new Livre());
		model.addAttribute("livre", livre);
		model.addAttribute("titre", "Modification de Livre");
		return "livres/update";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable("id") Integer pid, Model model,
			@Valid @ModelAttribute("livreForm") Livre livreForm) throws ErrorLivre
	{
		Livre livre = this.verifLivre(pid).get();
		livre.setAuteur(livreForm.getAuteur());
		livre.setTitre(livreForm.getTitre());
		grl.save(livre);
		return "redirect:/livre";
	}
}
