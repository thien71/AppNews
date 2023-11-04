package com.example.appnews;

import android.util.Log;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
public class XMLDOMParser {
    public Document getDocument(String xml) {
        Document document = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = factory.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            is.setEncoding("UTF-8");
            document = db.parse(is);
        } catch (ParserConfigurationException e) {
            Log.e("Error: ", e.getMessage(), e);
            return null;
        } catch (IOException e) {
            Log.e("Error: ", e.getMessage(), e);
            return null;
        } catch (SAXException e) {
            Log.e("Error: ", e.getMessage(), e);
            return null;
        }
        return document;
    }

    public String getValue(Element item, String name) {
        NodeList nodes = item.getElementsByTagName(name);

        // Kiểm tra xem name có phải là "description" không
        if ("description".equals(name)) {
            return this.getDescriptionValue(item);
        }

        return this.getTextNodeValue(nodes.item(0));
    }

    private final String getTextNodeValue(Node elem) {
        Node child;
        if (elem != null) {
            if (elem.hasChildNodes()) {
                for (child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
                    if (child.getNodeType() == Node.TEXT_NODE) {
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }

    private String getDescriptionValue(Element item) {
        NodeList nodeList = item.getElementsByTagName("description");
        if (nodeList.getLength() > 0) {
            Element descriptionElement = (Element) nodeList.item(0);
            // Lấy nội dung của thẻ <description>
            String description = descriptionElement.getTextContent();

            // Thực hiện trích xuất URL hình ảnh từ nội dung description
            String imageUrl = extractImageUrlFromDescription(description);

            return imageUrl;
        }

        return "";
    }

    private String extractImageUrlFromDescription(String description) {
        String imageUrl = "";
        if (description != null && !description.isEmpty()) {
            int startIndex = description.indexOf("<img src=\"");
            if (startIndex != -1) {
                int endIndex = description.indexOf("\" ></a>", startIndex + 10);
                if (endIndex != -1) {
                    imageUrl = description.substring(startIndex + 10, endIndex);
                }
            }
        }
        return imageUrl;
    }
}