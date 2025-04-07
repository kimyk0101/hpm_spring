package himedia.hpm_spring.controller;

import himedia.hpm_spring.repository.vo.MountainsVo;
import himedia.hpm_spring.service.MountainsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/location")
    public List<MountainsVo> getMountainsByLocation(@RequestParam String location) {
        return mountainsService.retrieveMountainsByLocation(location);
    }

    @GetMapping("/height")
    public List<MountainsVo> getMountainsByHeight(@RequestParam String height) {
        return mountainsService.retrieveMountainsByHeight(height);
    }
}
