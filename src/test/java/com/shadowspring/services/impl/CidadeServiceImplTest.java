package com.shadowspring.services.impl;

import com.shadowspring.entity.Cidade;
import com.shadowspring.repository.CidadeRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CidadeServiceImplTest {

    private Cidade cidade;
    private CidadeServiceImpl cidadeService;

    @Mock
    CidadeRepository cidadeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        cidadeService = new CidadeServiceImpl(cidadeRepository);

        cidade = new Cidade();

        cidade = Cidade.builder()
                .id(1L)
                .nomeCidade("Tramandai")
                .estado("RS")
                .build();


    }

    @Test
    void findById() {

        cidade.setId(cidade.getId());


    }

    @Test
    void save() {
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