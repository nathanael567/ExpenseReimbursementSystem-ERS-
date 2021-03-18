window.onbeforeunload = function(e) {
	document.getElementById("username").innerHTML = localStorage.getItem("unloadUser");
};

window.onload = function () {
    getTicketInfo();

}
let btnApprove;
let btnDeny;

function getTicketInfo1() {
    fetch('http://localhost:8081/project1/displayAllTickets.json').then(
        function (response) {
            return response.json();
        }, function () {
            console.log();
        }
    ).then(function (myJSON) {
        console.log(myJSON);

    })

}
function getTicketInfo() {
    fetch('http://localhost:8081/project1/displayAllTickets.json').then(
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

    for (let i = 0; i < ticketList.length; i++) {
        console.log("inside function");
        let obj = ticketList[i];
        console.log(obj);
        let row = table.insertRow(-1);

        let cell1 = row.insertCell(0);
        let cell2 = row.insertCell(1);
        let cell3 = row.insertCell(2);
        let cell4 = row.insertCell(3);
        let cell5 = row.insertCell(4);
        let cell6 = row.insertCell(5);
        let cell7 = row.insertCell(6);
        let cell8 = row.insertCell(7);
        let cell9 = row.insertCell(8);
        let cell10 = row.insertCell(9);
        let cell11 = row.insertCell(10);

        let newText1 = document.createTextNode(`${obj.ticketid}`);
        cell1.appendChild(newText1);

        let newText2 = document.createTextNode(`$${obj.amount}`);
        cell2.appendChild(newText2);

        let newText3 = document.createTextNode(`${new Date(obj.submitted)}`);
        cell3.appendChild(newText3);

        let newText4 = document.createTextNode(`${new Date(obj.resolved)}`);
        cell4.appendChild(newText4);

        let newText5 = document.createTextNode(`${obj.description}`);
        cell5.appendChild(newText5);

        let newText6 = document.createTextNode(`${obj.author}`);
        cell6.appendChild(newText6);

        let newText7 = document.createTextNode(`${obj.resolver}`);
        cell7.appendChild(newText7);

        let newText8;
        if (obj.statusid == 1) {
            newText8 = document.createTextNode("Pending");
        }
        else if (obj.statusid == 2)
            newText8 = document.createTextNode("Approved");
        else
            newText8 = document.createTextNode("Denied");
        cell8.appendChild(newText8);

        let newText9;
        if (obj.typeid == 1)
            newText9 = document.createTextNode("Lodging");
        else if (obj.typeid == 2)
            newText9 = document.createTextNode("Travel");
        else if (obj.typeid == 4)
            newText9 = document.createTextNode("Food");
        else
            newText9 = document.createTextNode("Other");
        cell9.appendChild(newText9);


        if (obj.statusid == 1) {
            btnApprove = document.createElement("button");
            btnApprove.innerHTML = "Approve";
            btnApprove.style.color = "green";
            btnApprove.onclick = function () {
                approveTicketRequest(obj.ticketid); 
            }
            btnDeny = document.createElement("button");
            btnDeny.innerHTML = "Deny";
            btnDeny.style.color = "red";
            btnDeny.onclick = function () {
                denyTicketRequest(obj.ticketid); 
            }
            cell10.appendChild(btnApprove);
            cell11.appendChild(btnDeny);
        } else {
            continue;
        }
    }

    
}
function approveTicketRequest(id) {
    console.log(id);
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            getTicketInfo1();
        }
    }
    console.log(id);
    xhttp.open("POST", 'http://localhost:8081/project1/approveTicket.change', true);
    xhttp.send(id);
}

function denyTicketRequest(id) {
    console.log(id);
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            getTicketInfo1();
        }
    }
    console.log(id);
    xhttp.open("POST", 'http://localhost:8081/project1/denyTicket.change', true);
    xhttp.send(id);
}



/**
 * THIS IS THE BEGINNING OF THE SORTABLE/SEARCHABLE TABLE JAVASCRIPT
 * *****************************************************************
 * 
 */

function myFunction() {
    // Declare variables
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("table1");
    tr = table.getElementsByTagName("tr");

    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
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

/**
 * THIS IS THE BEGINNING OF THE SORTABLE/SEARCHABLE TABLE JAVASCRIPT
 * *****************************************************************
 * 
 */

function searchFunction() {
    // Declare variables
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("searchInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("table1");
    tr = table.getElementsByTagName("tr");

    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
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