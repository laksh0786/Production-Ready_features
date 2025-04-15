package com.springBootLearning.Prod_ready_features.controllers;

import com.springBootLearning.Prod_ready_features.dtos.PostDto;
import com.springBootLearning.Prod_ready_features.services.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor //all the required args will be assigned using this constructor
public class PostController {

    //we are using the PstService interface this helps us to achieve code modularity and extensibility
    private final PostService postService; //no need to create the constructor we have using RequiredArgsConstructor annotation

    @GetMapping
    public List<PostDto> getAllPosts(){

        return postService.getAllPosts();

    }

    @GetMapping("/{postId}")
    public PostDto findPostById(@PathVariable Long postId){

        return postService.getPostById(postId);

    }

    @PostMapping
    public PostDto createNewPost(@RequestBody @Valid PostDto postDto){

        return postService.createNewPost(postDto);

    }

    @PutMapping("/{id}")
    public PostDto updatePostId(@RequestBody @Valid PostDto postDto , @PathVariable Long id){

        return postService.updatePostById(postDto , id);

    }

}
