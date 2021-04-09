package com.br.vxassist.restcontroller;

import com.br.vxassist.VxPayofApplication;
import com.br.vxassist.filter.ContaFilter;
import com.br.vxassist.model.NodeFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/comprovantes")
public class ComporvantesRestController implements Serializable {

    Logger logger = LoggerFactory.getLogger(VxPayofApplication.class);

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public NodeFile get(@ModelAttribute ContaFilter contaFitler, Sort sort){
        try {
//            String jsonInString =  new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(
//                    getNode(new ClassPathResource("teste").getFile())
//            );
            return getNode(new ClassPathResource("teste").getFile());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static NodeFile getNode(File node){
        if(node.isDirectory()){
            return new NodeFile(node.getName(),node.getAbsolutePath(),"directory", getDirList(node));
        }else{
            return new NodeFile(node.getName(),node.getAbsolutePath(),"file",null);
        }
    }

    public static List<NodeFile> getDirList(File node){
        List<NodeFile> nodeList=new ArrayList<>();
        for(File n : node.listFiles()){
            nodeList.add(getNode(n));
        }
        return nodeList;
    }
}
