package com.example.fooddeliveryapp.errorhandler;

import org.springframework.core.log.LogFormatUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.UnknownHttpStatusCodeException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class RestTemplateResponseErrorHandler extends DefaultResponseErrorHandler {
    @Override
    protected void handleError(ClientHttpResponse response, HttpStatus statusCode) throws IOException {
        String statusText = response.getStatusText();
        HttpHeaders headers = response.getHeaders();
        byte[] body = getResponseBody(response);
        Charset charset = getCharset(response);
        String message = getErrorMessageFromResponse(statusCode.value(), statusText, body, charset);

        switch (statusCode.series()){
            case CLIENT_ERROR:
                throw HttpClientErrorException.create(message, statusCode, statusText, headers, body, charset);
            case SERVER_ERROR:
                throw HttpServerErrorException.create(message, statusCode, statusText, headers, body, charset);
            default:
                throw new UnknownHttpStatusCodeException(message, statusCode.value(), statusText, headers, body, charset);
        }
    }

    private String getErrorMessageFromResponse(
            int rawStatusCode, String statusText, @Nullable byte[] responseBody, @Nullable Charset charset
    ){
        String preface = rawStatusCode + " " + statusText + ": ";
        if(ObjectUtils.isEmpty(responseBody)){
            return preface + "[no body]";
        }
        charset = (charset != null ? charset : StandardCharsets.UTF_8);
        String bodyText = new String(responseBody,charset);
        bodyText = LogFormatUtils.formatValue(bodyText,true);
        return preface + bodyText;
    }
}
