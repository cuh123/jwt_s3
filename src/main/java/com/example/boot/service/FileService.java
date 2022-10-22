package com.example.boot.service;

import com.example.boot.config.jwt.JwtTokenUtil;
import com.example.boot.dao.FileDao;
import com.example.boot.model.FileModel;
import com.example.boot.util.HashUtils;
import com.example.boot.util.S3Utils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("fileService")
public class FileService {

    @Autowired
    private FileDao fileDao;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
	private S3Utils s3Utils;
    
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    
    @Value("${cloud.aws.s3.domain}")
    private String domain;

	@Value("${aes.key}")
	private String AES_KEY;

    /**
     * 파일 업로드
     * @param auth
     * @param contactType
     * @param contactTitle
     * @param contactTxt
     * @return result
     */
    public int fileUpload(String auth, List<MultipartFile> files, String contactType, String contactTitle, String contactTxt) throws Exception {
    	// 현재 날짜로 폴더 생성
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String today = sdf.format(date);
    	String userId = jwtTokenUtil.getUserIdFromToken(auth); // 토큰을 이용해 사용자 ID 찾기
		List<String> fileList = Lists.newArrayList();
		FileModel fileModel = new FileModel();
    	
    	// AWS S3에 업로드 될 파일의 URL을 DB에 넣기 위해 URL 경로 생성
    	List<String> urlList = Lists.newArrayList();

    	if(files != null && files.size() > 0){
			for (MultipartFile file : files) {
				String fileRandomName = HashUtils.generateRandomKey(16);
				String fileOriginName = file.getOriginalFilename();
				String fileExtension = fileOriginName.substring(fileOriginName.lastIndexOf("."));
				String fileFullName = fileRandomName + "_" + userId + fileExtension;
				String fileFullPath = "/contact/" + today + "/" + fileFullName;
				urlList.add(fileFullPath);
			}

			// AWS S3 파일 업로드를 위해 DB에 들어갈 URL 경로에서 파일명만 추출
			if(urlList.size() > 0){
				for(int i = 0; i<urlList.size(); i++){
					fileList.add(urlList.get(i).substring(urlList.get(i).lastIndexOf("/")+1));
					if(i == 0){
						fileModel.setContact_pic_url_1(urlList.get(i));
					} else if(i == 1){
						fileModel.setContact_pic_url_2(urlList.get(i));
					}
				}
			}
		}

		fileModel.setContact_type(contactType);
		fileModel.setContact_title(contactTitle);
		fileModel.setContact_txt(contactTxt);
		fileModel.setContact_answer_status(0);
		fileModel.setUser_id(userId);
		
		int result = fileDao.fileUpload(fileModel);
		// AWS S3에 파일 업로드 하기 위해 파일 목록과 사용자 ID를 넘겨주면서 S3Utils의 fileUpload 호출
		if(fileList.size() > 0){
			s3Utils.fileUpload(files, fileList);
		}
    	return result;
    }
    
    /**
     * 파일 삭제
     * @param auth
     * @param contactId
     * @return result
     */
    public int fileRemove(String auth, int contactId) throws Exception {
    	Map<String, Object> param = Maps.newHashMap();
    	String userId = jwtTokenUtil.getUserIdFromToken(auth); // 토큰을 이용해 사용자 ID 찾기
    	param.put("user_id", userId);
    	param.put("contact_id", contactId);
    	
    	// 사용자 ID와 문의ID를 통해 AWS S3에 파일 업로드 된 폴더 날짜와 파일명을 가져와 세팅
    	FileModel fileModel = new FileModel();
		fileModel = fileDao.getFileDetail(param);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Date createDt = sdf.parse(fileModel.getCreate_dt());
		String folderDt = sdf.format(createDt).replaceAll("-", "");
		List<String> picUrl = Lists.newArrayList();
		picUrl.add(fileModel.getContact_pic_url_1());
		picUrl.add(fileModel.getContact_pic_url_2());
		
		// 문의 취소 시 문의 내역 삭제 처리
		int result = fileDao.fileRemove(param);
		for(int i=0; i<picUrl.size(); i++) {
			if(picUrl.get(i) != null) {
				// 등록 날짜를 찾아 AWS S3에 업로드된 파일을 삭제 처리
				String picName = picUrl.get(i).substring(picUrl.get(i).lastIndexOf("/")+1);
				s3Utils.fileDelete(bucket + "/contact/" + folderDt, picName);
			}
		}
    	return result;
    }

}
