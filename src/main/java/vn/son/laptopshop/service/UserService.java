package vn.son.laptopshop.service;

import org.springframework.stereotype.Service;

import vn.son.laptopshop.domain.User;
import vn.son.laptopshop.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String handlHello() {
        return "Hello from service";
    }

    public User handlSaveUser(User user) {

        return this.userRepository.save(user);

    }
}
