package com.lcy.ps.auth.api.v1.domain.user;

import com.lcy.ps.core.domain.user.User;
import com.lcy.ps.core.dto.request.login.SignInDTO;
import com.lcy.ps.core.dto.request.login.SignUpDTO;
import com.lcy.ps.core.exception.AlreadyRegisteredEmailException;
import com.lcy.ps.core.exception.NotRegisteredUserException;
import com.lcy.ps.core.exception.SignInException;
import com.lcy.ps.core.repository.UserRepository;
import com.lcy.ps.auth.api.v1.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.stereotype.Service;

@Service
public class UserService implements AuthService {
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

    @Override
    public long auth(String token) {
        return jwtTokenProvider.validateTokenAndReturnUserId(token);
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
