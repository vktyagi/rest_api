package com.demo.console.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.demo.console.wrapper.exception.BadRequestParameterException;

public class ValidationUtils {

  private static final Logger LOGGER = Logger.getLogger(ValidationUtils.class);
  public static final String DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";
  public static final String DATE_FORMAT_YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
  public static final String DATE_FORMAT_MMDDYYYY = "MMddYYYY";
  public static final String DATE_FORMAT_ISO = "yyyy-MM-dd'T'HH:mm:ss.sssZ";
  public static final String DATE_FORMAT_MM_DD_YYYY = "MM/dd/yyyy";
  public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("##.00");

  private static String SEGMENT_REGEX = "Sgmnt\\b*\\d\\d";
  private static String ALPHANUMERIC_REGEX = "^[a-zA-Z0-9 ]*$";
  private static String DECIMAL_PRECISION_REGEX = "[0-9]+([.0-9]{1,4})";
  private static String REGEX = "[0-9]";
  private static String RUN_DATE_REGEX = "((20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
  private static String RUN_DATE_REGEX_MMDDYY = "(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((20)\\d\\d)";

  private static String MRI_CODE_REGEX = "[-+]*[0-9]*";

  private ValidationUtils() {
  }

  /**
   * To validate string null or empty
   * 
   * @param str
   * @return
   */
  public static boolean isNumeric(String value) {

    if (null != value && !value.isEmpty()
        && (value.matches(REGEX) || value.matches(DECIMAL_PRECISION_REGEX))) {
      return true;
    }
    return false;
  }

  public static boolean isNullEmpty(String str) {
    if (null == str || str.isEmpty()) {
      return true;
    }
    return false;
  }

  /**
   * 
   * To check string string having at least 2 alphanumeric character
   * 
   * @param str
   * @return
   */
  public static boolean isAlphanumeric(String str) {
    Pattern pattern = Pattern.compile(String.format(ALPHANUMERIC_REGEX));
    Matcher matcher = pattern.matcher(str);
    return matcher.matches();
  }

  public static boolean isPositiveNumber(Object value) {

    if (value instanceof Integer) {
      Integer intValue = (Integer) value;
      if (intValue >= 0) {
        return true;
      }
    } else if (value instanceof Double) {
      Double doubleValue = (Double) value;
      if (doubleValue >= 0) {
        return true;
      }
    } else if (value instanceof BigDecimal) {
      BigDecimal bigDecimalValue = (BigDecimal) value;
      if (bigDecimalValue.doubleValue() >= 0) {
        return true;
      }
    }

    return false;
  }

  /**
   * To validate positive number of up to given decimal places
   * 
   * @param value
   * @param decimalPlace
   * @return
   */
  public static boolean isPositiveDecimal(Double value, int decimalPlace) {
    if (value < 0) {
      return false;
    }
    try {
      String changedStr = String.valueOf(value).toString();
      if (changedStr.contains(".")) {
        String[] splitVal = changedStr.split("\\.");

        if (splitVal.length == 2 && null != splitVal[1] && splitVal[1].length() <= decimalPlace) {
          return true;
        }
      } else {
        return true;
      }
    } catch (Exception e) {
      LOGGER.error(e);
      return false;
    }
    return false;
  }

  /**
   * To validate valid date format.
   * 
   * @param format
   *          E.g: MM/DD/YYYY
   * @param value
   *          E.g: 06/25/2015
   * @return boolean
   */
  public static boolean isValidDateFormat(String format, String value) {
    Date date = null;
    SimpleDateFormat sdf = null;

    try {
      sdf = new SimpleDateFormat(format);
      date = sdf.parse(value);

      if (!value.equals(sdf.format(date))) {
        date = null;
      }
    } catch (ParseException ex) {
      return false;
    }

    return date != null;
  }

  /**
   * To validate segment value. It will check segment format SegmntNN, where N is 0-9 digits
   * 
   * @param sgmntStr
   * @return boolean
   */
  public static boolean isValidSgmntFromat(String sgmntStr) {
    Pattern pattern = null;
    Matcher matcher = null;

    try {
      pattern = Pattern.compile(SEGMENT_REGEX);

      matcher = pattern.matcher(sgmntStr);
      return matcher.matches();
    } catch (Exception e) {
      LOGGER.error(e);
      return false;
    }
  }

  /**
   * Convert string object to Date object with given format
   * 
   * @param format
   * @return
   * @throws ParseException
   */
  public static Date convertStringToDate(String format, String dateStr) {

    DateFormat dateFormat = new SimpleDateFormat(format);
    try {
      return dateFormat.parse(dateStr);
    } catch (ParseException e) {
      throw new BadRequestParameterException(e.getMessage());
    }
  }

