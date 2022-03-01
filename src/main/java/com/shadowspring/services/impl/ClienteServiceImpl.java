package com.shadowspring.services.impl;

import com.shadowspring.dto.ClienteDTO;
import com.shadowspring.dto.ClienteNovoDTO;
import com.shadowspring.entity.Cidade;
import com.shadowspring.entity.Cliente;
import com.shadowspring.exceptions.EntidadeNaoEncontradaException;
import com.shadowspring.repository.ClienteRepository;
import com.shadowspring.services.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteServices {

	@Autowired
	ClienteRepository repository;
	
	@Autowired
	CidadeServiceImpl cidadeService;

	@Override
	public Cliente findById(Long id) {
		return repository.findById(id).orElseThrow(()-> new EntidadeNaoEncontradaException("Cliente não encontrado"));
	}

	public Cliente buscarCliente(Long id){
		return repository.findById(id).orElseThrow(()-> new EntidadeNaoEncontradaException(String.format("Cliente não encontrado com este código %d", id)));
	}

	@Override
	public Cliente save(Cliente cliente) {
		return repository.save(cliente);
	}

	@Override
	public Cliente update(Cliente cliente) {
		Cliente carregaCliente =findById(cliente.getId());
		updateData(carregaCliente,cliente );
		return repository.save(carregaCliente);

	}

	@Override
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch (EmptyResultDataAccessException e){
			throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro com este código %d", id));
		}
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
	public List<Cliente> listAll() {
		return repository.findAll(Sort.by("id").ascending());
	}

	@Override
	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getSexo(), dto.getDataNascimento(), dto.getVrRentabilidade(),dto.getIdade(),dto.getCidade());
	}
	
	@Override
	public Cliente fromDTO(ClienteNovoDTO dto) {
		Cidade cidade = cidadeService.findById(dto.getCidadeId());
		return new  Cliente (null, dto.getNome(), dto.getSexo(), dto.getDataNascimento(),dto.getVrRentabilidade(), dto.getIdade(),cidade);
	}

}
