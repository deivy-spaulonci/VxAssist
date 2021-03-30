package com.br.vxassist.restcontroller;

import com.br.vxassist.dto.FormaPagamentoDTO;
import com.br.vxassist.model.FormaPagamento;
import com.br.vxassist.serviceImpl.FormaPagamentoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/forma-pagamento")
public class FormaPagamentoRestController implements Serializable {


    @Autowired
    private final FormaPagamentoServiceImpl formaPagamentoServiceImpl;

    @Autowired
    public FormaPagamentoRestController(FormaPagamentoServiceImpl formaPagamentoServiceImpl){
        super();
        this.formaPagamentoServiceImpl = formaPagamentoServiceImpl;
    }

    @GetMapping()
    public List<FormaPagamentoDTO> get(){
        return formaPagamentoServiceImpl.get();
    }

}
