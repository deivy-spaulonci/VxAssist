package com.br.vxassist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NodeFile {
    private String name;
    private String location;
    private String type;
    private List<NodeFile> nodeFileList;
}
