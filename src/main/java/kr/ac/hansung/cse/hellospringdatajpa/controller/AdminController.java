package kr.ac.hansung.cse.hellospringdatajpa.controller;

import kr.ac.hansung.cse.hellospringdatajpa.entity.User;
import kr.ac.hansung.cse.hellospringdatajpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    // 관리자만 접근 가능한 사용자 목록 페이지
    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public String showUserList(Model model) {
        List<User> userList = userService.findAllUsers();
        model.addAttribute("users", userList);
        return "user_list_only_admin";
    }
}
