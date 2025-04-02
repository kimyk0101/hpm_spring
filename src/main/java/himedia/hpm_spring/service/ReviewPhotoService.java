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

import himedia.hpm_spring.mappers.ReviewPhotoMapper;
import himedia.hpm_spring.repository.vo.ReviewPhotoVo;

@Service
public class ReviewPhotoService {

    @Autowired
    private ReviewPhotoMapper reviewPhotoMapper;
    
	@Value("${file.upload-dir}")
	private String uploadDir;

    @Transactional
    public List<String> insertPhoto(Integer reviewsId, MultipartFile[] photos) {
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
                ReviewPhotoVo reviewPhotoVo = new ReviewPhotoVo();
                reviewPhotoVo.setReviewsId(reviewsId);
                reviewPhotoVo.setFileName(fileName);
                reviewPhotoVo.setFilePath("/uploads/" + fileName); // 프론트에서 접근할 URL 기준 경로
                reviewPhotoVo.setUpdateDate(new Date());

                reviewPhotoMapper.insertPhoto(reviewPhotoVo);
                filePaths.add(newFilePath.toString());
            }

            return filePaths;

        } catch (Exception e) {
            // 에러 로그 좀 더 구체적으로
            System.err.println("리뷰 사진 업로드 실패: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    //	리뷰 사진 조회
    public List<ReviewPhotoVo> selectAllPhotoByReviewId(int reviewsId) {
        return reviewPhotoMapper.selectAllPhotoByReviewId(reviewsId);
    }

    //	리뷰 사진 삭제
    @Transactional
    public int deletePhotoByReviewId(int reviewsId) {
        return reviewPhotoMapper.deletePhotoByReviewId(reviewsId);
    }

}