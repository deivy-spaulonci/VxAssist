package com.br.vxassist.model;


import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.beans.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DESPESA")
public class Despesa implements Serializable {

    @Id
    @TableGenerator(name = "DESPESA_IDS",
            table = "TABELA_DE_IDS",
            pkColumnName = "tabela",
            pkColumnValue = "despesa_id",
            allocationSize = 1,
            initialValue = 7428,
            valueColumnName = "id_atual")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DESPESA_IDS")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "TIPO_DESPESA_ID")
    @RestResource(path = "tipoDespesa", rel="tipoDespesa", exported = true)
    private TipoDespesa tipoDespesa;

    @ManyToOne
    @JoinColumn(name = "FORNECEDOR_ID", nullable = true)
    private Fornecedor fornecedor;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date data;

    @ManyToOne(optional = false)
    @JoinColumn(name = "FORMA_PAGAMENTO_ID")
    private FormaPagamento formaPagamento;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(length = 255, nullable = true)
    private String obs;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinTable(name = "DESPESA_INFORMACAO_EXTRA", joinColumns = @JoinColumn(name = "DESPESA_ID"), inverseJoinColumns = @JoinColumn(name = "INFORMACAO_EXTRA_ID"))
    private List<InformacaoExtra> informacaoExtra;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_LACAMENTO", insertable = false, nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date dataLancamento;

    @Override
    public String toString() {
        return tipoDespesa.getNome();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Despesa other = (Despesa) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Transient
    public String getDataMesAno() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        return sdf.format(getData());
    }

}
