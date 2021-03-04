# Project demo 

Utilizado para verificar como usar o commandBus em um projeto com ddd+cqrs+hexagonal.
Neste caso utilizamos o projeto [java-cqrs-commandbus](https://github.com/dathoangse/java-cqrs-commandbus) with springboot.

## Integre ao seu projeto

### Projetos maven

Adicione as dependências:

```
<dependency>
  <groupId>net.dathoang.cqrs.commandbus</groupId>
  <artifactId>commandbus-spec</artifactId>
  <version>0.3.0</version>
</dependency>

<dependency>
    <groupId>net.dathoang.cqrs.commandbus</groupId>
    <artifactId>commandbus-core</artifactId>
    <version>0.3.0</version>
</dependency>

<dependency>
  <groupId>net.dathoang.cqrs.commandbus</groupId>
  <artifactId>commandbus-spring</artifactId>
  <version>0.3.0</version>
</dependency>
```

## Auto-scan CommandBus

Utilizamos nesse projeto o auto-scan para configurar nosso CommandBus com Commands e CommandHandlers.

### Criando commands

Basicamente é necessário implementar a interface Command<R> para criar um command.

Veja o exemplo: [CriarTribunalCommand.java](src/main/java/com/example/demo/core/ports/commands/CriarTribunalCommand.java)

*Nossos commands não retornarão nenhum valor, apesar de a biblioteca permitir que se retorne.

## Criando CommandHandlers

A biblioteca sugere que se utilize uma ou algumas packages para agrupar os commandHandlers.
Para criar um CommandHandler, deve-se implementar a interface CommandHandler<C extends Command<R>, R>.

Além disso, deve-se utilizar a seguinte Annotation para identificar para a biblioteca qual é o Command que esse CommandHandler trata:

```
@CommandMapping(value = Class<? extends Command>)
ou no exemplo:
@CommandMapping(value = CriarTribunalCommand.class)
```

Exemplo: [CriarTribunalCommandHandler.java](src/main/java/com/example/demo/core/services/commandHandlers/CriarTribunalCommandHandler.java)

## Configurando o CommandBus

Antes de continuar, deve-se criar uma configuração que será lida pelo auto-scan para identificar quais são os CommandHandlers da aplicação.
 
É necessário haver um Bean com a Annotation que indique quais são os pacotes nos quais se encontram os CommandHandlers, por exemplo:

```
@HandlerScan(basePackages = {"com.example.demo.core.services.commandHandlers"})
```

Classe de exemplo utilizada para definir essa configuração:
Exemplo: [CommandBusConfiguration.java](src/main/java/com/example/demo/CommandBusConfiguration.java)

## Inicialização do CommandBus no springboot

A biblioteca já possui uma classe de configuração do CommandBus (e do QueryBus), sendo assim devemos apenas utilizá-la.

Utilizamos aqui da seguinte forma:

```
	@Autowired
    private ApplicationContext applicationContext;

	CommandBusSpringConfiguration commandBusConfiguration = 
			new CommandBusSpringConfiguration(applicationContext);

```

Para verificar se funcionou, pode-se iniciliazar a aplicação com um código que chegue até essa inicialização, a aplicaçAo gerará um log indicando quais os commandHandlers conseguiu identificar.

Feita essa inicialização deve-se injetar nas classes controller que farão uso do CommandBus a partir desse objeto inicializado:

```
Usar: commandBusConfiguration.getCommandBus();
```

## Utilização do CommandBus

Uma vez inicializado o CommandBus, basta criar um Command e executar:

```
commandBusConfiguration.getCommandBus().handle(command);
```

Exemplo: [TesteController.java](src/main/java/com/example/demo/infrastructure/adapters/cli/TesteController.java)
