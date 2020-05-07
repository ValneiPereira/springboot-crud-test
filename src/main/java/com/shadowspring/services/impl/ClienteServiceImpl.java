package com.shadowspring.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shadowspring.controllers.dto.ClienteDTO;
import com.shadowspring.controllers.dto.ClienteNewDTO;
import com.shadowspring.entity.Cidade;
import com.shadowspring.entity.Cliente;
import com.shadowspring.repository.ClienteRepository;
import com.shadowspring.services.ClienteServices;

@Service
public class ClienteServiceImpl implements ClienteServices {

	@Autowired
	ClienteRepository repository;
	
	@Autowired
	CidadeServiceImpl cidadeService;
	
	

	@Override
	public Cliente findById(Long id) {
		Optional<Cliente> Clientes = repository.findById(id);
		return Clientes.orElse(null);
	}

	@Override
	public Cliente save(Cliente Cliente) {
		return repository.save(Cliente);
	}

	@Override
	public Cliente update(Cliente cliente) {
		Cliente carregaCliente =findById(cliente.getId());
		updateData(carregaCliente,cliente );
		return repository.save(carregaCliente);
	}

	@Override
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);

	}
	
	@Override
	public Page<Cliente> findPage(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	private void updateData(Cliente carregaCliente, Cliente cliente) {
		carregaCliente.setNome(cliente.getNome());
		
	}

	@Override
	public List<Cliente> findByNomeCliente(String cliente) {
 
		return repository.findByNome(cliente);
	}
	
	@Override
	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getSexo(), dto.getDataNascimento(), dto.getIdade(),dto.getCidade());
	}
	
	@Override
	public Cliente fromDTO(ClienteNewDTO dto) {
		Cidade cidade = cidadeService.findById(dto.getCidadeId());
		return new  Cliente (null, dto.getNome(), dto.getSexo(), dto.getDataNascimento(), dto.getIdade(),cidade);
		 
		
	}
	
	
	
	
	
	/*
	 * public Cliente fromDTO(ClienteNewDTO dto) { Cliente cliente = new
	 * Cliente(null, dto.getNome(), dto.getSexo(), dto.getDataNascimento(),
	 * dto.getIdade(), dto.getCidade()); Cidade cidade =
	 * cidadeRepository.findById(dto.getCidadeId()); cliente.getCidade().getId();
	 * return cliente; }
	 */
	 
	

	
	
	
	

}
