/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    function calcSubtotal() {
        var qty = $('#quantity').val();
        var price = $('$uni_price').val();
        var total = qty * price;
        $('#total').html(total);
        var mpay = $('#PaymentId').val();
        if (@feeShip > 0) {
            var fee = @feeShip * mpay) - 1;
        } else {
            var fee = @feeShip * mpay;
        }
        var subtotal = total + fee;
        $('#subtotal').html(subtotal);
        $('#pay').html(fee);
    }
    
    $('#PaymentId').change(
        function () {
            calcSubtotal();
        }
    );
    
    $('#Quantity').change(
        function () {
            calcSubtotal();
        }
    );
    
    calcSubtotal(); // khi chay lan dau tien, goi ham nay
});

