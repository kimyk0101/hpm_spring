package himedia.hpm_spring.controller;

import himedia.hpm_spring.repository.vo.MountainsVo;
import himedia.hpm_spring.service.MountainsService;

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
public class MountainsController {

    @Autowired
    private MountainsService mountainsService;

    @GetMapping
    public List<MountainsVo> getAllMountains() {
        return mountainsService.retrieveAllMountains();
    }

    @GetMapping("/{id}")
    public MountainsVo getMountainById(@PathVariable Long id) {
        return mountainsService.retrieveMountainById(id);
    }

    @GetMapping("/name/{name}")
    public String getMountainByName(@PathVariable String name) {
    	String mountainId = mountainsService.retrieveMountainByName(name);
        if (mountainId != null) {
            return mountainId;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 이름의 산을 찾을 수 없습니다: " + name);
        }
    }    
    
    @GetMapping("/search")
    public List<MountainsVo> searchMountains(@RequestParam String keyword) {
        return mountainsService.searchMountains(keyword);
    }
}
