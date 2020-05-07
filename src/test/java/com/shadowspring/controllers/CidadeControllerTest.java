package com.shadowspring.controllers;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.shadowspring.entity.Cidade;
import com.shadowspring.repository.CidadeRepository;

@ActiveProfiles("test")
@SpringBootTest
@RunWith(SpringRunner.class)
public class CidadeControllerTest {
	private  final Long ID = 1L;
	private  final String NOME_CIDADE = "Tapes";
	private  final String ESTADO = "RS";

	@Autowired
	private CidadeRepository cidadeRepository;

	private Cidade cidade;

	@Before
	public void setUp() {
		cidade = new Cidade();
		cidade.setNomeCidade(NOME_CIDADE);
		cidade.setEstado(ESTADO);
		cidadeRepository.save(cidade);
	}
	
	@After
	public void clean(){
		cidadeRepository.deleteAll();
	}
	
	@Test
	public void testSave()  {
		cidade = new Cidade();
		//cidade.setId(1L);
		cidade.setNomeCidade("Chapecó");
		cidade.setEstado("SC");
		
		Cidade response = cidadeRepository.save(cidade);
		
		assertNotNull(response);
	}
	

	
}
