package wirebarley.exchange.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import wirebarley.exchange.vo.ExchangeVO;



@Service("exchangeService")
public class ExchangeServiceImpl implements ExchangeService {
	public static final String BASE_URL = "http://apilayer.net/api/";
    public static final String ENDPOINT = "live";
    
    @Value("${access.key}")
    private String accessKey = null;    
    private List<ExchangeVO> list = null;
    private JSONObject exchangeRates = null;   
    static CloseableHttpClient httpClient = HttpClients.createDefault();
    private ExchangeVO exchangeObj = null;
        
	@Override
	public ExchangeVO getExchangeObj(String transferType, String receiptType) {
		String combinText = transferType.concat(receiptType);
		list = new ArrayList<ExchangeVO>();		
        HttpGet get = new HttpGet(BASE_URL + ENDPOINT + "?access_key=" + accessKey +
        		"&currencies=" + receiptType + "&source=" +transferType);
        try {
            CloseableHttpResponse response =  httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            // the following line converts the JSON Response to an equivalent Java Object
            exchangeRates = new JSONObject(EntityUtils.toString(entity));
            // Parsed JSON Objects are accessed according to the JSON resonse's hierarchy, output strings are built
            Date timeStampDate = new Date((long)(exchangeRates.getLong("timestamp")*1000)); 
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
            String formattedDate = dateFormat.format(timeStampDate);
            System.out.println("Date: " + formattedDate);               
            System.out.println("\n");
            exchangeObj =  new ExchangeVO(combinText, exchangeRates.getJSONObject("quotes").getDouble(combinText)); 
            response.close();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return exchangeObj;
	}
}
