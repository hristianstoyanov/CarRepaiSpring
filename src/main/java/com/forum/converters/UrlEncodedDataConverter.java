package com.forum.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forum.beans.Image;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Hrisi on 24.11.2018 ã..
 */
public class UrlEncodedDataConverter extends AbstractHttpMessageConverter<Image>{

    private static final FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected boolean supports(Class<?> clazz) {
        return (Image.class == clazz);
    }

    @Override
    protected Image readInternal (Class<? extends Image> clazz,HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        Map<String, String> vals = formHttpMessageConverter.read(null, inputMessage).toSingleValueMap();
        return mapper.convertValue(vals, Image.class);
    }

    @Override
    protected void writeInternal(Image myObject, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
