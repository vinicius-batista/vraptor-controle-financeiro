package controllers;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import helpers.SenhaHelper;
import modelo.Usuario;
import modelo.dao.DAOFactory;

@Controller
public class AuthController {

	@Inject
	private Result result;

	public void login(String errorMessage) {
		setErrorMessage(errorMessage);
	}

	public void cadastro(String errorMessage) {
		setErrorMessage(errorMessage);
	}

	private void setErrorMessage(String errorMessage) {
		if (errorMessage != null) {
			result.include("errorMessage", errorMessage);
			result.include("hasError", true);
		} else {
			result.include("hasError", false);
		}
	}

	public void logar(Usuario usuario) {
		try {
			var token = new UsernamePasswordToken(usuario.getEmail(), usuario.getSenha(), true);
			SecurityUtils.getSubject().login(token);

			result.redirectTo(HomeController.class).index(null);
		} catch (Exception e) {
			result.redirectTo(AuthController.class).login(e.getMessage());
		}
	}

	public void cadastrar(Usuario usuario) {
		var senha = usuario.getSenha();
		var encryptedSenha = SenhaHelper.gerarHash(usuario.getSenha());
		usuario.setSenha(encryptedSenha);
		usuario.setRole("user");
		try {
			var daoFac = new DAOFactory();
			daoFac.abrirConexao();
			try {
				var usuarioDAO = daoFac.criarUsuarioDAO();
				usuarioDAO.registrar(usuario);

				var token = new UsernamePasswordToken(usuario.getEmail(), senha, true);
				SecurityUtils.getSubject().login(token);

				result.redirectTo(HomeController.class).index(null);
			} catch (Exception e) {
				result.redirectTo(AuthController.class).cadastro(e.getMessage());
			} finally {
				daoFac.fecharConexao();
			}
		} catch (Exception e) {
			result.redirectTo(AuthController.class).cadastro(e.getMessage());
		}
	}
}
