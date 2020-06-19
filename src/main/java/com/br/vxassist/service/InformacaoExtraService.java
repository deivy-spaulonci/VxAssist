package com.br.vxassist.service;

import com.br.vxassist.model.Estado;
import com.br.vxassist.model.ExtratoDescricao;
import com.br.vxassist.model.InformacaoExtra;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InformacaoExtraService {
    public abstract InformacaoExtra save(InformacaoExtra informacaoExtra);
}
