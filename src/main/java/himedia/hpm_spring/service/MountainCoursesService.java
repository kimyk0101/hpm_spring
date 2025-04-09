package himedia.hpm_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import himedia.hpm_spring.mappers.MountainCoursesMapper;
import himedia.hpm_spring.repository.vo.MountainCoursesVo;

@Service
public class MountainCoursesService {
	
	@Autowired
    private MountainCoursesMapper mountainCoursesMapper;
	
	public List<MountainCoursesVo> retrieveMountainById(Long id) {
        return mountainCoursesMapper.findByMountainId(id);
    }
}
