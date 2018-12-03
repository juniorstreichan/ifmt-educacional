package base.vo;
import java.io.Serializable;
public abstract class EntidadeVO extends BaseVO implements Serializable, IEntidadeVO {

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (!this.getId().equals(((EntidadeVO) obj).getId())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return this.getId().intValue();
    }
}
