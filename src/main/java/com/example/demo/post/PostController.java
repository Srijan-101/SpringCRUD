package com.example.demo.post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping ("/addPost")
    public ResponseEntity<Post> addPost(@RequestBody Post post, @RequestParam Long userId){
         return postService.addPostByUserId(userId,post);
    }

    @DeleteMapping("/deletePost")
    public ResponseEntity<String> deletePost(@RequestParam Long userId,@RequestParam Long postId){
        return postService.deleteBookById(userId,postId);
    }

    @PutMapping("/updatePost")
    public ResponseEntity<Post> updatePost(@RequestParam Long userId,@RequestParam Long postId,@RequestBody Post post){
        return postService.updateBookById(userId,postId,post);
    }

    @GetMapping  ("/getPostbyUserId")
    public ResponseEntity<List<Post>> getPost(@RequestParam Long userId){
        return postService.getPostByUserId(userId);
    }
}
