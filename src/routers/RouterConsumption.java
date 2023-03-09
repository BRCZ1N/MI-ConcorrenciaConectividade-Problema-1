package routers;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import routers.RouterInvoice.Handler;
import utilityclasses.HttpMethods;
import utilityclasses.RequestHttp;

public class RouterConsumption implements RouterInterface {

	private Map<Pattern, Handler> routers = new HashMap<>();
	private String datePattern = "(\\d{2}-\\d{2}-\\d{4})";
	private String idPattern = "(\\d+)";

	public RouterConsumption() {

		routers.put(Pattern.compile("/consumption/status/" + idPattern), this::getCurrentStateConsumption);
		routers.put(Pattern.compile("/consumption/historic/" + idPattern), this::getHistoricConsumption);

	}

	@Override
	public void router(RequestHttp http) {

		if (http.getMethod() == HttpMethods.GET) {
			
			Matcher match = Pattern.matches(idPattern, datePattern)
			
			if() {
				
				
			}else if(){
				
				
			}else {
				
				
			}

		} else if (http.getMethod() == HttpMethods.POST) {

		} else if (http.getMethod() == HttpMethods.PUT) {

		} else if(http.getMethod() == HttpMethods.DELETE) {

		}else {
			
			//Não implementado
			
		}

	}

	public void getCurrentStateConsumption(RequestHttp http) {

	}

	public void getHistoricConsumption(RequestHttp http) {

	}

	public interface Handler {

		public void handler(RequestHttp http);

	}

}
