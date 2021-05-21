// $('#loadAuthors').click(() => {
// const products = [];
//
// $( document ).ready(function() {
//     const wantedID = document.getElementById("wantedidnumber").value;
//     $('.authors-container').empty();
//     document.getElementById('ex1').value = '';
//     document.getElementById('ex2').value = '';
//     document.getElementById('ex3').value = '';
//  //   console.log(wantedID);
//
//   fetch('http://localhost:8081/product/all' ).
//       then((response) => response.json()).
//       then((json) => json.forEach((dataObject, idx) => {
//           // products.push(dataObject);
//        //  console.log(dataObject.name);
//         // console.log(dataObject.working);
//       if(dataObject.category.id == wantedID ){
//           let tableRow = '<tr>' +
//               '<td>' + idx + '</td>' +
//               '<td>' + dataObject.name + '</td>' +
//               '<td>' + dataObject.catalogNumber + '</td>' +
//               '<td>' + dataObject.price + '</td>' +
//               '<td>' + dataObject.category.name + '</td>' +
//               '<td>' + dataObject.created + '</td>' +
//               '<td>' + ' <img data-enlargeable alt="" class="imgInTable" title="" src=' + dataObject.imgUrl + ' /> ' + '</td>' +
//               '<td><button>Delete</button></td>' +
//               '<td><form th:action="@{/cart/addTo/{id}/(id='+ dataObject.id +')}" th:method="POST"> <input type="submit" value="Add to Cart"  >   </form></td>' +
//
//               '</tr>';
//
//        //   console.log(tableRow);
//           $('.authors-container').append(tableRow);
//       }
//
//   }));
//
// });


$( document ).ready(function() {
    //  making which btn is active and what it does
    const wantedID = document.getElementById("wantedidnumber").value;
// Get the container element
    var btnContainer = document.getElementById("myDIV");

// Get all buttons with class="btn" inside the container
    var btns = btnContainer.getElementsByClassName("btn");
    console.log("test");
    console.log(btns);
// Loop through the buttons and add the active class to the current/clicked button
    for (var i = 0; i < btns.length; i++) {

        if(btns[i].id === wantedID){
            btns[i].className += " active";
        }else{
            btns[i].className = "btn";
        }

    }


});
