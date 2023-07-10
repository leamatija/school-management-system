package com.internship.spring.project.schoolmanagementsystem.domain.dto.classroom;
import lombok.*;

import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassroomSessionRequestDTO {
    private Integer teacherId;
    private List<Integer> weekDays;
    private String startLocalDate;
    private String endLocalDate;
    @Pattern(regexp = "((?i)[0-9]{1,2}:??[0-9]{0,2})",message = "Invalid format, required format is hh:mm")
    private String startTime;
    @Pattern(regexp = "((?i)[0-9]{1,2}:??[0-9]{0,2})",message = "Invalid format, required format is hh:mm")
    private String endTime;
    private Integer subjectId;


}
