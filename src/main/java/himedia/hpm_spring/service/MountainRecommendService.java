package himedia.hpm_spring.service;

import himedia.hpm_spring.mappers.MountainRecommendMapper;
import himedia.hpm_spring.repository.vo.MountainRecommendVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MountainRecommendService {

    @Autowired
    private MountainRecommendMapper mountainRecommendMapper;

    public List<MountainRecommendVo> findAllMountainRecommends() {
        return mountainRecommendMapper.findAllMountainRecommends();
    }
}