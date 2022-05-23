package com.shadowspring.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String campoNome;
	private String erro;

	public FieldMessage(String campoNome, String erro) {
		
		this.campoNome = campoNome;
		this.erro = erro;
	}

	public String getCampoNome() {
		return campoNome;
	}

	public void setCampoNome(String campoNome) {
		this.campoNome = campoNome;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	
}
