<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
double[] maxTemperature = {10.2, 11.8, 12.9, 9.0};
for(int i=0; i<maxTemperature.length; i++){
	if(i<maxTemperature.length-1)
		out.print(maxTemperature[i] +", ");
	else
		out.print(maxTemperature[i]);
}


%>