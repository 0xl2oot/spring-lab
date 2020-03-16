package tech.yonghong.sign;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author yonghong
 * @date 2020/3/13
 **/
@Data
public class RecordSearchForm {

    @NotNull(message = "不能为空")
    private Long agencyId;

    private String deviceAlias;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
