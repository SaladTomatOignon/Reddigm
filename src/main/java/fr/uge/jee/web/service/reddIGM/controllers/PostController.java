package fr.uge.jee.web.service.reddIGM.controllers;

import fr.uge.jee.web.service.reddIGM.dto.*;
import fr.uge.jee.web.service.reddIGM.models.User;
import fr.uge.jee.web.service.reddIGM.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/posts/")
@AllArgsConstructor
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest) {
        postService.save(postRequest, ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/vote")
    public ResponseEntity<PostResponse> voteForPost(@Valid @RequestBody VotePostDto vote) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.vote(vote, principal));
    }


    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        return status(HttpStatus.OK).body(postService.getPost(id));
    }

    @GetMapping("by-subject/{id}")
    public ResponseEntity<List<PostResponse>> getPostsBySubject(@PathVariable Long id) {
        return status(HttpStatus.OK).body(postService.getPostsBySubjectId(id));
    }

    @GetMapping("by-user/{id}")
    public ResponseEntity<List<PostResponse>> getPostsById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(postService.getPostsById(id));
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return status(HttpStatus.OK).body(postService.getAllPosts());
    }
}