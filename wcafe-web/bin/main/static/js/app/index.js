var action = {
		
		init : function () {
			var _this = this;
			
			$('#orderSave').click(function () {
				_this.save();
			});
		},
		
		
		save : function () {
			var details = [];
			var getBasket = $(".basket");
			
			getBasket.children('.orderdetail').each(function () {
				console.log(jQuery(this).find('.selectedType').attr('value'));
				var options=[];
				opts = jQuery(this).find('.selectedOption');
				opts.each(function () {
					options.push(jQuery.parseJSON($(this).attr('value')));
				});
				console.log(options);
				var detail={
						price: parseInt(jQuery(this).find('.detailCost').text()),
						quantity: parseInt(jQuery(this).find('.detailQuantity').attr('value')),
						productId: parseInt(jQuery(this).find('.detailProduct').text()),
						type: jQuery.parseJSON(jQuery(this).find('.selectedType').attr('value')),
						options: options
					};
				details.push(detail);
			});
			
			var order = {
					userId:  $('#userId').text(),
					price: parseInt($('#totalCost').text()),
					details: details
			};
			
			var jwt= $("#jwt").text();
			var orderUrl= $("#orderurl").text();
			
			$.ajax({
				type: 'POST',
				url: orderUrl+'/v1',
				beforeSend: function (xhr) {
					xhr.setRequestHeader('Authorization', 'Bearer '+jwt);
				},
				dataType:'json',
				contentType: 'application/json; charset=utf-8',
				data: JSON.stringify(order)
				
			}).done(function() {
				alert('주문이 완료되었습니다.');
				window.location.href="/";
			}).fail(function (error) {
				alert(JSON.stringify(error));
			});
		}
		

};

action.init();