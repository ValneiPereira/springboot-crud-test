package com.shadowspring.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.shadowspring.entity.Cliente;
import com.shadowspring.repository.ClienteRepository;
import com.shadowspring.services.ClienteServices;
@Service
public class ClienteServiceImpl  implements ClienteServices{
  
  @Autowired
  ClienteRepository repository;  

  @Override
  public Cliente save(Cliente cliente) { 
    return repository.save(cliente);
  }  
  
  @Override
  public List<Cliente> findAll() {
    return repository.findAll();
  }
  
  @Override
  public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
	  PageRequest pageRequest=  PageRequest.of(page,linesPerPage, Direction.valueOf(direction), orderBy);
	  return repository.findAll(pageRequest);
  }
  
}
