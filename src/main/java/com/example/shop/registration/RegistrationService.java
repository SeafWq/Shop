package com.example.shop.registration;

import com.example.shop.user.Role;
import com.example.shop.user.User;
import com.example.shop.user.UserRepository;
import com.example.shop.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request){
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if  (!isValidEmail){
            throw new IllegalStateException("Email is not valid");
        }

      return userService.signUpUser(
              new User(
                      request.getEmail(),
                      request.getNameAcc()  ,
                      request.getPassword(),
                      Role.USER
              )
      );
    }

    public int enabledUser(String email){
        return userRepository.enableUser(email);
    }

}
