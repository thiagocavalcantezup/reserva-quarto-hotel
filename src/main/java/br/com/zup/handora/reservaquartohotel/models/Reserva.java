package br.com.zup.handora.reservaquartohotel.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "quarto_id")
    private Quarto quarto;

    @Column(nullable = false)
    private String reservadoPara;

    @Column(nullable = false)
    @Future
    private LocalDate checkIn;

    @Column(nullable = false)
    @Future
    private LocalDate checkOut;

    @Column(nullable = false)
    private LocalDateTime criadaEm = LocalDateTime.now();

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Reserva() {}

    public Reserva(String reservadoPara, @Future LocalDate checkIn, @Future LocalDate checkOut) {
        this.reservadoPara = reservadoPara;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Long getId() {
        return id;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

}
