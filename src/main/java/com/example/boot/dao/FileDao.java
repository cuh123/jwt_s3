package com.example.boot.dao;

import com.example.boot.model.FileModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("fileDao")
public class FileDao extends BaseDao {
	
    public int fileUpload(FileModel fileModel) throws Exception {
    	return insert("FileMapper.fileUpload", fileModel);
    }
    
    public int fileRemove(Map<String, Object> param) throws Exception {
    	return delete("FileMapper.fileRemove", param);
    }

    public FileModel getFileDetail(Map<String, Object> param) throws Exception {
        return (FileModel) queryForObject("FileMapper.getFileDetail", param);
    }

}
