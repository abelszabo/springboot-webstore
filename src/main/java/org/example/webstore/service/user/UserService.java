package org.example.webstore.service.user;


import org.example.webstore.api.user.RegisterUserRequest;
import org.example.webstore.api.user.RegisterUserResponse;
import org.example.webstore.entity.User;
import org.example.webstore.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        User user = new User();

        user.setEmail(request.email());
        user.setFullName(request.username());

        user = userRepository.save(user);

        return new RegisterUserResponse(user.getUuid());
    }
}
