package com.jose.seller.models;

import com.jose.seller.utils.EventoJsonAuxiliar;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Evento {

    @Id
    @Column(columnDefinition = "jsonb", name = "content")
    @Type(type = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    private EventoJsonAuxiliar eventoJsonAuxiliar;
}
