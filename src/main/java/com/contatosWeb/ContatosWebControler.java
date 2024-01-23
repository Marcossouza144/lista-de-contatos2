package com.contatosWeb;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class ContatosWebControler {

	private static final ArrayList<ContatosWeb> CONTATOS_WEB = new ArrayList<ContatosWeb>();

	static {

		CONTATOS_WEB.add(new ContatosWeb("1", "Marcos", "Souza", "+55 11 98646 3833"));
		CONTATOS_WEB.add(new ContatosWeb("2", "Ana Silva", "AnaS", "+55 11 98765 4321"));
		CONTATOS_WEB.add(new ContatosWeb("3", "Carlos Oliveira", "Carlito", "+55 21 91234 5678"));
		CONTATOS_WEB.add(new ContatosWeb("4", "Sofia Mendes", "Sofi", "+55 31 87654 3210"));
		CONTATOS_WEB.add(new ContatosWeb("5", "Pedro Rocha", "PDR", "+55 41 89012 3456"));
		CONTATOS_WEB.add(new ContatosWeb("6", "Mariana Costa", "MariC", "+55 11 94567 8901"));
		CONTATOS_WEB.add(new ContatosWeb("7", "Rafaela Santos", "Rafa", "+55 21 87654 3210"));
		CONTATOS_WEB.add(new ContatosWeb("8", "Lucas Pereira", "LucasP", "+55 31 95432 6789"));
	

	}

	@GetMapping("/")
	public String index() {

		return "index";

	}

	@GetMapping("/contact")
	public ModelAndView register() {

		ModelAndView modelandview = new ModelAndView("cadastro");

		modelandview.addObject("ContatosWeb", new ContatosWeb());

		return modelandview;

	}

	@GetMapping("/contactList")
	public ModelAndView contactList() {

		ModelAndView modelandview = new ModelAndView("listar");

		modelandview.addObject("ContatosWebs", CONTATOS_WEB);

		return modelandview;
	}

	@PostMapping("/contactList")
	public String identifier(ContatosWeb contato) {

		String id = UUID.randomUUID().toString();

		contato.setId(id);

		CONTATOS_WEB.add(contato);

		return "redirect:/contactList";

	}

	@PostMapping("/list/delete")
	public String remove(@RequestParam String id) {

		if (!CONTATOS_WEB.isEmpty()) {

			CONTATOS_WEB.removeIf(contatosWeb -> contatosWeb.getId().equals(id));

		}

		return "redirect:/contactList";

	}

	@GetMapping("/contact/{id}")
	public ModelAndView edit(@PathVariable String id) {

		ModelAndView modeloDeVisualizacao = new ModelAndView("cadastro");

		ContatosWeb ContatosWeb = searchId(id);

		modeloDeVisualizacao.addObject("ContatosWeb", ContatosWeb);

		return modeloDeVisualizacao;

	}

	@PutMapping("/contactList/{id}")
	public String toUpdate(ContatosWeb contatoNovo) {

		Integer indiceDoContato = searchIndexId(contatoNovo.getId());
		
		ContatosWeb contatoAntigo = CONTATOS_WEB.get(indiceDoContato);
		
		CONTATOS_WEB.remove(contatoAntigo);
		
		CONTATOS_WEB.add(indiceDoContato, contatoNovo);
		
		return "redirect:/contactList";
		
		
	}

	private ContatosWeb searchId(String id) {

		Integer indiceDoContato = searchIndexId(id);

		if (indiceDoContato != null) {

			ContatosWeb contato = CONTATOS_WEB.get(indiceDoContato);

			return contato;

		}

		return null;

	}

	private Integer searchIndexId(String id) {

		for (int i = 0; i < CONTATOS_WEB.size(); i++) {

			ContatosWeb contatoId = CONTATOS_WEB.get(i);

			if (contatoId.getId().equals(id)) { 

				return i;

			}
		}

		return null;
	}
}