package com.shadowspring.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Sexo {
  M("M"), 
  F("F");

  private String descricao;

  Sexo(String descricao) {
    this.descricao = descricao;
  }
  @JsonValue
  public String getDescricao() {
    return this.descricao;
  }

}
