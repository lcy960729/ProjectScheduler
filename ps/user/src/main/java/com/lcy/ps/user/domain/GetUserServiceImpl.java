package com.lcy.ps.user.domain;

import com.lcy.ps.core.exception.AlreadyRegisteredEmailException;
import com.lcy.ps.core.exception.NotFoundEntityException;
import com.lcy.ps.core.exception.NotRegisteredUserException;
import com.lcy.ps.core.exception.SignInException;
import com.lcy.ps.integrate.domain.user.GetUserService;
import com.lcy.ps.integrate.dto.UserModel;
import com.lcy.ps.user.component.JwtTokenProvider;
import com.lcy.ps.user.dto.request.SignInDTO;
import com.lcy.ps.user.dto.request.SignUpDTO;
import com.lcy.ps.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetUserServiceImpl implements GetUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserModel> getUsers(List<Long> userIds) {
        return userRepository.findAllById(userIds).stream()
                .map(user -> new UserModel(user.getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    public UserModel getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(NotFoundEntityException::new);

        return new UserModel(user.getEmail());
    }
}
