package qnelldo.sixtimes.domain.user.mongodb.repository;

import co.elastic.clients.elasticsearch.core.search.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import qnelldo.sixtimes.domain.user.mongodb.document.UserProfile;
import qnelldo.sixtimes.domain.user.mysql.entity.User;

import java.util.Optional;

public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
    Optional<UserProfile> findByUserId(Long userId);

}
