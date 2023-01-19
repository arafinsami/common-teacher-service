package com.eteacher.service.entity.ntrca;

import com.eteacher.service.entity.BaseEntity;
import com.eteacher.service.entity.commonteacher.Employee;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "EMPLOYEE_PUBLICATION")
public class EmployeePublication extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_PUBLICATION_ID")
    private Long id;

    @Column(name = "TOPIC")
    private String topic;

    @Column(name = "TOPIC_BN")
    private String topicBn;

    @Column(name = "TOPIC_DESCRIPTION")
    private String topicDescription;

    @Column(name = "TOPIC_DESCRIPTION_BN")
    private String topicDescriptionBn;

    @Column(name = "IS_RECOGNIZED")
    private Boolean isRecognized;

    @Column(name = "RECOGNIZED_AUTHORITIES")
    private String recognizedAuthorities;

    @Column(name = "PUBLISHED_URL")
    private String publishedUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;
}
