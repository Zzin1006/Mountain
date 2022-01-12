package com.mountain.mountain.domain.community.service;

import com.mountain.mountain.controller.community.dto.RegisterCommuDTO;
import com.mountain.mountain.controller.community.dto.ResponseCommuDTO;
import com.mountain.mountain.domain.category.model.Category;
import com.mountain.mountain.domain.category.service.CategoryService;
import com.mountain.mountain.domain.community.dao.CommunityRepository;
import com.mountain.mountain.domain.community.model.Community;
import com.mountain.mountain.domain.user.model.User;
import com.mountain.mountain.exception.CustomException;
import com.mountain.mountain.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.swing.undo.CannotUndoException;
import java.util.Optional;

@Service
public class CommunityService {

    @Autowired
    CommunityRepository communityRepository;
    @Autowired
    CategoryService categoryService;


    public Community registerCommunity(RegisterCommuDTO registerCommuDTO ,User user ) {

        Category category = categoryService.findCateByNo(registerCommuDTO);

        Community community = Community.builder()
                .writerId(user)
                .title(registerCommuDTO.getTitle())
                .cateId(category)
                .content(registerCommuDTO.getContent())
                .viewCount(0L)
                .build();

        return communityRepository.save(community);
    }




    public Page<Community> findAll(Specification<Community> spec, Pageable pageable) {
        Page<Community> communities = communityRepository.findAll(spec, pageable);

        if (communities.isEmpty())
            throw new CustomException(ErrorCode.NOT_FOUND_COMMUNITY);

        return communities;
    }


    public void deleteCommunity(User user, Long commupostNo) {
        Community commu = communityRepository.findById(commupostNo)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMUNITY));

        if(commu.getWriterId().getId().equals(user.getId())) {
            communityRepository.delete(commu);
        } else {
            throw new CustomException(ErrorCode.FORBIDDEN_USER);
        }
    }




    public Community findCommunityByNo(Long commupostNo) {

        Community commu = communityRepository.findById(commupostNo)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_COMMUNITY));


            commu.setViewCount(commu.getViewCount()+1L);
            communityRepository.save(commu);
            return commu;
    }
}

