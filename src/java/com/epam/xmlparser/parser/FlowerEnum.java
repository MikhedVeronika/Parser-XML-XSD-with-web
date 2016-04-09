package com.epam.xmlparser.parser;


public enum FlowerEnum {
    FLOWERS("flowers"),
    ONE_YEAR_FLOWER("one-year-flower"),
    MULTI_YEAR_FLOWER("multi-year-flower"),
    ID("id"),

    NAME("name"),
    SOIL("soil"),
    ORIGIN("origin"),

    LEAF_COLOR("leaf-color"),             // visual-parameters
    FLOWER_COLOR("flower-color"),         // visual-parameters
    AVERAGE_SIZE_CM("average-size-cm"),   // visual-parameters

    REQUIRED_TEMPERATURE("required-temperature"),     // growing-tips
    LIGHTENING("lightening"),                         // growing-tips
    WEEK_WATERING_ML("week-watering-ml"),             // growing-tips

    MULTIPLYING("multiplying"),

    FLOWERING_DAYS_COUNT("flowering-days-count"),    // for one-year-flowers
    AGE_YEARS("age-years"),                          // for multi-year-flowers

    VISUAL_PARAMETERS("visual-parameters"),
    GROWING_TIPS("growing-tips");

    private String value;

    FlowerEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
