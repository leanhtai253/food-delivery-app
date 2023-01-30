let host = "http://foodappapi-env.eba-7inbxjki.ap-northeast-1.elasticbeanstalk.com";
let hostAddr = host + "/user/address";
$(document).ready(function(){
    let accessToken = getCookie("access-token");
    $.ajax({
        method: "GET",
        url: host + "/user/address/default-address",
        headers: {"Authorization": "Bearer " + accessToken},
        success: function(response) {
            console.log(response);
            let addr = response.data;
            let defaultAddress = addr.street + ", " + addr.state + ", " + addr.city;
            $("#defaultAddress").text(defaultAddress);
            $(".defaultAddress").text(defaultAddress);
        }
    })
    $.ajax({
        method: "GET",
        url: host + "/user/address/all",
        headers: {"Authorization": "Bearer " + accessToken},
        success: function(response) {
            let da = response.data;
            console.log(da);
            let countHome = 0;
            let countWork = 0;
            da.forEach(el => {
                if (el.addressType == 'Home') {
                    countHome += 1;
                    $("#home-addrs").append(`
                    <div addressNo=${el.number} type="button" data-dismiss="modal" class="addressItem d-flex align-items-center mb-2 border rounded p-2">
                        <div class="mr-3 bg-light rounded p-2 osahan-icon">
                            <i class="mdi mdi-home-variant-outline"></i>
                        </div>
                        <div class="w-100">
                            <p class="mb-0 font-weight-bold text-dark">Home ${countHome}</p>
                            <p class="mb-0 small">${el.street + ", " + el.state + ", " + el.city}</p>
                        </div>
                    </div>
                    `)
                } else {
                    countWork += 1
                    $("#work-addrs").append(`
                    <div addressNo=${el.number} type="button" data-dismiss="modal" class="addressItem d-flex align-items-center mb-2 border rounded p-2">
                        <div class="mr-3 bg-light rounded p-2 osahan-icon">
                            <i class="mdi mdi-home-variant-outline"></i>
                        </div>
                        <div class="w-100">
                            <p class="mb-0 font-weight-bold text-dark">Work ${countWork}</p>
                            <p class="mb-0 small">${el.street + ", " + el.state + ", " + el.city}</p>
                        </div>
                    </div>
                    `)
                }
            });
            $("#home-addr-tab").text(`Home (${countHome})`);
            $("#work-addr-tab").text(`Work (${countWork})`);
        }
    });
    $(document).on("click", ".addressItem", function() {
        console.log($(this))
        let addressNo = $(this).attr("addressNo");
        console.log(addressNo);
        $.ajax({
            method: "POST",
            url: hostAddr + `/update/default/${addressNo}`,
            headers: {"Authorization": "Bearer " + accessToken}
        }).done(function(data){
            location.reload();
        })
    });
    $(document).on("click", "#home-addr-tab", function() {
        $(this).attr("aria-selected","true");
        $("#work-addr-tab").attr("aria-selected","false");
        $(this).removeClass("bg-light text-dark").addClass("bg-primary text-white");
        $("#work-addr-tab").removeClass("bg-primary text-white").addClass("bg-light text-dark");
    });
    $(document).on("click", "#work-addr-tab", function() {
        $(this).attr("aria-selected","true");
        $("#home-addr-tab").attr("aria-selected","false");
        $(this).removeClass("bg-light text-dark").addClass("bg-primary text-white");
        $("#home-addr-tab").removeClass("bg-primary text-white").addClass("bg-light text-dark");
    })
    $(document).on("click", "#addAddressButton", function() {
        console.log($(this))
        let newAddr = {
            addressType: "Home",
            state: $("#inputState").val(),
            city: $("#inputCity").val(),
            street: $("#inputStreet").val()
        }
        
        if ($("#work-addr-option").closest(".address-options").hasClass("active")) {
            newAddr.addressType = "Work"
        }
        console.log(newAddr);
        $.ajax({
            method: "POST",
            url: hostAddr + '/add',
            data: JSON.stringify(newAddr),
            contentType: "application/json; charset=utf-8",
            headers: {"Authorization": "Bearer " + accessToken},
            success: function(data){
                // console.log(data)
                location.reload()
            }
        })
    });
})

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

