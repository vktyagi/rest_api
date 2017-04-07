package com.demo.console.wrapper.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedClientException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class DefaultExceptionHandler {
  private static final Logger LOGGER = Logger.getLogger(DefaultExceptionHandler.class);

  @ExceptionHandler({ MissingServletRequestParameterException.class,
      UnsatisfiedServletRequestParameterException.class, ServletRequestBindingException.class,
      MissingParameterException.class })
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public Map<String, Object> handleRequestException(Exception ex) {
    LOGGER.error("Error :  ", ex);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("error", "Request Error");
    map.put("error_description", ex.getMessage());
    map.put("error_code", "400");
    return map;
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  public Map<String, Object> handleUnsupportedMediaTypeException(
      HttpMediaTypeNotSupportedException ex) throws IOException {
    LOGGER.error("Error :  ", ex);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("error", "Unsupported Media Type");
    map.put("error_description", ex.getLocalizedMessage());
    return map;
  }

  @ExceptionHandler({ Exception.class })
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public Map<String, Object> handleUncaughtException(Exception ex) throws IOException {
    LOGGER.error("Error :  ", ex);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("error", "Unknown Error");
    if (ex.getCause() != null) {
      map.put("error_description", ex.getCause().getMessage());
    } else {
      map.put("error_description", ex.getMessage());
    }
    map.put("error_code", "500");
    return map;
  }

  @ExceptionHandler(AccessDeniedException.class)
  @ResponseStatus(value = HttpStatus.FORBIDDEN)
  public Map<String, Object> handleForbiddenException(Exception ex) throws IOException {
    LOGGER.error("Error :  ", ex);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("error", "forbidden");
    if (ex.getCause() != null) {
      map.put("error_description", ex.getCause().getMessage());
    } else {
      map.put("error_description", ex.getMessage());
    }
    map.put("error_code", "403");
    return map;
  }

  @ExceptionHandler(UnauthorizedClientException.class)
  @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
  public Map<String, Object> handleUnauthorizedException(Exception ex) throws IOException {
    LOGGER.error("Error :  ", ex);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("error", "unauthorized");
    if (ex.getCause() != null) {
      map.put("error_description", ex.getCause().getMessage());
    } else {
      map.put("error_description", ex.getMessage());
    }
    map.put("error_code", "401");
    return map;
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
  public Map<String, Object> handleMetthodNotAllowedException(Exception ex) throws IOException {
    LOGGER.error("Error :  ", ex);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("error", "Not Allowed");
    if (ex.getCause() != null) {
      map.put("error_description", ex.getCause().getMessage());
    } else {
      map.put("error_description", ex.getMessage());
    }
    map.put("error_code", "405");
    return map;
  }

  @ExceptionHandler({ BadRequestParameterException.class })
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public Map<String, Object> badRequestException(Exception ex) {
    LOGGER.error("Error ", ex);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("error", "Request Error");
    map.put("error_description", ex.getMessage());
    map.put("error_code", HttpStatus.BAD_REQUEST.toString());
    return map;
  }

  @ExceptionHandler({ NotificationException.class, DataParserException.class,
      UnSupportedMessageTypeException.class })
  @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
  public Map<String, String> notAcceptableParameterException(Exception ex) {
    LOGGER.error("Error :  ", ex);
    Map<String, String> errorMap = new LinkedHashMap<String, String>();
    errorMap.put("error_description", ex.getMessage().toString());
    errorMap.put("type", "error");
    errorMap.put("error_reason", "invalid data or message type");

    return errorMap;
  }

  @ExceptionHandler({ NoDataFoundException.class, NoResultException.class,
      NonUniqueResultException.class })
  @ResponseStatus(value = HttpStatus.OK)
  public Map<String, Object> handleResourceNotFoundException(Exception ex) throws IOException {
    LOGGER.error("Error :  ", ex);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("error", "Resource not found!");
    if (ex.getCause() != null) {
      map.put("error_desciption", ex.getCause().getMessage());
    } else {
      map.put("error_desciption", ex.getMessage());
    }
    map.put("error_code", "404");
    return map;
  }

  @ExceptionHandler({ PersistenceException.class, DataIntegrityViolationException.class })
  @ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
  public Map<String, Object> handlePersistenceException(RuntimeException ex) {
    LOGGER.error("Error :  ", ex);
    Map<String, Object> map = new HashMap<String, Object>();

    if (ex.getCause() != null) {
      map.put("error", ex.getCause().getMessage());
      map.put("error_desciption", ex.getCause().getCause().getMessage());
    } else {
      map.put("error", "Invalid data or message type");
      map.put("error_desciption", ex.getMessage());
    }
    map.put("error_code", "417");
    return map;
  }

  @ExceptionHandler({ FileFormatException.class })
  @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
  public Map<String, Object> handleFileTemplateException(FileFormatException ex) {
    LOGGER.error("FileTemplateException :  ", ex);
    Map<String, Object> map = new HashMap<String, Object>();

    if (ex.getCause() != null) {
      map.put("error_description", ex.getCause().getMessage());
    } else {
      map.put("error_description", ex.getMessage());
    }

    if (null != ex.getError() && !ex.getError().isEmpty()) {
      map.put("error", ex.getError());
    } else {
      map.put("error", "Invalid file format");
    }

    map.put("error_code", 422);

    if (null != ex.getErrorValues() && !ex.getErrorValues().isEmpty()) {
      map.put("error_values", ex.getErrorValues());
    }

    return map;
  }

}
