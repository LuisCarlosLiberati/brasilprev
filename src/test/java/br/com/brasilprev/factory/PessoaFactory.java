package br.com.brasilprev.factory;

import br.com.brasilprev.pojo.PessoaPojo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
public class PessoaFactory {


    @JsonIgnoreProperties
    public static  PessoaPojo criarPessoa() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        PessoaPojo pessoa = objectMapper.readValue(new FileInputStream("src/test/resources/requestBody/pessoas.json"), PessoaPojo.class);
        return pessoa;
    }

    public static PessoaPojo criarPessoaValida() throws IOException {
        PessoaPojo pessoavalida = criarPessoa();
        return pessoavalida;
    }

    @JsonIgnoreProperties
    public static  PessoaPojo criarPessoaJsonInvalido() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        PessoaPojo pessoa = objectMapper.readValue(new FileInputStream("src/test/resources/requestBody/pessoaInvalida.json"), PessoaPojo.class);
        return pessoa;
    }

    public static PessoaPojo criarPessoaInvalida() throws IOException {
        PessoaPojo pessoaInvalida = criarPessoaJsonInvalido();
        return pessoaInvalida;
    }

}
