package io.sskuratov.sodiumconsumptioncalc.state;

public enum States {
    INIT("Начало"),
    URINE_CREATININE_CONCENTRATION("Концентрация креатинина в разовой порции мочи"),
    URINE_CREATININE_CONCENTRATION_UNITS("Единицы измерения"),
    URINE_SODIUM_CONCENTRATION("Концентрация натрия в разовой порции мочи"),
    URINE_POTASSIUM_CONCENTRATION("Концентрация калия в разовой порции мочи"),
    SEX("Пол"),
    AGE("Возраст"),
    HEIGHT("Рост"),
    WEIGHT("Вес"),
    COMPLETED("Завершено"),
    ERROR("Ошибка");

    private final String description;

    States(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
