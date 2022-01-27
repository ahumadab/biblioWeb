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

import com.diginamic.biblioWeb.exceptions.ErrorClient;
import com.diginamic.biblioWeb.model.Client;
import com.diginamic.biblioWeb.repository.JpaClient;

@Controller
@RequestMapping("/client")
public class ClientController
{

	@Autowired
	JpaClient grc;

	@GetMapping("/clients")
	public String findAll(Model model)
	{
		model.addAttribute("clients", (List<Client>) grc.findAll());
		model.addAttribute("titre", "Liste de Clients");
		return "clients/Liste";
	}

	@GetMapping("/add")
	public String add(Model model)
	{
		model.addAttribute("clientForm", new Client());
		model.addAttribute("titre", "Ajout client");
		return "clients/add";
	}

	@PostMapping("/add")
	public String add(Model model, @Valid @ModelAttribute("clientForm") Client clientForm)
	{
		grc.save(clientForm);
		return "redirect:/client/clients";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer pid) throws ErrorClient
	{
		Optional<Client> c = grc.findById(pid);
		if (c.isEmpty())
		{
			throw new ErrorClient("Client id: " + pid + " non trouvé !");
		}
		grc.deleteById(pid);
		return "redirect:/client/clients";
	}
}
