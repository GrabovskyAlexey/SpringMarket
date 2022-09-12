package ru.grabovsky.productback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test"})
public class FunctionalTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    public RequestBuilder createRequest() {
        return new RequestBuilder();
    }

    public class RequestBuilder {

        private final Map<String, Object> params;

        private String url;

        public RequestBuilder() {
            params = new HashMap<>();
        }

        public RequestBuilder url(String url) {
            this.url = url;
            return this;
        }

        public RequestBuilder param(String parameter, Object value) {
            params.put(parameter, value);
            return this;
        }

        public<T> T get(Class<T> cls) {
            return testRestTemplate.getForObject(url, cls, params);
        }

        public <T> T get(ParameterizedTypeReference<T> typeReference) {
            return testRestTemplate.exchange(url, HttpMethod.GET, null, typeReference, params)
                    .getBody();
        }

        public <T> T post(ParameterizedTypeReference<T> typeReference, HttpEntity<?> entity) {
            return testRestTemplate.exchange(url, HttpMethod.POST, entity, typeReference, params)
                    .getBody();
        }

        public <T> T put(ParameterizedTypeReference<T> typeReference, HttpEntity<?> entity) {
            return testRestTemplate.exchange(url, HttpMethod.PUT, entity, typeReference, params)
                    .getBody();
        }

        public <T> T delete(ParameterizedTypeReference<T> typeReference) {
            return testRestTemplate.exchange(url, HttpMethod.DELETE, null, typeReference, params)
                    .getBody();
        }
    }

    public String getResource(String name) {
        String className = getClass().getSimpleName();
        String directory = className + FileSystems.getDefault().getSeparator() + name;

        try (InputStream inputStream = getClass().getResourceAsStream(directory)){
            assert inputStream != null;
            byte[] bytes = inputStream.readAllBytes();
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
