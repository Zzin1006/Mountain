package com.mountain.mountain.domain.comment.service;

import com.mountain.mountain.controller.communitycomment.dto.RegisterCommuCommentDTO;
import com.mountain.mountain.domain.category.dao.CategoryRepository;
import com.mountain.mountain.domain.category.model.Category;
import com.mountain.mountain.domain.comment.dao.CommentRespository;
import com.mountain.mountain.domain.comment.model.Comment;
import com.mountain.mountain.domain.community.model.Community;
import com.mountain.mountain.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRespository commentRespository;

    @Autowired
    CategoryRepository categoryRepository;


    public Comment createComment(User user, Long cateNo, Community community, RegisterCommuCommentDTO registerCommentDTO) {

        Optional<Category> category = categoryRepository.findById(cateNo);

        Comment comment = Comment.builder()
                .commentContent(registerCommentDTO.getContent())
                .cateId(category.get())
                .commuNo(community)
                .user(user)
                .build();

        return commentRespository.save(comment);
    };


}



