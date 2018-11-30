package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Categoria;
import modelo.Despesa;

public class DespesaDAO {
  private final Connection conexao;

  public DespesaDAO(Connection conexao) {
    this.conexao = conexao;
  }

  public void gravar(Despesa despesa) throws SQLException {
    String query = "INSERT INTO despesas (controle_mensal_id, descricao, valor, data, categoria_id) VALUES "
        + "(?, ?, ?, ?, ?);";
    try (PreparedStatement pstmt = conexao.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
      pstmt.setInt(1, despesa.getControleMensalId());
      pstmt.setString(2, despesa.getDescricao());
      pstmt.setDouble(3, despesa.getValor());
      pstmt.setDate(4, despesa.getData());
      pstmt.setInt(5, despesa.getCategoria().getId());

      var resultado = pstmt.executeUpdate();
      if (resultado == 1) {
        var rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
          despesa.setId(rs.getInt(1));
        }
        System.out.println("\nInserção bem sucedida.");
      } else {
        System.out.println("A inserção não foi feita corretamente.");
      }
    }
  }

  public ArrayList<Despesa> buscarLista(int controleMensalId) throws SQLException {
    ArrayList<Despesa> despesas = new ArrayList<Despesa>();
    var query = "SELECT * FROM despesas inner join categorias on categorias.id = despesas.categoria_id"
        + " WHERE despesas.controle_mensal_id = ?";

    try (PreparedStatement pstmt = conexao.prepareStatement(query)) {
      pstmt.setInt(1, controleMensalId);
      try (var rs = pstmt.executeQuery()) {
        while (rs.next()) {
          var categoria = new Categoria(rs.getInt(7), rs.getString(8));
          var despesa = new Despesa(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getDate(5),
              categoria);
          despesas.add(despesa);
        }
      }
    }

    return despesas;
  }

  public Despesa buscar(int id) throws SQLException {
    Despesa despesa = null;
    var query = "SELECT * FROM despesas inner join categorias on categorias.id = despesas.categoria_id"
        + " WHERE despesas.id = ?";
    try (PreparedStatement pstmt = conexao.prepareStatement(query)) {
      pstmt.setInt(1, id);
      try (var rs = pstmt.executeQuery()) {
        if (rs.next()) {
          var categoria = new Categoria(rs.getInt(7), rs.getString(8));
          despesa = new Despesa(rs.getInt(1), rs.getInt(2), rs.getString(4), rs.getDouble(3), rs.getDate(5), categoria);
        }
      }
    }
    return despesa;
  }

  public void editar(Despesa despesa) throws SQLException {
    var query = "update despesas set descricao = ?, valor = ?, data = ?, categoria_id = ? where id = ?";
    try (PreparedStatement pstmt = conexao.prepareStatement(query)) {
      pstmt.setString(1, despesa.getDescricao());
      pstmt.setDouble(2, despesa.getValor());
      pstmt.setDate(3, despesa.getData());
      pstmt.setInt(4, despesa.getCategoria().getId());
      pstmt.setInt(5, despesa.getId());

      var resultado = pstmt.executeUpdate();
      if (resultado == 1) {
        System.out.println("update bem sucedido.");
      } else {
        System.out.println("update não foi feito corretamente.");
      }
    }
  }

  public void remover(Despesa despesa) throws SQLException {
    var query = "DELETE FROM despesas WHERE id = ?";
    try (PreparedStatement pstmt = conexao.prepareStatement(query)) {
      pstmt.setInt(1, despesa.getId());
      var resultado = pstmt.executeUpdate();
      if (resultado == 1) {
        System.out.println("Remoção efetuada com sucesso.");
      } else {
        System.out.println("Não foi possível efetuar a remoção.");
      }
    }
  }
}