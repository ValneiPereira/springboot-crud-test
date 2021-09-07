package com.shadowspring.controllers;

import static com.shadowspring.builders.CidadeBuilder.umaCidade;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shadowspring.entity.Cidade;
import com.shadowspring.exceptions.BadRequestException;
import com.shadowspring.repository.CidadeRepository;
import com.shadowspring.repository.ClienteRepository;
import com.shadowspring.dto.CidadeDTO;
import com.shadowspring.services.CidadeServices;
import com.shadowspring.services.ClienteServices;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CidadeControllerTest {


	private  final String NOME_CIDADE = "Tramandaí";
	private  final String ESTADO = "RS";
	private  final String URL = "/cidades";

	private Cidade cidade;
	
	private CidadeDTO cidadeDTO;

	@MockBean
	private CidadeServices cidadeService;
	
	@MockBean
	private ClienteServices clienteService;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	MockMvc mvc;

	@BeforeEach
	public void setUp() {
		cidade = umaCidade().agora();

		cidadeDTO = new CidadeDTO();
		cidadeDTO.setNomeCidade(NOME_CIDADE);
		cidadeDTO.setEstado(ESTADO);
 
	}
	
	@AfterEach
	public void tearDown() {
		clienteRepository.deleteAll();
		cidadeRepository.deleteAll();
	} 
	

	@Test
	public void testSalvarCidadeValida() throws Exception {

		when(cidadeService.fromDTO(cidadeDTO)).thenReturn(cidade);
		when(cidadeService.save(any())).thenReturn(cidade);
		mvc.perform(MockMvcRequestBuilders.post(URL)
				.content(asJsonString(cidadeDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void testSaveCidadeNomeNull() throws Exception {
		cidadeDTO.setNomeCidade(null);
		
		mvc.perform(MockMvcRequestBuilders.post(URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(cidadeDTO)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$").isNotEmpty());
	}
	
	@Test
	public void testSaveCidadeMesmoNome() throws Exception {
		cidadeDTO = new CidadeDTO();
		cidadeDTO.setId(2L);
		cidadeDTO.setNomeCidade("Tramandaí");
		cidadeDTO.setEstado(ESTADO);
		
		  when(cidadeService.fromDTO(cidadeDTO)).thenReturn(cidade);
		  when(cidadeService.save(any())).thenThrow(BadRequestException.class);
		 
		
		mvc.perform(MockMvcRequestBuilders.post(URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(cidadeDTO)))
				.andExpect(status().isBadRequest());
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
