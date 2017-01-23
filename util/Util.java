package br.com.microdatasistemas.simintegrationws.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.text.MaskFormatter;

import br.gov.sintegra.ie.InscricaoEstadual;
import br.gov.sintegra.ie.InscricaoEstadualFactory;

public class Util {

    public static String lpad(String valueToPad, String filler, int size) {
	while (valueToPad.length() < size) {
	    valueToPad = filler + valueToPad;
	}
	return valueToPad;
    }

    public static String rpad(String valueToPad, String filler, int size) {
	while (valueToPad.length() < size) {
	    valueToPad = valueToPad + filler;
	}
	return valueToPad;
    }

    public static String format(String pattern, String value) {
	MaskFormatter mask;
	try {
	    mask = new MaskFormatter(pattern);
	    mask.setValueContainsLiteralCharacters(false);
	    return mask.valueToString(value);
	} catch (ParseException e) {
	    throw new RuntimeException(e);
	}
    }

    public static String truncate(String value, int length) {
	if (value != null && value.length() > length)
	    value = value.substring(0, length);
	return value;
    }

    public static String truncateRight(String value, int length) {
	if (value != null && value.length() > length) {
	    int finalIndex = value.length();
	    value = value.substring(finalIndex - length, finalIndex);
	}
	return value;
    }

    public static String fillZeroesString(String value, int quantity) {
	if(isEmpty(value))
	    value = "";
	
	StringBuilder sb = new StringBuilder(value);
	int length = sb.length();

	if (quantity != 1) {
	    if (length < quantity) {
		for (int i = 1; i <= quantity - length; i++) {
		    sb.insert(0, "0");
		}
	    }
	}
	return sb.toString();
    }

    /**
     * @param str
     *            - The String value that needs to be checked if it's numeric or not.
     * @return 'true' if the value is numeric, 'false' if it's not.
     */
    public static boolean isNumeric(String str) {
	if (!isEmpty(str)) {
	    return str.matches("[-+]?\\d*\\.?\\d+");
	}
	return false;
    }

    /**
     * @param str
     *            - The String value that needs to be checked if it's numeric and the default value in case it's not.
     * @return The Long value of the String if this is numeric, the default value if this is not.
     */
    public static Long isNumeric(String str, Long defaultValue) {
	if (isNumeric(str)) {
	    return Long.valueOf(str);
	}
	return defaultValue;
    }

    public static boolean isEmpty(String value) {
	if (value == null || value.trim().isEmpty()) {
	    return true;
	}
	return false;
    }

    public static boolean isEmpty(Object value) {
	if (value == null) {
	    return true;
	}
	return isEmpty(value.toString());
    }
    
    public static <T> T isEmpty(T value, T defaultValue) {
	if (isEmpty(value)) {
	    return defaultValue;
	}
	return value;
    }

    public static String cleanCharacters(String value) {
	if (!isEmpty(value)) {
	    return value.replace(".", "").replace("-", "").replace("/", "").replace("+", "").replace("(", "").replace(")", "").replace(" ", "").replace("º", "").replace("ª", "");
	}
	return value;
    }
    
    public static String cleanAccentuation(String value) {
	if(!isEmpty(value)) {
	    return value.replace("Ç", "C").replace("ç", "c")
		.replace("Á", "A").replace("á", "a").replace("À", "A").replace("à", "a").replace("Ã", "A").replace("ã", "a")
		.replace("É", "E").replace("é", "e").replace("Ê", "E").replace("ê", "e")
		.replace("Í", "I").replace("í", "i")
		.replace("Ó", "O").replace("ó", "o").replace("Ô", "O").replace("ô", "o").replace("Õ", "O").replace("õ", "o")
		.replace("Ú", "U").replace("ú", "u");
	}
	return value;
    }

    public static String getFormattedCNPJ(String cnpj) {
	if (!isEmpty(cleanCharacters(cnpj))) {
	    return format("##.###.###/####-##", cleanCharacters(cnpj));
	}
	return cnpj;
    }
    
    public static String getFormattedNonNumericCNPJ(String cnpj) {
	if(!isEmpty(cleanCharacters(cnpj))) {
	    StringBuilder sb = new StringBuilder(fillZeroesString(cleanCharacters(cnpj), 14));
	    return sb.insert(2, ".").insert(6, ".").insert(10, "/").insert(15, "-").toString();
	}
	return cnpj;
    }

    public static String getFormattedCPF(String cpf) {
	if (!isEmpty(cpf)) {
	    return format("###.###.###-##", cleanCharacters(cpf));
	}
	return cpf;
    }
    
    public static String getFormattedNonNumericCPF(String cpf) {
	if(!isEmpty(cleanCharacters(cpf))) {
	    StringBuilder sb = new StringBuilder(fillZeroesString(cleanCharacters(cpf), 11));
	    return sb.insert(3, ".").insert(7, ".").insert(11, "-").toString();
	}
	return cpf;
    }

    public static String getFormattedCEP(String cep) {
	if (!isEmpty(cep) && isNumeric(cleanCharacters(cep))) {
	    return format("##.###-###", cleanCharacters(cep));
	}
	return cep;
    }

    public static boolean isCNPJ(String cnpj) {
	int soma = 0, dig;

	if (cnpj.length() != 14)
	    return false;
	
	String cnpj_calc = cnpj.substring(0, 12);

	char[] chr_cnpj = cnpj.toCharArray();

	/* Primeira parte */
	for (int i = 0; i < 4; i++)
	    if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
		soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
	for (int i = 0; i < 8; i++)
	    if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9)
		soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
	dig = 11 - (soma % 11);

	cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

	/* Segunda parte */
	soma = 0;
	for (int i = 0; i < 5; i++)
	    if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
		soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
	for (int i = 0; i < 8; i++)
	    if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9)
		soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
	dig = 11 - (soma % 11);
	cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

	return cnpj.equals(cnpj_calc);
    }

    public static boolean isCPF(String cpf) {
	if (!cpf.substring(0, 1).equals("")) {
	    int d1, d2;
	    int digito1, digito2, resto;
	    int digitoCPF;
	    String nDigResult;
	    cpf = cpf.replace('.', ' ');
	    cpf = cpf.replace('-', ' ');
	    cpf = cpf.replaceAll(" ", "");
	    d1 = d2 = 0;
	    digito1 = digito2 = resto = 0;

	    for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
		digitoCPF = Integer.valueOf(cpf.substring(nCount - 1, nCount)).intValue();

		// multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
		d1 = d1 + (11 - nCount) * digitoCPF;

		// para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
		d2 = d2 + (12 - nCount) * digitoCPF;
	    }

	    // Primeiro resto da divisão por 11.
	    resto = (d1 % 11);

	    // Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
	    if (resto < 2)
		digito1 = 0;
	    else
		digito1 = 11 - resto;

	    d2 += 2 * digito1;

	    // Segundo resto da divisão por 11.
	    resto = (d2 % 11);

	    // Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
	    if (resto < 2)
		digito2 = 0;
	    else
		digito2 = 11 - resto;

	    // Digito verificador do CPF que está sendo validado.
	    String nDigVerific = cpf.substring(cpf.length() - 2, cpf.length());

	    // Concatenando o primeiro resto com o segundo.
	    nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

	    // comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
	    return nDigVerific.equals(nDigResult);
	}
	else
	    return false;
    }
    
    public static boolean isIncricaoEstadual(String estado, String incricaoEstadual) {
	if(!isEmpty(estado) && !isEmpty(incricaoEstadual)) {
	    if(incricaoEstadual.equalsIgnoreCase("isento"))
		return true;
	    
	    InscricaoEstadual ie = InscricaoEstadualFactory.getInstance(estado);
	    if(ie != null)
		return ie.validar(incricaoEstadual);

	    return false;
	}
	return false;
    }
    
    public static boolean checkInstance(Class<?> obj, Class<?> type) {
	if (obj == null && type == null)
	    return true;

	if ((obj != null && type != null) && obj.isAssignableFrom(type))
	    return true;
	
	return false;
    }
    
    private static boolean checkForDateOrCalendarInstance(Object date) {
	if(!checkInstance(date.getClass(), Date.class) && !checkInstance(date.getClass(), Calendar.getInstance().getClass()))
	    return false;
	return true;
    }
    
    public static <T> Date returnDateFromCalendar(T obj) {
	if(checkForDateOrCalendarInstance(obj) == false)
	    throw new IllegalArgumentException("The parameter in method returnDateFromCalendar must be Date or Calendar!");
	
	if(checkInstance(obj.getClass(), Date.class))
	    return (Date) obj;
	else
	    return ((Calendar) obj).getTime();
    }
    
    private static boolean between(Date currentDate, Date startDate, Date endDate, DateFormat dateFormat) throws ParseException {
	startDate = dateFormat.parse(dateFormat.format(startDate));
	endDate = dateFormat.parse(dateFormat.format(endDate));
	currentDate = dateFormat.parse(dateFormat.format(currentDate));
	
	if(endDate.before(startDate)) {
	    throw new IllegalArgumentException("The end date '" + dateFormat.format(endDate) + "' must be higher than start date '" + dateFormat.format(startDate) + "'!");
	}
	if(currentDate.equals(startDate) || currentDate.equals(endDate)) {
	    return true;
	}
	else if(currentDate.after(startDate) && currentDate.before(endDate)) {
	    return true;
	}
	return false;
    }
    
    public static boolean betweenDate(Date currentDate, Date startDate, Date endDate) throws ParseException {
	return between(currentDate, startDate, endDate, DateFormat.getDateInstance());
    }
    
    public static boolean betweenDateTime(Date currentDate, Date startDate, Date endDate) throws ParseException {
	return between(currentDate, startDate, endDate, DateFormat.getDateTimeInstance());
    }
    
    public static <D1, D2, D3> boolean betweenDate(D1 currentDate, D2 startDate, D3 endDate) throws ParseException {
	if(!checkForDateOrCalendarInstance(currentDate) || !checkForDateOrCalendarInstance(startDate) || !checkForDateOrCalendarInstance(endDate))
	    throw new IllegalArgumentException("The parameters type in betweenDate method must be Date or Calendar!");
	
	return betweenDate(returnDateFromCalendar(currentDate), returnDateFromCalendar(startDate), returnDateFromCalendar(endDate));
    }
    
    public static <D1, D2, D3> boolean betweenDateTime(D1 currentDate, D2 startDate, D3 endDate) throws ParseException {
	if(!checkForDateOrCalendarInstance(currentDate) || !checkForDateOrCalendarInstance(startDate) || !checkForDateOrCalendarInstance(endDate))
	    throw new IllegalArgumentException("The parameters type in betweenDateTime method must be Date or Calendar!");
	
	return betweenDateTime(returnDateFromCalendar(currentDate), returnDateFromCalendar(startDate), returnDateFromCalendar(endDate));
    }


}