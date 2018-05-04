package aula;

import java.util.ArrayList;

import xml.Cidade;
import xml.DadosPrevisao;

public class Principal {

	public static void main(String[] args) throws Exception {
		urlCidade cidade = new urlCidade();
		urlPrevisao previsao = new urlPrevisao();
		String r = cidade.getXMLCidade("sao jose");
		String p = previsao.getXMLPrevisao("4963");
		Banco b = new Banco();
		Cidade[] lista = cidade.xmlToObjectCidade(r);
		for(Cidade cidade1:lista){
			System.out.print(cidade1.id+" ");
			System.out.print(cidade1.uf+" ");
			System.out.println(cidade1.nome);
		}
		System.out.println();
		DadosPrevisao[] ds = previsao.xmlToObjectPrevisoes(p);
		for(DadosPrevisao prev:ds) {
			System.out.println("Data: "+prev.dia+" Tempo: "+prev.tempo+" Maxima: "+prev.maxima+" Minima: "+prev.minima+" IUV: "+prev.iuv);
		}
		ArrayList<String> dados = previsao.xmlToObjectPrevisao(p);
		for(String d:dados) {
			System.out.println(d);
		}
		b.createTablePrevisao();
		b.insertDados(4963);
		ArrayList<DadosPrevisao> list = new ArrayList<>();
		list = (ArrayList<DadosPrevisao>) b.selectDados("select * from tbprevisao");
		for(DadosPrevisao d:list) {
			System.out.println(d.dia+" "+d.maxima);
		}
	}

}
