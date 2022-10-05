package ua.pp.disik.englishroulette.backend.repository;

import org.springframework.data.repository.CrudRepository;
import ua.pp.disik.englishroulette.backend.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}