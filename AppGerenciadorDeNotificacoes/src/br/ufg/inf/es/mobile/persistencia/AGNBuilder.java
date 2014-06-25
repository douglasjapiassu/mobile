package br.ufg.inf.es.mobile.persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.j256.ormlite.table.TableUtils;

import br.ufg.inf.es.mobile.model.Disciplina;
import br.ufg.inf.es.mobile.model.Notificacao;
import br.ufg.inf.es.mobile.model.Usuario;
import br.ufg.inf.es.mobile.util.NotificacaoEnum;
import android.content.Context;
import android.util.Log;

public class AGNBuilder  {

	private static DatabaseHelper helper;
	private static DisciplinaDAO disciplinaDAO;
	private static UsuarioDAO usuarioDAO;
	private static NotificacaoDAO notificacaoDAO;
	Disciplina web, mobile, integracao, persistencia, concorrencia;

	public AGNBuilder(Context context) {
		helper = new DatabaseHelper(context);
	}
	
	public void construct() {
		if (!disciplinas()) Log.e("ERROS", "Erro ao criar Disciplinas");
		if (!usuarios()) Log.e("ERROS", "Erro ao criar Usuários");
		if (!notificacoes()) Log.e("ERROS", "Erro ao criar Notificações");
	}
	
	private Boolean disciplinas() {
		List<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
		
		web = new Disciplina("Desenvolvimento Web", "Fábio Nogueira Lucena", true);
		mobile = new Disciplina("Desenvolvimento Mobile", "Fábio Nogueira Lucena", false);
		integracao = new Disciplina("Integração de Aplicações", "Fábio Nogueira Lucena", true);
		persistencia = new Disciplina("Persistência", "Marcelo Quinta", true);
		concorrencia = new Disciplina("Concorrência", "Marcelo Quinta", true);
		
		try {
			TableUtils.clearTable(helper.getConnectionSource(), Disciplina.class);
			disciplinaDAO = new DisciplinaDAO(helper.getConnectionSource());
			
			disciplinaDAO.create(web);
			disciplinaDAO.create(mobile);
			disciplinaDAO.create(integracao);
			disciplinaDAO.create(persistencia);
			disciplinaDAO.create(concorrencia);
			
			listaDisciplinas = disciplinaDAO.queryForAll();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		
		Log.i("INFO", listaDisciplinas.toString());
		
		return true;
	}
	
	private Boolean usuarios() {
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		
		Usuario admin = new Usuario("admin", "admin", "Administrador");
		Usuario douglas = new Usuario("090187", "12345", "Douglas");
		
		try {
			TableUtils.clearTable(helper.getConnectionSource(), Usuario.class);
			usuarioDAO = new UsuarioDAO(helper.getConnectionSource());
			
			usuarioDAO.create(admin);
			usuarioDAO.create(douglas);
			
			listaUsuarios = usuarioDAO.queryForAll();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		
		Log.i("INFO", listaUsuarios.toString());
		
		return true;
	}
	
	private Boolean notificacoes() {
		List<Notificacao> listaNotificacoes = new ArrayList<Notificacao>();
		
		Notificacao avisoDeProva = new Notificacao(new Date(), false, NotificacaoEnum.TipoNotificacao.PROVA_IMINENTE.getCodigo(), NotificacaoEnum.TipoRemetente.DOCENTE.getCodigo(), "Cabeçalho", "www.google.com.br", "admin", web);
		Notificacao notasFrequencia = new Notificacao(new Date(), false, NotificacaoEnum.TipoNotificacao.NOTAS_E_FREQUENCIA.getCodigo(), NotificacaoEnum.TipoRemetente.BIBLIOTECA.getCodigo(), "Cabeçalho", "Mensagem www.google.com.br", "admin", web);
		Notificacao avisoBiblioteca = new Notificacao(new Date(), false, NotificacaoEnum.TipoNotificacao.VENCIMENTO_EMPRESTIMO.getCodigo(), NotificacaoEnum.TipoRemetente.PRO_REITORIA.getCodigo(), "Cabeçalho", "Mensagem www.google.com.br", "12345", persistencia);
		Notificacao comunicadoGeral = new Notificacao(new Date(), false, NotificacaoEnum.TipoNotificacao.COMUNICADO_GERAL.getCodigo(), NotificacaoEnum.TipoRemetente.DIRECAO.getCodigo(), "Cabeçalho", "Mensagem www.google.com.br", "", concorrencia);
		
		try {
			TableUtils.clearTable(helper.getConnectionSource(), Notificacao.class);
			notificacaoDAO = new NotificacaoDAO(helper.getConnectionSource());
			
			notificacaoDAO.create(avisoDeProva);
			notificacaoDAO.create(notasFrequencia);
			notificacaoDAO.create(avisoBiblioteca);
			notificacaoDAO.create(comunicadoGeral);
			
			listaNotificacoes = notificacaoDAO.queryForAll();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		
		Log.i("INFO", listaNotificacoes.toString());
		
		return true;
	}

	
}