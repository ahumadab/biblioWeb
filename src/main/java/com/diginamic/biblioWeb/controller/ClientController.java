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
	private JpaClient grc;

	private Optional<Client> verifClient(Integer id) throws ErrorClient
	{
		Optional<Client> c = grc.findById(id);
		if (c.isEmpty())
		{
			throw new ErrorClient("Client id: " + id + " non trouvé !");
		}
		return c;
	}

	@GetMapping("")
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
		model.addAttribute("titre", "Ajout de Client");
		return "clients/add";
	}

	@PostMapping("/add")
	public String add(Model model, @Valid @ModelAttribute("clientForm") Client clientForm)
	{
		grc.save(clientForm);
		return "redirect:/client";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer pid) throws ErrorClient
	{
		this.verifClient(pid);
		grc.deleteById(pid);
		return "redirect:/client";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Integer pid, Model model) throws ErrorClient
	{
		Client client = this.verifClient(pid).get();
		model.addAttribute("clientForm", new Client());
		model.addAttribute("client", client);
		model.addAttribute("titre", "Modification de Client");
		return "clients/update";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable("id") Integer pid, Model model,
			@Valid @ModelAttribute("clientForm") Client clientForm) throws ErrorClient
	{
		Client client = this.verifClient(pid).get();
		client.setNom(clientForm.getNom());
		client.setPrenom(clientForm.getPrenom());
		grc.save(client);
		return "redirect:/client";
	}
}
