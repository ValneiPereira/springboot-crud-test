package com.shadowspring.util;

import com.shadowspring.entity.Cidade;
import com.shadowspring.entity.Cliente;
import com.shadowspring.enums.Sexo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static com.shadowspring.builders.CidadeBuilder.umaCidade;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@ActiveProfiles("test")
class ExcelExporterTest {

    private List<Cliente> listClientes;

    @InjectMocks
    private ExcelExporter excelExporter;

    @BeforeEach
    public void setUp() {

        Cidade cidade = umaCidade().agora();

        Cliente cliente = Cliente.builder()
                .id(1L)
                .nome("Valnei")
                .sexo(Sexo.M)
                .dataNascimento(LocalDate.of(1980, Month.APRIL, 3))
                .idade(40)
                .cidade(cidade)
                .vrRentabilidade(new BigDecimal(10))
                .build();

        listClientes = new ArrayList<>();
        listClientes.add(cliente);

    }

    @Test
    void export() throws IOException {
        excelExporter = new ExcelExporter(listClientes);
        excelExporter.export();
        assertNotNull(excelExporter);

    }
}
