window.onbeforeunload = function(e) {
	document.getElementById("username").innerHTML = localStorage.getItem("unloadUser");
};


window.onload = function () {
    getTicketInfo();

}
function getTicketInfo() {
    fetch('http://localhost:8081/project1/displayTickets.json').then(
        function (response) {
            return response.json();
        }, function () {
            console.log();
        }
    ).then(function (myJSON) {
        console.log(myJSON);
        setTableValues(myJSON);

    })
}


function setTableValues(ticketList) {

    let table = document.getElementById("table1");
    for(let i = 0; i < ticketList.length; i++){
        console.log("inside function");
        let obj = ticketList[i];
        console.log(obj);
        let row = table.insertRow(-1);

        let cell1 = row.insertCell(0);
        let cell2 = row.insertCell(1);
        let cell3 = row.insertCell(2);
        let cell4 = row.insertCell(3);
        let cell5 = row.insertCell(4);

        let newText1 = document.createTextNode(`${obj.ticketid}`);
        cell1.appendChild(newText1);

        let newText2 = document.createTextNode(`$${obj.amount}`);
        cell2.appendChild(newText2);

        let newText3 = document.createTextNode(`${new Date(obj.submitted)}`);
        cell3.appendChild(newText3);

        let newText4;
        if(obj.statusid == 1)
            newText4 = document.createTextNode("Pending");
        else if(obj.statusid == 2)
            newText4 = document.createTextNode("Approved");
        else
            newText4 = document.createTextNode("Denied");
        cell4.appendChild(newText4);

        let newText5; 
        if(obj.typeid == 1)
            newText5 = document.createTextNode("Lodging");
        else if(obj.typeid == 2)
            newText5 = document.createTextNode("Travel");
        else if(obj.typeid == 3)
            newText5 = document.createTextNode("Food");
        else
            newText5 = document.createTextNode("Other");
        cell5.appendChild(newText5);
    }
    
} 
function searchFunction() {
    // Declare variables
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("searchInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("table1");
    tr = table.getElementsByTagName("tr");

    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[3];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

