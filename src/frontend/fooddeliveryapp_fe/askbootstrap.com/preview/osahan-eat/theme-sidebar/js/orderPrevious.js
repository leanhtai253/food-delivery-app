let hostCard = "http://localhost:8481/order/previous";
$(document).ready(function(){
    let accessToken = getCookie("access-token");
    $.ajax({
        method: "GET",
        url: hostCard + "",
        headers: {"Authorization": "Bearer " + accessToken},
        success: function(data) {
                    let da = data.data;
                    let ind = 1;
                    da.forEach(function(el){
                        $(".orderPreItem").append(`
                        <div class="bg-white shadow-sm rounded p-3 mb-4">
                        <div class="d-flex align-items-center mb-1">
                         <h6 class="mb-0">#ORDER${el.id}</h6>
                            <p class="badge badge-success mb-0 ml-auto"><i class="mdi mdi-check-circle"></i>
                                Completed</p>
                        </div>
                        <div class="d-flex align-items-center">
                            <p class="small"><i class="mdi mdi-currency-usd mr-1"></i>${el.totalPrice} <span
                                    class="ml-2"><i class="mdi mdi-star mr-1"></i>${el.rating}</span></p>
                        </div>
                        <p class="text-dark mb-2"><span class="mr-2 text-black"></span>${el.foodList}</p>
                        <div class="d-flex align-items-center row pt-2 mt-3">
                            <div class="col-6 pr-2">
                                <a href="#" class="btn btn-primary btn-block" data-toggle="modal"
                                    data-target="#detailsModal">Details</a>
                            </div>
                            <div class="col-6 pl-2">
                                <a href="settings.html" class="btn btn-outline-primary btn-block">Get help</a>
                            </div>
                        </div>
                    </div>
                        `)
                        ind += 1;
                    });
                    $("#cardId1").closest(".osahan-card-pay").addClass("active")
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
function setCookie(cname, cvalue, exdays) {
  var d = new Date();
  d.setTime(d.getTime() + exdays * 24 * 60 * 60 * 1000);
  var expires = "expires=" + d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

