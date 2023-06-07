package ewallet.ewallet.entity.response;

import ewallet.ewallet.constant.SuccesCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class SuccesResponse <T> extends CommonResponse{
    @Getter
    @Setter
    T data;

    public SuccesResponse(String message, T data) {
        super.setCode(SuccesCode.SS.toString());
        super.setMessage(message);
        setStatus(HttpStatus.OK.name());
        this.data = data;
    }
}
