package com.shadowspring.enums;

public enum Sexo {
  MASCULINO("M"), 
  FEMININO("F");

  private String descricao;

  Sexo(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return this.descricao;
  }

}
