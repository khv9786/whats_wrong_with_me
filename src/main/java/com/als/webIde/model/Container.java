package com.als.webIde.model;

import com.als.webIde.domain.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.ToOne;

@Entity
@Getter
@NoArgsConstructor
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "containerId")
    private String containerId;//컨테이너 아이디

    @OneToOne
    @JoinColumn(name = "userPk")
    private Member user_pk;// 회원 번호

    @Builder
    public Container(String containerId) {
        this.containerId = containerId;
    }
}