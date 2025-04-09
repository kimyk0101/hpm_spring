package himedia.hpm_spring.mappers;

import himedia.hpm_spring.repository.vo.MountainVo;


import java.util.List;


public interface MountainMapper {

    List<MountainVo> selectAllMountains();

    MountainVo selectMountainById(Long id);
        
    MountainVo selectMountainByName(String name);
    
    List<MountainVo> searchMountains(String keyword);
    
}
