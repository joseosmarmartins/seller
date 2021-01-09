package com.jose.seller.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoJsonAuxiliar implements Serializable {

    private Date data;
    private String descricao;
    private Boolean sucesso;
}
