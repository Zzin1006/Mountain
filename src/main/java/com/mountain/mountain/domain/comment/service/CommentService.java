package com.mountain.mountain.domain.comment.service;

import com.mountain.mountain.controller.communitycomment.dto.RegisterCommuCommentDTO;
import com.mountain.mountain.domain.category.dao.CategoryRepository;
import com.mountain.mountain.domain.category.model.Category;
import com.mountain.mountain.domain.comment.dao.CommentRespository;
import com.mountain.mountain.domain.comment.model.Comment;
import com.mountain.mountain.domain.community.dao.CommunityRepository;
import com.mountain.mountain.domain.community.model.Community;
import com.mountain.mountain.domain.user.model.User;
import com.mountain.mountain.exception.CustomException;
import com.mountain.mountain.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LongSummaryStatistics;
import java.util.Optional;

@Service
@Slf4j
public class CommentService {

    @Autowired
    CommentRespository commentRespository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CommunityRepository communityRepository;


    @Transactional
    public Comment createComment(User user, Community community, RegisterCommuCommentDTO registerCommentDTO) {


        Comment comment = Comment.builder()
                .commentContent(registerCommentDTO.getContent())
                .commuNo(community)
                .user(user)
                .build();

        return commentRespository.save(comment);
    };

    @Transactional
    public Page<Comment> getCommunityCommentList(Community community, Pageable pageable) {
        return commentRespository.findByCommuNo(community,pageable);
    }


    @Transactional
    public void deleteCommunityComment(User user, Long commuPostNum , Long commentNo) {

        Community community =
                communityRepository.findById(commuPostNum)
                        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMUNITY));

        Comment comment =
                commentRespository.findById(commentNo)
                        .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_REPLY));


        if(!comment.getUser().getId().equals(user.getId())) {
            throw new CustomException(ErrorCode.FORBIDDEN_USER);
        } else if(!comment.getCommuNo().equals(community)){
            throw new CustomException(ErrorCode.BAD_REQUEST_PARAM);
        } else {
            commentRespository.delete(comment);
        }
    }

}



