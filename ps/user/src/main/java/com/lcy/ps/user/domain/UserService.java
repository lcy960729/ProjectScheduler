package com.lcy.ps.user.domain;

import com.lcy.ps.user.dto.request.SignInDTO;
import com.lcy.ps.user.dto.request.SignUpDTO;
import com.lcy.ps.core.exception.AlreadyRegisteredEmailException;
import com.lcy.ps.core.exception.NotRegisteredUserException;
import com.lcy.ps.core.exception.SignInException;
import com.lcy.ps.user.component.JwtTokenProvider;
import com.lcy.ps.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String signIn(SignInDTO signInDTO) {
        User user = get(signInDTO.getEmail());

        if (user.getPw().equals(signInDTO.getPw())) {
            return jwtTokenProvider.createToken(user);
        }

        throw new SignInException();
    }

    public User signUp(SignUpDTO signUpDTO) {
        isNotExistUser(signUpDTO.getEmail());

        User user = User.of(signUpDTO);

        user = userRepository.save(user);

        return user;
    }

    private User get(String email) {
        return userRepository.findByEmailLike(email)
                .orElseThrow(NotRegisteredUserException::new);
    }

    private void isNotExistUser(String email) {
        if (userRepository.findByEmailLike(email).isPresent()) {
            throw new AlreadyRegisteredEmailException();
        }
    }
}