  public static String convertDateToString(String format, Date date) {
      try {
         DateFormat dateFormat = new SimpleDateFormat(format);
         return dateFormat.format(date);
      } catch (Exception e) {
         return "";
      }
  }

  public static String convertDateToISOformat(String format, Date date) {
    DateFormat dateFormat = new SimpleDateFormat(format);
    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    return dateFormat.format(date);
  }

  /**
   * Method to validate Run date format
   * 
   * @param dateString
   * @return
   */

  public static boolean isDateValid(final String dateString) {
    Pattern datePattern = Pattern.compile(RUN_DATE_REGEX);
    Matcher dateMatcher = datePattern.matcher(dateString);
    if (dateMatcher.matches()) {
      dateMatcher.reset();
      if (dateMatcher.find()) {

        String[] dateArr = dateString.split("-");
        int year = Integer.parseInt(dateArr[0]);
        int month = Integer.parseInt(dateArr[1]);
        int day = Integer.parseInt(dateArr[2]);

        if ((day == 31) && (month == 4 || month == 6 || month == 9 || month == 11)) {
          return false; // 1,3,5,7,8,10,12 has 31 days
        } else if (month == 2) {

          if ((day == 30) || (day == 31)) {
            return false;
          }

          if ((year % 4) != 0 && day == 29) {
            return false;
          }
          return true;
        } else {
          return true;
        }
      }
    }

    return false;

  }

  public static boolean isValidDateMMDDYY(final String dateString) {
    Pattern datePattern = Pattern.compile(RUN_DATE_REGEX_MMDDYY);
    Matcher dateMatcher = datePattern.matcher(dateString);

    if (dateMatcher.matches()) {
      dateMatcher.reset();
      if (dateMatcher.find()) {

        String[] dateArr = dateString.split("/");

        int month = Integer.parseInt(dateArr[0]);
        int day = Integer.parseInt(dateArr[1]);
        int year = Integer.parseInt(dateArr[2]);
        if (dateArr[2].length() != 4) {
          return false;
        }
        if (day < 1 || day > 31) {
          return false;
        }
        if (month < 1 || month > 12) {
          return false;
        }

        if ((day == 31) && (month == 4 || month == 6 || month == 9 || month == 11)) {
          return false; // 1,3,5,7,8,10,12 has 31 days
        } else if (month == 2) {

          if ((day == 30) || (day == 31)) {
            return false;
          }

          if ((year % 4) != 0 && day == 29) {
            return false;
          }
          return true;
        } else {
          return true;
        }
      }
    }

    return false;

  }

  /**
   * This method will remove the white spaces
   * 
   * @param strValue
   * @param fieldName
   * @return
   */
  public static String trimWhiteSpace(String strValue) {
    return strValue.trim().replaceAll("\\s+", " ");
  }

  public static boolean isProperlyParenthesized(String expr) {
    Deque<Character> stack = new ArrayDeque<Character>();
    for (int i = 0; i < expr.length(); ++i) {
      char c = expr.charAt(i);
      if (c == '(')
        stack.push(c);
      else if (c == ')') {
        if (stack.isEmpty())
          return false;
        stack.pop();
      }
    }
    return stack.isEmpty();
  }

  public static boolean isValidMRICode(String value) {
    if (null != value && !value.isEmpty()
        && (value.matches(REGEX) || value.matches(MRI_CODE_REGEX))) {
      return true;
    }

    return false;
  }

  public static Double formatDecimalValue(double decimalVal) {
    return new Double(DECIMAL_FORMAT.format(decimalVal));
  }

  public static BigDecimal convertStringToBigDecimal(String str) {
    try {
      double value = Double.parseDouble(str);
      return new BigDecimal(DECIMAL_FORMAT.format(value));
    } catch (NumberFormatException e) {
      return new BigDecimal(0);
    }
  }
  
  public static BigDecimal getBigDecimalAbsScaled(double doubleVal){
      return new BigDecimal(String.valueOf(doubleVal)); 
  }
  
  public static String replaceAll(String string, String regEx, String replacement){
      StringBuffer strBuf = new StringBuffer(string);
      for(int i=0; i<regEx.length();i++){
	  int index = strBuf.indexOf(Character.toString(regEx.charAt(i)));
	  if(index != -1){
	      strBuf.deleteCharAt(index);
	      strBuf.insert(index, replacement);
	  }
	  if(strBuf.toString().contains(Character.toString(regEx.charAt(i)))){
	      return replaceAll(strBuf.toString(),regEx,replacement);
	  }
      }
      return strBuf.toString().trim();
  }
}
