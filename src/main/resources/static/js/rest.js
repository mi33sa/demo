/**
 * restの挙動制御
 */

jQuery(function($){


	$('#btn-update').click(function(event){
		siteuserUpdate();
	});

/*	$('#btn-delete').click(function(event){
		var value = $(this).val;
		siteuserDelete(value);
	});*/

})

function siteuserUpdate(){

	var formData = $('#user-form').serializeArray();

	$.ajax({
		type:"PUT",
		cache:false,
		url:'/rest/update',
		data:formData,
		dataType:'json'
	}).done(function(data){
		alert('ユーザを更新しました');
		window.location.href='/';

	}).fail(function(jqXHR, textstatus, errorThrown){
		alert('ユーザ更新に失敗しました');

	}).always(function(){

	});
}

function siteuserDelete(id){

	let token = $("meta[name='_csrf']").attr("content");
	let header = $("meta[name='_csrf_header']").attr("content");

    $(document).ajaxSend(function(e, xhr, options) {
      xhr.setRequestHeader(header, token);
    });

	$.ajax({
		type:"DELETE",
		cache:false,
		url:'/rest/delete/' + id,
		contentType : "application/json",
		data:null,
		dataType:'json'
	}).done(function(data){
		alert('ユーザを削除しました');
		window.location.href='/list';

	}).fail(function(jqXHR, textstatus, errorThrown){
		alert('ユーザ削除に失敗しました');

	}).always(function(){

	});
}