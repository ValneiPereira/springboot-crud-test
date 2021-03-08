package com.shadowspring.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CidadeTest {

   private Cidade cidade;

    @Before
    public void setUp(){
        cidade = new Cidade();
    }

    @Test
    public void getId() {
        Long idValor= 4L;
        cidade.setId(idValor);
        assertEquals(idValor,cidade.getId());
    }

    @Test
    public void getNomeCidade() {
        String nomeCidade = "Tramandai";
        cidade.setNomeCidade(nomeCidade);
        assertEquals(nomeCidade,cidade.getNomeCidade());
    }

    @Test
    public void getEstado() {
        String nomeEstado = "Rio Grande do Sul";
        cidade.setEstado(nomeEstado);
        assertEquals(nomeEstado,cidade.getEstado());
    }

    @Test
    public void testEquals_Symmetric() {
        Cidade x = new Cidade(4L,"Tramandai","RS");
        Cidade y = new Cidade(4L,"Tramandai","RS");
        assertTrue(x.equals(y) && y.equals(x));
        assertEquals(x.hashCode(), y.hashCode());
    }

}