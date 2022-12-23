package br.com.banco.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.model.TipoTransacao;
import br.com.banco.repositorios.TipoTransacaoRepositorio;

@Service
public class TipoTransacaoService {

    @Autowired
    private TipoTransacaoRepositorio tipoTransacaoRepositorio;

    public TipoTransacao criar(TipoTransacao tipoTransacao) {
        return tipoTransacaoRepositorio.save(tipoTransacao);
    }

    public List<TipoTransacao> listarTipoTransacaos() {
        return tipoTransacaoRepositorio.findAll();
    }

    public TipoTransacao buscarTipoTransacaoPorId(int id) {
        return tipoTransacaoRepositorio.findById(id).orElseThrow(() -> new EntityNotFoundException("Tipo Transacao não localizada."));
    }

    // public TipoTransacao editar(int id, TipoTransacao tipoTransacao) {
    //     try {
    //         TipoTransacao atual = tipoTransacaoRepositorio.getReferenceById(id);
    //         BeanUtils.copyProperties(tipoTransacao, atual);
    //         return tipoTransacaoRepositorio.save(atual);
    //     } catch (EmptyResultDataAccessException e) {
    //         throw new EntityNotFoundException("Tipo Transacao não localizada.");
    //     } catch (FatalBeanException e) {
    //         throw new EntityNotFoundException("Erro ao atualizar o Recurso.");
    //     }
    // }

    

    // public void excluirTipoTransacao(int id) {
    //     try {
    //         tipoTransacaoRepositorio.deleteById(id);
    //     } catch (EmptyResultDataAccessException e) {
    //         throw new EntityNotFoundException("Objeto não encontrado.");
    //     }
    // }
}
