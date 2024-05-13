package com.als.webIde.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "file")
@Getter @Setter @NoArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_pk")
    private Long filePk;

    @Column(name = "user_pk", nullable = false)
    private Long userPk;

    @Column(name = "suffix_file", nullable = false)
    private String suffixFile;

    @Column(name = "content_cd", nullable = false)
    private String contentCd;

    @Column(name = "file_title", nullable = false)
    private String fileTitle;

    @Column(name = "path", nullable = false)
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "container_pk", insertable = false, updatable = false)
    private Container container;
}