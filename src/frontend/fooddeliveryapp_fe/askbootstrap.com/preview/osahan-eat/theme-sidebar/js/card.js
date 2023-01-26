let hostCard = "http://localhost:8481/card";
$(document).ready(function(){
    let accessToken = getCookie("access-token");
    console.log(accessToken);
    $.ajax({
        method: "GET",
        url: hostCard + "",
        headers: {"Authorization": "Bearer " + accessToken},
        success: function(data) {
                    let da = data.data;
                    let ind = 1;
                    $(".cardModalLength").text(`(${da.length})`)
                    da.forEach(function(el){
                        $("#cardModalList").append(`
                            <label class="btn osahan-radio osahan-card-pay btn-light btn-sm rounded mb-2 w-100">
                                <input type="radio" name="options" id="cardId${ind}" checked>
                                <div class="d-flex align-items-center card-detials small mb-3">
                                    <p class="small"><i class="mdi mdi-chip"></i></p>
                                    <p class="ml-auto d-flex align-items-center">
                                        <span class="card-no mr-2"><i class="mdi mdi-circle"></i> <i
                                                class="mdi mdi-circle"></i> <i class="mdi mdi-circle"></i> <i
                                                class="mdi mdi-circle"></i></span>
                                        <span class="small">${Math.floor(Math.random() * 999) + 1000}</span>
                                    </p>
                                </div>
                                <h1 class="mb-0 id=${ind} value=${el.company}">${el.company}</h1>
                                <p class="small mb-1 id=${ind} value=${el.level}">${el.level}</p>
                                <p class="text-right mb-1 id=${ind} value=${el.cardType}">${el.cardType}</i></p>
                            </label>
                        `)
                        ind += 1;
                    });
                    $("#cardId1").closest(".osahan-card-pay").addClass("active")
                }
    })
    $(document).on("click", ".addCard", function() {
        console.log(this);
        let data = {
            company: $("#inputCompany").val(),
            cardType: $("#inputCardType").val(),
            level: $("#inputLevel").val()
        }
        $.ajax({
            method: "POST",
            url: hostCard + `/add`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            headers: {"Authorization": "Bearer " + accessToken}
        }).done(function(data){
            location.reload();
        })
    });
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

