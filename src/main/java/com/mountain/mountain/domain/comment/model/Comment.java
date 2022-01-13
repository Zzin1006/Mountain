package com.mountain.mountain.domain.comment.model;


import com.mountain.mountain.domain.category.model.Category;
import com.mountain.mountain.domain.community.model.Community;
import com.mountain.mountain.domain.mountain.model.Mountain;
import com.mountain.mountain.domain.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentNo;

    @OneToOne
    @JoinColumn(name = "uid")
    private User user;

    @ManyToOne // 데이터 n : 1 매핑 하나의 산에 여러개 댓글
    @JoinColumn(name = "mountain_no")
    private Mountain mountainNo;

    @ManyToOne
    @JoinColumn(name = "commu_no")
    private Community commuNo;

    @ManyToOne
    @JoinColumn(name = "cate_id")
    private Category cateId;

    @Column(name = "comment_content", length = 300)
    private String commentContent;

    @Column(name = "fst_reg_dtm")
    @CreatedDate
    private LocalDateTime fstRegDtm;

    @Column(name = "lst_reg_dtm")
    @UpdateTimestamp
    private LocalDateTime lstUpdDtm;


    @Builder
    public Comment(Long commentNo, User user, Mountain mountainNo, Community commuNo, Category cateId, String commentContent, LocalDateTime fstRegDtm, LocalDateTime lstUpdDtm) {
        this.commentNo = commentNo;
        this.user = user;
        this.mountainNo = mountainNo;
        this.commuNo = commuNo;
        this.cateId = cateId;
        this.commentContent = commentContent;
        this.fstRegDtm = fstRegDtm;
        this.lstUpdDtm = lstUpdDtm;
    }
}
