package com.eteacher.service.audit;

import com.eteacher.service.entity.ntrca.EmployeePublication;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeePublicationAudit {

    private Long id;

    private String topic;

    private String topicBn;

    private String topicDescription;

    private String topicDescriptionBn;

    private Boolean isRecognized;

    private String recognizedAuthorities;

    private String publishedUrl;

    public static EmployeePublicationAudit audit(EmployeePublication publication) {
        EmployeePublicationAudit audit = new EmployeePublicationAudit();
        audit.setId(publication.getId());
        audit.setTopic(publication.getTopic());
        audit.setTopicBn(publication.getTopicBn());
        audit.setTopicDescription(publication.getTopicDescription());
        audit.setTopicDescriptionBn(publication.getTopicDescriptionBn());
        audit.setIsRecognized(publication.getIsRecognized());
        audit.setRecognizedAuthorities(publication.getRecognizedAuthorities());
        audit.setPublishedUrl(publication.getPublishedUrl());
        return audit;
    }
}
