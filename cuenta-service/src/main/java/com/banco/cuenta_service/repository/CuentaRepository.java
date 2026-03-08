package com.banco.cuenta_service.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.banco.cuenta_service.entity.Cuenta;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM Cuenta c WHERE c.id = :id")
    Cuenta obtenerCuentaParaActualizar(@Param("id") Long id);

}
