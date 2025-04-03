package himedia.hpm_spring.repository.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ClubCommentsVo {
    private Long id;
    private String content;
    private LocalDateTime updateDate;
    private Long clubsId;
    private Long usersId; // clubs 테이블의 id와 연결
    private String nickname; // 추가
}