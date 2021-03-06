var update = {
	
		init : function () {
			var _this = this;
			
			$('.completed').click(function () {
				_this.updateStatus(this);
			});
			
		},
		
		updateStatus : function (_this) {
			var orderId = $(_this).val();			
			var jwt= $("#jwt").text();
			var orderUrl= $("#orderurl").text();
			
			$.ajax({
				type: 'PUT',
				url: orderUrl+'/v1/'+orderId,
				beforeSend: function (xhr) {
					xhr.setRequestHeader('Authorization', 'Bearer '+jwt);
				},
				dataType:'json',
				contentType: 'application/json; charset=utf-8',
				
			}).done(function() {
				alert('제조가 완료되었습니다.');
				window.location.href="/admin/management";
			}).fail(function (error) {
				alert(error.responseText);
			});
		}

}

update.init();