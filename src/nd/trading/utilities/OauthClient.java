package nd.trading.utilities;

public class OauthClient extends ApiClient implements IApiClient {

	public OauthClient(String baseUrl, boolean addDefaultHeaders) {
		super(baseUrl, addDefaultHeaders);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String CallApi(String xChangeName, Methods method, String endpoint,
			boolean isSigned, String parameters, String body) {
		
		return null;
	}

}
