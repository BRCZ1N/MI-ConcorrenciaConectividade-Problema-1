<p align="center">
 align="center" alt="GitHub Readme Stats" />
 <h2 align="center">Consumo de Energia Inteligente </h2>
 
# Índice

- [Desenvolvedor](#desenvolvedor)
- [Tecnologias utilizadas](#Tecnologias)
- [Solução](#Solução)
- [Componentes](#Componentes)
   - [Medidor](#Medidor)
   - [Servidor](#Servidor)
   - [Client](#Client)
- [Considerações finais](#consideracoes)


# Desenvolvedor
<br /><a href="https://github.com/BRCZ1N">Bruno Campos</a>


# Tecnologias
<ul>
  <li>TCP</li>
  <li>UDP</li>
  <li>Java</li>
  <li>JSON</li>
</ul>



# Solução

<h2>Restrições do projeto</h2>

   1. Interface para gerenciamento dos medidores;
   2. Acompanhar consumo de energia;
   3. Gerar fatura;
   4. Alerta sobre consumo excessivo;
   
   <h2>   1. Interface para gerenciamento dos medidores.</h2>
   
 &emsp; 
 
  &emsp; 
  
<h2>2. Acompanhar consumo de energia.</h2>

 &emsp; .

<h2>3. Gerar fatura.</h2>

&emsp; 

 
# Componentes do projeto

<h2>- Tela do cliente</h2>
<p2> </p2>
 <ul>
  <li>1. Visualizar historico de consumo </li>
  <li>2. Gerar fatura </li>
  <li>3. Visualizar fatura </li>
  <li>4. Visualizar todas as faturas geradas</li>
  <li>5. Status de consumo do cliente</li>
  <li>6. Desconectar</li>
</ul>

<h2>- Tela do medidor</h2>
<p2> </p2>
 <ul>
  <li>1. Envia as medições</li>
  <li>2. Altera o ritmo de consumo das medições</li>
 
</ul>

<h2>- Tela do servidor</h2>
<p2> </p2>
 <ul>
  <li>1. Envia as medições</li>
  <li>2. Altera o ritmo de consumo das medições</li>
 
</ul>

<h2>- API REST</h2>
<p2>  &emsp; A api se conecta com o nó e com o servidor via conexões MQTT, cada método envia uma mensagem para o elemento responsável por possui aquela informação, logo em seguida se inscreve no tópico para receber a resposta. A cada requisição é aberta e fechada a conexão. Todos os métodos seguem esse padrão.<p2> 
 
 # Considerações finais 
<p2> &emsp; O projeto consegue realizar tudo dentro das obrigações mínimas. Na implementação surgiu desafios com relação a compreensão da API Rest, também possibilitou um estudo e reflexão mais aprofundada no que diz respeito ao que é a computação de borda. Algo que poderia ser adicionado em outras versões, é uma quantidade maior brokers, visto que é um servidor, o qual manipula todas as informações. Esses servidores poderiam ser separados por setores. No entanto, como o projeto ainda está em fase de projeto, não é prejudicado o seu desempenho. Também pode ser modificada a interface no terminal, e inserir uma interface web.
