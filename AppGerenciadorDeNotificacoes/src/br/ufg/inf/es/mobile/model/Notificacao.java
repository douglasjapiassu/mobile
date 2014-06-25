package br.ufg.inf.es.mobile.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.ufg.inf.es.mobile.util.NotificacaoEnum;

@DatabaseTable(tableName="notificacao")
public class Notificacao extends GenericModel {

	@DatabaseField
	private Date data_recebimento;
	
	@DatabaseField
	private Boolean isMensagemLida;
	
	@DatabaseField(columnName = "tipo_notificacao")
	private int tipoNotificacao;
	
	@DatabaseField(columnName = "tipo_remetente")
	private int tipoRemetente;
	
	@DatabaseField
	private String cabecalho;
	
	@DatabaseField
	private String mensagem;
	
	@DatabaseField
	private String matricula;
	
	@DatabaseField(columnName = "id_disciplina", foreign = true)
	private Disciplina disciplina;
	
	public Notificacao() {
		
	}
	
	
	
	public Notificacao(Date data_recebimento, Boolean isMensagemLida,
			int tipoNotificacao, int tipoRemetente, String cabecalho,
			String mensagem, String matricula, Disciplina disciplina) {
		super();
		this.data_recebimento = data_recebimento;
		this.isMensagemLida = isMensagemLida;
		this.tipoNotificacao = tipoNotificacao;
		this.tipoRemetente = tipoRemetente;
		this.cabecalho = cabecalho;
		this.mensagem = mensagem;
		this.matricula = matricula;
		this.disciplina = disciplina;
	}



	public String getDataRecebimentoFormatada(){
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		String data = formato.format(this.data_recebimento);
		
		return data;
	}
	
	@Override
	public String toString() {
		
		return 
    		"Data: "+ getDataRecebimentoFormatada() + "\n"+ 
    		"Mensagem Lida: "+ (this.isMensagemLida ? "Sim" : "Não") + "\n"+ 
    		"Tipo de Notificação: "+ NotificacaoEnum.getTipoNotificacao(this.tipoNotificacao) + "\n"+ 
    		"Tipo de Remetente: "+ NotificacaoEnum.getTipoRemetente(this.tipoRemetente) + "\n"+ 
    		"Cabeçalho: "+ this.cabecalho + "\n"+ 
    		"Mensagem: "+ this.mensagem;
	}

	public Notificacao(int identificador, Date data_recebimento,
			Boolean isMensagemLida, int tipoNotificacao, int tipoRemetente,
			String cabecalho, String mensagem) {
		super();
		this.data_recebimento = data_recebimento;
		this.isMensagemLida = isMensagemLida;
		this.tipoNotificacao = tipoNotificacao;
		this.tipoRemetente = tipoRemetente;
		this.cabecalho = cabecalho;
		this.mensagem = mensagem;
	}

	public Date getData_recebimento() {
		return data_recebimento;
	}

	public void setData_recebimento(Date data_recebimento) {
		this.data_recebimento = data_recebimento;
	}

	public Boolean getIsMensagemLida() {
		return isMensagemLida;
	}

	public void setIsMensagemLida(Boolean isMensagemLida) {
		this.isMensagemLida = isMensagemLida;
	}

	public int getTipoNotificacao() {
		return tipoNotificacao;
	}

	public void setTipoNotificacao(int tipoNotificacao) {
		this.tipoNotificacao = tipoNotificacao;
	}

	public int getTipoRemetente() {
		return tipoRemetente;
	}

	public void setTipoRemetente(int tipoRemetente) {
		this.tipoRemetente = tipoRemetente;
	}

	public String getCabecalho() {
		return cabecalho;
	}

	public void setCabecalho(String cabecalho) {
		this.cabecalho = cabecalho;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}



	public Disciplina getDisciplina() {
		return disciplina;
	}



	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
}
