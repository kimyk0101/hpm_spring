package himedia.hpm_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import himedia.hpm_spring.mappers.MountainCourseMapper;
import himedia.hpm_spring.repository.vo.MountainCourseVo;

@Service
public class MountainCourseService {
	
	@Autowired
    private MountainCourseMapper mountainCourseMapper;
	
	public List<MountainCourseVo> retrieveMountainById(Long id) {
        return mountainCourseMapper.findByMountainId(id);
    }
}
