
package com.shadowspring.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

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

@ActiveProfiles("test")

@SpringBootTest

@RunWith(SpringRunner.class)
public class CidadeRepositoryTest {

	private final String NOME_CIDADE = "Tapes";
	private final String ESTADO = "RS";

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	private Cidade cidade;

	@Before
	public void setUp() {

		clienteRepository.deleteAll();
		cidadeRepository.deleteAll();

		cidade = new Cidade();
		cidade.setNomeCidade(NOME_CIDADE);
		cidade.setEstado(ESTADO);
		cidade = cidadeRepository.save(cidade);
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
