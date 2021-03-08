package com.shadowspring.entity;

import com.shadowspring.enums.Sexo;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class ClienteTest {

    private Cliente cliente;

    @Before
    public void setUp(){
        cliente = new Cliente();
    }

    @Test
    public void getId() {
        Long idValor= 4L;
        cliente.setId(idValor);
        assertEquals(idValor,cliente.getId());
    }

    @Test
    public void getNome() {
        String nomeCliente = "valnei";
        cliente.setNome(nomeCliente);
        assertEquals(nomeCliente,cliente.getNome());
    }

    @Test
    public void getSexo() {
        Sexo sexo = Sexo.M;
        cliente.setSexo(Sexo.M);
        assertEquals(sexo,cliente.getSexo());
    }

    @Test
    public void getDataNascimento() {
        LocalDate dataNascimento = LocalDate.of(1980, 4, 3);
        cliente.setDataNascimento(dataNascimento);
        assertEquals(dataNascimento,cliente.getDataNascimento());
    }

    @Test
    public void getIdade(){
        Integer idade = 41;
        cliente.setIdade(idade);
        assertEquals(idade,cliente.getIdade());
    }

    @Test
    public void getCidade(){
        Cidade  cidade= new Cidade(1L,"Tramandai","RS");
        cliente.setCidade(cidade);
        assertEquals(cidade,cliente.getCidade());
    }

    @Test
    public void testEquals_Symmetric() {
        Cidade  cidade= new Cidade(1L,"Tramandai","RS");
        LocalDate dataNascimento = LocalDate.of(1980, 4, 3);
        Cliente x = new Cliente(3L,"Valnei",Sexo.M,dataNascimento,41,cidade);
        Cliente y = new Cliente(3L,"Valnei",Sexo.M,dataNascimento,41,cidade);
        assertTrue(x.equals(y) && y.equals(x));
        assertEquals(x.hashCode(), y.hashCode());
    }
}