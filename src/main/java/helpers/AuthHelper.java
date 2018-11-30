package helpers;

import org.apache.shiro.SecurityUtils;

import br.com.caelum.vraptor.Result;
import controllers.AuthController;

public class AuthHelper {
  static public void isAuthenticated (Result result) {
    if (! SecurityUtils.getSubject().isAuthenticated()) {
      result.forwardTo(AuthController.class).login(null);
    }
  }
}