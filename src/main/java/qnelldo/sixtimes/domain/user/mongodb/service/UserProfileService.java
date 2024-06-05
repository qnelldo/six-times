package qnelldo.sixtimes.domain.user.mongodb.service;

import org.springframework.stereotype.Service;
import qnelldo.sixtimes.domain.user.mongodb.document.UserProfile;
import qnelldo.sixtimes.domain.user.mongodb.repository.UserProfileRepository;

import java.util.Optional;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }


    public UserProfile saveUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    public Optional<UserProfile> findUserProfileByUserId(Long userId) {
        return userProfileRepository.findByUserId(userId);
    }

    public void deleteUserProfile(String id) {
        userProfileRepository.deleteById(id);
    }

}
