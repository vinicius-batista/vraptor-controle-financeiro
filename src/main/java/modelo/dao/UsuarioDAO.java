package modelo.dao;

import modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

  private final Connection conexao;

  public UsuarioDAO(Connection conexao) {
    this.conexao = conexao;
  }

  public void registrar(Usuario usuario) throws SQLException {
    var query = "INSERT INTO usuarios (nome, email, senha, role) VALUES " + "(?, ?, ?, ?);";
    try (PreparedStatement pstmt = conexao.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
      pstmt.setString(1, usuario.getNome());
      pstmt.setString(2, usuario.getEmail());
      pstmt.setString(3, usuario.getSenha());
      pstmt.setString(4, usuario.getRole());

      int resultado = pstmt.executeUpdate();
      if (resultado == 1) {
        ResultSet rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
          usuario.setId(rs.getInt(1));
        }
        System.out.println("\nInserção bem sucedida.");
      } else {
        System.out.println("A inserção não foi feita corretamente.");
      }
    }
  }

  public Usuario buscar(int id) throws SQLException {
    Usuario usuario = null;
    var query = "SELECT * FROM usuarios WHERE id = ?";
    try (PreparedStatement pstmt = conexao.prepareStatement(query)) {
      pstmt.setInt(1, id);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          usuario = new Usuario();
          usuario.setId(rs.getInt(1));
          usuario.setNome(rs.getString(2));
          usuario.setEmail(rs.getString(3));
          usuario.setSenha(rs.getString(4));
          usuario.setRole(rs.getString(5));
        }
      }
    }
    return usuario;
  }

  public Usuario buscar(String email) throws SQLException {
    Usuario usuario = null;
    var query = "SELECT * FROM usuarios WHERE email = ?";
    try (PreparedStatement pstmt = conexao.prepareStatement(query)) {
      pstmt.setString(1, email);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          usuario = new Usuario();
          usuario.setId(rs.getInt(1));
          usuario.setNome(rs.getString(2));
          usuario.setEmail(rs.getString(3));
          usuario.setSenha(rs.getString(4));
          usuario.setRole(rs.getString(5));
        }
      }
    }
    return usuario;
  }
}
