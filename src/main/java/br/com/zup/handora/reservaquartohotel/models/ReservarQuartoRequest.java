package br.com.zup.handora.reservaquartohotel.models;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservarQuartoRequest {

    @NotNull
    private String reservadoPara;

    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkIn;

    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate checkOut;

    public ReservarQuartoRequest() {}

    public ReservarQuartoRequest(@NotNull String reservadoPara, @NotNull @Future LocalDate checkIn,
                                 @NotNull @Future LocalDate checkOut) {
        this.reservadoPara = reservadoPara;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Reserva toModel() {
        return new Reserva(reservadoPara, checkIn, checkOut);
    }

    public String getReservadoPara() {
        return reservadoPara;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

}
