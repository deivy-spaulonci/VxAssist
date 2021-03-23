package com.br.vxassist.controller;

import com.br.vxassist.restcontroller.DespesaController;
import com.br.vxassist.service.DespesaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@ExtendWith(MockitoExtension.class)
public class DespesaControllerTest {
    private MockMvc mockMvc;

    @Mock
    private DespesaService despesaService;

    @InjectMocks
    private DespesaController despesaController;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(despesaController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) ->new MappingJackson2JsonView())
                .build();
    }

    @Test
    void testWhenPOStisCalledThenDespesaShouldBeCreated(){

    }
}
