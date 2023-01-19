package com.eteacher.service.response;


import com.eteacher.service.entity.ntrca.EmployeePublication;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeePublicationResponse {

    private Long id;

    private String topic;

    private String topicBn;

    private String topicDescription;

    private String topicDescriptionBn;

    private Boolean isRecognized;

    private String recognizedAuthorities;

    private String publishedUrl;

    public static EmployeePublicationResponse response(EmployeePublication publication) {
        EmployeePublicationResponse response = new EmployeePublicationResponse();
        response.setId(publication.getId());
        response.setTopic(publication.getTopic());
        response.setTopicBn(publication.getTopicBn());
        response.setTopicDescription(publication.getTopicDescription());
        response.setTopicDescriptionBn(publication.getTopicDescriptionBn());
        response.setIsRecognized(publication.getIsRecognized());
        response.setRecognizedAuthorities(publication.getRecognizedAuthorities());
        response.setPublishedUrl(publication.getPublishedUrl());
        return response;
    }
}
