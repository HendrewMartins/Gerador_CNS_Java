package gerador_cns;

public class ValidaCns {

    /**Classe de validação para verificar se o CNS é valido
     * FONTE: integracao.esusab.ufsc.br/ledi/documentacao/regras/algoritmo_CNS.html
    */
    public boolean validacao(String cns) {
        float soma = 0;

        /**como existe um padrão nos campos de sempre somarem um numero em relação ao anterior pode ser utilizado um for e incrementar os valores */
        for (int x = 0; x < 15; x++) {
            soma +=  (Integer.valueOf(cns.substring(x, x + 1)).intValue()) * (15 - x);
        }

        /**como retorna um boolean vc pode comparar direto no return não seria necessário um if */
        return (soma % 11) == 0;

    }
}
