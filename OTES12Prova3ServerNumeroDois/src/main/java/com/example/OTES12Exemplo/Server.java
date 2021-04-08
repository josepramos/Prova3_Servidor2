package com.example.OTES12Exemplo;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Server {

    @GetMapping("/smi/{mt}/{fc}/{fa}/{fd}")
    public String smi(
    					   @PathVariable(value = "mt") int mt,
    					   @PathVariable(value = "fc") int fc,
    					   @PathVariable(value = "fa") int fa,
    					   @PathVariable(value = "fd") int fd
    					   ) {

    	float mt_float = (float) mt;
		float fc_float = (float) fc;
		float fa_float = (float) fa;
		float fd_float = (float) fd;

		float resultado_smi = (mt_float - fa_float - fc_float - fd_float)/mt_float;
    	String responseJson = "{\"resultado_smi\": "+resultado_smi+"}";
    	
    	return responseJson;
    }

	@GetMapping("/integridade/{ameaca}/{seguranca}")
	public String integridade(
			@PathVariable(value = "ameaca") float ameaca,
			@PathVariable(value = "seguranca") float seguranca
	) {

		float resultado_integridade = (1-(ameaca*(1-seguranca)))*100;

		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("##.00", dfs);

		String integridade_porcentagem = df.format(resultado_integridade);
		integridade_porcentagem = integridade_porcentagem + "%";

		String responseJson = "{\"resultado_integridade\": \""+integridade_porcentagem+"\"}";

		return responseJson;
	}

	@GetMapping("/dre/{erros}/{defeitos}")
	public String dre(
			@PathVariable(value = "erros") float erros,
			@PathVariable(value = "defeitos") float defeitos
	) {

		float resultado_dre = erros/(erros+defeitos);

		String responseJson = "{\"resultado_dre\": \""+resultado_dre+"\"}";

		return responseJson;
	}

	@GetMapping("/re/{probabilidade}/{custo}")
	public String re(
			@PathVariable(value = "probabilidade") float probabilidade,
			@PathVariable(value = "custo") float custo
	) {

		float resultado_re = probabilidade*custo;

		String responseJson = "{\"resultado_re\": \""+resultado_re+"\"}";

		return responseJson;
	}


}
