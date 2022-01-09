package com.mountain.mountain.controller.community.dto;

import com.mountain.mountain.domain.category.model.Category;
import com.mountain.mountain.domain.community.model.Community;
import com.mountain.mountain.domain.user.model.User;
import lombok.Builder;
import lombok.Data;

@Data
public class RegisterCommuDTO {



    private Long cateId;

    private String content;

    private String title;


}
