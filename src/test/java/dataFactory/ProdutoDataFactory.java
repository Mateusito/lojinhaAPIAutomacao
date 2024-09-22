package dataFactory;

import pojo.ComponentePojo;
import pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDataFactory {
    public static ProdutoPojo criarProdutoComumComValorIgualA(double valor){

        ProdutoPojo produto = new ProdutoPojo();
        produto.setProdutoNome("Macbook");
        produto.setProdutoValor(valor);

        List<String> cores = new ArrayList<>();
        cores.add("Prata");
        cores.add("Verde Musgo");

        produto.setProdutoCores(cores);
        produto.setProdutoUrlMock("");


/* Aqui abaixo como estamos instaciando uma lista e dentro desta lista tem outra lista, teremos que fazer o instanciamento
uma dentro da outra então teremos a lista  List<ComponentePojo> componentes = new ArrayList<>();, e os componentes também
 tem uma lista dentro colocaremos a lista ComponentePojo componente = new ComponentePojo();, e então sim iremos adicionar
  os componentes */ //Descrição de como funciona a lista que tem outra lista dentro

        List<ComponentePojo> componentes = new ArrayList<>();

        ComponentePojo componente = new ComponentePojo();
        componente.setComponenteNome("Carregador");
        componente.setComponenteQuantidade(1);
        componentes.add(componente);

        ComponentePojo segundoComponente = new ComponentePojo();
        segundoComponente.setComponenteNome("HeadPhone");
        segundoComponente.setComponenteQuantidade(1);
        componentes.add(segundoComponente);

        produto.setComponentes(componentes);

        return produto;
    }
}
