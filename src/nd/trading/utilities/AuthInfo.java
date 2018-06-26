package nd.trading.utilities;

public class AuthInfo {
	public String key;
	public String secret;
	public String passPhrase;
	public String token;
	public String baseUrl;

	public AuthInfo(String baseUrl, String key, String secret,
			String passPhrase, String token) {
		this.key = key;
		this.secret = secret;
		this.passPhrase = passPhrase;
		this.token = token;
		this.baseUrl = baseUrl;
	}
}
