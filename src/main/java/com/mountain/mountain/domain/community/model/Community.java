package com.mountain.mountain.domain.community.model;


import com.mountain.mountain.domain.category.model.Category;
import com.mountain.mountain.domain.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="community")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commupostNo;

    @OneToOne
    @JoinColumn(name = "write_id")
    private User writerId;

    @OneToOne
    @JoinColumn(name = "cate_id")
    private Category cateId;

    @Column(name = "content")
    private String content;

    @Column(name = "fst_reg_dtm")
    @CreatedDate
    private LocalDateTime fstRegDtm;

    @Column(name = "lst_reg_dtm")
    @LastModifiedBy
    private LocalDateTime lstUpdDtm;

    @Column(name = "view_count")
    private Long viewCount;

    @Column(name = "title")
    private String title;

    @Builder
    public Community(Long commupostNo, User writerId, Category cateId, String content, LocalDateTime fstRegDtm, LocalDateTime lstUpdDtm, Long viewCount, String title) {
        this.commupostNo = commupostNo;
        this.writerId = writerId;
        this.cateId = cateId;
        this.content = content;
        this.fstRegDtm = fstRegDtm;
        this.lstUpdDtm = lstUpdDtm;
        this.viewCount = viewCount;
        this.title = title;
    }
}
