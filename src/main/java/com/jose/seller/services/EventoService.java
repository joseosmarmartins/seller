package com.jose.seller.services;

import com.jose.seller.models.Evento;
import com.jose.seller.repository.IEventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    private final IEventoRepository iEventoRepository;

    public EventoService(IEventoRepository iEventoRepository) {
        this.iEventoRepository = iEventoRepository;
    }

    public void salvar(Evento evento) {
        iEventoRepository.save(evento);
    }

    public List<Evento> get() {
        return iEventoRepository.findAll();
    }
}
