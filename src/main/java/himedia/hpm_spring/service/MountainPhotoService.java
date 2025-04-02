package himedia.hpm_spring.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import himedia.hpm_spring.mappers.MountainPhotoMapper;
import himedia.hpm_spring.repository.vo.CommunityPhotoVo;
import himedia.hpm_spring.repository.vo.MountainPhotoVo;

@Service
public class MountainPhotoService {

    @Autowired
    private MountainPhotoMapper mountainPhotoMapper;
    
	@Value("${file.upload-dir}")
	private String uploadDir;

    @Transactional    
    public List<String> insertPhoto(Integer mountainsId, MultipartFile[] photos) {
        List<String> filePaths = new ArrayList<>();

        try {
            // 0. 업로드 디렉토리 보장
            Path uploadPath = Paths.get(uploadDir);
            Files.createDirectories(uploadPath);

            for (MultipartFile photo : photos) {
                // 2. 새 파일 이름 및 경로 설정
                String fileName = UUID.randomUUID() + "_" + photo.getOriginalFilename();
                Path newFilePath = uploadPath.resolve(fileName);
                Files.copy(photo.getInputStream(), newFilePath, StandardCopyOption.REPLACE_EXISTING);

                // 3. DB 업데이트
                MountainPhotoVo mountainPhotoVo = new MountainPhotoVo();
                mountainPhotoVo.setMountainsId(mountainsId);
                mountainPhotoVo.setFileName(fileName);
                mountainPhotoVo.setFilePath("/uploads/" + fileName); // 프론트에서 접근할 URL 기준 경로
                mountainPhotoVo.setUpdateDate(new Date());

                mountainPhotoMapper.insertPhoto(mountainPhotoVo);
                filePaths.add(newFilePath.toString());
            }

            return filePaths;

        } catch (Exception e) {
            // 에러 로그
            System.err.println("등산후기 사진 업로드 실패: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    //	등산후기 사진 조회
    public List<MountainPhotoVo> selectAllPhotoByMountainId(int mounatinsId) {
        return mountainPhotoMapper.selectAllPhotoByMountainId(mounatinsId);
    }

    //	등산후기 사진 삭제
    @Transactional
    public int deletePhotoByMountainId(int mounatinsId) {
        return mountainPhotoMapper.deletePhotoByMountainId(mounatinsId);
    }

}