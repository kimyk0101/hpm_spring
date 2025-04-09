package himedia.hpm_spring.mappers;

import java.util.List;

import himedia.hpm_spring.repository.vo.MountainCoursesVo;

public interface MountainCoursesMapper {

	List<MountainCoursesVo> findByMountainId(Long mountainId);
}
