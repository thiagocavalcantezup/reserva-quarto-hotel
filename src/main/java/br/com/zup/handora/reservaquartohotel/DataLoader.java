package br.com.zup.handora.reservaquartohotel;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.zup.handora.reservaquartohotel.models.Quarto;
import br.com.zup.handora.reservaquartohotel.models.TipoQuarto;
import br.com.zup.handora.reservaquartohotel.repositories.QuartoRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final QuartoRepository quartoRepository;

    public DataLoader(QuartoRepository quartoRepository) {
        this.quartoRepository = quartoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (quartoRepository.count() == 0) {
            load();
        }
    }

    private void load() {
        Quarto quarto = new Quarto(
            "Quarto com vista para o mar.", new BigDecimal("200.00"), TipoQuarto.CASAL
        );

        quartoRepository.save(quarto);
    }

}
