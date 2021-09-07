
package com.shadowspring.repository;

import com.shadowspring.entity.Cidade;
import com.shadowspring.entity.Cliente;
import com.shadowspring.enums.Sexo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
	private final LocalDate DATA_NASCIMENTO = LocalDate.of(1980, Month.APRIL, 3);
	

	private Cliente cliente;

	@BeforeEach
	public void setUp() {

		Cidade cidade = Cidade.builder()
				.nomeCidade(NOME_CIDADE)
				.estado(NOME_ESTADO)
				.build();
		cidadeRepository.save(cidade);

		cliente = Cliente.builder()
				.nome(NOME_CLIENTE)
				.sexo(SEXO)
				.idade(IDADE)
				.dataNascimento(DATA_NASCIMENTO)
				.cidade(cidade)
				.build();
	}
	
	@AfterEach
	public void tearDown() {
		clienteRepository.deleteAll();
		cidadeRepository.deleteAll();
	}

	@Test
	public void testSaveCliente() {
		cliente = clienteRepository.save(cliente);
		assertNotNull(cliente.getId());
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
