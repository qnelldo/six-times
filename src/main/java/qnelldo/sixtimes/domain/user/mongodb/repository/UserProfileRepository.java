package qnelldo.sixtimes.domain.user.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import qnelldo.sixtimes.domain.user.mongodb.document.UserProfile;

import java.util.Optional;

public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
    Optional<UserProfile> findByUserId(Long userId);

}
