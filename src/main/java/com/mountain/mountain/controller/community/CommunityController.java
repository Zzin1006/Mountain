package com.mountain.mountain.controller.community;

import com.mountain.mountain.controller.community.dto.RegisterCommuDTO;
import com.mountain.mountain.domain.category.model.Category;
import com.mountain.mountain.domain.category.service.CategoryService;
import com.mountain.mountain.domain.community.service.CommunityService;
import com.mountain.mountain.domain.user.model.User;
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


}
