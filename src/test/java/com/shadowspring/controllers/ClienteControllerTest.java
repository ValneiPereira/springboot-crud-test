package com.shadowspring.controllers;

import static org.junit.Assert.assertEquals;

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
		cliente.setCidade(null);

	}
	
	@Test
	public void testPagina() {
		cliente = clienteRepository.save(cliente);
		int pageSize = 10;
		Pageable pageable = PageRequest.of(0, pageSize);
		Page<Cliente> assembleias = clienteRepository.findAll(pageable);
		assertEquals(1, assembleias.getContent().size());
	}

}
