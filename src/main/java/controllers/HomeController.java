package controllers;

import java.util.Calendar;

import org.apache.shiro.SecurityUtils;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import modelo.ControleMensal;
import modelo.dao.DAOFactory;

@Controller
public class HomeController {

  @Path("/")
  public ControleMensal index(ControleMensal controleMensal) {
    var userEmail = SecurityUtils.getSubject().getPrincipal().toString();

    if (controleMensal == null || controleMensal.getAno() == 0) {
      var cal = Calendar.getInstance();
      controleMensal = new ControleMensal();
      controleMensal.setMes(cal.get(Calendar.MONTH) + 1);
      controleMensal.setAno(cal.get(Calendar.YEAR));
    }

    try {
      var daoFac = new DAOFactory();
      daoFac.abrirConexao();
      try {
        var controleMensalDAO = daoFac.criarControleMensalDAO();
        var usuarioDAO = daoFac.criarUsuarioDAO();
        var usuario = usuarioDAO.buscar(userEmail);

        controleMensal = controleMensalDAO.buscar(usuario.getId(), controleMensal.getMes(), controleMensal.getAno());
      } catch (Exception e) {
        System.out.println(e.getMessage());
      } finally {
        daoFac.fecharConexao();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return controleMensal;
  }
}