package com.br.vxassist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CONTA")
public class Conta implements Serializable {

    @Id
    @TableGenerator(name = "CONTA_IDS",
            table = "TABELA_DE_IDS",
            pkColumnName = "tabela",
            pkColumnValue = "conta_id",
            allocationSize = 1,
            initialValue = 1267,
            valueColumnName = "id_atual")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CONTA_IDS")
    private Long id;

    @Column(length = 60, nullable = false)
    private String numero;

    @Column(name = "CODIGO_BARRA", length = 60, nullable = false)
    private String codigoBarra;

    @NotNull(message = "{notnull.conta.tipo-conta}")
    @ManyToOne(optional = false)
    @JoinColumn(name = "TIPO_CONTA_ID")
    private TipoConta tipoConta;

    @NotNull(message = "{notnull.conta.emissao}")
    @NotEmpty(message = "{notempty.conta.emissao}")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date emissao;

    @NotNull(message = "{notnull.conta.vencimento}")
    @NotEmpty(message = "{notempty.conta.vencimento}")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date vencimento;

    @Column(length = 10, nullable = false, columnDefinition = "default 0")
    private int parcela;

    @Column(name = "TOTAL_PARCELAS", length = 10, columnDefinition = "default 0")
    private int totalParcela;

    @NotNull(message = "{notnull.conta.valor}")
    @NotEmpty(message = "{notempty.conta.valor}")
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal valor;

    //PAGAMENTO/////////////////////////////////////////////////////////////////
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_PAGAMENTO", nullable = true)
    private Date dataPagamento;

    @ManyToOne(optional = true)
    @JoinColumn(name = "FORMA_PAGAMENTO_ID")
    private FormaPagamento formaPagamento;

    @Column(name = "VALOR_PAGO", precision = 10, scale = 2, nullable = true)
    private BigDecimal valorPago;

    //cancelamento
    private Boolean cancelado;

    @Column(name = "ID_CANCELAMENTO")
    private Long idCancelamento;

    @Column(length = 255, nullable = true)
    private String obs;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "CONTA_LANCAMENTO_CONTA_CARTAO", joinColumns = @JoinColumn(name = "CONTA_ID"), inverseJoinColumns = @JoinColumn(name = "LANCAMENTO_CONTA_CARTAO_ID"))
    private List<LancamentoContaCartao> lancamentoContaCartao;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_LANCAMENTO", insertable = false, nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date dataLancamento;

    // 2 - Pago
    // 1 - Em Aberto
    // 0 - Vencimento Hoje
    // -1 - Atrasado
    @Transient
    public int getIntStatus() {
        if (getDataPagamento() == null) {
            Calendar currDtCal = Calendar.getInstance();
            currDtCal.setTime(new Date());
            currDtCal.set(Calendar.HOUR_OF_DAY, 0);
            currDtCal.set(Calendar.MINUTE, 0);
            currDtCal.set(Calendar.SECOND, 0);
            currDtCal.set(Calendar.MILLISECOND, 0);
            return getVencimento().compareTo(currDtCal.getTime());
        } else {
            return 2;
        }
    }

    @Transient
    public String getStatus() {
        switch (getIntStatus()) {
            case 1:
                return "Em aberto";
            case 0:
                return "Vencimento Hoje";
            case -1:
                return "Atrasado";
            case 2:
                return "Pago";
            case 3:
                return "Cancelado e substituido";
            default:
                return "Pago";
        }
    }

    @Transient
    public String getCorStatus() {
        switch (getIntStatus()) {
            case 1:
                return "aberto";/*em aberto*/
            case 0:
                return "vencimentoHoje";/*vencimento hoje*/
            case -1:
                return "atrasado";/*atrasado*/
            case 2:
                return "";/*pago*/
            case 3:
                return "gray";
            default:
                return "";
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Date getEmissao() {
        return emissao;
    }

    public void setEmissao(Date emissao) {
        this.emissao = emissao;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public Boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(Boolean cancelado) {
        this.cancelado = cancelado;
    }

    public Long getIdCancelamento() {
        return idCancelamento;
    }

    public void setIdCancelamento(Long idCancelamento) {
        this.idCancelamento = idCancelamento;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public List<LancamentoContaCartao> getLancamentoContaCartao() {
        return lancamentoContaCartao;
    }

    public void setLancamentoContaCartao(List<LancamentoContaCartao> lancamentoContaCartao) {
        this.lancamentoContaCartao = lancamentoContaCartao;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    @Override
    public String toString() {
        return this.tipoConta.getNome();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Conta other = (Conta) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
