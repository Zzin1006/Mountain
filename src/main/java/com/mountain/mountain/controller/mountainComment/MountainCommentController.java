package com.mountain.mountain.controller.mountainComment;


import com.mountain.mountain.controller.mountainComment.dto.RegisterMTCommentDTO;
import com.mountain.mountain.controller.mountainComment.dto.ResponseMTCommentDTO;
import com.mountain.mountain.domain.comment.service.CommentService;
import com.mountain.mountain.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mountains")
public class MountainCommentController {


    @Autowired
    CommentService commentService;


    // MT 댓글작성
    @PostMapping("/{mountainNo}/comments")
    public ResponseMTCommentDTO createMTComment (@PathVariable(value = "mountainNo")Long mountainNo,
                                                 @RequestBody RegisterMTCommentDTO registerMTCommentDTO,
                                                 Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        return new ResponseMTCommentDTO(commentService.createMTComment(user, mountainNo, registerMTCommentDTO));

    }

    // MT 댓글 삭제
    @DeleteMapping("/{mountainNo}/comments/{commentNo}")
    public void deleteMtComment (
            @PathVariable(value = "mountainNo")Long mountainNo,
            @PathVariable(value = "commentNo")Long commentNo,
            Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        commentService.deleteMTComment(user, mountainNo, commentNo);
    }

}
