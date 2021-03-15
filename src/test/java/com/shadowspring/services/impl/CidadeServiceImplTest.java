package com.shadowspring.services.impl;

import com.shadowspring.entity.Cidade;
import com.shadowspring.repository.CidadeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class CidadeServiceImplTest {

    private Cidade cidade;
    private CidadeServiceImpl service;

    @Mock
    CidadeRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new CidadeServiceImpl(repository);
        //repository.deleteAll();
        cidade = Cidade.builder()
                .id(1L)
                .nomeCidade("Tramandai")
                .estado("RS")
                .build();
    }

    @Test
    void findById() {
        Long cidadeId = cidade.getId();
        when(repository.findById(cidade.getId())).thenReturn(Optional.of(cidade));
        cidade = service.findById(cidadeId);

        assertNotNull(cidade);
        assertEquals(cidadeId, cidade.getId());
        verify(repository,times(1)).findById(cidadeId);


    }

    @Test
    void save() {
        Cidade cidade = new Cidade();
        cidade.setNomeCidade("Gravatai");

        when(repository.save(Mockito.any(Cidade.class))).thenReturn(cidade);
        cidade = service.save(cidade);
        assertNotNull(cidade);



    }

    @Test
    void findPage() {
    }

    @Test
    void findByEstado() {
    }

    @Test
    void findByNomeCidade() {
    }

    @Test
    void fromDTO() {
    }
}