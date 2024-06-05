package qnelldo.sixtimes.domain.post.mongodb.document;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import qnelldo.sixtimes.domain.post.mysql.entity.Post;

import java.time.LocalDateTime;

@Document(collection = "posts")
@Getter
@Setter
public class PostDocument {

    @Id
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;

    public PostDocument() {
    }

    public PostDocument(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
        this.createdAt = post.getCreatedAt();
    }

    // Getters and Setters
}