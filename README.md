# Teste Desenvolvedor Java Junior - TGID 

## Erros Encontrados 

## Descreva os erros que você encontrou.  

### Regras de negócio: 

> 1. O saldo exibido das empresas não contabiliza as comissões. 

> 2. As empresas podem vender o produto de outras empresas. 

> 3. O estoque não é alterado após a venda. 

### Código: 

> 1. Atributos e Variáveis com Acentuação: Exemplo -> "código" 

> 2. Excesso de estruturas condicionais. 

> 3. Classes com excessos de responsabilidade. 

> 4. Métodos similares sem reuso. 

### Boas Práticas 

> 1. Classes Main com exceção de funcionalidade. 

> 2. Falta de divisão das classes em pacotes. 

> 3. Muitos métodos stream aglomerados. 

> 4. O método criarVendas dentro da classe Main, porém é necessária uma classe única para o mesmo. 

### Experiência do Usuário 

> 1. Cliente não consegue ter acesso o valor dos produtos. 

> 2. Itens exibidos fora de ordem. 

> 3. Mensagem de Senha ou Usuário incorreto, pouco segura. 

<br> 

## Descreva como se estivesse repassando os ajustes para um programador. 

### Regras de Negócio: 

> 1. Necessário revisar o cálculo do saldo par incluir a comissão. 

> 2. Falta criar uma estrutura para delimitar os produtos vendidos por determinada empresa. 

> 3. Implementar Lógica para atualizar o estoque após cada venda efetuada. 

### Código 

> 1. Revisar o código para renomear variáveis e atributos com nomes incorretos. 

> 2. Refatore classes para seguir o princípio da responsabilidade única, dividindo as tarefas em classes menores e mais específicas. 

> 3. Reduza a complexidade do código, dividindo-o em funções menores e aplicando boas práticas. 

> 4. Identifique métodos que realizam tarefas semelhantes e crie funções reutilizáveis para eliminar duplicações de código. 

### Boas Práticas 

> 1. Mova a lógica principal do programa para classes dedicadas em vez de sobrecarregar a classe Main. Isso torna o código mais organizado e manutenível. 

> 2. Organize as classes em pacotes lógicos de acordo com sua funcionalidade para manter uma estrutura hierárquica e facilitar a navegação. 

> 3. Separe a criação de vendas em uma classe específica para essa funcionalidade, em vez de mantê-la na classe Main. 

### Experiência do Usuário: 

> 1. Revise a interface do usuário e certifique-se de que os elementos sejam exibidos de forma lógica e organizada, seguindo o fluxo de uso. 

> 2. Torne os valores dos produtos visíveis e de fácil acesso para os clientes, seja por meio de uma interface de compra ou um carrinho de compras, para uma experiência mais amigável. 

## Em caso de erros na regra de negócio, faça um relato para a empresa que solicitou o sistema 

> 1. Não identifiquei erro na Regra de negócios, apenas na forma a qual foi implementada. 