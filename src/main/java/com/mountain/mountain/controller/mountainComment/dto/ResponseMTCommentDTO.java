package com.mountain.mountain.controller.mountainComment.dto;


import com.mountain.mountain.controller.community.dto.CommunityDTO;
import com.mountain.mountain.controller.user.dto.UserDTO;
import com.mountain.mountain.domain.comment.model.Comment;
import com.mountain.mountain.domain.community.model.Community;
import com.mountain.mountain.domain.mountain.model.Mountain;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseMTCommentDTO {

    private Long commentNo;

    private UserDTO user;

    private Mountain mountainNo;

    private String commentContent;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;


    public ResponseMTCommentDTO(Comment mtComment) {
        this.commentNo = mtComment.getCommentNo();
        this.user = new UserDTO(mtComment.getUser());
        this.commentContent = mtComment.getCommentContent();
        this.createdAt = mtComment.getFstRegDtm();
        this.updateAt = mtComment.getLstUpdDtm();
        this.mountainNo = mtComment.getMountainNo();
    }

}
