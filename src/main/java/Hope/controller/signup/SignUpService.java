package Hope.controller.signup;

import Hope.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignUpService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SignUpService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Transactional
    public boolean signUp(String username, String password, String firstName, String lastName) {
        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty() ||
                firstName == null || firstName.isEmpty() ||
                lastName == null || lastName.isEmpty()) {
            System.out.println("Invalid input provided.");
            return false;
        }

        boolean userExists = userRepository.findUserByUsername(username).isPresent();
        if (userExists) {
            System.out.println("User already exists with username: " + username);
            return false;
        }

        String encodedPassword = passwordEncoder.encode(password);

        userRepository.insertUser(username, encodedPassword, firstName, lastName);
        boolean userInserted = userRepository.findUserByUsername(username).isPresent();

        if (!userInserted) {
            System.out.println("User not inserted with username: " + username);
        }

        return userInserted;
    }

}


/*
@Service
public class SignUpService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SignUpService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Transactional
    public void signUp(String username, String password, String firstName, String lastName) {
        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty() ||
                firstName == null || firstName.isEmpty() ||
                lastName == null || lastName.isEmpty()) {
            throw new InvalidInputException("Invalid input provided.");
        }

        if (userRepository.findUserByUsername(username).isPresent()) {
            throw new UserAlreadyExistsException("User already exists with username: " + username);
        }

        String encodedPassword = passwordEncoder.encode(password);
        userRepository.insertUser(username, encodedPassword, firstName, lastName);

        if (!userRepository.findUserByUsername(username).isPresent()) {
            throw new RuntimeException("User not inserted with username: " + username);
        }
    }
}
*/
