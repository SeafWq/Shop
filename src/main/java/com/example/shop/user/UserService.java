package com.example.shop.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {


    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    private final UserRepository userRepository;
    private final UserDto userDto;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User myUser= userDto.findByEmail(email);
        if (myUser == null) {
            throw new UsernameNotFoundException("Unknown user: "+email);
        }
        UserDetails user = org.springframework.security.core.userdetails.User.builder()
                .username(myUser.getEmail())
                .password(myUser.getPassword())
                .roles(String.valueOf(myUser.getRole()))
                .build();
        return user;
    }

    public String signUpUser(User user){
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if (userExists){
            throw new IllegalStateException("Email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "user signup";
    }

}