package qnelldo.sixtimes.domain.post.mysql.service;

import org.springframework.stereotype.Service;
import qnelldo.sixtimes.domain.post.mongodb.document.PostDocument;
import qnelldo.sixtimes.domain.post.mongodb.repository.PostDocumentRepository;
import qnelldo.sixtimes.domain.post.mysql.entity.Post;
import qnelldo.sixtimes.domain.post.mysql.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostDocumentRepository postDocumentRepository;

    public PostService(PostRepository postRepository, PostDocumentRepository postDocumentRepository) {
        this.postDocumentRepository = postDocumentRepository;
        this.postRepository = postRepository;
    }

    public Post createPost(String title, String content, String author) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(author);
        post.setCreatedAt(LocalDateTime.now());
        Post savedPost = postRepository.save(post);

        // 동기화: MongoDB에 저장
        PostDocument postDocument = new PostDocument(savedPost);
        postDocumentRepository.save(postDocument);

        return savedPost;

    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public Post updatePost(Long id, String title, String content) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setTitle(title);
            post.setContent(content);
            return postRepository.save(post);
        }
        return null;
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}