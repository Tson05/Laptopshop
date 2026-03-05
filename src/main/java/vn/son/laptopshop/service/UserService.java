package vn.son.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.son.laptopshop.domain.Role;
import vn.son.laptopshop.domain.User;
import vn.son.laptopshop.repository.RoleRepository;
import vn.son.laptopshop.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;

    }

    public String handlHello() {
        return "Hello from service";
    }

    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    public List<User> getAllUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User handlSaveUser(User user) {

        return this.userRepository.save(user);

    }

    public User getUserById(long id) {
        return this.userRepository.findById(id);
    }

    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    public Role getRoleByName(String name) {
        return this.roleRepository.findByName(name);
    }
}
