package nd.trading.utilities;

public class CustomClient extends ApiClient implements IApiClient {

	public CustomClient(String baseUrl, boolean addDefaultHeaders) {
		super(baseUrl, addDefaultHeaders);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String CallApi(String xChangeName, Methods method, String endpoint,
			boolean isSigned, String parameters, String body) {
		
		return null;
	}

}
