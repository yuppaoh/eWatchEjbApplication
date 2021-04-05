
/* global Session */

$(document).ready(function () {
        
    function calcSubtotal() {
        var qty = $('#quantity').val();
        var price = $('#upri').val();
        var amout = qty*price;
        
        $('#total').html(amout+".0");
        $('#subtotal').html(amout+".0");
        
        $('#squantity').html;
    }

    $('#quantity').change(
            function () {
                calcSubtotal();
            }
    );
    
    calcSubtotal(); // khi chay lan dau tien, goi ham nay
});


