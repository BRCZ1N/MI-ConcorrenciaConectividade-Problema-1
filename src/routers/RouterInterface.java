package routers;

import http.RequestHttp;

/**
 * Esta � a classe Interface, para as classes de roteamento do servidor
 */
public interface RouterInterface {

	String router(RequestHttp http);

}
