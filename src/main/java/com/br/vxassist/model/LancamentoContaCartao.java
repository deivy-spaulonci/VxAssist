package com.br.vxassist.model;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Audited
@AuditTable(value="lancamento_conta_cartao_aud")
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

    @NotEmpty(message = "Valor vazio!")
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(length = 10, nullable = false)
    private int parcela;

    @Column(name = "TOTAL_PARCELAS", length = 10, nullable = false)
    private int totalParcela;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public int getParcela() {
        return parcela;
    }

    public void setParcela(int parcela) {
        this.parcela = parcela;
    }

    public int getTotalParcela() {
        return totalParcela;
    }

    public void setTotalParcela(int totalParcela) {
        this.totalParcela = totalParcela;
    }

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
