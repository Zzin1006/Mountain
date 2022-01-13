package com.mountain.mountain.controller.comment.dto;

import com.mountain.mountain.controller.category.dto.ResponseCategoryDTO;
import com.mountain.mountain.controller.community.dto.ResponseCommuDTO;
import com.mountain.mountain.controller.user.dto.UserDTO;
import com.mountain.mountain.domain.category.model.Category;
import com.mountain.mountain.domain.comment.model.Comment;
import com.mountain.mountain.domain.community.model.Community;
import com.mountain.mountain.domain.mountain.model.Mountain;
import com.mountain.mountain.domain.user.model.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class ResponseCommentDTO {

    private Long commentNo;

    private UserDTO user;

    //private Mountain mountainNo;

    private Long commuNo;

    private ResponseCategoryDTO cateId;

    private String commentContent;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    public ResponseCommentDTO(Comment comment) {
        this.commentNo = comment.getCommentNo();
        this.user = new UserDTO(comment.getUser());
        this.commentContent = comment.getCommentContent();
        this.createdAt = comment.getFstRegDtm();
        this.updateAt = comment.getLstUpdDtm();
        this.cateId = new ResponseCategoryDTO(comment.getCateId());
        this.commuNo = comment.getCommuNo().getCommupostNo();
    }
}





