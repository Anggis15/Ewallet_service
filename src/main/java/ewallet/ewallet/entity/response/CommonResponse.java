package ewallet.ewallet.entity.response;

import lombok.Getter;
import lombok.Setter;

public class CommonResponse {
    @Getter
    @Setter
    private String code;

    @Getter @Setter
    private String status;

    @Getter @Setter
    private String message;
}
