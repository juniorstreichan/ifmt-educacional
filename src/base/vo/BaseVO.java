package base.vo;
import java.io.Serializable;

public abstract class BaseVO implements Serializable, IBaseVO {

    private static final long serialVersionUID = 1L;
    protected String validacaoMsg;

    public BaseVO() {
        this.validacaoMsg = "";
    }

    @Override
    public String getValidacaoMsg() {
        return validacaoMsg;
    }
    
    @Override
    public abstract String toString();
}
