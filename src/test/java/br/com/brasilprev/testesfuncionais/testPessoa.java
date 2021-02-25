package br.com.brasilprev.testesfuncionais;

import br.com.brasilprev.factory.PessoaFactory;
import br.com.brasilprev.pojo.PessoaPojo;
import br.com.brasilprev.util.Configuracoes;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.hamcrest.Matchers;

import java.io.IOException;

import static io.restassured.RestAssured.*;


public class testPessoa {
    PessoaPojo pessoaValida;
    PessoaPojo pessoaInvalida;
    private Response response;


    @Before
    public void setUp(){
        Configuracoes configuracoes = ConfigFactory.create(Configuracoes.class);
        baseURI = configuracoes.baseURI();
        port = configuracoes.port();
        basePath = configuracoes.basePath();
    }


    @Dado("que desejo cadastrar uma pessoa")
    public void que_desejo_cadastrar_uma_pessoa() throws IOException {
        pessoaValida = PessoaFactory.criarPessoaValida();

    }

    @Quando("consulto o ddd {string} e numero {string}")
    public void consulto_o_ddd_e_numero(String ddd, String numero) {
        //verifico se a pessoa  existe cadastrada, se ela existir seria necessario um delete para a pessoa. Feature ainda nao implementada.
        Response responseNova = consultaPessoaCadastradaPeloTelefone(ddd, numero);
        if(responseNova.statusCode() == 200) {
            System.out.println("Já existe um registro de Pessoa com estes dados");
            //iria fazer a rotina para excluir a pessoa  para poder cadastrar a nova.
        }
    }

    @E("realizo o post em \\/pessoas")
    public void realizo_o_post_em_pessoas() {

        response = RestAssured
            .given()
                .contentType(ContentType.JSON)
                .body(pessoaValida)
            .when()
                .post();
    }

    @Entao("deve ser exibido o status code {int}")
    public void deve_ser_exibido_o_status_code(Integer statusCode) {
        response.then().statusCode(statusCode);

    }

    @Entao("a resposta deve conter todos os dados da pessoa cadastrada")
    public void a_resposta_deve_conter_todos_os_dados_da_pessoa_cadastrada() {
        response.then().body("nome", Matchers.equalToIgnoringCase(pessoaValida.getNome()))
                        .body("cpf", Matchers.equalTo(pessoaValida.getCpf()))
                        .body("enderecos[0].logradouro", Matchers.equalToIgnoringCase("Rua São João Clímaco"))
                        .body("enderecos[0].numero", Matchers.equalTo(249))
                        .body("enderecos[0].complemento",Matchers.equalToIgnoringCase("casa"))
                        .body("enderecos[0].bairro",Matchers.equalToIgnoringCase("São João Clímaco"))
                        .body("enderecos[0].cidade",Matchers.equalToIgnoringCase("São Paulo"))
                        .body("enderecos[0].estado",Matchers.equalToIgnoringCase("SP"))
                        .body("telefones[0].ddd",Matchers.equalToIgnoringCase("11"))
                        .body("telefones[0].numero",Matchers.equalToIgnoringCase("949041329"));
    }




    @Dado("possuo o cadastro da pessoa com ddd {string} e numero {string}")
    public void possuo_o_cadastro_da_pessoa_com_ddd_e_numero(String ddd, String numero) throws IOException {
        //verifico se a pessoa não existe cadastrada, se não existir realizo o cadastro.
        Response responseNova = consultaPessoaCadastradaPeloTelefone(ddd, numero);
        if(responseNova.statusCode() == 404) {
           cadastrarPessoaValida();
        }
    }

    @Quando("realizo o get em \\/pessoas informando o ddd {string} e numero {string}")
    public void realizo_o_get_em_pessoas_informando_o_ddd_e_numero(String ddd, String numero) {
        response = consultaPessoaCadastradaPeloTelefone(ddd, numero);
    }

    @Entao("a resposta deve conter o {string} e numero {string}")
    public void a_resposta_deve_conter_o_e_numero(String ddd, String numero) {
        response.then()
                .body("telefones[0].ddd", Matchers.equalTo(ddd))
                .body("telefones[0].numero", Matchers.equalTo(numero));
    }


    @Dado("que não possuo cadastro da pessoa com ddd {string} e numero {string}")
    public void que_não_possuo_cadastro_da_pessoa_com_ddd_e_numero(String ddd, String numero) {
        //verifico se a pessoa  existe cadastrada, se  existir realizo a exclusao
        Response responseNova = consultaPessoaCadastradaPeloTelefone(ddd, numero);
        if(responseNova.statusCode() == 200) {
//            descadastrarPessoaValida();
// seria necessario rotina de excluir a pessoa para o teste sempre funcionar, mas como nao existe o endpoint então nao criei a rotina
        }
    }

    @Entao("a resposta deve conter a mensagem de erro informando que o ddd {string} e numero {string} não foi encontrado")
    public void a_resposta_deve_conter_a_mensagem_de_erro_informando_que_o_ddd_e_numero_não_foi_encontrado(String ddd, String numero) {
        response.then()
                .body("erro", Matchers.equalToIgnoringCase("Não existe pessoa com o telefone ("+ddd+")"+numero));

    }


    @Quando("realizo o post para cadastrar a mesma pessoa")
    public void realizo_o_post_para_cadastrar_a_mesma_pessoa(){
       try{ response = cadastrarPessoaValida();
       } catch (IOException e) {
           e.printStackTrace();
       }
    }


    @Dado("possuo o cadastro da pessoa com cpf {string}")
    public void possuo_o_cadastro_da_pessoa_com_cpf(String cpf) {
        try{
            pessoaValida = PessoaFactory.criarPessoaValida();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Entao("a mensagem de pessoa ja cadastrada com este cpf {string}")
    public void a_mensagem_de_pessoa_ja_cadastrada_com_este_cpf(String cpf) {
        response.then()
                .body("erro", Matchers.equalToIgnoringCase("Já existe pessoa cadastrada com o CPF " + cpf));
    }


    @Dado("que desejo cadastrar uma pessoa com nome de muitos caracteres")
    public void que_desejo_cadastrar_uma_pessoa_com_nome_de_muitos_caracteres() throws IOException {
        pessoaInvalida = PessoaFactory.criarPessoaInvalida();
    }

    @Quando("realizo post em pessoa informando um body invalido")
    public void realizo_post_em_pessoa_informando_um_body_invalido() {
        response = RestAssured
            .given()
                .contentType(ContentType.JSON)
                .body(pessoaInvalida)
            .when()
                .post();
    }


    @Entao("a resposta informando o preenchimento invalido")
    public void a_resposta_informando_o_preenchimento_invalido() {
        response.then()
                .body("erro", Matchers.equalToIgnoringCase("O nome deve conter no máximo 50 caracteres"));
    }




    public Response cadastrarPessoaValida() throws IOException {
        pessoaValida = PessoaFactory.criarPessoaValida();
        response = RestAssured
            .given()
                .contentType(ContentType.JSON)
                .body(pessoaValida)
            .when()
                .post();

        return response;
    }

    public Response consultaPessoaCadastradaPeloTelefone( String ddd, String numero){
        response = RestAssured
            .given()
                .contentType(ContentType.JSON)
            .when()
                .get("/"+ddd+"/"+numero);
        return response;
    }



}
