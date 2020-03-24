let xhr;
function startMethod() {
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = resultProcess;
	xhr.open("GET", "ajaxExam02.xml", true);
	xhr.send(null);
}
function resultProcess() {
	if(xhr.readyState ==4){
		if(xhr.status ==200){
			processXML();
		}
	}
}

function processXML() {
	//기존의 내용을 지워야 계속 눌러도 추가가 안된다.
	var trChild = document.getElementById("resultDisplay").childNodes;
	//alert("개수= "+trChild.length);
	//크롬에서는 trChild.length가 하나 지울때마다 다시 계산을 해서 index의 값이 바뀐다.
	//그래서 거꾸로 돌리면 된다.
	for(var i =trChild.length-1;i>=0;i--){
		trChild[i].remove();
	}//for
	
	//----------------------------------
	var xmlDoc = xhr.responseXML;
	var trTag = "";
	var tdTag = "";
	var subjects = xmlDoc.getElementsByTagName("subject");
	//alert("행의 수 : "+subjects.length);//5
	for(var i= 0; i<subjects.length; i++){
		trTag = document.createElement("tr");
		var childs = subjects[i].childNodes;
		//alert("열의 수 : "+ childs.length)
		
		for(var j=0; j<childs.length; j++){
			if(childs[j].firstChild!=null){
				tdTag = document.createElement("td");
				
				tdTag.appendChild(childs[j].firstChild);
				trTag.appendChild(tdTag);
			}
		}//for j
		/*if(document.getElementById("resultDisplay").value != null){
			trTag.parentNode.removeChild(item);
		}*/
		document.getElementById("resultDisplay").appendChild(trTag);
	}//for i
}
