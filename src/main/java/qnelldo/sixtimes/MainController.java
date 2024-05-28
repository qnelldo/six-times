package qnelldo.sixtimes;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String root() {
        return "Welcome to the home page!";
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal OAuth2User oAuth2User) {
        if (oAuth2User != null) {
            return "Welcome, " + oAuth2User.getAttribute("name") + "!";
        } else {
            return "Welcome to the home page!";
        }
    }

    @GetMapping("/login")
    public String login(@AuthenticationPrincipal OAuth2User oAuth2User) {
        if (oAuth2User != null) {
            return "Already logged in as " + oAuth2User.getAttribute("name");
        } else {
            return "Redirecting to OAuth2 login...";
        }
    }
}
