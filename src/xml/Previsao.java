package xml;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "cidade")
@XmlType(propOrder = {"nome", "uf", "atualizacao","previsao"})
public class Previsao {
	@XmlElement(name = "nome")
	public String nome;
	@XmlElement(name = "uf")
	public String uf;
	@XmlElement(name = "atualizacao")
	public String atualizacao;
	
	@XmlElement
	private DadosPrevisao[] previsao;

	public DadosPrevisao[] getDados() {
		return previsao;
	}
	public String toString() {
		return this.previsao.toString();
	}
	public ArrayList<String> dadosPrev(){
		ArrayList<String> dados = new ArrayList<>();
		dados.add(nome);
		dados.add(uf);
		dados.add(atualizacao);
		return dados;
		
	}
}

