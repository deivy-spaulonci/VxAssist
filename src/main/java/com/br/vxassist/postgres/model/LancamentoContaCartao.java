package com.br.vxassist.postgres.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LANCAMENTO_CONTA_CARTAO")
public class LancamentoContaCartao {

    @Id
    @TableGenerator(name = "LANCAMENTO_CONTA_CARTAO_IDS",
            table = "TABELA_DE_IDS",
            pkColumnName = "tabela",
            pkColumnValue = "lancamento_conta_cartao_id",
            allocationSize = 1,
            initialValue = 295,
            valueColumnName = "id_atual")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "LANCAMENTO_CONTA_CARTAO_IDS")
    private Long id;

    @NotNull(message = "{notnull.lancamento-conta-cartao.data}")
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA", nullable = false)
    private Date data;

    @ManyToOne
    @JoinColumn(name = "FORNECEDOR_ID", nullable = true)
    private Fornecedor fornecedor;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(length = 10, nullable = false)
    private int parcela;

    @Column(name = "TOTAL_PARCELAS", length = 10, nullable = false)
    private int totalParcela;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
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
        final LancamentoContaCartao other = (LancamentoContaCartao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
