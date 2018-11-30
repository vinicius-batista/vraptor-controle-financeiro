package helpers;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.util.SimpleByteSource;

public class SenhaHelper {
  private static int HASH_ITERATIONS = 750000;
  // Same salt as in shiro.ini, but NOT base64-encoded.
  private static String PRIVATE_SALT = "hahahaha";

  /**
   * 
   * @param senha
   * @return senha criptografada 
   */
  static public String gerarHash(String senha) {
    var hashService = new DefaultHashService();

    hashService.setHashIterations(HASH_ITERATIONS);
    hashService.setHashAlgorithmName(Sha512Hash.ALGORITHM_NAME);
    hashService.setPrivateSalt(new SimpleByteSource(PRIVATE_SALT));
    hashService.setGeneratePublicSalt(true);

    DefaultPasswordService passwordService = new DefaultPasswordService();
    passwordService.setHashService(hashService);

    String encryptedSenha = passwordService.encryptPassword(senha);

    return encryptedSenha;
  }
}