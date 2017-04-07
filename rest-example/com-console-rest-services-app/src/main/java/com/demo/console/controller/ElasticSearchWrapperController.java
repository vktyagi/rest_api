package com.demo.console.controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.console.services.ElasticSearchWarapperService;
import com.demo.console.wrapper.exception.BadRequestParameterException;

@RestController
@RequestMapping(value = "/search", produces = "application/json;charset=UTF-8")
public class ElasticSearchWrapperController {
    private static final Logger LOGGER = Logger.getLogger(ElasticSearchWrapperController.class);

    @Autowired
    private ElasticSearchWarapperService elasticService;

    @RequestMapping(value = { "", "/*", "/*/*", "/*/*/*" }, method = RequestMethod.OPTIONS)
    public String optionsDefault() {
	return "Server OK";
    }

    @RequestMapping(value = "mri", method = RequestMethod.GET)
    @ResponseBody
    public String searchMriSegment(@RequestParam(value = "query", required = true) String mriQuery, @RequestParam(value = "offset", required = false) Integer offset,
	    @RequestParam(value = "limit", required = false) Integer limit) throws IOException, ParseException {
	LOGGER.info("Getting MRI Search Value :::" + mriQuery);
	if (mriQuery.length() < 1) {
	    throw new BadRequestParameterException("query string parameter must be at least one character");
	}
	Integer off, lim;
	if (offset != null) {
	    off = offset;
	} else {
	    off = 0;
	}
	if (limit != null) {
	    lim = limit;
	} else {
	    lim = 10;
	}
	return elasticService.searchMriSegment(mriQuery, off, lim);
    }

    @RequestMapping(value = "audience", method = RequestMethod.GET)
    @ResponseBody
    public String searchAudience(@RequestParam(value = "query", required = false) String audienceQuery, @RequestParam(value = "offset", required = false) Integer offset,
	    @RequestParam(value = "limit", required = false) Integer limit) throws IOException, ParseException {
	LOGGER.info("Getting Audience Search Value :::" + audienceQuery);
	Integer off, lim;
	if (offset != null) {
	    off = offset;
	} else {
	    off = 0;
	}
	if (limit != null) {
	    lim = limit;
	} else {
	    lim = 10;
	}
	return elasticService.searchAudience(audienceQuery, off, lim);
    }

}
