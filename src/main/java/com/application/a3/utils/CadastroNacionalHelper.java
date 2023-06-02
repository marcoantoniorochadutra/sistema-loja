package com.application.a3.utils;

import com.application.a3.constants.ExceptionReturnMessage;
import com.application.a3.exception.GenericException;
import com.application.a3.model.dto.FornecedorDto;
import com.application.a3.model.ref.TipoEntidade;

public class CadastroNacionalHelper {

	public static boolean validarCadastroNacional(FornecedorDto dto) {

		if (dto.getTipo().equals(TipoEntidade.FISICA)) {
			if (!cpfValidator(dto.getCadastroNacional())) {
				throw new GenericException(ExceptionReturnMessage.CPF_INVALIDO);
			}
			return true;
		} else if (dto.getTipo().equals(TipoEntidade.JURIDICA)) {
			if (!cnpjValidator(dto.getCadastroNacional())) {
				throw new GenericException(ExceptionReturnMessage.CNPJ_INVALIDO);
			}
			return true;

		} else {
			throw new GenericException(ExceptionReturnMessage.REFERENCIA_INEXISTENTE);
		}

	}

	private static boolean cpfValidator(String cpf) {
		if (cpf == null) {
			return false;
		} else {
			String cpfGerado = "";
			cpf = removeMask(cpf);
			if (!checkCpfLength(cpf))
				return false;
			if (checkSameDigit(cpf))
				return false;
			cpfGerado = cpf.substring(0, 9);
			cpfGerado = cpfGerado.concat(cpfCalc(cpfGerado));
			cpfGerado = cpfGerado.concat(cpfCalc(cpfGerado));

			if (!cpfGerado.equals(cpf))
				return false;
		}
		return true;
	}

	private static boolean cnpjValidator(String cnpj) {
		if (cnpj == null) {
			return false;
		} else {
			cnpj = removeMask(cnpj);
			if (!checkCnpjLength(cnpj))
				return false;
			if (checkSameDigit(cnpj))
				return false;
		}

		return cnpj.equals(cnpjCalc(cnpj));
	}

	public static String removeMask(String value) {
		value = value.replace("-", "");
		value = value.replace(".", "");
		value = value.replace("/", "");
		return value;
	}

	private static boolean checkCpfLength(String cpf) {
		return cpf.length() == 11;
	}

	private static boolean checkCnpjLength(String cnpj) {
		return cnpj.length() == 14;
	}

	private static boolean checkSameDigit(String value) {
		char firstDig = value.charAt(0);
		char[] charCpf = value.toCharArray();
		for (char c : charCpf)
			if (c != firstDig)
				return false;
		return true;
	}

	private static String cpfCalc(String cpf) {
		int digGerado = 0;
		int mult = cpf.length() + 1;
		char[] charCpf = cpf.toCharArray();
		for (int i = 0; i < cpf.length(); i++)
			digGerado += (charCpf[i] - 48) * mult--;
		if (digGerado % 11 < 2)
			digGerado = 0;
		else
			digGerado = 11 - digGerado % 11;
		return String.valueOf(digGerado);
	}

	private static String cnpjCalc(String cnpj) {
		int soma = 0, dig;
		String cnpj_calc = cnpj.substring(0, 12);

		char[] chr_cnpj = cnpj.toCharArray();

		for (int i = 0; i < 4; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
				soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
		for (int i = 0; i < 8; i++)
			if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9)
				soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
		dig = 11 - (soma % 11);

		cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

		soma = 0;
		for (int i = 0; i < 5; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
				soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
		for (int i = 0; i < 8; i++)
			if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9)
				soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
		dig = 11 - (soma % 11);
		cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
		return cnpj_calc;
	}

}
