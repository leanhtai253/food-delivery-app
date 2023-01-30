let listingsHost = "http://fooddeliveryappapi-env.eba-ampvcd6n.ap-northeast-1.elasticbeanstalk.com" + "/listings/sort";
$(document).ready(function () {
  if (getCookie("listingsFilter") != null) {
    filterSubmission = JSON.parse(getCookie("listingsFilter"));
    if (filterSubmission.cat == 0) {
      filtersUrl = listingsHost + `/all?sortBy=${filterSubmission.sortBy}&price=${filterSubmission.price}`;
    } else {
      filtersUrl = listingsHost + `/${filterSubmission.cat}?sortBy=${filterSubmission.sortBy}&price=${filterSubmission.price}`
    }
    $.ajax({
      method: "GET",
      url: filtersUrl,
      headers: { Authorization: "Bearer " + getCookie("access-token") },
      success: function (response) {
        console.log(response);
        let da = response.data;
        da.forEach(function (food) {
          $("#listingResults").append(`
            <a href="#" class="text-decoration-none text-dark col-lg-3 col-md-6 mb-4" data-toggle="modal" data-target="#myitemsModal">
            <img src="img/food/${food.image}" class="img-fluid rounded">
            <div class="d-flex align-items-center mt-3 mb-2">
                <p class="text-black h6 m-0">${food.name}</p>
                <span class="badge badge-success ml-auto"><i class="mdi mdi-cash"></i>${food.price} 000 VND</span>
            </div>
            <p class="small mb-2"><i class="mdi mdi-star text-warning"></i><span
                    class="font-weight-bold text-dark ml-1">${food.rating}</span> (${food.orders}) <i
                    class="mdi mdi-silverware-fork-knife ml-2 mr-1"></i> ${food.category} <i
                    class="mdi mdi-motorbike ml-2 mr-2"></i>45 - 55 min</p>
            </a>
                    `);
        });
      },
    });
  }
});

function getCookie(cname) {
  var name = cname + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var ca = decodedCookie.split(";");
  for (var i = 0; i < ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == " ") {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}
function setCookie(cname, cvalue, exdays) {
  var d = new Date();
  d.setTime(d.getTime() + exdays * 24 * 60 * 60 * 1000);
  var expires = "expires=" + d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}
