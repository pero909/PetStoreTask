package com.example.petstore.Repository;

import com.example.petstore.Model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserJpaRepository extends JpaRepository<User,Long> {

}
