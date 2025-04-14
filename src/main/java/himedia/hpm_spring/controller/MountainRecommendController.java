package himedia.hpm_spring.controller;

import himedia.hpm_spring.repository.vo.MountainRecommendVo;
import himedia.hpm_spring.service.MountainRecommendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/mountain-recommends")
public class MountainRecommendController {

    @Autowired
    private MountainRecommendService mountainRecommendService;

    @GetMapping
    public ResponseEntity<List<MountainRecommendVo>> retrieveAllMountainRecommends() {
        List<MountainRecommendVo> mountainRecommends = mountainRecommendService.findAllMountainRecommends();
        return ResponseEntity.ok(mountainRecommends);
    }
}