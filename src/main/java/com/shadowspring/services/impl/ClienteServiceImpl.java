package com.shadowspring.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
  public Page<Cliente> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

}
