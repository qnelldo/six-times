package qnelldo.sixtimes.domain.user.mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qnelldo.sixtimes.domain.user.mongodb.document.UserProfile;
import qnelldo.sixtimes.domain.user.mongodb.service.UserProfileService;

import java.util.Optional;

@RestController
@RequestMapping("/api/userprofile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/user/{userId}")
    public Optional<UserProfile> getUserProfileByUserId(@PathVariable Long userId) {
        return userProfileService.findUserProfileByUserId(userId);
    }

    @PostMapping("/user/{userId}")
    public UserProfile createUserProfile(@PathVariable Long userId, @RequestBody UserProfile userProfile) {
        userProfile.setUserId(userId);
        return userProfileService.saveUserProfile(userProfile);
    }

    @DeleteMapping("/{id}")
    public void deleteUserProfile(@PathVariable String id) {
        userProfileService.deleteUserProfile(id);
    }
}