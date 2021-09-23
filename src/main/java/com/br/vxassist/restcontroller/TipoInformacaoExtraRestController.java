package com.br.vxassist.restcontroller;

import com.br.vxassist.dto.TipoInformacaoExtraDTO;
import com.br.vxassist.filter.TipoFilter;
import com.br.vxassist.serviceImpl.TipoInformacaoExtraServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tipo-informacao-extra")
public class TipoInformacaoExtraRestController implements Serializable {
    @Autowired
    private final TipoInformacaoExtraServiceImpl tipoInformacaoExtraServiceImpl;

    @Autowired
    public TipoInformacaoExtraRestController(TipoInformacaoExtraServiceImpl tipoInformacaoExtraServiceImpl){
        super();
        this.tipoInformacaoExtraServiceImpl = tipoInformacaoExtraServiceImpl;
    }

    @GetMapping()
    public List<TipoInformacaoExtraDTO> get(@ModelAttribute TipoFilter tipoFilter){
        return tipoInformacaoExtraServiceImpl.get(tipoFilter, Sort.by("nome").ascending());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoInformacaoExtraDTO create(@RequestBody @Valid TipoInformacaoExtraDTO tipoInformacaoExtraDTO) {
        return tipoInformacaoExtraServiceImpl.create(tipoInformacaoExtraDTO);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody TipoInformacaoExtraDTO tipoInformacaoExtraDTO){
        try{
            this.tipoInformacaoExtraServiceImpl.update(tipoInformacaoExtraDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex) {
            throw new EntityNotFoundException("Id do Tipo de Informação Extra não encontrado!");
        }
    }

}
