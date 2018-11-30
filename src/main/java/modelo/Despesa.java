package modelo;

import java.time.LocalDate;

public class Despesa extends Financeiro {
  private Categoria categoria;

  public Despesa() {

  }

  public Despesa(int id, int controleMensalId, String descricao, double valor, LocalDate data, Categoria categoria) {
    super(id, controleMensalId, descricao, valor, data);
    this.categoria = categoria;
  }

  /**
   * @return the categoria
   */
  public Categoria getCategoria() {
    return categoria;
  }

  /**
   * @param categoria the categoria to set
   */
  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }
}