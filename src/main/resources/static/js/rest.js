/**
 * restの挙動制御
 */

jQuery(function($){

	$('#btn-update').click(function(event){
		siteuserUpdate();
	});
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