package gerador_cns;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        ValidaCns validar_cns_gerado = new ValidaCns();
        String cnsGerado = primeiroBloco();
        /**O Retorno já é uma condição verdadeira ou falsa não precisa comparar*/
        if (validar_cns_gerado.validacao(cnsGerado)) {
            cnsGerado = format("###.####.####.####", cnsGerado );
            System.out.println("----------------------CNS----------------------\n");
            System.out.println("Gerado cartão = "+ cnsGerado +" \n");
            System.out.println("-----------------------------------------------\n");
        } else {
            System.out.println("Cartão Gerado Invalido -> "+cnsGerado);
        }
    }

    public static String primeiroBloco() {
        /**Sugestão de já inserir direto no array o primeiro digito e utilizar o List não precisa saber o tamanho do objeto é mais fácil*/
        List<Integer> numerosMeio = new ArrayList<>();

        Random random = new Random();
        numerosMeio.add(random.nextInt((9 - 7) + 1) + 7);

        for (int i = 1; i < 11; i++) {
            numerosMeio.add(random.nextInt(10));
        }

        /**reaproveitar o array para realizar a soma não precisa realizar o subString */
        return (montarCNS(numerosMeio));
    }

    public static String montarCNS(List<Integer> listaCNS) {
        float soma = 0;

        /** utilizar o array de listaCNS para realizar a soma */
        for (int x = 0; x < listaCNS.size(); x++) {
            soma += listaCNS.get(x) * (15-x);
        }

        int restante = (int) (soma / 11);
        restante = (int) ((11 * (restante + 1)) - soma);

        /**mesma situação os 3 for são identicos sempre diminuindo 1, criando mais um for no primeiro laço conseguimos a repetição necessário com um unico código*/
        // Gera os 4 digitos restante ---- O correto seria montar em outro método que retorna-se o arraylist montado
        for (int value = 4; value > 0; value--) {
            Integer digito = 0;
            for (int i = 0; i <= 9; i++) {
                if (i * value <= restante) {
                    restante = restante - (i * value);
                    digito = i;
                }
            }
            listaCNS.add(digito);
        }

        /**monta uma vez o Array de String*/
        StringBuilder Cns_final = new StringBuilder();
        for (Integer numero : listaCNS) {
            /**não precisa transformar em String o inteiro*/
            Cns_final.append(numero);
        }

        return Cns_final.toString();
    }

    /**
     * @description formatar CNS
     * */
    private static String format(String pattern, Object value) {
        MaskFormatter mask;
        try {
            mask = new MaskFormatter(pattern);
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
