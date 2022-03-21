$(document).ready(function(){
		
	$(document).on("keyup", "#empid", function(){	
	console.log($('input[name="id"]').val());
	$.ajax({
			url : '/empAttendsnce/getAllById',
			type : 'GET',
			data: {id : $('input[name="id"]').val()},
			success : function(data) {
				$('#empAttendance').html("");
				$('#empAttendance').append(data);
				
			}, 
			error : function(jqXHR, status, errorThrown){
				
			}
		});	
		
	});	
		
});