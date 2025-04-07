package himedia.hpm_spring.mappers;

import himedia.hpm_spring.repository.vo.MountainsVo;


import java.util.List;


public interface MountainsMapper {

    List<MountainsVo> selectAllMountains();

    MountainsVo selectMountainById(Long id);

    List<MountainsVo> selectMountainsByLocation(String location);

    List<MountainsVo> selectMountainsByHeight(String height);
}
