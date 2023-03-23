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

# Solução

<h2>Requisitos do projeto</h2>

   1. Interface para gerenciamento dos medidores;
   2. Acompanhamento do consumo de energia;
   3. Geração de fatura;
   4. Alerta sobre consumo excessivo;
   
   <h2>   1. Interface para gerenciamento dos medidores.</h2>
   
 &emsp; Para esse requisito foi desenvolvido uma interface simples que possui uma entrada para o identificador do cliente e a sua senha que serão enviadas através de um socket UDP para a autenticação de usuário, após essa fase de autenticação, produziu-se 2 threads para os medidores onde uma delas incrementa o contador do medidor e a secundária envia dados ao servidor após um cooldown especificado, e no metodo ao qual essas threads são iniciadas tem-se a entrada de teclado para alterar o ritmo de consumo do medidor.
  
<h2>2. Acompanhar consumo de energia.</h2>

 &emsp; Objetivando o acompanhamento do consumo de energia essa funcionalidade foi desenvolvida buscando os consumos do cliente armazenados na estrutura de dados dos serviços de consumo que estão no servidor usando o identificador do usuario como chave e pegando os consumos gerados pelos medidor associado a ele, além disso pega-se o calculo do consumo total do cliente e também o identificaor, e por fim adiciona-os em um JSONObject
<h2>3. Gerar fatura.</h2>

<h2>3.Alerta sobre consumo excessivo.</h2>

&emsp;A cada consumo gerado é feito uma média dos consumos anteriores e incrementado a uma certo valor se o valor do consumo gerado atualmente for superior a essa média incrementada então o cliente é classificado com o alto consumo, do contrário o consumo é classificado como normal, e por fim após o usuário requisitar essa solicitação a API Rest é enviado o identificador do cliente e a classificação de consumo

 
# Componentes do projeto

<h2>- Servidor</h2>
<p2> O servidor processa as requisições http advindas da interface do cliente ou do insomnia, realiza o tratamento das mesmas, e por fim retorna a resposta da requisição em formato http além de exibi-las no terminal,  ainda leva-se em conta a sua função de gerar threads para cada tipo de componente no servidor, isto é, seja medidor(UDP) ou cliente(TCP).</p2>

<h2>- Interface do cliente</h2>
<p2> O cliente se conecta ao servidor através da API Rest. O mesmo, por conseguinte, apresenta as seguintes funcionalidades:</p2>
 <ul>
  <li>1. Visualizar historico de consumo </li>
  <li>2. Gerar fatura </li>
  <li>3. Visualizar fatura </li>
  <li>4. Visualizar todas as faturas geradas</li>
  <li>5. Status de consumo do cliente</li>
  <li>6. Desconectar</li>
</ul>

<h2>- Inteface do medidor </h2>
<p2> O medidor realiza o envio dos dados ao servidor de forma continua, os dados enviados são:</p2>
 <ul>
  <li>1. Envia as medições e os dados necessários para armazenar a medição no servidor são eles: o identificador do cliente, a data e hora da medição e o consumo do cliente</li>
  <li>2. Altera o ritmo de consumo das medições</li>
</ul>

<h2>- API REST</h2>
<p2>  &emsp; A API Rest conecta o cliente(usuario) TCP ao servidor TCP, além disso é possível usar o insomnia para tais operações. Possui métodos get, para visualizar o historico de consumo, gerar e mostrar a fatura do cliente, visualizar a fatura do cliente, visualizar todas as faturas do cliente e para visualizar o status de consumo do cliente.<p2> 
 
 # Considerações finais 
<p2> &emsp; O projeto consegue realizar tudo dentro das obrigações mínimas. Na implementação surgiu desafios com relação a compreensão da API Rest, também possibilitou aprofundamento em questões relacionadas no que diz respeito ao que é uma API Restful e a tipos de conexões(TCP e UDP). Em versões posteriores poderiam ser adicionados, um mecanismo para encerrar a conexão com medidor, visto que o medidor se comunica via socket UDP com o servidor, e um possível melhorament na tr entre componentes. Apesar disso, tem-se que o estado atual é apenas um prótotipo simples e que não tem seu desempenho prejudicado pela falta dos mesmos. 
