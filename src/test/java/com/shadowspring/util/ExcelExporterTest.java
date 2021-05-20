package com.shadowspring.util;

import com.shadowspring.entity.Cidade;
import com.shadowspring.entity.Cliente;
import com.shadowspring.enums.Sexo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ExcelExporterTest {

    private Cliente cliente;
    private Cidade cidade;
    private List<Cliente> listClientes;

    @InjectMocks
    private ExcelExporter excelExporter;

    @Before
    public void setUp(){
        cidade = Cidade.builder()
                .id(1L)
                .nomeCidade("Tramandaí")
                .estado("RS")
                .build();

        cliente = Cliente.builder()
                .id(1L)
                .nome("Valnei")
                .sexo(Sexo.M)
                .dataNascimento(LocalDate.of(1980, Month.APRIL, 03))
                .idade(40)
                .cidade(cidade)
                .vrRentabilidade(new BigDecimal(10))
                .build();

        listClientes = new ArrayList<>();
        listClientes.add(cliente);

    }

    @Test
    public void export() throws IOException {
        excelExporter = new ExcelExporter(listClientes);
        excelExporter.export();
        assertNotNull(excelExporter);

    }
}
