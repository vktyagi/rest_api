package com.demo.console.wrapper.s3;

import static com.demo.console.utils.ApplicationConstants.BASE_PLAN_URL_TEMPLATE_FILE;
import static com.demo.console.utils.ApplicationConstants.TEMPLATE_PREFIX;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

@Service
public class S3Wrapper {

  private static final Logger LOGGER = Logger.getLogger(S3Wrapper.class);

  @Resource
  private AmazonS3Client s3Client;

  @Resource
  String s3Bucket;

  @Resource
  String s3Url;

  @PostConstruct
  public void init() {
    s3Client.setRegion(Region.getRegion(com.amazonaws.regions.Regions.US_EAST_1));
    LOGGER.debug("s3 client initialized");
  }

  public boolean upload(String s3FileKey, InputStream is) throws IOException {

    // Check and create bucket
    createBucket();

    Calendar calendar = Calendar.getInstance();
    // gets a calendar using the default time zone and locale.
    calendar.add(Calendar.MINUTE, 2);
    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentLength(is.available());

    PutObjectRequest puRequest = new PutObjectRequest(s3Bucket, s3FileKey, is, metadata);
    puRequest.setCannedAcl(CannedAccessControlList.PublicRead);
    PutObjectResult puResult = s3Client.putObject(puRequest);
    LOGGER.debug("Ranker result file " + s3FileKey + " uploaded to s3-bucket: " + s3Bucket + ", "
        + puResult);
    return true;
  }

  public String upload(String s3FileKey, File file, String fileContentType) {

    // Check and create bucket
    createBucket();
    Calendar calendar = Calendar.getInstance();
    // gets a calendar using the default time zone and locale.
    calendar.add(Calendar.MINUTE, 2);
    Date expireTime = calendar.getTime();

    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setHeader("Expires", expireTime);
    metadata.setCacheControl("max-age=2");

    LOGGER.info("Uploading Campaing result file to S3 bucket... ");
    LOGGER.info("S3 Key : " + s3FileKey + " and file content type : " + fileContentType);
    PutObjectRequest puRequest = new PutObjectRequest(s3Bucket, s3FileKey, file);
    puRequest.setMetadata(metadata);
    puRequest.setCannedAcl(CannedAccessControlList.PublicRead);
    PutObjectResult puResult = s3Client.putObject(puRequest);
    LOGGER.debug(puResult);
    return s3Client.getResourceUrl(s3Bucket, s3FileKey);
  }

  public InputStream downloadS3File(String s3FileKey) throws IOException {

    if (isBucketExist()) {
      LOGGER.debug("Downloading file from S3 bucket :" + s3FileKey);
      GetObjectRequest getRequest = new GetObjectRequest(s3Bucket, s3FileKey);
      S3Object s3Object = s3Client.getObject(getRequest);
      InputStream s3OutputStream = s3Object.getObjectContent();

      LOGGER.debug("Base file file " + s3FileKey + " downloaded from s3-bucket: " + s3Bucket + ", "
          + s3Object);
      return s3OutputStream;
    }
    return null;
  }

  public void deleteS3Object() {
    if (isBucketExist()) {
      Date expireTime = new Date();

      ObjectListing objectList = s3Client.listObjects(s3Bucket, "ranker/results");
      List<S3ObjectSummary> objectSummary = objectList.getObjectSummaries();
      for (int i = 0; i < objectSummary.size(); i++) {
        LOGGER.info("Last Modified>>>>>>>>>>>>> " + objectSummary.get(i).getKey());
        long difTime = expireTime.getTime() - objectSummary.get(i).getLastModified().getTime();
        if (difTime > (1000 * 60 * 2)) {
          s3Client.deleteObject(s3Bucket, objectSummary.get(i).getKey());
          LOGGER.info("Deleted Object >>>>>>>>>>>>> " + objectSummary.get(i).getKey());
        }
      }
    }
  }

  private void createBucket() {
    if (!s3Client.doesBucketExist(s3Bucket)) {
      s3Client.createBucket(new CreateBucketRequest(s3Bucket));
      LOGGER.debug("Bucket crated : " + s3Bucket);
    }
  }

  private boolean isBucketExist() {
    if (!s3Client.doesBucketExist(s3Bucket)) {
      LOGGER.debug(s3Bucket + " Bucket does not exist");
      throw new AmazonServiceException(s3Bucket + " Bucket does not exist");
    }
    return true;
  }

  public void deleteS3Object(String s3FileKey) {
    s3Client.deleteObject(s3Bucket, s3FileKey);
    LOGGER.info("Key: " + s3FileKey + " deleted from bucket: " + s3Bucket);
  }
  public String baseplanUpload(String s3FileKey, String fileContentType) throws AmazonServiceException, AmazonClientException {

 	// Check and create bucket
 	String fileLink = StringUtils.EMPTY;

 	String completeFilePath = TEMPLATE_PREFIX + "/" + s3FileKey;

 	if (!isFileExist(completeFilePath)) {

 	    ClassLoader loader = getClass().getClassLoader();
 	    File file = new File(loader.getResource(BASE_PLAN_URL_TEMPLATE_FILE).getFile());

 	    ObjectMetadata metadata = new ObjectMetadata();
 	    metadata.setCacheControl("max-age=86400");
 	    LOGGER.info("Uploading base plan file template file to S3 bucket... ");
 	    LOGGER.info("S3 Key : " + s3FileKey);
 	    PutObjectRequest puRequest = new PutObjectRequest(s3Bucket, completeFilePath, file);
 	    puRequest.setMetadata(metadata);
 	    puRequest.setCannedAcl(CannedAccessControlList.PublicRead);
 	    PutObjectResult puResult = s3Client.putObject(puRequest);
 	    LOGGER.debug(puResult);
 	    fileLink = s3Client.getResourceUrl(s3Bucket, completeFilePath);
 	    LOGGER.info("base plan file segment template file uploaded to S3 bucket successfully.");
 	} else {
 	    LOGGER.debug("File " + s3FileKey + " already exists.");
 	    fileLink = s3Client.getResourceUrl(s3Bucket, TEMPLATE_PREFIX) + '/' + BASE_PLAN_URL_TEMPLATE_FILE;
 	}
 	fileLink = fileLink.replace("https://", "http://");
 	return fileLink;
     }

     private boolean isFileExist(String fileName) {
 	ObjectListing objectList = s3Client.listObjects(s3Bucket, TEMPLATE_PREFIX);
 	List<S3ObjectSummary> objectSummary = objectList.getObjectSummaries();
 	for (int i = 0; i < objectSummary.size(); i++) {
 	    LOGGER.info("Last Modified>>>>>>>>>>>>> " + objectSummary.get(i).getKey());
 	    if (objectSummary.get(i).getKey().equalsIgnoreCase(fileName)) {
 		LOGGER.info("Template file:  " + fileName + " already exists.");
 		return true;

 	    }
 	}
 	return false;
     }
}
