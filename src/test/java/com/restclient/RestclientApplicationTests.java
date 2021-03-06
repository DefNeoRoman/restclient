package com.restclient;

import com.restclient.dto.UserTO;
import com.restclient.model.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashSet;
import java.util.Set;

public class RestclientApplicationTests {
    private final
    Logger logger = LoggerFactory.getLogger(RestclientApplicationTests.class);
    private final
    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void getAllUsers() {
        String fooResourceUrl = "http://localhost:8080/rest/user/all";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
        logger.info(response.toString());
    }

    @Test
    public void getOneUser() {
        String fooResourceUrl = "http://localhost:8080/rest/user/edit?id=1";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
        logger.info(response.toString());
    }

    @Test
    public void deleteOneUser() {
        String entityUrl = "http://localhost:8080/rest/user/?id=2";
        restTemplate.delete(entityUrl);
        createOneUser();
        logger.info("User was deleted with id=1 and added in end");
    }

    @Test
    public void createOneUser() {
        User newEmployee = new User();
        newEmployee.setName("fromRestClient");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> requestBody = new HttpEntity<>(newEmployee, headers);
        restTemplate.postForObject("http://localhost:8080/rest/user", requestBody, User.class);
        logger.info("User was created {}", newEmployee.toString());

    }

    @Test
    public void editOneUser() {
        UserTO updatedInstance = new UserTO();
        String resourceUrl = "http://localhost:8080/rest/user/edit";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(resourceUrl)
                .queryParam("role_user", true)
                .queryParam("role_admin", true);
        updatedInstance.setId(3L);
        updatedInstance.setName("nEditMethod");
        Set<Long> roleIDs = new HashSet<>();
        roleIDs.add(2L);
        updatedInstance.setRolesID(roleIDs);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserTO> requestUpdate = new HttpEntity<>(updatedInstance, headers);
        restTemplate.exchange(resourceUrl,HttpMethod.PUT, requestUpdate, User.class);
    }
}
