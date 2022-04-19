package br.com.zup.handora.reservaquartohotel.controllers;

import java.net.URI;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.handora.reservaquartohotel.models.Quarto;
import br.com.zup.handora.reservaquartohotel.models.Reserva;
import br.com.zup.handora.reservaquartohotel.models.ReservarQuartoRequest;
import br.com.zup.handora.reservaquartohotel.repositories.QuartoRepository;

@RestController
@RequestMapping("/quartos/{quartoId}/reservas")
class ReservarQuartoController {

    private final QuartoRepository quartoRepository;

    public ReservarQuartoController(QuartoRepository quartoRepository) {
        this.quartoRepository = quartoRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Void> reservarQuarto(@PathVariable Long quartoId,
                                               @RequestBody ReservarQuartoRequest reservarQuartoRequest,
                                               UriComponentsBuilder ucb) {
        Quarto quarto = quartoRepository.findById(quartoId)
                                        .orElseThrow(
                                            () -> new ResponseStatusException(
                                                HttpStatus.NOT_FOUND,
                                                "Não existe um quarto com o id fornecido."
                                            )
                                        );

        Reserva reserva = reservarQuartoRequest.toModel();
        quarto.reservar(reserva);

        quartoRepository.flush();

        URI location = ucb.path("/quartos/{quartoId}/reservas/{reservaId}")
                          .buildAndExpand(quartoId, reserva.getId())
                          .toUri();

        return ResponseEntity.created(location).build();
    }

}
