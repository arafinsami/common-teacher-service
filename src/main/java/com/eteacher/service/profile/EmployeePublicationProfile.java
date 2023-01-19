package com.eteacher.service.profile;

import com.eteacher.service.entity.ntrca.EmployeePublication;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeePublicationProfile {

    private Long id;

    private String topic;

    private String topicBn;

    private String topicDescription;

    private String topicDescriptionBn;

    private Boolean isRecognized;

    private String recognizedAuthorities;

    private String publishedUrl;

    public EmployeePublication to() {
        EmployeePublication publication = new EmployeePublication();
        publication.setId(id);
        publication.setTopic(topic);
        publication.setTopicBn(topicBn);
        publication.setTopicDescription(topicDescription);
        publication.setTopicDescriptionBn(topicDescriptionBn);
        publication.setIsRecognized(isRecognized);
        publication.setRecognizedAuthorities(recognizedAuthorities);
        publication.setPublishedUrl(publishedUrl);
        return publication;
    }
}
