package com.shadowspring.entity;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CidadeTest {

   private Cidade cidade;

    @BeforeEach
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
        String nomeCidade = "Tramandaí";
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
        Cidade x = new Cidade(4L,"Tramandaí","RS");
        Cidade y = new Cidade(4L,"Tramandaí","RS");
        assertTrue(x.equals(y) && y.equals(x));
        assertEquals(x.hashCode(), y.hashCode());
    }

}