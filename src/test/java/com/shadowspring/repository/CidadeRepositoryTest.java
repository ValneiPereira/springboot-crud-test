package com.shadowspring.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.After;
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
	
	@Autowired
	private ClienteRepository clienteRepository;

	private Cidade cidade;
	
	//private Cliente cliente;

	@Before
	public void setUp() {
		
		//cliente = new Cliente();
		cidade = new Cidade();
		cidade.setNomeCidade(NOME_CIDADE);
		cidade.setEstado(ESTADO);
		cidade =cidadeRepository.save(cidade);
	}
	
	@After
	public void tearDown() {
		clienteRepository.deleteAll();
		cidadeRepository.deleteAll();
	}
	
	@Test
	public void testSave()  {
		cidade = cidadeRepository.save(cidade);
		assertNotNull(cidade.getId());
	}
	
	@Test
	public void testDelete() {
		cidade = cidadeRepository.save(cidade);
		cidadeRepository.delete(cidade);
		assertFalse(cidadeRepository.findById(cidade.getId()).isPresent());
	}
	
	@Test
	public void testFindById() {
		cidade = cidadeRepository.save(cidade);
		Optional<Cidade> response = cidadeRepository.findById(cidade.getId());
		assertTrue(response.isPresent());
	}
	
}
