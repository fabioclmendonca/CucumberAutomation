package fabioclmendonca.common;

import java.util.stream.Stream;

public enum TipoQuestao {
    MULTIPLE("Multiple Choice", "multiple"),
    BOOLEAN("True / False", "boolean");


    private String valorTela;
    private String valorJson;
    private TipoQuestao(String valorTela, String valorJson){
        this.valorTela = valorTela;
        this.valorJson = valorJson;
    }

    public String getValorTela() {
        return valorTela;
    }

    public String getValorJson() {
        return valorJson;
    }

    public static TipoQuestao getTipoQuestaoPorValorTela(String valorTela){
        return Stream.of(TipoQuestao.values())
                .filter(tipoQuestao -> tipoQuestao.getValorTela().equals(valorTela))
                .findFirst().get();
    }

}
