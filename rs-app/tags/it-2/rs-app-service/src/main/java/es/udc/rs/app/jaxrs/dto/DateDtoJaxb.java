package es.udc.rs.app.jaxrs.dto;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "dateType", propOrder = {"day", "month", "year"})
public class DateDtoJaxb {

    @XmlAttribute(required = true)
	private int day;
    @XmlAttribute(required = true)
	private int month;
    @XmlAttribute(required = true)
	private int year;
	
	public DateDtoJaxb() {		
	}

	public DateDtoJaxb(Calendar date) {
        this.day = date.get(Calendar.DAY_OF_MONTH);
        this.month = date.get(Calendar.MONTH) - Calendar.JANUARY + 1;
        this.year = date.get(Calendar.YEAR);

	}

	public DateDtoJaxb(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
    @Override
    public String toString() {
        return "DateDto [day=" + day + ", month=" + month
                + ", year=" + year + "]";
    }	
	
}