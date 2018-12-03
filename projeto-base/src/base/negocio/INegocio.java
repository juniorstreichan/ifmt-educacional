package base.negocio;

public interface INegocio<VO> {

    RetornoNegocio validarInclusao(VO vo);

    RetornoNegocio validarAlteracao(VO vo);

    RetornoNegocio validarExclusao(VO vo);
    
}
