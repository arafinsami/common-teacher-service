package com.eteacher.service.client;

import com.eteacher.service.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "CommonSettingRepository", configuration = FeignConfig.class, url = "${commonSettingService}", decode404 = true)
//@FeignClient(contextId = "foreignContextId", name = "foreignscholarship-service", configuration = RestClientConfiguration.class)
public interface CommonRestClient {

   /* @RequestMapping(method = RequestMethod.GET, value = "/api/v1/university/find/{id}")
    String getCountryById(@PathVariable Long id);*/

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/country/find/{id}")
    String getCountryById(@PathVariable Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/division/find/{id}")
    String getDivisionById(@PathVariable Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/district/find/{id}")
    String getDistrictById(@PathVariable Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/subject/find/{id}")
    String getSubjectById(@PathVariable Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/designation/find/{id}")
    String getDesignationById(@PathVariable Long id);

}
