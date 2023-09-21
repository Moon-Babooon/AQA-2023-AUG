package org.prog.old;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.classic.MinimalHttpClient;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.HttpEntities;
import org.prog.dto.NameDto;
import org.prog.dto.ResultsDto;
import org.prog.dto.UserDto;
import org.testng.annotations.Test;

import java.io.IOException;

public class ApacheTests {

    public static final String URL = "https://randomuser.me/api/?inc=gender,name,nat&noinfo&results=5";

    @SneakyThrows
    @Test
    public void apacheApiTest() throws IOException {
        MinimalHttpClient httpClient = HttpClients.createMinimal();

        HttpGet get = new HttpGet(URL);
        get.setHeader("", "");
        get.setEntity(HttpEntities.create("content"));

        CloseableHttpResponse response = httpClient.execute(get);
        String responseBody = EntityUtils.toString(response.getEntity());
        ResultsDto dto = objectMapper().readValue(responseBody, ResultsDto.class);
        System.out.println(dto.getResults().size());
        UserDto test = UserDto.builder().name(NameDto.builder().last("test").first("test").build())
                        .nat("test").build();
        System.out.println(objectMapper().writeValueAsString(test));
    }

    @SneakyThrows
    @Test
    public void apacheApiTest2() throws IOException {
        MinimalHttpClient httpClient = HttpClients.createMinimal();
        HttpPost post = new HttpPost(URL);
        post.setEntity(HttpEntities.create("request body"));
        post.setHeader("x-Client", "org.prog.client");
        CloseableHttpResponse response = httpClient.execute(post);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    private ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
