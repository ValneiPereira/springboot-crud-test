package com.shadowspring.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.shadowspring.entity.Cliente;
import com.shadowspring.enums.Sexo;
import com.shadowspring.repository.ClienteRepository;

@ActiveProfiles("test")
@SpringBootTest
@RunWith(SpringRunner.class)
public class ClienteControllerTest {

	@Autowired
	private ClienteRepository clienteRepository;

	private Cliente cliente;

	@Before
	public void setUp() {
		clienteRepository.deleteAll();

		cliente = new Cliente();
		cliente.setNome("valnei");
		cliente.setSexo(Sexo.M);
		cliente.setIdade(40);
		cliente.setDataNascimento(LocalDate.of(1980, 04, 03));
		cliente.setCidade(null);

	}

	@Test
	public void testFindAll() {
		cliente = clienteRepository.save(cliente);
		List<Cliente> list = clienteRepository.findAll();
		assertTrue(list.contains(cliente));

	}

	@Test
	public void testPage() {
		cliente = clienteRepository.save(cliente);
		Pageable pageable = PageRequest.of(0, 24);
		Page<Cliente> clientes = clienteRepository.findAll(pageable);
		assertEquals(1, clientes.getContent().size());
	}

}
