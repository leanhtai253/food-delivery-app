let cartHost = "http://localhost:8481/cart";

$(document).ready(function() {
    $.ajax({
        method: "GET",
        url: cartHost + "/view-cart",
        headers: {"Authorization": "Bearer " + getCookie("access-token")},
        success: function(resp) {
            console.log(resp)
            let items = resp.data;
            let totalPrice = 0;
            if (items.length > 1) {
                $(".cartItemCount").text(`(${items.length} items)`)
            } else {
                $(".cartItemCount").text(`(${items.length} item)`)
            }
            items.forEach((item)=>{
                totalPrice += item.quantity * item.price;
                $("#cartItemList").append(`
                <div class="d-flex align-items-center mb-3 cartItem" value=${item.productId}>
                            <div class="mr-2">
                                <img src="img/food/${item.image}" class="img-fluid rounded cartItemImg" value="${item.image}" />
                            </div>
                            <div class="small text-black-50 cartItemQuantity" value=${item.quantity}>${item.quantity} x</div>
                            <div class="ml-2">
                                <p class="mb-0 text-black cartItemName" value="${item.name}">${item.name}</p>
                                <p class="mb-0 small cartItemPrice" value=${item.price}>${item.price} 000 VND</p>
                            </div>
                            <a class="removeItemCartBtn ml-auto"><i
                                    class="btn btn-light text-danger mdi mdi-trash-can-outline rounded"></i></a>
                </div>
                `)
            });
            $("#checkOutCartBtn").text(`Check out (${totalPrice} 000 VND)`);
            $("#confirmPaymentBtn").text(`Confirm payment (${totalPrice} 000 VND)`);
        }
    });
    $(document).on("click",".removeItemCartBtn", function(){
        console.log(this);
        let cartItem = $(this).closest(".cartItem");
        let cartItemData = {
            "productId" : $(cartItem).attr("value"),
            "quantity" : $(cartItem).find('.cartItemQuantity').attr("value"),
            "price": $(cartItem).find('.cartItemPrice').attr("value"),
            "name": $(cartItem).find('.cartItemName').attr("value"),
            "image": $(cartItem).find('.cartItemImg').attr("value")
        }
        $.ajax({
            method: "POST",
            url: cartHost + "/remove",
            data: JSON.stringify(cartItemData),
            contentType: "application/json; charset=utf-8",
            headers: {"Authorization": "Bearer " + getCookie("access-token")},
            error: function(resp) {
                console.log(resp)
            },
            success: function(resp) {
                $(cartItem).remove();
                $.ajax({
                    method: "GET",
                    url: cartHost + "/view-cart",
                    headers: {"Authorization": "Bearer " + getCookie("access-token")},
                    success: function(resp) {
                        let items = resp.data;
                        let totalPrice = 0;
                        if (items.length > 1) {
                            $(".cartItemCount").text(`(${items.length} items)`)
                        } else {
                            $(".cartItemCount").text(`(${items.length} item)`)
                        }
                        items.forEach((item)=>{
                            totalPrice += item.quantity * item.price;
                        });
                        $("#checkOutCartBtn").text(`Check out (${totalPrice} 000 VND)`);
                        $("#confirmPaymentBtn").text(`Confirm payment (${totalPrice} 000 VND)`);
                    }
                })
            }
            
        })
        
    }) 
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