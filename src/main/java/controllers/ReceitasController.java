package controllers;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import modelo.ControleMensal;
import modelo.Receita;
import modelo.dao.DAOFactory;

@Controller
public class ReceitasController {

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
  @Path("/receitas/adicionar")
  public void adicionarForm(String errorMessage) {
    this.setErrorMessage(errorMessage);
  }

  @Post
  @Path("/receitas/adicionar")
  public void adicionar(Receita receita) {
    var userEmail = SecurityUtils.getSubject().getPrincipal().toString();

    try {
      var daoFac = new DAOFactory();
      daoFac.abrirConexao();
      try {
        var controleMensalDAO = daoFac.criarControleMensalDAO();
        var usuarioDAO = daoFac.criarUsuarioDAO();
        var receitaDAO = daoFac.criarReceitaDAO();

        var usuario = usuarioDAO.buscar(userEmail);

        var mes = receita.getData().getMonthValue();
        var ano = receita.getData().getYear();
        var controleMensal = controleMensalDAO.buscar(usuario.getId(), mes, ano);

        if (controleMensal == null) {
          controleMensal = new ControleMensal();
          controleMensal.setAno(ano);
          controleMensal.setMes(mes);
          controleMensal.setUsuarioId(usuario.getId());

          controleMensalDAO.gravar(controleMensal);
        }

        receita.setControleMensalId(controleMensal.getId());

        receitaDAO.gravar(receita);

        result.redirectTo(HomeController.class).index(null);
      } catch (Exception e) {
        result.redirectTo(ReceitasController.class).adicionarForm(e.getMessage());
      } finally {
        daoFac.fecharConexao();
      }
    } catch (Exception e) {
      result.redirectTo(ReceitasController.class).adicionarForm(e.getMessage());
    }
  }

  @Get
  @Path("/receitas/{receita.id}/edit")
  public Receita editarForm(Receita receita, String errorMessage) {
    this.setErrorMessage(errorMessage);

    try {
      var daoFac = new DAOFactory();
      daoFac.abrirConexao();
      try {
        var receitaDAO = daoFac.criarReceitaDAO();

        receita = receitaDAO.buscar(receita.getId());
      } catch (Exception e) {
        System.out.println(e.getMessage());
      } finally {
        daoFac.fecharConexao();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return receita;
  }

  @Post
  @Path("/receitas/editar")
  public void editar(Receita receita) {
    try {
      var daoFac = new DAOFactory();
      daoFac.abrirConexao();
      try {
        var receitaDAO = daoFac.criarReceitaDAO();
        receitaDAO.editar(receita);

        result.redirectTo(HomeController.class).index(null);
      } catch (Exception e) {
        result.redirectTo(ReceitasController.class).editarForm(receita, e.getMessage());
      } finally {
        daoFac.fecharConexao();
      }
    } catch (Exception e) {
      result.redirectTo(ReceitasController.class).editarForm(receita, e.getMessage());
    }
  }

  @Path("/receitas/{receita.id}/deletar")
  public void deletar(Receita receita) {
    try {
      var daoFac = new DAOFactory();
      daoFac.abrirConexao();
      try {
        var receitaDAO = daoFac.criarReceitaDAO();
        receitaDAO.remover(receita);

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