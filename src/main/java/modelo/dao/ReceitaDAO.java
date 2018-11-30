package modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Receita;

public class ReceitaDAO {
  private final Connection conexao;

  public ReceitaDAO(Connection conexao) {
    this.conexao = conexao;
  }

  public void gravar(Receita receita) throws SQLException {
    String query = "INSERT INTO receitas (controle_mensal_id, descricao, valor, data) VALUES " + "(?, ?, ?, ?);";
    try (PreparedStatement pstmt = conexao.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
      pstmt.setInt(1, receita.getControleMensalId());
      pstmt.setString(2, receita.getDescricao());
      pstmt.setDouble(3, receita.getValor());
      pstmt.setDate(4, Date.valueOf(receita.getData()));

      var resultado = pstmt.executeUpdate();
      if (resultado == 1) {
        var rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
          receita.setId(rs.getInt(1));
        }
        System.out.println("\nInserção bem sucedida.");
      } else {
        System.out.println("A inserção não foi feita corretamente.");
      }
    }
  }

  public ArrayList<Receita> buscarLista(int controleMensalId) throws SQLException { 
    var receitas = new ArrayList<Receita>();
    var query = "SELECT * FROM receitas WHERE controle_mensal_id = ?";

    try (PreparedStatement pstmt = conexao.prepareStatement(query)) {
      pstmt.setInt(1, controleMensalId);
      try (var rs = pstmt.executeQuery()) {
        while (rs.next()) {
          var receita = new Receita(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getDate(5).toLocalDate());
          receitas.add(receita);
        }
      }
    }
    return receitas;
  }

  public Receita buscar(int id) throws SQLException {
    Receita receita = null;
    var query = "SELECT * FROM receitas WHERE id = ?";

    try (PreparedStatement pstmt = conexao.prepareStatement(query)) {
      pstmt.setInt(1, id);
      try (var rs = pstmt.executeQuery()) {
        if (rs.next()) {
          receita = new Receita(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getDate(5).toLocalDate());
        }
      }
    }
    return receita;
  }

  public void editar(Receita receita) throws SQLException {
    var query = "update receitas set descricao = ?, valor = ?, data = ? where id = ?";
    try (PreparedStatement pstmt = conexao.prepareStatement(query)) {
      pstmt.setString(1, receita.getDescricao());
      pstmt.setDouble(2, receita.getValor());
      pstmt.setDate(3, Date.valueOf(receita.getData()));
      pstmt.setInt(4, receita.getId());

      var resultado = pstmt.executeUpdate();
      if (resultado == 1) {
        System.out.println("update bem sucedido.");
      } else {
        System.out.println("update não foi feito corretamente.");
      }
    }
  }

  public void remover(Receita receita) throws SQLException {
    var query = "DELETE FROM receitas WHERE id = ?";
    try (PreparedStatement pstmt = conexao.prepareStatement(query)) {
      pstmt.setInt(1, receita.getId());
      var resultado = pstmt.executeUpdate();
      if (resultado == 1) {
        System.out.println("Remoção efetuada com sucesso.");
      } else {
        System.out.println("Não foi possível efetuar a remoção.");
      }
    }
  }
}