package qnelldo.sixtimes.domain.user.mongodb.document;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "user_profile")
@Data
public class UserProfile {
    @Id
    private String id;
    private Long userId; // User ID와 연동
    private String userImgUrl;
    private List<String> interests;
}
