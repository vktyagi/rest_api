package com.demo.console.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.demo.console.dao.AdditionalParameterDao;
import com.demo.console.dao.MriParameterDao;
import com.demo.console.models.data.MriParameterJson;
import com.demo.console.models.data.ValidEnum;
import com.demo.console.models.entities.MriParameterMaster;
import com.demo.console.services.MasterDataService;
import com.demo.console.utils.ValidationUtils;
import com.demo.console.wrapper.exception.BadRequestParameterException;

@Service("masterDataService")
public class MasterDataServiceImpl implements MasterDataService {
  private static final Logger LOGGER = Logger.getLogger(MasterDataServiceImpl.class);

  @Resource
  private AdditionalParameterDao addtionalParameterDao;


  @Resource
  private MriParameterDao mriParameterDao;

  



  


  public List<String> getMriList(String mriSetwithCodes) {
    List<String> codeList = new ArrayList<String>();
    List<String> mriList = new ArrayList<String>();
    int tempNum = 100000;
    String word = "";
    String number = "'";
    String validOpreator = "";
    char[] ch = mriSetwithCodes.trim().toCharArray();
    for (int i = 0; i < ch.length; i++) {
      int no = (int) ch[i];
      if (no == 45 || no == 43 || no >= 48 && no <= 57) {
        word = word + ch[i];
        if (number.length() > 0) {
          mriList.add(number + "'");
          number = "'";
        }
        if (validOpreator.length() > 0) {
          if (ValidEnum.contains(validOpreator)) {
            validOpreator = "";
          } else {
            throw new BadRequestParameterException("Invalid logical operator in MRI Code : '"
                + validOpreator + "'  at index = " + (i - validOpreator.length()) + "");
          }
        }
      } else if (no == 32 || no == 40 || no == 41 || no == 43 || no >= 65 && no <= 90 || no >= 97
          && no <= 122) {
        if (no == 32 && "-".equalsIgnoreCase(word)) {
          throw new BadRequestParameterException("Invalid spaces in MRI Code at index = " + (i + 1));
        }
        if (word.trim().length() > 0) {
          if (no != 40) {
            mriList.add(word);
            codeList.add(word);
          } else if (no == 40) {
            mriList.add(word + "*");
            String tempString = word.trim().replace("+", "");
            tempNum = Integer.parseInt(tempString);
            codeList.clear();
          }

          word = "";
        }

        number = number + ch[i];
        if (no != 32 && no != 40 && no != 41) {
          validOpreator = validOpreator + ch[i];
        }

        if (no == 41) {

          if (100000 != tempNum && 1 > tempNum) {
            throw new BadRequestParameterException("Group count must be equals or greater than 1");
          }
          if (100000 != tempNum && tempNum > codeList.size()) {
            throw new BadRequestParameterException("Group must have more codes");
          }
        }
      }
    }
    if (word.length() > 0) {
      mriList.add(word);
    }
    if (number.length() > 0) {
      mriList.add(number + "'");
    }
    if (validOpreator.length() > 1 && !ValidEnum.contains(validOpreator)) {
      throw new BadRequestParameterException("Invalid logical operator in MRI Code : "
          + validOpreator + " at index = " + (ch.length + 1 - validOpreator.length()));
    }

    return mriList;

  }

    @Override
    public List<MriParameterJson> getAllMriParameterList(long offset) {
	List<MriParameterJson> mriParameterJsonList = new ArrayList<MriParameterJson>();
	List<MriParameterMaster> ampMriParameterList = mriParameterDao.getMriLabelList(offset);
	for (MriParameterMaster mriParameterMaster : ampMriParameterList) {
	    MriParameterJson mriParameterJson = new MriParameterJson();
	    mriParameterJson.setMriDictionaryId(mriParameterMaster.getMriDictionaryId());
	    StringBuilder mriLabel = new StringBuilder(mriParameterMaster.getMriLabel());
	    String startDate = ValidationUtils.convertDateToString(ValidationUtils.DATE_FORMAT_MM_DD_YYYY, mriParameterMaster.getDictionaryMonthStart());
	    String endDate = ValidationUtils.convertDateToString(ValidationUtils.DATE_FORMAT_MM_DD_YYYY, mriParameterMaster.getDictionaryMonthEnd());
	    if (!startDate.isEmpty() && !endDate.isEmpty()) {
		mriLabel.append(" START DATE: ");
		mriLabel.append(startDate);
		mriLabel.append(" END DATE: ");
		mriLabel.append(endDate);
	    }
	    mriParameterJson.setMriLabel(mriLabel.toString());
	    mriParameterJsonList.add(mriParameterJson);
	}
	return mriParameterJsonList;
    }
}
