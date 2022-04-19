package br.com.zup.handora.reservaquartohotel.repositories;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import br.com.zup.handora.reservaquartohotel.models.Quarto;

public interface QuartoRepository extends JpaRepository<Quarto, Long> {

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Quarto> findById(Long id);

}
