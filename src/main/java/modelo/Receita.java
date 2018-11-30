package modelo;

import java.time.LocalDate;

public class Receita extends Financeiro {
  public Receita() {
  }

  public Receita(int id, int controleMensalId, String descricao, double valor, LocalDate data) {
    super(id, controleMensalId, descricao, valor, data);
  }
}