
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
class ClienteRepositoryTest {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	private final Sexo SEXO = Sexo.M;
	private final LocalDate DATA_NASCIMENTO = LocalDate.of(1980, Month.APRIL, 3);
	

	private Cliente cliente;

	@BeforeEach
	public void setUp() {

		String NOME_CIDADE = "Cachoeirinha";
		String NOME_ESTADO = "RS";
		Cidade cidade = Cidade.builder()
				.nomeCidade(NOME_CIDADE)
				.estado(NOME_ESTADO)
				.build();
		cidadeRepository.save(cidade);

		String NOME_CLIENTE = "Valnei";
		int IDADE = 40;
		cliente = Cliente.builder()
				.nome(NOME_CLIENTE)
				.sexo(SEXO)
				.idade(IDADE)
				.dataNascimento(DATA_NASCIMENTO)
				.cidade(cidade)
				.build();
	}
	
	@AfterEach
	void tearDown() {
		clienteRepository.deleteAll();
		cidadeRepository.deleteAll();
	}

	@Test
	void testSaveCliente() {
		cliente = clienteRepository.save(cliente);
		assertNotNull(cliente.getId());
	}

	@Test
	void testFindClienteById() {
		cliente.setId(null);
		cliente = clienteRepository.save(cliente);
		Optional<Cliente> response = clienteRepository.findById(cliente.getId());
		assertTrue(response.isPresent());
	}

	@Test
	void testFindAllClientes() {
		cliente = clienteRepository.save(cliente);
		Pageable pageable = PageRequest.of(0, 1);
		assertEquals(1, cidadeRepository.findAll(pageable).getContent().size());
	}
}
