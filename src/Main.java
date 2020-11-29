import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) {

		ArrayList<Coche> coches = new ArrayList<>();
		Coche c1 = new Coche("16553", "negro", 2);
		Coche c2 = new Coche("3456", "rojo", 4);
		coches.add(c1);
		coches.add(c2);
		try {
			// Crear un fichero XML
			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = fact.newDocumentBuilder();

			DOMImplementation impl = builder.getDOMImplementation();

			// Establecer raiz
			Document doc = impl.createDocument(null, "Concesionario", null);

			doc.setXmlVersion("1.0");
			//añadimos los elementos al array coches,pasamos x parametro la raiz,el nombre del elemento y c que es el contenido
			for (Coche c : coches) {
				aniadirElementos(doc, "coche", c);
			}
			//Cerrar fichero XML
			Source source = new DOMSource(doc);
			Result result = new StreamResult(new File("concesionario.xml"));
			Transformer concecionario = TransformerFactory.newInstance().newTransformer();
			concecionario.transform(source, result);
			
			System.out.println("Fin de programa");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Imprimir coches");
		for (Coche c : coches) {
			System.out.println(c);
		}
		leerFichero("concesionario.xml", "coche");

	}

	public static void aniadirElementos(Document doc, String coche, Coche c) {
		// Crear los nodos
		Element elemento = null;
		Text text = null;
		Element raiz = null;

		raiz = doc.createElement(coche);

		elemento = doc.createElement("matricula");
		text = doc.createTextNode(c.getMatricula());
		raiz.appendChild(elemento);
		elemento.appendChild(text);

		elemento = doc.createElement("color");
		text = doc.createTextNode(c.getColor());
		raiz.appendChild(elemento);
		elemento.appendChild(text);

		elemento = doc.createElement("puertas");
		text = doc.createTextNode(c.getNumPuertas() + "");
		raiz.appendChild(elemento);
		elemento.appendChild(text);

		doc.getDocumentElement().appendChild(raiz);

	}

	public static void leerFichero(String fichero, String elemento) {

		try {
			// Creo una instancia de DocumentBuilderFactory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// Creo un documentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();

			// Obtengo el documento, a partir del XML
			Document documento = builder.parse(new File(fichero));

			// Cojo todas las etiquetas coche del documento
			NodeList listaCoches = documento.getElementsByTagName(elemento);

			// Recorro las etiquetas
			for (int i = 0; i < listaCoches.getLength(); i++) {
				// Cojo el nodo actual coche
				Node nodo = listaCoches.item(i);
				// Compruebo si el nodo es un elemento,si tiene datos
				 if (nodo.getNodeType() == Node.ELEMENT_NODE) {
	                    // Lo transformo a Element
	                    Element e = (Element) nodo;
	                    // Obtengo sus hijos
	                    NodeList hijos = e.getChildNodes();
	                    // Recorro sus hijos
	                    for (int j = 0; j < hijos.getLength(); j++) {
	                        // Obtengo al hijo actual
	                        Node hijo = hijos.item(j);
	                        // Compruebo si es un nodo
	                        if (hijo.getNodeType() == Node.ELEMENT_NODE) {
	                            // Muestro el contenido---getNodeName()-->nombre etiqueta
	                            System.out.println("Propiedad: " + hijo.getNodeName() + ", Valor: " + hijo.getTextContent());
	                        }

	                    }
	                    System.out.println("");
	                }

			}

		} catch (ParserConfigurationException | SAXException | IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
