package modelo;

import java.util.ArrayList;

public class Usuario {
  private int id;
  private String nome;
  private String senha;
  private String email;
  private String role;
  private ArrayList<ControleMensal> controleMensals;

  public Usuario(String senha, String email) {
    this.senha = senha;
    this.email = email;
  }

  public Usuario() {

  }

  /**
   * @return the role
   */
  public String getRole() {
    return role;
  }

  /**
   * @param role the role to set
   */
  public void setRole(String role) {
    this.role = role;
  }

  /**
   * @return the controleMensals
   */
  public ArrayList<ControleMensal> getControleMensals() {
    return controleMensals;
  }

  /**
   * @param controleMensals the controleMensals to set
   */
  public void setControleMensals(ArrayList<ControleMensal> controleMensals) {
    this.controleMensals = controleMensals;
  }

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the senha
   */
  public String getSenha() {
    return senha;
  }

  /**
   * @param senha the senha to set
   */
  public void setSenha(String senha) {
    this.senha = senha;
  }

  /**
   * @return the nome
   */
  public String getNome() {
    return nome;
  }

  /**
   * @param nome the nome to set
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }
}