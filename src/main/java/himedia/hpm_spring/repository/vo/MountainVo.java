package himedia.hpm_spring.repository.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MountainVo {

	private Long id;			// 	산 고유번호
	private String name;		//	산 이름
	private String course;		//	등산 코스
	private String level;		//	난이도
	private String location;	//	산 위치
	private String longitude;	//	경도
	private String latitude;	// 	위도
	private String height;		//	고도
	private String time;		//	소요시간
	private String explanation;	//	산 정보
	private String weather;		//	날씨 정보
}
