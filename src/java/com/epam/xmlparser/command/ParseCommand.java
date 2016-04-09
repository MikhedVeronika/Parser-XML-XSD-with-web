package com.epam.xmlparser.command;

import com.epam.xmlparser.entity.Flower;
import com.epam.xmlparser.entity.MultiYearFlower;
import com.epam.xmlparser.entity.OneYearFlower;
import com.epam.xmlparser.manager.ConfigurationManager;
import com.epam.xmlparser.parser.AbstractFlowersBuilder;
import com.epam.xmlparser.parser.FlowerBuilderFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ParseCommand implements Command{

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FlowerBuilderFactory flowerBuilderFactory = new FlowerBuilderFactory();
        String page;

        String parser = request.getParameter("parser");
        String res;
        if (parser.equals("DOM")) {
            res = "DOM";
        } else if (parser.equals("SAX")) {
            res = "SAX";
        } else if (parser.equals("StAX")) {
            res = "StAX";
        } else {
            res = "no parser";
        }
        res = res.toUpperCase();
        request.setAttribute("res", res);
        if (res.equals("DOM") || res.equals("SAX") || res.equals("STAX")) {
            AbstractFlowersBuilder builder = flowerBuilderFactory.createFlowerBuilder(res);
            String path = "data/flowers.xml";
            builder.buildSetFlowers(path);
            Set<Flower> flowers = builder.getFlowers();
            Set<OneYearFlower> oneYearFlowerSet = new HashSet<>();
            Set<MultiYearFlower> multiYearFlowerSet = new HashSet<>();
            for (Flower f: flowers) {
                if (f instanceof OneYearFlower) {
                    oneYearFlowerSet.add((OneYearFlower) f);
                }
                else if (f instanceof MultiYearFlower) {
                    multiYearFlowerSet.add((MultiYearFlower)f);
                }
            }
            request.setAttribute("oneYearFlowers", oneYearFlowerSet);
            request.setAttribute("multiYearFlowers", multiYearFlowerSet);
        }
        page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.RESULTS_PAGE_PATH);
        return page;
    }
}
