package com.mountain.mountain.controller.community;

import com.mountain.mountain.controller.community.dto.RegisterCommuDTO;
import com.mountain.mountain.domain.category.model.Category;
import com.mountain.mountain.domain.category.service.CategoryService;
import com.mountain.mountain.domain.community.dao.CommunityRepository;
import com.mountain.mountain.domain.community.model.Community;
import com.mountain.mountain.domain.community.service.CommunityService;
import com.mountain.mountain.domain.user.model.User;
import com.mountain.mountain.exception.CustomException;
import com.mountain.mountain.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/communities")
public class CommunityController {

    @Autowired
    CommunityService communityService;

    @Autowired
    CategoryService categoryService;



    // 커뮤니티글 삭제
    @DeleteMapping("/{commupostNo}")
    public void deleteCommunity(@PathVariable(value = "commupostNo") Long commupostNo, Authentication authentication) {
        User user = ((User) authentication.getPrincipal());

        communityService.deleteCommunity(user,commupostNo);

    }

}
