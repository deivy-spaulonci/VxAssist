package com.br.vxassist.controller;

import com.br.vxassist.serviceImpl.DespesaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private final DespesaServiceImpl despesaServiceImpl;

    public HomeController(DespesaServiceImpl despesaServiceImpl) {
        this.despesaServiceImpl = despesaServiceImpl;
    }


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("valor","conteudo do valor");
        return "home";
    }
}
