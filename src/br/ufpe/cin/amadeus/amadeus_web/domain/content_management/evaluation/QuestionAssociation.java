/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import org.hibernate.annotations.CollectionOfElements;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class QuestionAssociation extends Question {
	@CollectionOfElements(fetch=FetchType.EAGER)
	@JoinTable(
	    name = "Association",
	    joinColumns = @JoinColumn(name = "QUESTIONASSOCIATION_ID"))
	@Column(
	   name = "value",
	   nullable = false
	) 
	@org.hibernate.annotations.MapKey(
	    columns={
	        @Column(
	             name="_key",
	             nullable=false
	        )
	   }
	) 
    private Map<String, String> associations = new HashMap<String, String>();

	
	public Map<String, String> getAssociations() {
		return associations;
	}

	public void setAssociations(Map<String, String> associations) {
		this.associations = associations;
	}

	@Override
	public String toString() {
		String retorno = "";
		
		retorno = this.getDescription();
		retorno += "<br/><br/> ";
		for(String key : this.getAssociations().keySet()) {
			retorno += key + " -> " + this.getAssociations().get(key) + "<br/>";
		}
		
		return retorno;
	}
}
