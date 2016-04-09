package com.epam.xmlparser.parser;

import com.epam.xmlparser.characteristics.GrowingTips;
import com.epam.xmlparser.characteristics.VisualParameters;
import com.epam.xmlparser.entity.Flower;
import com.epam.xmlparser.entity.MultiYearFlower;
import com.epam.xmlparser.entity.OneYearFlower;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

public class FlowersStAXBuilder extends AbstractFlowersBuilder {
    private XMLInputFactory inputFactory;

    public FlowersStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public FlowersStAXBuilder (Set<Flower> flowers) {
        super(flowers);
    }

    @Override
    public void buildSetFlowers(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            // StAX parsing
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (FlowerEnum.valueOf(name.toUpperCase().replace('-', '_')) == FlowerEnum.ONE_YEAR_FLOWER) {
                        OneYearFlower flower = buildOneYearFlower(reader);
                        flowers.add(flower);
                    } else if (FlowerEnum.valueOf(name.toUpperCase().replace('-', '_')) == FlowerEnum.MULTI_YEAR_FLOWER) {
                        MultiYearFlower flower = buildMultiYearFlower(reader);
                        flowers.add(flower);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            System.err.println("StAX parsing error! " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.err.println("File " + fileName + " not found! " + ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Impossible close file " + fileName + " : " + e);
            }
        }
    }

    private OneYearFlower buildOneYearFlower(XMLStreamReader reader) throws XMLStreamException {
        OneYearFlower flower = new OneYearFlower();
        flower = (OneYearFlower) buildFlower(reader, flower);
        return flower;
    }

    private MultiYearFlower buildMultiYearFlower(XMLStreamReader reader) throws XMLStreamException {
        MultiYearFlower flower = new MultiYearFlower();
        flower = (MultiYearFlower) buildFlower(reader, flower);
        return flower;
    }

    private Flower buildFlower(XMLStreamReader reader, Flower flower) throws XMLStreamException {

        flower.setVisualParameters(new VisualParameters());
        flower.setId(reader.getAttributeValue(null, FlowerEnum.ID.getValue()));

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerEnum.valueOf(name.toUpperCase().replace('-', '_'))) {
                        case NAME:
                            flower.setName(getXMLText(reader));
                            break;
                        case SOIL:
                            flower.setSoil(getXMLText(reader));
                            break;
                        case ORIGIN:
                            flower.setOrigin(getXMLText(reader));
                            break;
                        case VISUAL_PARAMETERS:
                            flower.setVisualParameters(getXMLVisualParameters(reader));
                            break;
                        case GROWING_TIPS:
                            flower.setGrowingTips(getXMLGrowingTips(reader));
                            break;
                        case MULTIPLYING:
                            flower.setMultiplying(getXMLText(reader));
                            break;
                        case FLOWERING_DAYS_COUNT:
                            OneYearFlower flower1 = (OneYearFlower) flower;
                            flower1.setFloweringDaysCount(Integer.valueOf(getXMLText(reader)));
                            break;
                        case AGE_YEARS:
                            MultiYearFlower flower2 = (MultiYearFlower) flower;
                            flower2.setAgeYears(Integer.valueOf(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FlowerEnum.valueOf(name.toUpperCase().replace('-', '_')) == FlowerEnum.ONE_YEAR_FLOWER
                            || FlowerEnum.valueOf(name.toUpperCase().replace('-', '_')) == FlowerEnum.MULTI_YEAR_FLOWER) {
                        return flower;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag" + reader.getLocalName());
    }

    private VisualParameters getXMLVisualParameters(XMLStreamReader reader) throws XMLStreamException {
        VisualParameters visualParameters = new VisualParameters();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerEnum.valueOf(name.toUpperCase().replace('-', '_'))) {
                        case LEAF_COLOR:
                            visualParameters.setLeafColor(getXMLText(reader));
                            break;
                        case FLOWER_COLOR:
                            visualParameters.setFlowerColor(getXMLText(reader));
                            break;
                        case AVERAGE_SIZE_CM:
                            visualParameters.setAverageSizeCm(Integer.valueOf(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FlowerEnum.valueOf(name.toUpperCase().replace('-', '_')) == FlowerEnum.VISUAL_PARAMETERS) {
                        return visualParameters;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag VisualParameters");
    }

    private GrowingTips getXMLGrowingTips(XMLStreamReader reader) throws XMLStreamException {
        GrowingTips growingTips = new GrowingTips();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerEnum.valueOf(name.toUpperCase().replace('-', '_'))) {
                        case REQUIRED_TEMPERATURE:
                            growingTips.setRequiredTemperature(Integer.valueOf(getXMLText(reader)));
                            break;
                        case LIGHTENING:
                            growingTips.setLightening(getXMLText(reader));
                            break;
                        case WEEK_WATERING_ML:
                            growingTips.setWeekWateringMl(Integer.valueOf(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FlowerEnum.valueOf(name.toUpperCase().replace('-', '_')) == FlowerEnum.GROWING_TIPS) {
                        return growingTips;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag GrowingTips");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

}


