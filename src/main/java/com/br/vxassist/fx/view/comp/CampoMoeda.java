package com.br.vxassist.fx.view.comp;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

import com.br.vxassist.fx.util.MaskFieldUtil;
import javafx.scene.control.TextField;

public class CampoMoeda extends TextField{
	public CampoMoeda() {
		MaskFieldUtil.monetaryField(this);
	}

	public BigDecimal getValorBigDecimal() {
		if(getText()==null || getText().trim().isEmpty())
			return BigDecimal.ZERO;
		else {
			Locale Local = new Locale("pt","BR");
			DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Local));
			try {
				return new BigDecimal(df.parseObject(getText()).toString());
			} catch (ParseException e) {
				e.printStackTrace();
				return BigDecimal.ZERO;
			}
		}
			
	}
}
