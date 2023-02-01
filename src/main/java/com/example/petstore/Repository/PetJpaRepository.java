package com.example.petstore.Repository;

import com.example.petstore.Model.Pet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PetJpaRepository extends JpaRepository<Pet,Long> {
}
