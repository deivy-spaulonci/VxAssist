package com.br.vxassist.restcontroller;

import com.br.vxassist.dto.FornecedorDTO;
import com.br.vxassist.filter.FornecedorFilter;
import com.br.vxassist.serviceImpl.FornecedorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fornecedor")
public class FornecedorRestController implements Serializable {

    @Autowired
    private final FornecedorServiceImpl fornecedorServiceImpl;

    public FornecedorRestController(FornecedorServiceImpl fornecedorServiceImpl){
        super();
        this.fornecedorServiceImpl = fornecedorServiceImpl;
    }

    @GetMapping()
    public List<FornecedorDTO> get(@ModelAttribute FornecedorFilter fornecedorFilter, Sort sort){
        return fornecedorServiceImpl.get(fornecedorFilter, sort);
    }

    @GetMapping("/page")
    public Page<FornecedorDTO> listAll(@ModelAttribute FornecedorFilter fornecedorFilter,
                                                       Pageable pageable){
        Page<FornecedorDTO> resultPage = fornecedorServiceImpl.getPage(fornecedorFilter, pageable);
        return resultPage;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorDTO> findFornecedorById(@PathVariable("id") Long id){
        try{
            FornecedorDTO fornecedorDTO = fornecedorServiceImpl.findById(id);
            return new ResponseEntity<>(fornecedorDTO, HttpStatus.OK);
        }catch (Exception ex) {
            throw new EntityNotFoundException("Id do Fornecedor não encontrado!");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody FornecedorDTO fornecedorDTO){
        try{
            return new ResponseEntity<>(this.fornecedorServiceImpl.create(fornecedorDTO), HttpStatus.CREATED);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(ex.getMessage(), null, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping
    public ResponseEntity<FornecedorDTO> update(@Valid @RequestBody FornecedorDTO fornecedorDTO){
        try{
            return new ResponseEntity<>(this.fornecedorServiceImpl.create(this.fornecedorServiceImpl.findById(fornecedorDTO.getId())), HttpStatus.OK);
        }catch (Exception ex) {
            throw new EntityNotFoundException("Id do Fornecedor não encontrado!");
        }

    }

    @GetMapping(value = "/consultacnpj", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String consultaCNPJ(@RequestParam(name = "cnpj") String cnpj){
        String URL_API = "https://www.receitaws.com.br/v1/cnpj/"+cnpj;
        HttpURLConnection con = null;

        try {
            URL url = null;
            url = new URL(URL_API);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            System.out.println(getJson(url));
            return getJson(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getJson(URL url) {
        if (url == null)
            throw new RuntimeException("URL é null");

        String html = null;
        StringBuilder sB = new StringBuilder();
        try (BufferedReader bR = new BufferedReader(new InputStreamReader(url.openStream()))) {
            while ((html = bR.readLine()) != null)
                sB.append(html);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sB.toString();
    }

}
