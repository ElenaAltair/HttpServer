package ru.netology;

import org.apache.http.NameValuePair;

import java.util.List;
import java.util.stream.Collectors;

public class Request {
    private String method;
    private String path;
    private List<String> headers;
    private String body;
    private List<NameValuePair> queryParams;

    public Request(String method, String path, List<String> headers, String body) {
        this.method = method;
        this.path = path;
        this.headers = headers;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public void setQueryParams(List<NameValuePair> params) {
        this.queryParams = params;
    }


    public List<NameValuePair> getQueryParams() {
        return queryParams;
    }

    public String getQueryParam(String name) {
        return queryParams.stream()
                .filter(p -> p.getName().equals(name))
                .map(p -> p.getValue())
                .collect(Collectors.joining(", "));
    }


    @Override
    public String toString() {
        return "Request{" +
                "method='" + method + '\'' +
                ", \npath='" + path + '\'' +
                ", \nheaders=" + headers +
                ", \nbody='" + body + '\'' +
                ", \nqueryParams=" + queryParams +
                '}';
    }
}
