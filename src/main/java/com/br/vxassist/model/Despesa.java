package com.br.vxassist.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.beans.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Audited
@AuditTable(value="despesa_aud")
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

    @NotNull(message = "{notnull.despesa.tipo-despesa")
    @ManyToOne(optional = false)
    @JoinColumn(name = "TIPO_DESPESA_ID")
    @RestResource(path = "tipoDespesa", rel="tipoDespesa", exported = true)
    private TipoDespesa tipoDespesa;

    @ManyToOne
    @NotNull(message = "{notnull.despesa.fornecedor}")
    @JoinColumn(name = "FORNECEDOR_ID", nullable = true)
    private Fornecedor fornecedor;

    @NotNull(message = "{notnull.despesa.data}")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data;

    @NotNull(message = "{notnull.despesa.forma-pagamento}")
    @ManyToOne(optional = false)
    @JoinColumn(name = "FORMA_PAGAMENTO_ID")
    private FormaPagamento formaPagamento;

    @NotNull(message = "{notnull.despesa.valor}")
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(length = 255, nullable = true)
    private String obs;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinTable(name = "DESPESA_INFORMACAO_EXTRA", joinColumns = @JoinColumn(name = "DESPESA_ID"), inverseJoinColumns = @JoinColumn(name = "INFORMACAO_EXTRA_ID"))
    private List<InformacaoExtra> informacaoExtra;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_LACAMENTO", insertable = false, nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonIgnore
    private Date dataLancamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoDespesa getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(TipoDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public List<InformacaoExtra> getInformacaoExtra() {
        return informacaoExtra;
    }

    public void setInformacaoExtra(List<InformacaoExtra> informacaoExtra) {
        this.informacaoExtra = informacaoExtra;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
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
