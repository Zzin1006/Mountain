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

    // 커뮤니티 글 등록
    @PostMapping("/{cateNo}")
    public void registerCommu(@PathVariable(value = "cateNo") Long cateId,
                              @RequestBody RegisterCommuDTO registerCommuDTO, Authentication authentication) {

        // header에 user포함, RequestParam으로 받을 필요x
        User user = ((User) authentication.getPrincipal()); // 인증유저
        // 어떤 카테고리에 글을 쓸 것인지
        Category cate = categoryService.findCateByNo(cateId);
        communityService.registerCommu(user, cateId, registerCommuDTO);

    }
}
