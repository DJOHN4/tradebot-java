package nd.trading.utilities;

import java.util.Date;

public class ApiClient  {

	 //public final HttpClient _httpClient;
     protected String baseUrl = "";
     public static final Date UnixEpoch = new Date();//(year: 1970, month: 1, day: 1, hour: 0, minute: 0, second: 0, kind: DateTimeKind.Utc);

     public ApiClient(String baseUrl, boolean addDefaultHeaders)
     {
         this.baseUrl = baseUrl;
         //_httpClient = new HttpClient
          // {
              // BaseAddress = new Uri(_baseUrl)
           //};

         if (addDefaultHeaders)
         {
             ConfigureHttpClient();
         }
     }
     protected void ConfigureHttpClient()
     {
        /* _httpClient.DefaultRequestHeaders
             .Accept.Clear();
         _httpClient.DefaultRequestHeaders
             .Accept
             .Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
         _httpClient.DefaultRequestHeaders.Add("User-Agent", "DJ's trade program");*/
     }
     
     protected String CreateHttpMethod(String method)
     {
        /*     switch (method.ToUpper())
             {
                 case "DELETE":
                     return HttpMethod.Delete;
                 case "POST":
                     return HttpMethod.Post;
                 case "PUT":
                     return HttpMethod.Put;
                 case "GET":
                     return HttpMethod.Get;
                 default:
                     throw new NotImplementedException();
             }*/
		 return "GET";
     }
     protected String GenerateTimeStamp(Date baseDateTime)
     {
             //DateTimeOffset dtOffset = new DateTimeOffset(baseDateTime);
            // return dtOffset.ToUnixTimeMilliseconds().ToString();
    	 return "";
     }
   
     public  double ToUnixTimestamp(Date dateTime)
     {
             return 0.0;// (dateTime - UnixEpoch).TotalSeconds;

     }
}
