package ua.pp.disik.englishroulette.backend.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.pp.disik.englishroulette.backend.entity.User;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
