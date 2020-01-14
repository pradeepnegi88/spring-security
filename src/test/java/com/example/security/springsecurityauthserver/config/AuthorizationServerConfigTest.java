package com.example.security.springsecurityauthserver.config;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AuthorizationServerConfigTest {
    @Autowired
    private MockMvc mockMvc;
    String urlTemplate = "/oauth/token";

    @Test
    void itShouldAuthenticate() throws Exception {
        MultiValueMap<String, String> params = getStringStringMultiValueMap();

        String base64ClientCredentials = new String(Base64.encodeBase64("12345:12345".getBytes()));
        ResultActions result
                = mockMvc.perform(post(urlTemplate)
                .params(params)
                .header("Authorization", "Basic " + base64ClientCredentials)
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        assertNotNull(jsonParser.parseMap(resultString).get("access_token").toString());
    }

    @Test
    void itShouldUnAuthenticate() throws Exception {
        String base64ClientCredentials = new String(Base64.encodeBase64("23e23e:23434".getBytes()));
        mockMvc.perform(post(urlTemplate)
                .params(getStringStringMultiValueMap())
                .header("Authorization", "Basic " + base64ClientCredentials)
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isUnauthorized());

    }

    private MultiValueMap<String, String> getStringStringMultiValueMap() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", "admin2");
        params.add("password", "admin2");
        return params;
    }

}