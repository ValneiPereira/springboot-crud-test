package com.shadowspring.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shadowspring.entity.Cliente;
import com.shadowspring.dto.ClienteDTO;
import com.shadowspring.dto.ClienteNovoDTO;

public interface ClienteServices {
	
	Cliente save(Cliente cliente);
	Cliente findById(Long id);
	Cliente update(Cliente cliente);
	void delete(Long id);
	Page<Cliente> findPage(Pageable pageable);
	Cliente fromDTO(ClienteDTO dto);
	Cliente fromDTO(ClienteNovoDTO dto);
	List<Cliente> findByNomeCliente(String cliente);
	List<Cliente> listAll();
	Cliente buscarCliente(Long id);
}
