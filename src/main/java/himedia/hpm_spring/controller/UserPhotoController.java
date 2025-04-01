package himedia.hpm_spring.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import himedia.hpm_spring.repository.vo.UserPhotoVo;
import himedia.hpm_spring.service.UserPhotoService;

@RestController
@RequestMapping("/api/userPhoto")
public class UserPhotoController {

    @Autowired
    private UserPhotoService userPhotoService;

    //	프로필 사진 업로드
    @PostMapping("/upload")
    public ResponseEntity<String> uploadPhoto(@RequestParam("usersId") Integer userId, 
                                              @RequestParam("photo") MultipartFile photo) throws IOException {
    	
		String filePath = userPhotoService.insertPhoto(userId, photo);
		return ResponseEntity.ok("프로필 사진이 성공적으로 업로드되었습니다. 파일 경로: " + filePath);
    }
    
    //	프로필 사진 조회
    @GetMapping("/view/{usersId}")
    public ResponseEntity<UserPhotoVo> viewPhoto(@PathVariable("usersId") int usersId) {
        UserPhotoVo photo = userPhotoService.selectPhotoByUserId(usersId);

        if (photo != null) {
            return ResponseEntity.ok(photo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    
    //	usersId, photoId로 사진 삭제
    @DeleteMapping("/delete/{usersId}")
    public ResponseEntity<?> deletePhoto(@PathVariable("usersId") int usersId) {
        try {
            int result = userPhotoService.deletePhotoByUserId(usersId);
            if (result == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사진을 찾을 수 없습니다.");
            }
            return ResponseEntity.ok("사진이 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("사진 삭제 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
    
}