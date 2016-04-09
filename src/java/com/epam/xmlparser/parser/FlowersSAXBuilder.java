package com.epam.xmlparser.parser;

import com.epam.xmlparser.entity.Flower;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class FlowersSAXBuilder extends AbstractFlowersBuilder{
    private FlowerHandler fh;
    private XMLReader reader;

    public FlowersSAXBuilder() {
        // создание SAX-анализатора
        fh = new FlowerHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(fh);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        }
    }

    public FlowersSAXBuilder(Set<Flower> flowers) {
        super(flowers);
        // more code
    }

    @Override
    public void buildSetFlowers(String fileName) {
        try {
            // разбор XML-документа
            reader.parse(fileName);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        } catch (IOException e) {
            System.err.print("ошибка I/О потока: " + e);
        }
        flowers = fh.getFlowers();
    }
}
