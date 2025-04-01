package himedia.hpm_spring.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import himedia.hpm_spring.mappers.UserPhotoMapper;
import himedia.hpm_spring.repository.vo.UserPhotoVo;

@Service
public class UserPhotoService {

    @Autowired
    private UserPhotoMapper userPhotoMapper;
    
	@Value("${file.upload-dir}")
	private String uploadDir;

    @Transactional
    public String insertPhoto(Integer usersId, MultipartFile photo) {
    	
    	String fileName = UUID.randomUUID().toString() + "_" + photo.getOriginalFilename();
    	Path filePath = Paths.get(uploadDir, fileName);
    	
    	try {
    		Files.copy(photo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    		
    		Date updateDate = new Date();
    		
            //	UserPhotoVo 객체 생성 및 파일 정보 설정
            UserPhotoVo userPhotoVo = new UserPhotoVo();
            userPhotoVo.setFileName(fileName);
            userPhotoVo.setFilePath(filePath.toString());
            userPhotoVo.setUpdateDate(updateDate);
            userPhotoVo.setUsersId(usersId);
            
            userPhotoMapper.insertOrUpdatePhoto(userPhotoVo);
    		
    		return filePath.toString();	//	성공
    		
    		
		} catch (Exception e) {
			e.printStackTrace();
	        return filePath.toString();
		}
    }

    //	프로필 사진 조회
    public UserPhotoVo selectPhotoByUserId(int usersId) {
        return userPhotoMapper.selectPhotoByUserId(usersId);
    }

    //	프로필 사진 삭제
    @Transactional
    public int deletePhotoByUserId(int usersId) {
        return userPhotoMapper.deletePhotoByUserId(usersId);
    }

}