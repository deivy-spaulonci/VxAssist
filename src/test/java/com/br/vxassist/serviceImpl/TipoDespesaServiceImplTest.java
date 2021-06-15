package com.br.vxassist.serviceImpl;

import com.br.vxassist.builder.TipoDespesaDTOBuilder;
import com.br.vxassist.mapper.TipoDespesaMapper;
import com.br.vxassist.repository.TipoDespesaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TipoDespesaServiceImplTest {

    private final TipoDespesaMapper tipoDespesaMapper  = TipoDespesaMapper.INSTANCE;

    @Mock
    private TipoDespesaRepository tipoDespesaRepository;

    @InjectMocks
    private TipoDespesaServiceImpl tipoDespesaService;

    private TipoDespesaDTOBuilder tipoDespesaDTOBuilder;

    @BeforeEach
    void setup(){
        tipoDespesaDTOBuilder = TipoDespesaDTOBuilder.builder().build();
    }

}
