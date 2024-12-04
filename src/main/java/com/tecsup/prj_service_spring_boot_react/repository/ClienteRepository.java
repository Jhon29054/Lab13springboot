package com.tecsup.prj_service_spring_boot_react.repository;

import com.tecsup.prj_service_spring_boot_react.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
