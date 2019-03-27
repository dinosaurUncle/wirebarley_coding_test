<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<title>사용자 관리</title>
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
	exchageTypeSelectboxSetting();
	getExchangeList();
	exchangeValueDisplay();
});

function exchageTypeSelectboxSetting(){
	let nationList = null;
	$.ajax({      
        type:"get",  
        url:"/nations", 
        async: false,        
        success:function(response){                   	
        	nationList = response.nationList;
        	console.log(nationList.length);     
        },   
        error:function(e){  
            alert(e.responseText);
            exchangeValue = 1193.93;  
        }  
    });
   	for (let nation in nationList) {
   	   	let option = null;
   	 	option = $("<option value='" + nationList[nation].exchangeValue+"'>" 
				+ nationList[nation].name+"("+nationList[nation].exchangeValue+")</option>");
		if (nationList[nation].exchangeType == "trans"){
			$("#transferNation").append(option);		
		} else if (nationList[nation].exchangeType == "receip"){
			$("#receiptNation").append(option);
		} else {

		}
    }  
	
}

function getExchangeList(){
	let prefixExchangeType = $("#transferNation").val();
	let suffixExchangeType = $("#receiptNation").val();
	let combineExchangeType = prefixExchangeType.concat(suffixExchangeType);	
	$.ajax({      
        type:"get",  
        url:"/exchange/"+prefixExchangeType+"/"+suffixExchangeType, 
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
	const prefixText = "수취금액은 ";
	remittance = $("#remittance").val();
	let exchangeMoney = exchangeValue * remittance;
	exchangeMoney = numberWithCommas(exchangeMoney);
	transferNationValue = $("#receiptNation").val();
	const suffixText = " 입니다";
	let compoundText = prefixText.concat(exchangeMoney);
	compoundText = compoundText.concat(transferNationValue);
	compoundText = compoundText.concat(suffixText);
	$("#resultMessage").text(compoundText);
}
</script>
</head>
<body>
	<div id="app">
		<h1>환율계산</h1>
		
		<div>
			<table>
				<tbody>
					<tr>
						<td>송금국가: </td>
						<td><select id="transferNation" onchange="transferNationChange()">								
							</select> 
						</td>
					</tr>
					<tr>
						<td>수취국가: </td>
						<td><select id="receiptNation" onchange="receiptNationChange()">							
							</select> 
						</td>
					</tr>
					<tr>
						<td>환율: </td>
						<td><p id="exchangeValue"></p></td>
					</tr>
					<tr>
						<td>송금액: </td>
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