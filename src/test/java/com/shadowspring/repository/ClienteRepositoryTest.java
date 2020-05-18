
package com.shadowspring.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.shadowspring.entity.Cidade;
import com.shadowspring.entity.Cliente;
import com.shadowspring.enums.Sexo;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ClienteRepositoryTest {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	private final String NOME_CIDADE = "Cachoeirinha";
	private final String NOME_ESTADO = "RS";
	private final String NOME_CLIENTE = "Valnei";
	private final Sexo SEXO = Sexo.M;
	private final int IDADE = 40;
	private final LocalDate DATA_NASCIMENTO = LocalDate.of(1980, Month.APRIL, 03);
	

	private Cliente cliente;
	private Cidade cidade;

	@Before
	public void setUp() {
		

		cidade = new Cidade();
		cidade.setNomeCidade(NOME_CIDADE);
		cidade.setEstado(NOME_ESTADO);
		cidadeRepository.save(cidade);

		cliente = new Cliente();
		cliente.setNome(NOME_CLIENTE);
		cliente.setSexo(SEXO);
		cliente.setIdade(IDADE);
		cliente.setDataNascimento(DATA_NASCIMENTO);
		cliente.setCidade(cidade);

	}
	
	@After
	public void tearDown() {
		clienteRepository.deleteAll();
		cidadeRepository.deleteAll();
	}

	@Test
	public void testSaveCliente() {
		cliente = clienteRepository.save(cliente);
		assertNotNull(cliente.getId());
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void testSaveClienteNomeNull() {
		cliente.setNome(null);
		cliente = clienteRepository.save(cliente);
	}

	@Test
	public void testFindClienteById() {
		cliente.setId(null);
		cliente = clienteRepository.save(cliente);
		Optional<Cliente> response = clienteRepository.findById(cliente.getId());
		assertTrue(response.isPresent());
	}

	@Test
	public void testFindAllClientes() {
		cliente = clienteRepository.save(cliente);
		Pageable pageable = PageRequest.of(0, 1);
		assertEquals(1, cidadeRepository.findAll(pageable).getContent().size());
	}

}
