package com.br.vxassist.controller;

import com.br.vxassist.restcontroller.DespesaRestController;
import com.br.vxassist.service.ServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@ExtendWith(MockitoExtension.class)
public class DespesaControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ServiceInterface serviceInterface;

    @InjectMocks
    private DespesaRestController despesaController;

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
