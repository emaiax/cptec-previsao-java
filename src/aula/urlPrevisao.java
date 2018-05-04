package aula;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import xml.DadosPrevisao;
import xml.Previsao;

public class urlPrevisao {
	public String getXMLPrevisao(String cidade) throws Exception {
		 String charset = java.nio.charset.StandardCharsets.ISO_8859_1.name();
		 String linha, resultado = "";
		 String urlListaCidade = "http://servicos.cptec.inpe.br/XML/cidade/7dias/"+cidade+"/previsao.xml";
		 /* codifica os par�metros */
		 String parametro = String.format(urlListaCidade, URLEncoder.encode(cidade, charset) );
		 URL url = new URL(parametro);
		 URLConnection conexao = url.openConnection(); 
		 BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
		 while((linha = reader.readLine()) != null){
		 resultado +=linha;
		 }
		 return resultado;
		}
	
	public DadosPrevisao[] xmlToObjectPrevisoes(String xml) throws Exception {
		 StringReader sr = new StringReader(xml);
		 /* a base do XML � uma marca��o de nome cidades */
		 JAXBContext context = JAXBContext.newInstance(Previsao.class);
		 Unmarshaller un = context.createUnmarshaller();
		 Previsao previsao = (Previsao) un.unmarshal((Reader) sr);
		 return previsao.getDados();
		}
	public ArrayList<String> xmlToObjectPrevisao(String xml) throws Exception {
		 StringReader sr = new StringReader(xml);
		 /* a base do XML � uma marca��o de nome cidades */
		 JAXBContext context = JAXBContext.newInstance(Previsao.class);
		 Unmarshaller un = context.createUnmarshaller();
		 Previsao prev = (Previsao) un.unmarshal((Reader) sr);
		 return prev.dadosPrev();
		}

}
