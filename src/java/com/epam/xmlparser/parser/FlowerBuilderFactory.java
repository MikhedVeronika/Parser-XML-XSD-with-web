package com.epam.xmlparser.parser;


public class FlowerBuilderFactory {

    private enum ParserType {
        DOM, SAX, STAX
    }
    public AbstractFlowersBuilder createFlowerBuilder(String parserType) {
        ParserType type = ParserType.valueOf(parserType.toUpperCase());
        switch (type) {
            case DOM:
                return new FlowersDOMBuilder();
            case STAX:
                return new FlowersStAXBuilder();
            case SAX:
                return new FlowersSAXBuilder();
            default:
                throw new EnumConstantNotPresentException (type.getDeclaringClass(), type.name());
        }
    }
}
