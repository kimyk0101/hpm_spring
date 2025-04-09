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

    // 특정 산 정보 조회 (id)
    public MountainsVo retrieveMountainById(Long id) {
        return mountainsMapper.selectMountainById(id);
    }
    
    // 특정 산 정보 조회 (name)
    public String retrieveMountainByName(String name) {
    	MountainsVo mountain = mountainsMapper.selectMountainByName(name);
        if (mountain != null) {
            return mountain.getId().toString(); // 산 ID를 문자열로 반환
        } else {
            return null; // 산 코드가 없을 경우 null 반환
        }
    }
    
    // 검색 기능
    public List<MountainsVo> searchMountains(String keyword) {
        return mountainsMapper.searchMountains(keyword);
    }

}
