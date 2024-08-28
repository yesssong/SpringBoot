package util;

import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo_visit.vo.ProductVo;

public class MySearchUtil {

	public static List<ProductVo> search_shop(String p_name,int start,int display)
	{
		List<ProductVo> list = new ArrayList<ProductVo>();
		
		String clientId = "B6P5_6lUk9ZoZlmT93CT";
		String clientSecret = "8jHC8rFrhH";

		try {

			//방법1일경우 인코딩
			//p_name = URLEncoder.encode(p_name, "utf-8");
			
			String urlStr = String.format("https://openapi.naver.com/v1/search/shop.xml?query=%s&start=%d&display=%d",
					         p_name,start,display
					);

			
			//방법1)
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			//발급받은 ID
			connection.setRequestProperty("X-Naver-Client-Id", clientId); 
			//발급받은 PW
			connection.setRequestProperty("X-Naver-Client-Secret", clientSecret); 
			// 받을요청타입
			connection.setRequestProperty("Content-Type", "application/xml"); 
			connection.connect();

//---------------------------------------------------------------------------------------------------------------------------------
			//방법2) RestTemplate(5.0이전) -> 이후 : WebClient사용하도록 권고
            RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add("X-Naver-Client-Id",clientId);
			headers.add("X-Naver-Client-Secret", clientSecret); 
			headers.add("Content-Type","application/xml");
			HttpEntity<?> entity = new HttpEntity<>(headers);
			ResponseEntity<String> resultMap  = restTemplate.exchange(urlStr, HttpMethod.GET,entity, String.class);
			// System.out.println("----------------------------------------");
			// System.out.println(resultMap.getBody());
			// System.out.println("----------------------------------------");
//---------------------------------------------------------------------------------------------------------------------------------



			SAXBuilder builder = new SAXBuilder();
			//방법1
			//Document   doc = builder.build (connection.getInputStream());

			//방법2
			Document   doc = builder.build ( new StringReader(resultMap.getBody()))	;	


			Element  root                = doc.getRootElement();  // <rss>
			List<Element>   item_list = root.getChild("channel").getChildren("item");

			for(Element item : item_list){
				String title = item.getChildText("title");
				String link  = item.getChildText("link");
				String image = item.getChildText("image");
				int lprice=0,hprice=0;
				try {
					lprice = Integer.parseInt(item.getChildText("lprice"));
				} catch (Exception e) {
					
				}
				
				try {
					hprice = Integer.parseInt(item.getChildText("hprice"));
				} catch (Exception e) {
					
				}
				
				String mallName = item.getChildText("mallName");
				
				//상품목록을 포장
				ProductVo vo = new ProductVo();
				vo.setTitle(title);
				vo.setLink(link);
				vo.setImage(image);
				vo.setLprice(lprice);
				vo.setHprice(hprice);
				vo.setMallName(mallName);
								
				//ArrayList에 넣기
				list.add(vo);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return list;
		
	}//end:search_shop()
	
	
	
	
}
