package base.negocio;
public class RetornoNegocio {
    
    private boolean valido;
    private String retornoMsg;

    public RetornoNegocio(boolean valido, String retornoMsg) {
        this.valido = valido;
        this.retornoMsg = retornoMsg;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public String getReturnoMsg() {
        return retornoMsg;
    }

    public void setReturnoMsg(String retornoMsg) {
        this.retornoMsg = retornoMsg;
    }
}
