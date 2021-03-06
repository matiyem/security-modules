package com.example.customlogouthandler;


import com.example.ServletInitializer;
import com.example.customLogOutHandler.services.UserCache;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { ServletInitializer.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SqlGroup({ @Sql(value = "classpath:before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD), @Sql(value = "classpath:after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD) })
@TestPropertySource(locations="classpath:application.properties")
class CustomLogoutHandlerIntegrationTest {
//First, we check that the cache is empty
//Next, we authenticate a user via the withBasicAuth method
//Now we can verify the user data and language value retrieved
//Consequently, we can verify that the user must now be in the cache
//Again, we check the user data by hitting the language endpoint and using a session cookie
//Finally, we verify logging out the user
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserCache userCache;

    @LocalServerPort
    private int port;

    @Test
    public void whenLogin_thenUseUserCache() {
        // User cache should be empty on start
        assertThat(userCache.size()).isEqualTo(0);

        // Request using first login
        ResponseEntity<String> response = restTemplate.withBasicAuth("user", "pass")
            .getForEntity(getLanguageUrl(), String.class);

        assertThat(response.getBody()).contains("english");

        // User cache must contain the user
        assertThat(userCache.size()).isEqualTo(1);

        // Getting the session cookie
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", response.getHeaders()
            .getFirst(HttpHeaders.SET_COOKIE));

        // Request with the session cookie
        response = restTemplate.exchange(getLanguageUrl(), HttpMethod.GET, new HttpEntity<String>(requestHeaders), String.class);
        assertThat(response.getBody()).contains("english");

        // Logging out using the session cookies
        response = restTemplate.exchange(getLogoutUrl(), HttpMethod.GET, new HttpEntity<String>(requestHeaders), String.class);
        assertThat(response.getStatusCode()
            .value()).isEqualTo(200);
    }

    @Test
    public void whenLogout_thenCacheIsEmpty() {
        // User cache should be empty on start
        assertThat(userCache.size()).isEqualTo(0);

        // Request using first login
        ResponseEntity<String> response = restTemplate.withBasicAuth("user", "pass")
            .getForEntity(getLanguageUrl(), String.class);

        assertThat(response.getBody()).contains("english");

        // User cache must contain the user
        assertThat(userCache.size()).isEqualTo(1);

        // Getting the session cookie
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", response.getHeaders()
            .getFirst(HttpHeaders.SET_COOKIE));

        // Logging out using the session cookies
        response = restTemplate.exchange(getLogoutUrl(), HttpMethod.GET, new HttpEntity<String>(requestHeaders), String.class);
        assertThat(response.getStatusCode()
            .value()).isEqualTo(200);

        // User cache must be empty now
        // this is the reaction on custom logout filter execution
        assertThat(userCache.size()).isEqualTo(0);

        // Assert unauthorized request
        response = restTemplate.exchange(getLanguageUrl(), HttpMethod.GET, new HttpEntity<String>(requestHeaders), String.class);
        assertThat(response.getStatusCode()
            .value()).isEqualTo(401);
    }

    private String getLanguageUrl() {
        return "http://localhost:" + port + "/user/language";
    }

    private String getLogoutUrl() {
        return "http://localhost:" + port + "/user/logout";
    }

}
