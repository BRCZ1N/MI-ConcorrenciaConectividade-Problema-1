<h2 align="center">Consumo de energia inteligente </h2>
 
# Índice

- [Desenvolvedor](#desenvolvedor)
- [Solução](#Solução)
- [Componentes](#Componentes)
   - [Medidor](#Medidor)
   - [Servidor](#Servidor)
   - [Cliente](#Cliente)
   - [API Rest](#APIRest)
- [Considerações finais](#consideracoes)

# Desenvolvedor
<br /><a href="https://github.com/BRCZ1N">Bruno Campos</a>

# Introdução

Levando-se em conta que as concessionárias atuam na coordenação do fornecimento de energia elétrica e que as mesmas dependem de ação humana para realizar as medições de consumo e também para impressões das faturas dos consumidores, fora proposto aos alunos do curso de Engenharia de Computação da Universidade Estadual de Feira de Santana(UEFS) que desenvolvessem um sistema inteligente com infraestrutura de IoT(Internet das Coisas) para oferecer um serviço inteligente de energia.

O relatório presente pretende descrever de forma simples um projeto que baseia-se em um sistema de comunicação que utiliza arquitetura em rede desenvolvido na linguagem de programação Java.

O presente projeto foi desenvolvido a partir de sockets para dois tipos de aplicações, o primeiro tipo foi desenvolvido utilizando "DatagramSockets", isto é, sockets que utilizam do protocolo UDP para conectar a aplicação dos medidores com o servidor, já o segundo utilizou de "Socket" e "ServerSocket" que provém o protocolo TCP para criação de aplicações com a comunicação através da padrozinação em APIs Rest para a trocar de dados entre o servidor e o usuário final. Os medidores foram criados para enviar os valores de medição para o servidor, além de prover uma interface simples para alteração dos ritmos de consumo. Com relação ao servidor o mesmo é responsável por armazenar os valores de medição, além do processamento de dados, e finalmente para ser disponível em rede para o usuário consumidor realizar serviços que outrora deveriam ser feitos por funcionários das concessionárias, os serviços dessa aplicação consistem em: acompanhar o seu consumo de energia, disponibilizar um alerta de consumo para o cliente, geração de faturas, retornar uma fatura gerada do cliente e também para retornar todas as suas faturas que já foram geradas do mesmo. Com relação a parte de teste de execução desse protótipo, utilizou-se de containers docker para virtualizar e executar a aplicação em um ambiente de rede.

# Fundamentação teórica

<li>O servidor pode ser definido como uma entidade que provê serviços para serem utilizados por clientes, seja ele um usuário ou um sistema. 
<li>O cliente pode ser determinado como uma entidade que utiliza dados providos de servidores.
<li>Relacionamento cliente-servidor, consiste basicamente em uma entidade que requisita serviços, isto é, um cliente e uma entidade que processa e envia respostas ao cliente, isto é, provendo-o, conhecido como servidor.
<li>API REST (Representational State Transfer) é um estilo de arquitetura de software para sistemas distribuídos que utiliza o protocolo HTTP para a comunicação entre diferentes sistemas. É um conjunto de padrões e princípios que definem como as requisições e respostas devem ser feitas, permitindo a integração de diferentes aplicações e sistemas. Em uma arquitetura RESTful, cada recurso é representado por uma URL, e as operações que podem ser realizadas nesses recursos são definidas pelos verbos HTTP (GET, POST, PUT, DELETE, entre outros).
<li>Containers são uma forma de virtualização de aplicativos que permite a criação de ambientes isolados e independentes, nos quais é possível executar aplicativos e serviços sem afetar o sistema operacional ou outras aplicações que estejam sendo executadas no mesmo servidor ou máquina.
<li>Docker é uma plataforma de código aberto que permite a criação, distribuição e execução de aplicativos em containers.
<li>UDP (User Datagram Protocol) é um protocolo de comunicação de rede que oferece a transmissão rápida de dados entre dispositivos em uma rede sem estabelecer uma conexão prévia, o mesmo não garante uma confiabilidade visto que não possui mecanismos de controle de fluxo, retransmissão de pacotes perdidos ou controle de congestionamento
<li>TCP (Transmission Control Protocol) é um protocolo de comunicação de rede que oferece uma transmissão confiável de dados entre dois dispositivos em uma rede, estabelecendo uma conexão antes da transferência de dados, além de verificar a entrega dos dados. 

# Metodologia geral

Primeiramente criou-se uma aplicação server que utilizou-se do server socket para gerar a comunicação de rede entre o servidor e os clientes TCP, neste caso os clientes finais, após isso criou-se um datagram socket para gerar a comunicação entre os clientes UDP, neste caso os medidores. Com isso foram implementados APIs Rest para tratar as requisições recebidas pelos clientes finais, utilizando como padrão de corpo de resposta de requisição o formato JSON(Javascript Object Notation). Além disso foram criadas entradas de threads para as possíveis conexões de medidor e cliente, isto é, threads UDP e threads TCP respectivammente.
 
Após a criação da aplicação servidor criou-se a aplicação medidor que conta com um datagram socket para gerar comunicação entre o medidor e o próprio servidor, nessa aplicação utilizou-se de threads para enviar o consumo a cada intervalo e para a simulação da medição em tempo real, além disso na thread principal ficou a entrada de dados responsável por alterar o ritmo de consumo, a mesma possui uma interface simples que pede o identificador do cliente final e a senha do mesmo, além de exigir a autenticação para acessar as threads que auxiliam na geração de consumo mencionadas anteriormente.
 
Com a criação da aplicação servidor e medidor, surge a necessidade da aplicação cliente que fará uso dos dados da aplicação servidor, isto é, o cliente pode enviar solicitações ao servidor que serão processadas e serão retornadas ao cliente, então temos também uma nova necessidade de comunicação, neste caso é uma comunicação que exige confiabilidade, isto é, protocolo TCP, utilizando desse protocolo e de requisições o cliente envia pedidos para o servidor e aguarda as respostas com dados que ele pode utilizar em formato de resposta.
 
Para auxiliar as necessidades do projeto foram criadas algumas componentes de serviços, isto é, entidades que armazenam dados temporários e/ou processam dados que serão transmitidos na rede.
 
# Solução para os requisitos principais

<h2>Requisitos do projeto</h2>

   1. Interface para gerenciamento dos medidores;
   2. Acompanhamento do consumo de energia;
   3. Geração de fatura;
   4. Alerta sobre consumo excessivo;
   
<h2>1. Interface para gerenciamento dos medidores.</h2>
   
 &emsp;Para esse requisito foi desenvolvido uma interface simples que possui uma entrada para o identificador do usuário e a sua senha que serão enviados para a autenticação de usuário pelo servidor, produziu-se threads para a interface medidor onde uma delas incrementa o contador do medidor e a secundária envia dados ao servidor após um intervalo especificado, e no método ao qual essas threads são iniciadas tem-se a entrada de dados para alterar o ritmo de consumo do medidor.
  
<h2>2. Acompanhar consumo de energia.</h2>

&emsp;Objetivando o acompanhamento do consumo de energia, essa funcionalidade foi desenvolvida para buscar os consumos do usuário armazenados na estrutura de dados dos serviços de consumo no servidor usando o identificador do usuário como chave e pegando os consumos associados a ele, unido-se a esses consumos, pega-se o consumo total do usuário e também o identificador do usuário, finalmente a partir desses atributos é possível gerar um JSON que represente os consumos do cliente que poderá ser repassado na resposta da requisição.

<h2>3. Gerar fatura.</h2>

&emsp;Para efetuar a geração de faturas prevalece a necessidade de pegar consumos que ainda não foram contabilizados em faturas anteriores, sendo assim no momento de geração da fatura pega-se a data de geração da última fatura e utiliza a mesma para contabilizar os consumos posteriores a ela, além desse aspecto ainda há a necessidade do nome de usuário, identificador do usuário, tarifa e o consumo atual do cliente, por fim a partir desses dados é possível gerar um JSON que poderá ser repassado na resposta da requisição da fatura para representar a entidade de fatura.


<h2>4. Alerta sobre consumo excessivo.</h2>

&emsp;Para essa funcionalidade é necessário entender que para cada consumo gerado nos serviços de consumo é feito uma média dos consumos anteriores e incrementado a uma certo valor, se o valor do consumo que será adicionado atualmente for superior a essa média incrementada então o usuário é classificado como tendo um alto consumo, do contrário consumo normal, finalmente então atualizando o estado de consumo do usuário. Utilizando-se de toda essa lógica para atualização, tem-se que essa funcionalidade é disponível por uma instância de usuário da estrutura de dados relacionado a classe serviço de usuário, isto é, na resposta da requisição é repassado o estado de consumo relacionado a instância de um usuário e o seu identificador.
 
# Componentes do projeto

<h2>- Servidor</h2>
<p2> O servidor processa as requisições, realiza o tratamento das mesmas, e por fim retorna a resposta da requisição, além de exibi-las no terminal, ainda se considera a sua função de gerar threads para cada tipo de componente no servidor, isto é, seja medidor(UDP) ou usuário(TCP).</p2>

<h2>- Interface do usuário</h2>
<p2> O usuário se conecta ao servidor e aos serviços através da API Rest. O mesmo, por conseguinte, apresenta as seguintes funcionalidades:</p2>
 <ul>
  <li>1. Visualizar historico de consumo </li>
  <li>2. Gerar fatura </li>
  <li>3. Visualizar fatura </li>
  <li>4. Visualizar todas as faturas geradas</li>
  <li>5. Status de consumo do usuário</li>
  <li>6. Desconectar</li>
</ul>

<h2>- Interface do medidor </h2>
<p2> O medidor realiza o envio dos dados ao servidor de forma continua</p2>
 <ul>
  <li>1. Envia as medições e os dados necessários para armazenar a medição no servidor são eles: o identificador do usuário, a data e hora da medição e o consumo do usuário</li>
  <li>2. Altera o ritmo de consumo das medições</li>
</ul>

<h2>- API REST</h2>
<p2>  &emsp; A API Rest conecta o usuário TCP ao servidor TCP. Possui métodos get, para visualizar o histórico de consumo, gerar e mostrar a fatura do usuário, visualizar a fatura do usuário, visualizar todas as faturas do usuário e para visualizar o estado de consumo do usuário.<p2> 
 
 # Considerações finais 
<p2> &emsp; O projeto consegue realizar tudo dentro das obrigações mínimas. Na implementação surgiu desafios com relação à compreensão da API Rest, o mesmo também possibilitou aprofundamento em questões relacionadas no que diz respeito ao que é uma API Restful e a tipos de conexões(TCP e UDP). Em versões posteriores, poderiam ser adicionado um melhoramento entre as trocas de dados das conexões. Apesar disso, tem-se que o estado atual é apenas de um protótipo simples e que não deve refletir em um possível produto
