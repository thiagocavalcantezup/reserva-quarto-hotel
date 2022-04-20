package br.com.zup.handora.reservaquartohotel.models;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.zup.handora.reservaquartohotel.exceptions.ReservaAtivaException;

@Entity
@Table(name = "quartos")
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    @Size(max = 200)
    private String descricao;

    @Column(nullable = false)
    @Positive
    private BigDecimal diaria;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoQuarto tipo;

    @Column(nullable = false)
    private boolean reservaAtiva = false;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "quarto")
    private Set<Reserva> reservas = new HashSet<>();

    @Version
    private int versao;

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Quarto() {}

    public Quarto(String descricao, BigDecimal diaria, TipoQuarto tipo) {
        this.descricao = descricao;
        this.diaria = diaria;
        this.tipo = tipo;
    }

    public void reservar(Reserva reserva) {
        if (isReservaAtiva()) {
            throw new ReservaAtivaException("O quarto já possui uma reserva ativa.");
        }

        adicionar(reserva);
        reservaAtiva = true;
    }

    public void adicionar(Reserva reserva) {
        reserva.setQuarto(this);
        this.reservas.add(reserva);
    }

    public Long getId() {
        return id;
    }

    public boolean isReservaAtiva() {
        return reservaAtiva;
    }

}
