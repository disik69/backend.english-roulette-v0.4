package ua.pp.disik.englishroulette.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pp.disik.englishroulette.backend.repository.UserRepository;

@Service
public class UserService implements RepositoryService<UserRepository> {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserRepository repository() {
        return userRepository;
    }
}
