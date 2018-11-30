package ua.pp.disik.englishroulette.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.pp.disik.englishroulette.backend.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
