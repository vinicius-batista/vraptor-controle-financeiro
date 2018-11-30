package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Categoria;

public class CategoriaDAO {
  private final Connection conexao;

  public CategoriaDAO(Connection conexao) {
    this.conexao = conexao;
  }

  public void gravar(Categoria categoria) throws SQLException {
    String query = "INSERT INTO categorias (nome) VALUES (?);";
    try (PreparedStatement pstmt = conexao.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
      pstmt.setString(1, categoria.getNome());

      var resultado = pstmt.executeUpdate();
      if (resultado == 1) {
        var rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
          categoria.setId(rs.getInt(1));
        }
        System.out.println("\nInserção bem sucedida.");
      } else {
        System.out.println("A inserção não foi feita corretamente.");
      }
    }
  }

  public ArrayList<Categoria> buscarLista() throws SQLException { 
    var categorias = new ArrayList<Categoria>();
    var query = "SELECT * FROM categorias";

    try (PreparedStatement pstmt = conexao.prepareStatement(query)) {
      try (var rs = pstmt.executeQuery()) {
        while (rs.next()) {
          var categoria = new Categoria(rs.getInt(1), rs.getString(2));
          categorias.add(categoria);
        }
      }
    }
    return categorias;
  }

  public Categoria buscar(int id) throws SQLException {
    Categoria categoria = null;
    var query = "SELECT * FROM categorias WHERE id = ?";

    try (PreparedStatement pstmt = conexao.prepareStatement(query)) {
      pstmt.setInt(1, id);
      try (var rs = pstmt.executeQuery()) {
        if (rs.next()) {
          categoria = new Categoria(rs.getInt(1), rs.getString(2));
        }
      }
    }
    return categoria;
  }

  public void editar(Categoria categoria) throws SQLException {
    var query = "update categorias set nome = ? where id = ?";
    try (PreparedStatement pstmt = conexao.prepareStatement(query)) {
      pstmt.setString(1, categoria.getNome());
      pstmt.setInt(2, categoria.getId());

      var resultado = pstmt.executeUpdate();
      if (resultado == 1) {
        System.out.println("update bem sucedido.");
      } else {
        System.out.println("update não foi feito corretamente.");
      }
    }
  }

  public void remover(Categoria categoria) throws SQLException {
    var query = "DELETE FROM categorias WHERE id = ?";
    try (PreparedStatement pstmt = conexao.prepareStatement(query)) {
      pstmt.setInt(1, categoria.getId());
      var resultado = pstmt.executeUpdate();
      if (resultado == 1) {
        System.out.println("Remoção efetuada com sucesso.");
      } else {
        System.out.println("Não foi possível efetuar a remoção.");
      }
    }
  }
}