package himedia.hpm_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import himedia.hpm_spring.mappers.MountainsMapper;
import himedia.hpm_spring.repository.vo.MountainsVo;

@Service
public class MountainsService {

    @Autowired
    private MountainsMapper mountainsMapper;

    // 모든 산 정보 조회
    public List<MountainsVo> retrieveAllMountains() {
        return mountainsMapper.selectAllMountains();
    }

    // 특정 산 정보 조회
    public MountainsVo retrieveMountainById(Long id) {
        return mountainsMapper.selectMountainById(id);
    }

    // 특정 지역의 산 정보 조회
    public List<MountainsVo> retrieveMountainsByLocation(String location) {
        return mountainsMapper.selectMountainsByLocation(location);
    }

    // 특정 고도 이상의 산 정보 조회
    public List<MountainsVo> retrieveMountainsByHeight(String height) {
        return mountainsMapper.selectMountainsByHeight(height);
    }
}
