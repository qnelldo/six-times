package qnelldo.sixtimes.domain.user.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qnelldo.sixtimes.domain.user.mysql.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{


}
