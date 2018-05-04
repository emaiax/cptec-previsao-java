package xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "cidade")
@XmlType(propOrder = {"nome", "uf", "id"})
public class Cidade {
	@XmlElement(name = "id")
	public Integer id;
	@XmlElement(name = "nome")
	public String nome;
	@XmlElement(name = "uf")
	public String uf;
	public void setId(Integer id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
}

