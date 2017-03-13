# prime
Projeto prime, é um projeto arquitetado do zero para representa do zero projetos futuros

-----------------------------------------------------------------------------
Autenticação via WS:
-----------------------------------------------------------------------------
Request:
Metodo: POST
URL: http://localhost:8080/webservice/auth

HEADER:
Content-Type: application/json

Json: {
		"usuario":"reinaldo"
		,"senha":"123"
	  }


Response: Token criptografado

---------------------------------------------------------------------------
Acessando após autenticado:
-----------------------------------------------------------------------------
Request:
Metodo: GET
URL: http://localhost:8080/webservice/produto/buscar

HEADER:
Content-Type: application/json
Authorization: [Token adquirido na requisição anterior]



