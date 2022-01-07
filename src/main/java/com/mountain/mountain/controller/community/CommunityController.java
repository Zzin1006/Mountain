package com.mountain.mountain.controller.community;

import com.mountain.mountain.controller.category.dto.ResponseCategoryDTO;
import com.mountain.mountain.controller.community.dto.RegisterCommuDTO;
import com.mountain.mountain.controller.community.dto.ResponseCommuDTO;
import com.mountain.mountain.domain.category.model.Category;
import com.mountain.mountain.domain.category.service.CategoryService;
import com.mountain.mountain.domain.community.model.Community;
import com.mountain.mountain.domain.community.service.CommunityService;
import com.mountain.mountain.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/communities")
public class CommunityController {

    @Autowired
    CommunityService communityService;

    @Autowired
    CategoryService categoryService;





    //// 커뮤니티 글 조회
    @GetMapping("")
    public Page<ResponseCommuDTO> getCommunityList(Pageable pageable){
        return communityService.findAllCommunity(pageable).map(Community -> new ResponseCommuDTO(Community));
    }

}
