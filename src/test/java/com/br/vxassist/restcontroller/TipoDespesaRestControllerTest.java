package com.br.vxassist.restcontroller;

import com.br.vxassist.builder.TipoDespesaDTOBuilder;
import com.br.vxassist.dto.TipoDespesaDTO;
import com.br.vxassist.serviceImpl.TipoDespesaServiceImpl;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static com.br.vxassist.util.JsonConversionUtils.asJsonString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TipoDespesaRestControllerTest {

    private static final String TIPODESPESA_API_URL_PATH = "/api/v1/tipo-despesa";

    @Mock
    private TipoDespesaServiceImpl tipoDespesaService;

    @InjectMocks
    private TipoDespesaRestController tipoDespesaRestController;

    private MockMvc mockMvc;

    private TipoDespesaDTOBuilder tipoDespesaDTOBuilder;

    @BeforeEach
    void setup() {
        tipoDespesaDTOBuilder = TipoDespesaDTOBuilder.builder().build();
        mockMvc = MockMvcBuilders.standaloneSetup(tipoDespesaRestController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test//para o codigo 201
    void whenPostIsCalledThenStatusCreatedShouldBeReturned() throws Exception {
        TipoDespesaDTO expectedCreatedTipoDespesaDTO = tipoDespesaDTOBuilder.buildTipoDespesaDTO();

        when(tipoDespesaService.create(expectedCreatedTipoDespesaDTO)).thenReturn(expectedCreatedTipoDespesaDTO);

        mockMvc.perform(post(TIPODESPESA_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(expectedCreatedTipoDespesaDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Is.is(expectedCreatedTipoDespesaDTO.getId().intValue())))
                .andExpect(jsonPath("$.nome", Is.is(expectedCreatedTipoDespesaDTO.getNome())));
    }


    @Test// para o codigo 400
    void whenPostIsCalledWhitoutRequiredFieldThenBadRequestStatusShouldBeInformed() throws Exception {
        TipoDespesaDTO expectedCreatedTipoDespesaDTO = tipoDespesaDTOBuilder.buildTipoDespesaDTO();
        expectedCreatedTipoDespesaDTO.setNome(null);

        mockMvc.perform(post(TIPODESPESA_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(expectedCreatedTipoDespesaDTO)))
                .andExpect(status().isBadRequest());
    }
}
