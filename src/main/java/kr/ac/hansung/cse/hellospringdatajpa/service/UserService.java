package kr.ac.hansung.cse.hellospringdatajpa.service;

import kr.ac.hansung.cse.hellospringdatajpa.entity.User;
import kr.ac.hansung.cse.hellospringdatajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean register(String email, String rawPassword) {
        // 이메일 중복 확인
        if (userRepository.existsByEmail(email)) {
            return false; // 회원가입 실패
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // User 생성 및 저장
        User user = new User();
        user.setEmail(email);
        user.setPassword(encodedPassword);

        if (email.equalsIgnoreCase("admin@gmail.com")) {
            user.getRoles().add("ROLE_ADMIN");
        } else {
            user.getRoles().add("ROLE_USER");
        }

        userRepository.save(user);
        return true;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
