package com.shadowspring.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.shadowspring.entity.Cidade;

@ActiveProfiles("test")
@SpringBootTest
@RunWith(SpringRunner.class)
public class CidadeRepositoryTest {
	
	private  final String NOME_CIDADE = "Tapes";
	private  final String ESTADO = "RS";

	@Autowired
	private CidadeRepository cidadeRepository;

	private Cidade cidade;

	@Before
	public void setUp() {
		cidadeRepository.deleteAll();
		
		cidade = new Cidade();
		cidade.setNomeCidade(NOME_CIDADE);
		cidade.setEstado(ESTADO);
		
		cidade =cidadeRepository.save(cidade);
	}
	
	
	
	@Test
	public void testSave()  {
		
		cidade = cidadeRepository.save(cidade);
		assertNotNull(cidade.getId());
		
	}
	
	
	

	
}
