package himedia.hpm_spring.mappers;

import java.util.List;

import himedia.hpm_spring.repository.vo.MountainCourseVo;

public interface MountainCourseMapper {

	List<MountainCourseVo> findByMountainId(Long mountainId);
}
