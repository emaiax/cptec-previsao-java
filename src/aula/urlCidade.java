package aula;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import xml.Cidades;

public class urlCidade {
	public String getXMLCidade(String cidade) throws Exception {
		 String charset = java.nio.charset.StandardCharsets.ISO_8859_1.name();
		 String linha, resultado = "";
		 String urlListaCidade = "http://servicos.cptec.inpe.br/XML/listaCidades?city=%s";
		 /* codifica os parâmetros */
		 String parametro = String.format(urlListaCidade, URLEncoder.encode(cidade, charset) );
		 URL url = new URL(parametro);
		 URLConnection conexao = url.openConnection(); 
		 BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
		 while((linha = reader.readLine()) != null){
		 resultado +=linha;
		 }
		 return resultado;
		}
	
	public xml.Cidade[] xmlToObjectCidade(String xml) throws Exception {
		 StringReader sr = new StringReader(xml);
		 /* a base do XML é uma marcação de nome cidades */
		 JAXBContext context = JAXBContext.newInstance(Cidades.class);
		 Unmarshaller un = context.createUnmarshaller();
		 Cidades cidades = (Cidades) un.unmarshal(sr);
		 return cidades.getCidade();
		}
	
	
}
