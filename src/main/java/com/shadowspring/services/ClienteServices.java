package com.shadowspring.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shadowspring.controllers.dto.ClienteDTO;
import com.shadowspring.controllers.dto.ClienteNewDTO;
import com.shadowspring.entity.Cliente;

public interface ClienteServices {
	
	Cliente save(Cliente cliente);
	Cliente findById(Long id);
	Cliente update(Cliente cliente);
	void delete(Long id);
	Page<Cliente> findPage(Pageable pageable);
	Cliente fromDTO(ClienteDTO dto);
	Cliente fromDTO(ClienteNewDTO dto);
	List<Cliente> findByNomeCliente(String cliente);


}
