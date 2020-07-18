package com.shadowspring.services.impl;

import java.util.List;

import com.shadowspring.services.Email;

public class EmailService implements Email{

	@Override
	public boolean enviarEmail(String email, String nome, String mensagem) {
		
		return false;
	}

	@Override
	public boolean enviarListaEmail(List<String> emails, List<String> nomes, String mensagem) {
		
		return false;
	}

	@Override
	public boolean enviarEmailMarketing(String email, String nome, String mensagem) {
		
		return false;
	}

	@Override
	public boolean enviarListaEmailMarketing(List<String> emails, List<String> nomes, String mensagem) {
		
		return false;
	}

}
