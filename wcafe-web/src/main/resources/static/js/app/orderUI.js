var ui = {
		
		init : function () {
			var _this = this;
			
			$(function(){
				_this.activated();
			});
			
			$('.menuToBasket').click(function () {
				if($('#orderSave').attr('disabled',true)) $('#orderSave').attr('disabled',false);
				_this.addToBasket(this);
				if($(this).text()=="담기") alert("주문리스트에 메뉴를 담았습니다.");
			});
			
			$('.basket').on('click','.rmdetail',function () {
				_this.rmFromBasket(this);
			});			
			
			$(document).on('click', '.number-spinner button', function () {    
				_this.inputSpinner(this);
			});
			
			$('#refresh').click(function () {
				location.reload();
			});
					
		},
		
		activated : function () {
			var nav = $('.nav-tabs .nav-link:first');
			nav.addClass('active');
			nav.attr('aria-selected','true');
			
			var pane = $('.tab-pane:first');
			pane.addClass('show active');
			
			var type = $('.typecheck label:first-child');
			type.addClass('active');
			
			var hot = $('.HOT');
			hot.addClass('btn-outline-danger');
			
			var ice = $('.ICE');
			ice.addClass('btn-outline-primary');
			
		},
		
		addToBasket : function (_this) {			
			var parent = $(_this).parents('div.collapse');
			var selected = "";
			
			var quantity = parseInt(parent.find('.quantity').val());
			var tq = parseInt($('#orderList').val())+quantity;
			
			var product = jQuery.parseJSON(parent.find('.d-none').text());
			selected+=product.id;
			
			var cost = quantity*product.price;
			var tc = parseInt($('#totalCost').text())+product.price*quantity;
			
			var typeN = parent.find('.types:checked');
			var type;
			if(typeN.length==0){
				type=jQuery.parseJSON(parent.find('.types:first').val());
			}
			else{
				type=jQuery.parseJSON(typeN.val());
				selected+=type.id;
			}
			
			
			var options = parent.find('.options:checked');
			var appendedOptions="";
			options.each(function () {
				var option=jQuery.parseJSON(jQuery(this).val());
				selected+=option.id;
				appendedOptions+="<span class=\"selectedOption\" value="+JSON.stringify(option)+">"+option.name+"</span>";
			});
			
			var alreadySelected = $('.basket').find('.product'+selected);
			
			if(alreadySelected.length==0){
				$('.basket').append(
						"<div class=\"mb-1 orderdetail card product"+selected+"\">" +
							"<div class=\"card-body\">" +
								"<div class=\"d-flex\">"+
									"<h5 class=\"p-2\">"+
											"<strong>"+product.name+"</strong>"+
											"<font class=\"ml-2 selectedType "+type.name+"\" value="+JSON.stringify(type)+">"+type.name+"</font>"+
									"</h5>" +
									"<a herf class=\"close ml-auto rmdetail\" vertical-align=\"super\">&times;</a>"+
								"</div>"+
								"<div class=\"d-flex\">" +
									"<div class=\"p-2\">" +
										appendedOptions+
									"</div>"+
									"<div class=\"ml-auto p-2\">"+
										"<span class=\"detailQuantity\" value=\""+quantity+"\">"+quantity+"</span>잔, ₩<span class=\"detailCost\">"+cost+"</span>" +
									"</div>"+ 
								"</div>"+ 
							"</div>"+
							"<div class=\"d-none detailProduct\">"+product.id+"</div>"+
						"</div>");
			}
			else{
				var q =  cls.find('.detailQuantity');
				var c= cls.find('.detailCost');
				quantity += parseInt(q.attr('value'));
				cost = quantity*product.price;
				q.attr('value',quantity);
				q.text(quantity);
				c.text(cost);				
			}
			
			$('#orderList').text(tq);
			$('#orderList').val(tq);
			$('#listCount').text(tq);
			$('#totalCost').text(tc);
		},
	
		rmFromBasket : function (_this) {
			var parent = $(_this).parents('div.orderdetail');
			var cost = parseInt($('#totalCost').text())-parseInt(parent.find('.detailCost').text());
			var n = parseInt($('#orderList').val())-parseInt(parent.find('.detailQuantity').text());
			
			$(_this).parents('div.orderdetail').remove();
			$('#orderList').text(n);
			$('#orderList').val(n);
			$('#listCount').text(n);
			$('#totalCost').text(cost);
			
			if(n==0) $('#orderSave').attr('disabled',true);
		},
		
		inputSpinner : function (_this) {
			var btn = $(_this),
				oldValue = btn.closest('.number-spinner').find('input').val().trim(),
				newVal = 0;
			
			if (btn.attr('data-dir') == 'up') {
				newVal = parseInt(oldValue) + 1;
			} else {
				if (oldValue > 1) {
					newVal = parseInt(oldValue) - 1;
				} else {
					newVal = 1;
				}
			}
			btn.closest('.number-spinner').find('input').val(newVal);
			
		}
		
};

ui.init();