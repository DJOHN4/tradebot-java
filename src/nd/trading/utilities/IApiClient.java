package nd.trading.utilities;

public interface IApiClient {
	String CallApi(String xChangeName, Methods method, String endpoint,
			boolean isSigned, String parameters, String body);

}
