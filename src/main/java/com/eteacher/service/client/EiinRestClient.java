//package com.eteacher.service.client;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//@FeignClient(contextId = "eiinContextId", name = "common-service", configuration = RestClientConfiguration.class)
////@FeignClient(contextId = "foreignContextId", name = "foreignscholarship-service", configuration = RestClientConfiguration.class)
//public interface EiinRestClient {
//
//  @RequestMapping(method = RequestMethod.GET, value = "/api/v1/reason-for-rejection/find/{id}")
//  String getResonById(@PathVariable Long id);
//
//}
