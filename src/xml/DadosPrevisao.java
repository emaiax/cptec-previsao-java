package xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "previsao")
@XmlType(propOrder = {"dia", "tempo", "maxima", "minima", "iuv"})
public class DadosPrevisao {
	@XmlElement(name = "dia")
	public String dia;
	@XmlElement(name = "tempo")
	public String tempo;
	@XmlElement(name = "maxima")
	public String maxima;
	@XmlElement(name = "minima")
	public String minima;
	@XmlElement(name = "iuv")
	public String iuv;
	public void setDia(String dia) {
		this.dia = dia;
	}
	public void setTempo(String tempo) {
		this.tempo = tempo;
	}
	public void setMaxima(String maxima) {
		this.maxima = maxima;
	}
	public void setMinima(String minima) {
		this.minima = minima;
	}
	public void setIuv(String iuv) {
		this.iuv = iuv;
	}
	
}
