/**
 * 기능명: Mountain
 * 파일명: MountainController.java, MountainCourseController.java
 * 설명: 산 정보 조회 및 검색 관련 API를 제공하는 컨트롤러 클래스
 *   		- 전체 산 목록 조회, 특정 ID로 산 조회, 산 이름으로 ID 조회, 키워드 기반 산 검색
 *		특정 산의 등산 코스를 조회하는 API 컨트롤러 클래스
 *			- 산 ID로 해당 산에 속한 등산 코스 목록 조회
 * 작성자: 김승룡
 * 작성일: 2025-04-07 ~ 04-09
 */

package himedia.hpm_spring.controller;

import himedia.hpm_spring.repository.vo.MountainVo;
import himedia.hpm_spring.service.MountainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/mountains")
public class MountainController {

    @Autowired
    private MountainService mountainService;

    @GetMapping
    public List<MountainVo> getAllMountains() {
        return mountainService.retrieveAllMountains();
    }

    @GetMapping("/{id}")
    public MountainVo getMountainById(@PathVariable Long id) {
        return mountainService.retrieveMountainById(id);
    }

    @GetMapping("/name/{name}")
    public String getMountainByName(@PathVariable String name) {
    	String mountainId = mountainService.retrieveMountainByName(name);
        if (mountainId != null) {
            return mountainId;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 이름의 산을 찾을 수 없습니다: " + name);
        }
    }    
    
    @GetMapping("/search")
    public List<MountainVo> searchMountains(@RequestParam String keyword) {
        return mountainService.searchMountains(keyword);
    }
}
