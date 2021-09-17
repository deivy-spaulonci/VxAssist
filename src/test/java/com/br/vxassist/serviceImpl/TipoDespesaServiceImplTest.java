package com.br.vxassist.serviceImpl;

import com.br.vxassist.builder.TipoDespesaDTOBuilder;
import com.br.vxassist.dto.TipoDespesaDTO;
import com.br.vxassist.exception.TipoAlreadyExistsException;
import com.br.vxassist.mapper.TipoDespesaMapper;
import com.br.vxassist.model.TipoDespesa;
import com.br.vxassist.repository.TipoDespesaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

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

//    @Test
//    void whenNewTipoDespesaInformedThenItShouldBeCreated() {
//
//        //given
//        TipoDespesaDTO expectedTipoDespesaToCreateDTO = tipoDespesaDTOBuilder.buildTipoDespesaDTO();
//        TipoDespesa expectedCreateTipoDespesa = tipoDespesaMapper.toModel(expectedTipoDespesaToCreateDTO);
//
//        //when
//        when(tipoDespesaRepository.save(expectedCreateTipoDespesa)).thenReturn(expectedCreateTipoDespesa);
//        when(tipoDespesaRepository.findByNome(expectedCreateTipoDespesa.getNome())).thenReturn(Optional.empty());
//        TipoDespesaDTO createdTipoDespesaDTO = tipoDespesaService.create(expectedTipoDespesaToCreateDTO);
//
//        //then
//        assertThat(createdTipoDespesaDTO, is(equalTo(expectedTipoDespesaToCreateDTO)));
//    }
//
//    @Test
//    void whenExistsTipoDespesaIsInformedThenAnExceptionShouldBeThrown() {
//
//        TipoDespesaDTO expectedTipoDespesaToCreateDTO = tipoDespesaDTOBuilder.buildTipoDespesaDTO();
//        TipoDespesa expectedCreateTipoDespesa = tipoDespesaMapper.toModel(expectedTipoDespesaToCreateDTO);
//
//        when(tipoDespesaRepository.findByNome(expectedCreateTipoDespesa.getNome())).thenReturn(
//                Optional.of(expectedCreateTipoDespesa));
//
//        assertThrows(TipoAlreadyExistsException.class, () -> tipoDespesaService.create(expectedTipoDespesaToCreateDTO));
//    }
}
