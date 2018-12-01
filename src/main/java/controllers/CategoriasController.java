package controllers;

import java.util.ArrayList;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import modelo.Categoria;
import modelo.dao.DAOFactory;

@Controller
public class CategoriasController {
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

  @Path("/categorias/")
  public void index(String errorMessage) {
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

  public void adicionar(Categoria categoria) {
    try {
      var daoFac = new DAOFactory();
      daoFac.abrirConexao();
      try {
        var categoriasDAO = daoFac.criarCategoriaDAO();

        categoriasDAO.gravar(categoria);
        result.redirectTo(CategoriasController.class).index(null);
      } catch (Exception e) {
        result.redirectTo(CategoriasController.class).index(e.getMessage());
      } finally {
        daoFac.fecharConexao();
      }
    } catch (Exception e) {
      result.redirectTo(CategoriasController.class).index(e.getMessage());
    }
  }

  public void editar(Categoria categoria) {
    try {
      var daoFac = new DAOFactory();
      daoFac.abrirConexao();
      try {
        var categoriasDAO = daoFac.criarCategoriaDAO();

        categoriasDAO.editar(categoria);
        result.redirectTo(CategoriasController.class).index(null);
      } catch (Exception e) {
        result.redirectTo(CategoriasController.class).index(e.getMessage());
      } finally {
        daoFac.fecharConexao();
      }
    } catch (Exception e) {
      result.redirectTo(CategoriasController.class).index(e.getMessage());
    }
  }

  public void deletar(Categoria categoria) {
    try {
      var daoFac = new DAOFactory();
      daoFac.abrirConexao();
      try {
        var categoriasDAO = daoFac.criarCategoriaDAO();

        categoriasDAO.remover(categoria);
        result.redirectTo(CategoriasController.class).index(null);
      } catch (Exception e) {
        result.redirectTo(CategoriasController.class).index(e.getMessage());
      } finally {
        daoFac.fecharConexao();
      }
    } catch (Exception e) {
      result.redirectTo(CategoriasController.class).index(e.getMessage());
    }
  }
}