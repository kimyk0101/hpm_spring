package himedia.hpm_spring.controller;

import himedia.hpm_spring.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/s3")
public class S3Controller {

    private final S3Service s3Service;

    
     // 프론트에서 호출할 Presigned URL 발급 API
     // 예: GET /api/s3/presigned-url?fileName=test.jpg
     
    @GetMapping("/presigned-url")
    public String getPresignedUrl(@RequestParam String fileName) {
        return s3Service.generatePresignedUrl(fileName).toString();
    }
}
