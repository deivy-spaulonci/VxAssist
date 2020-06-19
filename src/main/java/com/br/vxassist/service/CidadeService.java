package com.br.vxassist.service;

import com.br.vxassist.model.Cidade;
import com.br.vxassist.model.Estado;

import java.util.List;

public interface CidadeService {
    public abstract List<Cidade> getCidadeByEstado(Estado estado);
}
