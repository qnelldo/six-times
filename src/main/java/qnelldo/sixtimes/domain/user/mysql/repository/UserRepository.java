package qnelldo.sixtimes.domain.user.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qnelldo.sixtimes.domain.user.mysql.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByProviderAndProviderId(String provider, String providerId);

    Optional<User> findByEmail(String email);
}
