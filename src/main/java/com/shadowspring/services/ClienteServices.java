package com.shadowspring.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shadowspring.entity.Cliente;

public interface ClienteServices {
  
  Cliente save(Cliente cliente);
  
  Page<Cliente> findAll(Pageable pageable);

}
