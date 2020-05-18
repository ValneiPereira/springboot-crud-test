
package com.shadowspring.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.shadowspring.entity.Cidade;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class CidadeRepositoryTest {
	
	private  final Long ID = 1L;
	private  final String NOME_CIDADE = "Tramandaí";
	private  final String ESTADO = "RS";
	
	private Cidade cidade;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	


	@Before
	public void setUp() {
		cidade = new Cidade();
		
		cidade.setNomeCidade(NOME_CIDADE);
		cidade.setEstado(ESTADO);
		cidade = cidadeRepository.save(cidade);
	}
	
	@After
	public void tearDown() {
		clienteRepository.deleteAll();
		cidadeRepository.deleteAll();
	}

	@Test
	public void testSave() {
		cidade = cidadeRepository.save(cidade);
		assertNotNull(cidade.getId());
	}

	@Test
	public void testDeleteCidade() {
		cidade = cidadeRepository.save(cidade);
		cidadeRepository.delete(cidade);
		assertFalse(cidadeRepository.findById(cidade.getId()).isPresent());
	}

	@Test
	public void testFindCidadeById() {
		cidade = cidadeRepository.save(cidade);
		Optional<Cidade> response = cidadeRepository.findById(cidade.getId());
		assertTrue(response.isPresent());
	}

	@Test
	public void testFindAllCidades() {
		cidade = cidadeRepository.save(cidade);
		Pageable pageable = PageRequest.of(0, 1);
		assertEquals(1, cidadeRepository.findAll(pageable).getContent().size());
	}

}
