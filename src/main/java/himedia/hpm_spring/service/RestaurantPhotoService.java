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

import himedia.hpm_spring.mappers.RestaurantPhotoMapper;
import himedia.hpm_spring.repository.vo.RestaurantPhotoVo;

@Service
public class RestaurantPhotoService {

    @Autowired
    private RestaurantPhotoMapper restaurantPhotoMapper;
    
	@Value("${file.upload-dir}")
	private String uploadDir;

    @Transactional    
    public List<String> insertPhoto(Integer restaurantsId, MultipartFile[] photos) {
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
                RestaurantPhotoVo restaurantPhotoVo = new RestaurantPhotoVo();
                restaurantPhotoVo.setRestaurantsId(restaurantsId);
                restaurantPhotoVo.setFileName(fileName);
                restaurantPhotoVo.setFilePath("/uploads/" + fileName); // 프론트에서 접근할 URL 기준 경로
                restaurantPhotoVo.setUpdateDate(new Date());

                restaurantPhotoMapper.insertPhoto(restaurantPhotoVo);
                filePaths.add(newFilePath.toString());
            }

            return filePaths;

        } catch (Exception e) {
            // 에러 로그
            System.err.println("맛집후기 사진 업로드 실패: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    //	맛집후기 사진 조회
    public List<RestaurantPhotoVo> selectAllPhotoByRestaurantId(int restaurantsId) {
        return restaurantPhotoMapper.selectAllPhotoByRestaurantId(restaurantsId);
    }

    //	맛집후기 사진 삭제
    @Transactional
    public int deletePhotoByRestaurantId(int restaurantsId) {
        return restaurantPhotoMapper.deletePhotoByRestaurantId(restaurantsId);
    }

}