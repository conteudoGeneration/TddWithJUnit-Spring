# Spring TDD com JUnit (Model)

## Construa um spring em java 

## Não esqueça de colocar as dependencias nescessarias no pom XML

```
<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>org.junit.jupiter</groupId>
					<artifactId>junit-jupiter</artifactId>
				</exclusion>
				<exclusion>
					<groupId>org.junit.vintage</groupId>
					<artifactId>junit-vintage-engine</artifactId>
				</exclusion>
				<exclusion>
					<groupId>org.mockito</groupId>
					<artifactId>mockito-junit-jupiter</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>org.hsqldb</groupId>
			<artifactId>hsqldb</artifactId>
		</dependency>
	</dependencies> 
```

## Atualizando as bibliotecas do ApplicationTest

Atualize as bibliotecas do arquivo ApplicationTest

![alt text](https://i.imgur.com/r5zvS0E.png)

```
package com.generation.Junit2;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Junit2ApplicationTests {

	@Test
	void contextLoads() {
	}

}
````````

## Testando a Model @NotEmpty

- Antes de fazer o teste unitário vamos construir algumas camadas da aplicação

### Model/Entity 

 1. Crie um pacote e o nomeie como Model ou Entity, em seguida cole o código abaixo;
 
```
    package integracao.bancodedados.model;
    
    import javax.persistence.Entity;
    import javax.persistence.GeneratedValue;
    import javax.persistence.GenerationType;
    import javax.persistence.Id;
    import javax.validation.constraints.NotEmpty;
    
    @Entity
    public class ContatoModel {
    
    	//ATRIBUTOS
    	@Id
    	@GeneratedValue(strategy=GenerationType.IDENTITY)
    	private Long id;
    	
    	@NotEmpty(message="O DDD deve ser preenchido")
    	private String ddd;
    	
    	@NotEmpty(message="O Telefone deve ser preenchido")
    	private String telefone;
    	
    	@NotEmpty(message="O Nome deve ser preenchido")
    	private String nome;
    
    	//CONTRUCTORS
    	public ContatoModel(){
    	}
    	
    	public ContatoModel(String nome, String ddd, String telefone) {
    		this.nome = nome;
    		this.ddd = ddd;
    		this.telefone = telefone;
    	}
    	
    	//GETTERS AND SETTERS
    	public Long getId() {
    		return id;
    	}
    
    	public void setId(Long id) {
    		this.id = id;
    	}
    
    	public String getDdd() {
    		return ddd;
    	}
    
    	public void setDdd(String ddd) {
    		this.ddd = ddd;
    	}
    
    	public String getTelefone() {
    		return telefone;
    	}
    
    	public void setTelefone(String telefone) {
    		this.telefone = telefone;
    	}
    
    	public String getNome() {
    		return nome;
    	}
    
    	public void setNome(String nome) {
    		this.nome = nome;
    	}
    } 



````````


### Crie uma Interface de repository para usar os recursos do JPAHibernat

2. Crie um pacote e o nomeio como repository e insira o codigo abaixo;

```
package integracao.bancodedados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import integracao.bancodedados.model.ContatoModel;

public interface ContatoRepository extends JpaRepository<ContatoModel, Long> {

}

`````

Pronto! agora ja temos um projeto onde possamos implementar uma classe de teste.

### Implementando os testes unitários.

> Nesta seção iremos realizar testes unitários nas nossas anotações de
> validações inseridas na nossa model/entity.

3. Em src/test/java Crie um pacote e o nomeie como **contato**, em seguida insira o código abaixo;

````
package integracao.bancodedados.contatos;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import integracao.bancodedados.model.ContatoModel;
import integracao.bancodedados.repository.ContatoRepository;


//Run with rodará com o spring runner,
//que suporta testes de integração de interfaces (testa se a 
//repository está funcionando)

@RunWith(SpringRunner.class)
@DataJpaTest //melhor testador de JPA
public class ContatosRepositoryIntegrationTest {

	private ContatoModel contato;
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	@Before
	public void start() {
		contato = new ContatoModel("Gabriel", "011y", "9xxxxxxx9");
	}
	
	@Test (expected = RuntimeException.class)
	public void salvarComTelNulo() throws Exception {
		
		
		contato.setTelefone(null);
		contatoRepository.save(contato);
	}
	
	@Test (expected = RuntimeException.class)
	public void salvarComDddNulo() throws Exception {
		
		
		contato.setDdd(null);
		contatoRepository.save(contato);
	}
	
	@Test (expected = RuntimeException.class)
	public void salvarComNomeNulo() throws Exception {
		
		contato.setNome(null);
		contatoRepository.save(contato);
	}
	
}

````

![enter image description here](https://i.imgur.com/4GoLNsK.png)


O mesmo teste é feito por três vezes.

 - salvarComTelNulo()
 - salvarComDddNulo()
 - salvarComNomeNulo()

## Para testar o app

![alt text](https://i.imgur.com/tGWqm3i.png)

### Qualquer duvida não esqueça de pesquisar dentro projeto guia do gitHub

[Link do github:](https://github.com/conteudoGeneration/TddWithJUnit-Spring) 
