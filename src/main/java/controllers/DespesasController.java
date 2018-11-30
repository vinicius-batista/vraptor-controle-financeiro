package controllers;

import java.util.ArrayList;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import helpers.AuthHelper;
import modelo.Categoria;
import modelo.ControleMensal;
import modelo.Despesa;
import modelo.dao.DAOFactory;

@Controller
public class DespesasController {

  @Inject
  private Result result;

  private void setErrorMessage(String errorMessage) {
    if (errorMessage != null) {
      result.include("errorMessage", errorMessage);
      result.include("hasError", true);
    } else {
      result.include("hasError", false);
    }
  }

  @Get
  @Path("/despesas/adicionar")
  public void adicionarForm(String errorMessage) {
    AuthHelper.isAuthenticated(result);
    this.setErrorMessage(errorMessage);

    var categorias = new ArrayList<Categoria>();
    try {
      var daoFac = new DAOFactory();
      daoFac.abrirConexao();
      try {
        var categoriasDAO = daoFac.criarCategoriaDAO();

        categorias = categoriasDAO.buscarLista();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      } finally {
        daoFac.fecharConexao();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    result.include("categorias", categorias);
  }

  @Post
  @Path("/despesas/adicionar")
  public void adicionar(Despesa despesa) {
    AuthHelper.isAuthenticated(result);
    var userEmail = SecurityUtils.getSubject().getPrincipal().toString();

    try {
      var daoFac = new DAOFactory();
      daoFac.abrirConexao();
      try {
        var controleMensalDAO = daoFac.criarControleMensalDAO();
        var usuarioDAO = daoFac.criarUsuarioDAO();
        var despesaDAO = daoFac.criarDespesaDAO();

        var usuario = usuarioDAO.buscar(userEmail);

        var mes = despesa.getData().getMonthValue();
        var ano = despesa.getData().getYear();
        var controleMensal = controleMensalDAO.buscar(usuario.getId(), mes, ano);

        if (controleMensal == null) {
          controleMensal = new ControleMensal();
          controleMensal.setAno(ano);
          controleMensal.setMes(mes);
          controleMensal.setUsuarioId(usuario.getId());

          controleMensalDAO.gravar(controleMensal);
        }
        
        despesa.setControleMensalId(controleMensal.getId());

        despesaDAO.gravar(despesa);

        result.redirectTo(HomeController.class).index(null);
      } catch (Exception e) {
        result.redirectTo(DespesasController.class).adicionarForm(e.getMessage());
      } finally {
        daoFac.fecharConexao();
      }
    } catch (Exception e) {
      result.redirectTo(DespesasController.class).adicionarForm(e.getMessage());
    }
  }

  @Get
  @Path("/despesas/{despesa.id}/edit")
  public Despesa editarForm(Despesa despesa, String errorMessage) {
    AuthHelper.isAuthenticated(result);
    this.setErrorMessage(errorMessage);

    var categorias = new ArrayList<Categoria>();
    try {
      var daoFac = new DAOFactory();
      daoFac.abrirConexao();
      try {
        var despesaDAO = daoFac.criarDespesaDAO();
        despesa = despesaDAO.buscar(despesa.getId());

        var categoriasDAO = daoFac.criarCategoriaDAO();
        categorias = categoriasDAO.buscarLista();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      } finally {
        daoFac.fecharConexao();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    result.include("categorias", categorias);
    return despesa;
  }

  @Post
  @Path("/despesas/editar")
  public void editar(Despesa despesa) {
    AuthHelper.isAuthenticated(result);

    try {
      var daoFac = new DAOFactory();
      daoFac.abrirConexao();
      try {
        var despesaDAO = daoFac.criarDespesaDAO();
        despesaDAO.editar(despesa);

        result.redirectTo(HomeController.class).index(null);
      } catch (Exception e) {
        result.redirectTo(DespesasController.class).editarForm(despesa, e.getMessage());
      } finally {
        daoFac.fecharConexao();
      }
    } catch (Exception e) {
      result.redirectTo(DespesasController.class).editarForm(despesa, e.getMessage());
    }
  }

  @Path("/despesas/{despesa.id}/deletar")
  public void deletar(Despesa despesa) {
    AuthHelper.isAuthenticated(result);

    try {
      var daoFac = new DAOFactory();
      daoFac.abrirConexao();
      try {
        var despesaDAO = daoFac.criarDespesaDAO();
        despesaDAO.remover(despesa);

        result.redirectTo(HomeController.class).index(null);
      } catch (Exception e) {
        System.out.println(e.getMessage());
        result.redirectTo(HomeController.class).index(null);
      } finally {
        daoFac.fecharConexao();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      result.redirectTo(HomeController.class).index(null);
    }
  }
}