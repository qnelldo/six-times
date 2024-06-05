package qnelldo.sixtimes.domain.post.mysql.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import qnelldo.sixtimes.domain.post.mysql.entity.Post;
import qnelldo.sixtimes.domain.post.mysql.service.PostService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public Post createPost(@RequestParam String title, @RequestParam String content, @AuthenticationPrincipal OAuth2User oAuth2User) {
        String author = oAuth2User.getAttribute("name");
        return postService.createPost(title, content, author);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Optional<Post> getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestParam String title, @RequestParam String content) {
        return postService.updatePost(id, title, content);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}