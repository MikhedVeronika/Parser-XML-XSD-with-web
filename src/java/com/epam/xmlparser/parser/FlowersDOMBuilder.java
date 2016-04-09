package com.epam.xmlparser.parser;

import com.epam.xmlparser.characteristics.GrowingTips;
import com.epam.xmlparser.characteristics.VisualParameters;
import com.epam.xmlparser.entity.Flower;
import com.epam.xmlparser.entity.MultiYearFlower;
import com.epam.xmlparser.entity.OneYearFlower;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Set;

public class FlowersDOMBuilder extends AbstractFlowersBuilder {

    private DocumentBuilder docBuilder;

    public FlowersDOMBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("Ошибка конфигурации парсера: " + e);
        }
    }

    public FlowersDOMBuilder (Set<Flower> flowers) {
        super(flowers);
    }

    @Override
    public void buildSetFlowers(String fileName) {
        Document doc = null;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList flowersList = root.getElementsByTagName("one-year-flower");
            for (int i = 0; i < flowersList.getLength(); i++) {
                Element flowerElement = (Element) flowersList.item(i);
                OneYearFlower flower = buildOneYearFlower(flowerElement);
                flowers.add(flower);
            }
            flowersList = root.getElementsByTagName("multi-year-flower");
            for (int i = 0; i < flowersList.getLength(); i++) {
                Element flowerElement = (Element) flowersList.item(i);
                MultiYearFlower flower = buildMultiYearFlower(flowerElement);
                flowers.add(flower);
            }
        } catch (IOException e) {
            System.err.println("File error or I/O error: " + e);
        } catch (SAXException e) {
            System.err.println("Parsing failure: " + e);
        }
    }

    private OneYearFlower buildOneYearFlower(Element flowerElement) {
        OneYearFlower flower = new OneYearFlower();
        buildFlower(flowerElement, flower);
        flower.setFloweringDaysCount(Integer.valueOf(getElementTextContent(flowerElement, "flowering-days-count")));
        return flower;
    }

    private MultiYearFlower buildMultiYearFlower(Element flowerElement) {
        MultiYearFlower flower = new MultiYearFlower();
        buildFlower(flowerElement, flower);
        flower.setAgeYears(Integer.valueOf(getElementTextContent(flowerElement, "age-years")));
        return flower;
    }

    private void buildFlower(Element flowerElement, Flower flower) {

        flower.setVisualParameters(new VisualParameters());
        flower.setGrowingTips(new GrowingTips());


        flower.setId(flowerElement.getAttribute("id"));
        flower.setName(getElementTextContent(flowerElement, "name"));
        flower.setSoil(getElementTextContent(flowerElement, "soil"));
        flower.setOrigin(getElementTextContent(flowerElement, "origin"));

        VisualParameters vp = flower.getVisualParameters();
        Element vpElement = (Element) flowerElement.getElementsByTagName("visual-parameters").item(0);
        vp.setLeafColor(getElementTextContent(vpElement, "leaf-color"));
        vp.setFlowerColor(getElementTextContent(vpElement, "flower-color"));
        vp.setAverageSizeCm(Integer.valueOf(getElementTextContent(vpElement, "average-size-cm")));

        GrowingTips gt = flower.getGrowingTips();
        Element gtElement = (Element) flowerElement.getElementsByTagName("growing-tips").item(0);
        gt.setRequiredTemperature(Integer.valueOf(getElementTextContent(gtElement, "required-temperature")));
        gt.setLightening(getElementTextContent(gtElement, "lightening"));
        gt.setWeekWateringMl(Integer.valueOf(getElementTextContent(gtElement, "week-watering-ml")));

        flower.setMultiplying(getElementTextContent(flowerElement, "multiplying"));
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
