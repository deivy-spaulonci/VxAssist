package com.br.vxassist.serviceImpl;

import com.br.vxassist.dto.EstadoDTO;
import com.br.vxassist.mapper.EstadoMapper;
import com.br.vxassist.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoServiceImpl {

    @Autowired
    private EstadoRepository estadoRepository;

    private final EstadoMapper estadoMapper = EstadoMapper.INSTANCE;

    public List<EstadoDTO> get() {
        return estadoMapper.toEstadoDtoList(estadoRepository.findAll(Sort.by("nome").ascending()));
    }
}
