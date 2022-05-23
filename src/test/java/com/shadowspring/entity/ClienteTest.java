package com.shadowspring.entity;

import com.shadowspring.enums.Sexo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    public void setUp(){
        cliente = new Cliente();
    }

    @Test
    void getId() {
        Long idValor= 4L;
        cliente.setId(idValor);
        assertEquals(idValor,cliente.getId());
    }

    @Test
    void getNome() {
        String nomeCliente = "valnei";
        cliente.setNome(nomeCliente);
        assertEquals(nomeCliente,cliente.getNome());
    }

    @Test
    void getSexo() {
        Sexo sexo = Sexo.M;
        cliente.setSexo(Sexo.M);
        assertEquals(sexo,cliente.getSexo());
    }

    @Test
    void getDataNascimento() {
        LocalDate dataNascimento = LocalDate.of(1980, 4, 3);
        cliente.setDataNascimento(dataNascimento);
        assertEquals(dataNascimento,cliente.getDataNascimento());
    }

    @Test
    void getIdade(){
        Integer idade = 41;
        cliente.setIdade(idade);
        assertEquals(idade,cliente.getIdade());
    }

    @Test
    void getCidade(){
        Cidade  cidade= new Cidade(1L,"Tramandai","RS");
        cliente.setCidade(cidade);
        assertEquals(cidade,cliente.getCidade());
    }

    @Test
    void testEquals_Symmetric() {
        Cidade  cidade= new Cidade(1L,"Tramandai","RS");
        LocalDate dataNascimento = LocalDate.of(1980, 4, 3);
        Cliente x = new Cliente(3L,"Valnei",Sexo.M,dataNascimento,new BigDecimal("08.800"),41,cidade);
        Cliente y = new Cliente(3L,"Valnei",Sexo.M,dataNascimento,new BigDecimal("08.800"),41,cidade);
        Assertions.assertTrue(x.equals(y) && y.equals(x));
        assertEquals(x.hashCode(), y.hashCode());
    }
}