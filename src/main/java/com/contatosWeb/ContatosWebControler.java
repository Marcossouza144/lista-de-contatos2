package com.contatosWeb;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping("/cadastro")
	public ModelAndView cadastros() {

		ModelAndView modelandview = new ModelAndView("cadastro");

		modelandview.addObject("contatosWeb", new ContatosWeb());

		return modelandview;
	}

	@GetMapping("/listar")
	public ModelAndView exemplosDeCadastros() {

		ModelAndView modelandview = new ModelAndView("listar");

		modelandview.addObject("contatosWebs", CONTATOS_WEB);

		return modelandview;
	}

	@PostMapping("/listar")
	public String cadastrarContato(ContatosWeb contatosWeb) {
	    // 1. Gera um ID único usando UUID
	    String id = UUID.randomUUID().toString();

	    // 2. Define o ID gerado para o objeto contatosWeb
	    contatosWeb.setId(id);

	    // 3. Adiciona o objeto contatosWeb à lista CONTATOS_WEB
	    CONTATOS_WEB.add(contatosWeb);

	    // 4. Retorna um redirecionamento para a página de listagem
	    return "redirect:/listar";
	}

	@PostMapping("/listar/delete")
	public String remover(@RequestParam String id) {
		if (!CONTATOS_WEB.isEmpty()) {
			CONTATOS_WEB.removeIf(contato -> contato.getId().equals(id));

		}

		return "redirect:/listar";

	}
}