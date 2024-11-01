$('document').ready(function() {
	//alert("deneme")
	/* resim modal start*/
	$(".table #userTable #btnResim").click(function(e) {
		e.preventDefault();
		//alert("deneme");
		var src = $(this).find("img").attr('src');
		$("#modalResim #userResim").attr('src', src.toString());
		$("#modalResim .modal-title").text("Resim")
		//$("#modalResim").modal();
	});
	/* resim modal end  */
	/* view modal start*/
	$(".table #userTable #btnView").click(function(e) {
		e.preventDefault();
		$("#modalView .modal-title").text("Genel Bilgiler");
		var href = $(this).attr('href');		
		$.get(href, function(user, status){
			$("#modalView #id").val(user.id);
			$("#modalView #isim").val(user.name);
			$("#modalView #email").val(user.email);
			$("#modalView #userResim").attr('src','data:image/*;base64,'+user.stringResim);
		});
			
	});
	/* view modal end  */
	/* edit modal start*/
		$(".table #userTable #btnEdit").click(function(e) {
			e.preventDefault();
			$("#modalEdit .modal-title").text("Genel Bilgiler Güncelle");
			var href = $(this).attr('href');		
			$.get(href, function(user, status){
				$("#modalEdit #id").val(user.id);
				$("#modalEdit #isim").val(user.name);
				$("#modalEdit #email").val(user.email);
				$("#modalEdit #userResim").attr('src','data:image/*;base64,'+user.stringResim);
			});
				
		});
		/* edit modal end  */
		/* edit modal change img satart*/
		$("#modalEdit #resim").change(function(){
			readURL(this);
		});
		
		/* edit modal change img satart*/
		/* main user form change img satart*/
		$("#mainUserForm #resim").change(function(){
			readURL(this);
		});
		/* main user form  change img satart*/


});
/* edit modal change img satart*/
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#modalEdit #userResim').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}
/* edit modal change img satart*/

/* main user form change img satart*/
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#mainUserForm #userFormResim').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}
/* main user form change img satart*/