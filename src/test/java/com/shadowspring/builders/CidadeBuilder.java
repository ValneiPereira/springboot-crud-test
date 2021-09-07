package com.shadowspring.builders;

import com.shadowspring.entity.Cidade;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CidadeBuilder {

    private Cidade cidade;

    public static CidadeBuilder umaCidade(){
        CidadeBuilder builder = new CidadeBuilder();
        builder.cidade = Cidade.builder()
                .id(1L)
                .nomeCidade("Tramandaí")
                .estado("RS")
                .build();
        return builder;

    }

    public Cidade agora(){
        return cidade;
    }


}
