package routers;

import http.RequestHttp;

/**
 * Esta é a classe Interface, para as classes de roteamento do servidor
 * 
 * @author Bruno Campos de Oliveira Rocha
 * @version 1.0
 */
public interface RouterInterface {

	String router(RequestHttp http);

}
