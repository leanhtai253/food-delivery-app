let filtersHost = "http://localhost:8481";
let hostCat = filtersHost + "/categories";
let filterSubmission = {
    sortBy: "popularity",
    price: "desc"
};
$(document).ready(function(){
    $.ajax({
        method: "GET",
        url: hostCat + "/all/with-ids",
        headers: {"Authorization": "Bearer " + getCookie("access-token")},
        success: function(data) {
            let da = data.data;
            let ind = 1;
            da.forEach(function(el){
                $("#categoryModalList").append(`
                    <label class="btn osahan-radio btn-light btn-sm rounded">
                        <input type="radio" name="category-options" id=${ind} value=${el.number} />
                        ${el.name}
                    </label>
                `)
                ind += 1;     
            });
        }
    });
    $(document).on("click","#filterApplyBtn", function(){
        let sortBy = $('input[name="overall-options"]:checked').val();
        let price = $('input[name="filter-price-options"]:checked').val();
        let cat = parseInt($('input[name="category-options"]:checked').val());
        let filterSubmission = {
            sortBy: sortBy,
            price: price,
            cat: cat
        };
        // Cookies.set("listingsFilter", JSON.stringify(filterSubmission));
        setCookie("listingsFilter", JSON.stringify(filterSubmission));
        window.location.href="listing.html";
        // if (cat == 0) {
        //     $.ajax({
        //         method: "GET",
        //         url: listingsHost + `/all?sortBy=${sortBy}&price=${price}`,
        //         contentType: "application/json; charset=utf-8",
        //         headers: {"Authorization": "Bearer " + getCookie("access-token")},
        //         success: function(data) {
        //             console.log(data)
        //         }
        //     })
        // } else {

        // }

        console.log(filterSubmission);
    });
    $(document).on("click","#option-price-asc", function(){
        filterSubmission.price = "asc";
        console.log("asc")
    });
    $(document).on("click","#option-price-desc", function(){
        filterSubmission.price = "desc";
    });
    $(document).on("click","#categoryModelItem", function(){
        chosenCategory = $(this).attr("id")
    });
});

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
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
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}