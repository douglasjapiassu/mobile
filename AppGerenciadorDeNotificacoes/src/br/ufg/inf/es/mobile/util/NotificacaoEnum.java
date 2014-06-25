package br.ufg.inf.es.mobile.util;

public class NotificacaoEnum {
	
	public enum TipoNotificacao {
		
		PROVA_IMINENTE("Aviso de Prova Iminente", 1),
		NOTAS_E_FREQUENCIA("Notas e Frequência", 4),
		VENCIMENTO_EMPRESTIMO("Aviso de Vencimento de Empréstimo da Biblioteca", 2),
		COMUNICADO_GERAL("Comunicado Geral", 3);
		
		private final String descricao;
		private final int codigo;
		
		private TipoNotificacao(String descricao, int codigo) {
			this.descricao = descricao;
			this.codigo = codigo;
		}
		
		public String getDescricao() {
			return descricao;
		}
		
		public int getCodigo() {
			return codigo;
		}

	}
	
	public enum TipoRemetente {
		
		COORDENADOR("Coordenador do Curso", 1),
		DIRECAO("Direção de Unidade do Curso", 2),
		BIBLIOTECA("Biblioteca", 3),
		PRO_REITORIA("Pró-Reitorias", 4),
		REITORIA("Reitoria", 5),
		DOCENTE("Docente da Disciplina", 6);
		
		private final String descricao;
		private final int codigo;
		
		private TipoRemetente(String descricao, int codigo) {
			this.descricao = descricao;
			this.codigo = codigo;
		}
		
		public String getDescricao() {
			return descricao;
		}
		
		public int getCodigo() {
			return codigo;
		}

	}
	
	public static String getTipoNotificacao(int codigo) {
		switch(codigo) {
		case 1:
			return TipoNotificacao.PROVA_IMINENTE.getDescricao();
		case 2:
			return TipoNotificacao.NOTAS_E_FREQUENCIA.getDescricao();
		case 3:
			return TipoNotificacao.VENCIMENTO_EMPRESTIMO.getDescricao();
		case 4:
			return TipoNotificacao.COMUNICADO_GERAL.getDescricao();
		default:
			return "Não existe esse Tipo de Notificação";
		}
	}
	
	public static String getTipoRemetente(int codigo) {
		switch(codigo) {
		case 1:
			return TipoRemetente.COORDENADOR.getDescricao();
		case 2:
			return TipoRemetente.DIRECAO.getDescricao();
		case 3:
			return TipoRemetente.BIBLIOTECA.getDescricao();
		case 4:
			return TipoRemetente.PRO_REITORIA.getDescricao();
		case 5:
			return TipoRemetente.REITORIA.getDescricao();
		case 6:
			return TipoRemetente.DOCENTE.getDescricao();
		default:
			return "Não existe esse Tipo de Remetente";
		}
	}

}
