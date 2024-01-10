package com.contatosWeb;

public class ContatosWeb {

	private String id;

	private String usuario;
	
	private String apelido;

	private String telefone;

	
	
	public ContatosWeb () {
		
	}
	
	public ContatosWeb (String id,String usuario,String apelido, String telefone ) {
		
		this.id = id;
		this.usuario = usuario;
		this.apelido = apelido;
		this.telefone = telefone;
		
	}
	
	
	public boolean isNovo() {
		
	    return id == null;
	    
	}
	
	public String getId() {
		
		return id;
		
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}