package com.br.vxassist.service;

import com.br.vxassist.model.Estado;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EstadoService {
    public abstract List<Estado> getAll();
}