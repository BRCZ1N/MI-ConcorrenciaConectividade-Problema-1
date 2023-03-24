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
   
<h2>1. Interface para gerenciamento dos medidores.</h2>
   
 &emsp; Para esse requisito foi desenvolvido uma interface simples que possui uma entrada para o identificador do usuário e a sua senha que serão enviadas através de uma conexão UDP para a autenticação de usuário, produziu-se threads para a interface medidor onde uma delas incrementa o contador do medidor e a secundária envia dados ao servidor após um intervalo especificado, e no metodo ao qual essas threads são iniciadas tem-se a entrada de teclado para alterar o ritmo de consumo do medidor.
  
<h2>2. Acompanhar consumo de energia.</h2>

&emsp;Objetivando o acompanhamento do consumo de energia essa funcionalidade foi desenvolvida para buscar os consumos do usuário armazenados na estrutura de dados dos serviços de consumo que estão no servidor usando o identificador do usuário como chave e pegando os consumos gerados associado a ele, unido-se a esses consumos, pega-se o consumo total do usuário e também o identificador do usuário, finalmente a partir desses atributos é possível gerar um JSON que represente os consumos do cliente que poderá ser repassado na resposta da requisição para gerar os consumos do cliente.

<h2>3. Gerar fatura.</h2>

&emsp;Para efetuar a geração de faturas prevalece a necessidade de pegar consumos que ainda não foram contabilizados em faturas anteriores, sendo assim no momento de geração da fatura pega-se a data de geração da ultima fatura e utiliza a mesma para contabilizar os consumos posteriores a ela, além desse aspecto ainda há a necessidade do nome de usuário, identificador do usuário, tarifa e o consumo atual do cliente, por fim a partir desses dados é possível gerar um JSON que poderá ser repassado na resposta da requisição da fatura para representar a entidade de fatura.


<h2>4. Alerta sobre consumo excessivo.</h2>

&emsp;Para essa funcionalidade é necessário entender que para cada consumo gerado nos serviços de consumo é feito uma média dos consumos anteriores e incrementado a uma certo valor, se o valor do consumo que será adicionado atualmente for superior a essa média incrementada então o usuário é classificado como tendo um alto consumo, do contrário consumo normal , finalmente então atualizando o status de consumo do usuário. Utilizando-se de toda essa lógica para atualização, tem-se que essa funcionalidade é disponível por uma instancia de usuário da estrutura de dados relacionado a classe serviço de usuário, isto é, na resposta da requisição é repassado o status de consumo relacionado a instancia de um usuário e o seu identificador
 
# Componentes do projeto

<h2>- Servidor</h2>
<p2> O servidor processa as requisições, realiza o tratamento das mesmas, e por fim retorna a resposta da requisição, além de exibi-las no terminal,  ainda leva-se em conta a sua função de gerar threads para cada tipo de componente no servidor, isto é, seja medidor(UDP) ou usuário(TCP).</p2>

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

<h2>- Inteface do medidor </h2>
<p2> O medidor realiza o envio dos dados ao servidor de forma continua</p2>
 <ul>
  <li>1. Envia as medições e os dados necessários para armazenar a medição no servidor são eles: o identificador do usuário, a data e hora da medição e o consumo do usuário</li>
  <li>2. Altera o ritmo de consumo das medições</li>
</ul>

<h2>- API REST</h2>
<p2>  &emsp; A API Rest conecta o usuário TCP ao servidor TCP. Possui métodos get, para visualizar o historico de consumo, gerar e mostrar a fatura do usuário, visualizar a fatura do usuário, visualizar todas as faturas do usuário e para visualizar o status de consumo do usuário.<p2> 
 
 # Considerações finais 
<p2> &emsp; O projeto consegue realizar tudo dentro das obrigações mínimas. Na implementação surgiu desafios com relação a compreensão da API Rest, o mesmo também possibilitou aprofundamento em questões relacionadas no que diz respeito ao que é uma API Restful e a tipos de conexões(TCP e UDP). Em versões posteriores, poderiam ser adicionado um melhoramento entre as trocas de dados das conexões. Apesar disso, tem-se que o estado atual é apenas um prótotipo simples e é conivente com relação as especificações básicas do prótotipo
