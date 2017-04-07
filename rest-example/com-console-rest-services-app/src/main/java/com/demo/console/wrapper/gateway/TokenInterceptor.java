package com.demo.console.wrapper.gateway;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.demo.console.utils.Claim;

@Component
public class TokenInterceptor implements HandlerInterceptor {

  private static final Logger LOGGER = Logger.getLogger(TokenInterceptor.class);

  @Value("${access.control.max.age:2147483647}")
  // 2147483647 is Integer.MAX_VALUE
  String maxAge;

  @Override
  public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
      Exception arg3) throws Exception {
    // Nothing to do after completion.
  }

  @Override
  public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
      ModelAndView arg3) throws Exception {
    // Nothing to do post handle.
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2)
      throws Exception {

    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
      response.setStatus(200);
      response.setHeader("Access-Control-Max-Age", maxAge);
      return false;
    }

    StringBuilder builderString = new StringBuilder("{");
    JsonNode jsonNode = null;
    String subscriber = null;
    String email = null;
    String role = null;
    String iss = null;
    String endUser = null;
    long exp = 0;

    try {
      String jwtToken = request.getHeader("X-JWT-Assertion");
      if (null != jwtToken) {

        LOGGER.debug("JWT Token is: " + jwtToken);
        String claimString = jwtToken.substring(jwtToken.indexOf("=.") + 2,
            jwtToken.lastIndexOf("."));
        LOGGER.debug("ClaimString is: " + claimString);
        String claimJSON = new String(Base64.decodeBase64(claimString.getBytes()));
        LOGGER.debug("claimJSON is: " + claimJSON);

        jsonNode = new ObjectMapper().readTree(claimJSON);

        subscriber = jsonNode.get("http://wso2.org/claims/subscriber") != null ? jsonNode.get(
            "http://wso2.org/claims/subscriber").getTextValue() : "";
        email = jsonNode.get("http://wso2.org/claims/emailaddress") != null ? jsonNode.get(
            "http://wso2.org/claims/emailaddress").getTextValue() : "";
        role = jsonNode.get("http://wso2.org/claims/role") != null ? jsonNode.get(
            "http://wso2.org/claims/role").getTextValue() : "";
        endUser = jsonNode.get("http://wso2.org/claims/enduser") != null ? jsonNode.get(
            "http://wso2.org/claims/enduser").getTextValue() : "";
      } else {

        LOGGER.error("Invalid request: No jwt token in the request");
        PrintWriter out = response.getWriter();
        builderString.append("\"error\"" + ":\"Token missing in the request!\"" + ",");
        builderString.append("\"error_description\""
            + ":\"Token missing in the request. Please provide Bearer token in the request.!\"");
        builderString.append("}");
        out.print(builderString.toString());
        LOGGER.error("Invalid request: No jwt token in the request");
        return false;
      }

      Claim claim = new Claim(iss, exp, subscriber, email, role, endUser);
      LOGGER.info("Claim is: " + claim);
      if ("".equals(claim.getEmail())) {
        LOGGER.error("Email info missing in the claim. Skipping");
        PrintWriter out = response.getWriter();
        builderString.append("\"error\"" + ":\"User not authorized!\"" + ",");
        builderString.append("\"error_description\"" + ":\"Email info missing!\"");
        builderString.append("}");
        out.print(builderString.toString());

        return false;

      } else {
        request.setAttribute("userName", claim.getUsername());
        request.setAttribute("userEmail", claim.getEmail());
      }

    } catch (Exception e) {
      LOGGER.error("Exception during the processing of JWT token: " + e);

      PrintWriter out = response.getWriter();
      builderString.append("\"error\"" + ":\"Internal server error!\"" + ",");
      builderString.append("\"error_description\""
          + ":\"Exception during the processing of JWT token in the request.!\"" + ",");
      builderString.append("\"error_code\"" + ":500");
      builderString.append("}");
      out.print(builderString.toString());
      return false;
    }

    return true;
  }

}
