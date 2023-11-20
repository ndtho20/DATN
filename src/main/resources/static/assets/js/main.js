// <script>
//     $(document).ready(function(){
//
// // handel quantities
// // handel quantity plus
// $('.product__quantity-plus').click(function(e){
//     var btnplus = e.target
//     var inputquan = btnplus.parentElement.parentElement.children[1]
//     var valdef = inputquan.value;
//     var valne =  parseInt(valdef) + 1;
//     inputquan.value = valne;
//     // alert(valne)
// });
//
// //handel quantity minus
// $('.product__quantity-minus').click(function(e){
//     var btnplus = e.target
//     var input = btnplus.parentElement.parentElement.children[1]
//     var valdef = input.value;
//     var valne =  parseInt(valdef) - 1;
//     input.value = valne;
// });
//
// // /// handle with cart page
// $('.btnMinus').click(function(){
//     var idPro = $(this).data('id_session');
//     var valPush = -1
//     var idSession =  $('.idSession-' + idPro).val()
//     // alert(valMinus)
//     console.log(idSession)
//
//     $.ajax({
//         url: "/website__mvc/cart/update",
//         method: "POST",
//         data: {valPush: valPush, idSession: idSession},
//         success: function(data){
//             location.reload()
//             // alert(data)
//         }
//     })
//
// })
//
// $('.btnPlus').click(function(){
//     var valPush = 1
//     var idPro = $(this).data('id_session');
//     var idSession =  $('.idSession-' + idPro).val()
//     console.log(idSession)
//
//     $.ajax({
//         url: "/website__mvc/cart/update",
//         method: "POST",
//         data: {valPush: valPush, idSession: idSession},
//         success: function(data){
//             location.reload()
//             // alert(data)
//         }
//     })
//
// })
//
//
// $('.cart__product-actions').click(function(){
//     var idPro = $(this).data('id_session');
//     var idSession =  $('.idSession-' + idPro).val()
//     $.ajax({
//         url: "/website__mvc/cart/delete",
//         method: "POST",
//         data: {idSession: idSession},
//         success: function(data){
//             location.reload()
//             // console.log(data)
//         }
//     })
// })
//
//
//
//
// // const value = $('.cartQuantity').val();
//
// // value.change(function(){
// //     alert('aaaaa')
// // })
//
// $('.header__right-cart').click(function(){
//     $('.modals').removeClass('disabled');
//     // alert('aaaaa')
// });
//
//
// $('#modals__bnt-close').click(function(){
//     $('.modals').addClass('disabled');
// })
//
//
// $(".product__btn-buy").click(function(e){
//     // sessionStorage.clear();
//     e.preventDefault();
//     var id = $(this).data("id");
//     var idPro = parseInt($('.product__id-' + id).val());
//     var namePro = $('.product__name-' + id).val();
//     var imgPro = $('.product__img-' + id).val();
//     var pricePro = parseInt($('.product__priceSale-' + id).val());
//     var priceBase = parseInt($('.product__price-' + id).val());
//     var salePro = parseInt($('.product__salePro-' + id).val());
//     var qtyPro = parseInt($('.product__quantity-input').val());
//     var color =  parseInt($('.product__color-input:checked').val());
//     var size = parseInt($('.product__size-input:checked').val());
//     // alert(qtyPro)
//
//     $.ajax({
//         url: "/website__mvc/cart/add",
//         method: "POST",
//         data: {id: idPro, price: pricePro, namePro: namePro, quantityPro: qtyPro, colorPro: color, sizePro: size, imgPro: imgPro, priceBase: priceBase, salePro: salePro},
//         success: function(data) {
//             // reload();
//             location.reload();
//             console.log(data);
//         }
//     })
// });
// // $(".cartBtn").click(function(){
// //     var id = $(this).data("id");
// //     // var idPro = $('.product__id-' + id).val();
// //     // var namePro = $('.product__name-' + id).val();
// //     // var imgPro = $('.product__img-' + id).val();
// //     // var pricePro = $('.product__priceSale-' + id).val();
// //     // var quantityPro = 1;
//
// // });
//
//
// // xu ly input pass signup
//
// // const inputPass = $("#input__pass");
// // const eye = $(".signUp__icon")
// $(".signUp__icon").click(function(e){
//     e.target.classList.toggle('active')
//     let inputPa = e.target.parentElement.children[1];
//     // console.log(inputPa)
//     if(inputPa.type == 'password') {
//         inputPa.type = 'text'
//     }else {
//             inputPa.type = 'password'
//             }
//
// });
// $("#province").change(function(){
//     var province_id = $(this).val();
//     // alert(province_id);
//     $.ajax({
//         url: "/website__mvc/customers/district",
//         method: "POST",
//         data: {province_id:province_id},
//         success:function(data){
//         //    console.log(data);
//            $("#district").html(data)
//         }
//
//     })
// })
//
// $("#district").change(function(){
//     var district_id = $(this).val();
//     // alert(district_id);
//     $.ajax({
//         url: "/website__mvc/customers/wards",
//         method: "POST",
//         data: {district_id:district_id},
//         success:function(data){
//         //    console.log(data);
//            $("#wards").html(data)
//         }
//     })
// })
//
// $("#userInfo").click(function() {
//     $('.info').addClass('active');
// })
//
// ///
// const BaseImgUser = '<?php echo Base_URL.'/public/uploads/User/userFefault.jpg' ?>'
// if( $('.info__sibar-avartar').src == '' || $('.info__img').src == '' ){
//         $('.info__img').attr("src", BaseImgUser)
//         $('.info__sibar-avartar').attr("src", BaseImgUser)
//     }
// const fileReader = new FileReader()
// $('.info__img-btn').click(function(){
//     $('.info__img-input').click()
//     if( $('.info__img-input').val() == '' || $('.info__img').src == '' ){
//         $('.info__img').attr("src", BaseImgUser)
//         $('.info__sibar-avartar').attr("src", BaseImgUser)
//     }
//     $('.info__img-input').change(function(){
//         // alert($(this).val());
//         // $('.info__img').attr("src", '')
//         // $('.info__sibar-avartar').attr("src", '')
//         var file = this.files[0]
//         console.log(file)
//         fileReader.readAsDataURL(file)
//         fileReader.onload = function(e) {
//             $('.info__img').attr("src", e.target.result);
//             $('.info__sibar-avartar').attr("src", e.target.result)
//         }
//     })
//     // console.log($('.info__img-input').val())
//
// })
//
// $("#order").click(function() {
//     $('.location').removeClass('active')
//     $('.info').removeClass('active')
//     $('.order').addClass('active')
// })
//
// $("#location").click(function() {
//     $('.order').removeClass('active')
//     $('.info').removeClass('active')
//     $('.location').addClass('active')
// })
//
// $('#userInfo').click(function() {
//     $('.location').removeClass('active')
//     $('.order').removeClass('active')
//     $('.info').addClass('active')
// })
//
// const provinceVal =  $('#provinceVal').val()
// const districtVal =  $('#districtVal').val()
// const wardsVal =  $('#wardsVal').val()
// if(provinceVal != '' && districtVal != '' && wardsVal != ''){
//     $('#province').val(provinceVal)
//     $('#district').val(districtVal)
//     $('#wards').val(wardsVal)
// }
//
//
// $('.receipt__btn').click(function() {
//     var valDis = $('.receipt__input').val()
//     $.ajax({
//         url: "/website__mvc/order",
//         method: "POST",
//         data: {valDis:valDis},
//         success:function(data){
//             location.reload()
//         //    console.log(data)
//         }
//     })
//
// })
//
// //admin
//
// const statusVal = $('#statusVal').val();
// if(statusVal != ''){
//     $('#order_status').val(statusVal);
// }
//
// $('#order_btn').click(function(){
//     var order_status =  $('#order_status').val();
//     var order_code = $('#order_code').val();
//     // alert(order_status);
//     $.ajax({
//         url: "/website__mvc/admin/order/update",
//         method: "POST",
//         data: {order_status:order_status, order_code:order_code},
//         success:function(data){
//             console.log(data)
//             location.reload()
//         }
//     })
// })
//
// $('#order_delete').click(function(){
//     var order_code = $('#order_code').val();
//     $.ajax({
//         url: "/website__mvc/admin/order/delete",
//         method: "POST",
//         data: {order_code:order_code},
//         success:function(data){
//             console.log(data)
//             location.reload()
//         }
//     })
//
// })
// const categoryVal = $('#categoryVal').val();
// if(categoryVal != ''){
//     $('#category').val(categoryVal);
// }
//
// $('#category').change(function(){
//     var category_id = $(this).val();
//     $.ajax({
//         url: "/website__mvc/actions/category",
//         method: "POST",
//         data: {category_id:category_id},
//         success:function(data){
//         //    console.log(data);
//            $("#group").html(data)
//         }
//     })
// })
//
// $('#deleteImg').click(function(){
//     var idPro =  $('#idPro').val();
//     // alert(idPro);
//     $.ajax({
//         url: "/website__mvc/actions/deleteImg",
//         method: "POST",
//         data: {idPro:idPro},
//         success:function(data){
//            console.log(data);
//            location.reload();
//         }
//     })
// })
//
//
//
//
//
// });
// </script>
