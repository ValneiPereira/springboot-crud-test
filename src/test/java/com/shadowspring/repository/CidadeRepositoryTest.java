
package com.shadowspring.repository;

import com.shadowspring.entity.Cidade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static com.shadowspring.builders.CidadeBuilder.umaCidade;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class CidadeRepositoryTest {

    private Cidade cidade;

    @Autowired
    private CidadeRepository cidadeRepository;

    @BeforeEach
    public void setUp() {
        cidade = umaCidade().agora();
    }

    @AfterEach
    public void tearDown() {
        cidadeRepository.deleteAll();
    }

    @Test
    void testSave() {
        cidade = cidadeRepository.save(cidade);
        assertNotNull(cidade.getId());
    }

    @Test
    void testDeleteCidade() {
        cidade = cidadeRepository.save(cidade);
        cidadeRepository.delete(cidade);
        assertFalse(cidadeRepository.findById(cidade.getId()).isPresent());
    }

    @Test
    void testFindCidadeById() {
        cidade = cidadeRepository.save(cidade);
        Optional<Cidade> response = cidadeRepository.findById(cidade.getId());
        assertTrue(response.isPresent());
    }

    @Test
    void testFindAllCidades() {
        cidade = cidadeRepository.save(cidade);
        Pageable pageable = PageRequest.of(0, 1);
        assertEquals(1, cidadeRepository.findAll(pageable).getContent().size());
    }
}
