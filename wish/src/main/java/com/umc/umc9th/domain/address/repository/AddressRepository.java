package com.umc.umc9th.domain.address.repository;

import com.umc.umc9th.domain.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {

  Optional<Address> findByCityAndDistrictAndDong(
      String city, String district, String dong
  );
}