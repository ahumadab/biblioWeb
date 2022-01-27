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
		Optional<Livre> l = grl.findById(pid);
		if (l.isEmpty())
		{
			throw new ErrorLivre("Livre id: " + pid + " non trouvé !");
		}
		grl.deleteById(pid);
		return "redirect:/livre";
	}
}
