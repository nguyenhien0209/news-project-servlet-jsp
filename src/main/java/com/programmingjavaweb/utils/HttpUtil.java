package com.programmingjavaweb.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpUtil {

    private String value;

    public HttpUtil (String value) {
        this.value = value;
    }
    //parse JSON to String JSON
    //return chinh ten cua class va dinh kem them JSON String
    public static HttpUtil of(BufferedReader reader)  {
        StringBuilder sb = new StringBuilder();
        try {
            String line;
            while((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HttpUtil(sb.toString());
    }

    //Parse JSON String to Model, su dung trong truong hop tong quat
    public <T> T toModel(Class<T> tClass) {
        try {
            return new ObjectMapper().readValue(value, tClass);
        } catch (IOException e ) {
            e.printStackTrace();
            return null;
        }
    }
}
