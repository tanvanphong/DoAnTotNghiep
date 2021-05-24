package com.iuh.ABCStore.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Service
public class AmazonClient {

	private AmazonS3 s3client;

	@Value("${s3.endpointUrl}")
	private String endpointUrl;

	@Value("${s3.bucketName}")
	private String bucketName;

	@Value("${s3.accessKeyId}")
	private String accessKeyId;

	@Value("${s3.secretKey}")
	private String secretKey;

	@Value("${s3.region}")
	private String region;

	@PostConstruct
	private void initializeAmazon() {
		AWSCredentials credentials = new BasicAWSCredentials(this.accessKeyId, this.secretKey);
		this.s3client = AmazonS3ClientBuilder.standard().withRegion(region)
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
	}

//	public String uploadFile(MultipartFile multipartFile) throws Exception {
//
//		String fileUrl = "";
//		File file = convertMultiPartToFile(multipartFile);
//		
//		String fileName = generateFileName(multipartFile);
//		
//		
//		
//		fileUrl = "http://" + bucketName + ".s3.us-east-2.amazonaws.com/" + fileName;
//		
////		fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
//		uploadFileTos3bucket(fileName, file);
//		file.delete();
//		return fileUrl;
//	}
	
	
	
	public String deleteFileFromS3Bucket(String fileUrl) {
		String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
		s3client.deleteObject(bucketName, fileName);
		return "Successfully deleted";
	}

	public Boolean uploadFileTos3bucket(String fileName, File file) {
		s3client.putObject(bucketName, fileName, file);
		return true;
	}

	public File convertMultiPartToFile(MultipartFile file) throws IOException {
		
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		
		return convFile;
		
	}

	public String generateFileName(MultipartFile multiPart) {
		return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	}
}