package routers;

import http.RequestHttp;

/**
 * Esta é a classe Interface, para as classes de roteamento do servidor
 */
public interface RouterInterface {

	String router(RequestHttp http);

}
