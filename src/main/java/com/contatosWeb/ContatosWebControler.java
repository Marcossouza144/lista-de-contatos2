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

	@GetMapping ("/contact")
	public ModelAndView register() {

		ModelAndView modelandview = new ModelAndView("cadastro");

		modelandview.addObject("contatosWeb", new ContatosWeb());
		
		return modelandview;
		
	}

	@GetMapping("/listContact")
	public ModelAndView contactList() {

		ModelAndView modelandview = new ModelAndView("listar");

		modelandview.addObject("contatosWebs", CONTATOS_WEB);

		return modelandview;
	}

	@PostMapping("/register")
	public String addContact(ContatosWeb contatosWeb) {
		
	    String id = UUID.randomUUID().toString();

	    contatosWeb.setId(id);

	    CONTATOS_WEB.add(contatosWeb);

	    return "redirect:/listContact";
	    
	}
	
	@PostMapping("/listar/delete")
	public String remove(@RequestParam String id) {
		
		if (!CONTATOS_WEB.isEmpty()) {
			
			CONTATOS_WEB.removeIf(contato -> contato.getId().equals(id));

		}

		return "redirect:/listContact";

	}
	
	@GetMapping ("/contact/{id}")
	public ModelAndView edit (@PathVariable String id) {
		
		ModelAndView modelandview = new ModelAndView("cadastro");
		
		ContatosWeb contatosWeb = searchContact(id);
		
		modelandview.addObject("contato", contatosWeb);
		
		return modelandview;
		
	}
	
	private ContatosWeb searchContact (String id) {
		
		for (int i = 0 ; i < CONTATOS_WEB.size(); i++) {
			
			ContatosWeb contatosweb = CONTATOS_WEB.get(i);
		
			if (contatosweb.getId().equals(id)) {
				
				return contatosweb;
				
			}
			
		}
		
		return null;
		
	}
}
