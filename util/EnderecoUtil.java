package br.com.microdatasistemas.simintegrationws.util;

import br.com.microdata.component.util.PersistenceMicrodataException;
import br.com.microdatasistemas.simintegrationws.entity.EnderecoSped;

public class EnderecoUtil {
    private EnderecoSummary fatEnd = new EnderecoSummary();
    private EnderecoSummary cobEnd = new EnderecoSummary();
    private EnderecoSummary entEnd = new EnderecoSummary();
    private EnderecoSped endereco;

    public void fillAddresses(EnderecoSped endSped) throws PersistenceMicrodataException {
	this.endereco = endSped;

	this.fillFatEnd(endSped);
	this.fillCobEnd(endSped);
	this.fillEntEnd(endSped);

	if (fatEnd.equals(cobEnd) && fatEnd.equals(entEnd)) {
	    endereco.setCobEndereco("O MESMO");
	    endereco.setCobComplemento(!Util.isEmpty(fatEnd.getComplemento()) ? "O MESMO" : null);
	    endereco.setCobBairro("O MESMO");

	    endereco.setEntEndereco("O MESMO");
	    endereco.setEntComplemento(!Util.isEmpty(fatEnd.getComplemento()) ? "O MESMO" : null);
	    endereco.setEntBairro("O MESMO");
	}
    }

    private void fillFatEnd(EnderecoSped address) throws PersistenceMicrodataException {
	fatEnd.setEndereco(address.getFatEndereco());
	fatEnd.setNumero(address.getFatNumero());
	fatEnd.setComplemento(Util.truncate(address.getFatComplemento(), 60));
	fatEnd.setBairro(address.getFatBairro());
	fatEnd.setCep(Util.getFormattedCEP(address.getFatCep()));
    }

    private void fillCobEnd(EnderecoSped address) throws PersistenceMicrodataException {
	cobEnd.setEndereco(address.getCobEndereco());
	cobEnd.setNumero(address.getCobNumero());
	cobEnd.setComplemento(Util.truncate(address.getCobComplemento(), 60));
	cobEnd.setBairro(address.getCobBairro());
	cobEnd.setCep(Util.getFormattedCEP(address.getCobCep()));
    }

    private void fillEntEnd(EnderecoSped address) throws PersistenceMicrodataException {
	entEnd.setEndereco(address.getEntEndereco());
	entEnd.setNumero(address.getEntNumero());
	entEnd.setComplemento(Util.truncate(address.getEntComplemento(), 60));
	entEnd.setBairro(address.getEntBairro());
	entEnd.setCep(Util.getFormattedCEP(address.getEntCep()));
    }

    private static class EnderecoSummary {
	private String endereco;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;

	public void setEndereco(String endereco) {
	    this.endereco = endereco;
	}

	public void setNumero(String numero) {
	    this.numero = numero;
	}

	public String getComplemento() {
	    return complemento;
	}

	public void setComplemento(String complemento) {
	    this.complemento = complemento;
	}

	public void setBairro(String bairro) {
	    this.bairro = bairro;
	}

	public void setCep(String cep) {
	    this.cep = cep;
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
	    result = prime * result + ((cep == null) ? 0 : cep.hashCode());
	    result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
	    result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
	    result = prime * result + ((numero == null) ? 0 : numero.hashCode());
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
		return true;
	    if (obj == null)
		return false;
	    if (!(obj instanceof EnderecoSummary))
		return false;
	    EnderecoSummary other = (EnderecoSummary) obj;
	    if (bairro == null) {
		if (other.bairro != null)
		    return false;
	    } else if (!bairro.equals(other.bairro))
		return false;
	    if (cep == null) {
		if (other.cep != null)
		    return false;
	    } else if (!cep.equals(other.cep))
		return false;
	    if (complemento == null) {
		if (other.complemento != null)
		    return false;
	    } else if (!complemento.equals(other.complemento))
		return false;
	    if (endereco == null) {
		if (other.endereco != null)
		    return false;
	    } else if (!endereco.equals(other.endereco))
		return false;
	    if (numero == null) {
		if (other.numero != null)
		    return false;
	    } else if (!numero.equals(other.numero))
		return false;
	    return true;
	}
    }
}