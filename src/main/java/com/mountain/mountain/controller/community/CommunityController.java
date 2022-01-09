package com.mountain.mountain.controller.community;

import com.mountain.mountain.controller.community.dto.RegisterCommuDTO;
import com.mountain.mountain.controller.community.dto.ResponseCommuDTO;
import com.mountain.mountain.controller.community.specification.CommunitySpecification;
import com.mountain.mountain.domain.category.model.Category;
import com.mountain.mountain.domain.category.service.CategoryService;
import com.mountain.mountain.domain.community.model.Community;
import com.mountain.mountain.domain.community.service.CommunityService;
import com.mountain.mountain.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/communities")
public class CommunityController {

    @Autowired
    CommunityService communityService;


    // 커뮤니티 글 등록
    @PostMapping("")
    public ResponseCommuDTO postCommunity(
            @RequestBody RegisterCommuDTO registerCommuDTO, Authentication authentication) {
        User user = ((User) authentication.getPrincipal());
        return new ResponseCommuDTO(communityService.registerCommunity(registerCommuDTO, user));
    }


    // 커뮤니티 글 조회
    @GetMapping("")
    public Page<ResponseCommuDTO> getCommunityList(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String title,
            Pageable pageable) {

        Specification<Community> spec = ((root, query, criteriaBuilder) -> null);

        if(id != null) spec = spec.and(CommunitySpecification.equalCateId(id));
        if(title != null) spec = spec.and(CommunitySpecification.equalTitle(title));

        return communityService.findCommunities(pageable).map(Community -> new ResponseCommuDTO(Community));
    }

}
