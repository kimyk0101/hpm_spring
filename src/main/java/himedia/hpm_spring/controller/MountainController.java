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
