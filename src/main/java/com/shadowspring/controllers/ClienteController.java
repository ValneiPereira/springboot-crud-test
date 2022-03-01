package com.shadowspring.controllers;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import com.shadowspring.dto.ExcelDTO;
import com.shadowspring.exceptions.EntidadeNaoEncontradaException;
import com.shadowspring.util.ExcelExporter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shadowspring.entity.Cliente;
import com.shadowspring.dto.ClienteDTO;
import com.shadowspring.dto.ClienteNovoDTO;
import com.shadowspring.services.ClienteServices;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteServices services;


    @GetMapping(value = "/{id}")
    public Cliente findById(@PathVariable Long id) {
        return services.buscarCliente(id);
    }

    @GetMapping()
    public ResponseEntity<?> findPage(Pageable pageable) {
        Page<Cliente> clientes = services.findPage(pageable);
        Page<ClienteDTO> clientesDtoPage = clientes.map(ClienteDTO::new);
        return ResponseEntity.ok().body(clientesDtoPage);
    }

    @PostMapping
    public ResponseEntity<Cliente> insert(@RequestBody ClienteNovoDTO dto) {
        Cliente cliente = services.fromDTO(dto);
        cliente = services.save(cliente);
        //Disponibiliza link
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
        //Disponibiliza corpo
        //return ResponseEntity.status(HttpStatus.CREATED).body(cliente);

    }

    @PutMapping(value = "/{id}")
    public Cliente update(@RequestBody ClienteDTO dto, @PathVariable Long id) {
        Cliente cliente = services.fromDTO(dto);
        Cliente clienteAtual = services.buscarCliente(id);

        BeanUtils.copyProperties(cliente, clienteAtual,"id");

        return services.save(clienteAtual);

    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
            services.delete(id);
    }

    @GetMapping(value = "/nome-cliente/{cliente}")
    public ResponseEntity<?> findByCliente(@PathVariable String cliente) {
        List<Cliente> clientes = services.findByNomeCliente(cliente);
        return ResponseEntity.ok().body(clientes);
    }

    @GetMapping(value = "/export/excel")
    public ResponseEntity<ExcelDTO> exportToExcel() throws IOException {
        List<Cliente> listClientes = services.listAll();
        ExcelExporter excelExporter = new ExcelExporter(listClientes);
        return ResponseEntity.ok().body(ExcelDTO.builder().base64(excelExporter.export()).build());
    }
}