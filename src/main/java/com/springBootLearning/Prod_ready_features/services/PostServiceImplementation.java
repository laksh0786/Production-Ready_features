package com.springBootLearning.Prod_ready_features.services;

import com.springBootLearning.Prod_ready_features.dtos.PostDto;
import com.springBootLearning.Prod_ready_features.entities.PostEntity;
import com.springBootLearning.Prod_ready_features.exceptions.ResourceNotFoundException;
import com.springBootLearning.Prod_ready_features.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImplementation implements PostService{

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostServiceImplementation(PostRepository postRepository, ModelMapper modelMapper){
        this.postRepository = postRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<PostDto> getAllPosts(){

        return postRepository.findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity , PostDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public PostDto createNewPost(PostDto postDto) {

        PostEntity inpPost = modelMapper.map(postDto , PostEntity.class);

        return modelMapper.map(postRepository.save(inpPost) , PostDto.class);

    }

    @Override
    public PostDto getPostById(Long id) {

        PostEntity postEntity  = postRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id : " + id));

        return modelMapper.map(postEntity , PostDto.class);

    }

}
