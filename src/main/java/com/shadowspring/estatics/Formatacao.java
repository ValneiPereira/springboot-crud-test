package com.shadowspring.estatics;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.text.DecimalFormat;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Formatacao {
    public static final DecimalFormat MASCARA_PORCENTO = new DecimalFormat("0,00%");
}
