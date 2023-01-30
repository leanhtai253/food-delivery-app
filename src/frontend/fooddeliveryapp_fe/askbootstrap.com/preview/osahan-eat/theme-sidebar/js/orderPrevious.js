
$(document).ready(function () {
  let accessToken = getCookie("access-token");
  $.ajax({
      method: "GET",
      url: "http://fooddeliveryappapi-env.eba-ampvcd6n.ap-northeast-1.elasticbeanstalk.com/order/previous",
      headers: { "Authorization": "Bearer " + accessToken },
      success: function (response) {
          console.log(response);
          let data = response.data;
          if(data.length > 0) {
              $("#titlePreOrder").removeClass("d-none");
              $("#fiterPreOrder").removeClass("d-none");
          }
          $("#orderPrevious").empty();
          data.forEach(item => {
              var foods = item.foodList;
              var idOrder = item.id;
              console.log(foods);
              $("#orderPrevious").append(`
                <div class="col-lg-4 col-md-6 orderPreItem">
                    <div class="bg-white shadow-sm rounded p-3 mb-4">
                        <div class="d-flex align-items-center mb-1">
                            <h6 class="mb-0">#ORDER${idOrder}</h6>
                            <p class="badge badge-success mb-0 ml-auto"><i class="mdi mdi-check-circle"></i>
                                ${item.status}</p>
                        </div>
                        <div class="d-flex align-items-center">
                            <p class="small"><i class="mdi mdi-currency-usd"></i>${item.totalPrice}<span
                                    class="ml-4"><i class="mdi mdi-star"></i>${item.rating}</span></p>
                        </div>
                        <div id="listFoodItem${idOrder}" class="an">
                        </div>
                        <div class="d-flex align-items-center row pt-2 mt-3">
                            <div class="col-6 pr-2">
                                <a href="#" class="btn btn-primary btn-block" data-toggle="modal"
                                    data-target="#detailsModal">Detials</a>
                            </div>
                            <div class="col-6 pl-2">
                                <a href="settings.html" class="btn btn-outline-primary btn-block">Get help</a>
                            </div>
                        </div>
                    </div>
                </div>
              `)

              foods.forEach((food, i) => {
                $("#listFoodItem" + idOrder).append(`
                  <p class="text-dark mb-2"><span class="mr-2 text-black">${i+1}</span>${food}</p>
                `)
              })
          })
      }
  })
})


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


