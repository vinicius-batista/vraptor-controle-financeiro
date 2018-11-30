package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.ControleMensal;

public class ControleMensalDAO {
  private final Connection conexao;

  public ControleMensalDAO(Connection conexao) {
    this.conexao = conexao;
  }

  public void gravar(ControleMensal controleMensal) throws SQLException {
    String query = "INSERT INTO controle_mensais (mes, ano, usuario_id) VALUES (?, ?, ?);";
    try (PreparedStatement pstmt = conexao.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
      pstmt.setInt(1, controleMensal.getMes());
      pstmt.setInt(2, controleMensal.getAno());
      pstmt.setInt(3, controleMensal.getUsuarioId());

      var resultado = pstmt.executeUpdate();
      if (resultado == 1) {
        var rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
          controleMensal.setId(rs.getInt(1));
        }
        System.out.println("\nInserção bem sucedida.");
      } else {
        System.out.println("A inserção não foi feita corretamente.");
      }
    }
  }

  public ControleMensal buscar(int usuarioId, int mes, int ano) throws SQLException {
    ControleMensal controleMensal = null;

    var query = "SELECT * FROM controle_mensais WHERE usuario_id = ? and (mes = ? and ano = ?);";

    try (PreparedStatement pstmt = conexao.prepareStatement(query)) {
      pstmt.setInt(1, usuarioId);
      pstmt.setInt(2, mes);
      pstmt.setInt(3, ano);
      try (var rs = pstmt.executeQuery()) {
        if (rs.next()) {
          controleMensal = new ControleMensal();
          controleMensal.setId(rs.getInt(1) ); 
          controleMensal.setMes(rs.getInt(2));  
          controleMensal.setAno(rs.getInt(3));
          controleMensal.setUsuarioId(rs.getInt(4));
          
        }
      }
    }

    if (controleMensal != null) {
      var despesaDAO = new DespesaDAO(this.conexao);
      var despesas = despesaDAO.buscarLista(controleMensal.getId());

      var receitaDao = new ReceitaDAO(this.conexao);
      var receitas = receitaDao.buscarLista(controleMensal.getId());

      controleMensal.setDespesas(despesas);
      controleMensal.setReceitas(receitas);

      var totalReceitas = receitas.stream().map(a -> a.getValor()).reduce(0.0, (a, b) -> a + b);
      var totalDespesas = despesas.stream().map(a -> a.getValor()).reduce(0.0, (a, b) -> a + b);

      controleMensal.setTotalDespesas(totalDespesas);
      controleMensal.setTotalReceitas(totalReceitas);
    }

    return controleMensal;
  }
}