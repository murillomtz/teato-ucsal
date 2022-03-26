package br.ucsal.teatroucsal.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MensagensConstant {

    ERRO_GENERICO("Erro interno identificado. Contate o suporte."),

    ERRO_SITUACAO_CADASTRADO_ANTERIORMENTE("Situação já possui cadastro."),

    ERRO_SITUACAO_NAO_ENCONTRADO("Situação não encontrado."),

    ERRO_ID_INFORMADO("ID não pode ser informado na operação de cadastro.");

    private final String valor;
}
