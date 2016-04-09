package com.epam.xmlparser.parser;

import com.epam.xmlparser.characteristics.GrowingTips;
import com.epam.xmlparser.characteristics.VisualParameters;
import com.epam.xmlparser.entity.Flower;
import com.epam.xmlparser.entity.MultiYearFlower;
import com.epam.xmlparser.entity.OneYearFlower;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class FlowerHandler extends DefaultHandler {
    private Set<Flower> flowers;
    private Flower current;
    private FlowerEnum currentEnum;
    private EnumSet<FlowerEnum> withText;

    public FlowerHandler() {
        flowers = new HashSet<>();
        withText = EnumSet.range(FlowerEnum.NAME, FlowerEnum.AGE_YEARS);
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if ("one-year-flower".equals(localName)) {
            current = new OneYearFlower();
            current.setVisualParameters(new VisualParameters());
            current.setGrowingTips(new GrowingTips());
            current.setId(attrs.getValue(0));

        } else if ("multi-year-flower".equals(localName)) {
            current = new MultiYearFlower();
            current.setVisualParameters(new VisualParameters());
            current.setGrowingTips(new GrowingTips());
            current.setId(attrs.getValue(0));
        } else {
            FlowerEnum temp = FlowerEnum.valueOf(localName.toUpperCase().replace('-', '_'));
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if ("one-year-flower".equals(localName) || "multi-year-flower".equals(localName)) {
            flowers.add(current);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case NAME:
                    current.setName(s);
                    break;
                case SOIL:
                    current.setSoil(s);
                    break;
                case ORIGIN:
                    current.setOrigin(s);
                    break;
                case LEAF_COLOR:
                    current.getVisualParameters().setLeafColor(s);
                    break;
                case FLOWER_COLOR:
                    current.getVisualParameters().setFlowerColor(s);
                    break;
                case AVERAGE_SIZE_CM:
                    current.getVisualParameters().setAverageSizeCm(Integer.valueOf(s));
                    break;
                case REQUIRED_TEMPERATURE:
                    current.getGrowingTips().setRequiredTemperature(Integer.valueOf(s));
                    break;
                case LIGHTENING:
                    current.getGrowingTips().setLightening(s);
                    break;
                case WEEK_WATERING_ML:
                    current.getGrowingTips().setWeekWateringMl(Integer.valueOf(s));
                    break;
                case MULTIPLYING:
                    current.setMultiplying(s);
                    break;
                case FLOWERING_DAYS_COUNT:
                    OneYearFlower flower1 = (OneYearFlower) current;
                    flower1.setFloweringDaysCount(Integer.valueOf(s));
                    current = flower1;
                    break;
                case AGE_YEARS:
                    MultiYearFlower flower2 = (MultiYearFlower) current;
                    flower2.setAgeYears(Integer.valueOf(s));
                    current = flower2;
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }
}
