package com.example.demo.post;


import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.ResponseCache;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    //get All book by id
    public ResponseEntity<List<Post>> getPostByUserId(Long userId){
        List <Post> posts = postRepository.findByUserId(userId);
        if(posts.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(posts);
    }

    //Delete by userId and PostId
    public ResponseEntity<String> deleteBookById(Long userId,Long postId){
        Post post = postRepository.findByUserIdAndId(userId,postId);
        if(post == null)  return ResponseEntity.notFound().build();
        else {
            postRepository.delete(post);
            return ResponseEntity.ok("Book deleted successfully");
        }
    }

    //update by userId and PostId
    public ResponseEntity<Post> updateBookById(Long userId,Long postId,Post post){
        Post Existingpost = postRepository.findByUserIdAndId(userId,postId);
        if(post == null){
            return ResponseEntity.notFound().build();
        }
        Existingpost.setTitle(post.getTitle());
        Existingpost.setContent((post.getContent()));

        Post newPost = postRepository.save(Existingpost);
        return ResponseEntity.ok(newPost);
    }

    //Create new book
    public ResponseEntity<Post> addPostByUserId(Long userId, Post newPost){
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            throw new IllegalStateException("No user found by this id");
         }else{

            User userObj = new User();
            userObj.setEmail(user.getEmail());
            userObj.setName(user.getName());
            userObj.setId(user.getId());

            newPost.setUser(userObj);
            Post createdPost = postRepository.save(newPost);

            return ResponseEntity.ok(createdPost);
        }
    }
}
