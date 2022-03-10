package ekfkawl.dapp.domain.web.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResult<T> {
    private T data;
    private boolean success;
}