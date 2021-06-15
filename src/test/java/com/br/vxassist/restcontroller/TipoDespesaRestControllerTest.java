package com.br.vxassist.restcontroller;

import com.br.vxassist.builder.TipoDespesaDTOBuilder;
import com.br.vxassist.serviceImpl.TipoDespesaServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@ExtendWith(MockitoExtension.class)
public class TipoDespesaRestControllerTest {

    @Mock
    private TipoDespesaServiceImpl tipoDespesaService;

    @InjectMocks
    private TipoDespesaRestController tipoDespesaRestController;

    private MockMvc mockMvc;

    private TipoDespesaDTOBuilder tipoDespesaDTOBuilder;

    @Before
    void setup(){
        tipoDespesaDTOBuilder = TipoDespesaDTOBuilder.builder().build();
        mockMvc = MockMvcBuilders.standaloneSetup(tipoDespesaRestController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

}
