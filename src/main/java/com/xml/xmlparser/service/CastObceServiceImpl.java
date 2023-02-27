package com.xml.xmlparser.service;

import com.xml.xmlparser.model.CastObce;
import com.xml.xmlparser.repository.CastObceRepository;
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
public class CastObceServiceImpl implements CastObceService {

    @Autowired
    private CastObceRepository castObceRepository;

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
            String expression = "/vf:VymennyFormat/vf:Data/vf:CastiObci/vf:CastObce";
            NodeList list = (NodeList) xpath.compile(expression).evaluate(doc, XPathConstants.NODESET);

            doc.getDocumentElement().normalize();

            for (int i = 0; i < list.getLength(); ++i) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element tElement = (Element)node;
                    CastObce castObce = new CastObce();
                    int tempId = Integer.parseInt(tElement.getElementsByTagName("coi:Kod")
                            .item(0)
                            .getTextContent());
                    String tempName = tElement.getElementsByTagName("coi:Nazev")
                            .item(0)
                            .getTextContent();
                   int tempKod = Integer.parseInt(tElement.getElementsByTagName("obi:Kod")
                          .item(0)
                           .getTextContent());

                    castObce.setId(tempId);
                    castObce.setNazev(tempName);
                    castObce.setKodObce(tempKod);

                    castObceRepository.save(castObce);

                }
            }

        }

        catch (Exception e) {
            System.out.println(e);
        }


    }
}
