package com.xml.xmlparser.service;

import com.xml.xmlparser.model.Obec;
import com.xml.xmlparser.repository.ObecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.Iterator;

@Service
public class ObecServiceImpl implements ObecService {

    @Autowired
    private ObecRepository obecRepository;

    @Override
    public void saveObec() {
        File file = new File("src/main/resources/data.xml");
        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            XPath xpath = XPathFactory.newInstance().newXPath();
            xpath.setNamespaceContext(new NamespaceContext() {
                @Override
                public String getNamespaceURI(String prefix) {
                    if ("vf".equals(prefix)) {
                        return "urn:cz:isvs:ruian:schemas:VymennyFormatTypy:v1";
                    }
                    if ("obi".equals(prefix)) {
                        return "urn:cz:isvs:ruian:schemas:ObecIntTypy:v1";
                    }
                    return null;
                }

                @Override
                public String getPrefix(String namespaceURI) {
                    return null;
                }

                @Override
                public Iterator<String> getPrefixes(String namespaceURI) {
                    return null;
                }
            });
            String expression = "/vf:VymennyFormat/vf:Data/vf:Obce/vf:Obec";
            NodeList list = (NodeList) xpath.compile(expression).evaluate(doc, XPathConstants.NODESET);

            doc.getDocumentElement().normalize();

            for (int i = 0; i < list.getLength(); ++i) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element tElement = (Element)node;
                    Obec obec = new Obec();
                    int tempId = Integer.parseInt(tElement.getElementsByTagName("obi:Kod")
                            .item(0)
                            .getTextContent());
                    String tempName = tElement.getElementsByTagName("obi:Nazev")
                            .item(0)
                            .getTextContent();
                    System.out.println("id " + tempId);
                    System.out.println("name " + tempName);
                    obec.setObecKod(tempId);
                    obec.setNazev(tempName);

                    obecRepository.save(obec);

                }
            }

        }

        catch (Exception e) {
            System.out.println(e);
        }


    }

}
