package com.digitaul.lagunachicken.persistence.crud;

import com.digitaul.lagunachicken.persistence.entity.Chofer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChoferRepository extends JpaRepository<Chofer, Integer> {
}
