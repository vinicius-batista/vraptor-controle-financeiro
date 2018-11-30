package modelo;

import java.sql.Date;

public class Receita extends Financeiro {
  public Receita() {
  }

  public Receita(int id, int controleMensalId, String descricao, double valor, Date data) {
    super(id, controleMensalId, descricao, valor, data);
  }
}