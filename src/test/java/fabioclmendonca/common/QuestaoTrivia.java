package fabioclmendonca.common;

public class QuestaoTrivia {

    private Integer numeroDeQuestoes;
    private String categoria;
    private String dificuldade;
    private String tipo;

    public Integer getNumeroDeQuestoes() {
        return numeroDeQuestoes;
    }

    public void setNumeroDeQuestoes(Integer numeroDeQuestoes) {
        this.numeroDeQuestoes = numeroDeQuestoes;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
