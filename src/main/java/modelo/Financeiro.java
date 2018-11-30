package modelo;

import java.sql.Date;

public abstract class Financeiro {
  private int id;
  private int controleMensalId;
  private String descricao;
  private double valor;
  private Date data;

  public Financeiro() {
  }

  public Financeiro(int id, int controleMensalId, String descricao, double valor, Date data) {
    this.id = id;
    this.controleMensalId = controleMensalId;
    this.descricao = descricao;
    this.valor = valor;
    this.data = data;
  }

  /**
   * @return the data
   */
  public Date getData() {
    return data;
  }

  /**
   * @param data the data to set
   */
  public void setData(Date data) {
    this.data = data;
  }

  /**
   * @return the valor
   */
  public double getValor() {
    return valor;
  }

  /**
   * @param valor the valor to set
   */
  public void setValor(double valor) {
    this.valor = valor;
  }

  /**
   * @return the descricao
   */
  public String getDescricao() {
    return descricao;
  }

  /**
   * @param descricao the descricao to set
   */
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  /**
   * @return the controleMensalId
   */
  public int getControleMensalId() {
    return controleMensalId;
  }

  /**
   * @param controleMensalId the controleMensalId to set
   */
  public void setControleMensalId(int controleMensalId) {
    this.controleMensalId = controleMensalId;
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
}