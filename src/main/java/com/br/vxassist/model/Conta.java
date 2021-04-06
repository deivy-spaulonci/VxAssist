package com.br.vxassist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "TIPO_CONTA_ID")
    private TipoConta tipoConta;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date emissao;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date vencimento;

    @Column(length = 10, nullable = false, columnDefinition = "default 0")
    private int parcela;

    @Column(name = "TOTAL_PARCELAS", length = 10, columnDefinition = "default 0")
    private int totalParcela;

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
