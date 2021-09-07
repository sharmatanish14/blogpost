package com.learning.blogpost.service;

import com.learning.blogpost.dto.PostDto;
import com.learning.blogpost.exception.BlogPostException;
import com.learning.blogpost.model.Post;
import com.learning.blogpost.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private AuthService authService;

    @Autowired
    private PostRepository postRepository;

    public Post createPost(PostDto postDto) {

        Post post = new Post();

        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        User user = authService.getCurrentuser().orElseThrow(() -> new IllegalArgumentException("No user exists"));
        post.setUser(user.getUsername());

        post.setCreatedOn(Instant.now());

        return postRepository.save(post);

    }

    public List<PostDto> getAllPosts() {

        List<Post> postList = postRepository.findAll();
        return postList.stream().map(this::mapPostToPostDto).collect(Collectors.toList());
    }

    public Post getPost(Integer id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new BlogPostException("No post found for id :" + id));
        return post;
    }

    private PostDto mapPostToPostDto(Post post){
        PostDto postDto=new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setUsername(post.getUser());
        return postDto;
    }
}
