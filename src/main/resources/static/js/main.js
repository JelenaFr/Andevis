'use strict'

const fromSelect = document.getElementById("fromSelect");
const toSelect = document.getElementById("toSelect");
const sendQueryButton = document.getElementById("btn-calc");
const getHistoryButton = document.getElementById("btn-history");
const output = document.getElementById("output");


function change() {
  document.getElementById("btn-history").value = "Update my history";
}


function deleteEntry(url, element) {
    $.ajax({
      url: url,
      type: "DELETE",
      success: function (response) {
        $(element).closest('tr').hide();
      }
    });
  }


$.when($.ready).then(function () {
  getAllCurrencyCodes();
  getCalculation();

  function createTable() {
    let str = `<table id="historyTable" class="display" style="width:100%">`;
    str = str + `<thead>`;
    str = str + `<tr>`;
    str = str + `<th class="th-sm">Amount</th>`;
    str = str + `<th class="th-sm">Source currency</th>`;
    str = str + `<th class="th-sm">Value</th>`;
    str = str + `<th class="th-sm">Target currency</th>`;
    str = str + `<th class="th-sm">Date and time</th>`;
    str = str + `<th class="th-sm">Delete entry</th>`;
    str = str + `</tr>`;
    str = str + `</thead>`;
    str = str + `<tbody id="context">`;
    str = str + `</tbody>`;
    str = str + `</table>`;
    document.getElementById("history").innerHTML = str;
  }

  getHistoryButton.addEventListener("click", () => {
    getUserHistory()
  })


  function getUserHistory() {
    $("#context").innerHTML = "";
    createTable();
    $.ajax({
      type: 'GET',
      url: 'http://localhost:8080/calculator/history',
      data: JSON
    }).done(function (response) {
      response.forEach(item => context.innerHTML +=
        `
           <tr>
            <td>${item["amount"]}</td>
            <td>${item["currencyFrom"]}</td>
            <td>${item["result"]}</td>
            <td>${item["currencyTo"]}</td>
            <td>${item["operationTime"]}</td>
            <td><button class="btn btn-primary" input type="button" value="Delete" onclick="deleteEntry('http://localhost:8080/calculator/delete/' + ${item["id"]}, this )" ><i class="fa fa-trash"></i></button></td>
           </tr>
        `
      );
    })

  }

  function getAllCurrencyCodes() {
    $.ajax({
      type: 'GET',
      url: 'http://localhost:8080/calculator/allCodes',
      data: JSON
    }).done(function (response) {
      response.forEach(item => fromSelect.innerHTML += `<option value="${item}">${item}</option>`);
      response.forEach(item => toSelect.innerHTML += `<option value="${item}">${item}</option>`);
    })
  }


  function getCalculation() {
    sendQueryButton.addEventListener("click", () => {
      let amount = $("#amount").val();
      let fromInput = $("#fromSelect").val();
      let toInput = $("#toSelect").val();
      if (amount === "" || fromInput === "" || toInput === "") {
        window.alert("Please fill out all fields");
        return;
      } else {
        $.ajax({
          type: 'GET',
          url: 'http://localhost:8080/calculator/' + amount + '/' + fromInput + '/' + toInput,
          data: JSON
        }).done(function (response) {
          $('#form')[0].reset();
          output.innerHTML = "";
          output.innerHTML +=
                            `
                                <span>${amount}</span>
                                <span>${fromInput}</span>
                                <span> = </span>
                                <span>${response}</span>
                                <span>${toInput}</span>
                            `;
        })
      }
    })
  }


  $("#amount").blur(function () {
    this.value = parseFloat(this.value).toFixed(3);
  });

})
