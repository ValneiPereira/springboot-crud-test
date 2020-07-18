package com.shadowspring.services;

import java.util.List;

public interface Email {
	boolean enviarEmail(String email, String nome, String mensagem);
	boolean enviarListaEmail(List<String> emails, List<String> nomes, String mensagem);
	boolean enviarEmailMarketing(String email, String nome, String mensagem);
	boolean enviarListaEmailMarketing(List<String>emails,List<String> nomes, String mensagem);

}
