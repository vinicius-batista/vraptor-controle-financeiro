package controllers;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import helpers.AuthHelper;
import modelo.Receita;
import modelo.dao.DAOFactory;

@Controller
public class ReceitasController {

  @Inject
  private Result result;

  @Get
  @Path("/receitas/{receita.id}/edit")
  public Receita editarForm(Receita receita, String errorMessage) {
    AuthHelper.isAuthenticated(result);
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

	private void setErrorMessage(String errorMessage) {
		if (errorMessage != null) {
			result.include("errorMessage", errorMessage);
			result.include("hasError", true);
		} else {
			result.include("hasError", false);
		}
	}

  @Post
  @Path("/receitas/editar")
  public void editar(Receita receita) {
    AuthHelper.isAuthenticated(result);

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
}