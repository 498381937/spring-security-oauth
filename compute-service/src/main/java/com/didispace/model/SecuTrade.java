package com.didispace.model;

import java.util.Date;

public class SecuTrade {
	private String fundSecuAbbr;
	private String secuCode;
	private String secuAbbr;
	private String entrustDirection;
	private Double dealPrice;
	private Double dealVolume;
	private Date endDate;
	private String endDateJson;
	private Integer size;
	public String getFundSecuAbbr() {
		return fundSecuAbbr;
	}
	public void setFundSecuAbbr(String fundSecuAbbr) {
		this.fundSecuAbbr = fundSecuAbbr;
	}
	public String getSecuCode() {
		return secuCode;
	}
	public void setSecuCode(String secuCode) {
		this.secuCode = secuCode;
	}
	public String getSecuAbbr() {
		return secuAbbr;
	}
	public void setSecuAbbr(String secuAbbr) {
		this.secuAbbr = secuAbbr;
	}
	public String getEntrustDirection() {
		return entrustDirection;
	}
	public void setEntrustDirection(String entrustDirection) {
		this.entrustDirection = entrustDirection;
	}
	public Double getDealPrice() {
		return dealPrice;
	}
	public void setDealPrice(Double dealPrice) {
		this.dealPrice = dealPrice;
	}
	public Double getDealVolume() {
		return dealVolume;
	}
	public void setDealVolume(Double dealVolume) {
		this.dealVolume = dealVolume;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getEndDateJson() {
		return endDateJson;
	}
	public void setEndDateJson(String endDateJson) {
		this.endDateJson = endDateJson;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	
}
