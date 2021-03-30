package com.br.vxassist.restcontroller;

import com.br.vxassist.dto.CidadeDTO;
import com.br.vxassist.filter.CidadeFilter;
import com.br.vxassist.model.Cidade;
import com.br.vxassist.serviceImpl.CidadeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/cidade")
public class CidadeRestController implements Serializable {

    @Autowired
    private final CidadeServiceImpl cidadeServiceImpl;

    @Autowired
    public CidadeRestController(CidadeServiceImpl cidadeServiceImpl){
        super();
        this.cidadeServiceImpl = cidadeServiceImpl;
    }

    @GetMapping("/page")
    public Page<CidadeDTO> getPage(@ModelAttribute CidadeFilter cidadeFitler,
                                   Pageable pageable){
        return this.cidadeServiceImpl.getPage(cidadeFitler, pageable);

    }

    @GetMapping()
    public List<CidadeDTO> get(@ModelAttribute CidadeFilter cidadeFitler, Sort sort){
        return this.cidadeServiceImpl.get(cidadeFitler,sort);
    }

    @GetMapping("/{id}")
    public CidadeDTO findById(@PathVariable Long id){
        return this.cidadeServiceImpl.findCidadeById(id);
    }


}
