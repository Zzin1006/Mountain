package com.mountain.mountain.controller.comment;


import com.mountain.mountain.controller.comment.dto.RegisterCommentDTO;
import com.mountain.mountain.controller.comment.dto.ResponseCommentDTO;
import com.mountain.mountain.controller.community.dto.ResponseCommuDTO;
import com.mountain.mountain.domain.category.dao.CategoryRepository;
import com.mountain.mountain.domain.category.model.Category;
import com.mountain.mountain.domain.category.service.CategoryService;
import com.mountain.mountain.domain.comment.service.CommentService;
import com.mountain.mountain.domain.community.model.Community;
import com.mountain.mountain.domain.community.service.CommunityService;
import com.mountain.mountain.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/communities")
public class CommentController {


    @Autowired
    CommentService commentService;

    @Autowired
    CommunityService communityService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    // 댓글 등록
    @PostMapping("/{cateNo}/{commuPostNum}/comments")
    public ResponseCommentDTO postComment (
            @PathVariable(value = "cateNo") Long cateNo,
            @PathVariable(value = "commuPostNum") Long commuPostNum,
            @RequestBody RegisterCommentDTO registerCommentDTO,
            Authentication authentication) {

        User user = ((User) authentication.getPrincipal());
        Community community = communityService.findCommunityByNo(commuPostNum);
        
        return new ResponseCommentDTO(commentService.postComment(user, cateNo, community,registerCommentDTO));

    }




}
