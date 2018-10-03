var profWrapper = (function() {
	return {
        loadMyProfile : function() {
        	console.log(111);
			$.ajax({
				type : 'GET',
				url : 'getMyProfile',
				success : function(data) {
					$('#name').text(data.name);
					$('#id').text(data.id);
					$('#email').text(data.email);
					$('#mobile').text(data.mobile);
					$('#city').text(data.city);
					$('#profImg').attr("src", "data:image/png;base64,"+data.photo);
					$('#signImg').attr("src", "data:image/png;base64,"+data.sign);
				}
			});
		},
		uploadPhotoSign : function() {
			event.preventDefault();
			 // Get form
	        var form = $('#fileUploadForm')[0];
	        
			// Create an FormData object 
	        var formData = new FormData(form);
	        formData.append("id", $("#id").text());
	        
			$.ajax({
				type: "POST",
	            enctype: 'multipart/form-data',
	            url: "uploadPhotoSign",
	            data: formData,
	            processData: false, // prevent jQuery from processing data into a query string
	            contentType: false,
	            cache: false,
	            timeout: 600000,
	            headers: {'X-CSRF-Token': $('#csrfTkn').val()},
				success: function(data) {
					$('#fileUploadForm')[0].reset();
					$('#imageModal').modal('hide');
					profWrapper.loadMyProfile();
					alertify.success("Uploaded successfully.");
				},
				error: function(e) {
					console.log(e.responseText);
				}
			});
		}
	};
})();

$(document).ready(function() {
	
	// profWrapper.laodMyProfile();
	$("#imageModal").on("hidden.bs.modal", function() {
		$('#fileUploadForm')[0].reset();
	});
});