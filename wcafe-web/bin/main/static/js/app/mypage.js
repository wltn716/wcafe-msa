var myOrders = {
		
    init : function () {
        var _this = this;

        $(function(){
            _this.activated();
        });
                
    },

    activated : function () {
        $(function() {
            $('input[name="daterange"]').daterangepicker({
              opens: 'right'
            }, function(start, end, label) {
                $("#history").empty();
                $("#price").empty();
                
                var from = new Date(start.format('YYYY-MM-DD'));
                from.setHours(0,0,0,0);
                var to = new Date(end.format('YYYY-MM-DD'));
                to.setHours(0,0,0,0);
                var orders = jQuery.parseJSON($("#orders").text());
                var when = new Date(end.format('YYYY-MM-DD'));
                when.setDate(when.getDate()+1);

                var price=0;
                var totalPrice=0;
                var len = orders.length;

                $.each(orders, function(i,order) {

                    var year = order.createdAt.date.year;
                    var month = order.createdAt.date.month;
                    var day = order.createdAt.date.day; 
                    var date = year+"-"+month+"-"+day;
                    var flag = new Date(date).getTime()!=when.getTime() ? true : false;
                    
                    if(flag){ 
                        $('#'+when.getTime()).text(price);
                        price=0;
                        when = new Date(date);
                    }
                    
                    if(when.getTime()>=from.getTime()){
                        if(when.getTime()<=to.getTime()){
                            if(flag){
                                $("#history").append(
                                    "<div class=\"d-flex datebox mt-2 p-2\">"+
                                        "<div>"+date+"</div>"+
                                        "<div class=\"ml-auto\" align=\"right\">"+
                                           "합계 ₩ "+"<span id=\""+when.getTime()+"\">"+"</span>"+
                                        "</div>"+
                                    "</div>"
                                );
                            }

                            var detailInfo="";
                            $.each(order.details, function(j,detail){
                                detailInfo+=
                                    "<div class=\"d-flex p-2\">"+
                                        "<div>["+detail.type.name+"] "+detail.product.name+"</div>"+
                                        "<div class=\"ml-auto\">₩ "+detail.product.price+"</div>"+
                                    "</div>";
                                price+=detail.product.price;
                                totalPrice+=detail.product.price;
                            });

                            $("#history").append(
                                detailInfo
                            );

                            if(i=len-1) {
                                $('#'+when.getTime()).text(price);
                                $('#price').text("₩ "+totalPrice);
                            }
                        }
                        else{
                            $('#price').text("₩ "+totalPrice);
                            return;
                        } 
                    }
                });

            });
          });
        
    },

    
};

myOrders.init();