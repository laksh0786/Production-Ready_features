package com.springBootLearning.Prod_ready_features.services;

import com.springBootLearning.Prod_ready_features.dtos.PostDto;

import java.util.List;


public interface PostService {

    List<PostDto> getAllPosts();

    PostDto createNewPost(PostDto postDto);

    PostDto getPostById(Long id);

}
