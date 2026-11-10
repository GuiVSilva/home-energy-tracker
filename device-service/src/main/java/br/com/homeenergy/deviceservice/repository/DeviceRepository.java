package br.com.homeenergy.deviceservice.repository;

import br.com.homeenergy.deviceservice.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
}