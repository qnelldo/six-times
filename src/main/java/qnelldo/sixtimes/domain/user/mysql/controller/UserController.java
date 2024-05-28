package qnelldo.sixtimes.domain.user.mysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import qnelldo.sixtimes.domain.user.mysql.entity.User;
import qnelldo.sixtimes.domain.user.mysql.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/info")
    public User getUserInfo(@AuthenticationPrincipal OAuth2User oAuth2User) {
        if (oAuth2User != null) {
            String email = oAuth2User.getAttribute("email");
            Optional<User> user = userService.findByEmail(email);
            return user.orElse(null);
        }
        return null;
    }
}
