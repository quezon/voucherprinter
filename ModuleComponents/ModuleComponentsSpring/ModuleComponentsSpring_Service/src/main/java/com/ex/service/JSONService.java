package com.ex.service;

import java.time.format.DateTimeFormatter;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ex.entity.Voucher;
import com.ex.entity.VoucherEnum;
import com.ex.entity.Voucherable;
import com.ex.entity.VoucherableEnum;
import com.google.gson.Gson;

@Service
public class JSONService {
	@Autowired
	private Environment env;
	
	public JSONObject mergeJSONObjects(JSONObject paramFiles, JSONObject reportBody) throws JSONException {
		JSONObject mergedJSON = new JSONObject();
		mergedJSON.put("paramFiles", paramFiles);
		mergedJSON.put("reportBody", reportBody);
		return mergedJSON;
	}
	
	public <T extends Voucherable> JSONObject convertVoucherToJSON(T voucher) throws JSONException {
		String jsonInString = new Gson().toJson(voucher);
		JSONObject mJSONObject = new JSONObject(jsonInString);
		return mJSONObject;
	}
	
	public void supplyMostCommonKeys(JSONObject jobj, VoucherableEnum voucherEnum, String date) throws JSONException {
		Long fileNumber = jobj.getJSONObject(voucherEnum.getThreeLetterCode()).getLong("id");
		
		jobj.put("endsWith", env.getProperty("spring.template.xdoc.voucher." + voucherEnum.getPropCode() + ".endsWith"));
		jobj.put("parentFolder", env.getProperty("spring.directory.voucher"));
		jobj.put("fileNumber", fileNumber.toString());
		jobj.put("template", env.getProperty("spring.template.xdoc.voucher." + voucherEnum.getPropCode()));
		jobj.put("date", date);
	}
	
	public String convertVoucherToJSONStringForXDoc(Voucherable voucher) throws JSONException {
		VoucherEnum ve = null;
		String threeLetter;
		JSONObject jsonToReturn = new JSONObject();
		JSONObject jsonVoucher = convertVoucherToJSON(voucher);
		String dateString = voucher.getDateCreated().format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
		
		if (voucher instanceof Voucher) {
			threeLetter = VoucherEnum.getThreeLetterCodeByClazz(voucher.getClass());
			ve = VoucherEnum.getEnumByClazz(voucher.getClass());
			jsonToReturn.put(threeLetter, jsonVoucher);
			supplyMostCommonKeys(jsonToReturn, ve, dateString);
		}
		
		return jsonToReturn.toString();
	}
}
