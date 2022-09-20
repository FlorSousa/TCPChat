# TCPChat

Dentro da pasta *bin* estão os executaveis .class

Para executar o servidor:
  ```
    java Server
  ```
Para executar o cliente:
  ```
    java Client
  ```
  
 A classe Server cria duas Threads e cada cliente conectado é colocado em uma Thread, a classe ServerConnection é a classe com o código das Threads e 
 também é a abstração da conexão cliente-servidor.
 
 A classe HandleConnection administra as conexões do servidor e as armazena dentro de um arraylist do tipo ServerConnection, o metodo sendToAll varre o
 arraylist e se a conexão estiver aberta chama o metodo de ServerConnection sendResponse, caso a conexão que esteja mandando seja a da vez é mandado
 uma resposta "self-connection" e o cliente não exibe duas vezes a mensagem.
