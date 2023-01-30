let hostCard = "http://foodappapi-env.eba-7inbxjki.ap-northeast-1.elasticbeanstalk.com/search";
$(document).ready(function(){
    let accessToken = getCookie("access-token");
    $(document).on("click", ".searchBtn", function() {
        let keySearch = $("#searchInput").val()
        $.ajax({
            method: "GET",
            url: hostCard + `/restaurant/${keySearch}`,
            headers: {"Authorization": "Bearer " + accessToken},
        }).done(function (data) {
            let da = data.data;
            $("#resSection").text(`RESTAURANTS(${da.length})`)
            let ind = 1;
            $.get("templateSearchList.html", function(content) {
            let searchList = $(content)
            da.forEach(function(el){
                        searchList.append(`
                            <div class="bg-light p-3 rounded">
                                <img src="img/restaurant/${el.imageRes}" class="img-fluid">
                            </div>
                            <div class="mx-3 py-2 w-100">
                                <p class="mb-2 text-black">${el.nameRes}</p>
                                <p class="small mb-2">
                                    <i class="mdi mdi-star text-warning mr-1"></i> <span
                                        class="font-weight-bold text-dark">${el.ratingRes}</span> (${da.length})
                                    <i class="mdi mdi-silverware-fork-knife ml-3 mr-1"></i> ${el.cateRes}
                                    <i class="mdi mdi-currency-inr ml-3"></i> 340/-
                                </p>
                                <p class="mb-0 text-muted d-flex align-items-center"><span
                                        class="badge badge-light"><i class="mdi mdi-truck-fast-outline"></i>
                                        Free delivery</span>
                                    <span class="small ml-auto"><i class="mdi mdi-map-marker"></i> 1.5
                                        km</span>
                                </p>
                            </div>
                            `)
                        $("#searchItem").html(searchList)
                    
                })
                ind += 1;
            });
            
        })
    });

    $(document).on("click", "#resSection", function() {
        let keySearch = $("#searchInput").val()
        $.ajax({
            method: "GET",
            url: hostCard + `/restaurant/${keySearch}`,
            headers: {"Authorization": "Bearer " + accessToken},
        }).done(function (data) {
            let da = data.data;
            $("#resSection").text(`RESTAURANTS(${da.length})`)
            let ind = 1;
            $.get("templateSearchList.html", function(content) {
            let searchList = $(content)
            da.forEach(function(el){
                        searchList.append(`
                            <div class="bg-light p-3 rounded">
                                <img src="img/restaurant/${el.imageRes}" class="img-fluid">
                            </div>
                            <div class="mx-3 py-2 w-100">
                                <p class="mb-2 text-black">${el.nameRes}</p>
                                <p class="small mb-2">
                                    <i class="mdi mdi-star text-warning mr-1"></i> <span
                                        class="font-weight-bold text-dark">${el.ratingRes}</span> (${da.length})
                                    <i class="mdi mdi-silverware-fork-knife ml-3 mr-1"></i> ${el.cateRes}
                                    <i class="mdi mdi-currency-inr ml-3"></i> 340/-
                                </p>
                                <p class="mb-0 text-muted d-flex align-items-center"><span
                                        class="badge badge-light"><i class="mdi mdi-truck-fast-outline"></i>
                                        Free delivery</span>
                                    <span class="small ml-auto"><i class="mdi mdi-map-marker"></i> 5.7
                                        km</span>
                                </p>
                            </div>
                            `)
                        $("#searchItem").html(searchList)
                    
                })
                ind += 1;
            });
            
        })
    });

    $(document).on("click", "#foodSection", function() {
        let keySearch = $("#searchInput").val()
        $.ajax({
            method: "GET",
            url: hostCard + `/food/${keySearch}`,
            headers: {"Authorization": "Bearer " + accessToken},
        }).done(function (data) {
            let da = data.data;
            $("#foodSection").text(`DISHES(${da.length})`)
            let ind = 1;
            $.get("templateSearchList.html", function(content) {
            let searchList = $(content)
            da.forEach(function(el){
                        searchList.append(`
                            <div class="bg-light p-3 rounded">
                                <img src="img/food/${el.imageDish}" class="img-fluid">
                            </div>
                            <div class="mx-3 py-2 w-100">
                                <p class="mb-2 text-black">${el.nameDish}</p>
                                <p class="small mb-2">
                                    <i class="mdi mdi-star text-warning mr-1"></i> <span
                                        class="font-weight-bold text-dark">${el.ratingDish}</span> (${da.length})
                                    <i class="mdi mdi-silverware-fork-knife ml-3 mr-1"></i> ${el.cateDish}
                                    <i class="mdi mdi-currency-inr ml-3"></i> 340/-
                                </p>
                                <p class="mb-0 text-muted d-flex align-items-center"><span
                                        class="badge badge-light"><i class="mdi mdi-truck-fast-outline"></i>
                                        Free delivery</span>
                                    <span class="small ml-auto"><i class="mdi mdi-map-marker"></i> 3.5
                                        km</span>
                                </p>
                            </div>
                            `)
                        $("#searchItem").html(searchList)
                    
                })
                ind += 1;
            });
            
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

