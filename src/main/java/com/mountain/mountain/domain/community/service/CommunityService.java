package com.mountain.mountain.domain.community.service;

import com.mountain.mountain.controller.community.dto.RegisterCommuDTO;
import com.mountain.mountain.domain.category.model.Category;
import com.mountain.mountain.domain.community.dao.CommunityRepository;
import com.mountain.mountain.domain.community.model.Community;
import com.mountain.mountain.domain.user.model.User;
import com.mountain.mountain.exception.CustomException;
import com.mountain.mountain.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityService {

    @Autowired
    CommunityRepository communityRepository;

    public void registerCommu(User user, Long cateId, RegisterCommuDTO registerCommuDTO) {

        Community commu = Community.builder()
                .writerId(user)
                .title(registerCommuDTO.getTitle())
                .content(registerCommuDTO.getContent())
                .build();

        communityRepository.save(commu);
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
}