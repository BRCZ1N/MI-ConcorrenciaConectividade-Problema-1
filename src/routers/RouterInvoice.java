package routers;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import utilityclasses.HttpMethods;
import utilityclasses.RequestHttp;

public class RouterInvoice implements RouterInterface {

	private Map<Pattern, Handler> routers = new HashMap<>();
	private String datePattern = "(\\d{2}-\\d{2}-\\d{4})";
	private String idPattern = "(\\d+)";

	public RouterInvoice() {

		routers.put(Pattern.compile("/invoice/" + idPattern), this::getInvoice);
		routers.put(Pattern.compile("/invoice/all"), this::getAllInvoices);
		routers.put(Pattern.compile("/invoice/" + idPattern + "/data?inicio=" + datePattern + "&fim=" + datePattern),this::createInvoice);

	}

	@Override
	public void router(RequestHttp http) {

		if (http.getMethod() == HttpMethods.GET) {
			
			if() {
				
				
			}else if(){
				
				
			}else {
				
				//ERRO PATH NÃO EXISTE
				
			}

		} else if (http.getMethod() == HttpMethods.POST) {

		} else if (http.getMethod() == HttpMethods.PUT) {

		} else if(http.getMethod() == HttpMethods.DELETE) {

		}else {
			
			//Não implementado
			
		}

	}

	public void getInvoice(RequestHttp http) {

	}

	public void getAllInvoices(RequestHttp http) {

	}

	public void createInvoice(RequestHttp http) {

	}

	public interface Handler {

		public void handler(RequestHttp http);

	}

}
