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
	    try {
	        // 0. 업로드 디렉토리 보장
	        Path uploadPath = Paths.get(uploadDir);
	        Files.createDirectories(uploadPath);

	        // 1. 기존 사진 삭제 (있을 경우)
	        UserPhotoVo existing = userPhotoMapper.selectPhotoByUserId(usersId);
	        if (existing != null) {
	            Path oldFilePath = uploadPath.resolve(existing.getFileName());
	            Files.deleteIfExists(oldFilePath);
	        }

	        // 2. 새 파일 이름 및 경로 설정
	        String fileName = UUID.randomUUID() + "_" + photo.getOriginalFilename();
	        Path newFilePath = uploadPath.resolve(fileName);
	        Files.copy(photo.getInputStream(), newFilePath, StandardCopyOption.REPLACE_EXISTING);

	        // 3. PhotoVo 객체 생성 및 파일 정보 설정
	        UserPhotoVo userPhotoVo = new UserPhotoVo();
	        userPhotoVo.setUsersId(usersId);
	        userPhotoVo.setFileName(fileName);
	        userPhotoVo.setFilePath("/uploads/" + fileName); // 프론트에서 접근할 URL 기준 경로
	        userPhotoVo.setUpdateDate(new Date());

	        userPhotoMapper.insertOrUpdatePhoto(userPhotoVo);

	        return newFilePath.toString();

	    } catch (Exception e) {
	        // 에러 로그 좀 더 구체적으로
	        System.err.println("프로필 사진 업로드 실패: " + e.getMessage());
	        e.printStackTrace();
	        return null;
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