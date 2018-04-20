/**
 *  Resumen				
 *  Objeto		       		: G.java
 *  Descripcion		  		: 
 *  Fecha de Creacion   	: 17/01/2018.
 *  PE de CreaciÃ³n 			: EMISIONCERTIFICADOS.
 *  Autor			   		: GMD.
 *  -------------------------------------------------------------------------------------
 *  Modificaciones
 *  Motivo	            Fecha          Nombre         Descripcion
 *  -------------------------------------------------------------------------------------
 *
 */
package utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class Utilitario {

	public class Constantes {
		public static final String K_ENE = "ENERO";
		public static final String K_FEB = "FEBRERO";
		public static final String K_MAR = "MARZO";
		public static final String K_ABR = "ABRIL";
		public static final String K_MAY = "MAYO";
		public static final String K_JUN = "JUNIO";
		public static final String K_JUL = "JULIO";
		public static final String K_AGO = "AGOSTO";
		public static final String K_SEP = "SEPTIEMBRE";
		public static final String K_OCT = "OCTUBRE";
		public static final String K_NOV = "NOVIEMBRE";
		public static final String K_DIC = "DICIEMBRE";
		public static final int ORIGEN_DATOS=1;
		public static final String UPLOAD_FOLDER = "F:\\Cibertec 6to Ciclo\\Proyecto Integrador II\\Proyecto_Comparabien\\pit_comparabien\\uploads";
	}
	
	public static ArrayList<ArrayList<String>> armarSalidaProcedure(List<Map<String, Object>> lista) {
		ArrayList<ArrayList<String>> retorno = new ArrayList<ArrayList<String>>();		
		for (Map<String, Object> mapa : lista) {
			ArrayList<String> hijo = new ArrayList<String>();
			for(Map.Entry<String, Object> entry : mapa.entrySet()) {
			    Object v = entry.getValue();
			    hijo.add((v!=null)?v.toString().trim():"");
			}	
			retorno.add(hijo);
		}
		return retorno;
	}
	

	/**
	 * Para poder probar las funciones utilitarias.
	 * 
	 * @param
	 * @param
	 * @return
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
	}

	/**
	 * Recorta una cadena desde el lado izquierdo
	 * 
	 * @param cadena
	 * @param longitud
	 * @return
	 */
	public static String left(String cadena, int longitud) {
		String result = "";

		if (longitud > cadena.length()) {
			result = cadena;
		} else {
			result = cadena.substring(0, longitud);
		}

		return result;
	}

	/**
	 * Recorta una cadena desde el lado derecho
	 * 
	 * @param cadena
	 * @param longitud
	 * @return
	 */
	public static String right(String cadena, int longitud) {
		String result = "";

		if (longitud > cadena.length()) {
			result = cadena;
		} else {
			result = cadena.substring(cadena.length() - longitud);
		}
		return result;
	}

	/**
	 * Permite extraer una subcadena de una cadena principal dado un valor inicial y
	 * una longitud
	 * 
	 * @param cadena
	 * @param inicio
	 * @return
	 */
	public static String mid(String cadena, int inicio) {
		String result = "";

		if (inicio > cadena.length()) {
			result = "";
		} else {
			inicio = inicio - 1;
			result = cadena.substring(inicio);
		}
		return result;
	}

	/**
	 * Permite extraer una subcadena de una cadena principal dado un valor inicial y
	 * una longitud
	 * 
	 * @param cadena
	 * @param inicio
	 * @param longitud
	 * @return
	 */
	public static String mid(String cadena, int inicio, int longitud) {
		String result = "";

		if (inicio > cadena.length()) {
			result = "";
		} else {
			inicio = inicio - 1;
			if (inicio + longitud > cadena.length()) {
				result = cadena.substring(inicio, cadena.length());
			} else {
				result = cadena.substring(inicio, inicio + longitud);
			}
		}

		return result;
	}

	/**
	 * Convierte una cadena de caracteres a may\u00fascula
	 * 
	 * @param cadena
	 * @return
	 */
	public static String uCase(String cadena) {
		return cadena.toUpperCase();
	}

	/**
	 * Convierte una cadena de caracteres a min\u00fasculas
	 * 
	 * @param cadena
	 * @return
	 */
	public static String lCase(String cadena) {
		return cadena.toLowerCase();
	}

	/**
	 * Devuelve un valor num\u00e9rico que especifica la posici\u00f3n de la primera
	 * aparici\u00f3n de una cadena en otra, desde el principio de la cadena
	 * 
	 * @param cadena
	 * @param cadenaBuscada
	 * @return
	 */
	public static int inStr(String cadena, String cadenaBuscada) {
		int posicion = cadena.indexOf(cadenaBuscada);
		if (posicion == -1) {
			return 0;
		} else {
			return posicion + 1;
		}
	}

	/**
	 * Devuelve un valor num\u00e9rico que especifica la posici\u00f3n de la primera
	 * aparici\u00f3n de una cadena en otra, desde el inicio de la cadena
	 * 
	 * @param inicio
	 * @param cadena
	 * @param cadenaBuscada
	 * @return
	 */
	public static int inStr(int inicio, String cadena, String cadenaBuscada) {
		int posicion = cadena.indexOf(cadenaBuscada, inicio - 1);
		if (posicion == -1) {
			return 0;
		} else {
			return posicion + 1;
		}
	}

	/**
	 * Devuelve una cadena correspondiente al c\u00f3digo ANSI o DBCS especificado
	 * como argumento.
	 * 
	 * @param numero
	 * @return
	 */
	public static char chr(int numero) {
		return (char) numero;
	}

	/**
	 * Devuelve la longitud de una cadena de caracteres
	 * 
	 * @param cadena
	 * @return
	 */
	public static int len(String cadena) {
		return cadena.length();
	}

	/**
	 * Repite una cadena de caracteres n (cantidad) veces
	 * 
	 * @param cantidad
	 * @param cadenaRepetir
	 * @return
	 */
	public static String string(int cantidad, String cadenaRepetir) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < cantidad; i++) {
			sb.append(cadenaRepetir);
		}

		return sb.toString();
	}

	/**
	 * Repite una cadena de caracteres n (cantidad) veces
	 * 
	 * @param cantidad
	 * @param cadenaRepetir
	 * @return
	 */
	public static String string(int cantidad, char cadenaRepetir) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < cantidad; i++) {
			sb.append(cadenaRepetir);
		}

		return sb.toString();
	}

	/**
	 * Formatear Rut de Cliente Ejemplo: 007.976.042-0
	 * 
	 * @param numRut
	 * @return
	 */
	public static String formateaRut(String numRut) {

		int ind;
		String rutAux;
		String digVer;

		rutAux = Utilitario.trim(numRut);
		ind = Utilitario.len(rutAux);

		if (ind > 1) {
			digVer = Utilitario.uCase(Utilitario.right(rutAux, 1));
			ind = ind - 1;
			rutAux = Utilitario.left(rutAux, ind);
			if (ind < 10) {
				rutAux = Utilitario.string(9 - ind, "0") + rutAux;
			}
			return Utilitario.formatearFolioRut(rutAux) + "-" + digVer;
		} else {
			return rutAux;
		}
	}

	/**
	 * Formatear Rut de Cliente Ejemplo: 7.976.042-0 o 007.976.042-0
	 * 
	 * @param numRut
	 * @param completarceros
	 * @return
	 */
	public static String formateaRut(String numRut, boolean completarceros) {

		int ind;
		String rutAux;
		String digVer;

		rutAux = Utilitario.trim(numRut);
		ind = Utilitario.len(rutAux);

		if (ind > 1) {
			digVer = Utilitario.uCase(Utilitario.right(rutAux, 1));
			ind = ind - 1;
			rutAux = Utilitario.left(rutAux, ind);
			if (ind < 10) {
				rutAux = Utilitario.string(9 - ind, "0") + rutAux;
			}

			String folioFormateado = "";
			int digitos = 0;

			if (rutAux.length() < 9) {
				rutAux = StringUtils.leftPad(rutAux, 9, "0");
			}

			if (!completarceros) {
				rutAux = rutAux.replaceFirst("^0*", "");
				;
			}

			for (int i = rutAux.length() - 1; i >= 0; i--) {
				if (digitos == 3) {
					folioFormateado = "." + folioFormateado;
					digitos = 0;
				}
				folioFormateado = rutAux.charAt(i) + folioFormateado;
				digitos++;
			}
			return folioFormateado + "-" + digVer;
		} else {
			return rutAux;
		}
	}

	/**
	 * Formatea folio del Rut. Ejemplo: 007.976.042
	 * 
	 * @param folio
	 * @return
	 */
	public static String formatearFolioRut(String folio) {
		String folioFormateado = "";
		int digitos = 0;

		if (folio.length() < 9) {
			folio = StringUtils.leftPad(folio, 9, "0");
		}

		for (int i = 0; i < folio.length(); i++) {
			if (digitos == 3) {
				folioFormateado += ".";
				digitos = 0;
			}
			folioFormateado += folio.charAt(i);
			digitos++;
		}
		return folioFormateado;
	}

	/**
	 * Formatea folio del Rut. Ejemplo: 7.976.042
	 * 
	 * @param folio
	 * @return
	 */
	public static String formatearFolioRutSinCompletar(String folio) {
		String folioFormateado = "";
		int digitos = 0;

		for (int i = folio.length() - 1; i >= 0; i--) {
			if (digitos == 3) {
				folioFormateado = "." + folioFormateado;
				digitos = 0;
			}
			folioFormateado = folio.charAt(i) + folioFormateado;
			digitos++;
		}
		return folioFormateado;
	}

	/**
	 * Devuelve verdadero si la expresi\u00f3n es evaluado como un n\u00famero
	 * 
	 * @param cadena
	 * @return
	 */
	public static boolean isNumeric(String cadena) {
		try {
			Double.parseDouble(cadena);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	/**
	 * Quita espacios en blanco al inicio y fin de una cadena de caracteres
	 * 
	 * @param cadena
	 * @return
	 */
	public static String trim(String cadena) {
		if (!StringUtils.isEmpty(cadena)) {
			return cadena.trim();
		} else {
			return "";
		}
	}

	/**
	 * Borra subcadena de la cadena general
	 * 
	 * @param cadena
	 * @param cadenaABorrar
	 * @return
	 */
	public static String mBorrarCadena(String cadena, String cadenaABorrar) {
		String mBorrarCadena;
		int i;
		int j;
		String rT;
		String part;

		rT = "";
		j = 1;
		i = inStr(1, cadena, cadenaABorrar);
		if (i == 0) {
			mBorrarCadena = cadena;
		} else {
			while (i != 0) {
				part = mid(cadena, j, i - j);
				rT = rT + part;
				j = i + len(cadenaABorrar);
				i = inStr(j, cadena, cadenaABorrar);
			}
			mBorrarCadena = rT + mid(cadena, j);
		}

		return mBorrarCadena;
	}

	/**
	 * Devuelve la representaciï¿½n String de un nï¿½mero.
	 * 
	 * @param numero
	 * @return
	 */
	public static String str(int numero) {
		return String.valueOf(numero);
	}

	/**
	 * Devuelve la representaciï¿½n String de un nï¿½mero.
	 * 
	 * @param numero
	 * @return
	 */
	public static String str(double numero) {
		return String.valueOf(numero);
	}

	// Utilitarios Lib

	/**
	 * Desformatea el rut quitando los caracteres especiales
	 * 
	 * @param iRut
	 * @return
	 */
	public static String mDesformatearRut(String iRut) {
		String aRut = Utilitario.trim(iRut);
		aRut = Utilitario.mBorrarCadena(aRut, ".");
		aRut = Utilitario.mBorrarCadena(aRut, "-");

		return aRut;
	}

	/**
	 * Valida si el rut es correcto
	 * 
	 * @param true
	 *            si es correcto false caso contrario
	 * @return
	 */
	public static boolean fValidaRut(String sRut) {
		boolean fValidaRut = true;

		int lar;
		int ind;
		long digit;
		String rutAux;
		String digVer;
		String digCal;
		String fac;
		long suma;

		rutAux = sRut;
		lar = Utilitario.len(rutAux);
		if (lar < 2) {
			fValidaRut = false;
		} else {
			digVer = Utilitario.uCase(Utilitario.mid(rutAux, lar, 1));
			rutAux = Utilitario.left(rutAux, lar - 1);

			lar = Utilitario.len(rutAux);
			if (lar < 9) {
				rutAux = Utilitario.left("000000000", 9 - lar) + rutAux;
			}

			fac = "432765432";
			suma = 0;
			ind = 9;

			while (ind > 0) {
				suma = suma
						+ Long.parseLong(Utilitario.mid(rutAux, ind, 1)) * Long.parseLong(Utilitario.mid(fac, ind, 1));
				ind = ind - 1;
			}

			int div = (int) suma / 11;
			digit = 11 - (suma - div * 11);

			if (digit == 10) {
				digCal = "K";
			} else if (digit == 11) {
				digCal = "0";
			} else {
				digCal = Utilitario.trim(Utilitario.str(digit));
			}

			if (!digVer.equals(digCal)) {
				fValidaRut = false;
			} else {
				fValidaRut = true;
			}
		}

		return fValidaRut;

	}

	/**
	 * Devuelve la cadena con Formato Fecha DE: 20151010 A "10/10/2015".
	 * 
	 * @param cadena
	 * @return
	 */
	public static String formatoFecha(String cadena) {
		String fechafinal = "";
		cadena = (cadena.trim());

		if (cadena.length() == 8) {
			String dia = cadena.substring(6, 8);
			String mes = cadena.substring(4, 6);
			String anio = cadena.substring(0, 4);
			fechafinal = dia + "/" + mes + "/" + anio;

		} else if (cadena.length() != 8) {
			fechafinal = "00/00/0000";
		}
		return fechafinal;
	}

	/**
	 * Devuelve la fecha como cadena "10/10/2015"
	 * 
	 * @param cadena
	 * @return
	 */
	public static String formatearFecha(String fecha) {
		String dia = "00";
		String mes = "00";
		String anio = "0000";
		String fechaFormato = null;
		if (fecha.length() >= 10) {
			dia = Utilitario.mid(fecha, 1, 2);
			mes = Utilitario.mid(fecha, 4, 2);
			anio = Utilitario.mid(fecha, 7, 4);
		}
		fechaFormato = dia + "/" + mes + "/" + anio;
		return fechaFormato;
	}

	/**
	 * Devuelve la fecha como simple cadena DE "10/10/2015" A 20151010 .
	 * 
	 * @param cadena
	 * @return
	 */
	public static String desFormatearFecha(String fecha) {
		String dia = "00";
		String mes = "00";
		String anio = "0000";
		String fechaFormato = null;
		if (fecha.length() == 10) {
			dia = Utilitario.mid(fecha, 1, 2);
			mes = Utilitario.mid(fecha, 4, 2);
			anio = Utilitario.mid(fecha, 7, 4);
		}
		fechaFormato = anio + mes + dia;
		return fechaFormato;
	}

	/**
	 * Formatea fecha y hora (para insertar a BD) DE: "03/09/2016 19:37" A:
	 * "20160903 19:37"
	 * 
	 * @param fecha
	 * @return
	 */
	public static String desformatearFechaConHora(String fecha) {
		String cadena = "";
		String[] f = fecha.split(" ");
		f[0] = Utilitario.desFormatearFecha(f[0]);
		cadena = f[0] + " " + f[1];
		return cadena;
	}

	/**
	 * Devuelve la cadena con Formato Hora DE: 044652 A "04:45:52".
	 * 
	 * @param cadena
	 * @return
	 */
	public static String formatoHora(String cadena) {

		String hora;
		String minutos;
		String segundos;
		String horaFormato = null;

		hora = cadena.substring(0, 2);
		minutos = cadena.substring(2, 4);
		segundos = cadena.substring(4, 6);

		horaFormato = hora + ":" + minutos + ":" + segundos;
		return horaFormato;

	}

	/**
	 * Invierte d\u00eda y mes de una fecha devuelta desde la BD. DE: 09/03/1989 A:
	 * 03/09/1989.
	 * 
	 * @param cadena
	 * @return
	 */
	public static String invierteMesDia(String fecha) {
		String nuevaFecha = "";
		String[] fd = fecha.split("/");
		nuevaFecha = fd[1] + fd[0] + fd[2];
		return nuevaFecha;
	}

	/**
	 * Devuelve Descripcion larga de Estado Civil
	 * 
	 * @param estado
	 * @return
	 */
	public static String formatoEstadoCivil(String estado) {
		if (estado.equals("C")) {
			estado = "CASADO";
		} else if (estado.equals("V")) {
			estado = "VIUDO";
		} else if (estado.equals("S")) {
			estado = "SOLTERO";
		} else if (estado.equals("D")) {
			estado = "DIVORCIADO";
		}
		return estado;
	}

	/**
	 * Devuelve descripcion larga de Sexo
	 * 
	 * @param sexo
	 * @return
	 */
	public static String formatoSexo(String sexo) {
		if (sexo.equals("M")) {
			sexo = "MASCULINO";
		} else if (sexo.equals("F")) {
			sexo = "FEMENINO";
		} else {
			sexo = "AMBOS";
		}

		return sexo;
	}

	/**
	 * Adiciona o recorta espacios a la cadena de acuerdo a la longitud
	 * 
	 * @param cadena
	 * @param longitud
	 * @return
	 */
	public static String rightBlankPad(String cadena, int longitud) {
		String cadenaRepetida = string(longitud, " ");
		cadena = cadena + cadenaRepetida;
		cadena = left(cadena, longitud);
		return cadena;
	}

	/**
	 * Da formato al monto
	 * 
	 * @param monto
	 * @param formato
	 *            pueden ser : "##,###,###,###,##0" "###,###,##0.0000" "##,##0.0000"
	 *            "#,#.0000" "#,#.0#" "###,####,##0" "#,#" "###,##0"
	 *            "##,###,###,###,##0" "###,##0"
	 * @return
	 */
	public static String formatoDecimal(String monto, String formato) {
		if (!monto.equals("null")) {
			double dato = Double.parseDouble(monto);
			DecimalFormat formatoString = new DecimalFormat(formato);
			return formatoString.format(dato);
		} else {
			return "";
		}
	}

	/**
	 * Da formato al monto
	 * 
	 * @param monto
	 * @param formato
	 *            pueden ser : "##,###,###,###,##0" "###,###,##0.0000" "##,##0.0000"
	 *            "#,#.0000" "#,#.0#" "###,####,##0" "#,#" "###,##0"
	 *            "##,###,###,###,##0" "###,##0"
	 * @return
	 */
	public static String formatearMonto(String monto, String formato) {
		if (!StringUtils.isEmpty(monto)) {
			NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMAN);
			DecimalFormat formatter = (DecimalFormat) nf;
			formatter.applyPattern(formato);
			String fString = String.valueOf(formatter.format(new BigDecimal(monto)));
			return fString;
		} else {
			return "";
		}
	}

	/**
	 * Metodo que permite obtener la fecha actual
	 * 
	 * @return fecha actual formateada
	 */
	public static String getFechaActual() {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		return formateador.format(ahora);
	}

	@SuppressWarnings("static-access")
	public static int getDiffYears(Date first, Date last) {
		Calendar a = getCalendar(first);
		Calendar b = getCalendar(last);
		int diff = b.get(b.YEAR) - a.get(a.YEAR);
		if (a.get(a.MONTH) > b.get(b.MONTH) || (a.get(a.MONTH) == b.get(b.MONTH) && a.get(a.DATE) > b.get(b.DATE))) {
			diff--;
		}
		return diff;
	}

	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(date);
		return cal;
	}

	/**
	 * Metodo que permite obtener la hora actual
	 * 
	 * @return fecha actual formateada
	 */
	public static String getHoraActual() {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("hh:mm:ss a");
		return formateador.format(ahora);
	}

	public static boolean isEmpty(String cadena) {
		if (StringUtils.isEmpty(cadena)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * psDato = "1.135.356,56" devuelve "1,135,356.56" si borraComa = true
	 * 1135356.56
	 * 
	 * @return monto desformateado
	 */
	public static String desformatearMonto(String psDato, boolean borraComa) {
		if (Utilitario.isEmpty(psDato)) {
			return "0";
		}

		psDato = psDato.toString();

		psDato = psDato.replace(".", "!");
		psDato = psDato.replace(",", ".");
		psDato = psDato.replace("!", ",");

		if (borraComa == true) {
			psDato = psDato.replace(",", "");
		}

		return psDato;
	}

	/**
	 * Metodo que permite obtener Solo los Numeros de una Cadena de Texto
	 * 
	 * @return Cadena con Solo Numeros
	 */
	public static String val(String cadena) {
		StringBuilder validStr = new StringBuilder();
		boolean seenDot = false;
		boolean seenDigit = false;

		if (Utilitario.isNumeric(cadena)) {
			String cadenaAux = Utilitario.desformatearMonto(cadena, true);
			if (cadenaAux.indexOf("E") == -1 && Integer.parseInt(cadenaAux) == 0) {
				cadena = "0";
			}
		}

		for (int i = 0; i < cadena.length(); i++) {
			char c = cadena.charAt(i);
			if (c == '.' && !seenDot) {
				if (cadena.indexOf("E") == -1) {
					seenDot = true;
					validStr.append(c);
				}
			} else if ((c == '-' || c == '+') && !seenDigit) {
				validStr.append(c);
			} else if (Character.isDigit(c)) {
				seenDigit = true;
				validStr.append(c);
			} else if (Character.isWhitespace(c)) {
				continue;
			} else {
				break;
			}
		}
		return validStr.toString();
	}

	/**
	 * devuelve n spacios en blanco
	 * 
	 * @param n
	 *            numero de espacios en blanco
	 * @return espacios en blanco
	 */
	public static String space(int n) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i < n) {
			sb.append(" ");
			i++;
		}
		return sb.toString();
	}

	/**
	 * Devuelve la representacion Double de un String.
	 * 
	 * @param cadena
	 * @return Double
	 */
	public static Double cdbl(String cadena) {
		return new Double(cadena);
	}

	/**
	 * Metodo que permite obtener el mes anterior a la fecha actual
	 * 
	 * @return mes anterior a la fecha actual.
	 */
	public static String obtenerMesAnterior(int mesesResta) {
		try {
			Calendar fechaHoy = Calendar.getInstance();
			Date fechaAnterior;

			fechaHoy = Calendar.getInstance();
			fechaHoy.add(Calendar.MONTH, mesesResta);
			fechaAnterior = fechaHoy.getTime();

			String mesAnterior;
			DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
			mesAnterior = fecha.format(fechaAnterior);
			return mesAnterior;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Metodo que permite obtener la descripci\u00f3n del mes.
	 * 
	 * @return descripci\u00f3n del meses, tipo String.
	 */
	public static String descripcionMes(int mes) {
		String descripcion = "";
		switch (mes) {
		case 1:
			descripcion = "Enero";
			break;
		case 2:
			descripcion = "Febrero";
			break;
		case 3:
			descripcion = "Marzo";
			break;
		case 4:
			descripcion = "Abril";
			break;
		case 5:
			descripcion = "Mayo";
			break;
		case 6:
			descripcion = "Junio";
			break;
		case 7:
			descripcion = "Julio";
			break;
		case 8:
			descripcion = "Agosto";
			break;
		case 9:
			descripcion = "Septiembre";
			break;
		case 10:
			descripcion = "Octubre";
			break;
		case 11:
			descripcion = "Noviembre";
			break;
		case 12:
			descripcion = "Diciembre";
			break;

		default:
			descripcion = String.valueOf(mes);
			break;
		}
		return descripcion;

	}

	/***
	 * Llenar 0 a la izquierda
	 */
	public static String completa0Izquierda(String cadena, int cant0) {
		while (cadena.length() < cant0) {
			cadena = "0" + cadena;
		}

		return cadena;
	}

	/**
	 * Metodo que permite Formatear un monto con el formato '99.999.999,99'
	 * 
	 * @return monto formateado
	 */
	public static String formatearMonto(String monto) {
		String montoFormateado = "";
		String parte_entera = "";
		String parte_dec = "";
		int cuenta = 0;
		String aux = "";
		int largo = len(monto);
		if (inStr(monto, ".") == 0) {
			parte_entera = monto;
			parte_dec = "";
		} else {
			parte_entera = mid(monto, 1, inStr(monto, ".") - 1);
			parte_dec = mid(monto, inStr(monto, ".") + 1, largo - len(parte_entera) - 1);
		}
		for (int i = len(parte_entera); i > 0; i--) {
			if (cuenta == 3) {
				aux = "." + aux;
				cuenta = 0;
			}
			cuenta = cuenta + 1;
			aux = mid(parte_entera, i, 1) + aux;
		}
		montoFormateado = parte_dec.equals("") ? aux : aux + "," + parte_dec;
		return montoFormateado;
	}

	/**
	 * Metodo que permite Formatear un monto para enviarlo a BD DE: '99.999.999,99'
	 * A: '99999999.99'
	 * 
	 * @return monto formateado
	 */
	public static String formatearMontoParaBD(String monto) {
		// se elimina los puntos
		for (int i = 0; i < monto.length(); i++) {
			monto = monto.replace(".", "");
		}
		// se reemplaza la coma por el punto
		monto = monto.replace(",", ".");
		return monto;
	}

	/**
	 * Devuelve la representacion BigDecimal de un String.
	 * 
	 * @param cadena
	 * @return BigDecimal
	 */

	public static BigDecimal cbdc(String cadena) {
		return new BigDecimal(cadena);
	}

	/**
	 * Devuelve la representacion Regex de un String.
	 * 
	 * @param s
	 * @return String
	 */
	public static String createRegex(String s) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < s.length(); ++i) {
			char ch = s.charAt(i);
			if ("\\.^$|?*+[]{}()".indexOf(ch) != -1)
				b.append('\\').append(ch);
			else if (Character.isLetter(ch))
				b.append("[A-Za-z]");
			else if (Character.isDigit(ch))
				b.append("\\d");
			else
				b.append(ch);
		}
		return b.toString();
	}

	public static String desformatearFechaHora(String s) {
		String cad;

		cad = Utilitario.left("0", 4 - Utilitario.len(Utilitario.mid(s, 7, 4))) + Utilitario.mid(s, 7, 4)
				+ Utilitario.left("0", 2 - Utilitario.len(Utilitario.mid(s, 4, 2))) + Utilitario.mid(s, 4, 2)
				+ Utilitario.left("0", 2 - Utilitario.len(Utilitario.mid(s, 1, 2))) + Utilitario.mid(s, 1, 2) + " "
				+ Utilitario.mid(s, 12);

		return cad;
	}

	/**
	 * Metodo que aplica formato a una cadena
	 * 
	 * @return formato de la cadena.
	 */
	public static String format(String cadena, String formato, String separador) {
		// Ejemplo >> (71258631, 000-000-00, -) >> 712-586-31
		String retorno = "";
		String auxFormato = "";
		String auxCadena = "";
		ArrayList<Integer> posicionList = new ArrayList<Integer>();
		if (!StringUtils.isEmpty(cadena) && !StringUtils.isEmpty(formato)) {
			for (int i = 0; i < formato.length(); i++) {
				if (formato.charAt(i) == separador.charAt(0)) {
					posicionList.add(i);
				}
			}
			auxFormato = formato.replace(separador, "");
			if (auxFormato != null && auxFormato.length() != 0) {
				auxCadena = StringUtils.leftPad(cadena, auxFormato.length(), auxFormato.charAt(0));
			}

			for (Integer pos : posicionList) {
				auxCadena = auxCadena.substring(0, pos) + separador + auxCadena.substring(pos, auxCadena.length());
			}
			retorno = auxCadena;
		}
		return retorno;
	}

	public static String isNull(String cadena) {
		return cadena.trim().toLowerCase().startsWith("null") ? "" : cadena.trim();
	}

	/**
	 * 
	 * Convierte "null" a ""
	 */
	public static String isNull(String cadena, boolean useTrim) {
		cadena = cadena.replaceAll("null", "");

		if (useTrim) {
			cadena = cadena.trim();
		}

		return cadena;
	}

	public static String obtenerMes(String m) {

		if (m.equals("01")) {
			m = Constantes.K_ENE.toUpperCase();
		} else if (m.equals("02")) {
			m = Constantes.K_FEB.toUpperCase();
		} else if (m.equals("03")) {
			m = Constantes.K_MAR.toUpperCase();
		} else if (m.equals("04")) {
			m = Constantes.K_ABR.toUpperCase();
		} else if (m.equals("05")) {
			m = Constantes.K_MAY.toUpperCase();
		} else if (m.equals("06")) {
			m = Constantes.K_JUN.toUpperCase();
		} else if (m.equals("07")) {
			m = Constantes.K_JUL.toUpperCase();
		} else if (m.equals("08")) {
			m = Constantes.K_AGO.toUpperCase();
		} else if (m.equals("09")) {
			m = Constantes.K_SEP.toUpperCase();
		} else if (m.equals("10")) {
			m = Constantes.K_OCT.toUpperCase();
		} else if (m.equals("11")) {
			m = Constantes.K_NOV.toUpperCase();
		} else if (m.equals("12")) {
			m = Constantes.K_DIC.toUpperCase();
		}
		return m;
	}

	/*
	 * Da formato de texto descriptivo a la fecha input: "12/03/2017" output:
	 * "12 de Febrero del 2017"
	 */
	public static String formatoFechaTexto(String fecha) {
		String f[] = fecha.split("/");
		String d = f[0];
		String m = f[1];
		String y = f[2];

		if (m.equals("01")) {
			m = Constantes.K_ENE;
		} else if (m.equals("02")) {
			m = Constantes.K_FEB;
		} else if (m.equals("03")) {
			m = Constantes.K_MAR;
		} else if (m.equals("04")) {
			m = Constantes.K_ABR;
		} else if (m.equals("05")) {
			m = Constantes.K_MAY;
		} else if (m.equals("06")) {
			m = Constantes.K_JUN;
		} else if (m.equals("07")) {
			m = Constantes.K_JUL;
		} else if (m.equals("08")) {
			m = Constantes.K_AGO;
		} else if (m.equals("09")) {
			m = Constantes.K_SEP;
		} else if (m.equals("10")) {
			m = Constantes.K_OCT;
		} else if (m.equals("11")) {
			m = Constantes.K_NOV;
		} else if (m.equals("12")) {
			m = Constantes.K_DIC;
		}

		fecha = d + " de " + m + " del " + y;
		return fecha;
	}

	public static String convertirUTF8(String cadena) {
		try {
			if (!StringUtils.isEmpty(cadena)) {
				byte[] bytes = cadena.getBytes("ISO_8859_1");
				cadena = new String(bytes, "UTF8");
			} else {
				cadena = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			cadena = "";
		}

		return cadena;
	}

	public static String formatearNumero(String texto) {
		texto = Utilitario.trim(texto);
		String retorno = "";
		if (!texto.equals("") && texto != null) {
			boolean encontroPunto = false;
			for (int i = 0; i < texto.length(); i++) {
				char c = texto.charAt(i);
				if (c == '.') {
					encontroPunto = true;
					break;
				}
			}
			if (encontroPunto == false) {
				retorno = formatearNumeroSoloEntero(texto);
			} else {
				if (retorno.equals("0")) {
					retorno = "0";
				} else {
					retorno = formatearMonto(texto);
				}

			}
		} else {
			retorno = texto;
		}
		return retorno;
	}

	public static String formatearNumeroSoloEntero(String parte_entera) {
		int cuenta = 0;
		String aux = "";
		for (int i = len(parte_entera); i > 0; i--) {
			if (cuenta == 3) {
				aux = "." + aux;
				cuenta = 0;
			}
			cuenta = cuenta + 1;
			aux = mid(parte_entera, i, 1) + aux;
		}
		return aux;
	}

	public static String formatearNumeroPrima(String texto) {
		texto = Utilitario.trim(texto);
		String retorno = "";
		if (!texto.equals("") && texto != null) {
			boolean encontroPunto = false;
			for (int i = 0; i < texto.length(); i++) {
				char c = texto.charAt(i);
				if (c == '.') {
					encontroPunto = true;
					break;
				}
			}
			if (encontroPunto == false) {
				retorno = formatearNumeroSoloEntero(texto);
			} else {
				if (retorno.equals("0")) {
					retorno = "0";
				} else {
					retorno = formatearMontoDecimalSinCeroDerecha(texto);
				}

			}
		} else {
			retorno = texto;
		}
		return retorno;
	}

	public static String formatearMontoDecimalSinCeroDerecha(String monto) {
		String montoFormateado = "";
		String parte_entera = "";
		String parte_dec = "";
		int cuenta = 0;
		String aux = "";
		int largo = len(monto);
		if (inStr(monto, ".") == 0) {
			parte_entera = monto;
			parte_dec = "";
		} else {
			parte_entera = mid(monto, 1, inStr(monto, ".") - 1);
			parte_dec = mid(monto, inStr(monto, ".") + 1, largo - len(parte_entera) - 1);
			parte_dec = rtrim(parte_dec, '0');
		}
		for (int i = len(parte_entera); i > 0; i--) {
			if (cuenta == 3) {
				aux = "." + aux;
				cuenta = 0;
			}
			cuenta = cuenta + 1;
			aux = mid(parte_entera, i, 1) + aux;
		}
		montoFormateado = parte_dec.equals("") ? aux : aux + "," + parte_dec;
		return montoFormateado;
	}

	public static String ltrim(String s, char caracter) {
		int i = 0;
		while (i < s.length() && caracter == s.charAt(i)) {
			i++;
		}
		return s.substring(i);
	}

	public static String rtrim(String s, char caracter) {
		int i = s.length() - 1;
		while (i > 0 && caracter == s.charAt(i)) {
			i--;
		}
		return s.substring(0, i + 1);
	}

	/**
	 * Metodo convierteMonto
	 * 
	 * @return convierte el monto obtenido por el servicio y lo cambia al formato
	 *         especificado Ejm: 0.0000 -> 0 Ejm: 3346.5796 -> 3.346,5792
	 */
	public static String convierteMonto(String monto) {
		String montoFinal = "";
		String[] montoArray = monto.split("\\.");
		int longitud = montoArray.length;
		String decimal = montoArray[longitud - 1];

		if (montoArray[0].equalsIgnoreCase("0")) {
			montoFinal = montoArray[0] + "," + montoArray[1];
		} else {
			int ini = montoArray[0].length();
			int ult = montoArray[0].length();
			boolean first = true;
			while (ini != 0) {
				if (ini < 3) {
					ini = 0;
				} else {
					ini = ult - 3; // 12234567
				}

				if (first) {
					montoFinal = montoArray[0].substring(ini, ult);
					first = false;
				} else {
					montoFinal = montoArray[0].substring(ini, ult) + "." + montoFinal;
				}
				ult = ini;
			}
			montoFinal = montoFinal + "," + decimal;
		}
		return montoFinal;
	}

	public static long mCnv(String prmVar) {

		prmVar = prmVar.replace('.', '.');
		prmVar = prmVar.replace(',', '.');

		if (Utilitario.isNumeric(prmVar)) {
			long value = Long.parseLong(prmVar.replaceAll(",|\\.", ""));
			return value;
		} else {
			return new Long("0");
		}
	}

	public static String mCnvFixed(String prmVar, String typeConvertion) throws ParseException {
		if (typeConvertion.equals("K_TYPE_DOM_MONTO")) {
			int primerPunto = prmVar.indexOf(".");
			if (primerPunto != -1) {
				String parte1 = prmVar.substring(0, primerPunto);
				String parte2 = prmVar.substring(primerPunto + 1);
				parte2 = parte2.replaceAll("\\.", "");

				if ((parte2.length() > 0) && (Integer.parseInt(parte2) == 0)) {
					prmVar = parte1;
				} else {
					prmVar = parte1 + "," + rtrim(parte2, '0');
				}
				// NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
				// Number number = format.parse(parte1 + "," + parte2);
				// double d = number.doubleValue();
			}
		}
		return prmVar;
	}

	public static String padLeft(String cadena, int longitud, String caracter) {
		if (cadena != null) {
			if (cadena.length() < longitud) {
				StringBuffer res = new StringBuffer();
				int i = 0;
				int dif = longitud - cadena.length();
				while (i < dif) {
					res.append(caracter);
					i++;
				}
				res.append(cadena);
				return res.toString();
			} else {
				return cadena;
			}
		} else {
			StringBuffer res = new StringBuffer();
			int i = 0;
			while (i < longitud) {
				res.append(caracter);
				i++;
			}
			return res.toString();
		}
	}

	public static String enteroDecimalFormatoMonto(String monto) {
		String montoAux = "0";
		String signo = "";
		String cadAux = "";
		if (!StringUtils.isEmpty(monto)) {
			cadAux = Utilitario.mid(monto, 1, 1);
			if (cadAux.equals("-")) {
				signo = cadAux;
				monto = Utilitario.mid(monto, 2);
			}
			if (monto.indexOf(".") != -1) {
				String[] array = monto.split("\\.");
				Long entera = Long.parseLong(array[0]);
				Long decimal = Long.parseLong(Utilitario.val(array[1]));
				if (decimal == 0L) {
					return signo + Utilitario.formatearMonto(String.valueOf(entera));
				} else {
					return signo + Utilitario.formatearMonto(String.valueOf(entera) + "." + decimal);
				}
			} else {
				return signo + Utilitario.formatearMonto(monto);
			}
		}
		return montoAux;
	}

	/**
	 * Metodo redondeo da como resultado un entero
	 * 
	 * @return Recibe numero
	 */
	public static BigDecimal tecpis(BigDecimal numero) {
		BigDecimal pas = new BigDecimal(0.5);
		BigDecimal numeBase = new BigDecimal(0);
		BigDecimal oro = new BigDecimal(0);
		BigDecimal tecpis = new BigDecimal(0);

		if (numero.compareTo(new BigDecimal(0)) > 0) {
			numeBase = numero.subtract(new BigDecimal((Utilitario.busca(String.valueOf(numero)))));
			oro = new BigDecimal(Utilitario.busca(String.valueOf(numero)));
			if (numeBase.compareTo(pas) >= 0) {
				tecpis = oro.add(new BigDecimal(1));
			} else {
				tecpis = oro;
			}
		} else {
			numeBase = numero.subtract(new BigDecimal((Utilitario.busca(String.valueOf(numero)))));
			oro = new BigDecimal(Utilitario.busca(String.valueOf(numero)));
			if (numeBase.compareTo(pas.multiply(new BigDecimal(-1))) >= 0) {
				tecpis = oro;
			} else {
				tecpis = oro.subtract(new BigDecimal(1));
			}
		}
		return tecpis;
	}

	/**
	 * Metodo convierteMonto
	 * 
	 * @return Recibe un string o numero de forma 93.20.221 y lo deja 93 se detiene
	 *         en la primera "," o "."
	 */
	public static String busca(String s) {
		Integer largo = Utilitario.len(s);
		String linea = s;
		String nom = "";
		for (int i = 1; i < largo + 1; i++) {
			if (!Utilitario.mid(linea, i, 1).equals(",") && !Utilitario.mid(linea, i, 1).equals(".")) {
				nom = nom + Utilitario.mid(linea, i, 1);
			} else {
				break;
			}
		}
		return nom;
	}

	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	public static String bytesToHex(byte[] bytes) {
		char[] hexArray = "0123456789ABCDEF".toCharArray();
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	public static String nullOrBlank(Object objeto) {
		if (Utilitario.trim(String.valueOf(objeto)).equals("null")) {
			return "";
		} else {
			return Utilitario.trim(String.valueOf(objeto));
		}
	}

	public static String convertirUnicode(String cadena) {
		cadena = cadena.replaceAll("Ã¡", "\u00e1").replaceAll("Ã©", "\u00e9").replaceAll("Ã­", "\u00ed")
				.replaceAll("Ã³", "\u00f3").replaceAll("Ãº", "\u00fa").replaceAll("Ã±", "\u00f1");
		return cadena.replaceAll("Ã�", "\u00e1").replaceAll("Ã‰", "\u00e9").replaceAll("Ã�", "\u00ed")
				.replaceAll("Ã“", "\u00f3").replaceAll("Ãš", "\u00fa").replaceAll("Ã‘", "\u00f1");
	}
}