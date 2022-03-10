package ekfkawl.dapp.domain.web.service;

import ekfkawl.dapp.domain.web.response.SingleResult;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {
    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        result.setSuccess(true);
        return result;
    }
}
