package servico;

public interface IServico {

    public String cadastrar(String strObjeto);
    
    public String alterar(String strObjeto);
    
    public String remover(String strObjeto);
    
    public String buscarPorId(String strObjeto);
    
}
