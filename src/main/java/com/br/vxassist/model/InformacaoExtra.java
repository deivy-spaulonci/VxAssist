package com.br.vxassist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "INFORMACAO_EXTRA")
public class InformacaoExtra implements Serializable {

    @Id
    @TableGenerator(name = "INFORMACAO_EXTRA_IDS",
            table = "TABELA_DE_IDS",
            pkColumnName = "tabela",
            pkColumnValue = "informacao_extra_id",
            allocationSize = 1,
            initialValue = 8944,
            valueColumnName = "id_atual")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "INFORMACAO_EXTRA_IDS")    
    private Long id;

    @Size(min = 3, message = "{size.informacao-extra.nome}")
    @NotBlank(message = "{notblank.informacao-extra.nome}")
    @Column(name = "NUMERO", nullable = false)
    private String numero;

    @ManyToOne
    @JoinColumn(name = "TIPO_INFORMACAO_EXTRA_ID", nullable = false)
    private TipoInformacaoExtra tipoInformacaoExtra;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        } else {
            return Objects.equals(this.id, ((InformacaoExtra) obj).getId());
        }
    }
}
