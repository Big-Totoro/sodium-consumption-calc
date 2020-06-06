package io.sskuratov.sodiumconsumptioncalc.entities;

import java.math.BigDecimal;

public interface Entity {

    BigDecimal getMax();
    BigDecimal getMin();
    BigDecimal getValue();
    String getCaption();
    Enum getUnits();
}
