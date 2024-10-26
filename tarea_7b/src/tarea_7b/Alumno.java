package tarea_7b;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * @author Alberto Polo
 */

public class Alumno extends tarea_7 implements Serializable  {

	private static final long serialVersionUID = 2180170612359928850L;

	// Constante para indicar el número de alumnos
	private static final int NUMERO_DE_ALUMNOS = 5;

	// Scanner declarado como static para no tener que cerrarlo:
	private static Scanner sc = new Scanner(System.in);

	// Atributos privados de la clase Alumno
	private int nia = 0;
	private String nombre;
	private String apellidos;
	private char genero = 'S';
	private Date fechaNacimiento;
	private String ciclo;
	private String curso;
	private String grupo;

	// Constructores de la clase Alumno
	public Alumno() {
	}

	public Alumno(int nia, String nombre, String apellidos, char genero, Date fechaNacimiento, String ciclo,
			String curso, String grupo) {

		this.nia = nia;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.genero = genero;
		this.fechaNacimiento = fechaNacimiento;
		this.ciclo = ciclo;
		this.curso = curso;
		this.grupo = grupo;
	}

	// Getters & Setters:
	public int getNia() {
		return nia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	@Override
	public String toString() {
		return "NIA: " + nia + " --> " + nombre + ", " + apellidos + " - Género: " + genero + " - Fecha de Nacimiento: "
				+ fechaNacimiento + " - Ciclo: " + ciclo + " - Curso: " + curso + " Grupo: " + grupo;
	}

	/**
	 * @author Alberto Polo El método recoge los datos de la cantidad especificada
	 *         en la constante NUMERO_DE_ALUMNOS y los va añadiendo a una lista de
	 *         objetos de tipo Alumno.
	 * @return Devuelve una lista de Objetos de tipo Alumno.
	 */
	public List<Alumno> leeAlumnos() {
		List<Alumno> listaAlumnos = new ArrayList<>();

		for (int i = 0; i < NUMERO_DE_ALUMNOS; i++) {

			// NIA - int
			// Vamos añadiendo el nia secuencialmente incrementándolo en 1 unidad:
			nia = i + 1;

			// NOMBRE - String
			System.out.print("Introduzca el NOMBRE del alumno " + (i + 1) + ": ");
			nombre = sc.nextLine().toUpperCase();

			// APELLIDOS - String
			System.out.print("Introduzca los APELLIDOS del alumno " + (i + 1) + ": ");
			apellidos = sc.nextLine().toUpperCase();

			// GÉNERO - char

			do {
				System.out.print("Introduzca el GÉNERO del alumno(H/M): ");
				String entradaTeclado = sc.nextLine().toUpperCase();

				if (entradaTeclado.length() > 0) {
					genero = entradaTeclado.charAt(0);
				} else {
					genero = ' ';
				}

			} while (genero != 'H' && genero != 'M');

			// FECHA DE NACIMIENTO - Date
			System.out.print("Introduzca la FECHA DE NACIMIENTO del alumno en formato (dd/MM/yyyy): ");
			String fechaNacimientoString = sc.nextLine();
			fechaNacimiento = convierteStringEnDate(fechaNacimientoString);

			// CICLO - String
			System.out.print("Introduzca el CICLO del alumno " + (i + 1) + ": ");
			ciclo = sc.nextLine().toUpperCase();

			// CURSO - String
			System.out.print("Introduzca el CURSO del alumno " + (i + 1) + ": ");
			curso = sc.nextLine().toUpperCase();

			// GRUPO - String
			System.out.print("Introduzca el GRUPO del alumno " + (i + 1) + ": ");
			grupo = sc.nextLine().toUpperCase();
			System.out.println("----------------------------------------------------------------------");

			// Creo un nuevo objeto Alumno y lo añado a la lista:
			Alumno alumno = new Alumno(nia, nombre, apellidos, genero, fechaNacimiento, ciclo, curso, grupo);
			listaAlumnos.add(alumno);
		}

		return listaAlumnos;
	}

	/**
	 * @author Alberto Polo
	 * @param Recibe fechaDate como fecha en formato java.util.Date
	 * @return Devuelve la fecha introducida como parámetro transformada en String
	 */
	public String convierteDateEnString(Date fechaDate) {
		// Definimos el formato de fecha:
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

		// Convertimos el objeto Date a String:
		String fechaString = formatoFecha.format(fechaDate);

		return fechaString;
	}

	/**
	 * @author Alberto Polo
	 * @param Recibe fechaString como fecha en formato String
	 * @return Devuelve la fecha introducida como parámetro transformada en
	 *         java.util.Date
	 */
	public Date convierteStringEnDate(String fechaString) {
		// Definir formato de fecha según el String:
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		Date fechaDate = new Date();
		try {
			// Convertir el String en Date;
			fechaDate = formato.parse(fechaString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fechaDate;
	}

	/**
	 * @author Alberto Polo
	 * @param listaAlumnos recibe una lista de Objetos de tipo Alumno que se
	 *                     utilizará para generar el fichero Alumnos.XML
	 * 
	 */

	public void generaXML(List<Alumno> listaAlumnos) {

		// Creamos la factoría para generar documentos XML:
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();

			// Creamos el documento XML vacio:
			Document document = implementation.createDocument(null, "Alumnos", null);
			document.setXmlVersion("1.0");

			for (Alumno alumno : listaAlumnos) {
				// Creamos el nodo alumno:
				Element nodoAlumno = document.createElement("alumno");
				// Agregamos el nodo a la raíz:
				document.getDocumentElement().appendChild(nodoAlumno);
				// Creamos elementos hijo del nodo alumno mediante función
				// CrearElementoAlumnoEtiquetas():
				crearElementoAlumnoEtiquetas("nia", Integer.toString(alumno.getNia()), nodoAlumno, document);
				crearElementoAlumnoEtiquetas("nombre", alumno.getNombre(), nodoAlumno, document);
				crearElementoAlumnoEtiquetas("apellidos", alumno.getApellidos(), nodoAlumno, document);
				crearElementoAlumnoEtiquetas("genero", Character.toString(alumno.getGenero()), nodoAlumno, document);
				crearElementoAlumnoEtiquetas("fecha_de_nacimiento", convierteDateEnString(alumno.getFechaNacimiento()),
						nodoAlumno, document);
				crearElementoAlumnoEtiquetas("ciclo", alumno.getCiclo(), nodoAlumno, document);
				crearElementoAlumnoEtiquetas("curso", alumno.getCurso(), nodoAlumno, document);
				crearElementoAlumnoEtiquetas("grupo", alumno.getGrupo(), nodoAlumno, document);
			}

			// Generamos el fichero XML a partir del documento creado:
			Source source = new DOMSource(document);
			Result result = new StreamResult(new File("Alumnos.xml"));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);

		} catch (ParserConfigurationException | TransformerException | TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author Alberto Polo
	 * @param datoAlumno recibe el nombre que tendrá la etiqueta del nodo
	 * @param valor      recibe el contenido que tendrá el nodo
	 * @param alumno     recibe el nodo alumno
	 * @param documento  recibe el documento XML
	 */
	private static void crearElementoAlumnoEtiquetas(String datoAlumno, String valor, Element alumno,
			Document documento) {

		Element elem = documento.createElement(datoAlumno);
		Text text = documento.createTextNode(valor);
		elem.appendChild(text);
		alumno.appendChild(elem);
	}
}