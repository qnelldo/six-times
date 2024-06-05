package qnelldo.sixtimes.domain.post.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qnelldo.sixtimes.domain.post.mysql.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {


}
