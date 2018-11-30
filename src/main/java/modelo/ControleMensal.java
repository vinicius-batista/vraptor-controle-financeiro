package modelo;

import java.util.ArrayList;

public class ControleMensal {
  private int id;
  private int mes = 0;
  private int ano = 0;
  private int usuarioId;
  private double totalReceitas;
  private double totalDespesas;
  private ArrayList<Receita> receitas;
  private ArrayList<Despesa> despesas;

  /**
   * @return the despesas
   */
  public ArrayList<Despesa> getDespesas() {
    return despesas;
  }

  /**
   * @param despesas the despesas to set
   */
  public void setDespesas(ArrayList<Despesa> despesas) {
    this.despesas = despesas;
  }

  /**
   * @return the receitas
   */
  public ArrayList<Receita> getReceitas() {
    return receitas;
  }

  /**
   * @param receitas the receitas to set
   */
  public void setReceitas(ArrayList<Receita> receitas) {
    this.receitas = receitas;
  }

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return totalReceitas - totalDespesas
   */
  public double getBalanco() {
    return totalReceitas - totalDespesas;
  }

  /**
   * @return the totalDespesas
   */
  public double getTotalDespesas() {
    return totalDespesas;
  }

  /**
   * @param totalDespesas the totalDespesas to set
   */
  public void setTotalDespesas(double totalDespesas) {
    this.totalDespesas = totalDespesas;
  }

  /**
   * @return the totalReceitas
   */
  public double getTotalReceitas() {
    return totalReceitas;
  }

  /**
   * @param totalReceitas the totalReceitas to set
   */
  public void setTotalReceitas(double totalReceitas) {
    this.totalReceitas = totalReceitas;
  }

  /**
   * @return the mes
   */
  public int getMes() {
    return mes;
  }

  /**
   * @return the usuarioId
   */
  public int getUsuarioId() {
    return usuarioId;
  }

  /**
   * @param usuarioId the usuarioId to set
   */
  public void setUsuarioId(int usuarioId) {
    this.usuarioId = usuarioId;
  }

  /**
   * @return the ano
   */
  public int getAno() {
    return ano;
  }

  /**
   * @param ano the ano to set
   */
  public void setAno(int ano) {
    this.ano = ano;
  }

  /**
   * @param mes the mes to set
   */
  public void setMes(int mes) {
    this.mes = mes;
  }

}