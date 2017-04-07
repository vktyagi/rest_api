package com.demo.console.wrapper.auth;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public interface AuthService {

    public String getRankerResponse(String url, String input) throws IOException, ParseException;

    public String deleteRankerCampaign(String url) throws IOException, ParseException;

    public String getRankerorVAResults(String url) throws IOException, ParseException;

}
