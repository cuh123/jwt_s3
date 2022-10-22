package com.example.boot.util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class S3Utils {
	
	private AmazonS3 amazonS3;
	
	@Value("${cloud.aws.credentials.access-key}")
	private String accesskey;
	
	@Value("${cloud.aws.credentials.secret-key}")
	private String secretKey;
	
	@Value("${cloud.aws.region.static}")
	private String region;
	
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
	
    // AWS S3 자격 증명 지정
	@PostConstruct
	public void setAmazonS3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accesskey, secretKey);

        amazonS3 = AmazonS3ClientBuilder.standard()
        								.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
        								.withRegion(region)
        								.build();
    }
	
	// 폴더 생성
	public void createFolder(String bucketName, String folderName) {
		amazonS3.putObject(bucketName, folderName + "/", new ByteArrayInputStream(new byte[0]), new ObjectMetadata());
    }
	
	// 다중 파일 업로드
	public void fileUpload(List<MultipartFile> files, List<String> fileList) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String today = sdf.format(date);

		if(!files.isEmpty()) {
			createFolder(bucket + "/contact", today);
		}

		ObjectMetadata objectMetadata = new ObjectMetadata();
		for(int i=0; i<files.size(); i++) {
			objectMetadata.setContentType(files.get(i).getContentType());
			objectMetadata.setContentLength(files.get(i).getSize());
			objectMetadata.setHeader("filename", files.get(i).getOriginalFilename());
			amazonS3.putObject(new PutObjectRequest(bucket + "/contact/" + today, fileList.get(i), files.get(i).getInputStream(), objectMetadata));
		}
	}
	
	// 다중 파일 삭제
	public void fileDelete(String filePath, String fileName) {
		amazonS3.deleteObject(new DeleteObjectRequest(filePath, fileName));
	}
}
