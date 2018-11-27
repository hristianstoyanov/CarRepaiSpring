package com.forum;

import com.forum.converters.UrlEncodedDataConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Hrisi on 24.11.2018 ã..
 */
@SpringBootApplication
@Configuration
public class CarForum implements WebMvcConfigurer{

    public static void main(String... args) {
        SpringApplication.run(CarForum.class, args);
    }

    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
    }

    private UrlEncodedDataConverter converter() {
        UrlEncodedDataConverter converter = new UrlEncodedDataConverter();
        MediaType mediaType = new MediaType("application", "x-www-form-urlencoded", Charset.forName("UTF-8"));
        converter.setSupportedMediaTypes(Arrays.asList(mediaType));
        return converter;
    }

}
