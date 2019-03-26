<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<title>����� ����</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
<!-- Optional theme -->
<script src="<c:url value="/resources/import/lib/jquery-3.3.1.min.js" />"></script>
<script>
let exchangeValue = 0;
let remittance = 0;
let receiptNationValue = "";
let transferNationValue = "";
let exchangeObj = null;
$( document ).ready(function() {
	getExchangeList();
	exchangeValueDisplay();
});

function getExchangeList(){
	let prefixExchangeType = $("#transferNation").val();
	let suffixExchangeType = $("#receiptNation").val();
	let combineExchangeType = prefixExchangeType.concat(suffixExchangeType);	
	$.ajax({      
        type:"get",  
        url:"/WireBarleyTestSample/exchange/"+prefixExchangeType+"/"+suffixExchangeType, 
        async: false,        
        success:function(response){                   	
        	exchangeValue = response.exchangeObj.exchangeValue;     
        },   
        error:function(e){  
            alert(e.responseText);
            exchangeValue = 1193.93;  
        }  
    });  

}

function transferNationChange(){
	getExchangeList();
	transferNationValue = $("#transferNation").val();
	$("#receiptMark").text(transferNationValue);
	exchangeValueDisplay();
	$("#resultMessage").text("");
}
function numberWithCommas(x) {
	x = x.toFixed(2);
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function exchangeValueDisplay(){
	let exchageValueText = numberWithCommas(exchangeValue);
	exchageValueText = exchageValueText.concat("  ");
	receiptNationValue = $("#receiptNation").val();
	transferNationValue = $("#transferNation").val();
	let exchangeText = exchageValueText.concat(receiptNationValue);
	exchangeText = exchangeText.concat("/");
	exchangeText = exchangeText.concat(transferNationValue);
	$("#exchangeValue").text(exchangeText);
	
	
}

function receiptNationChange(){
	getExchangeList();
	exchangeValueDisplay();
	$("#resultMessage").text("");
}

function receiptNationSummit(){
	getExchangeList();
	const prefixText = "����ݾ��� ";
	remittance = $("#remittance").val();
	let exchangeMoney = exchangeValue * remittance;
	exchangeMoney = numberWithCommas(exchangeMoney);
	transferNationValue = $("#receiptNation").val();
	const suffixText = " �Դϴ�";
	let compoundText = prefixText.concat(exchangeMoney);
	compoundText = compoundText.concat(transferNationValue);
	compoundText = compoundText.concat(suffixText);
	$("#resultMessage").text(compoundText);
}
</script>
</head>
<body>
	<div id="app">
		<h1>ȯ�����</h1>
		
		<div>
			<table>
				<tbody>
					<tr>
						<td>�۱ݱ���: </td>
						<td><select id="transferNation" onchange="transferNationChange()">
								<option value="USD">�̱�(USD)</option>
								<option value="AUD">ȣ��(AUD)</option>
							</select> 
						</td>
					</tr>
					<tr>
						<td>���뱹��: </td>
						<td><select id="receiptNation" onchange="receiptNationChange()">
								<option value="KRW">�ѱ�(KRW)</option>
								<option value="JPY">�Ϻ�(JPY)</option>
								<option value="PHP">�ʸ���(PHP)</option>
							</select> 
						</td>
					</tr>
					<tr>
						<td>ȯ��: </td>
						<td><p id="exchangeValue"></p></td>
					</tr>
					<tr>
						<td>�۱ݾ�: </td>
						<td><input type="text" id="remittance" value="100"><span id="receiptMark">USD</span></td>
					</tr>
					<tr>
						<td colspan="2"><button onclick="receiptNationSummit()">Submit</button></td>
					</tr>
				</tbody>
			</table>
		</div>
		<br>
		
		<div><span id="resultMessage"></span></div>
	</div>
</body>
</html>