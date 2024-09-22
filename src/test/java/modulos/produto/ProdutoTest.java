package modulos.produto;

import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
/*import pojo.ComponentePojo;
import pojo.ProdutoPojo;
import pojo.UsuarioPojo;

import javax.lang.model.element.ModuleElement;
import java.lang.invoke.StringConcatFactory;     Biblioteca que não estão sendo usadas.
import java.util.ArrayList;
import java.util.List;
import static io.restassured.matcher.RestAssuredMatchers.*;*/

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest do módulo de Produto")

/*-----------------------------CLASSE QUE JA REALIZAM AS CONFIGURAÇÕES INICIAIS DA API--------------------------------*/

public class ProdutoTest {
    private String token;

    // Esse próximo comando significa ANTES DE CADA TEST faça algo
    @BeforeEach
    public void beforeEach(){
        // Configurando os Dados da API Rest da lojinha
        baseURI= "http://165.227.93.41";
        //port =  8080; aqui colocamos a porta aonde esta rodando a API
        basePath = "/lojinha-bugada";

        //Obter o token do usuario admin
        this.token = given() // pense que aqui é a requisição
                .contentType(ContentType.JSON)

                /*.body("{\n" +
                        "  \"usuarioLogin\": \"admin\",\n" +
                        "  \"usuarioSenha\": \"admin\"\n" +
                        "}") // e termina aqui, estamos enviando login e senha

                 */ //Ao invés de usar o que esta nos comentarios usaremos assim como esta abaixo
                .body(UsuarioDataFactory.criarUsuarioAdministrador("admin","admin"))

            .when() // pense que aqui é a requisição do método que enviamos para o postaman
                .post("/v2/login")

                // Aqui pense que daqui para baixo é a resposta da requisição que fizemos
            .then()
                .extract()
                    .path("data.token");
    }

    /*------------------------------------INICIO DOS TESTES ----------------------------------------------------------*/

    @Test
    @DisplayName("Validar que o valor do produto igual a 0.00 não é permitido")
    public void testValidarLimitesZeradoProibidoValorProduto(){

        /* Configurando os Dados da API Rest da lojinha,MAS CRIAMOS UMA CLASSE SEPARADA PARA ISSO LOGO ACIMA BEFOREach ^
        baseURI= "http://165.227.93.41";
        //port =  8080; aqui colocamos a porta aonde esta rodando a API
        basePath = "/lojinha";

        //Obter o token do usuario admin
        String token = given() // pense que aqui é a requisição
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"usuarioLogin\": \"admin\",\n" +
                        "  \"usuarioSenha\": \"admin\"\n" +
                        "}") // e termina aqui, estamos enviando login e senha
                .when() // pense que aqui é a requisição do método que enviamos para o postaman
                .post("/v2/login")

                // Aqui pense que daqui para baixo é a resposta da requisição que fizemos
                .then()
                .extract()
                .path("data.token");
        */ //Configurando a API dentro do test, mas ja temos a classe no começo do código

        /*Tentar inserir um produto com o valor 0.00 e validar que a mensagem de erro foi apresentada e o
         status code retornado foi 422 pois inflíngil uma regra de negócios*/

        given()
                .contentType(ContentType.JSON)
                .header("token",this.token)

                /*.body("{\n" +
                        "  \"produtoNome\": \"Monitor\",\n" +
                        "  \"produtoValor\": 0.00,\n" +
                        "  \"produtoCores\": [\n" +
                        "    \"Prata\"\n" +
                        "  ],\n" +
                        "  \"produtoUrlMock\": \"string\",\n" +
                        "  \"componentes\": [\n" +
                        "    {\n" +
                        "      \"componenteNome\": \"Cabo de energia\",\n" +
                        "      \"componenteQuantidade\": 1\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}") // Adicionando produto e componente

                 */ //Ao invés de usar o que esta nos comentarios usaremos assim como esta abaixo
                .body(ProdutoDataFactory.criarProdutoComumComValorIgualA(0.00))

            .when()
                .post("/v2/produtos")
            .then()
                .assertThat()
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);
    }

    /*------------------------------------INICIO DOS TESTES ----------------------------------------------------------*/

    @Test
    @DisplayName("Validar que o valor do produto igual a 7000.01 não é permitido")
    public void testValidarLimitesMaiorSeteMillProibidoValorProduto(){

        /*Tentar inserir um produto com o valor 7000.01 e validar que a mensagem de erro foi apresentada e o
         status code retornado foi 422 pois inflíngil uma regra de negócios*/

        given()
                .contentType(ContentType.JSON)
                .header("token",this.token)
                .body(ProdutoDataFactory.criarProdutoComumComValorIgualA(7000.01))

            .when()
                .post("/v2/produtos")
            .then()
                .assertThat()
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);

    }
}
